package com.example.slbappusercenter.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
//import android.support.annotation.AttrRes;
//import android.support.annotation.DrawableRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.annotation.RequiresApi;
//import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.AttrRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleRes;

import com.example.slbappusercenter.R;

import java.util.ArrayList;

public class CustomIndicator extends RelativeLayout {
    private static final String TAG = "CustomIndicator";
    private LinearLayout mIndicatorContainer;//indicator容器
    private ArrayList<ImageView> mIndicators = new ArrayList<>();
    //mIndicatorRes[0] 为未选中，mIndicatorRes[1]为选中
    private int[] mIndicatorRes = new int[]{R.drawable.indicator_medal_nomal, R.drawable.indicator_medal_selected};
    private int mIndicatorPaddingLeft = 0;// indicator 距离左边的距离
    private int mIndicatorPaddingRight = 0;//indicator 距离右边的距离
    private int mIndicatorPaddingTop = 0;//indicator 距离上边的距离
    private int mIndicatorPaddingBottom = 0;//indicator 距离下边的距离
    private int mIndicatorMarginLeft = 0;
    private int mIndicatorPadding = -1;
    private int mIndicatorAlign = 1;
    private int total;
    private int currentIndex;

    public enum IndicatorAlign {
        LEFT,//做对齐
        CENTER,//居中对齐
        RIGHT //右对齐
    }

    public CustomIndicator(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        readAttrs(context, attrs);
        init();
    }

    public CustomIndicator(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttrs(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomIndicator(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        readAttrs(context, attrs);
        init();
    }

    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomIndicator);
        if (typedArray != null) {
            mIndicatorAlign = typedArray.getInt(R.styleable.CustomIndicator_indicatorAligns, 1);
            mIndicatorPaddingLeft = typedArray.getDimensionPixelSize(R.styleable.CustomIndicator_indicatorPaddingLeft, 0);
            mIndicatorPaddingRight = typedArray.getDimensionPixelSize(R.styleable.CustomIndicator_indicatorPaddingRight, 0);
            mIndicatorPaddingTop = typedArray.getDimensionPixelSize(R.styleable.CustomIndicator_indicatorPaddingTop, 0);
            mIndicatorPaddingBottom = typedArray.getDimensionPixelSize(R.styleable.CustomIndicator_indicatorPaddingBottom, 0);
            mIndicatorPadding = typedArray.getDimensionPixelSize(R.styleable.CustomIndicator_indicatorPadding, -1);
            mIndicatorMarginLeft = typedArray.getDimensionPixelSize(R.styleable.CustomIndicator_indicatorMarginLeft, 0);
            typedArray.recycle();
        }
    }


    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.custom_indicator_normal_layout, this, true);
        mIndicatorContainer = findViewById(R.id.indicator_container);
        if (mIndicatorAlign == 0) {
            setIndicatorAlign(IndicatorAlign.LEFT);
        } else if (mIndicatorAlign == 1) {
            setIndicatorAlign(IndicatorAlign.CENTER);
        } else {
            setIndicatorAlign(IndicatorAlign.RIGHT);
        }

    }

    public void initIndicator(int total) {
        initIndicator(total, 0);
    }

    /**
     * 初始化指示器Indicator
     */
    public void initIndicator(int total, int currentIndex) {
        if (total < 0) {
            total = 0;
        }
        this.total = total;
        this.currentIndex = currentIndex;
        mIndicatorContainer.removeAllViews();
        mIndicators.clear();
        for (int i = 0; i < total; i++) {
            ImageView imageView = new ImageView(getContext());
            if (mIndicatorPadding < 0) {
                imageView.setPadding(mIndicatorPaddingLeft > 0 ? mIndicatorPaddingLeft : 0, mIndicatorPaddingTop > 0 ? mIndicatorPaddingTop : 0, mIndicatorPaddingRight > 0 ? mIndicatorPaddingRight : 0, mIndicatorPaddingBottom > 0 ? mIndicatorPaddingBottom : 0);
            } else {
                imageView.setPadding(mIndicatorPadding, mIndicatorPadding, mIndicatorPadding, mIndicatorPadding);
            }
            if (i == (currentIndex % total)) {
                imageView.setImageResource(mIndicatorRes[1]);
            } else {
                imageView.setImageResource(mIndicatorRes[0]);
            }
            if (mIndicatorMarginLeft > 0 && i != 0) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.leftMargin = mIndicatorMarginLeft;
                imageView.setLayoutParams(lp);
            }
            mIndicators.add(imageView);
            mIndicatorContainer.addView(imageView);
        }
    }

    /**
     * 是否显示Indicator
     *
     * @param visible true 显示Indicator，否则不显示
     */
    public void setIndicatorVisible(boolean visible) {
        mIndicatorContainer.setVisibility(visible ? VISIBLE : GONE);
    }


    /**
     * 设置indicator 图片资源
     *
     * @param unSelectRes 未选中状态资源图片
     * @param selectRes   选中状态资源图片
     */
    public void setIndicatorRes(@DrawableRes int unSelectRes, @DrawableRes int selectRes) {
        mIndicatorRes[0] = unSelectRes;
        mIndicatorRes[1] = selectRes;
//        initIndicator(6, 0);
        initIndicator(total, currentIndex);
    }

    // 切换选中状态
    public void changeIndicatorStatus(int currentIndex) {
        // 切换indicator
        if (total == 0) {
            return;
        }
        this.currentIndex = currentIndex;
        int realSelectPosition = currentIndex % total;
        for (int i = 0; i < total; i++) {
            if (i == realSelectPosition) {
                mIndicators.get(i).setImageResource(mIndicatorRes[1]);
            } else {
                mIndicators.get(i).setImageResource(mIndicatorRes[0]);
            }
        }
    }

    /**
     * 设置Indicator 的对齐方式
     *
     * @param indicatorAlign {@link IndicatorAlign#CENTER }{@link IndicatorAlign#LEFT }{@link IndicatorAlign#RIGHT }
     */
    public void setIndicatorAlign(IndicatorAlign indicatorAlign) {
        mIndicatorAlign = indicatorAlign.ordinal();
        LayoutParams layoutParams = (LayoutParams) mIndicatorContainer.getLayoutParams();
        if (indicatorAlign == IndicatorAlign.LEFT) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else if (indicatorAlign == IndicatorAlign.RIGHT) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        } else {
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        }

        // 2017.8.27 添加：增加设置Indicator 的上下边距。

        layoutParams.setMargins(0, mIndicatorPaddingTop, 0, mIndicatorPaddingBottom);
        mIndicatorContainer.setLayoutParams(layoutParams);

    }


    /**
     * 获取mIndicatorContainer的背景
     */
    public LinearLayout getmIndicatorContainer() {
        return mIndicatorContainer;
    }
}

