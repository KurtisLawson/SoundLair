package com.example.soundlair2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.soundlair2.Models.AudioFile;
import com.example.soundlair2.Models.Playlist;
import com.example.soundlair2.Models.Song;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;

    // Storage for the available songs on the device. Used for adding new songs to a Playlist.
    public static ArrayList<AudioFile> audioFilesOnDevice;

    // The currently selected playlist, and the songs therein.
    ArrayList<Playlist> playlists;
    ArrayList<Song> currentSongs;

    // Media player components
    MediaPlayer currentTrackPlayer;
    MediaPlayer ambientTrackPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolvePermissions();

        int id = 0;
        if (audioFilesOnDevice != null) {
            for(AudioFile file : audioFilesOnDevice) {
                final Button newButton = new Button(this);
                newButton.setText(file.getTitle());
                newButton.setId(id);

                newButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        playTrackAtId(newButton.getId());
                    }
                });

                LinearLayout ll = (LinearLayout)findViewById(R.id.layout_SongList);

                ll.addView(newButton);

                id++;
            }
        }

    }

    public void playTrackAtId(int id) {
        AudioFile targetTrack = audioFilesOnDevice.get(id);
        System.out.println("playTrackAtId || Playing track " + id + " : " + targetTrack.getPath());

        currentTrackPlayer = MediaPlayer.create(this, Uri.fromFile(new File(targetTrack.getPath())));
        currentTrackPlayer.start();
    }

    // Iterate through playlists, and make a tab for each of them. (VIEW)
    private void initPlaylistView() {
//        TabLayout tabLayout = findViewById(R.id.tabs_Playlist);
//        ViewPager viewPager = findViewById(R.id.pager_PlaylistPager);
//
//        // Call a presenter
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
////        viewPagerAdapter.addFragments(new SongFragment(), "Songs");
//
//        for (int i = 0; i < 10; ++i) {
//            viewPagerAdapter.addFragments(new PlaylistFragment(), "Playlist" + (i+1));
//        }
//
//        viewPager.setAdapter(viewPagerAdapter);
//
//        // Display fragments
//        tabLayout.setupWithViewPager(viewPager);
    }

    private void initSongView() {



    }


    // Get all Music Files from device storage (MODEL)
    public ArrayList<AudioFile> getAudioFiles(Context context) {
        ArrayList<AudioFile> tempAudioList = new ArrayList<AudioFile>();
        System.out.println(" getAudioFiles || Checking Device for Music Files...");

        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        String[] projection = {
//                MediaStore.Audio.Media.ALBUM,
//                MediaStore.Audio.Media.TITLE,
//                MediaStore.Audio.Media.DURATION,
//                MediaStore.Audio.Media.DATA, // Used to get path
//                MediaStore.Audio.Media.ARTIST
//        };

        String storagePath = Environment.getExternalStorageDirectory().getPath() + "/Music/";
        File storageFile = new File(storagePath);
        Uri storageUri = Uri.fromFile(storageFile);

        File[] fileArray = storageFile.listFiles();

        for (int i = 0; i < fileArray.length; ++i) {

            String path = fileArray[i].getAbsolutePath();
            String title = "";
            String artist = "";
            String album = "";
            String duration = "";

            // Getting the name of the file ex. Temptation.mp3
            System.out.println(" \n  getAudioFiles || Getting new Audio File...");
            String fileNameArray[] = path.split("\\/");
            String fileName = fileNameArray[fileNameArray.length-1];

            // Splitting the file name from the extension ex. Temptation || mp3
            String fileExtensionArray[] = fileName.split("\\.");
            title = fileExtensionArray[0];

            if (fileExtensionArray[1].equals("mp3")) {
                System.out.println(" getAudioFiles || " + title + " Is a valid mp3 file");
                System.out.println(" getAudioFiles || File Path - " + path);
                System.out.println(" getAudioFiles || File Title - " + title);
                System.out.println(" getAudioFiles || File Extension - " + fileExtensionArray[1]);

                // Get the duration of the mp3 file
                MediaMetadataRetriever meta = new MediaMetadataRetriever();
                meta.setDataSource(path);
                duration = meta.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION); // TODO Convert from ms to seconds (if needed)

                AudioFile musicFile = new AudioFile(path, title, artist, album, duration);
                tempAudioList.add(musicFile);
            }
        }

        return tempAudioList;
    }

    private void resolvePermissions() {
        System.out.println("resolvePermissions || Attempting permission checker:");
        // Resolve the permission to write to external storage.
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("resolvePermissions || No permission granted.");
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_CODE);
        } else {
            Toast.makeText(this, "Media Permissions Granted !", Toast.LENGTH_SHORT).show();
            System.out.println("resolvePermissions || Successfully resolved Permissions.");
            audioFilesOnDevice = getAudioFiles(this);
            initPlaylistView(); // VIEW
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        System.out.println("resolvePermissions || In overridden method...");
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Handle permission
                System.out.println("resolvePermissions || Successfully resolved Permissions.");
                Toast.makeText(this, "Media Permissions Granted !", Toast.LENGTH_SHORT).show();
                audioFilesOnDevice = getAudioFiles(this);
                initPlaylistView(); // VIEW
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_CODE);
            }
        }
    }
}