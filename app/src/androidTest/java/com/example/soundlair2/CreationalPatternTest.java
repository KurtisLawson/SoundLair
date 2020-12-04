package com.example.soundlair2;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.soundlair2.CreationalModels.TrackBuilder;
import com.example.soundlair2.Models.AudioFile;
import com.example.soundlair2.Models.Song;
import com.example.soundlair2.Presenters.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CreationalPatternTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.soundlair2", appContext.getPackageName());
    }

    @Test
    public void GetPathFromTrackBuilder() {
        TrackBuilder builder = new TrackBuilder();

        AudioFile newFile = builder.setPath("/testPath").setTitle("testTitle").setAlbum("testAlbum").setArtist("testArtist").setDuration("28542").buildAudioFile();
        assertEquals("/testPath", newFile.getPath());
    }

    @Test
    public void GetTitleFromTrackBuilder() {
        TrackBuilder builder = new TrackBuilder();

        AudioFile newFile = builder.setPath("/testPath").setTitle("testTitle").setAlbum("testAlbum").setArtist("testArtist").setDuration("28542").buildAudioFile();
        assertEquals("testTitle", newFile.getTitle());
    }

    @Test
    public void GetAlbumFromTrackBuilder() {
        TrackBuilder builder = new TrackBuilder();

        AudioFile newFile = builder.setPath("/testPath").setTitle("testTitle").setAlbum("testAlbum").setArtist("testArtist").setDuration("28542").buildAudioFile();
        assertEquals("testAlbum", newFile.getAlbum());
    }

    @Test
    public void GetArtistFromTrackBuilder() {
        TrackBuilder builder = new TrackBuilder();

        AudioFile newFile = builder.setPath("/testPath").setTitle("testTitle").setAlbum("testAlbum").setArtist("testArtist").setDuration("28542").buildAudioFile();
        assertEquals("testArtist", newFile.getArtist());
    }

    @Test
    public void GetDurationFromTrackBuilder() {
        TrackBuilder builder = new TrackBuilder();

        AudioFile newFile = builder.setPath("/testPath").setTitle("testTitle").setAlbum("testAlbum").setArtist("testArtist").setDuration("28542").buildAudioFile();
        assertEquals("28542", newFile.getDuration());
    }

    @Test
    public void GetSongFromTrackBuilder() {
        TrackBuilder builder = new TrackBuilder();

        AudioFile newFile = builder.setPath("/testPath").setTitle("testTitle").setAlbum("testAlbum").setArtist("testArtist").setDuration("28542").buildAudioFile();
        Song newSong = builder.buildSong();

        assertEquals(true, newSong.getTargetSong().equals(newFile));
    }
}