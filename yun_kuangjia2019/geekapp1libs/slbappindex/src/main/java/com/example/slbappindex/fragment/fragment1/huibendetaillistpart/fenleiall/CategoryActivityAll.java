package com.example.slbappindex.fragment.fragment1.huibendetaillistpart.fenleiall;

import android.os.Bundle;
//import android.support.annotation.Nullable;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.LinearSnapHelper;
//import androidx.appcompat.widget.OrientationHelper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappshouye.bean.SCategoryBean;
import com.example.biz3slbappshouye.bean.SCategoryBean1;
import com.example.biz3slbappshouye.presenter.SIndexCategoryListAllPresenter;
import com.example.biz3slbappshouye.view.SIndexCategoryListAllView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.playermusic.floatbutton.floatbutton2.DisplayUtil;
import com.example.slbappcomm.utils.GridSpacingItemDecoration;
import com.example.slbappcomm.widgets.recyclerviewnice.XRecyclerView;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment1.allhuibenpart.part1.MoreHuibenCategoryAdapter;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryActivityAll extends SlbBaseActivity implements View.OnClickListener, SIndexCategoryListAllView {

    private TextView tvLeft;
    private TextView tvCenter;
    private LinearLayout llNl2;
    private TextView tvRight;
    private XRecyclerView recyclerView0;
    private MoreHuibenCategoryAdapter mAdapter0;
    private List<SCategoryBean1> mList0;
    private SIndexCategoryListAllPresenter presenter0;
    private EmptyView mEmptyView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_categoryall;
    }

    @Override
    protected void onDestroy() {
        presenter0.onDestory();
        super.onDestroy();
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findview();
        onclick();
        tvCenter.setText("全部分类");
        tvLeft.setVisibility(View.VISIBLE);
        llNl2.setVisibility(View.GONE);
        presenter0 = new SIndexCategoryListAllPresenter();
        presenter0.onCreate(this);
        mList0 = new ArrayList<>();
        type = getIntent().getStringExtra(CommonUtils.HIOS_TYPE);
        name = getIntent().getStringExtra(CommonUtils.HIOS_NAME);
        donetwork();


    }

    private String type = "";
    private String name = "";

    private void donetwork() {
        mEmptyView.loading();
        presenter0.getIndexCategoryListData(type);
    }

    private void onclick() {
        tvLeft.setOnClickListener(this);
        mEmptyView.notices(CommonUtils.TIPS_WUSHUJU, CommonUtils.TIPS_WUWANG, "小象正奔向故事里...", "");
        mEmptyView.bind(recyclerView0).setRetryListener(new EmptyView.RetryListener() {
            @Override
            public void retry() {
                //
                donetwork();
            }
        });
        mAdapter0.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //item click
                SCategoryBean1 bean = (SCategoryBean1) adapter.getItem(position);
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("name", bean.getName());
//        map.put("quantity","3");
                if (bean.getHios().contains("pic")) {
                    MobclickAgent.onEvent(CategoryActivityAll.this, "readbookpage_category", map);
                } else if (bean.getHios().contains("audio")) {
                    MobclickAgent.onEvent(CategoryActivityAll.this, "listenbookpage_category", map);
                }
                HiosHelper.resolveAd(CategoryActivityAll.this, CategoryActivityAll.this, bean.getHios());

            }
        });
    }

    private void findview() {
        tvLeft = findViewById(R.id.tv_left);
        tvCenter = findViewById(R.id.tv_center);
        llNl2 = findViewById(R.id.ll_nl2);
        tvRight = findViewById(R.id.tv_right);
        recyclerView0 = findViewById(R.id.recycler_view0);
        mEmptyView = findViewById(R.id.empty_view);
        //
        recyclerView0.setHasFixedSize(true);
        recyclerView0.setNestedScrollingEnabled(false);
        recyclerView0.setFocusable(false);
        recyclerView0.setLayoutManager(new GridLayoutManager(this, 4, RecyclerView.VERTICAL, false));
        recyclerView0.addItemDecoration(new GridSpacingItemDecoration(4, (int) (DisplayUtil.getScreenWidth(this) * 8f / 375), true));
        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
        mLinearSnapHelper.attachToRecyclerView(recyclerView0);
        mAdapter0 = new MoreHuibenCategoryAdapter(R.layout.activity_morehuiben_rec1_item);
        recyclerView0.setAdapter(mAdapter0);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_left) {
            onBackPressed();
        } else {
        }
    }

    @Override
    public void OnIndexCategoryListSuccess(SCategoryBean list) {
        mEmptyView.success();
        mList0 = list.getCategoryViewList();
        mAdapter0.setNewData(mList0);
    }

    @Override
    public void OnIndexCategoryListNodata(String s) {
        mEmptyView.nodata();
//        Toasty.normal(this, s).show();
    }

    @Override
    public void OnIndexCategoryListFail(String s) {
        mEmptyView.errorNet();
//        Toasty.normal(this, s).show();
    }
}
