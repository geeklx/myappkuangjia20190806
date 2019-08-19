package com.example.slbappindex.fragment.fragment1.tingshulistpart;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappshouye.bean.SLB2CategoryListBean;
import com.example.biz3slbappshouye.bean.SLB2CategoryListBean1;
import com.example.biz3slbappshouye.presenter.SLB2CategoryListPresenter;
import com.example.biz3slbappshouye.view.SLB2CategoryListView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.widgets.recyclerviewnice.XRecyclerView;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment1.tingshulistpart.listheader.ReFreshClassicsHeader;
import com.example.slbappindex.fragment.fragment1.popcommpart.PopCommActivity;
import com.example.slbappindex.fragment.fragment1.popcommpart.PopComm_ListBean;
import com.geek.libglide47.base.GlideImageView;
import com.haier.cellarette.baselibrary.emptyview.NiubiEmptyView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

public class ListenBooksListActivity extends SlbBaseActivity implements View.OnClickListener, SLB2CategoryListView {

    private LinearLayout ll_nl2;
    private GlideImageView iv_bg2;
    private TextView tv_right;
    private TextView tvLeft;
    private TextView tvCenter;
    private XRecyclerView mRecyclerView;
    private ListenBooksListAdapter mAdapter;
    private List<SLB2CategoryListBean1> mListTemp;
    private NiubiEmptyView niubiEmptyView;
    // 分页
    private static final int PAGE_SIZE = 12;
    private int mNextRequestPage = 1;
    private int mPage;
    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    private SLB2CategoryListPresenter presenter;
    private String LB_IDS1;
    private String LB_IDS2;
    private String LB_NAME1;
    private String LB_NAME2;
    private RefreshLayout refreshLayout;
    private ReFreshClassicsHeader smart_header1;
    private ClassicsFooter smart_footer1;
    //
    private View view;
    private ImageView iv111;
    private RelativeLayout rl123;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment1_listenbooks1_list;
    }

    @Override
    protected void onResume() {
        MobclickAgent.onEvent(this, "ListenBooksListActivity");
        super.onResume();
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        LB_IDS1 = getIntent().getStringExtra(CommonUtils.LB_IDS1);
        LB_IDS2 = getIntent().getStringExtra(CommonUtils.LB_IDS2);
        LB_NAME1 = getIntent().getStringExtra(CommonUtils.LB_NAME1);
        LB_NAME2 = getIntent().getStringExtra(CommonUtils.LB_TYPE1);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        tvCenter.setText(LB_NAME1);
        tvLeft.setVisibility(View.VISIBLE);
        tv_right.setVisibility(View.VISIBLE);

        presenter = new SLB2CategoryListPresenter();
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
                SLB2CategoryListBean1 bean = (SLB2CategoryListBean1) adapter.getItem(position);
                int i = view.getId();
                Intent intent = new Intent("hs.act.slbapp.ListenBooksListActivity2");
                // String categoryId, String columnId, String type
                intent.putExtra(CommonUtils.HUIBEN_IDS_ZONG, bean.getpId());
                intent.putExtra(CommonUtils.HUIBEN_IDS, bean.getItemId());
                intent.putExtra(CommonUtils.HUIBEN_TITLES, bean.getTitle());
                startActivity(intent);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SLB2CategoryListBean1 bean = (SLB2CategoryListBean1) adapter.getItem(position);
                int i = view.getId();
                Intent intent = new Intent("hs.act.slbapp.ListenBooksListActivity2");
                intent.putExtra(CommonUtils.HUIBEN_IDS_ZONG, bean.getpId());
                intent.putExtra(CommonUtils.HUIBEN_IDS, bean.getItemId());
                intent.putExtra(CommonUtils.HUIBEN_TITLES, bean.getTitle());
                startActivity(intent);
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

//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
////                if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_DRAGGING) {
////                    //show 手指拖拽和停止均可展示图片
////                    mAdapter.setScrolling(false);
////                    //通知adapter恢复getview中的图下载
////                    if (Glide.with(ListenBooksListActivity.this).isPaused())
////                        Glide.with(ListenBooksListActivity.this).resumeRequests();
////                } else {
////                    //hide
////                    mAdapter.setScrolling(true);
////                }
//                //
////                 if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_DRAGGING) {
////                     Glide.with(ListenBooksListActivity.this).resumeRequests();
////                 } else {
////                     Glide.with(ListenBooksListActivity.this).pauseRequests();
////                 }
//                //
//                switch (newState) {
//                    case RecyclerView.SCROLL_STATE_IDLE: // The RecyclerView is not currently scrolling.
//                        //当屏幕停止滚动，加载图片
//                        try {
//                            if (getApplicationContext() != null)
//                                Glide.with(getApplicationContext()).resumeRequests();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    case RecyclerView.SCROLL_STATE_DRAGGING: // The RecyclerView is currently being dragged by outside input such as user touch input.
//                        //当屏幕滚动且用户使用的触碰或手指还在屏幕上，停止加载图片
//                        try {
//                            if (getApplicationContext() != null)
//                                Glide.with(getApplicationContext()).pauseRequests();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    case RecyclerView.SCROLL_STATE_SETTLING: // The RecyclerView is currently animating to a final position while not under outside control.
//                        //由于用户的操作，屏幕产生惯性滑动，停止加载图片
//                        try {
//                            if (getApplicationContext() != null)
//                                Glide.with(getApplicationContext()).pauseRequests();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                }
//
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//
//            }
//        });
    }

    private void findview() {
        rl123 = findViewById(R.id.rl123);
        refreshLayout = findViewById(R.id.refreshLayout);
        smart_header1 = findViewById(R.id.smart_header1);
//        smart_footer1 = findViewById(R.id.smart_footer1);
//        smart_header1.setEnableLastTime(false);

        tvLeft = findViewById(R.id.tv_left);
        tvCenter = findViewById(R.id.tv_center);
        iv_bg2 = findViewById(R.id.iv_bg2);
        ll_nl2 = findViewById(R.id.ll_nl2);
        tv_right = findViewById(R.id.tv_right);
        mRecyclerView = findViewById(R.id.recycler_view0);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));
        mAdapter = new ListenBooksListAdapter(R.layout.activity_fragment1_books_list_item_new1);

        view = getLayoutInflater().inflate(R.layout.activity_addheader_listenbook, (ViewGroup) mRecyclerView.getParent(), false);
        iv111 = view.findViewById(R.id.iv111);
        mAdapter.addHeaderView(view);
        mRecyclerView.setAdapter(mAdapter);

        niubiEmptyView = new NiubiEmptyView();
        niubiEmptyView.bind(this, rl123, mAdapter);

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

    @Override
    protected void onDestroy() {
        presenter.onDestory();
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
        ll_nl2.setVisibility(View.VISIBLE);
        presenter.getLB2CategoryListData(ageRange, mNextRequestPage, PAGE_SIZE, LB_IDS1, LB_IDS2, LB_NAME2);
    }

    @Override
    public void OnLB2CategoryListSuccess(SLB2CategoryListBean slb2CategoryListBean, String s) {
        refreshLayout.finishRefresh();
        mListTemp = slb2CategoryListBean.getList();
        if (mNextRequestPage == 1) {
            if (slb2CategoryListBean.getDataExt() != null) {
//                Glide.with(this).load(sListCommBean.getDataExt().getScreenImg()).into(iv_bg2);
                Glide.with(this).load(slb2CategoryListBean.getDataExt().getScreenImg()).into(iv111);
//                iv_bg2.loadImage(sListCommBean.getDataExt().getScreenImg(),R.drawable.btn_common_lightblue0);
            }
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
    public void OnLB2CategoryListNodata(String s) {
        refreshLayout.finishRefresh(false);
//        Toasty.normal(CategoryBooksListActivity.this, msg).show();
        if (mNextRequestPage == 1) {
            mAdapter.setEnableLoadMore(true);
            niubiEmptyView.nodata(CommonUtils.TIPS_WUSHUJU);
        } else {
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void OnLB2CategoryListFail(String s) {
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
