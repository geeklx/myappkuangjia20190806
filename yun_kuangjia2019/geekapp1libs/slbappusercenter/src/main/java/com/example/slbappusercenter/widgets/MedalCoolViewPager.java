package com.example.slbappusercenter.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.slbappcomm.viewpager.LxCoolViewPager;

public class MedalCoolViewPager extends LxCoolViewPager {

    public MedalCoolViewPager(Context context) {
        super(context);
    }

    public MedalCoolViewPager(Context context, AttributeSet attrs) {
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