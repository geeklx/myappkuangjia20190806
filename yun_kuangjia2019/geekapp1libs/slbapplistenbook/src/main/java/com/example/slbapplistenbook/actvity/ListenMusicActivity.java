package com.example.slbapplistenbook.actvity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.OrientationHelper;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappcomm.bean.SLBThreeMusicUrlBean;
import com.example.biz3slbappcomm.presenter.SLBThreeMusicUrlPresenter;
import com.example.biz3slbappcomm.view.SLBThreeMusicUrlView;
import com.example.biz3slbappshouye.bean.SLB3CategoryListBean;
import com.example.biz3slbappshouye.bean.SLB4CategoryListDetailBean;
import com.example.biz3slbappshouye.bean.SLB4CategoryListDetailBean1;
import com.example.biz3slbappshouye.bean.SListenBookDetailBean2;
import com.example.biz3slbappshouye.presenter.SLB3CategoryListPresenter;
import com.example.biz3slbappshouye.presenter.SLB4CategoryListDetailPresenter;
import com.example.biz3slbappshouye.view.SLB3CategoryListView;
import com.example.biz3slbappshouye.view.SLB4CategoryListDetailView;
import com.example.biz3slbappusercenter.bean.SISSCBean;
import com.example.biz3slbappusercenter.bean.SLB1SaveFavoritesBean;
import com.example.biz3slbappusercenter.presenter.SISSaveFavoritesPresenter;
import com.example.biz3slbappusercenter.presenter.SLB1SaveFavoritesPresenter;
import com.example.biz3slbappusercenter.view.SISSaveFavoritesView;
import com.example.biz3slbappusercenter.view.SLB1SaveFavoritesView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.playermusic.ListenBookObservable;
import com.example.slbappcomm.playermusic.ListenMusicPlayerService;
import com.example.slbappcomm.playermusic.SLB4CategoryListDetailBean1Temp;
import com.example.slbappcomm.pop.renwupay.payforvip.PopPayForVip2;
import com.example.slbappcomm.pop.renwupay.payforvip.PopPayForVip3;
import com.example.slbappcomm.pop.share.PopForShare;
import com.example.slbappcomm.pop.successpay.PopSuccessPay;
import com.example.slbappcomm.viewpager.LxCoolViewPager;
import com.example.slbappcomm.widgets.recyclerviewnice.XRecyclerView;
import com.example.slbapplistenbook.R;
import com.example.slbapplistenbook.adapter.ListenBookCoolViewAdapter;
import com.example.slbapplistenbook.adapter.ListenBooksListAdapterList1;
import com.example.slbapplistenbook.adapter.ListenCoolViewPager;
import com.example.slbapplistenbook.utils.ListenMusicUtil;
import com.example.slbapplistenbook.view.ListenMusicImageView;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;
import com.haier.cellarette.baselibrary.handleralluse.base.WeakRefHandler;
import com.haier.cellarette.baselibrary.recycleviewutils.AdvertiseLinearLayoutManager;
import com.haier.cellarette.baselibrary.recycleviewutils.JackSnapHelper;
import com.haier.cellarette.baselibrary.screenutils.LightOnOffUtils;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;
import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;
import com.umeng.analytics.MobclickAgent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ListenMusicActivity extends SlbBaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener,
        SLB1SaveFavoritesView, SISSaveFavoritesView, SLB4CategoryListDetailView, SLB3CategoryListView, SLBThreeMusicUrlView, Observer {

    private ListenMusicImageView Image;
    private TextView tv_share;
    private TextView tv33;
    private TextView MusicStatus;
    private TextView MusicTime;
    private SeekBar MusicSeekBar;
    private TextView MusicTotal;
    private LinearLayout ll22;
    private TextView tv_playmode;
    private TextView tv_pre;
    private TextView tv_startpause;
    private TextView tv_next;
    private TextView tv_home;
    private TextView tv_sc1;
    //
    private ListenMusicPlayerService.MyBinder mBinder;
    private boolean flag;
    //    private boolean is_scroll;
    private boolean is_fisin_houtai;// 当进入页面后将tv_startOrpause状态修改为false
    private int current_url = 0;
    private boolean isfinish1;
    private boolean playmode;// false 全局 true 单曲
    private boolean tv_startOrpause;// false pause true start
    private boolean fromUser_seekbar;// false 正常 true 滚动
    private boolean is_sc;// false 未收藏 true 收藏
    private int seekbar_progress;
    private int PAGE_SIZE = 999;
    //
    private LinearLayout rl1;
    private EmptyView mEmptyView;
    private ListenCoolViewPager viewpager;
    private ListenBookCoolViewAdapter adapter;
    private List<SLB4CategoryListDetailBean1> mList;
    private SLB1SaveFavoritesPresenter presenter1;
    private String EXTRA_ID_ZONG = "";// 以商品的id为txt的名字总id
    private String EXTRA_ID = "";// 以商品的id为txt的名字
    private String EXTRA_NAME = "";// 以商品的id为txt的名字
    private ArrayList<String> EXTRA_XMLY;// 以商品的id为txt的名字
    private SLB4CategoryListDetailPresenter presenter;
    private SISSaveFavoritesPresenter presenter2;
    //
    private XRecyclerView recyclerview3;
    private ListenBooksListAdapterList1 mAdapter3;
    private List<SLB4CategoryListDetailBean1> mList3;
    private SLB3CategoryListPresenter presenter3;
    private SLBThreeMusicUrlPresenter presenter_three_music_url;

    @Override
    protected void onDestroy() {
        if (tv_startOrpause) {
            SPUtils.getInstance().put(CommonUtils.LISTENBOOK_TAG1, false);
            stopService(new Intent(BaseApp.get(), ListenMusicPlayerService.class));
        }
        presenter1.onDestory();
        presenter2.onDestory();
        presenter.onDestory();
        presenter3.onDestory();
        presenter_three_music_url.onDestory();
        // 息屏
        LightOnOffUtils.getInstance(getApplicationContext()).set_off_light(this);
        // 暂停第三方音乐bufen
        LocalBroadcastManagers.getInstance(getApplicationContext()).unregisterReceiver(mMessageReceiver);
        set_cancel_leakcanary();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        String aaa = "";
        isonstop = true;
        super.onStop();

    }

    private boolean isonstop;

    private int current_url_zhifu = 0;

    @Override
    protected void onResume() {
        if (SPUtils.getInstance().getInt(CommonUtils.HUIBEN_PAYSUCCESS, -1) == CommonUtils.HUIBEN_PAYSUCCESS_TAG1) {
            // VIP跳出层 支付成功
            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, -1);
            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS, -1);
            current_url_zhifu = current_url + 1;
            //
            if (mBinder != null && mBinder.getService() != null) {
                mBinder.getService().setOnPlayFinishCallBack(null);
                unbindService(conn);
                mBinder = null;
                flag = false;
            }
            setDatas();

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
            current_url_zhifu = current_url + 1;
            //
            if (mBinder != null && mBinder.getService() != null) {
                mBinder.getService().setOnPlayFinishCallBack(null);
                unbindService(conn);
                mBinder = null;
                flag = false;
            }
            setDatas();

        }
        if (!tv_startOrpause) {
            if (isonstop) {
                EXTRA_ID = SPUtils.getInstance().getString("float_id");
                isonstop = false;
            }
            setDatas();
        }
        super.onResume();

    }

    // 取消状态 保证不会内存泄漏bufen
    private void set_cancel_leakcanary() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
        if (flag) {
            mBinder.getService().setOnPlayFinishCallBack(null);
            if (tv_startOrpause) {
                mBinder.musicDestroy();
            }
            unbindService(conn);
            mBinder = null;
            flag = false;
        }
        if (listenBookObservable3 != null) {
            listenBookObservable3.deleteObserver(this);
        }
    }

    @Override
    protected void onPause() {
//        is_first_in2 = false;
        if (flag) {
            if (mBinder != null && mBinder.isService()) {
                if (!tv_startOrpause) {
                    is_first_in2 = false;
                    SPUtils.getInstance().put(CommonUtils.LISTENBOOK_TAG1, true);
                    mBinder.getService().setSeekbar_progress(seekbar_progress);
                    //
                    mBinder.getService().setOnPlayFinishCallBack(null);
                    unbindService(conn);
                    mBinder = null;
                    flag = false;
                } else {
                    is_first_in2 = true;
                }
            }
            SPUtils.getInstance().put(CommonUtils.LISTENBOOK_TAG21, EXTRA_ID_ZONG);
        }
        super.onPause();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        NavigationBarUtil.fullScreenImmersive(getWindow().getDecorView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_listenmusic;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        //虚拟键
        if (NavigationBarUtil.hasNavigationBar(this)) {
//            NavigationBarUtil.initActivity(getWindow().getDecorView());
            NavigationBarUtil.hideBottomUIMenu(this);
        }
        // 亮屏
        LightOnOffUtils.getInstance(getApplicationContext()).set_on_light(this);
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        findview();
        onclick();
        setDatas2();
        donetwork();
        // 首页切换到我的
        mMessageReceiver = new MessageMusicReceiverIndex();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(CommonUtils.LB_broadcastreceiver);
        LocalBroadcastManagers.getInstance(getApplicationContext()).registerReceiver(mMessageReceiver, filter);
        // 统计页面开始时间
//        Intent it = new Intent(this, UpdataCommonservices.class);
//        it.setAction(UpdataCommonservices.HUIBEN_READINGTIME_ACTION);
//        it.putExtra(UpdataCommonservices.id_zong, EXTRA_ID_ZONG);
//        it.putExtra(UpdataCommonservices.id, EXTRA_ID);
//        it.putExtra(UpdataCommonservices.type, UpdataCommonservices.type1);
//        it.putExtra(UpdataCommonservices.sourceType, UpdataCommonservices.sourceType1);
//        it.putExtra(UpdataCommonservices.time, System.currentTimeMillis() + "");
//        startService(it);
        // 友盟统计bufen
        MobclickAgent.onEvent(this, "ListenMusicActivity");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", EXTRA_NAME);
//        map.put("quantity","3");
        MobclickAgent.onEvent(this, "ListenMusicActivity", map);
    }

    private void setDatas2() {
        //
        EXTRA_XMLY = new ArrayList<>();
        EXTRA_XMLY = getIntent().getStringArrayListExtra(CommonUtils.HUIBEN_XMLY);
        EXTRA_ID_ZONG = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS_ZONG);
        EXTRA_ID = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS);
        EXTRA_NAME = getIntent().getStringExtra(CommonUtils.HUIBEN_TITLES);
        // 播放听书 下次进来的时候与上次的比较 如果相同就继续 如果不同就销毁service然后重启播放新的
        if (!TextUtils.equals(EXTRA_ID_ZONG, SPUtils.getInstance().getString(CommonUtils.LISTENBOOK_TAG21))) {
            SPUtils.getInstance().put(CommonUtils.LISTENBOOK_TAG1, false);
            stopService(new Intent(BaseApp.get(), ListenMusicPlayerService.class));
        }
        Intent intent = new Intent(BaseApp.get(), ListenMusicPlayerService.class);
        intent.setAction(ListenMusicPlayerService.LMP_ACT);
        intent.putExtra(CommonUtils.HUIBEN_IDS_ZONG, EXTRA_ID_ZONG);
        intent.putExtra(CommonUtils.HUIBEN_IDS, EXTRA_ID);
        startService(intent);
    }

    private void donetwork() {
        mEmptyView.loading();
        seekbar_progress = 0;
        tv_startOrpause = false;
        tv_startpause.setBackgroundResource(R.drawable.slb_lb_pause);
        set_enable_view(false);
        presenter1 = new SLB1SaveFavoritesPresenter();
        presenter1.onCreate(this);
        presenter = new SLB4CategoryListDetailPresenter();
        presenter.onCreate(this);
        presenter2 = new SISSaveFavoritesPresenter();
        presenter2.onCreate(this);
        presenter3 = new SLB3CategoryListPresenter();
        presenter3.onCreate(this);
        presenter_three_music_url = new SLBThreeMusicUrlPresenter();
        presenter_three_music_url.onCreate(this);

        //
//        setDatas();
    }

    private void setDatas() {
        presenter.getLB4CategoryListDetailData(EXTRA_ID_ZONG, EXTRA_ID);

    }

    private void onclick() {
        tv_home.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                onBackPressed();
            }
        });
        tv_sc1.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (!NetworkUtils.isConnected()) {
                    Toasty.normal(getApplicationContext(), "网络异常，请检查网络连接！").show();
                    return;
                }
                SlbLoginUtil2.get().loginTowhere(ListenMusicActivity.this, new Runnable() {
                    @Override
                    public void run() {
                        if (!is_sc) {
                            is_sc = true;
                            tv_sc1.setBackgroundResource(R.drawable.play_collect33);
                        } else {
                            is_sc = false;
                            tv_sc1.setBackgroundResource(R.drawable.play_collect3);
                        }
                        if (mList != null && mList.get(current_url) != null) {
                            presenter1.getLB1SaveFavoritesData(mList.get(current_url).getItemId(),
                                    mList.get(current_url).getpId(), !is_sc, mList.get(current_url).getSourceType());
                        }
                    }
                });

            }
        });
        tv_playmode.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (!NetworkUtils.isConnected()) {
                    Toasty.normal(getApplicationContext(), "网络异常，请检查网络连接！").show();
                    return;
                }
                // 播放模式
                if (!playmode) {
                    playmode = true;
//                    is_scroll = false;
                    tv_playmode.setBackgroundResource(R.drawable.slb_lb_refresh2);
                    mBinder.getService().setPlay_mode(1);
                } else {
                    playmode = false;
//                    is_scroll = true;
                    tv_playmode.setBackgroundResource(R.drawable.slb_lb_refresh1);
                    mBinder.getService().setPlay_mode(0);
                }

            }
        });
        tv_pre.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                // 上一首
                current_url--;
                if (current_url < 0) {
                    current_url = 0;
                    Toasty.normal(getApplicationContext(), "已经是第一").show();
                    return;
                }
                //
                if (viewpager.getCurrentItem() == 0) {
                    Toasty.normal(getApplicationContext(), "已经是第一").show();
                    return;
                }
                ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_STOP);
                if (viewpager.getmCurrentItem() != null) {
                    ListenMusicUtil.getInstance().stopMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                }
                if (NetworkUtils.isConnected()) {
                    if (SPUtils.getInstance().getBoolean("NetChange", false)) {
                        SPUtils.getInstance().put("NetChange", false);
                    }
                }
                if (mBinder != null && mBinder.getService() != null) {
                    mBinder.getService().setIs_auto(true);
                }
                MyLogUtil.e("--nextplay-tv_pre-", current_url + "");
                viewpager.setCurrentItem(current_url);

//                set_smoothscroll();
            }
        });
        tv_startpause.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                // play pause stop
                // 1.图片动画
//                Image.playMusic();
                if (!NetworkUtils.isConnected()) {
                    Toasty.normal(getApplicationContext(), "网络异常，请检查网络连接！").show();
                    return;
                }
                // 2.音乐播放
                if (mBinder != null && mBinder.getService() != null && !tv_startOrpause) {
                    // 暂停
                    tv_startOrpause = true;
//                    is_scroll = false;
                    tv_startpause.setBackgroundResource(R.drawable.slb_lb_start);
//                    ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_PLAYING);
//                    ListenMusicUtil.getInstance().playMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                    ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_PLAYING);
                    if (viewpager.getmCurrentItem() != null) {
                        ListenMusicUtil.getInstance().playMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                    }
//                    viewpager.playMusic();
                    mBinder.musicPause();
                } else {
                    // 开始
                    tv_startOrpause = false;
//                    is_scroll = true;
                    tv_startpause.setBackgroundResource(R.drawable.slb_lb_pause);
                    if ((current_url == mList.size() - 1) && isfinish1) {
                        // 当在最后一个的时候 如果还拖动进度条 就继续播放当前 如果不拖动就返回第一个播放
//                        if (mBinder.getService().getPlay_mode() == 0) {
////                            current_url = 0;
//                        } else if (mBinder.getService().getPlay_mode() == 1) {
////                            current_url = current_url;
//                            ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_PAUSE);
//                            if (viewpager.getmCurrentItem() != null) {
//                                ListenMusicUtil.getInstance().playMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
//                            }
//                        }
                        play_next();
                    } else {
                        set_ani_yuanyuan2();
//                        mBinder.musicContinue();
                        if (mBinder != null && mBinder.getService() != null) {
                            if (SPUtils.getInstance().getBoolean("NetChange", false)) {
                                if (TextUtils.isEmpty(mBinder.getService().getMusicList().get(current_url).getAudioUrl())) {
                                    //
                                    presenter_three_music_url.getLBThreeMusicUrlData(
                                            mBinder.getService().getMusicList().get(current_url).getThirdItemId(),
                                            mBinder.getService().getMusicList().get(current_url).getThirdPid(),
                                            mBinder.getService().getMusicList().get(current_url).getSource());
                                } else {
//                                mBinder.musicNext(mList.get(current_url).getAudioUrl());
                                    mBinder.musicNext(mBinder.getService().getMusicList().get(current_url).getAudioUrl());
                                }
                                SPUtils.getInstance().put("NetChange", false);
                            } else {
                                mBinder.musicContinue();
                            }
                        }
                    }
                }
                mAdapter3.setOnPause(tv_startOrpause);
                mAdapter3.notifyDataSetChanged();
            }
        });
        tv_next.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                // 下一首
                current_url++;
                if (current_url >= mList.size()) {
                    if (is_payfor) {
                        set_nextitem_playmusic();
                        current_url = mList.size() - 1;
                    } else {
                        current_url = 0;
                        play_next();
                    }
                    return;
                }
                //
                if (viewpager.getCurrentItem() == adapter.getCount() - 1) {
                    Toasty.normal(getApplicationContext(), "已经是最后").show();
                    return;
                }
                ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_STOP);
                if (viewpager.getmCurrentItem() != null) {
                    ListenMusicUtil.getInstance().stopMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                }
                if (NetworkUtils.isConnected()) {
                    if (SPUtils.getInstance().getBoolean("NetChange", false)) {
                        SPUtils.getInstance().put("NetChange", false);
                    }
                }
                if (mBinder != null && mBinder.getService() != null) {
                    mBinder.getService().setIs_auto(true);
                }
                MyLogUtil.e("--nextplay-tv_next-", current_url + "");
                viewpager.setCurrentItem(current_url);

//                set_smoothscroll();
            }
        });
        MusicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MyLogUtil.e("-seekbar-onProgressChanged--", "");
//                seekBar.setTag(progress);
                fromUser_seekbar = fromUser;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                MyLogUtil.e("-seekbar-onStartTrackingTouch--", "");
//                ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_PLAYING);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                MyLogUtil.e("-seekbar-onStopTrackingTouch--", "");
                seekbar_progress = seekBar.getProgress();
                if (fromUser_seekbar) {
                    fromUser_seekbar = false;
                    isfinish1 = false;
                    play_next_ui();
                    //
                    if (viewpager.getmCurrentItem() != null) {
                        if (ListenMusicUtil.getInstance().getState_ani() == ListenMusicUtil.getInstance().STATE_STOP) {
                            ListenMusicUtil.getInstance().initMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                        } else {
                            ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_PAUSE);
                        }
                        ListenMusicUtil.getInstance().playMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                    }
                    if (mBinder != null && mBinder.getService() != null) {
                        mBinder.getService().setCurrent(current_url);
                        mBinder.musicSeekTo(seekBar.getProgress());
                    }
                }
            }
        });
        adapter.setImageView(new ListenBookCoolViewAdapter.OnWhichPagerListener() {
            @Override
            public void OnResult(ImageView view, int pos) {
                //
                iv = view;
//                current_url = pos;
//                ListenMusicUtil.getInstance().initMusic(view);
            }
        });
//        mAdapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                SLB4CategoryListDetailBean1 bean = (SLB4CategoryListDetailBean1) mAdapter3.getItem(position);
//                int i = view.getId();
//                if (TextUtils.equals(bean.getReadRight(), "2") || TextUtils.equals(bean.getReadRight(), "4")) {
//                    get_shidu_pop2(bean.getpId(), bean.getItemId(), bean.getSourceType());
//                } else if (TextUtils.equals(bean.getReadRight(), "3")) {
//                    get_shidu_pop3(bean.getpId(), bean.getItemId(), bean.getSourceType());
//                } else {
//                    if (TextUtils.equals(bean.getItemId(), mList.get(current_url).getItemId())) {
//                        return;
//                    }
//                    mAdapter3.setEX_ID(bean.getItemId());
//                    mAdapter3.notifyDataSetChanged();
//                    //
//                    for (int i1 = 0; i1 < mList.size(); i1++) {
//                        if (TextUtils.equals(bean.getItemId(), mList.get(i1).getItemId())) {
//                            current_url = i1;
//                        }
//                    }
//                    ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_STOP);
//                    ListenMusicUtil.getInstance().stopMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
//                    viewpager.setCurrentItem(current_url);
//                }
//            }
//        });
//        mAdapter3.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//
//            }
//        });
        viewpager.addOnPageChangeListener(new LxCoolViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                MyLogUtil.e("----第几个pos--onPageScrolled--", i + " " + is_scroll);
                MyLogUtil.e("----第几个pos--onPageScrolled--", i + " ");
//                ListenMusicUtil.getInstance().stopMusic(adapter.getPrimaryItem());
//                if (is_scroll) {
//                    is_scroll = false;
//                    return;
//                }
//                if (viewpager.getmCurrentItem() != null && viewpager.getmCurrentItem().getObject() != null) {
//                    ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_STOP);
//                    ListenMusicUtil.getInstance().stopMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
//                }
            }

            @Override
            public void onPageSelected(int i) {
//                MyLogUtil.e("----第几个pos--onPageSelected--", i + " " + is_scroll);
                MyLogUtil.e("----第几个pos--onPageSelected--", i + " ");
                current_url = i;
                // 播放下一首bufen
                if (!SPUtils.getInstance().getBoolean(CommonUtils.LISTENBOOK_TAG1, false)) {
//                    if (viewpager.getmCurrentItem() != null && !is_scroll) {
//                        ListenMusicUtil.getInstance().initMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
//                        ListenMusicUtil.getInstance().playMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
//                    }
                    if (!is_first_in2) {// 如果从1进入 这时创建了2个 所以不能拦截
                        is_first_in2 = true;
                        return;
                    }
                    play_next();
                }
//                09-21 13:54:44.969 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged点击、滑屏
//                09-21 13:54:47.321 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged释放
//                09-21 13:54:48.326 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged无动作
//                09-21 13:54:51.440 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged点击、滑屏
//                09-21 13:54:51.600 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged释放
//                09-21 13:54:51.600 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageSelected
//                09-21 13:54:51.855 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged无动作
//                09-21 13:54:51.855 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->instantiateItem
//                09-21 13:54:51.856 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->destroyItem

            }

            @Override
            public void onPageScrollStateChanged(int i) {
//                MyLogUtil.e("----第几个pos--onPageScrollStateChanged--", i + " " + is_scroll);
                MyLogUtil.e("----第几个pos--onPageScrollStateChanged--", i + " ");

                switch (i) {
                    case LxCoolViewPager.SCROLL_STATE_IDLE:
                        //无动作、初始状态
                        Log.i("--onPageChanged--", "---->onPageScrollStateChanged无动作");
//                        is_scroll = true;
                        set_ani_yuanyuan();
                        break;
                    case LxCoolViewPager.SCROLL_STATE_DRAGGING:
                        //点击、滑屏
                        Log.i("--onPageChanged--", "---->onPageScrollStateChanged点击、滑屏");
//                        viewpager.stopMusic();
                        ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_STOP);
                        ListenMusicUtil.getInstance().stopMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                        break;
                    case LxCoolViewPager.SCROLL_STATE_SETTLING:
                        //释放
                        Log.i("--onPageChanged--", "---->onPageScrollStateChanged释放");
//                        viewpager.stopMusic();
                        break;
                }
            }
        });
    }

    private final int MIN_CLICK_DELAY_TIME = 1500;
    private long lastClickTime;

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                recy_current = position;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            lastClickTime = curClickTime;
            SLB4CategoryListDetailBean1 bean = mAdapter3.getItem(position);
            int i = view.getId();
            if (TextUtils.equals(bean.getReadRight(), "2") || TextUtils.equals(bean.getReadRight(), "4")) {
                get_shidu_pop2(bean.getpId(), bean.getItemId(), bean.getSourceType());
                set_stopmusic_click();
            } else if (TextUtils.equals(bean.getReadRight(), "3")) {
                get_shidu_pop3(bean.getpId(), bean.getItemId(), bean.getSourceType());
                set_stopmusic_click();
            } else {
                if (TextUtils.equals(bean.getItemId(), mList.get(current_url).getItemId())) {
                    return;
                }
                // 从播放圆圆列表拿到当前的pos
                for (int i1 = 0; i1 < mList.size(); i1++) {
                    if (TextUtils.equals(bean.getItemId(), mList.get(i1).getItemId())) {
                        current_url = i1;
                    }
                }
//                        ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_STOP);
//                        ListenMusicUtil.getInstance().stopMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                viewpager.setCurrentItem(current_url);
                //
//                        set_smoothscroll();
            }
        }
    }

    // 爱的魔力转圈圈1
    private void set_ani_yuanyuan() {
        if (viewpager.getmCurrentItem() != null) {
            if (SPUtils.getInstance().getBoolean("NetChange", false)) {
                if (viewpager.getmCurrentItem() != null && viewpager.getmCurrentItem().getObject() != null) {
                    ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_STOP);
                    ListenMusicUtil.getInstance().stopMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                }
            } else {
                ListenMusicUtil.getInstance().initMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                ListenMusicUtil.getInstance().playMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
            }
        }
    }

    // 爱的魔力转圈圈2
    private void set_ani_yuanyuan2() {
        if (viewpager.getmCurrentItem() != null) {
            if (ListenMusicUtil.getInstance().getState_ani() == ListenMusicUtil.getInstance().STATE_STOP) {
                ListenMusicUtil.getInstance().initMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
            } else {
                ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_PAUSE);
            }
            ListenMusicUtil.getInstance().playMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
        }
    }

    private ImageView iv;
    private PopForShare popSharePay;

    @Override
    public void onClick(View view) {
        int i = view.getId();
//        if (i == R.id.tv_startpause) {
//
//        }
//        else if (i == R.id.tv_startpause) {
//
//        } else if (i == R.id.tv_next) {
//
//        } else {
//
//        }
    }

    private void findview() {
        rl1 = findViewById(R.id.rl11);
        mEmptyView = findViewById(R.id.empty_view);
        mEmptyView.bind(rl1).setRetryListener(new EmptyView.RetryListener() {
            @Override
            public void retry() {
                // 重试bufen
                setDatas();
            }
        });
        Image = findViewById(R.id.Image);
        tv_share = findViewById(R.id.tvfx9);
        tv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                popSharePay = new PopForShare(ListenMusicActivity.this,
                        share_title, share_name, share_img, share_imgUrl, share_imgUrl2, new PopForShare.OnFinishResultClickListener() {
                    @Override
                    public void onWeChat1() {
                        // 微信
//                        popSharePay.set_wechat1(share_title, share_name, share_img, share_imgUrl, share_imgUrl2);
                    }

                    @Override
                    public void onWeChat2() {
                        // 朋友圈

                    }
                });
            }
        });
        tv33 = findViewById(R.id.tv33);
        MusicStatus = findViewById(R.id.MusicStatus);
        MusicTime = findViewById(R.id.MusicTime);
        MusicSeekBar = findViewById(R.id.MusicSeekBar);
        MusicTotal = findViewById(R.id.MusicTotal);
        ll22 = findViewById(R.id.ll22);
        tv_playmode = findViewById(R.id.tv_playmode);
        tv_pre = findViewById(R.id.tv_pre);
        tv_startpause = findViewById(R.id.tv_startpause);
        tv_next = findViewById(R.id.tv_next);
        tv_home = findViewById(R.id.tv_home);
        tv_sc1 = findViewById(R.id.tv_sc1);
        viewpager = findViewById(R.id.vp);

        simpleDateFormat = new SimpleDateFormat("mm:ss", Locale.getDefault());
        // new
        viewpager.setScrollMode(LxCoolViewPager.ScrollMode.HORIZONTAL);
        viewpager.setAutoScroll(false);
        viewpager.setAutoScrollDirection(LxCoolViewPager.AutoScrollDirection.FORWARD);
        viewpager.setInfiniteLoop(false);
        viewpager.setScrollDuration(true, 1000);
        viewpager.setDrawEdgeEffect(false);
        viewpager.setEdgeEffectColor(getResources().getColor(R.color.colorPrimary));
        mList = new ArrayList<>();
        adapter = new ListenBookCoolViewAdapter(ListenMusicActivity.this, mList);
//        viewpager.setAdapter(adapter);
        //
        recyclerview3 = findViewById(R.id.recyclerview3);
//        recyclerview3.setLayoutManager(new GridLayoutManager(this, 1, OrientationHelper.HORIZONTAL, false));
        recyclerview3.setLayoutManager(new AdvertiseLinearLayoutManager(this, OrientationHelper.HORIZONTAL, false));
//        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
        JackSnapHelper mLinearSnapHelper = new JackSnapHelper(JackSnapHelper.TYPE_SNAP_START);
        mLinearSnapHelper.attachToRecyclerView(recyclerview3);
        mAdapter3 = new ListenBooksListAdapterList1(R.layout.activity_listenmusic_item2);
        recyclerview3.setAdapter(mAdapter3);
    }

    private boolean is_first_in;
    private boolean is_first_in2;

    private void play_next() {

        // 2.音乐播放
        if (mBinder != null && mBinder.getService() != null) {
//            if (!is_first_in) {
//                is_first_in = true;
//                mBinder.getService().setCurrent(current_url);
//                mBinder.musicStart(mList.get(current_url).getAudioUrl());
//                return;
//            }
            mBinder.getService().setCurrent(current_url);
//            mBinder.musicNext("http://openod.sign.qingting.fm/m4a/5b923f2f7cb891034006a8ec_10745593_24.m4a?sign=ec5876e13b83d3fea3a0f864d9ba864a&t=5c8c2083");
            if (TextUtils.isEmpty(mList.get(current_url).getAudioUrl())) {
                //
                presenter_three_music_url.getLBThreeMusicUrlData(mList.get(current_url).getThirdItemId(), mList.get(current_url).getThirdPid(), mList.get(current_url).getSource());
            } else {
                mBinder.musicNext(mList.get(current_url).getAudioUrl());
            }
            //
            play_next_ui();
        }
    }

    private void play_next2() {
        if (mBinder != null && mBinder.getService() != null) {
//            mBinder.getService().setCurrent(current_url);
            if (TextUtils.isEmpty(mList.get(current_url).getAudioUrl())) {
                presenter_three_music_url.getLBThreeMusicUrlData(mList.get(current_url).getThirdItemId(), mList.get(current_url).getThirdPid(), mList.get(current_url).getSource());
            } else {
                mBinder.musicNext(mList.get(current_url).getAudioUrl());
            }
        }
    }

    private void play_next_ui() {
        //
//        if (Util.isOnMainThread()) {
//            // 1.图片动画
//            Image.playNextMusic();
////        tv33.setText(adapter.getContacts(current_url).getItemName());
//            RequestOptions options = new RequestOptions()
//                    .placeholder(R.drawable.ic_def_loading)
//                    .error(R.drawable.ic_def_loading)
//                    .fallback(R.drawable.ic_def_loading); //url为空的时候,显示的图片;
//            Glide.with(this).load(mList.get(current_url).getCoverImg()).apply(options).into(Image);
//        }
        //
        tv_startOrpause = false;
        tv_startpause.setBackgroundResource(R.drawable.slb_lb_pause);
        // 请求收藏接口bufen
        if (!is_first_in) {
            is_first_in = true;
        } else {
            if (mList != null && mList.get(current_url) != null) {
//                ShowLoadingUtil.showLoading(ListenMusicActivity.this, "", 1500, null);
                EXTRA_ID_ZONG = mList.get(current_url).getpId();
                EXTRA_ID = mList.get(current_url).getItemId();
                EXTRA_NAME = mList.get(current_url).getTitle();
                presenter2.getISSaveFavoritesData(mList.get(current_url).getpId(), mList.get(current_url).getItemId(), "audioItem");
                //
                set_nextitem_playmusic();
            }
        }
        // 2.列表动画
//        if (!is_fis) {
//            is_fis = true;
//            return;
//        }
        MyLogUtil.e("geek111111111111", "ccccccccccc");
        set_enable_music_zhuangtai();
    }

    private void set_enable_music_zhuangtai() {
        set_enable_view(false);
        viewpager.setCurrentItem(current_url);
        // 音乐下一首的不可用seekbar状态
        viewpager.postDelayed(new Runnable() {
            @Override
            public void run() {
                set_enable_view(true);
            }
        }, 1500);
    }

    private void set_enable_view(boolean is_en) {
        MusicSeekBar.setEnabled(is_en);
        tv_home.setEnabled(is_en);
        tv_startpause.setEnabled(is_en);
        tv_pre.setEnabled(is_en);
        tv_next.setEnabled(is_en);
        if (is_en) {
//            ll22.setVisibility(View.VISIBLE);
            mAdapter3.setOnItemChildClickListener(this);
        } else {
//            ll22.setVisibility(View.GONE);
            mAdapter3.setOnItemChildClickListener(null);
        }
    }

    @Override
    public void OnLBThreeMusicUrlSuccess(SLBThreeMusicUrlBean slbThreeMusicUrlBean) {
//        mList.get(current_url).setAudioUrl(slbThreeMusicUrlBean.getThirdUrl());
        mBinder.musicNext(slbThreeMusicUrlBean.getThirdUrl());
    }

    @Override
    public void OnLBThreeMusicUrlNodata(String s) {
        mBinder.musicNext("https://www.baidu.com/");
    }

    @Override
    public void OnLBThreeMusicUrlFail(String s) {
        mBinder.musicNext("https://www.baidu.com/");
    }

    /**
     * ---------------------刷新进度条-------------------------
     */
    private SimpleDateFormat simpleDateFormat;
    private ScheduledExecutorService scheduledExecutorService;

    private void set_update_progress_ui() {
        if (mHandler != null) {
            return;
        }
        if (scheduledExecutorService != null) {
            return;
        }
        mHandler = new WeakRefHandler(mCallback);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (mBinder != null && mBinder.getService() != null && mBinder.getService().is_playing() && !fromUser_seekbar) {
                    // 将SeekBar位置设置到当前播放位置
                    Message msg1 = Message.obtain();
                    msg1.what = 10;
                    if (mBinder.getService().getmPlayer() != null) {
                        msg1.obj = mBinder.getService().getmPlayer().getCurrentPosition();
                    } else {
                        msg1.obj = 0;
                    }
                    mHandler.sendMessage(msg1);
                }
            }
        }, 0, 80, TimeUnit.MILLISECONDS);

    }

    private Handler mHandler;
    private Handler.Callback mCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 10 && msg.obj != null) {
                final int time = (Integer) msg.obj;
//                MyLogUtil.e("---geektime---", time + "");
                if (mBinder != null && mBinder.getService() != null && mBinder.getService().getmPlayer() != null) {
                    MusicSeekBar.setProgress(time);
                    MusicSeekBar.setMax(mBinder.getService().getmPlayer().getDuration());
                    MusicTime.setText(simpleDateFormat.format(time));
                    MusicTotal.setText(simpleDateFormat.format(mBinder.getService().getmPlayer().getDuration()));
                }
            }
            return true;
        }
    };

    /**
     * ---------------------刷新进度条-------------------------
     */

    // 收藏bufen
    @Override
    public void OnLB1SaveFavoritesSuccess(SLB1SaveFavoritesBean s) {
        Toasty.normal(this, s.getMsg()).show();
    }

    @Override
    public void OnLB1SaveFavoritesNodata(SLB1SaveFavoritesBean s) {
        Toasty.normal(this, s.getMsg()).show();
    }

    @Override
    public void OnLB1SaveFavoritesFail(String s) {
        Toasty.normal(this, s).show();
    }
    // 收藏bufen

    private List<SListenBookDetailBean2> getList() {
        List<SListenBookDetailBean2> list1 = new ArrayList<>();
        list1.add(new SListenBookDetailBean2("1", "1", "1", "1", "1", "https://sairobo-edu-elephant-test.oss-cn-hangzhou.aliyuncs.com/20190107/e24cb8239f3940c383d74b4ed6c7e410.jpg", "", "mp3/yww.mp3", false));
        list1.add(new SListenBookDetailBean2("2", "2", "2", "2", "2", "https://sairobo-edu-elephant-test.oss-cn-hangzhou.aliyuncs.com/20190107/e24cb8239f3940c383d74b4ed6c7e410.jpg", "", "http://sairobo-edu-elephant-test.oss-cn-hangzhou.aliyuncs.com/apk/20190107/20190107173026_mm5w.mp3", false));
        return list1;
    }

    private String share_title = "";
    private String share_name = "";
    private String share_img = "";
    private String share_imgUrl = "";
    private String share_imgUrl2 = "";

    // 获取听书列表bufen
    @Override
    public void OnLB4CategoryListDetailSuccess(SLB4CategoryListDetailBean sHuibenDetailBean) {
        mEmptyView.success();
        if (sHuibenDetailBean.getShareInfo() != null) {
            share_title = sHuibenDetailBean.getShareInfo().getTitle();
//            share_title = EXTRA_NAME;
            share_name = sHuibenDetailBean.getShareInfo().getDesc();
//            share_name = EXTRA_NAME;
            share_img = sHuibenDetailBean.getShareInfo().getImgUrl();
            share_imgUrl = sHuibenDetailBean.getShareInfo().getUrlForFriend();
            share_imgUrl2 = sHuibenDetailBean.getShareInfo().getUrlForMoments();
        }
        if (sHuibenDetailBean.getAudioView().isCollect()) {
            is_sc = true;
            tv_sc1.setBackgroundResource(R.drawable.play_collect33);
        } else {
            is_sc = false;
            tv_sc1.setBackgroundResource(R.drawable.play_collect3);
        }
//        mList = sHuibenDetailBean.getList();// 如果喜马拉雅 就要传入list重组
//        if (EXTRA_XMLY != null && EXTRA_XMLY.size() > 0) {
//            for (int i = 0; i < mList.size(); i++) {
//                SLB4CategoryListDetailBean1 bean = mList.get(i);
//                bean.setAudioUrl(EXTRA_XMLY.get(i));
//            }
//        }
////        mList = getList();
//        adapter.setData(mList);
//        viewpager.setAdapter(adapter);
//        // 绑定音乐
//        if (!flag) {
//            Intent intent = new Intent(this, ListenMusicPlayerService.class);
//            intent.putExtra("flag", flag);
//            bindService(intent, conn, BIND_AUTO_CREATE);
//        }
        presenter3.getLB3CategoryListData(1, PAGE_SIZE, EXTRA_ID_ZONG, EXTRA_ID);

    }

    @Override
    public void OnLB4CategoryListDetailNodata(String s) {
        mEmptyView.errorNet();
    }

    @Override
    public void OnLB4CategoryListDetailFail(String s) {
        mEmptyView.errorNet();
    }

    private ListenBookObservable listenBookObservable3;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (ListenMusicPlayerService.MyBinder) service;
            listenBookObservable3 = ((ListenMusicPlayerService.MyBinder) service).getMyObservable();
            listenBookObservable3.addObserver(ListenMusicActivity.this);
            aaa = mBinder.getService().getEX_ID();
//            presenter3.getLB3CategoryListData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), 1, PAGE_SIZE, EXTRA_ID_ZONG, EXTRA_ID);
            //
            flag = true;
            tv_startOrpause = false;
            tv_startpause.setBackgroundResource(R.drawable.slb_lb_pause);
            if (mBinder.getService().getPlay_mode() == 1) {
                playmode = true;
//                is_scroll = false;
                tv_playmode.setBackgroundResource(R.drawable.slb_lb_refresh2);
                mBinder.getService().setPlay_mode(1);
            } else if (mBinder.getService().getPlay_mode() == 0) {
                playmode = false;
//                is_scroll = true;
                tv_playmode.setBackgroundResource(R.drawable.slb_lb_refresh1);
                mBinder.getService().setPlay_mode(0);
            }
            mBinder.getService().setIs_payfor(is_payfor);
            ArrayList<SLB4CategoryListDetailBean1Temp> list12 = new ArrayList<>();
            ArrayList<String> list12ID = new ArrayList<>();
            for (SLB4CategoryListDetailBean1 bean : mList) {
                list12.add(new SLB4CategoryListDetailBean1Temp(bean.getAudioUrl(), bean.getTitle(), bean.getItemId(), bean.getpId(), bean.getSource(), bean.getThirdItemId(), bean.getThirdPid()));
                list12ID.add(bean.getItemId());
            }
            mBinder.getService().setMusicList(list12);
            mBinder.getService().setMusicListID(list12ID);
            //
//            mBinder.getService().setOnPrepared(new ListenMusicPlayerService.DemoPreparedCallBack() {
//                @Override
//                public void isPrepared(boolean prepared) {
//                    if (prepared) {
//                        MusicTotal.setText(mBinder.getService().get_maxtime());
//                        MusicSeekBar.setMax(mBinder.getService().getmPlayer().getDuration());
//                    }
//                }
//            });
            mBinder.getService().setOnPlayFinishCallBack(new ListenMusicPlayerService.PlayFinishCallBack() {
                @Override
                public void isFinish(boolean isfinish, int path) {
                    isfinish1 = isfinish;
                    if (isfinish) {
                        // 列表播放完成bufen
                        tv_startpause.post(new Runnable() {
                            @Override
                            public void run() {
//                                Image.stopMusic();
                                tv_startOrpause = true;
                                tv_startpause.setBackgroundResource(R.drawable.slb_lb_start);
                                //
                                current_url++;
                                if (current_url >= mList.size()) {
                                    if (is_payfor) {
                                        set_nextitem_playmusic();
                                        current_url = mList.size() - 1;
                                        mAdapter3.setOnPause(tv_startOrpause);
                                        mAdapter3.notifyDataSetChanged();
                                    } else {
                                        current_url = 0;
                                        play_next();
                                    }
                                }
                            }
                        });
                        // 列表动画完成
                        ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_STOP);
                        ListenMusicUtil.getInstance().stopMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
//                        viewpager.stopMusic();
                        MyLogUtil.e("---geee---", "播放完成");

                    } else {
                        current_url = path;
                        play_next_ui();
//                        play_next();
                        MyLogUtil.e("---geee---", "播放继续" + current_url);
                    }
                }
            });

            // 如果在后台就使用滚动去触发播放器
            if (/*mBinder.getService().is_playing() && */SPUtils.getInstance().getBoolean(CommonUtils.LISTENBOOK_TAG1, false)) {
                // 后台一直在播放 播到哪里就是哪里
                SPUtils.getInstance().put(CommonUtils.LISTENBOOK_TAG1, false);
//                if (TextUtils.equals(EXTRA_ID, SPUtils.getInstance().getString(CommonUtils.LISTENBOOK_TAG22))) {
                if (TextUtils.equals(EXTRA_ID, mBinder.getService().getEX_ID())) {
                    // 如果点击A进入的 之后继续点A进入 就保持A的后台播放状态继续播放
                    viewpager.setCurrentItem(mBinder.getService().getCurrent());
                    if (mBinder.getService().getmPlayer() != null) {
                        MusicSeekBar.setProgress(mBinder.getService().getmPlayer().getCurrentPosition());
                        MusicSeekBar.setMax(mBinder.getService().getmPlayer().getDuration());
                    } else {
                        MusicSeekBar.setProgress(0);
                        MusicSeekBar.setMax(0);
                    }
                    MusicTotal.setText(mBinder.getService().get_maxtime());
                    is_first_in = true;
                } else {
                    // 如果点A进入的 之后继续点B进入 就废弃A的后台播放状态 直接跳转B播放
                    for (int i = 0; i < mList.size(); i++) {
                        if (TextUtils.equals(EXTRA_ID, mList.get(i).getItemId())) {
                            current_url = i;
                        }
                    }
                    play_next();
                }

            } else {
                // 如果一开始没有后台 就进入哪个播哪个
                for (int i = 0; i < mList.size(); i++) {
                    if (TextUtils.equals(EXTRA_ID, mList.get(i).getItemId())) {
                        current_url = i;
                    }
                }
                if (current_url_zhifu > 0) {
                    current_url = current_url_zhifu;

                }
                play_next();

            }
            // 给service播放列表bufen
            if (mBinder != null && mBinder.getService() != null) {

                // 刷新进度条
                set_update_progress_ui();
                // 动画滚动bufen
                if (current_url == 0) {
                    is_first_in2 = true;
                    ListenMusicUtil.getInstance().initMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                    ListenMusicUtil.getInstance().playMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                }
            }
            // 获取播放状态显示gif
            set_smoothscroll();
            viewpager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    set_enable_view(true);
                }
            },1500);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            flag = false;
        }
    };

    // 是否收藏bufen
    @Override
    public void OnISSaveFavoritesSuccess(SISSCBean sisscBean) {
//        ShowLoadingUtil.onDestory();
        if (sisscBean.isCollect()) {
            is_sc = true;
            tv_sc1.setBackgroundResource(R.drawable.play_collect33);
        } else {
            is_sc = false;
            tv_sc1.setBackgroundResource(R.drawable.play_collect3);
        }
        if (sisscBean.getShareInfo() != null) {
            share_title = sisscBean.getShareInfo().getTitle();
//            share_title = EXTRA_NAME;
            share_name = sisscBean.getShareInfo().getDesc();
//            share_name = EXTRA_NAME;
            share_img = sisscBean.getShareInfo().getImgUrl();
            share_imgUrl = sisscBean.getShareInfo().getUrlForFriend();
            share_imgUrl2 = sisscBean.getShareInfo().getUrlForMoments();
        }
    }

    @Override
    public void OnISSaveFavoritesNodata(String s) {

    }

    @Override
    public void OnISSaveFavoritesFail(String s) {

    }

    // 下面的播放列表bufen
    private String aaa = "";

    private int recy_current = 0;// 判断列表选中bufen

    private void set_smoothscroll() {
        if (mList != null && mAdapter3 != null && recyclerview3 != null && mBinder != null && mBinder.getService() != null) {
            if (current_url < mList.size()) {
                mAdapter3.setEX_ID(mList.get(current_url).getItemId());
                mAdapter3.setOnPause(tv_startOrpause);
                mAdapter3.notifyDataSetChanged();
                for (int i = 0; i < mAdapter3.getData().size(); i++) {
                    if (i >= PAGE_SIZE) {
                        recy_current = PAGE_SIZE;
                        break;
                    }
                    if (TextUtils.equals(mList.get(current_url).getItemId(), mAdapter3.getData().get(i).getItemId())) {
                        recy_current = i;
                        break;
                    }
                }
            }
            ((AdvertiseLinearLayoutManager) recyclerview3.getLayoutManager()).scrollToPositionWithOffset(recy_current, 0);
//            recyclerview3.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    ((AdvertiseLinearLayoutManager) recyclerview3.getLayoutManager()).scrollToPositionWithOffset(recy_current, 0);
//                }
//            }, 500);
        }
    }

    private void set_nextitem_playmusic() {
        if (recy_current + 1 <= mAdapter3.getData().size()) {
            SLB4CategoryListDetailBean1 bean = null;
            if (mBinder != null && mBinder.getService() != null) {
                if (current_url < mList.size()) {
                    bean = mAdapter3.getItem(recy_current);
                } else {
                    recy_current = recy_current + 1;
                    bean = mAdapter3.getItem(recy_current);
                }
            }
            if (current_url_zhifu > 0) {
                current_url_zhifu = 0;
            } else {
                if (bean != null) {
                    if (TextUtils.equals(bean.getReadRight(), "2") || TextUtils.equals(bean.getReadRight(), "4")) {
                        get_shidu_pop2(bean.getpId(), bean.getItemId(), bean.getSourceType());
                        set_stopmusic_click();
                        return;
                    } else if (TextUtils.equals(bean.getReadRight(), "3")) {
                        get_shidu_pop3(bean.getpId(), bean.getItemId(), bean.getSourceType());
                        set_stopmusic_click();
                        return;
                    }
                } else {
                    Toasty.normal(getApplicationContext(), "已经是最后").show();
                }
            }
        } else {
            Toasty.normal(getApplicationContext(), "已经是最后").show();
        }
        set_smoothscroll();

    }

    private boolean is_payfor;// 是否收费

    @Override
    public void OnLB3CategoryListSuccess(SLB3CategoryListBean slb3CategoryListBean, String s) {
        mList3 = slb3CategoryListBean.getList();
        mAdapter3.setNewData(mList3);
        //
//        mList = slb3CategoryListBean.getList();// 如果喜马拉雅 就要传入list重组
//        if (EXTRA_XMLY != null && EXTRA_XMLY.size() > 0) {
//            for (int i = 0; i < mList.size(); i++) {
//                SLB4CategoryListDetailBean1 bean = mList.get(i);
//                bean.setAudioUrl(EXTRA_XMLY.get(i));
//            }
//        }
//        mList = getList();
        //
        List<SLB4CategoryListDetailBean1> list_temp = new ArrayList<>();
        for (int i = 0; i < mList3.size(); i++) {
            SLB4CategoryListDetailBean1 bean1 = mList3.get(i);
            if (TextUtils.equals(bean1.getReadRight(), "2") ||
                    TextUtils.equals(bean1.getReadRight(), "3") ||
                    TextUtils.equals(bean1.getReadRight(), "4")) {
                if (!is_payfor) {
                    is_payfor = true;
                }
            } else {
                list_temp.add(bean1);
            }
        }
        mList = list_temp;
        adapter.setData(mList);
        viewpager.setAdapter(adapter);
        // 绑定音乐
        if (!flag) {
            Intent intent = new Intent(this, ListenMusicPlayerService.class);
            intent.putExtra("flag", flag);
            bindService(intent, conn, BIND_AUTO_CREATE);
        }

    }

    @Override
    public void OnLB3CategoryListNodata(String s) {

    }

    @Override
    public void OnLB3CategoryListFail(String s) {

    }

    @Override
    public void update(Observable observable, Object o) {
        if (BaseAppManager.getInstance().top().equals(ListenMusicActivity.this)) {
            mAdapter3.setEX_ID(String.valueOf(o));
            mAdapter3.setOnPause(tv_startOrpause);
            mAdapter3.notifyDataSetChanged();
        }
    }

    // 试读完成弹窗bufen
    private PopPayForVip2 popShiduPay2;

    private void get_shidu_pop2(final String bookId, final String bookItemId, final String sourceType) {
        popShiduPay2 = new PopPayForVip2(this, R.drawable.slb_popup_shidu33, getResources().getString(R.string.readbooks_tips2), new PopPayForVip2.OnFinishResultClickListener() {
            @Override
            public void onKaitonghuiyuan() {
                // 开通会员
                SlbLoginUtil2.get().loginTowhere(ListenMusicActivity.this, new Runnable() {
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
                SlbLoginUtil2.get().loginTowhere(ListenMusicActivity.this, new Runnable() {
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
        popShiduPay2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //
                if (recy_current >= mList.size()) {
                    recy_current = recy_current - 1;
                }
            }
        });
    }

    // 试读完成弹窗bufen
    private PopPayForVip3 popShiduPay3;

    private void get_shidu_pop3(final String bookId, final String bookItemId, final String sourceType) {
        popShiduPay3 = new PopPayForVip3(this, R.drawable.slb_popup_shidu33, new PopPayForVip3.OnFinishResultClickListener() {
            @Override
            public void onKaitonghuiyuan() {
                // 开通会员
                SlbLoginUtil2.get().loginTowhere(ListenMusicActivity.this, new Runnable() {
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
                SlbLoginUtil2.get().loginTowhere(ListenMusicActivity.this, new Runnable() {
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
        popShiduPay3.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //
                if (recy_current >= mList.size()) {
                    recy_current = recy_current - 1;
                }
            }
        });
    }

    /**
     * 监听音乐变化bufen
     */
    private MessageMusicReceiverIndex mMessageReceiver;

    /**
     * 监听第三方音乐变化bufen
     */
    public class MessageMusicReceiverIndex extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (CommonUtils.LB_broadcastreceiver.equals(intent.getAction())) {
                    // 切换Fragment bufen
//                    set_stopmusic_click();
                    if (intent.getIntExtra("focusChange", 0) == -2) {
                        // 暂停
                        tv_startOrpause = true;
//                        is_scroll = false;
                        tv_startpause.setBackgroundResource(R.drawable.slb_lb_start);
//                    viewpager.playMusic();
                        mBinder.musicPause();
                        // 弱网bufen
                        if (SPUtils.getInstance().getBoolean("NetChange", false)) {
                            MusicSeekBar.setProgress(0);
                            MusicSeekBar.setMax(0);
                            MusicTime.setText("00:00");
                            MusicTotal.setText("00:00");
                            if (viewpager.getmCurrentItem() != null && viewpager.getmCurrentItem().getObject() != null) {
                                ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_STOP);
                                ListenMusicUtil.getInstance().stopMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                            }
//                            SPUtils.getInstance().put("NetChange", false);
                        }
                    } else if (intent.getIntExtra("focusChange", 0) == 1) {
                        // 开始
                        tv_startOrpause = false;
//                        is_scroll = true;
                        tv_startpause.setBackgroundResource(R.drawable.slb_lb_pause);
                        if ((current_url == mList.size() - 1) && isfinish1) {
                            // 当在最后一个的时候 如果还拖动进度条 就继续播放当前 如果不拖动就返回第一个播放
                            if (mBinder.getService().getPlay_mode() == 0) {
//                            current_url = 0;
                            } else if (mBinder.getService().getPlay_mode() == 1) {
//                            current_url = current_url;
                                ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_PAUSE);
                                if (viewpager.getmCurrentItem() != null) {
                                    ListenMusicUtil.getInstance().playMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                                }
                            }
                            play_next();
                        } else {
                            ListenMusicUtil.getInstance().setState_ani(ListenMusicUtil.getInstance().STATE_PAUSE);
                            if (viewpager.getmCurrentItem() != null) {
                                ListenMusicUtil.getInstance().playMusic(((View) viewpager.getmCurrentItem().getObject()).findViewById(R.id.iv1));
                            }
                            mBinder.musicContinue();
                        }
                    }
                    mAdapter3.setOnPause(tv_startOrpause);
                    mAdapter3.notifyDataSetChanged();
                }

            } catch (Exception e) {
            }
        }
    }

    private void set_stopmusic_click() {
        if (tv_startpause != null) {
            if (!tv_startOrpause) {
                tv_startpause.performClick();
            }
        }
    }


}
