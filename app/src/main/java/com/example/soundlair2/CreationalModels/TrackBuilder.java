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

    // Song Builder
    public TrackBuilder setAudioFile(AudioFile file) {
        this.targetSong = file;
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
            return new AudioFile(path, title, artist, album, duration);
        } else {
            throw new IllegalStateException();
        }
    }

    public Song buildSong() {
        if (validateSongParameters()) {
            return new Song(targetSong);
        } else {
            throw new IllegalStateException();
        }
    }

    private boolean validateSongParameters() {
        return true;
    }

    private boolean validateAudioFileParameters() {
        return true;
    }
}
