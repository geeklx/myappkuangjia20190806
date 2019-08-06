package com.example.slbappreadbook.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.slbappcomm.viewpager.LxCoolViewPager;

public class MyCoolViewPager extends LxCoolViewPager {

    public MyCoolViewPager(Context context) {
        super(context);
    }

    public MyCoolViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}