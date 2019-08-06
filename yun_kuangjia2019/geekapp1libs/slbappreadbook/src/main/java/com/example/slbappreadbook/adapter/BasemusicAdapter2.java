package com.example.slbappreadbook.adapter;

import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.example.biz3slbappshouye.bean.SHuibenDetailBean2;
import com.example.slbappreadbook.R;
import com.example.slbappreadbook.callback.ImgCallBack;
import com.geek.libglide47.base.GlideImageLoader;
import com.geek.libglide47.base.progress.CircleProgressView;
import com.geek.libglide47.base.progress.OnGlideImageViewListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class BasemusicAdapter2 extends PagerAdapter {
    private List<SHuibenDetailBean2> views;
    //    private View view;
//    private int pos;
    private Context context;
    private ImgCallBack imgCallBack;

    public BasemusicAdapter2(Context context, List<SHuibenDetailBean2> items, ImgCallBack imgCallBack) {
        this.context = context;
        this.views = items;
        this.imgCallBack = imgCallBack;
    }

    public void setData(List<SHuibenDetailBean2> items) {
        this.views = items;
    }

    public SHuibenDetailBean2 getContacts(int pos) {
        return views.get(pos);
    }

    public SHuibenDetailBean2 getNext(int pos) {
        if (pos + 1 > views.size() - 1) {
            return null;
        }
        return views.get(pos + 1);
    }

    public int getLastItem() {
        if (views == null || views.get(getCount() - 1) == null || views.get(getCount() - 1).getId() == null) {
            return -1;
        }
        return Integer.valueOf(views.get(getCount() - 1).getId());
    }

    public void setLastView(OnLastPagerListener onLastPagerListener) {
        this.onLastPagerListener = onLastPagerListener;
    }

    @Override
    public int getCount() {
        return views.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
//        RelativeLayout view = (RelativeLayout) View.inflate(context, R.layout.activity_readbook_item, null);
        View view = View.inflate(context, R.layout.activity_readbook_item, null);
        view.setLayoutParams(new CoolViewPager.LayoutParams());
//        View view = LayoutInflater.from(context).inflate(R.layout.activity_readbook_item, container, false);
//        this.view = view;
//        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
//        this.pos = position;
        if (onLastPagerListener != null) {
            onLastPagerListener.OnResult(view, position);
        }
//        final RelativeLayout rl2 = view.findViewById(R.id.rl2);
//        final RelativeLayout rllast1 = view.findViewById(R.id.rllast1);
//        final TextView tv_share1 = view.findViewById(R.id.tv_share1);
//        final TextView tv_re1 = view.findViewById(R.id.tv_re1);
//        final TextView tv_next1 = view.findViewById(R.id.tv_next1);
        final PhotoView glideImageView = view.findViewById(R.id.iv1);

        CircleProgressView progressView1 = view.findViewById(R.id.progressView1);
        CircleProgressView progressView2 = view.findViewById(R.id.progressView2);
        CircleProgressView progressView3 = view.findViewById(R.id.progressView3);
        final View maskView = view.findViewById(R.id.maskView);
        final CircleProgressView progressView;
        int randomNum = new Random().nextInt(3);
        switch (randomNum) {
            case 1:
                progressView = progressView2;
                break;
            case 2:
                progressView = progressView3;
                break;
            case 0:
            default:
                progressView = progressView1;
                break;
        }
        progressView1.setVisibility(View.GONE);
        progressView2.setVisibility(View.GONE);
        progressView3.setVisibility(View.GONE);
        progressView.setVisibility(View.GONE);
        maskView.setVisibility(View.GONE);
        String image_url = views.get(position).getPic();
        String image_url_thumbnail = views.get(position).getPic();
        GlideImageLoader imageLoader = GlideImageLoader.create(glideImageView);
        RequestOptions requestOptions = imageLoader.requestOptions(R.drawable.ic_def_loading);
        RequestOptions requestOptionsWithoutCache = imageLoader.requestOptions(R.drawable.ic_def_loading)
                .signature(new ObjectKey(UUID.randomUUID().toString()))  // 重点在这行
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
//        RequestOptions requestOptionsWithoutCache = imageLoader.requestOptions(R.color.black)
//                .fitCenter()
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE);
        imageLoader.setOnGlideImageViewListener(image_url, new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
//                    Toasty.normal(context, exception.getMessage()).show();
                    progressView.setVisibility(View.GONE);
                    maskView.setVisibility(View.GONE);
                }
                progressView.setProgress(percent);
                // 根据需求暂时隐藏
//                progressView.setVisibility(isDone ? View.GONE : View.VISIBLE);
//                maskView.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });

        imageLoader.requestBuilder(image_url, requestOptionsWithoutCache)
                .thumbnail(Glide.with(context)
                        .load(image_url_thumbnail)
                        .apply(requestOptions))
//                .transition(DrawableTransitionOptions.withCrossFade()) // 这句会影响图片的属性导致图片变形 猜测是动画引起
                .into(glideImageView);
        glideImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgCallBack != null) {
                    imgCallBack.onImgClick(0);
                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    private OnLastPagerListener onLastPagerListener;

    public interface OnLastPagerListener {
        void OnResult(View view, int pos);
    }
}