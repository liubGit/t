package com.elephant.healthycat.healthycattest.util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by huxq17 on 2016/2/1.
 */
public class MyBannerViewPager extends ViewPager {
    public MyBannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBannerViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }

    private ViewGroup parent;

    public void setParent(ViewGroup parent) {
        this.parent = parent;
    }

}
