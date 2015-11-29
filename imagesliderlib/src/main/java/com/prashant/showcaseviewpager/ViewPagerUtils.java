package com.prashant.showcaseviewpager;

import android.graphics.Color;
import android.support.v4.view.ViewPager;

import github.chenupt.springindicator.SpringIndicator;

/**
 * Created by Prashant on 11/26/2015.
 */
public class ViewPagerUtils {

    private static ViewPager viewPager;
    private static SpringIndicator springIndicator;

    public static void init(ViewPager viewPager){
        ViewPagerUtils.viewPager=viewPager;
    }

    public static void  init(SpringIndicator springIndicator){
        ViewPagerUtils.springIndicator = springIndicator;
    }

    public static void init(ViewPager viewPager,SpringIndicator springIndicator){
        ViewPagerUtils.springIndicator=springIndicator;
        ViewPagerUtils.viewPager=viewPager;
    }

    public static void setBackgroundColor(int backgroundColor){
        viewPager.setBackgroundColor(backgroundColor);
    }
}
