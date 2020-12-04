package com.example.soundlair2.CreationalModels;

import com.example.soundlair2.Models.AudioFile;
import com.example.soundlair2.Models.Song;

public class TrackBuilder {

    // Audio File Parameters
    private String path;
    private String title;
    private String artist;
    private String album;
    private String duration;

    // Song parameters
    private AudioFile targetSong;
    private float startTimestamp;
    private float endTimestamp;

    public TrackBuilder() {

    }

    public TrackBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public TrackBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public TrackBuilder setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public TrackBuilder setAlbum(String album) {
        this.album = album;
        return this;
    }

    public TrackBuilder setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    // Song Builder
    public TrackBuilder setAudioFile(AudioFile file) {
        // Target file
        this.targetSong = file;

        // File fields
        this.path = targetSong.getPath();
        this.title = targetSong.getTitle();
        this.artist = targetSong.getArtist();
        this.album = targetSong.getAlbum();
        this.duration = targetSong.getDuration();
        return this;
    }

    public TrackBuilder setStartTimestamp(float startStamp) {
        this.startTimestamp = startStamp;
        return this;
    }

    public TrackBuilder setEndTimestamp(float endStamp) {
        this.endTimestamp = endStamp;
        return this;
    }

    public AudioFile buildAudioFile() {
        if (validateAudioFileParameters()) {
            targetSong = new AudioFile(path, title, artist, album, duration);
            return targetSong;
        } else {
            throw new IllegalStateException();
        }
    }

    private boolean validateAudioFileParameters() {
        return true;
    }

    public Song buildSong() {
        if (validateSongParameters()) {
            return new Song(targetSong);
        } else {
            throw new IllegalStateException();
        }
    }

    private boolean validateSongParameters() {
        boolean valid = false;

        if (targetSong != null){
            valid = true;
        }

        return valid;
    }


}
