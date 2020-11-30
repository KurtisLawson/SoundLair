package com.example.soundlair2.Models;

import java.util.ArrayList;

public class Playlist {

    ArrayList<Song> activeSongList;
    ArrayList<Song> ambientSongList;

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
}
