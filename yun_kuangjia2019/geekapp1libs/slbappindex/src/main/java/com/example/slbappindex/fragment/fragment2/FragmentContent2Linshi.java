package com.example.slbappindex.fragment.fragment2;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.LinearSnapHelper;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
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
import com.example.slbappcomm.base.SlbBaseFragment;
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
import com.haier.cellarette.baselibrary.networkview.NetModel;
import com.haier.cellarette.baselibrary.networkview.NetworkChangeListener;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentContent2Linshi extends SlbBaseFragment implements View.OnClickListener, SBannerView, SCategoryView, SCategoryRecommendView {

    private NestedScrollView scroll_view1;
    private TextView tv_left2;
    private LinearLayout llbg3;
    private SimpleMarqueeView<Spanned> marqueeView3;

    private List<SBannerBean1> mListbanner1;
    private LXBannerView banner1;

    private RecyclerView recyclerView0;
    private MoreHuibenCategoryAdapter mAdapter0;
    private List<SCategoryBean1> mList0;

    private RecyclerView recyclerView1;
    private MoreHuibenImgAdapter mAdapter1;
    private List<MoreHuibenImgBean> mList1;
    private EmptyView mEmptyView;

    //
    private LinearLayout rl_search1;
    // banner
    private SBannerPresenter presenter1;
    private int bookColumn1 = 2;
    private int bookColumn2 = 3;
    // 分类1
    private SCategoryPresenter presenter2;
    // 分类2
    private SCategoryRecommendPresenter presenter4;

    private String tablayoutId;

    private int mOffset = 0;
    private int mScrollY = 0;
    //    private View parallax;
//    private NestedScrollView scrollView;
    private RefreshLayout refreshLayout;
    private ClassicsHeader smart_header1;
    private Toolbar toolbar;


    private NetworkChangeListener mlistener = new NetworkChangeListener() {
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            NetworkInfo.State wifiState = null;
            NetworkInfo.State mobileState = null;

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context
                    .CONNECTIVITY_SERVICE);
            wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
                mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            }
            Log.d(TAG1,
                    "wifi状态:" + wifiState + "\n mobile状态:" + mobileState);

            if (wifiState != null && mobileState != null
                    && State.CONNECTED != wifiState
                    && State.CONNECTED == mobileState) {// 手机网络连接成功
                Log.d(TAG1, "手机2g/3g/4g网络连接成功");

                NetModel.getInstance().setMobile(true);
                NetModel.getInstance().setWifi(false);
                NetModel.getInstance().setConnected(true);

//                retryData();
//                niubiEmptyView.loading();
//                mEmptyView.success();

            } else if (wifiState != null && State.CONNECTED == wifiState) {// 无线网络连接成功
                Log.d(TAG1, "无线网络连接成功");

                NetModel.getInstance().setMobile(false);
                NetModel.getInstance().setWifi(true);
                NetModel.getInstance().setConnected(true);

//                retryData();// 过一会儿会进一次 所以不能刷这块 除非是长连接的需求
//                niubiEmptyView.loading();
//                mEmptyView.success();

            } else if (wifiState != null && mobileState != null
                    && State.CONNECTED != wifiState
                    && State.CONNECTED != mobileState) {// 手机没有任何的网络
                Log.d(TAG1, "手机没有任何的网络");

                NetModel.getInstance().setMobile(false);
                NetModel.getInstance().setWifi(false);
                NetModel.getInstance().setConnected(false);

//                niubiEmptyView.errornet(CommonUtils.TIPS_WUWANG);
                mEmptyView.errorNet();

            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            tablayoutId = getArguments().getString("tablayoutId");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(BaseApp.get(), R.color.color_BEDFFF));
//        StatusBarUtilV7.immersive(getActivity(), ContextCompat.getColor(getActivity(), R.color.color_E5F7FF), 1.0f);
//        StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(BaseApp.get(), R.color.color_BEDFFF),0);
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
//        setHasOptionsMenu(true); //重要的一行代码，防止选项菜单错乱
        super.setup(rootView, savedInstanceState);
//        tvCenter = rootView.findViewById(R.id.tv_center);
        findview(rootView);
        // 伸缩的视图
        initsmart(rootView);
        onclick();
        //
        mListbanner1 = new ArrayList<>();
//        mListbanner1 = HuibenFragmentListUtils.getInstance(this).Data1();
//        mListbanner2 = new ArrayList<>();
//        mListbanner2 = HuibenFragmentListUtils.getInstance(this).Data1();
        // 接口
        presenter1 = new SBannerPresenter();
        presenter1.onCreate(this);
        //
        mList0 = new ArrayList<>();
//        mList0 = getSampleData0(10);
        presenter2 = new SCategoryPresenter();
        presenter2.onCreate(this);
        //
//        mList1 = new ArrayList<>();
//        mList1 = getMultipleItemData(6);
        // 接口
        presenter4 = new SCategoryRecommendPresenter();
        presenter4.onCreate(this);
        donetwork();
        //
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        getActivity().registerReceiver(mlistener, filter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment1_part30_22;
    }

    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(String cateId, boolean isrefresh) {

        if (!isrefresh) {
            return;
        }
//        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();
        refreshLayout.autoRefresh();
    }

    /**
     * 当切换底部的时候通知每个fragment切换的id是哪个bufen
     *
     * @param cateId
     */
    public void give_id(String cateId) {
//        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();

    }

    @Override
    public void onDestroyView() {
        banner1.pause();
        marqueeView3.stopFlipping();
        presenter1.onDestory();
        presenter2.onDestory();
        presenter4.onDestory();
        getActivity().unregisterReceiver(mlistener);
        super.onDestroyView();

    }


    private void initsmart(View rootView) {
//        toolbar = rootView.findViewById(R.id.toolbar22);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                finish();
//            }
//        });

        //状态栏透明和间距处理
//        StatusBarUtilV7.immersive(getActivity());
//        StatusBarUtilV7.setPaddingSmart(getActivity(), toolbar);

//        parallax = rootView.findViewById(R.id.parallax);
//        scrollView = rootView.findViewById(R.id.scrollView);
        refreshLayout = rootView.findViewById(R.id.refreshLayout22);
        smart_header1 = rootView.findViewById(R.id.smart_header22);
        smart_header1.setEnableLastTime(false);
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = offset / 2;
//                parallax.setTranslationY(mOffset - mScrollY);
//                toolbar.setAlpha(1 - Math.min(percent, 1));
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = offset / 2;
//                parallax.setTranslationY(mOffset - mScrollY);
//                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });
        scroll_view1.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            private int lastScrollY = 0;
            private int h = DensityUtil.dp2px(170);
            private int color = ContextCompat.getColor(getActivity(), R.color.colorPrimary) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
//                    buttonBar.setAlpha(1f * mScrollY / h);
//                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
//                    parallax.setTranslationY(mOffset - mScrollY);
                }
                lastScrollY = scrollY;
            }
        });
//        buttonBar.setAlpha(0);
//        buttonBar.setVisibility(View.GONE);
//        toolbar.setBackgroundColor(0);

    }

    /**
     * 第一次进来加载bufen
     */
    private void donetwork() {
//        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();
        mEmptyView.loading();
        //
        SimpleMF<Spanned> marqueeFactory3 = new SimpleMF<>(getActivity());
        List<Spanned> datas3 = new ArrayList<>();
        datas3.add(Html.fromHtml("白雪公主和七个小矮人"));
        datas3.add(Html.fromHtml("这是你的鞋子吗"));
        datas3.add(Html.fromHtml("噼里啪啦肠胃菌来啦</font>"));
//        datas3.add(Html.fromHtml("123<font color=\"#333333\">小猪佩奇</font>3456"));
        datas3.add(Html.fromHtml("戴眼镜的龙爸爸"));
        marqueeFactory3.setData(datas3);
        marqueeView3.setMarqueeFactory(marqueeFactory3);
//        marqueeView3.startFlipping();
        marqueeView3.setOnItemClickListener(new OnItemClickListener<TextView, Spanned>() {
            @Override
            public void onItemClickListener(TextView mView, Spanned mData, int mPosition) {
//                Toasty.normal(getActivity(), String.format("mPosition:%s,mData:%s,mView:%s,.", mPosition, mData, mView)).show();
                Intent intent = new Intent("hs.act.slbapp.SearchListActivity");
                intent.putExtra("search_key", mData.toString());
                startActivity(intent);
            }
        });
        presenter1.getBannerData( -1, 1);
//        setBanner();
        presenter2.getCategoryData();
        presenter4.getCategoryRecommendData();

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_left) {
//            onBackPressed();
        } else if (i == R.id.tv_left2) {
//            onBackPressed();
        } else {

        }
    }

    private void onclick() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                retryData();
            }
        });
        mEmptyView.notices(CommonUtils.TIPS_WUSHUJU, CommonUtils.TIPS_WUWANG, "小象正奔向故事里...", "");
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
                MoreHuibenImgBean bean = mList1.get(position);
                int i = view.getId();
                if (i == R.id.iv_provider3) {
                    HiosHelper.resolveAd(getActivity(), getActivity(), bean.getmBean().getHios());
                }
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
//        mEmptyView.loading();
        presenter1.getBannerData( -1, 1);
        presenter2.getCategoryData();
        presenter4.getCategoryRecommendData();
    }

    private void findview(View rootView) {
        marqueeView3 = rootView.findViewById(R.id.marqueeView3);
        tv_left2 = rootView.findViewById(R.id.tv_left2);
        scroll_view1 = rootView.findViewById(R.id.scroll_view22);
        mEmptyView = rootView.findViewById(R.id.emptyview1);
        rl_search1 = rootView.findViewById(R.id.rl_search1);
        llbg3 = rootView.findViewById(R.id.llbg3);
        banner1 = rootView.findViewById(R.id.banner1);
        recyclerView0 = rootView.findViewById(R.id.recycler_view0);
        recyclerView1 = rootView.findViewById(R.id.recycler_view1);
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
        recyclerView0.setLayoutManager(new GridLayoutManager(getActivity(), 5, RecyclerView.VERTICAL, false));
        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
        mLinearSnapHelper.attachToRecyclerView(recyclerView0);
        mAdapter0 = new MoreHuibenCategoryAdapter(R.layout.activity_morehuiben_rec0_item);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter0.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
//        mAdapter0.setNotDoAnimationCount(3);// mFirstPageItemCount
        recyclerView0.setAdapter(mAdapter0);
        //
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setNestedScrollingEnabled(false);
        recyclerView1.setFocusable(false);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
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
                    HiosHelper.resolveAd(getActivity(), getActivity(), mBean.getHios());

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


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */

    /**
     * ----------------------banner-----------------------
     */
    @Override
    public void OnSuccess(SBannerBean bean, int categoryId, int bannerType) {
        refreshLayout.finishRefresh();
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
    public void OnNodata(String s) {
        refreshLayout.finishRefresh(false);
//        Toasty.normal(getActivity(), s).show();
    }

    @Override
    public void OnFail(String s) {
        refreshLayout.finishRefresh(false);
//        Toasty.normal(getActivity(), s).show();
    }

    /**
     * ----------------------分类-----------------------
     */
    @Override
    public void OnCategorySuccess(SCategoryBean list, String s) {
//        mEmptyView.success();
        mList0 = list.getCategoryViewList();
        mAdapter0.setNewData(mList0);
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
        mList1 = new ArrayList<>();
        for (int i = 0; i < sCategoryRecommendBean.getRecommendList().size(); i++) {
            // 一级bean  包含title和list<SlbMoreBooksBean2> 例如有2条数据
            SCategoryRecommendBean1 bean = sCategoryRecommendBean.getRecommendList().get(i);
            // 二级bean 只有list<SlbMoreBooksBean2>   例如有三条数据
            for (int j = 0; j < bean.getBookViewList().size(); j++) {
                if (j == 0) {
                    SListCommBean1 slbMoreBooksBean2 = new SListCommBean1();
                    slbMoreBooksBean2.setBookName(bean.getClassfyName());
                    slbMoreBooksBean2.setBookId("-1");
                    mList1.add(new MoreHuibenImgBean(MoreHuibenImgBean.style1, slbMoreBooksBean2));
                }
                mList1.add(new MoreHuibenImgBean(MoreHuibenImgBean.style2, bean.getBookViewList().get(j)));

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
