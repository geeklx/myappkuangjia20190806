package com.example.slbappindex.fragment.fragment1.historypart;

import android.os.Bundle;
//import android.support.annotation.Nullable;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappshouye.bean.SLB1HistoryBean;
import com.example.biz3slbappshouye.bean.SLB1HistoryBean1;
import com.example.biz3slbappshouye.presenter.SLB1HistoryPresenter;
import com.example.biz3slbappshouye.view.SLB1HistoryView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.playermusic.floatbutton.floatbutton2.DisplayUtil;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.utils.GridSpacingItemDecoration;
import com.haier.cellarette.baselibrary.emptyview.NiubiEmptyView;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

public class HistoryListActivity extends SlbBaseActivity implements View.OnClickListener, SLB1HistoryView {

    private LinearLayout ll_nl2;
    private TextView tv_right;
    private TextView tvLeft;
    private TextView tvCenter;
    private RecyclerView mRecyclerView;
    private Fragment3MqhAdapter mAdapter;
    private List<SLB1HistoryBean1> mListTemp;
    private NiubiEmptyView niubiEmptyView;
    // 分页
    private static final int PAGE_SIZE = 30;
    private int mNextRequestPage = 1;
    private int mPage;
    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    private SLB1HistoryPresenter presenter3;
    private String ids_zong;
    private String ids;
    private String ids_cate;
    private String titles;
    private RefreshLayout refreshLayout;
    private ClassicsHeader smart_header1;
    private ClassicsFooter smart_footer1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment1_history_list;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        ids_zong = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS_ZONG);
        ids = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS);
        ids_cate = getIntent().getStringExtra(CommonUtils.HUIBEN_CATEIDS);
        titles = getIntent().getStringExtra(CommonUtils.HUIBEN_TITLES);
        findview();
        onclick();
        donetwork();
        //
        MobclickAgent.onEvent(this, "history-more");
    }

    private void donetwork() {
        tvCenter.setText(titles);
        tvLeft.setVisibility(View.VISIBLE);
        tv_right.setVisibility(View.VISIBLE);

        // 历史记录bufen
        presenter3 = new SLB1HistoryPresenter();
        presenter3.onCreate(this);
        setDatas();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_left) {
            //
            onBackPressed();
        } else {

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
                SLB1HistoryBean1 bean = (SLB1HistoryBean1) adapter.getItem(position);
//                Toasty.normal(CategoryBooksListActivity.this, bean.getBookName() + "item click").show();
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SLB1HistoryBean1 bean = (SLB1HistoryBean1) adapter.getItem(position);
                int i = view.getId();
                HiosHelper.resolveAd(HistoryListActivity.this, HistoryListActivity.this, bean.getHios());

            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);

//        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
//                Toasty.normal(SearchListActivity.this, Integer.toString(position)).show();
//            }
//        });

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

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4, RecyclerView.VERTICAL, false));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(4, (int) (DisplayUtil.getScreenWidth(this) * 8f / 375), true));
        mAdapter = new Fragment3MqhAdapter(R.layout.activity_mqh_fragment1_rec1history_item2);
        mRecyclerView.setAdapter(mAdapter);

        niubiEmptyView = new NiubiEmptyView();
        niubiEmptyView.bind(this, mRecyclerView, mAdapter);

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
    protected void onDestroy() {
        presenter3.onDestory();
        super.onDestroy();

    }

    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */

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
        if (TextUtils.equals(ids, CommonUtils.HUIBEN_TAG3)) {
            presenter3.getLB1HistoryData(mNextRequestPage, PAGE_SIZE);
        }
    }

    @Override
    public void OnLB1HistorySuccess(SLB1HistoryBean slb1HistoryBean, String s) {
        refreshLayout.finishRefresh();
        mListTemp = slb1HistoryBean.getList();
        if (mNextRequestPage == 1) {
            setData(true, mListTemp);
            if (mListTemp.size() == 0) {
//            mAdapter.setEmptyView(getView());
                niubiEmptyView.errornet(CommonUtils.TIPS_WUSHUJU);
            }
        } else {
            setData(false, mListTemp);
        }
    }

    @Override
    public void OnLB1HistoryNodata(String s) {
        refreshLayout.finishRefresh(false);
//        Toasty.normal(CategoryBooksListActivity.this, msg).show();
        if (mNextRequestPage == 1) {
            mAdapter.setEnableLoadMore(true);
            niubiEmptyView.errornet(CommonUtils.TIPS_WUSHUJU);
        } else {
            mAdapter.loadMoreFail();
        }

    }

    @Override
    public void OnLB1HistoryFail(String s) {
        refreshLayout.finishRefresh(false);
//        Toasty.normal(CategoryBooksListActivity.this, msg).show();
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
