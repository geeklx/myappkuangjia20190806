package com.example.slbappindex.fragment.fragment1.mybookslistpart;

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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappshouye.bean.SMyBooksBean;
import com.example.biz3slbappshouye.bean.SMyBooksBean1;
import com.example.biz3slbappshouye.presenter.SMyBooksPresenter;
import com.example.biz3slbappshouye.view.SMyBooksView;
import com.example.biz3slbappusercenter.bean.SLB1SaveFavoritesBean;
import com.example.biz3slbappusercenter.presenter.SLB1SaveFavoritesPresenter;
import com.example.biz3slbappusercenter.view.SLB1SaveFavoritesView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappindex.R;
import com.haier.cellarette.baselibrary.emptyview.NiubiEmptyView;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.BookPaddingDecoration;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

public class MyBooksListActivity extends SlbBaseActivity implements View.OnClickListener, SMyBooksView, SLB1SaveFavoritesView {

    private TextView tv_right;
    private TextView tvLeft;
    private TextView tvCenter;
    private RecyclerView mRecyclerView;
    private BookPaddingDecoration recycleViewDivider;
    private MyBooksListAdapter mAdapter;
    private List<SMyBooksBean1> mListTemp;
    private NiubiEmptyView niubiEmptyView;
    // 分页
    private static final int PAGE_SIZE = 99;
    private int mNextRequestPage = 1;
    private int mPage;
    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    private SMyBooksPresenter presenter;
    private String ids;
    private String ids_cate;
    private String titles;
    private RefreshLayout refreshLayout;
    private ClassicsHeader smart_header1;
    private ClassicsFooter smart_footer1;
    private String tips1 = "书架空空赶紧去阅读吧";

    //
    private SLB1SaveFavoritesPresenter presenter1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment1_mybooks_list;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        ids = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS);
        ids_cate = getIntent().getStringExtra(CommonUtils.HUIBEN_CATEIDS);
        titles = getIntent().getStringExtra(CommonUtils.HUIBEN_TITLES);
//        Toasty.normal(this, ids).show();
        findview();
        onclick();
        donetwork();
        //
        MobclickAgent.onEvent(this, "minebookcase-more");
    }

    private void donetwork() {
        tvCenter.setText(titles);
        tvLeft.setVisibility(View.VISIBLE);
        if (SlbLoginUtil2.get().isUserLogin()) {
            tv_right.setVisibility(View.VISIBLE);
        } else {
            tv_right.setVisibility(View.GONE);
        }
        // 删除
        presenter1 = new SLB1SaveFavoritesPresenter();
        presenter1.onCreate(this);
        // 我的书架bufen
        presenter = new SMyBooksPresenter();
        presenter.onCreate(this);
        setDatas();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_left) {
            //
            onBackPressed();
        } else if (i == R.id.tv_right) {
            // 管理 完成
            final String aaaa = tv_right.getText().toString().trim();
            SlbLoginUtil2.get().loginTowhere(MyBooksListActivity.this, new Runnable() {
                @Override
                public void run() {
                    if (TextUtils.equals(aaaa, "管理")) {
                        // 显示del
                        tv_right.setText("完成");
                        set_del(true);
                    } else if (TextUtils.equals(aaaa, "完成")) {
                        // 隐藏del
                        tv_right.setText("管理");
                        set_del(false);
                    }
                }
            });

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
                SMyBooksBean1 bean = (SMyBooksBean1) adapter.getItem(position);
//                Toasty.normal(CategoryBooksListActivity.this, bean.getBookName() + "item click").show();
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SMyBooksBean1 bean = (SMyBooksBean1) adapter.getItem(position);
                int i = view.getId();
                if (i == R.id.brademo1_img) {
                    HiosHelper.resolveAd(MyBooksListActivity.this, MyBooksListActivity.this, bean.getHios());

                }

                if (i == R.id.iv_del) {
//                    Toasty.normal(MyBooksListActivity.this, "删除" + position).show();
                    presenter1.getLB1SaveFavoritesData(bean.getItemId(), bean.getpId(), true, bean.getSourceType());
                    adapter.notifyItemRemoved(position);
                    adapter.getData().remove(bean);
                    //请求接口bufen
                    if (adapter.getData().size() == 0) {
                        // mRecyclerView.addItemDecoration(null);// 书架bufen
                        mRecyclerView.removeItemDecoration(recycleViewDivider);
                        tv_right.setVisibility(View.GONE);
                        // mRecyclerView.setAdapter(adapter);
                        niubiEmptyView.nodata(tips1);
                    }
                }
//                if (i == R.id.brademo1_img) {
//                    Toasty.normal(CategoryBooksListActivity.this, bean.getBannerImg() + "    " + position).show();
//                } else if (i == R.id.brademo1_tweetName) {
//                    Toasty.normal(CategoryBooksListActivity.this, bean.getBookName() + position).show();
//                } else if (i == R.id.brademo1_tweetText) {
//                    Toasty.normal(CategoryBooksListActivity.this, bean.getBookName() + position).show();
//                } else if (i == R.id.iv_del) {
//                    Toasty.normal(CategoryBooksListActivity.this, "删除" + position).show();
//                    adapter.notifyItemRemoved(position);
//                    adapter.getData().remove(bean);
//                    //请求接口bufen
//                    if (adapter.getData().size() == 0) {
//                        niubiEmptyView.errornet("暂无数据");
//                    }
//                } else {
//                }
            }
        });
//        mAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
//            @Override
//            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
//                SListCommBean1 bean = (SListCommBean1) adapter.getItem(position);
//                int id = view.getId();
//                if (bean.isRetweet()) {
//                    set_del(false);
//                } else {
//                    set_del(true);
//                }
////                if (i == R.id.brademo1_img) {
////                    Toasty.normal(SearchListActivity.this, bean.getUserAvatar() + "长按    " + position).show();
////                } else if (i == R.id.brademo1_tweetName) {
////                    Toasty.normal(SearchListActivity.this, bean.getUserName() + position).show();
////                } else if (i == R.id.brademo1_tweetText) {
////                    Toasty.normal(SearchListActivity.this, bean.getText() + position).show();
////                } else {
////                }
//                return true;
//            }
//        });
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
        tv_right = findViewById(R.id.tv_right);
        mRecyclerView = findViewById(R.id.recycler_view0);
        recycleViewDivider = new BookPaddingDecoration(this, OrientationHelper.VERTICAL, R.drawable.bg_book_shelf_charge9);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
//        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
//        mLinearSnapHelper.attachToRecyclerView(mRecyclerView);
        mAdapter = new MyBooksListAdapter(R.layout.activity_fragment1_mybooks_list_item_new1);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setNotDoAnimationCount(3);// mFirstPageItemCount
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
//            mRecyclerView.removeItemDecoration(recycleViewDivider);
            mAdapter.loadMoreEnd(isRefresh);
//            Toasty.normal(SearchListActivity.this, "已经到底了").show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    private void set_del(boolean isRe) {
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            SMyBooksBean1 bean1 = mAdapter.getItem(i);
            if (bean1 != null) {
                bean1.setRetweet(isRe);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        presenter1.onDestory();
        super.onDestroy();

    }


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
        mRecyclerView.removeItemDecoration(recycleViewDivider);// 书架bufen
        tv_right.setText("管理");
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        set_re_data();

    }

    private void set_re_data() {
        if (ids.equals(CommonUtils.HUIBEN_TAG2)) {
            presenter.getMyBooksData(mNextRequestPage, PAGE_SIZE, "1");
        }
    }


    /**
     * ------------------我的书架----------
     */

    @Override
    public void OnMyBooksSuccess(SMyBooksBean sListCommBean, String s) {
        refreshLayout.finishRefresh();
        mListTemp = sListCommBean.getList();
        if (mNextRequestPage == 1) {
            if (mListTemp.size() == 0) {
//            mAdapter.setEmptyView(getView());
                mRecyclerView.removeItemDecoration(recycleViewDivider);// 书架bufen
                tv_right.setVisibility(View.GONE);
                niubiEmptyView.nodata(tips1);
            } else {
                mRecyclerView.addItemDecoration(recycleViewDivider);// 书架bufen
                if (SlbLoginUtil2.get().isUserLogin()) {
                    tv_right.setVisibility(View.VISIBLE);
                } else {
                    tv_right.setVisibility(View.GONE);
                }
            }
            setData(true, mListTemp);
        } else {
            setData(false, mListTemp);
        }


    }

    @Override
    public void OnMyBooksNodata(String msg) {
        refreshLayout.finishRefresh(false);
//        Toasty.normal(CategoryBooksListActivity.this, msg).show();
        if (mNextRequestPage == 1) {
            mAdapter.setEnableLoadMore(true);
            niubiEmptyView.nodata(tips1);
        } else {
            mAdapter.loadMoreFail();
        }

    }

    @Override
    public void OnMyBooksFail(String msg) {
        refreshLayout.finishRefresh(false);
//        Toasty.normal(CategoryBooksListActivity.this, msg).show();
        if (mNextRequestPage == 1) {
            mAdapter.setEnableLoadMore(true);
            niubiEmptyView.errornet("请确认网络是否可用");

        } else {
            mAdapter.loadMoreFail();
        }
    }

    // 删除我的书架
    @Override
    public void OnLB1SaveFavoritesSuccess(SLB1SaveFavoritesBean s) {
        Toasty.normal(this, "已移除我的书架").show();
    }

    @Override
    public void OnLB1SaveFavoritesNodata(SLB1SaveFavoritesBean s) {
        Toasty.normal(this, s.getMsg()).show();
    }

    @Override
    public void OnLB1SaveFavoritesFail(String s) {
        Toasty.normal(this, s).show();
    }
}
