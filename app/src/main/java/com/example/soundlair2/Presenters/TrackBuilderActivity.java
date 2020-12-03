package com.example.soundlair2.Presenters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.soundlair2.Models.AudioFile;
import com.example.soundlair2.R;

import java.io.File;

import static com.example.soundlair2.Presenters.MainActivity.ADD_ACTIVE_TRACK;
import static com.example.soundlair2.Presenters.MainActivity.ADD_AMBIENT_TRACK;
import static com.example.soundlair2.Presenters.MainActivity.audioFilesOnDevice;

public class TrackBuilderActivity extends AppCompatActivity {

    int currentSelectedTrackIndex = 0;
    boolean songSelected = false;

    // It's convenient while choosing a track to know what you're playing.
    MediaPlayer testTrackPlayer;
    int maxVolume = 200;
    int curVolume = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_builder);

        displayAudioInSongList();

        populateTextFields();
    }

    private void displayAudioInSongList() {
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

    private void populateTextFields() {
        Bundle extras = getIntent().getExtras();
        TextView functionType = (TextView) findViewById(R.id.txt_TrackBuildText);

        if (extras.getInt("REQUEST_CODE") == ADD_ACTIVE_TRACK) {
            functionType.setText("Add New Track");
        } else if (extras.getInt("REQUEST_CODE") == ADD_AMBIENT_TRACK) {
            functionType.setText("Add New Ambience");
        }
    }

    public void playTrackAtId(int id) {
        songSelected = true;
        AudioFile targetTrack = audioFilesOnDevice.get(id);
        System.out.println("playTrackAtId || Playing track " + id + " : " + targetTrack.getPath());

        // Start playing the selected Track
        stopCurrentTrack();
        testTrackPlayer = MediaPlayer.create(this, Uri.fromFile(new File(targetTrack.getPath())));
        float vol = (float)(Math.log(maxVolume-curVolume)/Math.log(maxVolume));
        testTrackPlayer.start();
        testTrackPlayer.setVolume(vol , vol); // TODO Adjust volume with slider

        // Populate the text fields with the current playing song.
        TextView title = (TextView) findViewById(R.id.txt_PlayingSong);
        title.setText(targetTrack.getTitle());

        // Store the current AudioTrack as a reference, so it can be sent back to the playlist.
        currentSelectedTrackIndex = id;
    }

    public void stopCurrentTrack() {
        if (testTrackPlayer != null) {
            testTrackPlayer.stop();
        }
    }

    public void returnSelectedTrack(View view) {
        Intent i = new Intent();

        i.putExtra("AUDIOINDEX", currentSelectedTrackIndex);

        if (songSelected) {
            System.out.println("returnSelectedTrack || Returning track id = " + i.getExtras().getInt("AUDIOINDEX"));
            setResult(RESULT_OK, i);
        } else {
            setResult(RESULT_CANCELED, i);
        }

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopCurrentTrack();
    }
}