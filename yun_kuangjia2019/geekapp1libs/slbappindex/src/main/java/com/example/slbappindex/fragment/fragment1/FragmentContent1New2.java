package com.example.slbappindex.fragment.fragment1;

import android.os.Bundle;
//import android.support.annotation.Nullable;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappcomm.bean.SNew2IndexLianxiBean;
import com.example.biz3slbappcomm.bean.SNew2IndexLianxiBean1;
import com.example.biz3slbappcomm.bean.STagCommBean;
import com.example.biz3slbappcomm.presenter.SNew2IndexLianxiPresenter;
import com.example.biz3slbappcomm.view.SNew2IndexLianxiView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseFragment;
import com.example.slbappcomm.widgets.recyclerviewnice.XRecyclerView;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment1.allhuibenpart.part3.CategoryDiffAdapterNew2Index;
import com.example.slbappindex.fragment.fragment1.allhuibenpart.part3.CategoryDiffBeanNew2Index;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
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

public class FragmentContent1New2 extends SlbBaseFragment implements View.OnClickListener, SNew2IndexLianxiView {

    private TextView tv_center;
    private NestedScrollView scroll_view1;

    private XRecyclerView recyclerView1;
    private CategoryDiffAdapterNew2Index mAdapter1;
    private List<CategoryDiffBeanNew2Index> mList1;
    private EmptyView mEmptyView;
    // 分类2
    private SNew2IndexLianxiPresenter presenter4;

    private String tablayoutId;

    private int mOffset = 0;
    private int mScrollY = 0;
    //    private View parallax;
//    private NestedScrollView scrollView;
    private RefreshLayout refreshLayout;
    private ClassicsHeader smart_header1;
    private Toolbar toolbar;

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
        super.setup(rootView, savedInstanceState);
        findview(rootView);
        initsmart(rootView);
        onclick();
        // 接口
        presenter4 = new SNew2IndexLianxiPresenter();
        presenter4.onCreate(this);
        donetwork();
//        //
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
//        filter.addAction("android.net.wifi.STATE_CHANGE");
//        getActivity().registerReceiver(networkChangeListener, filter);

    }

//    @Override
//    public void net_con_phone() {
//
//    }
//
//    @Override
//    public void net_con_wifi() {
//
//    }

    @Override
    public void net_con_none() {
//        mEmptyView.errorNet();
        Toasty.normal(Objects.requireNonNull(getActivity()), "网络异常，请检查网络连接！").show();
    }

    @Override
    public void net_con_success() {
//        retryData();
    }

    @Override
    public void showNetPopup() {
        super.showNetPopup();
//        netState.setUpdate(true);
//        retryData();
//        ToastUtil.showToastCenter("当前是移动网络");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment1_new2;
    }

    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(String cateId, boolean isrefresh) {

        if (!isrefresh) {
            // 从缓存中拿出头像刷新

            return;
        }
//        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();
        // 如果不在顶部先回到顶部 再点击才刷新
        if (scrolly == 0) {
            if (refreshLayout != null) {
                refreshLayout.autoRefresh();
            }
        } else {
            scroll_view1.smoothScrollTo(0, 0);
        }
    }

    private int scrolly = 0;

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
        presenter4.onDestory();
        super.onDestroyView();

    }

    private void initsmart(View rootView) {
        refreshLayout = rootView.findViewById(R.id.refreshLayout22);
        smart_header1 = rootView.findViewById(R.id.smart_header22);
        smart_header1.setEnableLastTime(false);
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                MyLogUtil.e("-geek123-onHeaderPulling-percent--", percent + "");
                MyLogUtil.e("-geek123-onHeaderPulling-offset--", offset + "");
                MyLogUtil.e("-geek123-onHeaderPulling-bottomHeight--", bottomHeight + "");
                MyLogUtil.e("-geek123-onHeaderPulling-extendHeight--", extendHeight + "");
                mOffset = offset / 2;
//                parallax.setTranslationY(mOffset - mScrollY);
//                toolbar.setAlpha(1 - Math.min(percent, 1));
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                MyLogUtil.e("-geek123-onHeaderReleasing-percent--", percent + "");
                MyLogUtil.e("-geek123-onHeaderReleasing-offset--", offset + "");
                MyLogUtil.e("-geek123-onHeaderReleasing-bottomHeight--", bottomHeight + "");
                MyLogUtil.e("-geek123-onHeaderReleasing-extendHeight--", extendHeight + "");
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
                MyLogUtil.e("-geek123-scroll_view1--oldScrollY-", oldScrollY + "");
                MyLogUtil.e("-geek123-scroll_view1--scrollY-", scrollY + "");
                scrolly = scrollY;
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
        presenter4.getNew2IndexLianxiData("pic");

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
        mAdapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CategoryDiffBeanNew2Index bean = mList1.get(position);
                int i = view.getId();
                if (i == R.id.iv_provider3 || i == R.id.tv_provider3 || i == R.id.tv_provider31 || i == R.id.iv1) {
                    HiosHelper.resolveAd(getActivity(), getActivity(), bean.getmBean().getLinkUrl());
                }
            }
        });
    }

    private void retryData() {
//        mEmptyView.loading();
        presenter4.getNew2IndexLianxiData("pic");
    }

    private void findview(View rootView) {
        tv_center = rootView.findViewById(R.id.tv_center);
        tv_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 测试 小程序
//                String appId = "wxb56733123e74c1d5"; // 填应用AppId
//                IWXAPI api = WXAPIFactory.createWXAPI(Objects.requireNonNull(getContext()).getApplicationContext(), appId);
//                WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
//                req.userName = "gh_76d5447fee11"; // 填小程序原始id
//                req.path = "";                  //拉起小程序页面的可带参路径，不填默认拉起小程序首页
//                req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;// 可选打开 开发版，体验版和正式版
//                api.sendReq(req);
                //头像bufen
//                MobclickAgent.onEvent(getActivity(), "readbookpage_avatar");
//                if (SlbLoginUtil2.get().isUserLogin()) {
//                    Intent msgIntent = new Intent();
//                    msgIntent.setAction(CommonUtils.index_action);
//                    msgIntent.putExtra(CommonUtils.index_action, 2);
//                    LocalBroadcastManagers.getInstance(getActivity().getApplicationContext()).sendBroadcast(msgIntent);
//                } else {
//                    SlbLoginUtil2.get().loginTowhere(getActivity(), new Runnable() {
//                        @Override
//                        public void run() {
////                        Toasty.normal(getActivity(), "登录成功，刷新页面").show();
//
//                        }
//                    });
//                }
            }
        });
        //
        scroll_view1 = rootView.findViewById(R.id.scroll_view22);
        mEmptyView = rootView.findViewById(R.id.emptyview1);
        recyclerView1 = rootView.findViewById(R.id.recycler_view1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setNestedScrollingEnabled(false);
        recyclerView1.setFocusable(false);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL, false));
//        recyclerView1.addItemDecoration(new GridSpacingItemDecoration(3, (int) (DisplayUtil.getScreenWidth(getActivity()) * 8f / 375), true));
        mAdapter1 = new CategoryDiffAdapterNew2Index(mList1);
        mAdapter1.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
//                int type = mList1.get(position).type;
                int type = Objects.requireNonNull(mAdapter1.getItem(position)).type;
                if (type == CategoryDiffBeanNew2Index.style1) {
                    return 3;
                } else if (type == CategoryDiffBeanNew2Index.style2) {
                    return 1;
                } else if (type == CategoryDiffBeanNew2Index.style3) {
                    return 3;
                } else if (type == CategoryDiffBeanNew2Index.style4) {
                    return 3;
                } else if (type == CategoryDiffBeanNew2Index.style5) {
                    return 1;
                } else {
                    return 3;
                }
            }
        });
        recyclerView1.setAdapter(mAdapter1);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        // 从缓存中拿出头像bufen
        super.onResume();
    }


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */

    /**
     * -----------------------分类推荐-----------------------
     */
    @Override
    public void OnNew2IndexLianxiSuccess(SNew2IndexLianxiBean sCategoryRecommendBean) {
        mEmptyView.success();
        refreshLayout.finishRefresh();
        mList1 = new ArrayList<>();
        for (int i = 0; i < sCategoryRecommendBean.getData().size(); i++) {
            // 一级bean  包含title和list<SlbMoreBooksBean2> 例如有2条数据
            SNew2IndexLianxiBean1 bean = sCategoryRecommendBean.getData().get(i);
            // 二级bean 只有list<SlbMoreBooksBean2>   例如有三条数据
            for (int j = 0; j < bean.getResourceViews().size(); j++) {
                if (j == 0) {
                    STagCommBean slbMoreBooksBean2 = new STagCommBean();
                    if (bean.getTagListView() != null) {
                        slbMoreBooksBean2.setTitle(bean.getTagListView().getName());
                    } else {
                        slbMoreBooksBean2.setTitle("无标题");
                    }
//                    slbMoreBooksBean2.setBookId("-1");
                    mList1.add(new CategoryDiffBeanNew2Index(CategoryDiffBeanNew2Index.style1, slbMoreBooksBean2));
                }
                if (bean.getResourceViews().get(j).getStretchMode().equals("3")) {
                    mList1.add(new CategoryDiffBeanNew2Index(CategoryDiffBeanNew2Index.style2, bean.getResourceViews().get(j)));
                } else if (bean.getResourceViews().get(j).getStretchMode().equals("1")) {
                    mList1.add(new CategoryDiffBeanNew2Index(CategoryDiffBeanNew2Index.style3, bean.getResourceViews().get(j)));
                } else if (bean.getResourceViews().get(j).getStretchMode().equals("1")) {
                    mList1.add(new CategoryDiffBeanNew2Index(CategoryDiffBeanNew2Index.style4, bean.getResourceViews().get(j)));
                } else if (bean.getResourceViews().get(j).getStretchMode().equals("1")) {
                    mList1.add(new CategoryDiffBeanNew2Index(CategoryDiffBeanNew2Index.style5, bean.getResourceViews().get(j)));
                }
            }
        }
        mAdapter1.setNewData(mList1);// mAdapter1 = new MoreLBImgAdapterNew1(mList1);
    }

    @Override
    public void OnNew2IndexLianxiNodata(String s) {
        mEmptyView.nodata();
        refreshLayout.finishRefresh(false);
//        Toasty.normal(this, s).show();
    }

    @Override
    public void OnNew2IndexLianxiFail(String s) {
        mEmptyView.errorNet();
        refreshLayout.finishRefresh(false);
//        Toasty.normal(this, s).show();
    }

}
