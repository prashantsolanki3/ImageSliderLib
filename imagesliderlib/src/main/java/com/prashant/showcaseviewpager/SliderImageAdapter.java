package com.prashant.showcaseviewpager;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Created by Prashant on 11/25/2015.
 */
public class SliderImageAdapter extends FragmentStatePagerAdapter {

    ArrayList<String> imageUrls;
    boolean openFullscreenActivity=false;
    public PaletteGenerator paletteGenerator=null;
    public HashMap<Integer,Palette> palettes=new HashMap<>();

    public SliderImageAdapter(FragmentManager fm) {
        super(fm);
        imageUrls = new ArrayList<>();
    }

    public SliderImageAdapter(FragmentManager fm, ArrayList<String> imageUrls, boolean openFullscreenActivity) {
        super(fm);
        this.imageUrls = imageUrls;
        this.openFullscreenActivity = openFullscreenActivity;
    }

    public SliderImageAdapter(FragmentManager fm, ArrayList<String> imageUrls, boolean openFullscreenActivity,PaletteGenerator paletteGenerator) {
        super(fm);
        this.imageUrls = imageUrls;
        this.openFullscreenActivity = openFullscreenActivity;
        this.paletteGenerator = paletteGenerator;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
        notifyDataSetChanged();
    }

    public void addimageUrl(String url){
        this.imageUrls.add(url);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        SliderImageFragment fragment = SliderImageFragment.newInstance(imageUrls, position, openFullscreenActivity);
        fragment.setOnPaletteGeneratedListener(paletteGenerator);
        return fragment;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }
}
