//package com.example.slbappindex.fragment.fragment1.part3;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.Nullable;
//import com.google.android.material.appbar.AppBarLayout;
//import androidx.coordinatorlayout.widget.CoordinatorLayout;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearSnapHelper;
//import android.support.v7.widget.OrientationHelper;
//import androidx.recyclerview.widget.RecyclerView;
//import android.text.Html;
//import android.text.Spanned;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.blankj.utilcode.util.DeviceUtils;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.example.biz3slbappdemo1.bean.SlbBannerBean;
//import com.example.biz3slbappdemo1.bean.SlbBannerBean1;
//import com.example.biz3slbappdemo1.bean.SlbMoreBooksBean;
//import com.example.biz3slbappdemo1.bean.SlbMoreBooksBean1;
//import com.example.biz3slbappdemo1.bean.SlbMoreBooksBean2;
//import com.example.biz3slbappdemo1.bean.SlbMoreBooksCategoryBean;
//import com.example.biz3slbappdemo1.bean.SlbMoreBooksCategoryBean1;
//import com.example.biz3slbappdemo1.presenter.SlbBannerPresenter;
//import com.example.biz3slbappdemo1.presenter.SlbMoreBooksCategoryPresenter;
//import com.example.biz3slbappdemo1.presenter.SlbMoreBooksPresenter;
//import com.example.biz3slbappdemo1.view.SlbBannerView;
//import com.example.biz3slbappdemo1.view.SlbMoreBooksCategoryView;
//import com.example.biz3slbappdemo1.view.SlbMoreBooksView;
//import com.example.slbappindex.R;
//import com.example.slbappindex.fragment.fragment1.allhuibenpart.part2.MoreLBImgAdapterNew1;
//import com.example.slbappindex.fragment.fragment1.allhuibenpart.part2.MoreHuibenImgBean;
//import com.example.slbappindex.fragment.fragment1.allhuibenpart.part1.MoreHuibenCategoryAdapter;
//import com.geek.libglide47.base.GlideImageView;
//import com.gongwen.marqueen.SimpleMF;
//import com.gongwen.marqueen.SimpleMarqueeView;
//import com.gongwen.marqueen.util.OnItemClickListener;
//import com.haier.cellarette.baselibrary.bannerviewquan.LXBannerView;
//import com.haier.cellarette.baselibrary.bannerviewquan.holder.LXHolderCreator;
//import com.haier.cellarette.baselibrary.bannerviewquan.holder.LXViewHolder;
//import com.haier.cellarette.baselibrary.baseactivitys.SlbBaseActivity;
//import com.haier.cellarette.baselibrary.common.BaseApp;
//import com.haier.cellarette.baselibrary.emptyview.EmptyView;
//import com.haier.cellarette.baselibrary.toasts2.Toasty;
//import com.haier.cellarette.libutils.utilslib.device.DeviceUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class MoreHuibenActivityBeifen extends SlbBaseActivity implements View.OnClickListener, SlbBannerView, SlbMoreBooksCategoryView, SlbMoreBooksView {
//
//    private LinearLayout rl_an1;
//    private LinearLayout llbg2;
//    private LinearLayout llbg3;
//    private TextView tvLeft;
//    private TextView tv_left2;
//    private TextView tvCenter;
//    private CoordinatorLayout scroll_view1;
//    private SimpleMarqueeView<Spanned> marqueeView3;
//
//    private List<SlbBannerBean1> mListbanner1;
//    private LXBannerView banner1;
//
//    private RecyclerView recyclerView0;
//    private MoreHuibenCategoryAdapter mAdapter0;
//    private List<SlbMoreBooksCategoryBean1> mList0;
//
//    private List<SlbBannerBean1> mListbanner2;
//    private LXBannerView banner2;
//
//    private RecyclerView recyclerView1;
//    private MoreLBImgAdapterNew1 mAdapter1;
//    private List<MoreHuibenImgBean> mList1;
//    private EmptyView mEmptyView;
//
//    //
//    private AppBarLayout app_bar;
//    private CollapsingToolbarLayoutState state;
//    private RelativeLayout rl_search1;
//    // banner
//    private SlbBannerPresenter presenter1;
//    private int bookColumn1 = 2;
//    private int bookColumn2 = 3;
//    // 分类1
//    private SlbMoreBooksCategoryPresenter presenter2;
//    // 分类2
//    private SlbMoreBooksPresenter presenter4;
//
//
////    public List<MoreHuibenCategoryBean> getSampleData0(int lenth) {
////        List<MoreHuibenCategoryBean> list = new ArrayList<>();
////        for (int i = 0; i < lenth; i++) {
////            MoreHuibenCategoryBean status = new MoreHuibenCategoryBean();
////            status.setUserName("Chad" + i);
////            status.setCreatedAt("04/05/" + i);
////            status.setRetweet(i % 2 == 0);
////            status.setUserAvatar(i % 2 == 0 ? "http://wx1.sinaimg.cn/large/60d2c107ly1ftwko7cqwrg20b4069u0x.gif" : "https://wx3.sinaimg.cn/mw690/60d2c107ly1fwzh7u2km6j216o1kwngf.jpg");
////            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
////            list.add(status);
////        }
////        return list;
////    }
//
////    public List<MoreHuibenImgBean> getMultipleItemData(int lenth) {
////        List<MoreHuibenImgBean> list = new ArrayList<>();
////        for (int i = 0; i < lenth; i++) {
////            MoreHuibenChildImgBean status = new MoreHuibenChildImgBean();
////            status.setUserName(DateUtil.format_ymd(new Date()));
////            status.setCreatedAt(DateUtil.format_ymd(new Date()));
////            status.setRetweet(i % 2 == 0);
////            status.setUserAvatar("http://wx1.sinaimg.cn/large/60d2c107ly1ftwko7cqwrg20b4069u0x.gif");
////            status.setText("有趣的国学" + i);
////
////            list.add(new MoreHuibenImgBean(MoreHuibenImgBean.style1, status));
////            list.add(new MoreHuibenImgBean(MoreHuibenImgBean.style2, status));
////            list.add(new MoreHuibenImgBean(MoreHuibenImgBean.style2, status));
////
////
////        }
////
////        return list;
////    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_fragment1_part30;
//    }
//
//    @Override
//    protected void setup(@Nullable Bundle savedInstanceState) {
//        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        findview();
//        onclick();
//        donetwork();
//        Log.e("ssssssssssss", DeviceUtils.getAndroidID() + "");
//        Log.e("ssssssssssss", DeviceUtil.getImei(this) + "");
//    }
//
//    private void donetwork() {
//        tvCenter.setText("更多绘本");
//        tvLeft.setVisibility(View.VISIBLE);
//
//        mEmptyView.loading();
//        //
//        SimpleMF<Spanned> marqueeFactory3 = new SimpleMF<>(this);
//        List<Spanned> datas3 = new ArrayList<>();
//        datas3.add(Html.fromHtml("<font color=\"#ff0000\">白雪公主和七个小矮人</font>"));
//        datas3.add(Html.fromHtml("这是你的<font color=\"#00ff00\">鞋子</font>吗"));
//        datas3.add(Html.fromHtml("噼里啪啦<font color=\"#0000ff\">肠胃菌来啦</font>"));
//        datas3.add(Html.fromHtml("<font color=\"#333333\">小猪佩奇</font>"));
//        datas3.add(Html.fromHtml("<font color=\"#ff0000\"戴眼镜的>龙爸爸</font>"));
//        marqueeFactory3.setData(datas3);
//        marqueeView3.setMarqueeFactory(marqueeFactory3);
//        marqueeView3.startFlipping();
//        marqueeView3.setOnItemClickListener(new OnItemClickListener<TextView, Spanned>() {
//            @Override
//            public void onItemClickListener(TextView mView, Spanned mData, int mPosition) {
//                Toasty.normal(MoreHuibenActivityBeifen.this, String.format("mPosition:%s,mData:%s,mView:%s,.", mPosition, mData, mView)).show();
//            }
//        });
//        marqueeView3.startFlipping();
//        //
////        mListbanner1 = new ArrayList<>();
////        mListbanner1 = HuibenFragmentListUtils.getInstance(this).Data1();
////        mListbanner2 = new ArrayList<>();
////        mListbanner2 = HuibenFragmentListUtils.getInstance(this).Data1();
//        // 接口
//        presenter1 = new SlbBannerPresenter();
//        presenter1.onCreate(this);
//        presenter1.getSlbBannerData(bookColumn1);
//        presenter1.getSlbBannerData(bookColumn2);
////        setBanner();
//        //
//        mList0 = new ArrayList<>();
////        mList0 = getSampleData0(10);
//        presenter2 = new SlbMoreBooksCategoryPresenter();
//        presenter2.onCreate(this);
//        presenter2.getSlbMoreBooksCategoryData();
//        //
//        mList1 = new ArrayList<>();
////        mList1 = getMultipleItemData(6);
//        // 接口
//        presenter4 = new SlbMoreBooksPresenter();
//        presenter4.onCreate(this);
//        presenter4.getSlbMoreBooksData();
////        mAdapter1.setNewData(mList1);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mEmptyView.success();
//            }
//        }, 1000);
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        int i = v.getId();
//        if (i == R.id.tv_left) {
//            onBackPressed();
//        } else if (i == R.id.tv_left2) {
//            onBackPressed();
//        } else {
//
//        }
//    }
//
//    private void onclick() {
//        tvLeft.setOnClickListener(this);
//        tv_left2.setOnClickListener(this);
//        mEmptyView.bind(scroll_view1).setRetryListener(new EmptyView.RetryListener() {
//            @Override
//            public void retry() {
//                //
//
//            }
//        });
//        mAdapter0.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                //item click
//                SlbMoreBooksCategoryBean1 bean = (SlbMoreBooksCategoryBean1) adapter.getItem(position);
//                Toasty.normal(MoreHuibenActivityBeifen.this, "item click" + bean.getClassifyName()).show();
//                // 我的书架列表bufen
//                Intent intent = new Intent("hs.act.slbapp.SearchListActivity");
//                intent.putExtra("ids", bean.getClassifyCode());
//                startActivity(intent);
//
//            }
//        });
//        mAdapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                MoreHuibenImgBean addressBean = mList1.get(position);
//                int i = view.getId();
//                // style1
//                if (i == R.id.tv_provider2) {
//                    Toasty.normal(BaseApp.get(), position + "style1 click=" + addressBean.getmBean().getId()).show();
//                }
//                // style2
//                if (i == R.id.iv_provider3) {
//                    Toasty.normal(BaseApp.get(), position + "style2 click=" + addressBean.getmBean().getBookBigPicture()).show();
//                }
//                if (i == R.id.tv_provider3) {
//                    Toasty.normal(BaseApp.get(), position + "style2 click=" + addressBean.getmBean().getBookName()).show();
//                }
//            }
//        });
////        mAdapter1.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
////            @Override
////            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, final int position) {
////                int i = view.getId();
////
////                return true;
////            }
////        });
//    }
//
//    private void findview() {
//        tvLeft = (TextView) findViewById(R.id.tv_left);
//        tv_left2 = (TextView) findViewById(R.id.tv_left2);
//        tvCenter = (TextView) findViewById(R.id.tv_center);
//        marqueeView3 = findViewById(R.id.marqueeView3);
//        scroll_view1 = findViewById(R.id.scroll_view1);
//        mEmptyView = (EmptyView) findViewById(R.id.emptyview1);
//        app_bar = findViewById(R.id.app_bar1);
//        rl_search1 = findViewById(R.id.rl_search1);
//        rl_an1 = findViewById(R.id.rl_an1);
//        llbg2 = findViewById(R.id.llbg2);
//        llbg3 = findViewById(R.id.llbg3);
//        //
//
//        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                //计算进度百分比
//                float percent = Float.valueOf(Math.abs(verticalOffset)) / Float.valueOf(appBarLayout.getTotalScrollRange());
//                Log.e("-----percent------", percent + "");
//                Log.e("--verticalOffset---", verticalOffset + "");
//                Log.e("--Total---", appBarLayout.getTotalScrollRange() + "");
//
//                //根据百分比做你想做的
//                if (verticalOffset == 0) {
//                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
//                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
////                        collapsingToolbarLayout.setTitle("EXPANDED");//设置title为EXPANDED
//                    }
//                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
//                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
////                        collapsingToolbarLayout.setTitle("");//设置title不显示
////                        iv_icon2.setVisibility(View.VISIBLE);//隐藏播放按钮
//                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
//                        rl_search1.animate().scaleX((1 - (percent / 5))).scaleY((1 - (percent / 5))).start();
//                        llbg2.setVisibility(View.VISIBLE);
//                        llbg2.setBackgroundColor(ContextCompat.getColor(MoreHuibenActivityBeifen.this, R.color.color_568EC0));
//                        llbg3.setBackgroundColor(ContextCompat.getColor(MoreHuibenActivityBeifen.this, R.color.color_568EC0));
//                        rl_an1.setBackgroundColor(ContextCompat.getColor(MoreHuibenActivityBeifen.this, R.color.color_568EC0));
//                    }
//                } else {
//                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
//                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
////                            iv_icon2.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
//                            rl_search1.animate().scaleX(1.0f).scaleY(1.0f).start();
//                            llbg2.setVisibility(View.GONE);
//                            llbg2.setBackgroundColor(ContextCompat.getColor(MoreHuibenActivityBeifen.this, R.color.white));
//                            llbg3.setBackgroundColor(ContextCompat.getColor(MoreHuibenActivityBeifen.this, R.color.white));
//                            rl_an1.setBackgroundColor(ContextCompat.getColor(MoreHuibenActivityBeifen.this, R.color.white));
//                        }
////                        collapsingToolbarLayout.setTitle("INTERNEDIATE");//设置title为INTERNEDIATE
//                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
//                    }
//                }
//            }
//        });
//        banner1 = (LXBannerView) findViewById(R.id.banner1);
//        recyclerView0 = (RecyclerView) findViewById(R.id.recycler_view0);
//        banner2 = (LXBannerView) findViewById(R.id.banner2);
//        recyclerView1 = (RecyclerView) findViewById(R.id.recycler_view1);
//
//        //
//        banner1.setDelayedTime(3000);
//        banner1.setIndicatorRes(R.drawable.indicator_normal, R.drawable.indicator_selected);
//        banner1.setIndicatorVisible(false);
//        banner1.setIndicatorAlign(LXBannerView.IndicatorAlign.CENTER);
//        banner1.getmIndicatorContainer().setPadding(40, 10, 40, 10);
//        banner1.getmIndicatorContainer().setBackgroundResource(R.drawable.indicator_bg_trans);
//        //
//        banner2.setDelayedTime(3000);
//        banner2.setIndicatorRes(R.drawable.indicator_normal, R.drawable.indicator_selected);
//        banner2.setIndicatorVisible(true);
//        banner2.setIndicatorAlign(LXBannerView.IndicatorAlign.CENTER);
//        banner2.getmIndicatorContainer().setPadding(40, 10, 40, 10);
//        banner2.getmIndicatorContainer().setBackgroundResource(R.drawable.indicator_bg_trans);
//        //
//        recyclerView0.setHasFixedSize(true);
//        recyclerView0.setNestedScrollingEnabled(false);
//        recyclerView0.setFocusable(false);
//        recyclerView0.setLayoutManager(new GridLayoutManager(this, 1, OrientationHelper.HORIZONTAL, false));
//        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
//        mLinearSnapHelper.attachToRecyclerView(recyclerView0);
//        mAdapter0 = new MoreHuibenCategoryAdapter(R.layout.activity_morehuiben_rec0_item);
////        mAdapter0.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
////        mAdapter0.setNotDoAnimationCount(3);// mFirstPageItemCount
//        recyclerView0.setAdapter(mAdapter0);
//        //
//        recyclerView1.setHasFixedSize(true);
//        recyclerView1.setNestedScrollingEnabled(false);
//        recyclerView1.setFocusable(false);
//        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2, OrientationHelper.VERTICAL, false));
//        mAdapter1 = new MoreLBImgAdapterNew1(mList1);
////        mAdapter1.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
////        mAdapter1.setNotDoAnimationCount(1);// mFirstPageItemCount
//        mAdapter1.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
////                int type = mList1.get(position).type;
//                int type = Objects.requireNonNull(mAdapter1.getItem(position)).type;
//                if (type == MoreHuibenImgBean.style1) {
//                    return 2;
//                } else if (type == MoreHuibenImgBean.style2) {
//                    return 1;
//                } else {
//                    return 2;
//                }
//            }
//        });
//        recyclerView1.setAdapter(mAdapter1);
//
//
//    }
//
//    /**
//     * ----------------------banner-----------------------
//     */
//    private void setBanner() {
//        banner1.setPages(mListbanner1, new LXHolderCreator<BannerViewHolder>() {
//            @Override
//            public BannerViewHolder createViewHolder() {
//                return new BannerViewHolder();
//            }
//        });
//        banner2.setPages(mListbanner2, new LXHolderCreator<BannerViewHolder>() {
//            @Override
//            public BannerViewHolder createViewHolder() {
//                return new BannerViewHolder();
//            }
//        });
//    }
//
//    public class BannerViewHolder implements LXViewHolder<SlbBannerBean1> {
//        private GlideImageView mImageView;
//
//        @Override
//        public View createView(Context context) {
//            View view = LayoutInflater.from(context).inflate(R.layout.activity_fragment1_banner_item, null);
//            mImageView = view.findViewById(R.id.remote_item_image);
//            return view;
//        }
//
//        @Override
//        public void onBind(final Context context, int i, final SlbBannerBean1 mBean) {
//            Log.e("geek", "current position:" + i);
//            mImageView.loadImage(mBean.getBookBigPicture(), R.drawable.ic_def_loading);
//            mImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toasty.normal(BaseApp.get(), mBean.getBookName() + "").show();
//                }
//            });
//        }
//    }
//
//    @Override
//    public void onPause() {
//        banner1.pause();
//        banner2.pause();
//        marqueeView3.stopFlipping();
//        super.onPause();
//    }
//
//    @Override
//    public void onResume() {
//        banner1.start();
//        banner2.start();
//        marqueeView3.startFlipping();
//        super.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        banner1.pause();
//        banner2.pause();
//        marqueeView3.stopFlipping();
//        presenter1.onDestory();
//        presenter2.onDestory();
//        presenter4.onDestory();
//        super.onDestroy();
//
//    }
//
//
//    /**
//     * --------------------------------业务逻辑分割线----------------------------------
//     */
//
//    /**
//     * ----------------------banner-----------------------
//     */
//    @Override
//    public void OnSuccess(SlbBannerBean slbBannerBean, int bookColumn) {
//        if (bookColumn == bookColumn1) {
//            mListbanner1 = slbBannerBean.getBooksScroll();
//        }
//        if (bookColumn == bookColumn2) {
//            mListbanner2 = slbBannerBean.getBooksScroll();
//        }
//        setBanner();
//        banner1.start();
//        banner2.start();
//    }
//
//    @Override
//    public void OnNodata(String s) {
////        Toasty.normal(this, s).show();
//    }
//
//    @Override
//    public void OnFail(String s) {
////        Toasty.normal(this, s).show();
//    }
//
//    /**
//     * -----------------------分类1-----------------------
//     */
//    @Override
//    public void OnMoreBooksCategorySuccess(SlbMoreBooksCategoryBean slbMoreBooksCategoryBean) {
//        mList0 = slbMoreBooksCategoryBean.getBooksClassifyAll();
//        mAdapter0.setNewData(mList0);
//        mAdapter0.notifyDataSetChanged();
//    }
//
//    @Override
//    public void OnMoreBooksCategoryNodata(String s) {
//        Toasty.normal(this, s).show();
//    }
//
//    @Override
//    public void OnMoreBooksCategoryFail(String s) {
//        Toasty.normal(this, s).show();
//    }
//
//    /**
//     * -----------------------分类2-----------------------
//     */
//    @Override
//    public void OnMoreBooksSuccess(SlbMoreBooksBean slbMoreBooksBean) {
//        for (int i = 0; i < slbMoreBooksBean.getMoreBooks().size(); i++) {
//            // 一级bean  包含title和list<SlbMoreBooksBean2> 例如有2条数据
//            SlbMoreBooksBean1 bean = slbMoreBooksBean.getMoreBooks().get(i);
//            // 二级bean 只有list<SlbMoreBooksBean2>   例如有三条数据
//            for (int j = 0; j < bean.getList().size(); j++) {
//                if (j == 0) {
//                    SlbMoreBooksBean2 slbMoreBooksBean2 = new SlbMoreBooksBean2();
//                    slbMoreBooksBean2.setClassifyName(bean.getClassifyName());
//                    slbMoreBooksBean2.setId(-1);
//                    mList1.add(new MoreHuibenImgBean(MoreHuibenImgBean.style1, slbMoreBooksBean2));
//                }
//                mList1.add(new MoreHuibenImgBean(MoreHuibenImgBean.style2, bean.getList().get(j)));
//
//            }
//        }
//
//        mAdapter1.setNewData(mList1);// mAdapter1 = new MoreLBImgAdapterNew1(mList1);
//
//    }
//
//    @Override
//    public void OnMoreBooksNodata(String s) {
//        Toasty.normal(this, s).show();
//    }
//
//    @Override
//    public void OnMoreBooksFail(String s) {
//        Toasty.normal(this, s).show();
//    }
//
//
//}
