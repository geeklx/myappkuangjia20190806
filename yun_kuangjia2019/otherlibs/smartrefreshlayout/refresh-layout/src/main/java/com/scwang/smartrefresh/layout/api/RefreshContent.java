package com.scwang.smartrefresh.layout.api;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RestrictTo;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;
import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;
import static androidx.annotation.RestrictTo.Scope.SUBCLASSES;

//import android.support.annotation.RestrictTo;

//import static android.support.annotation.RestrictTo.Scope.LIBRARY;
//import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;
//import static android.support.annotation.RestrictTo.Scope.SUBCLASSES;

/**
 * 刷新内容组件
 * Created by SCWANG on 2017/5/26.
 */
@SuppressLint("SupportAnnotationUsage")
@RestrictTo({LIBRARY, LIBRARY_GROUP, SUBCLASSES})
public interface RefreshContent {
    void moveSpinner(int spinner);

    boolean canRefresh();

    boolean canLoadmore();

    int getMeasuredWidth();

    int getMeasuredHeight();

    void measure(int widthSpec, int heightSpec);

    void layout(int left, int top, int right, int bottom);

    View getView();

    View getScrollableView();

    ViewGroup.LayoutParams getLayoutParams();

    void onActionDown(MotionEvent e);

    void onActionUpOrCancel();

    void fling(int velocity);

    void setUpComponent(RefreshKernel kernel, View fixedHeader, View fixedFooter);

    void onInitialHeaderAndFooter(int headerHeight, int footerHeight);

    void setScrollBoundaryDecider(ScrollBoundaryDecider boundary);

    void setEnableLoadmoreWhenContentNotFull(boolean enable);

    AnimatorUpdateListener scrollContentWhenFinished(int spinner);
}
