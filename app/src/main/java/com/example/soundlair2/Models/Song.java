package com.example.soundlair2.Models;

public class Song {

    private AudioFile targetSong;
    private String startTimestamp;
    private String endTimestamp;

    public Song(AudioFile targetSong) {
        this.targetSong = targetSong;
    }

    public AudioFile getTargetSong() {
        return targetSong;
    }
}
