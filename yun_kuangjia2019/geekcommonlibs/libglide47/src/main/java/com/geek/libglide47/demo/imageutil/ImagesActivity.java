package com.geek.libglide47.demo.imageutil;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
//import androidx.core.view.ViewPager;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.viewpager.widget.ViewPager;

import com.geek.libglide47.R;
import com.geek.libglide47.base.util.DisplayUtil;
import com.geek.libglide47.demo.adapter.ImagesAdapter;
import com.geek.libglide47.demo.commonutil.ColorUtil;
import com.geek.libglide47.demo.commonutil.StatusBarUtil;
import com.geek.libglide47.demo.domain.ImageAttr;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

public class ImagesActivity extends Activity implements ViewTreeObserver.OnPreDrawListener {

    public static final String IMAGE_ATTR = "image_attr";
    public static final String CUR_POSITION = "cur_position";
    public static final int ANIM_DURATION = 300; // ms

    private RelativeLayout rootView;
    private ViewPager viewPager;
    private TextView tvTip;
    private ImagesAdapter mAdapter;
    private List<ImageAttr> imageAttrs;
    private boolean isAnimating;

    private int curPosition;
    private int screenWidth;
    private int screenHeight;
    private float scaleX;
    private float scaleY;
    private float translationX;
    private float translationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_image_preview);
        StatusBarUtil.setStatusBarTranslucent(this, false);

        viewPager = findViewById(R.id.viewPager);
        tvTip = findViewById(R.id.tv_tip);
        rootView = findViewById(R.id.rootView);
        screenWidth = DisplayUtil.getScreenWidth(this);
        screenHeight = DisplayUtil.getScreenHeight(this);

        Intent intent = getIntent();
        imageAttrs = (List<ImageAttr>) intent.getSerializableExtra(IMAGE_ATTR);
        curPosition = intent.getIntExtra(CUR_POSITION, 0);
        tvTip.setText(String.format(getString(R.string.image_index), (curPosition + 1), imageAttrs.size()));

        mAdapter = new ImagesAdapter(this, imageAttrs);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(curPosition);
        viewPager.getViewTreeObserver().addOnPreDrawListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                curPosition = position;
                tvTip.setText(String.format(getString(R.string.image_index), (curPosition + 1), imageAttrs.size()));
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishWithAnim();
    }

    private void initImageAttr(PhotoView photoView, ImageAttr attr, boolean isFinish) {
        int originalWidth = attr.width;
        int originalHeight = attr.height;
        int originalCenterX = attr.left + originalWidth / 2;
        int originalCenterY = attr.top + originalHeight / 2;

        float widthRatio = screenWidth * 1.0f / attr.realWidth;
        float heightRatio = screenHeight * 1.0f / attr.realHeight;
        int finalHeight = (int) (attr.realHeight * widthRatio);
        int finalWidth = screenWidth; //imageAttrs.size() == 1 ? screenWidth : finalHeight;

        scaleX = originalWidth * 1.0f / finalWidth;
        scaleY = originalHeight * 1.0f / finalHeight;
        translationX = originalCenterX - screenWidth / 2;
        translationY = originalCenterY - screenHeight / 2;

        Log.d("--->", "(left, top): (" + attr.left + ", " + attr.top + ")");
        Log.d("--->", "originalWidth: " + originalWidth + " originalHeight: " + originalHeight);
        Log.d("--->", "finalWidth: " + finalWidth + " finalHeight: " + finalHeight);
        Log.d("--->", "scaleX: " + scaleX + " scaleY: " + scaleY);
        Log.d("--->", "translationX: " + translationX + " translationY: " + translationY);
        Log.d("--->", "" + attr.toString());
        Log.d("--->", "----------------------------------------------------------------");
    }

    @Override
    public boolean onPreDraw() {
        if (isAnimating) return true;
        rootView.getViewTreeObserver().removeOnPreDrawListener(this);
        if (curPosition >= 9) {
            curPosition = 8;
        }
        PhotoView photoView = mAdapter.getPhotoView(curPosition);
        ImageAttr attr = imageAttrs.get(curPosition);
        initImageAttr(photoView, attr, false);

        translateXAnim(photoView, translationX, 0);
        translateYAnim(photoView, translationY, 0);
        scaleXAnim(photoView, scaleX, 1);
        scaleYAnim(photoView, scaleY, 1);
        setBackgroundColor(0f, 1f, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        return true;
    }

    public void finishWithAnim() {
        if (isAnimating) return;
        if (curPosition >= 9) {
            curPosition = 8;
        }
        PhotoView photoView = mAdapter.getPhotoView(curPosition);
        photoView.setScale(1f);
        ImageAttr attr = imageAttrs.get(curPosition);
        initImageAttr(photoView, attr, true);

        translateXAnim(photoView, 0, translationX);
        translateYAnim(photoView, 0, translationY);
        scaleXAnim(photoView, 1, scaleX);
        scaleYAnim(photoView, 1, scaleY);
        setBackgroundColor(1f, 0f, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
                finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    private void translateXAnim(final PhotoView photoView, float from, float to) {
        ValueAnimator anim = ValueAnimator.ofFloat(from, to);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setX((Float) valueAnimator.getAnimatedValue());
            }
        });
        anim.setDuration(ANIM_DURATION);
        anim.start();
    }

    private void translateYAnim(final PhotoView photoView, float from, float to) {
        ValueAnimator anim = ValueAnimator.ofFloat(from, to);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        anim.setDuration(ANIM_DURATION);
        anim.start();
    }

    private void scaleXAnim(final PhotoView photoView, float from, float to) {
        ValueAnimator anim = ValueAnimator.ofFloat(from, to);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleX((Float) valueAnimator.getAnimatedValue());
            }
        });
        anim.setDuration(ANIM_DURATION);
        anim.start();
    }

    private void scaleYAnim(final PhotoView photoView, float from, float to) {
        ValueAnimator anim = ValueAnimator.ofFloat(from, to);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleY((Float) valueAnimator.getAnimatedValue());
            }
        });
        anim.setDuration(ANIM_DURATION);
        anim.start();
    }

    private void setBackgroundColor(float from, float to, Animator.AnimatorListener listener) {
        ValueAnimator anim = ValueAnimator.ofFloat(from, to);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                rootView.setBackgroundColor(ColorUtil.evaluate((Float) valueAnimator.getAnimatedValue(), Color.TRANSPARENT, Color.BLACK));
            }
        });
        anim.setDuration(ANIM_DURATION);
        if (listener != null) {
            anim.addListener(listener);
        }
        anim.start();
    }
}
