//package com.example.slbappreadbook.adapter;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v4.view.PagerAdapter;
//import android.text.TextUtils;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.signature.ObjectKey;
//import com.example.biz3slbappshouye.bean.SHuibenDetailBean2;
//import com.example.slbappreadbook.R;
//import com.example.slbappreadbook.callback.ImgCallBack;
//import com.geek.libglide47.base.GlideImageLoader;
//import com.geek.libglide47.base.progress.CircleProgressView;
//import com.geek.libglide47.base.progress.OnGlideImageViewListener;
//import com.github.chrisbanes.photoview.PhotoView;
//import com.huanhailiuxin.coolviewpager.CoolViewPager;
//
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//
//public class BasemusicAdapter2 extends PagerAdapter {
//    private List<SHuibenDetailBean2> views;
//    //    private View view;
////    private int pos;
//    private Context context;
//    private ImgCallBack imgCallBack;
//
//    public BasemusicAdapter2(Context context, List<SHuibenDetailBean2> items, ImgCallBack imgCallBack) {
//        this.context = context;
//        this.views = items;
//        this.imgCallBack = imgCallBack;
//    }
//
//    public void setData(List<SHuibenDetailBean2> items) {
//        this.views = items;
//    }
//
//    public SHuibenDetailBean2 getContacts(int pos) {
//        return views.get(pos);
//    }
//
//    public SHuibenDetailBean2 getNext(int pos) {
//        if (pos + 1 > views.size() - 1) {
//            return null;
//        }
//        return views.get(pos + 1);
//    }
//
//    public int getLastItem() {
//        return Integer.valueOf(views.get(getCount() - 1).getBookItemId());
//    }
//
//    public void setLastView(OnLastPagerListener onLastPagerListener) {
//        this.onLastPagerListener = onLastPagerListener;
//    }
//
//    @Override
//    public int getCount() {
//        return views.size();
//    }
//
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
//        if (views != null && position < views.size()) {
//            if (views.get(position) != null) {
//                View view = View.inflate(context, R.layout.activity_readbook_item, null);
//                view.setLayoutParams(new CoolViewPager.LayoutParams());
//                if (onLastPagerListener != null) {
//                    onLastPagerListener.OnResult(view, position);
//                }
//                final PhotoView glideImageView = view.findViewById(R.id.iv1);
//                CircleProgressView progressView1 = view.findViewById(R.id.progressView1);
//                CircleProgressView progressView2 = view.findViewById(R.id.progressView2);
//                CircleProgressView progressView3 = view.findViewById(R.id.progressView3);
//                final View maskView = view.findViewById(R.id.maskView);
//                final CircleProgressView progressView;
//                int randomNum = new Random().nextInt(3);
//                switch (randomNum) {
//                    case 1:
//                        progressView = progressView2;
//                        break;
//                    case 2:
//                        progressView = progressView3;
//                        break;
//                    case 0:
//                    default:
//                        progressView = progressView1;
//                        break;
//                }
//                progressView1.setVisibility(View.GONE);
//                progressView2.setVisibility(View.GONE);
//                progressView3.setVisibility(View.GONE);
//                progressView.setVisibility(View.GONE);
//                maskView.setVisibility(View.GONE);
//                String image_url = views.get(position).getPic();
//                String image_url_thumbnail = views.get(position).getPic();
//                GlideImageLoader imageLoader = GlideImageLoader.create(glideImageView);
//                RequestOptions requestOptions = imageLoader.requestOptions(R.drawable.ic_def_loading);
//                RequestOptions requestOptionsWithoutCache = imageLoader.requestOptions(R.drawable.ic_def_loading)
//                        .signature(new ObjectKey(UUID.randomUUID().toString()))  // 重点在这行
//                        .fitCenter()
//                        .skipMemoryCache(false)
//                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
////        RequestOptions requestOptionsWithoutCache = imageLoader.requestOptions(R.color.black)
////                .fitCenter()
////                .skipMemoryCache(true)
////                .diskCacheStrategy(DiskCacheStrategy.NONE);
//                imageLoader.setOnGlideImageViewListener(image_url, new OnGlideImageViewListener() {
//                    @Override
//                    public void onProgress(int percent, boolean isDone, GlideException exception) {
//                        if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
////                    Toasty.normal(context, exception.getMessage()).show();
//                            progressView.setVisibility(View.GONE);
//                            maskView.setVisibility(View.GONE);
//                        }
//                        progressView.setProgress(percent);
//                        // 根据需求暂时隐藏
////                progressView.setVisibility(isDone ? View.GONE : View.VISIBLE);
////                maskView.setVisibility(isDone ? View.GONE : View.VISIBLE);
//                    }
//                });
//
//                imageLoader.requestBuilder(image_url, requestOptionsWithoutCache)
//                        .thumbnail(Glide.with(context)
//                                .load(image_url_thumbnail)
//                                .apply(requestOptions))
////                        .transition(DrawableTransitionOptions.withCrossFade()) // 这句会影响图片的属性导致图片变形 猜测是动画引起
//                        .into(glideImageView);
//                glideImageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (imgCallBack != null) {
//                            imgCallBack.onImgClick(0);
//                        }
//                    }
//                });
//                //此处假设所有的照片都不同，用resId唯一标识一个itemView；也可用其它Object来标识，只要保证唯一即可
//                view.setTag(views.get(position).getId());
//                container.addView(view);
//                return view;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        //注意：此处position是ViewPager中所有要显示的页面的position，与Adapter mDrawableResIdList并不是一一对应的。
//        //因为mDrawableResIdList有可能被修改删除某一个item，在调用notifyDataSetChanged()的时候，ViewPager中的页面
//        //数量并没有改变，只有当ViewPager遍历完自己所有的页面，并将不存在的页面删除后，二者才能对应起来
//        if (object != null) {
//            ViewGroup viewPager = ((ViewGroup) container);
//            int count = viewPager.getChildCount();
//            for (int i = 0; i < count; i++) {
//                View childView = viewPager.getChildAt(i);
//                if (childView == object) {
//                    viewPager.removeView(childView);
//                    break;
//                }
//            }
//        }
//    }
//
//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        if (object != null && views != null) {
//            String pptId = (String) ((ImageView) object).getTag();
//            if (pptId != null) {
//                for (int i = 0; i < views.size(); i++) {
//                    if (pptId == (views.get(i).getId())) {
//                        return i;
//                    }
//                }
//            }
//        }
//        return PagerAdapter.POSITION_NONE;
//    }
//
//    private OnLastPagerListener onLastPagerListener;
//
//    public interface OnLastPagerListener {
//        void OnResult(View view, int pos);
//    }
//}