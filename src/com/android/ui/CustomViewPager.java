package com.android.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * CustomViewPager is the custom ViewPager for the making sale Activity.
 * @author Sikarin	Larnamwong	5510546174
 *
 */
public class CustomViewPager extends ViewPager {
	/**
	 * isPagingEnabled is the paging enabled boolean.
	 */
	private boolean isPagingEnabled;
	/**
	 * Constructor of the class
	 * @param context is the Context of the Activity.
	 * @param attrs is the AttributeSet of the ViewPager.
	 */
    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isPagingEnabled = true;
    }

    /**
     * @see android.support.v4.view.ViewPager#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
       
        return false;
    }

    /**
     * @see android.support.v4.view.ViewPager#onInterceptTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        
        return false;
    }

    /**
     * set the enabled of the ViewPager.
     * @param b is the paging enabled boolean.
     */
    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = false;
    }

}
