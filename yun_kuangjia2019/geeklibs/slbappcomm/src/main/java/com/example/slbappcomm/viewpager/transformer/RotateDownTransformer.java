package com.example.slbappcomm.viewpager.transformer;

//import androidx.core.view.ViewPager;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class RotateDownTransformer implements ViewPager.PageTransformer {

    private static final float ROT_MOD = -15F;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1 || position > 1) {
            page.setRotation(0F);
        } else {
            final float width = page.getWidth();
            final float height = page.getHeight();
            final float rotation = ROT_MOD * position * -1.25F;
            page.setPivotX(width * 0.5F);
            page.setPivotY(height);
            page.setRotation(rotation);
        }
    }

}
