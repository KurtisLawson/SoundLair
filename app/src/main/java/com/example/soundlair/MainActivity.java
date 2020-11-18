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
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.soundlair.Models.MusicFile;
import com.example.soundlair.Models.ViewPagerAdapter;
import com.example.soundlair.Presenters.PlaylistFragment;
import com.example.soundlair.Presenters.SongFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPlaylists(); // VIEW
        resolvePermissions();
    }

    // Iterate through playlists, and make a tab for each of them.
    private void initPlaylists() {
        ViewPager viewPager = findViewById(R.id.pager_SongList);
        TabLayout tabLayout = findViewById(R.id.tabs_Playlist);

        // Call a presenter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//        viewPagerAdapter.addFragments(new SongFragment(), "Songs");

        for (int i = 0; i < 10; ++i) {
            viewPagerAdapter.addFragments(new PlaylistFragment(), "Playlist" + i+1);
        }

        viewPager.setAdapter(viewPagerAdapter);

        // Display fragments
        tabLayout.setupWithViewPager(viewPager);
    }


    // Get all Music Files from device storage
    public static ArrayList<MusicFile> getAllAudio(Context context) {
        ArrayList<MusicFile> tempAudioList = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA, // Used to get path
                MediaStore.Audio.Media.ALBUM

        };

        return tempAudioList;
    }

    private void resolvePermissions() {
        // Resolve the permission to write to external storage.
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_CODE);
        } else {
            Toast.makeText(this, "Permission Granted.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Handle permission
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_CODE);
            }
        }
    }

}