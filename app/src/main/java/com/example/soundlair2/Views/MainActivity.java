package com.example.soundlair2.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.soundlair2.Models.AudioFile;
import com.example.soundlair2.Models.Playlist;
import com.example.soundlair2.Models.Song;
import com.example.soundlair2.R;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_CODE = 1;

    public static final int ADD_ACTIVE_TRACK = 1;
    public static final int ADD_AMBIENT_TRACK = 2;

    // Storage for the available songs on the device. Used for adding new songs to a Playlist.
    public static ArrayList<AudioFile> audioFilesOnDevice;

    // A list of all playlists.
    ArrayList<Playlist> playlists;

    // The currently selected playlist, and the songs therein.
    Playlist currentPlaylist;

    // Media player components
    int maxVolume = 200;
    int curVolume = 200;
    MediaPlayer activeTrackPlayer;
    List<MediaPlayer> ambientTrackPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolvePermissions();

        initPlaylists(); // Model
        getSongsFromCurrentPlaylist(); // Model

        // Presenter
        updateListViews();

    }

    public void updateListViews() { // PRESENTER
        // 1. Track List
        int id = 0;
        LinearLayout trackLayout = (LinearLayout)findViewById(R.id.layout_TrackList);
        trackLayout.removeAllViews();

        if (currentPlaylist != null) {
            for(Song song : currentPlaylist.getActiveSongs()) {
                final Button newButton = new Button(this);
                newButton.setText(song.getTargetSong().getTitle());
                newButton.setId(id);

                newButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        playTrackAtId(newButton.getId());
                    }
                });

                trackLayout.addView(newButton);
                id++;
            }
        }

        // 2. Ambience List
        id = 0;
        LinearLayout ambientTracks = (LinearLayout)findViewById(R.id.layout_AmbientTrackList);
        ambientTracks.removeAllViews();

        if (currentPlaylist != null) {
            for(Song song : currentPlaylist.getAmbientSongs()) {
                final SeekBar newVolumeTrack = new SeekBar(this);
                newVolumeTrack.setId(id);

//                newButton.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        playTrackAtId(newButton.getId());
//                    }
//                });

                ambientTracks.addView(newVolumeTrack);
                id++;
            }
        }
    }

    public void playTrackAtId(int id) {
        Song targetTrack = currentPlaylist.getActiveSongs().get(id);
        System.out.println("playTrackAtId || Playing track " + id + " : " + targetTrack.getTargetSong().getPath());

        stopCurrentTrack();
        activeTrackPlayer = MediaPlayer.create(this, Uri.fromFile(new File(targetTrack.getTargetSong().getPath())));
        float vol = (float)(Math.log(maxVolume-curVolume)/Math.log(maxVolume));
        activeTrackPlayer.start();
        activeTrackPlayer.setVolume(vol , vol); // TODO Adjust volume with slider
    }

    public void stopCurrentTrack() {
        if (activeTrackPlayer != null) {
            activeTrackPlayer.stop();
        }
    }

    // Iterate through playlists, and make a tab for each of them. (VIEW)
    private void initPlaylists() {
        if (playlists == null || playlists.size() == 0) {
            playlists = new ArrayList<Playlist>();

            addNewActivePlayList(findViewById(android.R.id.content));
            getSongsFromCurrentPlaylist();
        }

        currentPlaylist = playlists.get(0);
    }

    private void getSongsFromCurrentPlaylist() {

    }

    // Functions to add a track to the list.
    public void addTrackToCurrentPlaylist(View view) {
        System.out.println("addTrackToCurrentPlaylist || Adding new active track.");

        Intent intent = new Intent(getApplicationContext(), TrackBuilderActivity.class);
        intent.putExtra("REQUEST_CODE", ADD_ACTIVE_TRACK);
        startActivityForResult(intent, ADD_ACTIVE_TRACK);
    }

    public void addAmbientTrackToCurrentPlaylist(View view) {
        System.out.println("addAmbientTrackToCurrentPlaylist || Adding new ambient track ");

        Intent intent = new Intent(getApplicationContext(), TrackBuilderActivity.class);
        intent.putExtra("REQUEST_CODE", ADD_AMBIENT_TRACK);
        startActivityForResult(intent, ADD_AMBIENT_TRACK);

    }

    public void addNewActivePlayList(View view){
        System.out.println("addNewPlayList || Adding new playlist. Bringing total to " + (playlists.size() + 1) + " playlist(s).");

        // 1. Get the tab view
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs_Playlist);

        // 2. Create a new tab, Populate it's fields.
        tabLayout.addTab(tabLayout.newTab().setText("New PlayList " + (playlists.size() + 1)));

        // 2. Create a new playlist.
        Playlist newPlaylist = new Playlist();
        playlists.add(newPlaylist);

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

    // ======= Activity result code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("onActivityResult || Active Track added. ");
        Bundle extras = data.getExtras();
        if (resultCode == RESULT_OK && extras != null) {
            int trackIndex = extras.getInt("AUDIOINDEX");

            if (requestCode == ADD_ACTIVE_TRACK){
                Song newTrack = new Song(audioFilesOnDevice.get(trackIndex));
                currentPlaylist.addNewActiveSong(newTrack);
                System.out.println("onActivityResult || Active Track added. ");

            } else if (requestCode == ADD_AMBIENT_TRACK) {

                Song newAmbience = new Song(audioFilesOnDevice.get(trackIndex));
                currentPlaylist.addNewAmbientSong(newAmbience);
                System.out.println("onActivityResult || Ambient Track added. ");
            }
        }

        updateListViews();
        // presenter.RefreshAndDisplay();

    }

    // ======= Permission Code
    private void resolvePermissions() {
        System.out.println("resolvePermissions || Attempting permission checker:");
        // Resolve the permission to write to external storage.
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("resolvePermissions || No permission granted.");
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, PERMISSION_REQUEST_CODE);
        } else {
            Toast.makeText(this, "Media Permissions Granted !", Toast.LENGTH_SHORT).show();
            System.out.println("resolvePermissions || Successfully resolved Permissions.");
            audioFilesOnDevice = getAudioFiles(this);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        System.out.println("resolvePermissions || In overridden method...");
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Handle permission
                System.out.println("resolvePermissions || Successfully resolved Permissions.");
                Toast.makeText(this, "Media Permissions Granted !", Toast.LENGTH_SHORT).show();
                audioFilesOnDevice = getAudioFiles(this);

            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopCurrentTrack();
    }
}