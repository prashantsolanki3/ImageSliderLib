package com.prashant.showcaseviewpager.infi;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.util.AttributeSet;

/**
 * Created by Prashant on 11/26/2015.
 */
public class PaletteViewPager extends ViewPager {

    public PaletteViewPager(Context context) {
        super(context);
    }

    public PaletteViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addOnPageChangeListener(OnPageChangeListener listener) {
        super.addOnPageChangeListener(listener);
    }

    public Palette[] getPalettes(){
        return getPalettes();
    }
}
