package com.haier.cellarette.baselibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class LxRelativeLayout extends RelativeLayout {

    private boolean itouch = false;

    public LxRelativeLayout(Context context) {
        super(context);
    }

    public LxRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LxRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return itouch;
    }

    public void setTouch(boolean is) {
        itouch = is;
    }
}
