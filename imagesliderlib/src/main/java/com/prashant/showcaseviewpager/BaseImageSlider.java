package com.prashant.showcaseviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleableRes;
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
 * Created by Prashant on 11/26/2015.
 */
public abstract class BaseImageSlider extends RelativeLayout{

    View view;
    ViewPager viewPager;
    Context context;
    FragmentManager fragmentManager;
    ArrayList<String> imageUrls;
    TypedArray attrArray;
    SpringIndicator springIndicator;
    SliderImageAdapter sliderImageAdapter;
    boolean navigationIcons=true,openFullScreenActivity=false;
    AttributeSet attributeSet;
    ImageView leftNav,rightNav;
    PaletteGenerator paletteGenerator=null;

    public BaseImageSlider(Context context) {
        super(context);
        this.context=context;
    }

    public BaseImageSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        attributeSet = attrs;
        this.context=context;
    }

    public BaseImageSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attributeSet = attrs;
        this.context=context;

    }

    private void inflateViewFromXml(Context context,@LayoutRes int layoutId){
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(layoutId, this);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        leftNav = (ImageView) view.findViewById(R.id.navigationIconLeft);
        rightNav = (ImageView) view.findViewById(R.id.navigationIconRight);
        springIndicator = (SpringIndicator) view.findViewById(R.id.indicator);

        if(imageUrls.size()<=1)
            springIndicator.setVisibility(GONE);
    }


    /**
     * @param fragmentManager #FragmentManager to initialize ImageSlider.
     * @param imageUrls ArrayList of String containing the Urls of the images.
     * @param openFullScreenActivity to open fullscreen Activity on click.
     *                               Xml attribute is overridden.
     * */
    protected void init(FragmentManager fragmentManager,@LayoutRes int layoutId,@StyleableRes int[]stylable,ArrayList<String> imageUrls,boolean openFullScreenActivity){
        this.fragmentManager = fragmentManager;
        this.imageUrls = imageUrls;
        this.openFullScreenActivity = openFullScreenActivity;
        attrArray = context.obtainStyledAttributes(attributeSet,stylable);
        inflateViewFromXml(context, layoutId);
        processAttrs();
        initInternal();

    }

    /**
     * @param fragmentManager #FragmentManager to initialize ImageSlider.
     * @param imageUrls ArrayList of String containing the Urls of the images.
     * */
    protected void init(FragmentManager fragmentManager,@LayoutRes int layoutId,@StyleableRes int[]stylable, final ArrayList<String> imageUrls){
        this.fragmentManager = fragmentManager;
        this.imageUrls = imageUrls;
        attrArray = context.obtainStyledAttributes(attributeSet,stylable);
        inflateViewFromXml(context, layoutId);
        processAttrs();
        initInternal();
    }

    /**
     * @param fragmentManager #FragmentManager to initialize ImageSlider.
     * @param imageUrls ArrayList of String containing the Urls of the images.
     * @param openFullScreenActivity to open fullscreen Activity on click.
     *                               Xml attribute is overridden.
     * */
    protected void init(FragmentManager fragmentManager,@LayoutRes int layoutId,@StyleableRes int[]stylable,ArrayList<String> imageUrls,boolean openFullScreenActivity,PaletteGenerator paletteGenerator){
        this.fragmentManager = fragmentManager;
        this.imageUrls = imageUrls;
        this.paletteGenerator=paletteGenerator;
        this.openFullScreenActivity = openFullScreenActivity;
        attrArray = context.obtainStyledAttributes(attributeSet,stylable);
        inflateViewFromXml(context,layoutId);
        processAttrs();
        initInternal();

    }

    /**
     * @param fragmentManager #FragmentManager to initialize ImageSlider.
     * @param imageUrls ArrayList of String containing the Urls of the images.
     * */
    protected void init(FragmentManager fragmentManager,@LayoutRes int layoutId,@StyleableRes int[]stylable, final ArrayList<String> imageUrls, PaletteGenerator paletteGenerator){
        this.fragmentManager = fragmentManager;
        this.imageUrls = imageUrls;
        this.paletteGenerator=paletteGenerator;
        attrArray = context.obtainStyledAttributes(attributeSet,stylable);
        inflateViewFromXml(context, layoutId);
        processAttrs();
        initInternal();
    }

    private void initInternal(){
        sliderImageAdapter = new SliderImageAdapter(fragmentManager, imageUrls, openFullScreenActivity,paletteGenerator);
        viewPager.setAdapter(sliderImageAdapter);
        springIndicator.setViewPager(viewPager);
        springIndicator.setOnPageChangeListener(new CircularViewPagerHandler(viewPager));
        setOnClickListeners();
        ViewPagerUtils.init(viewPager, springIndicator);
    }

    protected void setOnClickListeners(){
        leftNav.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() == 0 ? imageUrls.size() - 1 : viewPager.getCurrentItem() - 1);
            }
        });
        rightNav.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() == imageUrls.size() - 1 ? 0 : viewPager.getCurrentItem() + 1);
            }
        });
    }

    public void setInitialPosition(int pos){
        viewPager.setCurrentItem(pos);
    }

    protected abstract void processAttrs();

    public void setNavigationIconDrawable(Drawable iconPrevious,Drawable iconNext){
        leftNav.setImageDrawable(iconPrevious);
        rightNav.setImageDrawable(iconNext);
    }

    public void showNavigationIcons(boolean enable){
        if(enable){
            leftNav.setVisibility(VISIBLE);
            rightNav.setVisibility(VISIBLE);
        }else{
            leftNav.setVisibility(GONE);
            rightNav.setVisibility(GONE);
        }
    }

    public int getCurrentPosition(){
        return viewPager.getCurrentItem();
    }

    public void setBackgroundColor(int color){
        ViewPagerUtils.setBackgroundColor(color);
    }
}
