package com.example.slbappusercenter.adapter;

import android.content.Context;
import android.content.Intent;
//import android.support.annotation.NonNull;
//import androidx.core.view.PagerAdapter;
import androidx.annotation.NonNull;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappcomm.bean.SMyMedalCoolViewBean1;
import com.example.biz3slbappcomm.bean.SMyMedalCoolViewBean1Inside;
import com.example.biz3slbappcomm.bean.SMyMedalCoolViewBean2;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.utils.GridSpacingItemDecoration;
import com.example.slbappusercenter.MyMedalActivity;
import com.example.slbappusercenter.R;
import com.geek.libglide47.base.util.DisplayUtil;
import com.haier.cellarette.baselibrary.assetsfitandroid.fileassets.GetAssetsFileMP3TXTJSONAPKUtil;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.List;

import static cn.jiguang.share.android.api.AbsPlatform.getApplicationContext;

public class MedalCoolViewAdapter extends PagerAdapter {
    private List<SMyMedalCoolViewBean1> views;
    private Context context;

    public MedalCoolViewAdapter(Context context, List<SMyMedalCoolViewBean1> items) {
        this.views = items;
        this.context = context;
    }

    public void setData(List<SMyMedalCoolViewBean1> items) {
        this.views = items;
    }

    public SMyMedalCoolViewBean1 getContacts(int pos) {
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
        View view = View.inflate(context, R.layout.activity_my_medal_content, null);
        view.setLayoutParams(new CoolViewPager.LayoutParams());

        LinearLayout ll1 = view.findViewById(R.id.ll1);// 历史
        TextView tv1 = view.findViewById(R.id.tv1);// 最近获得tip
        TextView tv2 = view.findViewById(R.id.tv2);// 即将获得tip
        RecyclerView recyclerview1 = view.findViewById(R.id.recyclerview1);// 最近获得勋章列表

        LinearLayout ll3 = view.findViewById(R.id.ll3);// 绘本或听书
        TextView tv3 = view.findViewById(R.id.tv3);// 绘本或听书勋章tip
        TextView tv33 = view.findViewById(R.id.tv33);// 绘本或听书勋章数量tip

        RecyclerView recyclerview3 = view.findViewById(R.id.recyclerview2);// 绘本或听书勋章列表

        SMyMedalCoolViewBean1 sMyMedalCoolViewBean1 = views.get(position);
        if (TextUtils.equals(sMyMedalCoolViewBean1.getTag(), MyMedalActivity.LIST_TAG1)) {//历史页
            //最近获得勋章处理
            if (sMyMedalCoolViewBean1.getList() != null && sMyMedalCoolViewBean1.getList().size() > 0) {//有最近获得勋章
                ll1.setVisibility(View.VISIBLE);
                tv1.setText(sMyMedalCoolViewBean1.getTitle());
                set_list1(recyclerview1, sMyMedalCoolViewBean1);
            } else {
                ll1.setVisibility(View.GONE);
            }

            //即将获得勋章处理
            SMyMedalCoolViewBean1Inside sMyMedalCoolViewBean1Inside = sMyMedalCoolViewBean1.getsMyMedalCoolViewBean1Inside();
            if (sMyMedalCoolViewBean1Inside != null && sMyMedalCoolViewBean1Inside.getList().size() > 0) {
                ll3.setVisibility(View.VISIBLE);
                recyclerview3.setVisibility(View.VISIBLE);
                tv3.setText(sMyMedalCoolViewBean1Inside.getTitle());
                tv33.setText(sMyMedalCoolViewBean1Inside.getDescr());

                SMyMedalCoolViewBean1 sMyMedalCoolViewBean = new SMyMedalCoolViewBean1(sMyMedalCoolViewBean1Inside.getId(),
                        sMyMedalCoolViewBean1Inside.getTag(), sMyMedalCoolViewBean1Inside.getDescr(), sMyMedalCoolViewBean1Inside.getTitle(), sMyMedalCoolViewBean1Inside.getList());
                set_list2(recyclerview3, sMyMedalCoolViewBean);
            } else {
                ll3.setVisibility(View.GONE);
                recyclerview3.setVisibility(View.GONE);
            }

        } else if (TextUtils.equals(views.get(position).getTag(), MyMedalActivity.LIST_TAG2)) {
            ll3.setVisibility(View.VISIBLE);
            ll1.setVisibility(View.GONE);
            tv3.setText(views.get(position).getTitle());
            tv33.setText(views.get(position).getDescr());
            set_list2(recyclerview3, views.get(position));
        } else if (TextUtils.equals(views.get(position).getTag(), MyMedalActivity.LIST_TAG3)) {
            ll3.setVisibility(View.VISIBLE);
            ll1.setVisibility(View.GONE);
            tv3.setText(views.get(position).getTitle());
            tv33.setText(views.get(position).getDescr());
            set_list2(recyclerview3, views.get(position));
        }

        if (onWhichPagerListener != null) {
            onWhichPagerListener.OnResult(view, position);
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
        void OnResult(View view, int pos);
    }

    private MedalCoolViewItemAdapter myMedalAdapter_list1;
    private MedalCoolViewItemAdapter myMedalAdapter_list3;

    private void set_list1(RecyclerView recyclerview1, SMyMedalCoolViewBean1 bean) {
        recyclerview1.setLayoutManager(new GridLayoutManager(context, 3, RecyclerView.VERTICAL, false));
        recyclerview1.addItemDecoration(new GridSpacingItemDecoration(3, (int) (DisplayUtil.getScreenWidth(context) * 15f / 375), true));
        myMedalAdapter_list1 = new MedalCoolViewItemAdapter();
        recyclerview1.setAdapter(myMedalAdapter_list1);
        myMedalAdapter_list1.setNewData(bean.getList());
        myMedalAdapter_list1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (!NetworkUtils.isConnected()) {
                    Toasty.normal(getApplicationContext(), "网络异常，请检查网络连接！").show();
                    return;
                }
                SMyMedalCoolViewBean2 bean1 = (SMyMedalCoolViewBean2) adapter.getItem(position);
//                Toasty.normal(context, bean1.getMedalId()).show();
                if (context instanceof MyMedalActivity) {
                    ((MyMedalActivity) context).MusicDestory();
                }
                GetAssetsFileMP3TXTJSONAPKUtil.getInstance(context.getApplicationContext()).MusicDestory();
                Intent intent = new Intent("hs.act.slbapp.MedalPopActivity");
                intent.putExtra(CommonUtils.TAG_MEDALID1, bean1.getMedalId());
//                intent.putExtra(CommonUtils.TAG_MEDALBEAN1, bean1);
                context.startActivity(intent);

//                PopForShareImg popForShareImg = new PopForShareImg((Activity) context, null, null);
            }
        });
    }

    private void set_list2(RecyclerView recyclerview3, SMyMedalCoolViewBean1 bean) {
        recyclerview3.setLayoutManager(new GridLayoutManager(context, 3, RecyclerView.VERTICAL, false));
        recyclerview3.addItemDecoration(new GridSpacingItemDecoration(3, (int) (DisplayUtil.getScreenWidth(context) * 15f / 375), true));
        myMedalAdapter_list3 = new MedalCoolViewItemAdapter();
        recyclerview3.setAdapter(myMedalAdapter_list3);
        myMedalAdapter_list3.setNewData(bean.getList());
        myMedalAdapter_list3.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (!NetworkUtils.isConnected()) {
                    Toasty.normal(getApplicationContext(), "网络异常，请检查网络连接！").show();
                    return;
                }
                SMyMedalCoolViewBean2 bean1 = (SMyMedalCoolViewBean2) adapter.getItem(position);
//                Toasty.normal(context, bean1.getMedalId()).show();
                if (context instanceof MyMedalActivity) {
                    ((MyMedalActivity) context).MusicDestory();
                }
                GetAssetsFileMP3TXTJSONAPKUtil.getInstance(context.getApplicationContext()).MusicDestory();
                Intent intent = new Intent("hs.act.slbapp.MedalPopActivity");
                intent.putExtra(CommonUtils.TAG_MEDALID1, bean1.getMedalId());
//                intent.putExtra(CommonUtils.TAG_MEDALBEAN1, bean1);
                context.startActivity(intent);

            }
        });
    }
}