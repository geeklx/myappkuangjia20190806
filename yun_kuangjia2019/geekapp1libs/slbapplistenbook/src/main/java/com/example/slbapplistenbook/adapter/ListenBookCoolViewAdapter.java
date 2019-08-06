package com.example.slbapplistenbook.adapter;

import android.content.Context;
//import android.support.annotation.NonNull;
//import androidx.core.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.biz3slbappshouye.bean.SLB4CategoryListDetailBean1;
import com.example.slbapplistenbook.R;
import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.List;

public class ListenBookCoolViewAdapter extends PagerAdapter {
    private List<SLB4CategoryListDetailBean1> views;
    private Context context;

    public ListenBookCoolViewAdapter(Context context, List<SLB4CategoryListDetailBean1> items) {
        this.views = items;
        this.context = context;
    }

    public void setData(List<SLB4CategoryListDetailBean1> items) {
        this.views = items;
    }

    public SLB4CategoryListDetailBean1 getContacts(int pos) {
        return views.get(pos);
    }

    /**
     * 获取View的总数
     *
     * @return View总数
     */
    @Override
    public int getCount() {
        return views.size();
    }

    /**
     * 当ViewPager的内容有所变化时,进行调用。
     *
     * @param container ViewPager本身
     */
    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    private View mCurrentView;

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentView = (View) object;
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.activity_listenmusic_item1, null);
        view.setLayoutParams(new CoolViewPager.LayoutParams());
        ImageView iv1 = view.findViewById(R.id.iv1);
        TextView tv1 = view.findViewById(R.id.tv1);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_def_loading)
                .error(R.drawable.ic_def_loading)
                .fallback(R.drawable.ic_def_loading); //url为空的时候,显示的图片;
        Glide.with(context).load(views.get(position).getCoverImg()).apply(options).into(iv1);
        tv1.setText(views.get(position).getTitle());
        if (onWhichPagerListener != null) {
            onWhichPagerListener.OnResult(iv1, position);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    /**
     * 当ViewPager的内容变化结束时,进行调用。当该方法被调用时,必须确定所有的操作已经结束。
     *
     * @param container ViewPager本身
     */
    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }


    private OnWhichPagerListener onWhichPagerListener;

    public void setImageView(OnWhichPagerListener onWhichPagerListener) {
        this.onWhichPagerListener = onWhichPagerListener;
    }

    public interface OnWhichPagerListener {
        void OnResult(ImageView view, int pos);
    }
}