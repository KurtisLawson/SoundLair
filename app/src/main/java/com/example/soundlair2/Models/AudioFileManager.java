package com.example.soundlair2.Models;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;

import com.example.soundlair2.CreationalModels.TrackBuilder;

import java.io.File;
import java.util.ArrayList;

public class AudioFileManager {
    // Storage for the available songs on the device. Used for adding new songs to a Playlist.
    public static ArrayList<AudioFile> audioFilesOnDevice;

    private Context appContext;
    private TrackBuilder builder;

    public AudioFileManager(Context context) {
        appContext = context;
        builder = new TrackBuilder();
        initAudioFiles();
    }

    private void initAudioFiles() {
        // Get all Music Files from device storage (MODEL)
        ArrayList<AudioFile> tempAudioList = new ArrayList<AudioFile>();
        System.out.println(" getAudioFiles || Checking Device for Music Files...");

        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String storagePath = Environment.getExternalStorageDirectory().getPath() + "/Music/";
        File storageFile = new File(storagePath);
        Uri storageUri = Uri.fromFile(storageFile);

        File[] fileArray = storageFile.listFiles();

        for (int i = 0; i < fileArray.length; ++i) {

            String path = fileArray[i].getAbsolutePath();
            String title = "";
            String artist = "";
            String album = "";
            String duration = "";

            // Getting the name of the file ex. Temptation.mp3
            System.out.println(" \n  getAudioFiles || Getting new Audio File...");
            String fileNameArray[] = path.split("\\/");
            String fileName = fileNameArray[fileNameArray.length-1];

            // Splitting the file name from the extension ex. Temptation || mp3
            String fileExtensionArray[] = fileName.split("\\.");
            title = fileExtensionArray[0];

            if (fileExtensionArray[1].equals("mp3")) {
                System.out.println(" getAudioFiles || " + title + " Is a valid mp3 file");
                System.out.println(" getAudioFiles || File Path - " + path);
                System.out.println(" getAudioFiles || File Title - " + title);
                System.out.println(" getAudioFiles || File Extension - " + fileExtensionArray[1]);

                // Get the duration of the mp3 file
                MediaMetadataRetriever meta = new MediaMetadataRetriever();
                meta.setDataSource(path);
                duration = meta.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION); // TODO Convert from ms to seconds (if needed)

                AudioFile musicFile = builder.setPath(path).setTitle(title).setArtist(artist).setAlbum(album).setDuration(duration).buildAudioFile();
                tempAudioList.add(musicFile);
            }
        }

        audioFilesOnDevice = tempAudioList;
    }
}
