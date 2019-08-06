package com.haier.cellarette.baselibrary.splash;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.haier.cellarette.baselibrary.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AlphaView extends RelativeLayout implements ViewPager.OnPageChangeListener {

    //    布局设置
//    private Integer[] Layouts = {R.layout.splash_activity_lay1, R.layout.splash_activity_lay2, R.layout.splash_activity_lay3, R.layout.splash_activity_lay4};
    //    private Integer[] strings = new Integer[]{R.drawable.guid1, R.drawable.guid2, R.drawable.guid3, R.drawable.guid4};
    private ViewPager vp;
    private List<View> viewList;//图片资源的集合
    private LinearLayout vg;//放置圆点
    //实例化原点View
    private ImageView iv_point;
    private ImageView[] ivPointArray;
    private RelativeLayout relativeLayout;
    private ArrayList<ImageView> myImageViews;
    private LayoutInflater from;
    private View view;
    private Integer[] imagesIds = new Integer[]{};
    private Integer[] Layouts = new Integer[]{};
    private Context mContext;
    private int mVisible = -1;


    public AlphaView(Context context) {
        super(context);
    }

    public AlphaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setAttributeSet(context, attrs);
    }

    public AlphaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setAttributeSet(context, attrs);
    }

    public AlphaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        setAttributeSet(context, attrs);
    }

    private void setAttributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AlphaView);
        int mVis = typedArray.getInt(R.styleable.AlphaView_pointvisbile, VISIBLE);
        int mGri = typedArray.getInt(R.styleable.AlphaView_pointgravity, Gravity.CENTER_HORIZONTAL);

        setPointGravity(mGri);
        setPointVisbile(mVis == VISIBLE ? VISIBLE : GONE);
        typedArray.recycle();
    }

    private void init(Context context) {
        mContext = context;
        from = LayoutInflater.from(getContext());
        view = from.inflate(R.layout.activity_splash, this, true);

        vg = (LinearLayout) view.findViewById(R.id.guide_ll_point);
        vp = (ViewPager) view.findViewById(R.id.guide_vp);
        relativeLayout = view.findViewById(R.id.rl);
    }

    private void initViewPager(Context context) {
        //实例化图片资源
        viewList = new ArrayList<>();

        if (Layouts == null || Layouts.length == 0) {
            return;
        }
        for (int i = 0; i < Layouts.length; i++) {
            View v = from.inflate(Layouts[i], null);
            v.setId(Layouts[i]);
            viewList.add(v);
        }
        //View集合初始化好后，设置Adapter
        vp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        vp.addOnPageChangeListener(this);
    }


    /**
     * 设置点击事件
     * view.setSplashItemOnClick(this, R.layout.splash_activity_lay4, R.id.login);
     *
     * @param onClickListener
     * @param item_lay
     * @param clicks
     */
    public void setSplashItemOnClick(OnClickListener onClickListener, int item_lay, int... clicks) {
        if (viewList.size() != 0 && clicks.length != 0) {
            for (View view : viewList) {
                if (view.getId() == item_lay) {
                    for (int i : clicks) {
                        view.findViewById(i).setOnClickListener(onClickListener);
                    }
                }
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    /**
     * 设置是否小点是否显示
     *
     * @param isVisible
     */
    public void setPointVisbile(int isVisible) {
        mVisible = isVisible;
        if (isVisible == VISIBLE) {
            vg.setVisibility(isVisible);
            setPoint(mContext, 0, 0, 0, 0, 0, 0, 0, 0);
        } else {
            vg.setVisibility(GONE);
        }
    }


    /**
     * Gravity.left
     * Gravity.right
     * Gravity......
     * 设置点在布局中的位置
     *
     * @param gravity
     */
    public void setPointGravity(int gravity) {
        //这里实例化LinearLayout
        vg.setGravity(gravity);
    }


    /**
     * 设置图片的id数组
     *
     * @param imagesId
     */
    public void setImages(Integer[] imagesId) {
        imagesIds = imagesId;
        setImageViews(mContext);
    }

    /**
     * 设置布局
     *
     * @param layouts
     */
    public void setLayouts(Integer[] layouts) {
        Layouts = layouts;
        initViewPager(mContext);
        setPointVisbile(mVisible);
    }


    /**
     * @param context
     * @param top     margintop
     * @param bottom  marginbottom
     * @param left    marginleft
     * @param right   marginright
     * @param press   按下按钮的图片id
     * @param normal  正常按钮的图片id
     * @param width   按钮宽度
     * @param height  按钮高度
     */
    public void setPoint(Context context, int press, int normal, int width, int height, int left, int top, int right, int bottom) {

        if (mVisible != VISIBLE) {
            return;
        }
        if (viewList == null || viewList.size() == 0) {
            return;
        }

        //根据ViewPager的item数量实例化数组
        ivPointArray = new ImageView[viewList.size()];
        //循环新建底部圆点ImageView，将生成的ImageView保存到数组中
        int size = viewList.size();
        for (int i = 0; i < size; i++) {
            iv_point = new ImageView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width == 0 ? 50 : width, height == 0 ? 50 : height);
            layoutParams.setMargins(left == 0 ? 30 : left, top == 0 ? 30 : top, right == 0 ? 30 : right, bottom == 0 ? 30 : bottom);
            iv_point.setLayoutParams(layoutParams);

            ivPointArray[i] = iv_point;
            //第一个页面需要设置为选中状态，这里采用两张不同的图片
            if (i == 0) {
                iv_point.setBackgroundResource(press == 0 ? R.drawable.splash_dot_press_but : press);
            } else {
                iv_point.setBackgroundResource(normal == 0 ? R.drawable.splash_dot_normal_but : normal);
            }

            //将数组中的ImageView加入到ViewGroup
            vg.addView(ivPointArray[i]);
        }
    }


    //TODO  添加ImageView

    /**
     * 设置图片
     *
     * @param context
     */
    private void setImageViews(Context context) {

        myImageViews = new ArrayList<>();
        if (imagesIds == null || imagesIds.length == 0) {
            return;
        }
        for (int i = 0; i < imagesIds.length; i++) {
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(layoutParams);
            imageView.setBackgroundResource(imagesIds[imagesIds.length - 1 - i]);
            relativeLayout.addView(imageView);
            myImageViews.add(imageView);
        }
    }

    private float mF = (float) 1.00;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        DecimalFormat fnum = new DecimalFormat("##0.00");
        Float left = Float.valueOf(fnum.format(positionOffset));//从0-1
        float right = Math.abs(left - mF);//从1-0

        Log.e("mz:", "---------" + left + "---------:" + right);
        myImageViews.get(imagesIds.length - 1 - position).setAlpha(right);
    }

    private int getPointVisible() {
        return mVisible;
    }

    /**
     * 滑动后的监听
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        if (getPointVisible() == VISIBLE) {
            //循环设置当前页的标记图
            int length = imagesIds.length;
            for (int i = 0; i < length; i++) {
                ivPointArray[position].setBackgroundResource(R.drawable.splash_dot_press_but);
                if (position != i) {
                    ivPointArray[i].setBackgroundResource(R.drawable.splash_dot_normal_but);
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    class GuidePageAdapter extends PagerAdapter {

        private List<View> viewList;

        public GuidePageAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        /**
         * @return 返回页面的个数
         */
        @Override
        public int getCount() {
            if (viewList != null) {
                return viewList.size();
            }
            return 0;
        }

        /**
         * 判断对象是否生成界面
         *
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 初始化position位置的界面
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }
    }
}
