package com.example.slbapplistenbook.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.slbappcomm.viewpager.LxCoolViewPager;

public class ListenCoolViewPager extends LxCoolViewPager {

    public ListenCoolViewPager(Context context) {
        super(context);
    }

    public ListenCoolViewPager(Context context, AttributeSet attrs) {
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