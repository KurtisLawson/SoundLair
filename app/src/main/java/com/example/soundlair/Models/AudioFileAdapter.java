package com.example.soundlair.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soundlair.R;

import java.util.ArrayList;

public class AudioFileAdapter extends RecyclerView.Adapter<AudioFileAdapter.AudioViewHolder> {

    private Context context;
    private ArrayList<AudioFile> audioFiles;

    AudioFileAdapter(Context context, ArrayList<AudioFile> audioFiles) {
        this.context = context;
        this.audioFiles = audioFiles;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.audio_file, parent, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        holder.fileName.setText(audioFiles.get(position).getTitle());
//        holder.albumArt.setText(audioFiles.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return audioFiles.size();
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder {
        TextView fileName;
        ImageView albumArt;

        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);

            fileName = itemView.findViewById(R.id.txt_audioFileName);
            albumArt = itemView.findViewById(R.id.img_audioFileImg);
        }
    }
}
