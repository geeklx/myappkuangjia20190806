package com.example.slbappindex.fragment.fragment1;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
//import androidx.core.view.ViewPager;
import androidx.core.widget.NestedScrollView;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappshouye.bean.SBannerBean;
import com.example.biz3slbappshouye.bean.SBannerBean1;
import com.example.biz3slbappshouye.bean.SListCommBean;
import com.example.biz3slbappshouye.bean.SListCommBean1;
import com.example.biz3slbappshouye.bean.SMyBooksBean;
import com.example.biz3slbappshouye.bean.SMyBooksBean1;
import com.example.biz3slbappshouye.presenter.SBannerPresenter;
import com.example.biz3slbappshouye.presenter.SHistoryPresenter;
import com.example.biz3slbappshouye.presenter.SHotPresenter;
import com.example.biz3slbappshouye.presenter.SMyBooksPresenter;
import com.example.biz3slbappshouye.view.SBannerView;
import com.example.biz3slbappshouye.view.SListCommView;
import com.example.biz3slbappshouye.view.SMyBooksView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseFragment;
import com.example.slbappcomm.utils.LoginImgUtils;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment1.historypart.Fragment3Adapter;
import com.example.slbappindex.fragment.fragment1.mybookspart.Fragment2Adapter;
import com.example.slbappindex.fragment.fragment1.remenhuibenpart.Fragment1Adapter;
import com.geek.libglide47.base.GlideImageView;
import com.haier.cellarette.baselibrary.bannerviewquan.LXBannerView;
import com.haier.cellarette.baselibrary.bannerviewquan.holder.LXHolderCreator;
import com.haier.cellarette.baselibrary.bannerviewquan.holder.LXViewHolder;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;
import com.haier.cellarette.baselibrary.networkview.NetModel;
import com.haier.cellarette.baselibrary.networkview.NetworkChangeListener;
import com.haier.cellarette.baselibrary.qcode.ExpandViewRect;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.BookPaddingDecoration;
import com.haier.cellarette.baselibrary.widget.CircleImageView;
import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;
//import com.scwang.smartrefresh.layout.api.RefreshHeader;
//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//import com.scwang.smartrefresh.layout.header.ClassicsHeader;
//import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
//import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
//import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class FragmentContent1 extends SlbBaseFragment implements SBannerView, SListCommView, SMyBooksView {

    private RelativeLayout rl_fangke;
    private ImageView iv_login23;
    private ImageView iv_toni1;
    private ImageView iv_tina1;
    private AnimationDrawable ad1 = null;
    private AnimationDrawable ad2 = null;

    private CircleImageView iv_name;
    private TextView tv_name;
    private TextView tv_name12;
    private TextView tv_age;
    private TextView tv_ring;
    private TextView tv_cwzx2;

    private String tablayoutId;
    private Context mContext;
    private TextView tv_center;
    private TextView tv_right;
    private TextView tv_books0;
    private TextView tv_books;
    private RelativeLayout rl_left1;
    private RelativeLayout rl_right1;
    private LinearLayout ll_lottie1;
    private LinearLayout ll_lottie2;
    private LottieAnimationView animation_view1;
    private LottieAnimationView animation_view2;
    private LXBannerView mMZBannerView;
    private List<SBannerBean1> mList0;

    private EmptyView mEmptyView;
    //banner
    private SBannerPresenter presenter0;
    // 热门列表 bufen
    private RecyclerView mRecyclerView1;
    private Fragment1Adapter mAdapter1;
    private List<SListCommBean1> mList1;
    private SHotPresenter presenter1;
    // 我的书架列表 bufen
    private RecyclerView mRecyclerView2;
    private Fragment2Adapter mAdapter2;
    private List<SMyBooksBean1> mList2;
    private SMyBooksPresenter presenter2;
    // 历史记录 bufen
    private RelativeLayout rl_title4;
    private TextView tv_books3;
    private TextView tv_history1;
    private RecyclerView mRecyclerView3;
    private Fragment3Adapter mAdapter3;
    private List<SListCommBean1> mList3;
    private SHistoryPresenter presenter3;

    private NetworkChangeListener mlistener = new NetworkChangeListener() {
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            State wifiState = null;
            State mobileState = null;

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

//                retryData();
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
    public void onDestroyView() {
        mMZBannerView.pause();
        presenter0.onDestory();
        presenter1.onDestory();
        presenter2.onDestory();
        presenter3.onDestory();
        if (ad1 != null) {
            ad1.stop();
        }
        if (ad2 != null) {
            ad2.stop();
        }
        getActivity().unregisterReceiver(mlistener);
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        mMZBannerView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
//        if (mList0.size() == 1) {
//            mMZBannerView.setIndicatorVisible(false);
//            mMZBannerView.start();
//        }
        mMZBannerView.start();
        // 刷新我的书架bufen
        presenter2.getMyBooksData(1, 3, "0");
        presenter3.getHistoryData(1, 5);
        // 从缓存中拿出头像bufen
        LoginImgUtils.getInstance(getActivity().getApplicationContext()).get_head_icon(iv_login23);
        tv_name12.setText(TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_NAME)) ?
                (TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_TEL)) ? "访客" : SPUtils.getInstance().getString(CommonUtils.USER_TEL)) : SPUtils.getInstance().getString(CommonUtils.USER_NAME));

        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            tablayoutId = getArguments().getString("tablayoutId");
        }
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        rl_fangke = rootView.findViewById(R.id.rl_fangke);
        mEmptyView = rootView.findViewById(R.id.emptyview1);
        iv_login23 = rootView.findViewById(R.id.iv_login23);
        iv_toni1 = rootView.findViewById(R.id.iv_toni1);
        iv_tina1 = rootView.findViewById(R.id.iv_tina1);
        iv_name = rootView.findViewById(R.id.iv_name);
        tv_name = rootView.findViewById(R.id.tv_name);
        tv_name12 = rootView.findViewById(R.id.tv_name12);
        tv_age = rootView.findViewById(R.id.tv_age);
        tv_ring = rootView.findViewById(R.id.tv_ring);
        tv_cwzx2 = rootView.findViewById(R.id.tv_cwzx2);

        rl_left1 = rootView.findViewById(R.id.rl_left1);
        rl_right1 = rootView.findViewById(R.id.rl_right1);
        ll_lottie1 = rootView.findViewById(R.id.ll_lottie1);
        ll_lottie2 = rootView.findViewById(R.id.ll_lottie2);
        animation_view1 = rootView.findViewById(R.id.animation_view1);
        animation_view2 = rootView.findViewById(R.id.animation_view2);
        mMZBannerView = rootView.findViewById(R.id.banner);
        tv_center = rootView.findViewById(R.id.tv_center);
        tv_right = rootView.findViewById(R.id.tv_right);
        rl_title4 = rootView.findViewById(R.id.rl_title4);
        tv_books3 = rootView.findViewById(R.id.tv_books3);
        tv_history1 = rootView.findViewById(R.id.tv_history1);

        tv_books0 = rootView.findViewById(R.id.tv_books0);
        ExpandViewRect.expandViewTouchDelegate(tv_books0, 10, 10, 10, 10);
        tv_books = rootView.findViewById(R.id.tv_books);
        ExpandViewRect.expandViewTouchDelegate(tv_books, 10, 10, 10, 10);
        mRecyclerView1 = rootView.findViewById(R.id.recycler_view0);
        mRecyclerView2 = rootView.findViewById(R.id.recycler_view1);
        mRecyclerView3 = rootView.findViewById(R.id.recycler_view3);

        rl_title4.setVisibility(View.GONE);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.normal(mContext, "LOGO").show();
            }
        });
        tv_center.setText("合象绘本");
        iv_toni1.setBackgroundResource(R.drawable.ani_toni);
        iv_tina1.setBackgroundResource(R.drawable.ani_tina);
        ad1 = (AnimationDrawable) iv_toni1.getBackground();
        ad2 = (AnimationDrawable) iv_tina1.getBackground();
        ad1.start();
        ad2.start();
        // 伸缩的视图
        initsmart(rootView);
        initData();
    }

    private int mOffset = 0;
    private int mScrollY = 0;
    //    private View parallax;
    private NestedScrollView scrollView;
    private RelativeLayout rl32;
    private RefreshLayout refreshLayout;
    private ClassicsHeader smart_header1;
//    private Toolbar toolbar;

    private void initsmart(View rootView) {
//        toolbar = rootView.findViewById(R.id.toolbar);
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
        scrollView = rootView.findViewById(R.id.scrollView);
        rl32 = rootView.findViewById(R.id.rl32);
        refreshLayout = rootView.findViewById(R.id.refreshLayout1);
        smart_header1 = rootView.findViewById(R.id.smart_header1);
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
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment1;
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {

//        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();
        findview();
        onclick();
        // banner bufen
        mList0 = new ArrayList<>();
        presenter0 = new SBannerPresenter();
        presenter0.onCreate(this);
        // 热门绘本bufen
        mList1 = new ArrayList<>();
//        mList0 = HuibenFragmentListUtils.getInstance(getActivity()).getSampleData0(10);
        presenter1 = new SHotPresenter();
        presenter1.onCreate(this);
        // 我的书架bufen
        mList2 = new ArrayList<>();
//        mList2 = HuibenFragmentListUtils.getInstance(getActivity()).getSampleData2(6);
        presenter2 = new SMyBooksPresenter();
        presenter2.onCreate(this);
        // 历史记录bufen
        mList3 = new ArrayList<>();
//        mList3 = HuibenFragmentListUtils.getInstance(getActivity()).getSampleData2(4);
        presenter3 = new SHistoryPresenter();
        presenter3.onCreate(this);
        donetwork();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        getActivity().registerReceiver(mlistener, filter);
    }

    private boolean is_first = true;

    private void donetwork() {
//        if (refreshLayout != null) {
//            if (is_first) {
//                is_first = false;
//                refreshLayout.autoRefresh();
//            }
//        }
        mEmptyView.loading();
        retryData();

    }

    private void onclick() {
        mEmptyView.notices(CommonUtils.TIPS_WUSHUJU, CommonUtils.TIPS_WUWANG, "小象正奔向故事里...", "");
        mEmptyView.bind(scrollView).setRetryListener(new EmptyView.RetryListener() {
            @Override
            public void retry() {
                //
                retryData();
            }
        });
        iv_login23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 登录bufen
                if (SlbLoginUtil2.get().isUserLogin()) {
                    Intent msgIntent = new Intent();
                    msgIntent.setAction(CommonUtils.index_action);
                    msgIntent.putExtra(CommonUtils.index_action, 2);
                    LocalBroadcastManagers.getInstance(getActivity().getApplicationContext()).sendBroadcast(msgIntent);
                } else {
                    SlbLoginUtil2.get().loginTowhere(getActivity(), new Runnable() {
                        @Override
                        public void run() {
//                        Toasty.normal(getActivity(), "登录成功，刷新页面").show();

                        }
                    });
                }
            }
        });
        tv_books3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 历史记录
//                HiosHelper.resolveAd(getActivity(), getActivity(), "hios://hs.act.slbapp.BooksListActivity{act}?" +
//                        CommonUtils.HUIBEN_IDS + "={s}" + CommonUtils.HUIBEN_TAG3 + "&" + CommonUtils.HUIBEN_TITLES + "={s}" + "历史记录");
                Intent intent = new Intent("hs.act.slbapp.BooksListActivity");
                intent.putExtra(CommonUtils.HUIBEN_IDS, CommonUtils.HUIBEN_TAG3);
                intent.putExtra(CommonUtils.HUIBEN_TITLES, "历史记录");
                startActivity(intent);
            }
        });
        rl_fangke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 访客
                Toasty.normal(getActivity(), "访客").show();
            }
        });
        tv_ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 消息
                Toasty.normal(getActivity(), "消息").show();
            }
        });
        tv_cwzx2.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                // 宠物中心bufen
                SlbLoginUtil2.get().loginTowhere(getActivity(), new Runnable() {
                    @Override
                    public void run() {
                        if (TextUtils.equals(SBannerPresenter.banben, "测试")) {
                            Intent intent = new Intent("hs.act.slbapp.HIOSAdActivityLinshi");
                            intent.putExtra("linshihuiben", "http://aic.sairobo.cn:8087/petcenter/");
                            startActivity(intent);
                        }
                        if (TextUtils.equals(SBannerPresenter.banben, "线上")) {
                            Intent intent = new Intent("hs.act.slbapp.HIOSAdActivityLinshi");
                            intent.putExtra("linshihuiben", "https://hexiang-app-api.sairobo.cn/petcenter/");
                            startActivity(intent);
                        }
                    }
                });
//                HiosHelper.resolveAd(getActivity(), getActivity(), "http://aic.sairobo.cn:8087/petcenter/");
            }
        });
        rl_left1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                GetAssetsFileMP3TXTJSONAPKUtil.getInstance(mContext).playMusic(mContext, false, "mp3/duck_blue_style.mp3");
//                Toasty.normal(getActivity(), "敬请期待...").show();
//                Intent intent = new Intent("hs.act.slbapp.BooksListActivity");
//                intent.putExtra(CommonUtils.HUIBEN_IDS, CommonUtils.HUIBEN_TAG5);
//                intent.putExtra(CommonUtils.HUIBEN_TITLES, "国学经典");
//                startActivity(intent);
                Intent intent = new Intent("hs.act.slbapp.ListenBooksListActivity");
                intent.putExtra(CommonUtils.HUIBEN_IDS, CommonUtils.HUIBEN_TAG5);
                intent.putExtra(CommonUtils.HUIBEN_TITLES, "国学经典");
                startActivity(intent);
//                startActivity(new Intent("hs.act.slbapp.ListenMusicActivity"));
//                if (TextUtils.equals(presenter0.banben, "测试")) {
//                    Intent intent = new Intent("hs.act.slbapp.HIOSAdActivityLinshi");
//                    intent.putExtra("linshihuiben", "http://huiben-h5-test.sairobo.cn#/hb/hb002");
//                    startActivity(intent);
////                    HiosHelper.resolveAd(getActivity(), getActivity(), "http://huiben-h5-test.sairobo.cn#/hb/hb002");
//                }
//                if (TextUtils.equals(presenter0.banben, "线上")) {
//                    Intent intent = new Intent("hs.act.slbapp.HIOSAdActivityLinshi");
//                    intent.putExtra("linshihuiben", "http://huiben-h5.sairobo.cn#/hb/hb002");
//                    startActivity(intent);
////                    HiosHelper.resolveAd(getActivity(), getActivity(), "http://huiben-h5.sairobo.cn#/hb/hb002");
//                }
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        // 临时跳转到绘本bufen "TXT_ID"
//                        Intent intent = new Intent("hs.act.slbapp.ReadBookActivity");
//                        intent.putExtra(CommonUtils.ReadBookActivity_TXTID, "20181116");
//                        startActivity(intent);
//                    }
//                }, 0);
            }
        });
        rl_right1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // GetAssetsFileMP3TXTJSONAPKUtil.getInstance(mContext).playMusic(mContext, false, "mp3/duck_blue_style.mp3");
                startActivity(new Intent("hs.act.slbapp.MoreHuibenActivity"));
            }
        });
        LottieComposition.Factory.fromAssetFileName(mContext, "lottie/duck_blue_style.json", new OnCompositionLoadedListener() {

            @Override
            public void onCompositionLoaded(LottieComposition composition) {
                animation_view1.setComposition(composition);
                animation_view1.setProgress(0.333f);
                animation_view1.playAnimation();
            }
        });
        LottieComposition.Factory.fromAssetFileName(mContext, "lottie/little_girl_jumping_-_loader.json", new OnCompositionLoadedListener() {

            @Override
            public void onCompositionLoaded(LottieComposition composition) {
                animation_view2.setComposition(composition);
                animation_view2.setProgress(0.333f);
                animation_view2.playAnimation();
            }
        });
        tv_books0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 热门列表bufen
//                HiosHelper.resolveAd(getActivity(), getActivity(), "hios://hs.act.slbapp.BooksListActivity{act}?" +
//                        CommonUtils.HUIBEN_IDS + "={s}" + CommonUtils.HUIBEN_TAG1 + "&" + CommonUtils.HUIBEN_TITLES + "={s}" + "热门绘本");
                Intent intent = new Intent("hs.act.slbapp.BooksListActivity");
                intent.putExtra(CommonUtils.HUIBEN_IDS, CommonUtils.HUIBEN_TAG1);
                intent.putExtra(CommonUtils.HUIBEN_TITLES, "热门绘本");
                startActivity(intent);
            }
        });
        tv_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 我的书架列表bufen
//                HiosHelper.resolveAd(getActivity(), getActivity(), "hios://hs.act.slbapp.MyBooksListActivity{act}?" +
//                        CommonUtils.HUIBEN_IDS + "={s}" + CommonUtils.HUIBEN_TAG2 + "&" + CommonUtils.HUIBEN_TITLES + "={s}" + "我的书架");
                Intent intent = new Intent("hs.act.slbapp.MyBooksListActivity");
                intent.putExtra(CommonUtils.HUIBEN_IDS, CommonUtils.HUIBEN_TAG2);
                intent.putExtra(CommonUtils.HUIBEN_TITLES, "我的书架");
                startActivity(intent);
            }
        });
        mMZBannerView.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        mAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                //item click
//                Toasty.normal(mContext, position + "item click").show();
//            }
//        });
        mAdapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SListCommBean1 mBean = mList1.get(position);
                int i = view.getId();
                HiosHelper.resolveAd(getActivity(), getActivity(), mBean.getHios());

            }
        });
//        mAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                //item click
//                Toasty.normal(mContext, position + "item click").show();
//            }
//        });
        mAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SMyBooksBean1 mBean = mList2.get(position);
                int i = view.getId();
                HiosHelper.resolveAd(getActivity(), getActivity(), mBean.getHios());

            }
        });
//        mAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
//            @Override
//            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
//                SlbIndexMyBooksBean2 addressBean = (SlbIndexMyBooksBean2) adapter.getItem(position);
//                int i = view.getId();
//                if (i == R.id.brademo1_img) {
//                    Toasty.normal(mContext, addressBean.getBookBigPicture() + "长按    " + position).show();
//                } else if (i == R.id.brademo1_tweetName) {
//                    Toasty.normal(mContext, addressBean.getBookName() + position).show();
//                } else if (i == R.id.brademo1_tweetText) {
//                    Toasty.normal(mContext, addressBean.getBookUnique() + position).show();
//                } else {
//                }
//                return true;
//            }
//        });
        mAdapter3.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SListCommBean1 mBean = mList3.get(position);
                int i = view.getId();
                HiosHelper.resolveAd(getActivity(), getActivity(), mBean.getHios());

            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                retryData();
            }
        });
    }

    private void findview() {
        mMZBannerView.setDelayedTime(6000);
        mMZBannerView.setmIsCanLoop(true);
        mMZBannerView.setIndicatorRes(R.drawable.indicator_normal, R.drawable.indicator_selected);
        mMZBannerView.setIndicatorVisible(false);
        mMZBannerView.setIndicatorAlign(LXBannerView.IndicatorAlign.CENTER);
        mMZBannerView.getmIndicatorContainer().setPadding(40, 10, 40, 10);
        mMZBannerView.getmIndicatorContainer().setBackgroundResource(R.drawable.indicator_bg_trans);
        //热门绘本bufen
        mRecyclerView1.setHasFixedSize(true);
        mRecyclerView1.setNestedScrollingEnabled(false);
        mRecyclerView1.setFocusable(false);
        mRecyclerView1.setLayoutManager(new GridLayoutManager(mContext, 1, OrientationHelper.HORIZONTAL, false));
//        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
//        mLinearSnapHelper.attachToRecyclerView(mRecyclerView0);
        mAdapter1 = new Fragment1Adapter(R.layout.activity_fragment1_rec0_item);//

//        mAdapter0.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
//        mAdapter0.setNotDoAnimationCount(3);// mFirstPageItemCount
        mRecyclerView1.setAdapter(mAdapter1);

        // 我的书架bufen
        mRecyclerView2.setHasFixedSize(true);
        mRecyclerView2.setNestedScrollingEnabled(false);// 解决数据加载不完的问题
        mRecyclerView2.setFocusable(false);// 解决数据加载完成后, 没有停留在顶部的问题
        //添加Android自带的分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        //添加自定义分割线
//        DividerItemDecoration divider = new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL);
//        divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(mContext, R.drawable.bg_book_shelf_charge9)));
//        mRecyclerView.addItemDecoration(divider);
        BookPaddingDecoration recycleViewDivider = new BookPaddingDecoration(mContext, OrientationHelper.VERTICAL, R.drawable.bg_book_shelf_charge9);
        mRecyclerView2.addItemDecoration(recycleViewDivider);// 书架bufen
//        mRecyclerView.bringToFront();// view在最前方bufen
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView2.setLayoutManager(new GridLayoutManager(mContext, 3, RecyclerView.VERTICAL, false));
        mAdapter2 = new Fragment2Adapter(R.layout.activity_fragment1_rec1_item);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.openLoadAnimation(new Fragment1CustomAnimation());
//        mAdapter.setNotDoAnimationCount(3);// mFirstPageItemCount
        mRecyclerView2.setAdapter(mAdapter2);
        // 历史记录
        mRecyclerView3.setHasFixedSize(true);
        mRecyclerView3.setNestedScrollingEnabled(false);
        mRecyclerView3.setFocusable(false);
        mRecyclerView3.setLayoutManager(new GridLayoutManager(mContext, 1, OrientationHelper.HORIZONTAL, false));
        mAdapter3 = new Fragment3Adapter(R.layout.activity_fragment1_rec1history_item);
        mRecyclerView3.setAdapter(mAdapter3);
    }

    /**
     * 当切换底部的时候通知每个fragment切换的id是哪个bufen
     *
     * @param cateId
     */
    public void give_id(String cateId) {
//        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();

    }

    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(String cateId, boolean isrefresh) {

        if (!isrefresh) {
            // 从缓存中拿出头像bufen
            LoginImgUtils.getInstance(getActivity().getApplicationContext()).get_head_icon(iv_login23);
            tv_name12.setText(TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_NAME)) ?
                    (TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_TEL)) ? "访客" : SPUtils.getInstance().getString(CommonUtils.USER_TEL)) : SPUtils.getInstance().getString(CommonUtils.USER_NAME));

            return;
        }
//        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();
        refreshLayout.autoRefresh();
    }

    private void retryData() {
        presenter0.getBannerData(-1, 0);
        presenter1.getHotData(1, 9);
        presenter2.getMyBooksData(1, 3, "0");
        presenter3.getHistoryData(1, 5);

    }

    /**
     * ----------------------banner-----------------------
     */
    private void setBanner(List<SBannerBean1> mList1) {
        mMZBannerView.setPages(mList1, new LXHolderCreator<BannerViewHolder>() {
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
    public void OnSuccess(SBannerBean bean, int categoryId, int bannerType) {
        mList0 = bean.getBannerList();
        if (mList0 == null || mList0.size() == 0) {
            return;
        }
        if (mList0.size() == 1) {
            setBanner(mList0);
            mMZBannerView.setIndicatorVisible(false);
            mMZBannerView.setmIsCanLoop(false);
            mMZBannerView.start();
            return;
        }
        if (mList0.size() > 0) {
            setBanner(mList0);
            mMZBannerView.setIndicatorVisible(true);
            mMZBannerView.setmIsCanLoop(true);
            mMZBannerView.start();
        }
    }

    @Override
    public void OnNodata(String msg) {
//        Toasty.normal(getActivity(), msg).show();
        // 暂无数据图片bufen

    }

    @Override
    public void OnFail(String msg) {
//        Toasty.normal(getActivity(), msg).show();
        // 暂无数据图片bufen

    }

    /**
     * ------------------热门---我的书架-----历史记录----------
     */

    @Override
    public void OnListCommSuccess(SListCommBean sListCommBean, String s) {
        refreshLayout.finishRefresh();
        mEmptyView.success();
        // 热门
        if (TextUtils.equals(s, presenter1.getClass().getSimpleName())) {
            mList1 = new ArrayList<>();
            mList1 = sListCommBean.getList();
            mAdapter1.setNewData(mList1);
//            mAdapter1.notifyDataSetChanged();
        }
        // 历史记录
        if (TextUtils.equals(s, presenter3.getClass().getSimpleName())) {
            mList3 = new ArrayList<>();
            mList3 = sListCommBean.getList();
            mAdapter3.setNewData(mList3);
//            mAdapter3.notifyDataSetChanged();
            if (mList3.size() > 0) {
                // 显示
                rl_title4.setVisibility(View.VISIBLE);
            } else {
                // 隐藏
                rl_title4.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void OnListCommNodata(String msg) {
        refreshLayout.finishRefresh(false);
        mEmptyView.nodata();
//        Toasty.normal(getActivity(), msg).show();
        // 暂无数据bufen
    }

    @Override
    public void OnListCommFail(String msg) {
        refreshLayout.finishRefresh(false);
        mEmptyView.errorNet();
//        Toasty.normal(getActivity(), msg).show();
        // 暂无数据bufen
    }

    @Override
    public void OnMyBooksSuccess(SMyBooksBean sMyBooksBean, String s) {
        // 我的书架
        mList2 = new ArrayList<>();
        mList2 = sMyBooksBean.getList();
        mAdapter2.setNewData(mList2);
//            mAdapter2.notifyDataSetChanged();
    }

    @Override
    public void OnMyBooksNodata(String s) {

    }

    @Override
    public void OnMyBooksFail(String s) {

    }

}
