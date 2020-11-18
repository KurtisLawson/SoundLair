package com.example.soundlair.Presenters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.soundlair.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AudioFileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AudioFileFragment extends Fragment {

    
    public AudioFileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        return view;
    }
}