package com.prashant.showcaseviewpager;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleableRes;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 *
 * Created by Prashant on 11/25/2015.
 */
public class ImageSlider extends BaseImageSlider {

    public ImageSlider(Context context) {
        super(context);
    }

    public ImageSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * @param fragmentManager        #FragmentManager to initialize ImageSlider.
     * @param imageUrls              ArrayList of String containing the Urls of the images.
     * @param openFullScreenActivity to open fullscreen Activity on click.
     *                               Xml attribute is overridden.
     */
    public void init(FragmentManager fragmentManager, ArrayList<String> imageUrls, boolean openFullScreenActivity) {
        super.init(fragmentManager,R.layout.image_slider, R.styleable.ImageSlider, imageUrls, openFullScreenActivity);
    }

    /**
     * @param fragmentManager #FragmentManager to initialize ImageSlider.
     * @param imageUrls       ArrayList of String containing the Urls of the images.
     */
    public void init(FragmentManager fragmentManager, ArrayList<String> imageUrls) {
        super.init(fragmentManager,R.layout.image_slider, R.styleable.ImageSlider, imageUrls);
    }

    /**
     * @param fragmentManager        #FragmentManager to initialize ImageSlider.
     * @param imageUrls              ArrayList of String containing the Urls of the images.
     * @param openFullScreenActivity to open fullscreen Activity on click.
     *                               Xml attribute is overridden.
     */
    public void init(FragmentManager fragmentManager, ArrayList<String> imageUrls, boolean openFullScreenActivity, PaletteGenerator paletteGenerator) {
        super.init(fragmentManager,R.layout.image_slider, R.styleable.ImageSlider, imageUrls, openFullScreenActivity, paletteGenerator);
    }

    /**
     * @param fragmentManager  #FragmentManager to initialize ImageSlider.
     * @param imageUrls        ArrayList of String containing the Urls of the images.
     * @param paletteGenerator
     */
    public void init(FragmentManager fragmentManager, ArrayList<String> imageUrls, PaletteGenerator paletteGenerator) {
        super.init(fragmentManager,R.layout.image_slider, R.styleable.ImageSlider, imageUrls, paletteGenerator);
    }

    @Override
    protected void processAttrs() {
        if(attrArray==null)
            return;
        navigationIcons = attrArray.getBoolean(R.styleable.ImageSlider_is_NavigationIcons,true);
        openFullScreenActivity = attrArray.getBoolean(R.styleable.ImageSlider_is_OnClickOpenActivity,true);
        showNavigationIcons(navigationIcons);
    }





}
