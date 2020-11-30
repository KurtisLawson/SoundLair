package com.example.soundlair2.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.soundlair2.Models.AudioFile;
import com.example.soundlair2.Models.Song;
import com.example.soundlair2.R;

import java.io.File;

import static com.example.soundlair2.Views.MainActivity.audioFilesOnDevice;

public class TrackBuilderActivity extends AppCompatActivity {

    int currentSelectedTrackIndex = 0;

    // It's convenient while choosing a track to know what you're playing.
    MediaPlayer testTrackPlayer;
    int maxVolume = 200;
    int curVolume = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_builder);

        displayAudioInSongList();
    }

    public void displayAudioInSongList() {
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

        // Start playing the selected Track
        stopCurrentTrack();
        testTrackPlayer = MediaPlayer.create(this, Uri.fromFile(new File(targetTrack.getPath())));
        float vol = (float)(Math.log(maxVolume-curVolume)/Math.log(maxVolume));
        testTrackPlayer.start();
        testTrackPlayer.setVolume(vol , vol); // TODO Adjust volume with slider

        // Populate the text fields with the current playing song.


        // Store the current AudioTrack as a reference, so it can be sent back to the playlist.
        currentSelectedTrackIndex = id;
    }

    public void stopCurrentTrack() {
        if (testTrackPlayer != null) {
            testTrackPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopCurrentTrack();
    }
}