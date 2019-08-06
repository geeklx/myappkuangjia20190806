package com.example.slbappindex.fragment.fragment1.huibendetaillistpart.fenleilistpart;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappshouye.bean.SHuibenDetailListBean;
import com.example.biz3slbappshouye.bean.SListCommBean1;
import com.example.biz3slbappshouye.presenter.SHuibenDetailListPresenter;
import com.example.biz3slbappshouye.view.SHuibenDetailListCommView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.widgets.recyclerviewnice.XRecyclerView;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment1.popcommpart.PopCommActivity;
import com.example.slbappindex.fragment.fragment1.popcommpart.PopComm_ListBean;
import com.haier.cellarette.baselibrary.emptyview.NiubiEmptyView;
import com.haier.cellarette.baselibrary.networkview.NetModel;
import com.haier.cellarette.baselibrary.networkview.NetworkChangeListener;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class CategoryBooksListActivity extends SlbBaseActivity implements View.OnClickListener, SHuibenDetailListCommView {

    private LinearLayout ll_nl2;
    private TextView tv_right;
    private TextView tvLeft;
    private TextView tvCenter;
    private XRecyclerView mRecyclerView;
    private CategoryBooksListAdapter mAdapter;
    private List<SListCommBean1> mListTemp;
    private NiubiEmptyView niubiEmptyView;
    // 分页
    private static final int PAGE_SIZE = 12;
    private int mNextRequestPage = 1;
    private int mPage;
    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    private SHuibenDetailListPresenter presenter;
    private String ids;
    private String ids_cate;
    private String titles;
    private RefreshLayout refreshLayout;
    private ClassicsHeader smart_header1;
    private ClassicsFooter smart_footer1;
    private String hios_content = "";// 点击后请求接口

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment1_categorybooks_list;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        ids = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS);
        ids_cate = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS);
        titles = getIntent().getStringExtra(CommonUtils.HUIBEN_TITLES);
//        Toasty.normal(this, ids).show();
        findview();
        onclick();
        donetwork();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        registerReceiver(mlistener, filter);
    }

    private void donetwork() {
        tvCenter.setText(titles);
        tvLeft.setVisibility(View.VISIBLE);

        // 系列绘本bufen
        mListTemp = new ArrayList<>();
        presenter = new SHuibenDetailListPresenter();
        presenter.onCreate(this);
        setDatas();
    }

    private void select_useful(int drawabless) {
        Drawable drawable = getResources().getDrawable(drawabless);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        tv_right.setCompoundDrawables(null, null, drawable, null);
    }

    @Override
    protected void onResume() {
        MobclickAgent.onEvent(this, "CategoryBooksListActivity");
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_left) {
            //
            onBackPressed();
        } else if (i == R.id.tv_right) {
            //
            select_useful(R.drawable.arrows_up3);
            Intent intent = new Intent("hs.act.slbapp.PopCommActivity");
            intent.putExtra("ageRange", ageRange);
            startActivityForResult(intent, PopCommActivity.requestCodes);
        } else {

        }
    }

    private String ageRange = "-1";

    @Override
    protected void onActResult(int requestCode, int resultCode, Intent data) {
        super.onActResult(requestCode, resultCode, data);
        if (requestCode == PopCommActivity.requestCodes) {
            select_useful(R.drawable.arrows_down3);
            if (resultCode == PopCommActivity.resultCodes) {
                PopComm_ListBean bean = (PopComm_ListBean) data.getSerializableExtra("bean");
                tv_right.setText(bean.getText_content());
                ageRange = bean.getText_id();
                setDatas();

            }
        }

    }

    private void onclick() {
        tvLeft.setOnClickListener(this);
        tv_right.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                setDatas();
            }
        });
        niubiEmptyView.setRetry(new NiubiEmptyView.RetryListener() {
            @Override
            public void retry() {
                setDatas();
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //item click
                SListCommBean1 bean = (SListCommBean1) adapter.getItem(position);
//                Toasty.normal(CategoryBooksListActivity.this, bean.getBookName() + "item click").show();
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SListCommBean1 bean = (SListCommBean1) adapter.getItem(position);
                int i = view.getId();
//                HiosHelper.resolveAd(CategoryBooksListActivity.this, CategoryBooksListActivity.this, bean.getHios());
                //
                hios_content = bean.getHios();
                presenter.getHuibenDetailListData("-1", bean.getBookId(), "", 1, 2);

            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);

    }

    private void findview() {
        refreshLayout = findViewById(R.id.refreshLayout);
        smart_header1 = findViewById(R.id.smart_header1);
//        smart_footer1 = findViewById(R.id.smart_footer1);
        smart_header1.setEnableLastTime(false);

        tvLeft = findViewById(R.id.tv_left);
        tvCenter = findViewById(R.id.tv_center);
        ll_nl2 = findViewById(R.id.ll_nl2);
        tv_right = findViewById(R.id.tv_right);
        mRecyclerView = findViewById(R.id.recycler_view0);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
        mAdapter = new CategoryBooksListAdapter(R.layout.activity_fragment21_imgstyle3_item_new1);
        mRecyclerView.setAdapter(mAdapter);

        niubiEmptyView = new NiubiEmptyView();
        niubiEmptyView.bind(this, mRecyclerView, mAdapter);

    }

    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
            // 如果不在顶部先回到顶部 再点击才刷新
            mRecyclerView.smoothScrollToPosition(0);
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

    private void set_del(boolean isRe) {
        for (int i = 0; i < mAdapter.getItemCount() - 1; i++) {
            SListCommBean1 bean1 = mAdapter.getItem(i);
            bean1.setRetweet(isRe);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mlistener);
        presenter.onDestory();
        super.onDestroy();

    }

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

//                setDatas();
//                niubiEmptyView.loading();
//                mEmptyView.success();

            } else if (wifiState != null && State.CONNECTED == wifiState) {// 无线网络连接成功
                Log.d(TAG1, "无线网络连接成功");

                NetModel.getInstance().setMobile(false);
                NetModel.getInstance().setWifi(true);
                NetModel.getInstance().setConnected(true);

//                setDatas();
//                niubiEmptyView.loading();
//                mEmptyView.success();

            } else if (wifiState != null && mobileState != null
                    && State.CONNECTED != wifiState
                    && State.CONNECTED != mobileState) {// 手机没有任何的网络
                Log.d(TAG1, "手机没有任何的网络");

                NetModel.getInstance().setMobile(false);
                NetModel.getInstance().setWifi(false);
                NetModel.getInstance().setConnected(false);

                niubiEmptyView.errornet(CommonUtils.TIPS_WUWANG);
//                mEmptyView.errorNet();

            }
        }
    };


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */


//    private List<SearchKeyListBean> mList2 = HuibenFragmentListUtils.getInstance(SearchListActivity.this).getSampleDataBooks(6);

    /**
     * 加载更多bufen
     */
    private void loadMore() {
        set_re_data();
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
        ll_nl2.setVisibility(View.VISIBLE);
        presenter.getHuibenDetailListData( ageRange, "", ids_cate, mNextRequestPage, PAGE_SIZE);
    }

    @Override
    public void OnListComm1Success(SHuibenDetailListBean sHuibenDetailListBean, String s) {
        if (!TextUtils.isEmpty(hios_content)) {
            List<SListCommBean1> mList1 = new ArrayList<>();
            mList1 = sHuibenDetailListBean.getBookViewList();
            if (mList1.size() == 1) {
                HiosHelper.resolveAd(CategoryBooksListActivity.this, CategoryBooksListActivity.this, mList1.get(0).getHios());
            } else {
                HiosHelper.resolveAd(CategoryBooksListActivity.this, CategoryBooksListActivity.this, hios_content);
            }
            hios_content = "";
            return;
        }
        refreshLayout.finishRefresh();
        mListTemp = sHuibenDetailListBean.getBookViewList();
        if (mNextRequestPage == 1) {
            setData(true, mListTemp);
            if (mListTemp.size() == 0) {
//            mAdapter.setEmptyView(getView());
                niubiEmptyView.nodata(CommonUtils.TIPS_WUSHUJU);
            }
        } else {
            setData(false, mListTemp);
        }

    }

    @Override
    public void OnListComm1Nodata(String s) {
        refreshLayout.finishRefresh(false);
        if (mNextRequestPage == 1) {
            mAdapter.setEnableLoadMore(true);
            niubiEmptyView.nodata(CommonUtils.TIPS_WUSHUJU);

        } else {
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void OnListComm1Fail(String s) {
        if (!TextUtils.isEmpty(hios_content)) {
            hios_content = "";
            return;
        }
        refreshLayout.finishRefresh(false);
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
