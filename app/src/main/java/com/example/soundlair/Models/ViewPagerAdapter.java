package com.example.soundlair.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

// This should go in it's own folder
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> songFragments;
    private ArrayList<String> songTitles;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

        // Init the lists
        this.songFragments = new ArrayList<>();
        this.songTitles = new ArrayList<>();
    }

    public void addFragments(Fragment fragment, String title) {
        songFragments.add(fragment);
        songTitles.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return songFragments.get(position);
    }

    @Override
    public int getCount() {
        return songFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return songTitles.get(position);
    }
}
