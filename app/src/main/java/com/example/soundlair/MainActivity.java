package com.example.soundlair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.example.soundlair.Models.AudioFile;
import com.example.soundlair.Models.Playlist;
import com.example.soundlair.Models.Song;
import com.example.soundlair.Models.ViewPagerAdapter;
import com.example.soundlair.Presenters.PlaylistFragment;
import com.example.soundlair.Presenters.SongFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;

    // Storage for the available songs on the device. Used for adding new songs to a Playlist.
    ArrayList<AudioFile> songsOnDevice;

    // The currently selected playlist, and the songs therein.
    ArrayList<Playlist> playlists;
    ArrayList<Song> currentSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolvePermissions();
    }

    // Iterate through playlists, and make a tab for each of them. (VIEW)
    private void initPlaylistView() {
        ViewPager viewPager = findViewById(R.id.pager_SongList);
        TabLayout tabLayout = findViewById(R.id.tabs_Playlist);

        // Call a presenter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//        viewPagerAdapter.addFragments(new SongFragment(), "Songs");

        for (int i = 0; i < 10; ++i) {
            viewPagerAdapter.addFragments(new PlaylistFragment(), "Playlist" + (i+1));
        }

        viewPager.setAdapter(viewPagerAdapter);

        // Display fragments
        tabLayout.setupWithViewPager(viewPager);
    }


    // Get all Music Files from device storage (MODEL)
    public ArrayList<AudioFile> getAudioFiles(Context context) {
        ArrayList<AudioFile> tempAudioList = new ArrayList<>();
        System.out.println(" getAudioFiles || Checking Device for Music Files...");

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA, // Used to get path
                MediaStore.Audio.Media.ARTIST
        };

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        System.out.println(" getAudioFiles || Created cursor " + cursor);
        if (cursor != null && cursor.moveToFirst()) {

            do {
                String album = cursor.getString(0);
                String title = cursor.getString(1);
                String duration = cursor.getString(2);
                String path = cursor.getString(3);
                String artist = cursor.getString(4);

                AudioFile musicFile = new AudioFile(path, title, artist, album, duration);
                System.out.println("getAudioFiles || SONG " + path + " | " + title);
                tempAudioList.add(musicFile);
            } while (cursor.moveToNext());

            System.out.println("getAudioFiles || Read Complete. Closing Cursor...");
            cursor.close();
        } else {
            System.out.println("getAudioFiles || NO SONGS FOUND");
        }

        return tempAudioList;
    }

    private void resolvePermissions() {
        // Resolve the permission to write to external storage.
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_CODE);
        } else {
            Toast.makeText(this, "Media Permissions Granted !", Toast.LENGTH_SHORT).show();
            songsOnDevice = getAudioFiles(this);
            initPlaylistView(); // VIEW
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Handle permission
                Toast.makeText(this, "Media Permissions Granted !", Toast.LENGTH_SHORT).show();
                songsOnDevice = getAudioFiles(this);
                initPlaylistView(); // VIEW
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_CODE);
            }
        }
    }

}