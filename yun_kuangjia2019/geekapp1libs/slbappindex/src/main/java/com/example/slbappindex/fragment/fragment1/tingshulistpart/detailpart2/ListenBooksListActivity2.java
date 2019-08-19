package com.example.slbappindex.fragment.fragment1.tingshulistpart.detailpart2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
//import android.support.annotation.Nullable;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappshouye.bean.SLB3CategoryListBean;
import com.example.biz3slbappshouye.bean.SLB4CategoryListDetailBean1;
import com.example.biz3slbappshouye.presenter.SLB3CategoryListPresenter;
import com.example.biz3slbappshouye.view.SLB3CategoryListView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.playermusic.ListenBookObservable;
import com.example.slbappcomm.playermusic.ListenMusicPlayerService;
import com.example.slbappcomm.pop.renwupay.payforvip.PopPayForVip2;
import com.example.slbappcomm.pop.renwupay.payforvip.PopPayForVip3;
import com.example.slbappcomm.pop.successpay.PopSuccessPay;
import com.example.slbappcomm.widgets.recyclerviewnice.XRecyclerView;
import com.example.slbappindex.R;
import com.example.slbappxmly.XmlyUtil;
import com.geek.libglide47.base.GlideImageView;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;
import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ListenBooksListActivity2 extends SlbBaseActivity implements View.OnClickListener, SLB3CategoryListView, Observer {

    private GlideImageView iv_bg2;
    private LinearLayout ll_nl2;
    private TextView tv_right;
    private TextView tvLeft;
    private TextView tvCenter;
    private XRecyclerView mRecyclerView;
    private ListenBooksListAdapter2 mAdapter;
    private List<SLB4CategoryListDetailBean1> mListTemp;
    private EmptyView mEmptyView;
    // 分页
    private static final int PAGE_SIZE = 999;
    private int mNextRequestPage = 1;
    private int mPage;
    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    private SLB3CategoryListPresenter presenter;
    private String ids_zong;
    private String ids;
    private String ids_cate;
    private String titles;
    private RelativeLayout rl22;
    private SmartRefreshLayout refreshLayout;
    private ClassicsHeader smart_header1;
    private ClassicsFooter smart_footer1;

    @Override
    protected void onResume() {
        if (SPUtils.getInstance().getInt(CommonUtils.HUIBEN_PAYSUCCESS, -1) == CommonUtils.HUIBEN_PAYSUCCESS_TAG1) {
            // VIP跳出层 支付成功
            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, -1);
            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS, -1);
//            setDatas();

        }
        if (SPUtils.getInstance().getInt(CommonUtils.HUIBEN_PAYSUCCESS, -1) == CommonUtils.HUIBEN_PAYSUCCESS_TAG2) {
            // 订单跳出层
            if (getWindow().getDecorView().isAttachedToWindow()) {
                PopSuccessPay popSuccessPay = new PopSuccessPay(this);
                popSuccessPay.showAtLocation(getWindow().getDecorView(),
                        Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
                        , 0, 0); // 设置layout在PopupWindow中显示的位置
            }
            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, -1);
            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS, -1);
//            setDatas();

        }
        setDatas();
        // 获取播放状态显示gif
//        if (SPUtils.getInstance().getBoolean(CommonUtils.LISTENBOOK_TAG1, false)) {
//            if (mListTemp != null) {
//                bindservice();
//            }
//        } else {
//            mAdapter.setEX_ID("");
//            mAdapter.notifyDataSetChanged();
//        }
        super.onResume();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment1_listenbooks2_list;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        //虚拟键
        if (NavigationBarUtil.hasNavigationBar(this)) {
//            NavigationBarUtil.initActivity(getWindow().getDecorView());
            NavigationBarUtil.hideBottomUIMenu(this);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        ids_zong = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS_ZONG);
        ids = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS);
        titles = getIntent().getStringExtra(CommonUtils.HUIBEN_TITLES);
        //
//        MobclickAgent.onEvent(this, "ListenBooksListActivity2");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", titles);
//        map.put("quantity","3");
        MobclickAgent.onEvent(this, "ListenBooksListActivity2", map);
        findview();
        onclick();
        donetwork();
        // 喜马拉雅听书数据
        configXmly();
    }

    private void bindservice() {
        Intent intent = new Intent(this, ListenMusicPlayerService.class);
        intent.putExtra("flag", flag);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    private ListenMusicPlayerService.MyBinder mBinder;
    private ListenBookObservable listenBookObservable;
    private boolean flag;
    private String aaa = "";
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (ListenMusicPlayerService.MyBinder) service;
            listenBookObservable = ((ListenMusicPlayerService.MyBinder) service).getMyObservable();
            listenBookObservable.addObserver(ListenBooksListActivity2.this);
            flag = true;
            mBinder.getService().setIs_no_get_time(false);
            aaa = mBinder.getService().getEX_ID();
            set_scroll_view(aaa);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            flag = false;
        }
    };

    @Override
    public void update(Observable observable, Object o) {
        if (BaseAppManager.getInstance().top().equals(ListenBooksListActivity2.this)) {
            set_scroll_view(String.valueOf(o));
        }
    }

    private void set_scroll_view(String itemid) {
        mAdapter.setEX_ID(itemid);
        mAdapter.notifyDataSetChanged();
        int pos = 0;
        for (int i = 0; i < mAdapter.getItemCount() - 1; i++) {
            if (i >= PAGE_SIZE) {
                pos = PAGE_SIZE;
                break;
            }
            if (TextUtils.equals(itemid, mAdapter.getItem(i).getItemId())) {
                pos = i;
                break;
            }
        }
        ((GridLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(pos, 0);
//        mRecyclerView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                mRecyclerView.scrollToPosition(finalPos);
//            }
//        }, 500);
    }

    private XmlyUtil xmlyUtil;

    private void configXmly() {
        xmlyUtil = new XmlyUtil();
//        xmlyUtil.getXmlyList();

    }

    private void donetwork() {
//        tvCenter.setText(titles);
        tvCenter.setText("");
        tvLeft.setVisibility(View.VISIBLE);
        tv_right.setVisibility(View.VISIBLE);

        presenter = new SLB3CategoryListPresenter();
        presenter.onCreate(this);
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
        mEmptyView.bind(rl22).setRetryListener(new EmptyView.RetryListener() {
            @Override
            public void retry() {
                // 重试bufen
                setDatas();
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //item click
                SLB4CategoryListDetailBean1 bean = (SLB4CategoryListDetailBean1) adapter.getItem(position);
//                Toasty.normal(CategoryBooksListActivity.this, bean.getBookName() + "item click").show();
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SLB4CategoryListDetailBean1 bean = (SLB4CategoryListDetailBean1) adapter.getItem(position);
                int i = view.getId();
                if (TextUtils.equals(bean.getReadRight(), "2") || TextUtils.equals(bean.getReadRight(), "4")) {
                    get_shidu_pop2(bean.getpId(), bean.getItemId(), bean.getSourceType());
                } else if (TextUtils.equals(bean.getReadRight(), "3")) {
                    get_shidu_pop3(bean.getpId(), bean.getItemId(), bean.getSourceType());
                } else {
//                    helper.getView(R.id.iv_suo2).setVisibility(View.GONE);
                    Intent intent = new Intent("hs.act.slbapp.ListenMusicActivity");
                    intent.putExtra(CommonUtils.HUIBEN_IDS_ZONG, bean.getpId());
                    intent.putExtra(CommonUtils.HUIBEN_IDS, bean.getItemId());
                    intent.putExtra(CommonUtils.HUIBEN_TITLES, bean.getTitle());
                    intent.putStringArrayListExtra(CommonUtils.HUIBEN_XMLY, xmlyUtil.getList1());
                    startActivity(intent);
                    mAdapter.setEX_ID(bean.getItemId());
                    mAdapter.notifyDataSetChanged();
                }
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
//                if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_DRAGGING) {
//                    Glide.with(ListenBooksListActivity2.this).resumeRequests();
//                } else {
//                    Glide.with(ListenBooksListActivity2.this).pauseRequests();
//                }
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
    }

    private void findview() {
        iv_bg2 = findViewById(R.id.iv_bg2);
        rl22 = findViewById(R.id.rl22);
        mEmptyView = findViewById(R.id.empty_view);
        refreshLayout = findViewById(R.id.refreshLayout);
        smart_header1 = findViewById(R.id.smart_header1);
//        smart_footer1 = findViewById(R.id.smart_footer1);
        smart_header1.setEnableLastTime(false);

        tvLeft = findViewById(R.id.tv_left);
        tvCenter = findViewById(R.id.tv_center);
        ll_nl2 = findViewById(R.id.ll_nl2);
        tv_right = findViewById(R.id.tv_right);
        mRecyclerView = findViewById(R.id.recycler_view0);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(false);
//        mRecyclerView.setFocusable(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));
//        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
//        mLinearSnapHelper.attachToRecyclerView(mRecyclerView);
        mAdapter = new ListenBooksListAdapter2(R.layout.activity_fragment1_listenbooks_list_item);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setNotDoAnimationCount(3);// mFirstPageItemCount
//        View view = getLayoutInflater().inflate(R.layout.activity_addheader_listenbook, (ViewGroup) mRecyclerView.getParent(), false);
//        mAdapter.addHeaderView(view);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mEmptyView.success();
            mAdapter.setNewData(data);
        }
        mAdapter.loadMoreEnd(false);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        if (SPUtils.getInstance().getBoolean(CommonUtils.LISTENBOOK_TAG1, false)) {
            if (flag) {
                try {
                    if (listenBookObservable != null) {
                        listenBookObservable.deleteObserver(this);
                    }
                    if (mBinder != null) {
                        mBinder.getService().setIs_no_get_time(true);
                    }
                    unbindService(conn);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }
        super.onPause();
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
        mEmptyView.loading();
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        set_re_data();

    }

    private void set_re_data() {
        presenter.getLB3CategoryListData(mNextRequestPage, PAGE_SIZE, ids_zong, ids);

    }

    @Override
    public void OnLB3CategoryListSuccess(SLB3CategoryListBean slb3CategoryListBean, String s) {
        refreshLayout.finishRefresh();
        mListTemp = slb3CategoryListBean.getList();
        if (mNextRequestPage == 1) {
            if (slb3CategoryListBean.getDataExt() != null) {
                iv_bg2.loadImage(slb3CategoryListBean.getDataExt().getScreenImg(), R.drawable.slb_jq2);
            }
            setData(true, mListTemp);
            if (mListTemp.size() == 0) {
//            mAdapter.setEmptyView(getView());
                mEmptyView.nodata();
            } else {
                refreshLayout.setBackgroundResource(R.drawable.btn_ts2);
            }
        } else {
            refreshLayout.setBackgroundResource(R.drawable.btn_ts2);
            setData(false, mListTemp);
        }
        // 获取播放状态显示gif
        if (SPUtils.getInstance().getBoolean(CommonUtils.LISTENBOOK_TAG1, false)) {
            bindservice();
        } else {
            mAdapter.setEX_ID("");
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void OnLB3CategoryListNodata(String s) {
        refreshLayout.finishRefresh(false);
        if (mNextRequestPage == 1) {
            mAdapter.setEnableLoadMore(true);
            mEmptyView.nodata();

        } else {
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void OnLB3CategoryListFail(String s) {
        refreshLayout.finishRefresh(false);
        if (mNextRequestPage == 1) {
            mAdapter.setEnableLoadMore(true);
            // 根据需求修改bufen
            mAdapter.setNewData(null);
//            niubiEmptyView.errornet(CommonUtils.TIPS_WUSHUJU);
            mEmptyView.errorNet();

        } else {
            mAdapter.loadMoreFail();
        }
    }

    // 试读完成弹窗bufen
    private PopPayForVip2 popShiduPay2;

    private void get_shidu_pop2(final String bookId, final String bookItemId, final String sourceType) {
        popShiduPay2 = new PopPayForVip2(this, R.drawable.slb_popup_shidu33, getResources().getString(R.string.readbooks_tips23), new PopPayForVip2.OnFinishResultClickListener() {
            @Override
            public void onKaitonghuiyuan() {
                // 开通会员
                SlbLoginUtil2.get().loginTowhere(ListenBooksListActivity2.this, new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent("hs.act.slbapp.VipActivity");
                        SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, CommonUtils.HUIBEN_PAYSUCCESS_TAG1);
                        startActivity(intent);
//                        finish();
                        popShiduPay2.dismiss();
                    }
                });
            }

            @Override
            public void onGoumaihuiben() {
                // 购买绘本
                SlbLoginUtil2.get().loginTowhere(ListenBooksListActivity2.this, new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent("hs.act.slbapp.OrderPayActivity");
                        SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, CommonUtils.HUIBEN_PAYSUCCESS_TAG2);
                        intent.putExtra(CommonUtils.HUIBEN_IDS, bookItemId);
                        intent.putExtra(CommonUtils.HUIBEN_IDS_ZONG, bookId);
                        intent.putExtra(CommonUtils.HUIBEN_IDS_sourceType, sourceType);
                        startActivity(intent);
//                        finish();
                        popShiduPay2.dismiss();
                    }
                });
            }
        });
        popShiduPay2.showAtLocation(getWindow().getDecorView(),
                Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
                , 0, 0); // 设置layout在PopupWindow中显示的位置
    }

    // 试读完成弹窗bufen
    private PopPayForVip3 popShiduPay3;

    private void get_shidu_pop3(final String bookId, final String bookItemId, final String sourceType) {
        popShiduPay3 = new PopPayForVip3(this, R.drawable.slb_popup_shidu33, new PopPayForVip3.OnFinishResultClickListener() {
            @Override
            public void onKaitonghuiyuan() {
                // 开通会员
                SlbLoginUtil2.get().loginTowhere(ListenBooksListActivity2.this, new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent("hs.act.slbapp.VipActivity");
                        SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, CommonUtils.HUIBEN_PAYSUCCESS_TAG1);
                        startActivity(intent);
//                        finish();
                        popShiduPay3.dismiss();
                    }
                });
            }

            @Override
            public void onGoumaihuiben() {
                // 购买绘本
                SlbLoginUtil2.get().loginTowhere(ListenBooksListActivity2.this, new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent("hs.act.slbapp.OrderPayActivity");
                        SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, CommonUtils.HUIBEN_PAYSUCCESS_TAG2);
                        intent.putExtra(CommonUtils.HUIBEN_IDS, bookItemId);
                        intent.putExtra(CommonUtils.HUIBEN_IDS_ZONG, bookId);
                        intent.putExtra(CommonUtils.HUIBEN_IDS_sourceType, sourceType);
                        startActivity(intent);
//                        finish();
                        popShiduPay3.dismiss();
                    }
                });
            }
        });
        popShiduPay3.showAtLocation(getWindow().getDecorView(),
                Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
                , 0, 0); // 设置layout在PopupWindow中显示的位置
    }

}
