package com.example.soundlair2.Models;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;

public class Playlist {

    ArrayList<Song> activeSongList;
    ArrayList<Song> ambientSongList;

    // Media player components
    final int maxVolume = 100;

    MediaPlayer activeTrackPlayer;
    ArrayList<MediaPlayer> ambientTrackPlayers;

    public Playlist() {
        activeSongList = new ArrayList<Song>();
        ambientSongList = new ArrayList<Song>();
    }

    public ArrayList<Song> getActiveSongs() {
        return activeSongList;
    }

    public ArrayList<Song> getAmbientSongs() {
        return ambientSongList;
    }

    public void addNewActiveSong(Song newActiveSong) {
        activeSongList.add(newActiveSong);
    }

    public void addNewAmbientSong(Song newAmbientSong) {
        ambientSongList.add(newAmbientSong);
    }


    public void playTrackAtId(int id, Context context) {
        Song targetTrack = activeSongList.get(id);
        System.out.println("playTrackAtId || Playing track " + id + " : " + targetTrack.getTargetSong().getPath());

        stopCurrentTrack();
        activeTrackPlayer = MediaPlayer.create(context, Uri.fromFile(new File(targetTrack.getTargetSong().getPath())));
        activeTrackPlayer.start();
        float vol = (float) (maxVolume / 2) / maxVolume;
        activeTrackPlayer.setVolume(vol , vol); // TODO Adjust volume with slider
    }

    public void stopCurrentTrack() {
        if (activeTrackPlayer != null) {
            activeTrackPlayer.stop();
        }
    }

    public void stopAmbientTracks() {
        if (ambientTrackPlayers != null) {
            for (MediaPlayer player : ambientTrackPlayers) {
                player.stop();
            }
        }
    }


}
