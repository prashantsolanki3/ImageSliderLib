package com.prashant.showcaseviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.prashant.showcaseviewpager.infi.CircularViewPagerHandler;

import java.util.ArrayList;

import github.chenupt.springindicator.SpringIndicator;

/**
 *
 * Created by Prashant on 11/25/2015.
 */
public class ImageSliderFullScreen extends BaseImageSlider {


    public ImageSliderFullScreen(Context context) {
        super(context);
    }

    public ImageSliderFullScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageSliderFullScreen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    /**
     * @param fragmentManager #FragmentManager to initialize ImageSlider.
     * @param imageUrls       ArrayList of String containing the Urls of the images.
     */
    public void init(FragmentManager fragmentManager, ArrayList<String> imageUrls) {
        super.init(fragmentManager, R.layout.image_slider_fullscreen, R.styleable.ImageSliderFullScreen, imageUrls,false);
    }

    @Override
    protected void processAttrs(){

        if(attrArray==null)
            return;

        navigationIcons = attrArray.getBoolean(R.styleable.ImageSliderFullScreen_fs_NavigationIcons,false);
        showNavigationIcons(navigationIcons);
    }

}
