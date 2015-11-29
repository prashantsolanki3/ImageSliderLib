package com.prashant.showcaseviewpager.infi;

import android.support.v4.view.ViewPager;
import android.util.Log;

public class CircularViewPagerHandler implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private int         mOldPosition;
    private int         mScrollState;

    public CircularViewPagerHandler(final ViewPager viewPager) {
        mViewPager = viewPager;
    }

 
    @Override 
    public void onPageSelected(final int position) {

    }

    @Override 
    public void onPageScrollStateChanged(final int state) {
        handleScrollState(state);
        if(state==ViewPager.SCROLL_STATE_DRAGGING)
        mOldPosition = mViewPager.getCurrentItem();
        mScrollState = state;
    } 
 
    private void handleScrollState(final int state) {

        if (state == ViewPager.SCROLL_STATE_IDLE) {

            setNextItemIfNeeded();
        } 
    } 
 
    private void setNextItemIfNeeded() { 
        if (!isScrollStateSettling()) { 
            handleSetNextItem(); 
        } 
    } 
 
    private boolean isScrollStateSettling() { 
        return mScrollState == ViewPager.SCROLL_STATE_SETTLING;
    } 
 
    private void handleSetNextItem() {
        Log.d("Pos","old:"+mOldPosition+" scrolledTo:"+scrolledToPos);
        final int lastPosition = mViewPager.getAdapter().getCount() - 1;
        if(mOldPosition == lastPosition && scrolledToPos !=mOldPosition-1) {
            mViewPager.setCurrentItem(0, false);
        } else if(mOldPosition == 0 && scrolledToPos != 1) {
            mViewPager.setCurrentItem(lastPosition, false);
        } 
    }


    int scrolledToPos;
    /**
     * This method will be invoked when the current page is scrolled, either as part
     * of a programmatically initiated smooth scroll or a user initiated touch scroll.
     *
     * @param position             Position index of the first page currently being displayed.
     *                             Page position+1 will be visible if positionOffset is nonzero.
     * @param positionOffset       Value from [0, 1) indicating the offset from the page at position.
     * @param positionOffsetPixels Value in pixels indicating the offset from position.
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        scrolledToPos = position;
    }


}