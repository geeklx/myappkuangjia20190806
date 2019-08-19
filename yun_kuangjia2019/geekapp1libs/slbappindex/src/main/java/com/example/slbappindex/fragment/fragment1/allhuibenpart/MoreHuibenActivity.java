package com.example.slbappindex.fragment.fragment1.allhuibenpart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.LinearSnapHelper;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappshouye.bean.SBannerBean;
import com.example.biz3slbappshouye.bean.SBannerBean1;
import com.example.biz3slbappshouye.bean.SCategoryBean;
import com.example.biz3slbappshouye.bean.SCategoryBean1;
import com.example.biz3slbappshouye.bean.SCategoryRecommendBean;
import com.example.biz3slbappshouye.bean.SCategoryRecommendBean1;
import com.example.biz3slbappshouye.bean.SListCommBean1;
import com.example.biz3slbappshouye.presenter.SBannerPresenter;
import com.example.biz3slbappshouye.presenter.SCategoryPresenter;
import com.example.biz3slbappshouye.presenter.SCategoryRecommendPresenter;
import com.example.biz3slbappshouye.view.SBannerView;
import com.example.biz3slbappshouye.view.SCategoryRecommendView;
import com.example.biz3slbappshouye.view.SCategoryView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment1.allhuibenpart.part1.MoreHuibenCategoryAdapter;
import com.example.slbappindex.fragment.fragment1.allhuibenpart.part2.MoreHuibenImgAdapter;
import com.example.slbappindex.fragment.fragment1.allhuibenpart.part2.MoreHuibenImgBean;
import com.geek.libglide47.base.GlideImageView;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.gongwen.marqueen.util.OnItemClickListener;
import com.haier.cellarette.baselibrary.bannerviewquan.LXBannerView;
import com.haier.cellarette.baselibrary.bannerviewquan.holder.LXHolderCreator;
import com.haier.cellarette.baselibrary.bannerviewquan.holder.LXViewHolder;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;
import com.haier.cellarette.libwebview.hois2.HiosHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MoreHuibenActivity extends SlbBaseActivity implements View.OnClickListener, SBannerView, SCategoryView, SCategoryRecommendView {

    private LinearLayout scroll_view1;
    private LinearLayout llbg3;
    //    private TextView tvLeft;
    private TextView tv_left2;
    //    private TextView tvCenter;
    private SimpleMarqueeView<Spanned> marqueeView3;

    private List<SBannerBean1> mListbanner1;
    private LXBannerView banner1;

    private RecyclerView recyclerView0;
    private MoreHuibenCategoryAdapter mAdapter0;
    private List<SCategoryBean1> mList0;

//    private List<SBannerBean1> mListbanner2;
//    private LXBannerView banner2;

    private RecyclerView recyclerView1;
    private MoreHuibenImgAdapter mAdapter1;
    private List<MoreHuibenImgBean> mList1;
    private EmptyView mEmptyView;

    // banner
    private SBannerPresenter presenter1;
    private int bookColumn1 = 2;
    private int bookColumn2 = 3;
    // 分类1
    private SCategoryPresenter presenter2;
    // 分类2
    private SCategoryRecommendPresenter presenter4;


//    public List<MoreHuibenCategoryBean> getSampleData0(int lenth) {
//        List<MoreHuibenCategoryBean> list = new ArrayList<>();
//        for (int i = 0; i < lenth; i++) {
//            MoreHuibenCategoryBean status = new MoreHuibenCategoryBean();
//            status.setUserName("Chad" + i);
//            status.setCreatedAt("04/05/" + i);
//            status.setRetweet(i % 2 == 0);
//            status.setUserAvatar(i % 2 == 0 ? "http://wx1.sinaimg.cn/large/60d2c107ly1ftwko7cqwrg20b4069u0x.gif" : "https://wx3.sinaimg.cn/mw690/60d2c107ly1fwzh7u2km6j216o1kwngf.jpg");
//            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
//            list.add(status);
//        }
//        return list;
//    }

//    public List<MoreHuibenImgBean> getMultipleItemData(int lenth) {
//        List<MoreHuibenImgBean> list = new ArrayList<>();
//        for (int i = 0; i < lenth; i++) {
//            MoreHuibenChildImgBean status = new MoreHuibenChildImgBean();
//            status.setUserName(DateUtil.format_ymd(new Date()));
//            status.setCreatedAt(DateUtil.format_ymd(new Date()));
//            status.setRetweet(i % 2 == 0);
//            status.setUserAvatar("http://wx1.sinaimg.cn/large/60d2c107ly1ftwko7cqwrg20b4069u0x.gif");
//            status.setText("有趣的国学" + i);
//
//            list.add(new MoreHuibenImgBean(MoreHuibenImgBean.style1, status));
//            list.add(new MoreHuibenImgBean(MoreHuibenImgBean.style2, status));
//            list.add(new MoreHuibenImgBean(MoreHuibenImgBean.style2, status));
//
//
//        }
//
//        return list;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment1_part30_2;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        findview();
        onclick();
        donetwork();
//        Log.e("ssssssssssss", DeviceUtils.getAndroidID() + "");
//        Log.e("ssssssssssss", DeviceUtil.getImei(this) + "");
    }

    private void donetwork() {
//        tvCenter.setText("更多绘本");
        tv_left2.setVisibility(View.VISIBLE);

        mEmptyView.loading();
        //
        SimpleMF<Spanned> marqueeFactory3 = new SimpleMF<>(this);
        List<Spanned> datas3 = new ArrayList<>();
        datas3.add(Html.fromHtml("白雪公主和七个小矮人"));
        datas3.add(Html.fromHtml("这是你的鞋子吗"));
        datas3.add(Html.fromHtml("噼里啪啦肠胃菌来啦</font>"));
//        datas3.add(Html.fromHtml("123<font color=\"#333333\">小猪佩奇</font>3456"));
        datas3.add(Html.fromHtml("戴眼镜的龙爸爸"));
        marqueeFactory3.setData(datas3);
        marqueeView3.setMarqueeFactory(marqueeFactory3);
        marqueeView3.startFlipping();
        marqueeView3.setOnItemClickListener(new OnItemClickListener<TextView, Spanned>() {
            @Override
            public void onItemClickListener(TextView mView, Spanned mData, int mPosition) {
//                Toasty.normal(MoreHuibenActivity.this, String.format("mPosition:%s,mData:%s,mView:%s,.", mPosition, mData, mView)).show();
                Intent intent = new Intent("hs.act.slbapp.SearchListActivity");
                intent.putExtra("search_key", mData.toString());
                startActivity(intent);
            }
        });
        marqueeView3.startFlipping();
        //
        mListbanner1 = new ArrayList<>();
//        mListbanner1 = HuibenFragmentListUtils.getInstance(this).Data1();
//        mListbanner2 = new ArrayList<>();
//        mListbanner2 = HuibenFragmentListUtils.getInstance(this).Data1();
        // 接口
        presenter1 = new SBannerPresenter();
        presenter1.onCreate(this);
        presenter1.getBannerData( -1, 1);
//        setBanner();
        //
        mList0 = new ArrayList<>();
//        mList0 = getSampleData0(10);
        presenter2 = new SCategoryPresenter();
        presenter2.onCreate(this);
        presenter2.getCategoryData();
        //
        mList1 = new ArrayList<>();
//        mList1 = getMultipleItemData(6);
        // 接口
        presenter4 = new SCategoryRecommendPresenter();
        presenter4.onCreate(this);
        presenter4.getCategoryRecommendData();

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_left2) {
            onBackPressed();
        } else {

        }
    }

    private void onclick() {
//        tvLeft.setOnClickListener(this);
        tv_left2.setOnClickListener(this);
        mEmptyView.bind(scroll_view1).setRetryListener(new EmptyView.RetryListener() {
            @Override
            public void retry() {
                //
                retryData();
            }
        });
        mAdapter0.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //item click
                SCategoryBean1 bean = (SCategoryBean1) adapter.getItem(position);
//                Toasty.normal(MoreHuibenActivity.this, "item click" + bean.getCategoryId()).show();
                Intent intent = new Intent("hs.act.slbapp.CategoryBooksListActivity");
                intent.putExtra(CommonUtils.HUIBEN_IDS, CommonUtils.HUIBEN_TAG4);
//                intent.putExtra(CommonUtils.HUIBEN_CATEIDS, bean.getCategoryId());
//                intent.putExtra(CommonUtils.HUIBEN_TITLES, bean.getTitle());
                startActivity(intent);

            }
        });
        mAdapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MoreHuibenImgBean mBean = mList1.get(position);
                int i = view.getId();
                if (i == R.id.iv_provider3) {
                    HiosHelper.resolveAd(MoreHuibenActivity.this, MoreHuibenActivity.this, mBean.getmBean().getHios());
                }
//                String bookids = "";
//                if (!TextUtils.isEmpty(mBean.getmBean().getBookId())) {
//                    bookids = mBean.getmBean().getBookId();
//                }
//                if (!TextUtils.isEmpty(mBean.getmBean().getBookItemId())) {
//                    bookids = mBean.getmBean().getBookItemId();
//                }
//                HiosHelper.resolveAd(MoreHuibenActivity.this, MoreHuibenActivity.this, mBean.getmBean().getHios() + "?" +
//                        CommonUtils.HUIBEN_IDS + "={s}" + bookids + "&" + CommonUtils.HUIBEN_TITLES + "={s}" + mBean.getmBean().getBookName());
//                if (TextUtils.equals(addressBean.getmBean().getSourceType(), "book")) {
//                    Intent intent = new Intent("hs.act.slbapp.BooksListActivity");
//                    intent.putExtra(CommonUtils.HUIBEN_IDS, addressBean.getmBean().getBookId());
//                    intent.putExtra(CommonUtils.HUIBEN_TITLES, addressBean.getmBean().getBookName());
//                    startActivity(intent);
//                }
//                if (TextUtils.equals(addressBean.getmBean().getSourceType(), "bookItem")) {
//                    Intent intent = new Intent("hs.act.slbapp.ReadBookActivity");
//                    intent.putExtra(CommonUtils.HUIBEN_IDS, addressBean.getmBean().getBookItemId());
//                    intent.putExtra(CommonUtils.HUIBEN_TITLES, addressBean.getmBean().getBookName());
//                    startActivity(intent);
//                }
//                // style1
//                if (i == R.id.tv_provider2) {
//                    Toasty.normal(BaseApp.get(), position + "style1 click=" + addressBean.getmBean().getBookId()).show();
//                }
//                // style2
//                if (i == R.id.iv_provider3) {
//                    Toasty.normal(BaseApp.get(), position + "style2 click=" + addressBean.getmBean().getBannerImg()).show();
//                }
//                if (i == R.id.tv_provider3) {
//                    Toasty.normal(BaseApp.get(), position + "style2 click=" + addressBean.getmBean().getBookName()).show();
//                }
            }
        });
//        mAdapter1.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
//            @Override
//            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, final int position) {
//                int i = view.getId();
//
//                return true;
//            }
//        });
    }

    private void retryData() {
        presenter1.getBannerData(-1, 1);
        presenter2.getCategoryData();
        presenter4.getCategoryRecommendData();
    }

    private void findview() {
//        tvLeft = (TextView) findViewById(R.id.tv_left);
        tv_left2 = findViewById(R.id.tv_left2);
//        tvCenter = (TextView) findViewById(R.id.tv_center);
        marqueeView3 = findViewById(R.id.marqueeView3);
        scroll_view1 = findViewById(R.id.scroll_view1);
        mEmptyView = findViewById(R.id.emptyview1);
        llbg3 = findViewById(R.id.llbg3);
        //
        banner1 = findViewById(R.id.banner1);
        recyclerView0 = findViewById(R.id.recycler_view0);
        recyclerView1 = findViewById(R.id.recycler_view1);
        //
        banner1.setDelayedTime(6000);
        banner1.setmIsCanLoop(true);
        banner1.setIndicatorRes(R.drawable.indicator_normal, R.drawable.indicator_selected);
        banner1.setIndicatorVisible(true);
        banner1.setIndicatorAlign(LXBannerView.IndicatorAlign.CENTER);
        banner1.getmIndicatorContainer().setPadding(40, 10, 40, 10);
        banner1.getmIndicatorContainer().setBackgroundResource(R.drawable.indicator_bg_trans);
        //
        recyclerView0.setHasFixedSize(true);
        recyclerView0.setNestedScrollingEnabled(false);
        recyclerView0.setFocusable(false);
        recyclerView0.setLayoutManager(new GridLayoutManager(this, 5, RecyclerView.VERTICAL, false));
        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
        mLinearSnapHelper.attachToRecyclerView(recyclerView0);
        mAdapter0 = new MoreHuibenCategoryAdapter(R.layout.activity_morehuiben_rec0_item);
//        mAdapter0.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
//        mAdapter0.setNotDoAnimationCount(3);// mFirstPageItemCount
        recyclerView0.setAdapter(mAdapter0);
        //
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setNestedScrollingEnabled(false);
        recyclerView1.setFocusable(false);
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
        mAdapter1 = new MoreHuibenImgAdapter(mList1);
//        mAdapter1.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
//        mAdapter1.setNotDoAnimationCount(1);// mFirstPageItemCount
        mAdapter1.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
//                int type = mList1.get(position).type;
                int type = Objects.requireNonNull(mAdapter1.getItem(position)).type;
                if (type == MoreHuibenImgBean.style1) {
                    return 2;
                } else if (type == MoreHuibenImgBean.style2) {
                    return 1;
                } else if (type == MoreHuibenImgBean.style3) {
                    return 2;
                } else {
                    return 2;
                }
            }
        });
        recyclerView1.setAdapter(mAdapter1);


    }

    /**
     * ----------------------banner-----------------------
     */
    private void setBanner() {
        banner1.setPages(mListbanner1, new LXHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }

    public class BannerViewHolder implements LXViewHolder<SBannerBean1> {
        private GlideImageView mImageView;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_fragment1_banner_item, null);
            mImageView = view.findViewById(R.id.remote_item_image);
            return view;
        }

        @Override
        public void onBind(final Context context, int i, final SBannerBean1 mBean) {
            Log.e("geek", "current position:" + i);
            mImageView.loadImage(mBean.getBannerImg(), R.drawable.ic_def_loading);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HiosHelper.resolveAd(MoreHuibenActivity.this, MoreHuibenActivity.this, mBean.getHios());
//                    String bookids = "";
//                    if (!TextUtils.isEmpty(mBean.getBookId())) {
//                        bookids = mBean.getBookId();
//                    }
//                    if (!TextUtils.isEmpty(mBean.getBookItemId())) {
//                        bookids = mBean.getBookItemId();
//                    }
//                    HiosHelper.resolveAd(MoreHuibenActivity.this, MoreHuibenActivity.this, mBean.getHios() + "?" +
//                            CommonUtils.HUIBEN_IDS + "={s}" + bookids + "&" + CommonUtils.HUIBEN_TITLES + "={s}" + mBean.getBookName());
//                    if (TextUtils.equals(mBean.getSourceType(), "book")) {
//                        Intent intent = new Intent("hs.act.slbapp.BooksListActivity");
//                        intent.putExtra(CommonUtils.HUIBEN_IDS, mBean.getBookId());
//                        intent.putExtra(CommonUtils.HUIBEN_TITLES, mBean.getBookName());
//                        context.startActivity(intent);
//                    }
//                    if (TextUtils.equals(mBean.getSourceType(), "bookItem")) {
//                        Intent intent = new Intent("hs.act.slbapp.ReadBookActivity");
//                        intent.putExtra(CommonUtils.HUIBEN_IDS, mBean.getBookItemId());
//                        intent.putExtra(CommonUtils.HUIBEN_TITLES, mBean.getBookName());
//                        context.startActivity(intent);
//                    }
//                    Toasty.normal(BaseApp.get(), mBean.getBookName() + "").show();
                }
            });
        }
    }

    @Override
    public void onPause() {
        banner1.pause();
        marqueeView3.stopFlipping();
        super.onPause();
    }

    @Override
    public void onResume() {
        banner1.start();
        marqueeView3.startFlipping();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        banner1.pause();
        marqueeView3.stopFlipping();
        presenter1.onDestory();
        presenter2.onDestory();
        presenter4.onDestory();
        super.onDestroy();

    }


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */

    /**
     * ----------------------banner-----------------------
     */

    @Override
    public void OnSuccess(SBannerBean bean, int categoryId, int bannerType) {
        mListbanner1 = bean.getBannerList();
        if (mListbanner1 == null || mListbanner1.size() == 0) {
            return;
        }
        if (mListbanner1.size() == 1) {
            setBanner();
            banner1.setIndicatorVisible(false);
            banner1.setmIsCanLoop(false);
            return;
        }
        if (mListbanner1.size() > 0) {
            setBanner();
            banner1.setIndicatorVisible(true);
            banner1.setmIsCanLoop(true);
            banner1.start();
        }

    }

    @Override
    public void OnNodata(String msg) {
//        Toasty.normal(this, msg).show();
        // 暂无数据图片bufen

    }

    @Override
    public void OnFail(String msg) {
//        Toasty.normal(this, msg).show();
        // 暂无数据图片bufen

    }

    /**
     * ----------------------分类-----------------------
     */
    @Override
    public void OnCategorySuccess(SCategoryBean list, String s) {
//        mEmptyView.success();
        mList0 = list.getCategoryViewList();
        mAdapter0.setNewData(mList0);
        mAdapter0.notifyDataSetChanged();
    }

    @Override
    public void OnCategoryNodata(String s) {
//        mEmptyView.nodata();
//        Toasty.normal(this, s).show();
    }

    @Override
    public void OnCategoryFail(String s) {
//        mEmptyView.errorNet();
//        Toasty.normal(this, s).show();
    }

    /**
     * -----------------------分类推荐-----------------------
     */
    @Override
    public void OnCategoryRecommendSuccess(SCategoryRecommendBean sCategoryRecommendBean, String s) {
        mEmptyView.success();
        for (int i = 0; i < sCategoryRecommendBean.getRecommendList().size(); i++) {
            // 一级bean  包含title和list<SlbMoreBooksBean2> 例如有2条数据
            SCategoryRecommendBean1 bean = sCategoryRecommendBean.getRecommendList().get(i);
            // 二级bean 只有list<SlbMoreBooksBean2>   例如有三条数据
//            for (int j = 0; j < bean.getBookViewList().size(); j++) {
//                if (j == 0) {
//                    SListCommBean1 slbMoreBooksBean2 = new SListCommBean1();
//                    slbMoreBooksBean2.setBookName(bean.getClassfyName());
//                    slbMoreBooksBean2.setBookId("-1");
//                    mList1.add(new MoreHuibenImgBean(MoreHuibenImgBean.style1, slbMoreBooksBean2));
//                }
//
//                mList1.add(new MoreHuibenImgBean(MoreHuibenImgBean.style3, bean.getBookViewList().get(j)));
//            }

            for (int j = 0; j < bean.getBookViewList().size(); j++) {
                if (j == 0) {
                    SListCommBean1 slbMoreBooksBean2 = new SListCommBean1();
                    slbMoreBooksBean2.setBookName(bean.getClassfyName());
                    slbMoreBooksBean2.setBookId("-1");
                    mList1.add(new MoreHuibenImgBean(MoreHuibenImgBean.style1, slbMoreBooksBean2));
                }
//                if (bean.getBookViewList().get(j).getBookItemId().equals("23")){
                if (j % 2 == 0) {
                    mList1.add(new MoreHuibenImgBean(MoreHuibenImgBean.style2, bean.getBookViewList().get(j)));
                } else {
                    mList1.add(new MoreHuibenImgBean(MoreHuibenImgBean.style3, bean.getBookViewList().get(j)));
                }
            }
        }

        mAdapter1.setNewData(mList1);// mAdapter1 = new MoreLBImgAdapterNew1(mList1);
    }

    @Override
    public void OnCategoryRecommendNodata(String s) {
        mEmptyView.nodata();
//        Toasty.normal(this, s).show();
    }

    @Override
    public void OnCategoryRecommendFail(String s) {
        mEmptyView.errorNet();
//        Toasty.normal(this, s).show();
    }

}
