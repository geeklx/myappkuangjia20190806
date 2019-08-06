package com.example.slbappindex.fragment.fragment2;

import android.content.Intent;
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

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappcomm.bean.SNew2IndexZhukeBean;
import com.example.biz3slbappcomm.bean.SNew2IndexZhukeBean1;
import com.example.biz3slbappcomm.presenter.SNew2IndexZhukePresenter;
import com.example.biz3slbappcomm.view.SNew2IndexZhukeView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseFragment;
import com.example.slbappcomm.widgets.recyclerviewnice.XRecyclerView;
import com.example.slbappindex.R;
import com.example.slbappindex.order.dingdan.OrderAdapterNew2;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentContent2New2 extends SlbBaseFragment implements View.OnClickListener, SNew2IndexZhukeView {

    private TextView tv_center;
    private NestedScrollView scroll_view1;
    private XRecyclerView recyclerView1;
    private EmptyView mEmptyView;
    private OrderAdapterNew2 mAdapter;
    private List<SNew2IndexZhukeBean1> mList;
    // 分页
    private static final int PAGE_SIZE = 999;
    private int mNextRequestPage = 1;
    private int mPage;
    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    private SNew2IndexZhukePresenter presenter;

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
        mList = new ArrayList<>();
        presenter = new SNew2IndexZhukePresenter();
        presenter.onCreate(this);
        donetwork();

    }

    @Override
    public void net_con_success() {
        super.net_con_success();
//        donetwork();
    }

    @Override
    public void showNetPopup() {
        super.showNetPopup();
//        netState.setUpdate(true);
//        donetwork();
    }

    @Override
    public void net_con_none() {
//        mEmptyView.errorNet();
        Toasty.normal(Objects.requireNonNull(getActivity()), "网络异常，请检查网络连接！").show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment2_new2;
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

            return;
        }
//        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();
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
        presenter.onDestory();
        super.onDestroyView();

    }


    private void initsmart(View rootView) {
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
        setDatas();
    }

    /**
     * 加载第一页数据bufen
     */
    private void setDatas() {
//        niubiEmptyView.loading("");
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        set_re_data();

    }

    private void set_re_data() {
        presenter.getNew2IndexZhukeData(mNextRequestPage, PAGE_SIZE);
    }

    /**
     * 加载更多bufen
     */
    private void loadMore() {
        set_re_data();
    }

    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
//            Toasty.normal(SearchListActivity.this, "已经到底了").show();
        } else {
            mAdapter.loadMoreComplete();
        }
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
                setDatas();
            }
        });
        mEmptyView.notices(CommonUtils.TIPS_WUSHUJU, CommonUtils.TIPS_WUWANG, "小象正奔向故事里...", "");
        mEmptyView.bind(scroll_view1).setRetryListener(new EmptyView.RetryListener() {
            @Override
            public void retry() {
                //
                setDatas();
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SNew2IndexZhukeBean1 bean = (SNew2IndexZhukeBean1) adapter.getItem(position);
                int id = view.getId();
                if (TextUtils.equals(bean.getUrlType(), "h5")) {
                    HiosHelper.resolveAd(getActivity(), getActivity(), bean.getUrl());

                } else if (TextUtils.equals(bean.getUrlType(), "miniApp")) {
                    // 测试 小程序
                    String appId = "wxb56733123e74c1d5"; // 填应用AppId
                    IWXAPI api = WXAPIFactory.createWXAPI(Objects.requireNonNull(getContext()).getApplicationContext(), appId);
                    WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                    req.userName = "gh_76d5447fee11"; // 填小程序原始id
                    req.path = bean.getUrl();                  //拉起小程序页面的可带参路径，不填默认拉起小程序首页
                    req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;// 可选打开 开发版，体验版和正式版
                    api.sendReq(req);
                }

            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, recyclerView1);
//        mAdapter1.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
//            @Override
//            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, final int position) {
//                int i = view.getId();
//
//                return true;
//            }
//        });

    }

    private void findview(View rootView) {
        tv_center = rootView.findViewById(R.id.tv_center);
        tv_center.setText("主课");
        tv_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent("hs.act.slbapp.HIOSAdActivityLinshi2");
//                intent.putExtra("linshihuiben2", "https://www.baidu.com/");
//                startActivity(intent);
                startActivity(new Intent("hs.act.slbapp.VideoPlayActivity"));
            }
        });
        //
        scroll_view1 = rootView.findViewById(R.id.scroll_view22);
        mEmptyView = rootView.findViewById(R.id.emptyview1);
        recyclerView1 = rootView.findViewById(R.id.recycler_view1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setNestedScrollingEnabled(false);
        recyclerView1.setFocusable(false);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false));
        mAdapter = new OrderAdapterNew2(R.layout.activity_fragment4_order_item2);
        recyclerView1.setAdapter(mAdapter);

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

    @Override
    public void OnNew2IndexZhukeSuccess(SNew2IndexZhukeBean sOrderListBean) {
        mEmptyView.success();
        refreshLayout.finishRefresh();
        // 接口bufen
        mList = sOrderListBean.getData();
        if (mNextRequestPage == 1) {
            setData(true, mList);
            if (mList.size() == 0) {
//                mAdapter.setEmptyView(getView());
//                niubiEmptyView.nodata(CommonUtils.TIPS_WUSHUJU);
                mEmptyView.nodata();
                refreshLayout.finishRefresh(false);
            }
        } else {
            setData(false, mList);
        }
    }

    @Override
    public void OnNew2IndexZhukeNodata(String s) {
        if (mNextRequestPage == 1) {
            mAdapter.setEnableLoadMore(true);
//            niubiEmptyView.nodata(CommonUtils.TIPS_WUSHUJU);
            mEmptyView.nodata();
            refreshLayout.finishRefresh(false);
        } else {
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void OnNew2IndexZhukeFail(String s) {
        if (mNextRequestPage == 1) {
            mAdapter.setEnableLoadMore(true);
            // 根据需求修改bufen
            mAdapter.setNewData(null);
//            niubiEmptyView.errornet(CommonUtils.TIPS_WUSHUJU);
//            niubiEmptyView.errornet(CommonUtils.TIPS_WUWANG);
            mEmptyView.errorNet();
            refreshLayout.finishRefresh(false);
        } else {
            mAdapter.loadMoreFail();
        }
    }

}
