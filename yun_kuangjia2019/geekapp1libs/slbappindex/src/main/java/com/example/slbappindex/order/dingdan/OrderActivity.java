package com.example.slbappindex.order.dingdan;

import android.os.Bundle;
//import android.support.annotation.Nullable;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbapporder.bean.SOrderListBean;
import com.example.biz3slbapporder.bean.SOrderListBean1;
import com.example.biz3slbapporder.presenter.SOrderListPresenter;
import com.example.biz3slbapporder.view.SOrderListView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.widgets.recyclerviewnice.XRecyclerView;
import com.example.slbappindex.R;
import com.haier.cellarette.baselibrary.emptyview.NiubiEmptyView;
import com.haier.cellarette.baselibrary.qcode.ExpandViewRect;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

// 我的订单列表页面
public class OrderActivity extends SlbBaseActivity implements View.OnClickListener, SOrderListView {

    private TextView tvRight;
    private TextView tvCenter;
    private XRecyclerView recyclerview1;
    private OrderAdapter mAdapter;
    private List<SOrderListBean1> mList;
    private NiubiEmptyView niubiEmptyView;
    // 分页
    private static final int PAGE_SIZE = 12;
    private int mNextRequestPage = 1;
    private int mPage;
    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    private SOrderListPresenter presenter;

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment4_order;
    }

    @Override
    protected void onResume() {
        MobclickAgent.onEvent(this, "OrderActivity");
        super.onResume();
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        mList = new ArrayList<>();
        presenter = new SOrderListPresenter();
        presenter.onCreate(this);
        setDatas();
    }

    /**
     * 加载第一页数据bufen
     */
    private void setDatas() {
        niubiEmptyView.loading("");
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        set_re_data();

    }

    private void set_re_data() {
        presenter.getOrderListData(mNextRequestPage, PAGE_SIZE);
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

    private void onclick() {
        tvRight.setOnClickListener(this);
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                //item click
//                OrderBean bean = (OrderBean) adapter.getItem(position);
//                Toasty.normal(OrderActivity.this, bean.getContent2() + "item click").show();
//            }
//        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SOrderListBean1 bean = (SOrderListBean1) adapter.getItem(position);
                int id = view.getId();
                if (id == R.id.tv5) {
                    HiosHelper.resolveAd(OrderActivity.this, OrderActivity.this, bean.getHios());
//                    // 阅读绘本
//                    if (TextUtils.equals(bean.getSourceType(), "vip")) {
//                        Intent intent = new Intent("hs.act.slbapp.VipActivity");
//                        startActivity(intent);
//                    }
//                    if (TextUtils.equals(bean.getSourceType(), "book")) {
//                        Intent intent = new Intent("hs.act.slbapp.BooksListActivity");
//                        intent.putExtra(CommonUtils.HUIBEN_IDS, bean.getSourceId());
//                        intent.putExtra(CommonUtils.HUIBEN_TITLES, bean.getOrderName());
//                        startActivity(intent);
//                    }
//                    if (TextUtils.equals(bean.getSourceType(), "bookitem")) {
//                        Intent intent = new Intent("hs.act.slbapp.ReadBookActivity");
//                        intent.putExtra(CommonUtils.HUIBEN_IDS_ZONG, bean.getSourcePid());
//                        intent.putExtra(CommonUtils.HUIBEN_IDS, bean.getSourceId());
//                        intent.putExtra(CommonUtils.HUIBEN_TITLES, bean.getOrderName());
//                        startActivity(intent);
//                    }
//                    if (TextUtils.equals(bean.getSourceType(), "audio")) {
//                        Intent intent = new Intent("hs.act.slbapp.ListenBooksListActivity2");
//                        // String categoryId, String columnId, String type
//                        intent.putExtra(CommonUtils.HUIBEN_IDS_ZONG, bean.getSourcePid());
//                        intent.putExtra(CommonUtils.HUIBEN_IDS, bean.getSourceId());
//                        intent.putExtra(CommonUtils.HUIBEN_TITLES, bean.getOrderName());
//                        startActivity(intent);
//                    }
//                    if (TextUtils.equals(bean.getSourceType(), "audioitem")) {
//                        Intent intent = new Intent("hs.act.slbapp.ListenMusicActivity");
//                        intent.putExtra(CommonUtils.HUIBEN_IDS_ZONG, bean.getSourcePid());
//                        intent.putExtra(CommonUtils.HUIBEN_IDS, bean.getSourceId());
//                        intent.putExtra(CommonUtils.HUIBEN_TITLES, bean.getOrderName());
//                        intent.putStringArrayListExtra(CommonUtils.HUIBEN_XMLY, null);
//                        startActivity(intent);
//                    }
                }
            }
        });
        niubiEmptyView.setRetry(new NiubiEmptyView.RetryListener() {
            @Override
            public void retry() {
                setDatas();
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, recyclerview1);

    }

    private void findview() {
        tvRight = findViewById(R.id.tv_right);
        tvCenter = findViewById(R.id.tv_center);
        recyclerview1 = findViewById(R.id.recyclerview1);

        tvCenter.setText("我的订单");
        ExpandViewRect.expandViewTouchDelegate(tvRight, 20, 20, 20, 20);

        recyclerview1.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));
        mAdapter = new OrderAdapter(R.layout.activity_fragment4_order_item);
        recyclerview1.setAdapter(mAdapter);

        niubiEmptyView = new NiubiEmptyView();
        niubiEmptyView.bind(this, recyclerview1, mAdapter);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_right) {
            onBackPressed();
        } else {
        }
    }

    @Override
    public void OnOrderListSuccess(SOrderListBean sOrderListBean) {
        // 接口bufen
        mList = sOrderListBean.getList();
        if (mNextRequestPage == 1) {
            setData(true, mList);
            if (mList.size() == 0) {
//                mAdapter.setEmptyView(getView());
                niubiEmptyView.nodata(CommonUtils.TIPS_WUSHUJU);
            }
        } else {
            setData(false, mList);
        }
    }

    @Override
    public void OnOrderListNodata(String s) {
        if (mNextRequestPage == 1) {
            mAdapter.setEnableLoadMore(true);
            niubiEmptyView.nodata(CommonUtils.TIPS_WUSHUJU);
        } else {
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void OnOrderListFail(String s) {
        if (mNextRequestPage == 1) {
            mAdapter.setEnableLoadMore(true);
            // 根据需求修改bufen
            mAdapter.setNewData(null);
//            niubiEmptyView.errornet(CommonUtils.TIPS_WUSHUJU);
            niubiEmptyView.errornet(CommonUtils.TIPS_WUWANG);
        } else {
            mAdapter.loadMoreFail();
        }
    }
}
