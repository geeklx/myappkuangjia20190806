package com.example.slbappreadbook;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ServiceUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappshouye.bean.SHuibenDetailBean;
import com.example.biz3slbappshouye.bean.SHuibenDetailBean2;
import com.example.biz3slbappshouye.bean.SHuibenDetailTuijianBean;
import com.example.biz3slbappshouye.bean.SHuibenDetailTuijianBean1;
import com.example.biz3slbappshouye.bean.SPopRenwuBean;
import com.example.biz3slbappshouye.bean.SPopRenwuBean1;
import com.example.biz3slbappshouye.presenter.SHuibenDetailPresenter;
import com.example.biz3slbappshouye.presenter.SHuibenDetailTuijianPresenter;
import com.example.biz3slbappshouye.presenter.SPopRenwuPresenter;
import com.example.biz3slbappshouye.view.SHuibenDetaiTuijianView;
import com.example.biz3slbappshouye.view.SHuibenDetailCommView;
import com.example.biz3slbappshouye.view.SPopRenwuView;
import com.example.biz3slbappusercenter.bean.SLB1SaveFavoritesBean;
import com.example.biz3slbappusercenter.presenter.SLB1SaveFavoritesPresenter;
import com.example.biz3slbappusercenter.view.SLB1SaveFavoritesView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.playermusic.ListenMusicPlayerService;
import com.example.slbappcomm.playermusic.SLB4CategoryListDetailBean1Temp;
import com.example.slbappcomm.pop.renwupay.payforvip.PopPayForVip2;
import com.example.slbappcomm.pop.renwupay.payforvip.PopPayForVip3;
import com.example.slbappcomm.pop.share.PopForShare;
import com.example.slbappcomm.pop.successpay.PopSuccessPay;
import com.example.slbappcomm.services.UpdataCommonservices;
import com.example.slbappcomm.viewpager.LxCoolViewPager;
import com.example.slbappreadbook.adapter.BasemusicAdapter2;
import com.example.slbappreadbook.adapter.ReadBookLastViewAdapter;
import com.example.slbappreadbook.callback.ImgCallBack;
import com.example.slbappreadbook.callback.PreparedCallBack;
import com.example.slbappreadbook.callback.SeekBarListener;
import com.example.slbappreadbook.domain.HuibenFileCacheBean;
import com.example.slbappreadbook.domain.HuibenFileCacheBeanItem;
import com.example.slbappreadbook.down.DownHuibenUtils;
import com.example.slbappreadbook.huancun.DownManager;
import com.example.slbappreadbook.huancun.HuibenFlieCacheBaseBean2Manager;
import com.example.slbappreadbook.pagertransformer.AccordionTransformer2;
import com.example.slbappreadbook.pop.PopRenwuPay;
import com.example.slbappreadbook.utils.HuibenListUtils;
import com.example.slbappreadbook.utils.RingReadActControl;
import com.example.slbappreadbook.widget.MyCoolViewPager;
import com.example.slbappshare.fenxiang.JPushShareUtils;
import com.example.slbappshare.fenxiang.OnShareResultInfoLitener;
import com.example.slbappshare.fenxiang.beans.WeixinBeanParam;
import com.geek.libglide47.base.util.DisplayUtil;
import com.haier.cellarette.baselibrary.assetsfitandroid.fileandroid.FitAndroidAssetsToCacheUtil;
import com.haier.cellarette.baselibrary.common.ConstantsUtils;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;
import com.haier.cellarette.baselibrary.handleralluse.base.WeakRefHandler;
import com.haier.cellarette.baselibrary.loading.ShowLoadingUtil;
import com.haier.cellarette.baselibrary.recycleviewutils.AdvertiseLinearLayoutManager;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.SwitchButton;
import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;
import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.share.android.api.JShareInterface;
import me.jessyan.autosize.AutoSizeCompat;

//import android.support.annotation.Nullable;
//import android.support.constraint.ConstraintLayout;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.OrientationHelper;

public class ReadBookActivity extends SlbBaseActivity implements ImgCallBack, OnShareResultInfoLitener,
        SHuibenDetailCommView, SLB1SaveFavoritesView, SHuibenDetaiTuijianView, SPopRenwuView, Observer {

    private int TIME_SETTING_LAYOUT = 6000;
    private int current = 0;
    private boolean is_first;
    private boolean is_first2;
    private RbMusicPlayerService.MyBinder mBinder;
    private boolean flag;
    private List<SHuibenDetailBean2> items;
    private MyCoolViewPager vp;
    private ConstraintLayout fl1;
    private LinearLayout ll_title1;
    private TextView tv_suo2;
    private CardView card_view1;
    private LinearLayout ll2;
    //    private TextView tv_back;
    private RelativeLayout rl20;
    private TextView tv_sc1;
    private SwitchButton slbSb1;
    private boolean is_zdlb;
    private boolean is_sc;// false 未收藏 true 收藏
    private TextView tv_home;
    private ImageView iv_stop1;
    private TextView tv_share;
    private TextView tv_down;
    private TextView tv_volume;
    private RingReadActControl ringReadActControl;
    private boolean is_vol;// true 为静音模式 false 为非
    private TextView tv_play;
    private SeekBar sb_progressbar;
    private TextView tv_strings;
    private BasemusicAdapter2 adapter;
    private ConstraintLayout flindex1;
    private EmptyView mEmptyView;

    private int mVideoWidth;
    private int mVideoHeight;
    private boolean is_dianji;// 点击变大变小视图bufen
    private boolean is_heng_shu;// 是否为横屏或竖屏bufen
    private boolean is_shoucang;// 是否收藏bufen
    private String EXTRA_ID = "";// 以商品的id为txt的名字
    private String EXTRA_ID_ZONG = "";// 以商品的id为txt的名字
    private String EXTRA_NAME = "";// 以商品的id为txt的名字
    private String TXT_URL = "";// 以商品的id为txt的路径
    private String source_type = "";// 以商品的id为txt的路径
    private ArrayList<HuibenFileCacheBean> mGoodsList;// 保存路径ConstantsUtils.file_url_wenjian下的绘本txt
    private ArrayList<HuibenFileCacheBeanItem> mList_huancun;// 保存路径下的一本绘本的bean的txt
    private JPushShareUtils jPushShareUtils;
    // 推荐绘本
    private RecyclerView mRecyclerView11;
    private ReadBookLastViewAdapter mAdapter11;
    private List<SHuibenDetailTuijianBean1> mList11;
    private SHuibenDetailTuijianPresenter presenter11;
    private SHuibenDetailTuijianBean tj_bean11;
    private SHuibenDetailTuijianBean1 tj_bean;
    private boolean is_next_click;
    private boolean is_replay_click;
    //
    private SHuibenDetailPresenter presenter;
    //
    private SLB1SaveFavoritesPresenter presenter1;
    //
    private SPopRenwuPresenter popRenwuPresenter;

    //
    private PowerManager.WakeLock mWakeLock;
    // 试读三页
    private String is_vip_content2 = "2";
    private String is_vip_content3 = "3";
    private String is_vip_content4 = "4";
    private boolean is_vip;// false 继续读 true 只能看三页   0代表无需购买  1代表vip用户无需购买，2代表需购买
    private String is_vip_tag = "";// 2 vip购买 3单本购买

    @Override
    protected void onDestroy() {
        AppUtils.unregisterAppStatusChangedListener(this);
        ShowLoadingUtil.onDestory();
        presenter.onDestory();
        presenter1.onDestory();
        presenter11.onDestory();
        popRenwuPresenter.onDestory();
        JAnalyticsInterface.onPageEnd(this, "阿迷虎的故事");//this.getClass().getCanonicalName());
        if (handler22 != null) {
            handler22.removeCallbacks(runnable);
        }
        if (handler33 != null) {
            handler33.removeCallbacks(runnable33);
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        jPushShareUtils.ondes();
        if (mBinder != null && mBinder.isService()) {
            mBinder.musicDestroy();
            unbindService(conn);
            stopService(new Intent(this, RbMusicPlayerService.class));
            // 解决第一次播放连播的问题
            SPUtils.getInstance().put(RbMusicPlayerService.TAG_EXTRA_ID_FIRST, "");
            SPUtils.getInstance().put(RbMusicPlayerService.TAG_EXTRA_ID_ZONG_FIRST, "");
//            this.finish();
        } else {
//            this.finish();
        }
        // 暂停第三方音乐bufen
        LocalBroadcastManagers.getInstance(getApplicationContext()).unregisterReceiver(mMessageReceiver);
        // 写入缓存bufen
        if (mGoodsList != null && mGoodsList.size() > 0) {
            HuibenFlieCacheBaseBean2Manager.getInstance().AddHashMap(EXTRA_ID, mGoodsList);
        }
        if (downLoaderCallBack != null) {
            DownHuibenUtils.getInstance().pause(downLoaderCallBack);
        }
        // 息屏
        HuibenListUtils.getInstance(getApplicationContext()).set_off_light(this);
        // 统计页面关闭时间
        Intent intent = new Intent(this, UpdataCommonservices.class);
        intent.setAction(UpdataCommonservices.HUIBEN_READINGTIME_ACTION);
        intent.putExtra(UpdataCommonservices.id_zong, EXTRA_ID_ZONG);
        intent.putExtra(UpdataCommonservices.id, EXTRA_ID);
        intent.putExtra(UpdataCommonservices.type, UpdataCommonservices.type2);
        intent.putExtra(UpdataCommonservices.sourceType, UpdataCommonservices.sourceType2);
        intent.putExtra(UpdataCommonservices.time, System.currentTimeMillis() + "");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForegroundService(intent);
//        } else {
//            // Pre-O behavior.
//            startService(intent);
//        }
        startService(intent);
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        if (is_dianji) {
            //缩小动画
            is_dianji = false;
            set_hengshu(true);
            // 缩小
            dianji_onplay();
            if (current == adapter.getCount() - 1) {
            } else {
                return;
            }
        }
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }

    private boolean is_suo = false;// 加锁

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (is_suo) {
                suoping2();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        NavigationBarUtil.fullScreenImmersive(getWindow().getDecorView());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果在听书先关了
        if (ServiceUtils.isServiceRunning("com.example.slbappcomm.playermusic.ListenMusicPlayerService")) {
            SPUtils.getInstance().put(CommonUtils.LISTENBOOK_TAG1, false);
            stopService(new Intent(this, ListenMusicPlayerService.class));
        }
    }

    @Override
    protected void onResume() {
        if (SPUtils.getInstance().getInt(CommonUtils.HUIBEN_PAYSUCCESS, -1) == CommonUtils.HUIBEN_PAYSUCCESS_TAG1) {
            // VIP跳出层 支付成功
            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, -1);
            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS, -1);
            setDataBean();

        }
        if (SPUtils.getInstance().getInt(CommonUtils.HUIBEN_PAYSUCCESS, -1) == CommonUtils.HUIBEN_PAYSUCCESS_TAG2) {
            // 订单跳出层 支付成功
            if (getWindow().getDecorView().isAttachedToWindow()) {
                PopSuccessPay popSuccessPay = new PopSuccessPay(this);
                popSuccessPay.showAtLocation(getWindow().getDecorView(),
                        Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
                        , 0, 0); // 设置layout在PopupWindow中显示的位置
            }
            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, -1);
            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS, -1);
            setDataBean();

        }
        super.onResume();
        // 音量监听
        ringReadActControl.ringStartListenSystemVol();
        //
        Intent intent = new Intent(this, RbMusicPlayerService.class);
        startService(intent);
    }

    private boolean is_pasue_playmusic;// 判断音乐继续播放bufen

    // 刷新页面next数据bufen
    private void setUpdate_next_data(SHuibenDetailBean bean1) {
        //隐藏
        rl20.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(bean1.getNextBookItem().getBookItemId())) {
            EXTRA_ID = bean1.getNextBookItem().getBookItemId();
            EXTRA_ID_ZONG = bean1.getNextBookItem().getBookId();
            EXTRA_NAME = bean1.getNextBookItem().getItemName();
        }
        if (bean1.getShareInfo() != null) {
            share_title = bean1.getShareInfo().getTitle();
            share_name = bean1.getShareInfo().getDesc();
            share_img = bean1.getShareInfo().getImgUrl();
            share_imgUrl = bean1.getShareInfo().getUrlForFriend();
            share_imgUrl2 = bean1.getShareInfo().getUrlForMoments();
        }
        // 不需要存本地的字段bufen
        if (bean1.getBookItem().getReadRight().equals(is_vip_content2)
                || bean1.getBookItem().getReadRight().equals(is_vip_content3)
                || bean1.getBookItem().getReadRight().equals(is_vip_content4)) {
            is_vip = true;
            is_vip_tag = bean1.getBookItem().getReadRight();
        } else {
            is_vip = false;
        }
        //
        tv_play.setVisibility(View.GONE);
        TXT_URL = bean1.getBookItem().getCoverImgA();
        is_heng_shu = bean1.getBookItem().isVertical();
        source_type = bean1.getBookItem().getSourceType();
        // 进来是竖屏要设置为横屏
        is_dianji = true;
        set_hengshu(is_heng_shu);
        is_shoucang = bean1.getBookItem().isCollect();
        if (is_shoucang) {
            is_sc = true;
            tv_sc1.setBackgroundResource(R.drawable.play_collectrb33);
        } else {
            is_sc = false;
            tv_sc1.setBackgroundResource(R.drawable.play_collectrb3);
        }
        //
        items = bean1.getBookItemDetails();
        adapter.setData(items);// 网络
        vp.setAdapter(adapter);
        //
        tv_strings.setText(getString(R.string.image_counts, mBinder.getService().getCurrent() + 1, Integer.valueOf(sHuibenDetailBean1.getBookItem().getItemPages())));
        sb_progressbar.setMax(items.size() - 1);
        sb_progressbar.setProgress(mBinder.getService().getCurrent());
        //
//        is_pasue_playmusic = true;
//        vp.setCurrentItem(mBinder.getService().getCurrent());
    }

    @Override
    public void update(Observable observable, Object bean) {
        SHuibenDetailBean bean1 = (SHuibenDetailBean) bean;
        if (bean1 != null) {
            sHuibenDetailBean1 = bean1;
            items = bean1.getBookItemDetails();
//            current = mBinder.getService().getCurrent();
            current = mBinder.getService().getCurrent();
            setUpdate_next_data(bean1);
            //
//        is_pasue_playmusic = true;
            is_setmusicplay = true;// 下面的播放按钮
            iv_stop1.setImageResource(R.drawable.slb_playrb22);
            vp.setCurrentItem(mBinder.getService().getCurrent());
        }
    }

    @Override
    protected void onPause() {
//        // 统计页面置空时间
//        Intent intent = new Intent(this, Updataservices.class);
//        intent.setAction(Updataservices.HUIBEN_READINGTIME_ACTION);
//        intent.putExtra(Updataservices.id_zong, EXTRA_ID_ZONG);
//        intent.putExtra(Updataservices.id, EXTRA_ID);
//        intent.putExtra(Updataservices.type, "end");
//        intent.putExtra(Updataservices.time, System.currentTimeMillis() + "");
//        startService(intent);
        super.onPause();
        // 音量监听
        ringReadActControl.ringFinishListenSystemVol();
        // 切换前后台控制声音的播放bufen
        AppUtils.registerAppStatusChangedListener(this, new Utils.OnAppStatusChangedListener() {
            @Override
            public void onForeground() {
                // 前台
//                if (ScreenUtils.isLandscape()) {
//                    AutoSize.autoConvertDensityOfGlobal(ReadBookActivity.this); //如果没有自定义需求用这个方法
//                    AutoSize.autoConvertDensity(ReadBookActivity.this, 375, false); //如果有自定义需求就用这个方法
//                } else {
//                    AutoSize.autoConvertDensityOfGlobal(ReadBookActivity.this); //如果没有自定义需求用这个方法
//                    AutoSize.autoConvertDensity((Activity) ReadBookActivity.this, 375, true);// 667
//                }
                if (mBinder != null && mBinder.getService() != null /*&& !mBinder.getService().getmPlayer().isPlaying()*/) {
                    // 打开
//                    is_setmusicplay = true;
//                    iv_stop1.setImageResource(R.drawable.slb_playrb22);
//                    mBinder.musicContinue();
                    //
                    if (mBinder.getService().getCurrent() == adapter.getCount() - 1) {
//                            adapter.setData(chushihua_hashmap());// 拿出缓存bufen
//                            vp.setAdapter(adapter);
//                            vp.setCurrentItem(0);
                    } else {
                        if (mBinder.getService().getsHuibenDetailBean() != null) {
                            sHuibenDetailBean1 = mBinder.getService().getsHuibenDetailBean();
                            items = mBinder.getService().getsHuibenDetailBean().getBookItemDetails();
                            current = mBinder.getService().getCurrent();
                            setUpdate_next_data(mBinder.getService().getsHuibenDetailBean());
                            //
                            is_pasue_playmusic = true;
                            if (is_setmusicplay) {
                                vp.setCurrentItem(mBinder.getService().getCurrent());
                            }
                        }
                    }
                }
            }

            @Override
            public void onBackground() {
                // 后台
                if (mBinder != null && mBinder.getService() != null && mBinder.getService().getmPlayer() != null && mBinder.getService().getmPlayer().isPlaying()) {
                    // 关了
//                    is_setmusicplay = false;
//                    iv_stop1.setImageResource(R.drawable.slb_playrb2);
//                    mBinder.musicPause();
                    mBinder.getService().setsHuibenDetailBean(sHuibenDetailBean1);
                    mBinder.getService().setCurrent(current);

                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_readbook;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        //虚拟键
        if (NavigationBarUtil.hasNavigationBar(this)) {
//            NavigationBarUtil.initActivity(getWindow().getDecorView());
            NavigationBarUtil.hideBottomUIMenu(this);
        }
        super.setup(savedInstanceState);
        // 统计时间start bufen
        JAnalyticsInterface.onPageStart(this, "阿迷虎的故事");//this.getClass().getCanonicalName());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);// topbar
        findview();
        onclick();
        donetwork();
        // 亮屏
        HuibenListUtils.getInstance(getApplicationContext()).set_on_light(this);
        // 统计页面开始时间
        Intent it = new Intent(this, UpdataCommonservices.class);
        it.setAction(UpdataCommonservices.HUIBEN_READINGTIME_ACTION);
        it.putExtra(UpdataCommonservices.id_zong, EXTRA_ID_ZONG);
        it.putExtra(UpdataCommonservices.id, EXTRA_ID);
        it.putExtra(UpdataCommonservices.type, UpdataCommonservices.type1);
        it.putExtra(UpdataCommonservices.sourceType, UpdataCommonservices.sourceType2);
        it.putExtra(UpdataCommonservices.time, System.currentTimeMillis() + "");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForegroundService(it);
//        } else {
//            // Pre-O behavior.
//            startService(it);
//        }
        startService(it);
        // 第三方音乐bufen
        mMessageReceiver = new MessageReadBooksMusicReceiverIndex();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(CommonUtils.RB_broadcastreceiver);
        LocalBroadcastManagers.getInstance(getApplicationContext()).registerReceiver(mMessageReceiver, filter);
        //
//        MobclickAgent.onEvent(this, "ReadBookActivity");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", EXTRA_NAME);
//        map.put("quantity","3");
        MobclickAgent.onEvent(this, "ReadBookActivity", map);
    }


    private void findview() {
        flindex1 = findViewById(R.id.flindex1);
        mEmptyView = findViewById(R.id.empty_view);
        mEmptyView.bind(fl1).setRetryListener(new EmptyView.RetryListener() {
            @Override
            public void retry() {
                // 重试bufen
                setDataBean();
            }
        });
        vp = findViewById(R.id.vp);
        tv_suo2 = findViewById(R.id.tv_suo2);
        tv_strings = findViewById(R.id.tv_strings);
        sb_progressbar = findViewById(R.id.sb_progressbar);
        fl1 = findViewById(R.id.fl1);
        ll_title1 = findViewById(R.id.ll_title1);
        ll_title1.setVisibility(View.GONE);
        card_view1 = findViewById(R.id.card_view1);
        card_view1.setUseCompatPadding(true);
        card_view1.setRadius(16.0f);
        ll2 = findViewById(R.id.ll2);
//        tv_back = findViewById(R.id.tv_back);
        rl20 = findViewById(R.id.rl20);
        tv_sc1 = findViewById(R.id.tv_scrb2);
        slbSb1 = findViewById(R.id.slb_zdlb);
        tv_home = findViewById(R.id.tv_home);
        tv_share = findViewById(R.id.tv_share);
        tv_down = findViewById(R.id.tv_down);
        tv_volume = findViewById(R.id.tv_volume);
        ringReadActControl = new RingReadActControl(this, tv_volume, new Handler());
        ringReadActControl.setViewOnClick2();
//        tv_volume.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        tv_volume.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                // 设置静音模式bufen
//                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
//                        && !notificationManager.isNotificationPolicyAccessGranted()) {
//                    Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
//                    getApplicationContext().startActivity(intent);
//                    return;
//                }
                if (ContextCompat.checkSelfPermission(ReadBookActivity.this, Manifest.permission.ACCESS_NOTIFICATION_POLICY) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ActivityCompat.requestPermissions(ReadBookActivity.this, new String[]{Manifest.permission.ACCESS_NOTIFICATION_POLICY}, FitAndroidAssetsToCacheUtil.REQ_PERMISSION_CODE_SDCARD);
                    } else {
                        ringReadActControl.setViewOnClick();
                    }
                } else {
                    ringReadActControl.setViewOnClick();
                }

            }
        });
        tv_play = findViewById(R.id.tv_play);
        iv_stop1 = findViewById(R.id.iv_stop1);

        tv_play.setVisibility(View.GONE);
//        tv_back.setVisibility(View.GONE);
        rl20.setVisibility(View.VISIBLE);
        rl20.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));// grayCCCECECE
//        tv_home.setVisibility(View.VISIBLE);
//        tv_share.setVisibility(View.VISIBLE);
//        tv_down.setVisibility(View.VISIBLE);
//        setlayout_small();
//        card_view1.setPreventCornerOverlap(true);
//        card_view1.setUseCompatPadding(true);
        ll2.setVisibility(View.VISIBLE);
        iv_stop1.setVisibility(View.GONE);
        ll2.setBackgroundResource(R.drawable.footerbg2);
        tv_strings.setText("");
        tv_strings.setTextColor(ContextCompat.getColor(ReadBookActivity.this, R.color.black_000));

//        sb_progressbar.setProgressDrawable(getDrawable(R.drawable.books_bg_progress_black));
        handler22 = new Handler();
        handler33 = new Handler();

        vp.setScrollMode(LxCoolViewPager.ScrollMode.HORIZONTAL);
        vp.setAutoScroll(false);
        vp.setAutoScrollDirection(LxCoolViewPager.AutoScrollDirection.BACKWARD);
        vp.setInfiniteLoop(false);
        vp.setScrollDuration(true, 600);
        vp.setDrawEdgeEffect(true);
        vp.setEdgeEffectColor(getResources().getColor(R.color.colorPrimary));
//        vp.toggleAutoScrollDirection();
//        vp.setPageTransformer(false, new ZoomOutTransformer2());
        vp.setPageTransformer(false, new AccordionTransformer2());
//        vp.setOffscreenPageLimit(0);

        items = new ArrayList<>();
        adapter = new BasemusicAdapter2(ReadBookActivity.this, items, this);
        vp.setAdapter(adapter);
    }

    private void onclick() {
        is_zdlb = SPUtils.getInstance().getInt(CommonUtils.READBOOK_AUTOBUTTON, -1) == 1;
        slbSb1.setChecked(is_zdlb);
        slbSb1.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    //设置开启
                    SPUtils.getInstance().put(CommonUtils.READBOOK_AUTOBUTTON, 1);
                    is_zdlb = true;
                    if (current == adapter.getCount() - 1) {
                        if (mBinder != null) {
                            mBinder.getService().setToId2(EXTRA_ID_ZONG, EXTRA_ID, EXTRA_NAME);
//                                    mBinder.getService().setNext_id(next_id);
                            is_next_click = false;
//                            if (TextUtils.isEmpty(mBinder.getService().getNext_id())) {
//                                mBinder.getService().setToId(next_id);
//                            } else {
//                                mBinder.getService().setToId(mBinder.getService().getNext_id());
//                            }
                        }
                    }
                } else {
                    // 设置关闭
                    is_zdlb = false;
                    SPUtils.getInstance().put(CommonUtils.READBOOK_AUTOBUTTON, -1);
                }
            }
        });
        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_suo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 锁屏bufen
                if (!is_suo) {
                    is_suo = true;
                    tv_suo2.setBackgroundResource(R.drawable.slb_suo2);
                    // 锁屏
//                    // 功能栏
//                    set_isvis_seekbar(false);
                    rl20.setVisibility(View.GONE);
                    ll2.setVisibility(View.GONE);
                } else {
                    is_suo = false;
                    tv_suo2.setBackgroundResource(R.drawable.slb_suo1);
                    if (handler33 != null) {
                        handler33.removeCallbacks(runnable33);
                    }
                    // 解锁
                    // 功能栏
                    set_isvis_seekbar(true);

                }
            }
        });
        tv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 分享bufen
//                qita_wx_share();
                // 如果是横屏先退出小的再弹出窗
                set_pop_small();
                //
                get_share_pop();
            }
        });
        iv_stop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 暂停bufen
                qita_stop_music();
            }
        });
        tv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 下载bufen
                if (SPUtils.getInstance().getString(EXTRA_ID).equals(EXTRA_ID)) {
                    Toasty.normal(ReadBookActivity.this, "已下载完成").show();
                } else {
                    qita_down();
                }

            }
        });
        tv_sc1.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (!SlbLoginUtil2.get().isUserLogin()) {
                    onBackpress();
                }
                SlbLoginUtil2.get().loginTowhere(ReadBookActivity.this, new Runnable() {
                    @Override
                    public void run() {
                        if (!is_sc) {
                            is_sc = true;
                            tv_sc1.setBackgroundResource(R.drawable.play_collectrb33);
                        } else {
                            is_sc = false;
                            tv_sc1.setBackgroundResource(R.drawable.play_collectrb3);
                        }
//                        presenter1.getSaveFavoritesData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), EXTRA_ID, !is_sc);
                        presenter1.getLB1SaveFavoritesData(EXTRA_ID,
                                EXTRA_ID_ZONG, !is_sc, source_type);
                    }
                });
            }
        });
//        tv_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
        adapter.setLastView(new BasemusicAdapter2.OnLastPagerListener() {
            @Override
            public void OnResult(final View view, int pos) {
                if (view == null) {
                    return;
                }
                RelativeLayout rl2 = view.findViewById(R.id.rl2);
                ConstraintLayout rllast1 = view.findViewById(R.id.rllast1);
                //lastye
                if (pos == adapter.getCount() - 1) {
                    final TextView tv_zdlb1 = view.findViewById(R.id.tv_zdlb1);
                    TextView tv_backn1 = view.findViewById(R.id.tv_backn1);
                    final TextView tv_scn1 = view.findViewById(R.id.tv_scn1);
                    TextView tv_sharen1 = view.findViewById(R.id.tv_sharen1);
                    TextView tv_re1 = view.findViewById(R.id.tv_re1);
                    TextView tv_next1 = view.findViewById(R.id.tv_next1);
                    rl2.setVisibility(View.GONE);
                    rllast1.setVisibility(View.VISIBLE);
                    if (is_dianji) {
                        tv_scn1.setVisibility(View.VISIBLE);
                    } else {
                        tv_scn1.setVisibility(View.GONE);
                    }
                    tv_scn1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (is_sc) {
                                tv_scn1.setBackgroundResource(R.drawable.play_collectrb33);
                            } else {
                                tv_scn1.setBackgroundResource(R.drawable.play_collectrb3);
                            }
                        }
                    }, 100);
                    //
                    RecyclerView mRecyclerView11 = view.findViewById(R.id.recycler_view1);
                    ReadBookLastViewAdapter mAdapter11 = new ReadBookLastViewAdapter(R.layout.activity_readbook_tj);
                    if (ScreenUtils.isLandscape()) {
                        // 横屏
                        mRecyclerView11.setLayoutManager(new AdvertiseLinearLayoutManager(ReadBookActivity.this, RecyclerView.HORIZONTAL, false));
                        mRecyclerView11.setAdapter(mAdapter11);
                    } else {
                        // 竖屏
                        mRecyclerView11.setLayoutManager(new GridLayoutManager(ReadBookActivity.this, 3, RecyclerView.VERTICAL, false));
                        mRecyclerView11.setAdapter(mAdapter11);
                    }
//                    mRecyclerView11.setLayoutManager(new GridLayoutManager(ReadBookActivity.this, 1, OrientationHelper.HORIZONTAL, false));
//        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
//        mLinearSnapHelper.attachToRecyclerView(mRecyclerView);
                    if (tj_bean11 != null) {
                        mAdapter11.setNewData(tj_bean11.getResult());
                    }
                    mAdapter11.notifyDataSetChanged();
                    // 选中当前播放的item
                    for (int i = 0; i < mAdapter11.getData().size(); i++) {
                        if (TextUtils.equals(EXTRA_ID, mAdapter11.getData().get(i).getBookItemId())) {
                            mRecyclerView11.smoothScrollToPosition(i);
                        }
                    }
                    //
                    mAdapter11.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            if (!NetworkUtils.isConnected()) {
                                Toasty.normal(getApplicationContext(), "网络异常，请检查网络连接！").show();
                                return;
                            }
                            final SHuibenDetailTuijianBean1 bean = (SHuibenDetailTuijianBean1) adapter.getItem(position);
                            tj_bean = bean;
                            int i = view.getId();
//                                    ShowLoadingUtil.showLoading(ReadBookActivity.this, "", 1000, null);
                            if (mVideoWidth < ScreenUtils.getScreenWidth() && mVideoHeight < ScreenUtils.getScreenHeight()) {
                                if (current < 0) {
                                    return;
                                }
                                is_dianji = true;
//            vp.setPageTransformer(false, new AccordionTransformer2());
                                // 进来是竖屏要设置为横屏
                                set_hengshu(is_heng_shu);
                                // 放大
                                dianji_play();
                            } else {
                                mBinder.getService().setToId22(tj_bean.getBookId(), tj_bean.getBookItemId(), tj_bean.getBookName());
                                tj_bean = null;
                            }
                        }
                    });
                    // 自动连播
                    if (SPUtils.getInstance().getInt(CommonUtils.READBOOK_AUTOBUTTON, -1) == 1) {
//                        select_useful(tv_zdlb1, R.drawable.choose_green3);
                        if (ScreenUtils.isLandscape()) {
                            select_useful(tv_zdlb1, R.drawable.slb_zdlb2, DisplayUtil.dip2px(ReadBookActivity.this, 40));
                        } else {
                            select_useful(tv_zdlb1, R.drawable.slb_zdlb2, DisplayUtil.dip2px(ReadBookActivity.this, 20));
                        }
                    } else {
//                        select_useful(tv_zdlb1, R.drawable.choose_no3);
                        if (ScreenUtils.isLandscape()) {
                            select_useful(tv_zdlb1, R.drawable.slb_zdlb1, DisplayUtil.dip2px(ReadBookActivity.this, 40));
                        } else {
                            select_useful(tv_zdlb1, R.drawable.slb_zdlb1, DisplayUtil.dip2px(ReadBookActivity.this, 20));
                        }
                    }
                    tv_zdlb1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (is_zdlb) {
                                is_zdlb = false;
                                if (ScreenUtils.isLandscape()) {
                                    select_useful(tv_zdlb1, R.drawable.slb_zdlb1, DisplayUtil.dip2px(ReadBookActivity.this, 40));
                                } else {
                                    select_useful(tv_zdlb1, R.drawable.slb_zdlb1, DisplayUtil.dip2px(ReadBookActivity.this, 20));
                                }
                                SPUtils.getInstance().put(CommonUtils.READBOOK_AUTOBUTTON, -1);
                            } else {
                                is_zdlb = true;
                                if (ScreenUtils.isLandscape()) {
                                    select_useful(tv_zdlb1, R.drawable.slb_zdlb2, DisplayUtil.dip2px(ReadBookActivity.this, 40));
                                } else {
                                    select_useful(tv_zdlb1, R.drawable.slb_zdlb2, DisplayUtil.dip2px(ReadBookActivity.this, 20));
                                }
                                //设置开启
                                SPUtils.getInstance().put(CommonUtils.READBOOK_AUTOBUTTON, 1);
                                is_zdlb = true;
                                if (current == adapter.getCount() - 1) {
                                    if (mBinder != null) {
                                        mBinder.getService().setToId2(EXTRA_ID_ZONG, EXTRA_ID, EXTRA_NAME);
                                        is_next_click = false;
                                    }
                                }
                            }
                        }
                    });
                    // 返回
                    tv_backn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                        }
                    });
                    // 收藏
                    tv_scn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!NetworkUtils.isConnected()) {
                                Toasty.normal(getApplicationContext(), "网络异常，请检查网络连接！").show();
                                return;
                            }
                            if (!SlbLoginUtil2.get().isUserLogin()) {
                                onBackpress();
                            }
                            SlbLoginUtil2.get().loginTowhere(ReadBookActivity.this, new Runnable() {
                                @Override
                                public void run() {
                                    if (!is_sc) {
                                        is_sc = true;
                                        tv_scn1.setBackgroundResource(R.drawable.play_collectrb33);
                                        tv_sc1.setBackgroundResource(R.drawable.play_collectrb33);
                                    } else {
                                        is_sc = false;
                                        tv_scn1.setBackgroundResource(R.drawable.play_collectrb3);
                                        tv_sc1.setBackgroundResource(R.drawable.play_collectrb3);
                                    }
//                        presenter1.getSaveFavoritesData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), EXTRA_ID, !is_sc);
                                    presenter1.getLB1SaveFavoritesData(EXTRA_ID,
                                            EXTRA_ID_ZONG, !is_sc, source_type);
                                }
                            });
                        }
                    });
                    // 分享
                    tv_sharen1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // 分享bufen
//                qita_wx_share();
                            // 如果是横屏先退出小的再弹出窗
//                            set_pop_small();
                            //
                            if (!NetworkUtils.isConnected()) {
                                Toasty.normal(getApplicationContext(), "网络异常，请检查网络连接！").show();
                                return;
                            }
                            get_share_pop();
                        }
                    });
                    // 下一本
                    tv_next1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            SPUtils.getInstance().put(CommonUtils.READBOOK_AUTOBUTTON, 1);
                            if (!NetworkUtils.isConnected()) {
                                Toasty.normal(getApplicationContext(), "网络异常，请检查网络连接！").show();
                                return;
                            }
                            if (current == adapter.getCount() - 1) {
//                                ShowLoadingUtil.showLoading(ReadBookActivity.this, "", 1000, null);
                                if (mVideoWidth < ScreenUtils.getScreenWidth() && mVideoHeight < ScreenUtils.getScreenHeight()) {
                                    if (current < 0) {
                                        return;
                                    }
                                    is_dianji = true;
//            vp.setPageTransformer(false, new AccordionTransformer2());
                                    // 进来是竖屏要设置为横屏
                                    set_hengshu(is_heng_shu);
                                    // 放大
                                    is_next_click = true;
                                    dianji_play();
                                } else {
//                                    mBinder.getService().setToId(next_id);
                                    mBinder.getService().setToId2(EXTRA_ID_ZONG, EXTRA_ID, EXTRA_NAME);
//                                    mBinder.getService().setNext_id(next_id);
                                    is_next_click = false;
                                }
                            }
                        }
                    });
                    tv_re1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // 回到第一页
                            if (!NetworkUtils.isConnected()) {
                                Toasty.normal(getApplicationContext(), "网络异常，请检查网络连接！").show();
                                return;
                            }
                            if (mVideoWidth < ScreenUtils.getScreenWidth() && mVideoHeight < ScreenUtils.getScreenHeight()) {
                                is_replay_click = true;
                                if (current < 0) {
                                    return;
                                }
                                is_dianji = true;
//                                vp.setPageTransformer(false, new AccordionTransformer2());
                                // 进来是竖屏要设置为横屏
                                set_hengshu(is_heng_shu);
                                dianji_play();

                            } else {
                                current = 0;
                                is_setmusicplay = true;
                                iv_stop1.setImageResource(R.drawable.slb_playrb22);
                                vp.setCurrentItem(current);
                            }
//                            view.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                }
//                            }, 500);
                            //
//                            final ArrayList<String> list12 = new ArrayList<>();
//                            for (SHuibenDetailBean2 bean : items) {
//                                list12.add(bean.getAudio());
//                            }
//                            if (mBinder != null) {
//                                mBinder.getService().setMusicList(list12);
//                                mBinder.getService().setsHuibenDetailBean(sHuibenDetailBean1);
//                                mBinder.getService().setCurrent(current);
//                            }
                        }
                    });
                    //
                    // 任务弹窗bufen 为了统计时间 暂时废弃
//                    popRenwuPresenter.getPopRenwuData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), EXTRA_ID, "end");
                } else {
                    rllast1.setVisibility(View.GONE);
                    rl2.setVisibility(View.VISIBLE);
                }
            }
        });
        vp.addOnPageChangeListener(new LxCoolViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                MyLogUtil.e("----第几个pos--onPageScrolled--", i + "");
//                current = i;
//                if (mBinder != null && mBinder.getService() != null) {
//                    mBinder.getService().setCurrent(current);
//                }
//                SHuibenDetailBean2 model = adapter.getContacts(i);
                //播放音乐bufen
                if (!is_first) {
                    is_first = true;
                    return;
                }
                // 数据显示bufen
//                tv_strings.setText(getString(R.string.image_counts, current + 1, Integer.valueOf(sHuibenDetailBean1.getBookItem().getItemPages())));
//                sb_progressbar.setProgress(i);

            }

            @Override
            public void onPageSelected(int i) {
                MyLogUtil.e("----第几个pos--onPageSelected--", i + "");
                SHuibenDetailBean2 model = adapter.getContacts(i);
                current = i;
                Message msg1 = Message.obtain();
                msg1.what = 10;
                msg1.obj = current;
                mHandler.sendMessage(msg1);
//                09-21 13:54:44.969 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged点击、滑屏
//                09-21 13:54:47.321 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged释放
//                09-21 13:54:48.326 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged无动作
//                09-21 13:54:51.440 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged点击、滑屏
//                09-21 13:54:51.600 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged释放
//                09-21 13:54:51.600 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageSelected
//                09-21 13:54:51.855 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged无动作
//                09-21 13:54:51.855 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->instantiateItem
//                09-21 13:54:51.856 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->destroyItem
                // 试读bufen
//                if ((Integer.valueOf(model.getId()) == adapter.getLastItem()) && is_vip) {
                if (TextUtils.equals(model.getId(), adapter.getLastItem() + "")) {
                    //显示
                    if (mVideoWidth < ScreenUtils.getScreenWidth() && mVideoHeight < ScreenUtils.getScreenHeight()) {
//                        rl20.setVisibility(View.GONE);
//                        rl20.setBackgroundColor(ContextCompat.getColor(ReadBookActivity.this, R.color.transparent50));// grayCCCECECE
                    } else {
                        if (is_dianji) {
                            if (handler22 != null) {
                                handler22.removeCallbacks(runnable);
                            }
                        }
                        tv_suo2.setVisibility(View.GONE);
                        ll2.setVisibility(View.GONE);
                        rl20.setVisibility(View.GONE);
                        rl20.setBackgroundColor(ContextCompat.getColor(ReadBookActivity.this, R.color.transparent));// grayCCCECECE
                    }
                    if (is_vip) {
                        // 如果是横屏先退出小的再弹出窗
                        set_pop_small();
                        Message msg2 = Message.obtain();
                        msg2.what = 11;
                        msg2.obj = "弹窗";
//                        mHandler.sendMessage(msg2);
                        mHandler.sendMessageDelayed(msg2, 500);
                    }
                } else {
                    if (ll2.getVisibility() == View.GONE) {
                        rl20.setVisibility(View.GONE);
                        rl20.setBackgroundColor(ContextCompat.getColor(ReadBookActivity.this, R.color.transparent50));// grayCCCECECE
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                MyLogUtil.e("----第几个pos--onPageScrollStateChanged--", i + "");
                switch (i) {
                    case LxCoolViewPager.SCROLL_STATE_IDLE:
                        //无动作、初始状态
                        Log.i("--onPageChanged--", "---->onPageScrollStateChanged无动作");
                        break;
                    case LxCoolViewPager.SCROLL_STATE_DRAGGING:
                        //点击、滑屏
                        Log.i("--onPageChanged--", "---->onPageScrollStateChanged点击、滑屏");
                        break;
                    case LxCoolViewPager.SCROLL_STATE_SETTLING:
                        //释放
                        Log.i("--onPageChanged--", "---->onPageScrollStateChanged释放");
                        // 音乐bufen
//                        if (mBinder != null) {
//                            mBinder.musicNext(ReadBookActivity.this, adapter.getContacts(i).getAudio());
//                        }
                        break;
                }
            }
        });
        sb_progressbar.setOnSeekBarChangeListener(new SeekBarListener() {

            private int progre;
            private boolean fromuser;

            @SuppressLint("LongLogTag")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                int type = (int) seekBar.getTag();
                Log.e("-seekbar-onProgressChanged--", "");
                progre = progress;
                fromuser = fromUser;
//                seekBar.setTag(progress);
//                if (fromUser) {
//                    vp.setCurrentItem(progress);
//                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("-seekbar-onStartTrackingTouch--", "");
                if (is_dianji) {
//                    isontouching = true;
                    if (handler22 != null) {
                        handler22.removeCallbacks(runnable);
                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("-seekbar-onStopTrackingTouch--", "");
                if (fromuser) {
                    vp.setCurrentItem(progre);
                }
                if (is_dianji) {
//                    isontouching = false;
                    set_isvis_seekbar(true);
                    //
                    if (current == adapter.getCount() - 1) {
                        tv_suo2.setVisibility(View.GONE);
                        ll2.setVisibility(View.GONE);
                        rl20.setVisibility(View.GONE);
                        rl20.setBackgroundColor(ContextCompat.getColor(ReadBookActivity.this, R.color.transparent));// grayCCCECECE
                    } else {
//                        sb_progressbar.setProgressDrawable(getDrawable(R.drawable.books_bg_progress_white));
                    }
                }
                //
//                int type = (int) seekBar.getTag();
//                if (is_dianji && mBinder != null && mBinder.getService().getmPlayer().isPlaying()) {
//                    mBinder.musicStop();
//                }

            }
        });
    }

    private Handler mHandler;
    private Handler.Callback mCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 10 && msg.obj != null) {
                final int time = (Integer) msg.obj;
                if (mBinder != null && mBinder.getService() != null) {
                    mBinder.getService().setCurrent(time);
                }
                tv_strings.setText(getString(R.string.image_counts, time + 1, Integer.valueOf(sHuibenDetailBean1.getBookItem().getItemPages())));
                sb_progressbar.setProgress(time);
                // 音乐bufen
                if (mBinder != null) {
//                        mBinder.musicNext(ReadBookActivity.this, "mp3/" + model.getId() + ".mp3");
                    // wangluo
                    // 这里根据需求加入判断如果开了bar就不继续读 手动翻页
                    // 这里判断stopmusic的按钮
                    if (!is_setmusicplay) {
                        SPUtils.getInstance().put("stopmusic", true);
                    }
                    if (is_pasue_playmusic) {
                        mBinder.musicContinue();
                        is_pasue_playmusic = false;
                    } else {
                        if (is_setmusicplay) {
                            mBinder.musicNext(ReadBookActivity.this, adapter.getContacts(time).getAudio());
                        }
                    }
                }
            }
            if (msg.what == 11 && msg.obj != null) {
                if (TextUtils.equals(is_vip_tag, is_vip_content2) || TextUtils.equals(is_vip_tag, is_vip_content4)) {
                    // 显示一个
                    get_shidu_pop2();
                } else if (TextUtils.equals(is_vip_tag, is_vip_content3)) {
                    // 显示两个
                    get_shidu_pop3();
                }
            }
            return true;
        }
    };

    private void donetwork() {
        ShowLoadingUtil.showLoading(this, "", 1000, null);
        EXTRA_ID = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS);
        EXTRA_ID_ZONG = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS_ZONG);
        EXTRA_NAME = getIntent().getStringExtra(CommonUtils.HUIBEN_TITLES);
        if (TextUtils.equals(SPUtils.getInstance().getString(EXTRA_ID), EXTRA_ID)) {
            tv_down.setBackgroundResource(R.drawable.slbdowned);// 下载了
        } else {
            tv_down.setBackgroundResource(R.drawable.slbdown);// 没下载
        }
        jPushShareUtils = new JPushShareUtils(this);
        mHandler = new WeakRefHandler(mCallback);
        setDataBean();
    }

    private void setDataBean() {
        mEmptyView.loading();
        items = new ArrayList<>();
        mGoodsList = new ArrayList<>();
        mList_huancun = new ArrayList<>();
        presenter = new SHuibenDetailPresenter();
        presenter.onCreate(this);
        presenter.getHuibenDetailData(EXTRA_ID);
        presenter1 = new SLB1SaveFavoritesPresenter();
        presenter1.onCreate(this);
        //
        presenter11 = new SHuibenDetailTuijianPresenter();
        presenter11.onCreate(this);
        presenter11.getHuibenDetailTuijianData(EXTRA_ID_ZONG, EXTRA_ID);
        //
        popRenwuPresenter = new SPopRenwuPresenter();
        popRenwuPresenter.onCreate(this);

    }

    private void select_useful(TextView tv, int drawabless) {
        Drawable drawable = getResources().getDrawable(drawabless);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        tv.setCompoundDrawables(null, null, drawable, null);
    }

    private void select_useful(TextView tv, int drawabless, int size) {
        Drawable drawable = getResources().getDrawable(drawabless);
        drawable.setBounds(0, 0, size, size);
        tv.setCompoundDrawables(null, null, drawable, null);
    }

    private void set_success() {
        mEmptyView.success();
        card_view1.setVisibility(View.VISIBLE);
        ll2.setVisibility(View.VISIBLE);
        ll_title1.setVisibility(View.VISIBLE);
    }

    private void set_error() {
        mEmptyView.errorNet();
        card_view1.setVisibility(View.GONE);
        ll2.setVisibility(View.GONE);
        ll_title1.setVisibility(View.GONE);
    }

    // 从缓存中取出bufen
    private List<SHuibenDetailBean2> chushihua_hashmap() {
        List<SHuibenDetailBean2> mList = new ArrayList<>();

        mGoodsList = HuibenFlieCacheBaseBean2Manager.getInstance().getListBean(EXTRA_ID);
        for (int i = 0; i < mGoodsList.size(); i++) {
//            Bitmap bitmap = HuibenImgOtherToBitmap.downloadUrlToBitmap();
            SHuibenDetailBean2 baseBean2 = new SHuibenDetailBean2();
            baseBean2.setBookId(mGoodsList.get(i).getId());
            baseBean2.setPic(mGoodsList.get(i).getUrl());
            baseBean2.setAudio(mGoodsList.get(i).getUrl_mp3());
            mList.add(baseBean2);
        }
        return mList;
    }

    // 写入缓存中的TXT 文件bufen
    public void huiben_txt(List<SHuibenDetailBean2> items) {

        for (int i = 0; i < items.size(); i++) {
            mGoodsList.add(new HuibenFileCacheBean(items.get(i).getId() + "", items.get(i).getPic(), items.get(i).getAudio()));
        }
        HuibenFlieCacheBaseBean2Manager.getInstance().AddHashMap(EXTRA_ID, mGoodsList);
        chushihua_hashmap();
    }

    /**
     * 视图缩小放大操作bufen
     */

    public void FL1(View view) {
        if (mVideoWidth < ScreenUtils.getScreenWidth() && mVideoHeight < ScreenUtils.getScreenHeight()) {
            if (current < 0) {
                return;
            }
            is_dianji = true;
//            vp.setPageTransformer(false, new AccordionTransformer2());
            // 进来是竖屏要设置为横屏
            set_hengshu(is_heng_shu);
            // 放大
            dianji_play();
            // 此为宠物中心 任务弹窗接口 废弃
//            popRenwuPresenter.getPopRenwuData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), EXTRA_ID, "start");
            if (current >= adapter.getCount() - 2) {
                vp.notifyDataSetChanged();
            }
        }
    }

    //放大
    private void dianji_play() {
        setlayout_big();
//        fl1.animate().scaleX(1.0f).scaleY(1.0f).start();
        card_view1.setUseCompatPadding(false);
        card_view1.setRadius(0f);
        set_isvis_seekbar(true);
        tv_play.setVisibility(View.GONE);
        iv_stop1.setVisibility(View.VISIBLE);
        tv_suo2.setVisibility(View.VISIBLE);
        ll2.setVisibility(View.VISIBLE);
        ll2.setBackgroundResource(R.drawable.footerbg22);
        tv_strings.setTextColor(ContextCompat.getColor(ReadBookActivity.this, R.color.white));
        rl20.setVisibility(View.VISIBLE);
        rl20.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent50));// grayCCCECECE
//        sb_progressbar.setProgressDrawable(getDrawable(R.drawable.books_bg_progress_white));
        Intent intent = new Intent(this, RbMusicPlayerService.class);
        intent.putExtra("flag", flag);
        bindService(intent, conn, BIND_AUTO_CREATE);
        //
        if (current == adapter.getCount() - 1) {
            if (is_dianji) {
                if (handler22 != null) {
                    handler22.removeCallbacks(runnable);
                }
            }
            tv_suo2.setVisibility(View.GONE);
            ll2.setVisibility(View.GONE);
            rl20.setVisibility(View.GONE);
            rl20.setBackgroundColor(ContextCompat.getColor(ReadBookActivity.this, R.color.transparent));// grayCCCECECE
        }
        //
        Drawable draw = getResources().getDrawable(R.drawable.books_bg_progress_white);
        Rect bounds = sb_progressbar.getProgressDrawable().getBounds();
        sb_progressbar.setProgressDrawable(draw);
        sb_progressbar.getProgressDrawable().setBounds(bounds);
    }

    //缩小
    private void dianji_onplay() {
        setlayout_small();
//        fl1.animate().scaleX(1.0f).scaleY(1.0f).start();
        card_view1.setUseCompatPadding(true);
        card_view1.setRadius(16.0f);
        set_isvis_seekbar(false);
        tv_play.setVisibility(View.VISIBLE);
        iv_stop1.setVisibility(View.GONE);
        tv_suo2.setVisibility(View.GONE);
        ll2.setVisibility(View.VISIBLE);
        if (!ScreenUtils.isLandscape()) {
            ll2.setBackgroundResource(R.drawable.footerbg2);
        } else {
            ll2.setBackgroundResource(R.drawable.footerbg20);// 横屏会叠加圆角 不知道原因
        }
        tv_strings.setTextColor(ContextCompat.getColor(ReadBookActivity.this, R.color.black_000));
        rl20.setVisibility(View.VISIBLE);
        rl20.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));// grayCCCECECE
        Drawable draw = getResources().getDrawable(R.drawable.books_bg_progress_black);
        Rect bounds = sb_progressbar.getProgressDrawable().getBounds();
        sb_progressbar.setProgressDrawable(draw);
        sb_progressbar.getProgressDrawable().setBounds(bounds);
//        tv_home.setVisibility(View.VISIBLE);
//        tv_share.setVisibility(View.VISIBLE);
//        tv_down.setVisibility(View.VISIBLE);
        if (handler22 != null) {
            handler22.removeCallbacks(runnable);
        }
        if (mBinder != null && mBinder.isService()) {
            mBinder.musicDestroy();
            unbindService(conn);
            stopService(new Intent(this, RbMusicPlayerService.class));
            mBinder = null;
        }
        // 这里判断stopmusic的按钮
        if (!is_setmusicplay) {
            SPUtils.getInstance().put("stopmusic", true);
        }
    }

    //屏幕方向发生改变的回调方法
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e("--屏幕方向--", "当前屏幕为横屏");
            vp.notifyDataSetChanged();
        } else {
            Log.e("--屏幕方向--", "当前屏幕为竖屏");
            vp.notifyDataSetChanged();
        }
        super.onConfigurationChanged(newConfig);
        Log.e("TAG", "onConfigurationChanged");
        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  //设置横屏
    }

    // 横竖屏bufen
    private void set_hengshu(boolean is_hs) {
        if (is_hs) {
            // 竖屏
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            // 横屏
            getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

    }

    private void scaleWindow(View view) {
        /** 设置缩放动画 */
        final ScaleAnimation animation = new ScaleAnimation(1f, 0.7f, 1f, 0.6f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);// 从相对于自身0.5倍的位置开始缩放，也就是从控件的位置缩放
        animation.setDuration(1000);//设置动画持续时间

        /** 常用方法 */
        //animation.setRepeatCount(int repeatCount);//设置重复次数
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        //animation.setStartOffset(long startOffset);//执行前的等待时间
        view.setAnimation(animation);
        /** 开始动画 */
        animation.startNow();
    }

    private void setlayout_small() {
//        scaleWindow(fl1);
        ConstraintLayout.LayoutParams ll_param = (ConstraintLayout.LayoutParams) fl1.getLayoutParams();
        Double w = (ScreenUtils.getScreenWidth() * 0.8);
        Double h = (ScreenUtils.getScreenHeight() * 0.67);
        ll_param.width = w.intValue();
        ll_param.height = h.intValue();

        mVideoWidth = ll_param.width;
        mVideoHeight = ll_param.height;
        fl1.setLayoutParams(ll_param);
        //
//        if (ScreenUtils.isLandscape()) {
//            AutoSize.autoConvertDensityOfGlobal(ReadBookActivity.this); //如果没有自定义需求用这个方法
//            AutoSize.autoConvertDensity(ReadBookActivity.this, 375, true); //如果有自定义需求就用这个方法
//        } else {
//            AutoSize.autoConvertDensityOfGlobal(ReadBookActivity.this); //如果没有自定义需求用这个方法
//            AutoSize.autoConvertDensity((Activity) ReadBookActivity.this, 375, true);// 667
//        }
    }

    @Override
    public Resources getResources() {
        //需要升级到 v1.1.2 及以上版本才能使用 AutoSizeCompat
        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()));//如果没有自定义需求用这个方法
        AutoSizeCompat.autoConvertDensity((super.getResources()), 667, false);//如果有自定义需求就用这个方法
        return super.getResources();
    }

    private void setlayout_big() {
//        scaleWindow(fl1);
        ConstraintLayout.LayoutParams ll_param = (ConstraintLayout.LayoutParams) fl1.getLayoutParams();
        ll_param.width = FrameLayout.LayoutParams.WRAP_CONTENT;
        ll_param.height = FrameLayout.LayoutParams.MATCH_PARENT;

//        Double w = new Double((ScreenUtils.getScreenWidth()));
//        Double h = new Double((ScreenUtils.getScreenHeight()));
        Double w = (double) (ScreenUtils.getScreenWidth());
        Double h = (double) (ScreenUtils.getScreenHeight());
        mVideoWidth = w.intValue();
        mVideoHeight = h.intValue();
        fl1.setLayoutParams(ll_param);
    }

    private ReadBookObservable readBookObservable;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (RbMusicPlayerService.MyBinder) service;
            readBookObservable = ((RbMusicPlayerService.MyBinder) service).getMyObservable();
            readBookObservable.addObserver(ReadBookActivity.this);
            flag = true;
            mBinder.getService().setOnPrepared(pcb);
            //
            ArrayList<SLB4CategoryListDetailBean1Temp> list11 = new ArrayList<>();
            ArrayList<String> list12 = new ArrayList<>();
            for (SHuibenDetailBean2 bean : items) {
                list11.add(new SLB4CategoryListDetailBean1Temp(bean.getAudio(), bean.getDltName(), bean.getBookItemId(), bean.getBookId(), "", "", ""));
                list12.add(bean.getAudio());
            }
            mBinder.getService().setMusicListbean(list11);
            mBinder.getService().setMusicList(list12);
            mBinder.getService().setCurrent(current);
            if (!is_first2) {
                is_first2 = true;
                SPUtils.getInstance().put(RbMusicPlayerService.TAG_EXTRA_ID_FIRST, EXTRA_ID);
                SPUtils.getInstance().put(RbMusicPlayerService.TAG_EXTRA_ID_ZONG_FIRST, EXTRA_ID_ZONG);
//                mBinder.getService().setEXTRA_ID_FIRST(EXTRA_ID);
//                mBinder.getService().setEXTRA_ID_ZONG_FIRST(EXTRA_ID_ZONG);
            }
            mBinder.getService().setNext_id(EXTRA_ID);
            mBinder.getService().setEXTRA_ID_ZONG(EXTRA_ID_ZONG);
            mBinder.getService().setEXTRA_NAME(EXTRA_NAME);
            // 如果缩小状态下点击列表bufen
            if (tj_bean != null) {
//                mBinder.getService().setToId(tj_bean.getBookItemId());
                // end now
                Intent it = new Intent(ReadBookActivity.this, UpdataCommonservices.class);
                it.setAction(UpdataCommonservices.HUIBEN_READINGTIME_ACTION);
                it.putExtra(UpdataCommonservices.id_zong, EXTRA_ID_ZONG);
                it.putExtra(UpdataCommonservices.id, EXTRA_ID);
                it.putExtra(UpdataCommonservices.type, UpdataCommonservices.type2);
                it.putExtra(UpdataCommonservices.sourceType, UpdataCommonservices.sourceType2);
                it.putExtra(UpdataCommonservices.time, System.currentTimeMillis() + "");
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    startForegroundService(it);
//                } else {
//                    // Pre-O behavior.
//                    startService(it);
//                }
                startService(it);
                mBinder.getService().setToId22(tj_bean.getBookId(), tj_bean.getBookItemId(), tj_bean.getBookName());
                MyLogUtil.e("--geek11122--", tj_bean.getBookItemId() + "");
                tj_bean = null;
                return;
            }
            //  如果缩小状态下下一本
            if (is_next_click) {
                MyLogUtil.e("--geek11122--", EXTRA_ID + "");
                // end now
                Intent it = new Intent(ReadBookActivity.this, UpdataCommonservices.class);
                it.setAction(UpdataCommonservices.HUIBEN_READINGTIME_ACTION);
                it.putExtra(UpdataCommonservices.id_zong, EXTRA_ID_ZONG);
                it.putExtra(UpdataCommonservices.id, EXTRA_ID);
                it.putExtra(UpdataCommonservices.type, UpdataCommonservices.type2);
                it.putExtra(UpdataCommonservices.sourceType, UpdataCommonservices.sourceType2);
                it.putExtra(UpdataCommonservices.time, System.currentTimeMillis() + "");
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    startForegroundService(it);
//                } else {
//                    // Pre-O behavior.
//                    startService(it);
//                }
                startService(it);
//                mBinder.getService().setToId(next_id);
                mBinder.getService().setToId2(EXTRA_ID_ZONG, EXTRA_ID, EXTRA_NAME);
//                mBinder.getService().setNext_id(next_id);
//                if (TextUtils.isEmpty(mBinder.getService().getNext_id())) {
//                    mBinder.getService().setToId(next_id);
//                } else {
//                    mBinder.getService().setToId(mBinder.getService().getNext_id());
//                }
                is_next_click = false;
                return;
            }
            // 如果缩小状态重播
            if (is_replay_click) {
                current = 0;
                is_setmusicplay = true;
                iv_stop1.setImageResource(R.drawable.slb_playrb22);
                vp.setCurrentItem(current);
                is_replay_click = false;
                return;
            }
            // 音乐bufen
            if (flag) {
//                mBinder.musicStart(ReadBookActivity.this, "mp3/" + adapter.getContacts(current).getId() + ".mp3");
                // wangluo
                if (!TextUtils.isEmpty(adapter.getContacts(current).getAudio())) {
                    if (is_setmusicplay) {
                        mBinder.musicStart(ReadBookActivity.this, adapter.getContacts(current).getAudio());
                    }
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private PreparedCallBack pcb = new PreparedCallBack() {
        @Override
        public void isPrepared(boolean prepared) {
            if (!prepared) {
                //next
                if (current + 1 > adapter.getCount() - 1) {
//                    ToastUtil.showToastLong("最后一页");

                    //
//                    ToastUtils.setView(R.layout.toast_custom_view);
//                    if (ScreenUtils.isLandscape()) {
//                        ((TextView) ToastUtils.getToast().getView().findViewById(R.id.tv_message)).setTextSize(20);
//                    } else {
//                        ((TextView) ToastUtils.getToast().getView().findViewById(R.id.tv_message)).setTextSize(10);
//                    }
//                    ToastUtils.setGravity(Gravity.BOTTOM, 0, 20);
//                    ToastUtils.show("恭喜你，阅读完了一本绘本~");
//                    Toasty.normal(getApplicationContext(), "恭喜你，阅读完了一本绘本~").show();
                    return;
                }
                SHuibenDetailBean2 model = adapter.getNext(current);
                // 根据需求这里如果设置了暂停就不会自动滚动 需要手动翻页
                if (is_setmusicplay) {
                    vp.setCurrentItem(current + 1);
                }
            }
        }
    };

    /**
     * 图片点击回到控制页面的进度条显示
     */
    @Override
    public void onImgClick(int action) {
        if (!is_dianji) {
            return;
        }
        // 锁屏
        if (is_suo) {
            if (tv_suo2.getVisibility() == View.VISIBLE) {
                suoping1();
            } else {
                suoping2();
            }
            return;
        }
        // 功能栏
        if (rl20.getVisibility() == View.VISIBLE) {
            set_isvis_seekbar(false);
        } else {
            set_isvis_seekbar(true);
        }
    }

    // 设置锁屏1bufen
    private void suoping1() {
        tv_suo2.setVisibility(View.GONE);
        if (handler33 != null) {
            handler33.removeCallbacks(runnable33);
        }
    }

    // 设置锁屏2bufen
    private void suoping2() {
        tv_suo2.setVisibility(View.VISIBLE);
        handler33.removeCallbacks(runnable33);
        handler33.postDelayed(runnable33, TIME_SETTING_LAYOUT);
    }

    /**
     * 设置seekbar全屏状态下显示和隐藏2bufen
     */
    private void set_isvis_seekbar(boolean is_vis) {
        if (is_vis) {
            // 显示5秒后隐藏
            rl20.setVisibility(View.VISIBLE);
            rl20.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent50));// grayCCCECECE
            ll2.setVisibility(View.VISIBLE);
            tv_suo2.setVisibility(View.VISIBLE);
            handler22.removeCallbacks(runnable);
            handler22.postDelayed(runnable, TIME_SETTING_LAYOUT);

        } else {
            // 隐藏
            if (is_heng_shu) {
                getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);// topbar
            } else {
//                getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);// topbar
            }
            rl20.setVisibility(View.GONE);
            rl20.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent50));// grayCCCECECE
            ll2.setVisibility(View.GONE);
            tv_suo2.setVisibility(View.GONE);

        }
        //虚拟键
        if (NavigationBarUtil.hasNavigationBar(this)) {
//            NavigationBarUtil.initActivity(getWindow().getDecorView());
            NavigationBarUtil.hideBottomUIMenu(this);
        }
    }

    private Handler handler22;
    private Handler handler33;
    //    private boolean isontouching;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            if (isontouching) {
//                return;
//            }
            if (is_heng_shu) {
                getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);// topbar
            } else {
//                getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);// topbar
            }
            rl20.setVisibility(View.GONE);
            ll2.setVisibility(View.GONE);
            tv_suo2.setVisibility(View.GONE);
//            iv_stop1.setVisibility(View.VISIBLE);
//            ll2.setBackgroundResource(R.drawable.footerbg22);
//            tv_strings.setTextColor(ContextCompat.getColor(ReadBookActivity.this, R.color.white));
//            sb_progressbar.setProgressDrawable(getDrawable(R.drawable.books_bg_progress_white));

        }
    };
    private Runnable runnable33 = new Runnable() {
        @Override
        public void run() {
            tv_suo2.setVisibility(View.GONE);
        }
    };

    // 如果是大就变小bufen
    private void onBackpress() {
        if (is_dianji) {
            //缩小动画
            is_dianji = false;
            set_hengshu(true);
            // 缩小
            dianji_onplay();
        }
    }

    /**
     * ------------------分享-------------------
     */
    /**
     * 其他方式分享 - 微信
     */
    private void qita_wx_share() {
        Glide.with(this).asBitmap().load(JPushShareUtils.share_imageurl).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                JShareInterface.share("Wechat",
                        WeixinBeanParam.share_web2(
                                JPushShareUtils.share_title,
                                JPushShareUtils.share_text,
                                JPushShareUtils.share_url,
                                resource),
                        jPushShareUtils.mShareListener1);
            }
        });
//        JShareInterface.share("Wechat",
//                WeixinBeanParam.share_web2(
//                        jPushShareUtils.share_title,
//                        jPushShareUtils.share_text,
//                        jPushShareUtils.share_url,
//                        jPushShareUtils.drawableToBitmap(ReadBookActivity.this, getDrawable(R.drawable.geek_icon))),
//                jPushShareUtils.mShareListener1);
    }

    @Override
    public void onResults(String platform, String toastMsg, String data) {
        Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_LONG).show();
//        finish();
    }

    /**
     * ------------------下载绘本bufen----------------
     */

    // 下载绘本bufen
    private void qita_down() {
        // 保存列表
        // 1.取出缓存txt文件信息
        // 2.修改mp3的地址为本地
        // 3.保存为TXT_ID的副本下次进来检查是否缓存过来读取文件信息
        List<String> mList_url = new ArrayList<>();
        mGoodsList = HuibenFlieCacheBaseBean2Manager.getInstance().getListBean(EXTRA_ID);
        for (int i = 0; i < mGoodsList.size(); i++) {
            mList_url.add(mGoodsList.get(i).getUrl_mp3());
        }
        // 下载bufen
        String file_url = ConstantsUtils.file_url_mp3_huiben + EXTRA_ID + ConstantsUtils.FILE_SEP;
        if (FileUtils.createOrExistsDir(file_url)) {
            DownHuibenUtils.getInstance().start(this, mGoodsList, mList_url, file_url, downLoaderCallBack);
        }
    }

    private DownHuibenUtils.FileDownLoaderCallBack downLoaderCallBack = new DownHuibenUtils.FileDownLoaderCallBack() {
        @Override
        public void downLoadComplated(BaseDownloadTask task) {
            Log.e("------taskPath---", task.getTargetFilePath());
            Log.e("------taskFilename---", task.getFilename());
            HuibenFileCacheBean bean = (HuibenFileCacheBean) task.getTag();
            bean.setUrl_mp3(task.getTargetFilePath());
            if (bean.getId().equals(mGoodsList.get(mGoodsList.size() - 1).getId())) {
                down_finish();
            }
        }

        @Override
        public void downLoadWarn(BaseDownloadTask task) {
            Log.e("------warn---", "warn");
            Toasty.normal(ReadBookActivity.this, "已在下载队列中").show();
            while (task.getSmallFileSoFarBytes() != task.getSmallFileTotalBytes()) {
                int percent = (int) ((double) task.getSmallFileSoFarBytes() / (double) task.getSmallFileTotalBytes() * 100);
//                textView.setText("("+percent+"%"+")");
            }
        }

        @Override
        public void downLoadError(BaseDownloadTask task, Throwable e) {
            Log.e("------error---", e.toString());
        }

        @Override
        public void downLoadProgress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            int percent = (int) ((double) soFarBytes / (double) totalBytes * 100);
//            textView.setText("("+percent+"%"+")");
            Log.e("------progress---", "");
        }
    };

    // 下载完成后要改变的状态bufen
    private void down_finish() {
        //
        tv_down.setBackgroundResource(R.drawable.slbdowned);
        SPUtils.getInstance().put(EXTRA_ID, EXTRA_ID);// 为了后面判断是否下载过绘本 将绘本id存着
        // 保存bean
        mList_huancun = DownManager.getInstance(getApplicationContext()).getListBean(CommonUtils.TXTID_BEAN);
        if (mList_huancun.size() == 0) {
            ArrayList<HuibenFileCacheBeanItem> mList1 = new ArrayList<>();
            mList1.add(new HuibenFileCacheBeanItem(EXTRA_ID, EXTRA_NAME, TXT_URL, "", is_heng_shu, is_shoucang, source_type));
            DownManager.getInstance(getApplicationContext()).AddHashMap(CommonUtils.TXTID_BEAN, mList1);
        } else {
            mList_huancun.add(new HuibenFileCacheBeanItem(EXTRA_ID, EXTRA_NAME, TXT_URL, "", is_heng_shu, is_shoucang, source_type));
            DownManager.getInstance(getApplicationContext()).AddHashMap(CommonUtils.TXTID_BEAN, mList_huancun);
        }
        Toasty.normal(ReadBookActivity.this, "已下载完成").show();
    }

    // 暂停bufen
    private boolean is_setmusicplay = true;// true 能自动滚动播放 false 不能自动滚动播放
    private int is_setmusicplay_current = -2;//

    private void qita_stop_music() {
//        if (SPUtils.getInstance().getInt(CommonUtils.READBOOK_AUTOBUTTON, -1) == 1) {
//            Toasty.normal(ReadBookActivity.this, "当前为自动连播模式请关闭后重试").show();
//            return;
//        }
        is_setmusicplay_current = current;
        if (mBinder != null && mBinder.getService() != null && mBinder.getService().getmPlayer().isPlaying()) {
            // 关了
            is_setmusicplay = false;
            iv_stop1.setImageResource(R.drawable.slb_playrb2);
            mBinder.musicPause();
        } else if (mBinder != null && mBinder.getService() != null && !mBinder.getService().getmPlayer().isPlaying()) {
            // 打开
            is_setmusicplay = true;
            iv_stop1.setImageResource(R.drawable.slb_playrb22);
            if (SPUtils.getInstance().getBoolean("stopmusic", false)) {
                mBinder.musicNext(ReadBookActivity.this, adapter.getContacts(current).getAudio());
            } else {
                mBinder.musicContinue();
                SPUtils.getInstance().put("stopmusic", false);
            }
        }
    }

    // 点击收藏bufen
    @Override
    public void OnLB1SaveFavoritesSuccess(SLB1SaveFavoritesBean s) {
        Toasty.normal(ReadBookActivity.this, s.getMsg()).show();
    }

    @Override
    public void OnLB1SaveFavoritesNodata(SLB1SaveFavoritesBean s) {
        Toasty.normal(ReadBookActivity.this, s.getMsg()).show();
    }

    @Override
    public void OnLB1SaveFavoritesFail(String s) {
        Toasty.normal(ReadBookActivity.this, s).show();
    }

    // 如果是横屏先退出小的再弹出窗
    private void set_pop_small() {
//        if (ScreenUtils.isLandscape()) {
//            AutoSize.autoConvertDensityOfGlobal(ReadBookActivity.this); //如果没有自定义需求用这个方法
//            AutoSize.autoConvertDensity(ReadBookActivity.this, 375, false); //如果有自定义需求就用这个方法
//        } else {
//            AutoSize.autoConvertDensityOfGlobal(ReadBookActivity.this); //如果没有自定义需求用这个方法
//            AutoSize.autoConvertDensity((Activity) ReadBookActivity.this, 375, true);// 667
//        }
        onBackpress();
    }

    // 恭喜任务完成bufen
    private PopRenwuPay popRenwuPay;

    private void get_renwu_pop(SPopRenwuBean1 sPopRenwuBean) {
        // 恭喜读完弹窗
        popRenwuPay = new PopRenwuPay(ReadBookActivity.this, sPopRenwuBean, new PopRenwuPay.OnFinishResultClickListener() {
            @Override
            public void onResult(String jumpUrl) {
                //
                popRenwuPay.dismiss();
                if (TextUtils.equals(SPopRenwuPresenter.banben, "测试")) {
                    HiosHelper.resolveAd(ReadBookActivity.this, ReadBookActivity.this, "http://aic.sairobo.cn:8087/petcenter/");
                } else if (TextUtils.equals(SPopRenwuPresenter.banben, "预生产")) {
                    HiosHelper.resolveAd(ReadBookActivity.this, ReadBookActivity.this, "http://aic.sairobo.cn:8087/petcenter/");
                } else if (TextUtils.equals(SPopRenwuPresenter.banben, "线上")) {
                    HiosHelper.resolveAd(ReadBookActivity.this, ReadBookActivity.this, "http://aic.sairobo.cn:8087/petcenter/");
                }
//                finish();
            }
        });
//        popRenwuPay.showAtLocation(ReadBookActivity.this.getWindow().getDecorView(),
//                Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
//                , 0, 0); // 设置layout在PopupWindow中显示的位置
    }

    // 试读完成弹窗bufen
    private PopPayForVip2 popShiduPay2;

    private void get_shidu_pop2() {
        popShiduPay2 = new PopPayForVip2(this, R.drawable.slb_popup_shidu3, getResources().getString(R.string.readbooks_tips2), new PopPayForVip2.OnFinishResultClickListener() {
            @Override
            public void onKaitonghuiyuan() {
                // 开通会员
                SlbLoginUtil2.get().loginTowhere(ReadBookActivity.this, new Runnable() {
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
                SlbLoginUtil2.get().loginTowhere(ReadBookActivity.this, new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent("hs.act.slbapp.OrderPayActivity");
                        SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, CommonUtils.HUIBEN_PAYSUCCESS_TAG2);
                        intent.putExtra(CommonUtils.HUIBEN_IDS, EXTRA_ID);
                        intent.putExtra(CommonUtils.HUIBEN_IDS_ZONG, EXTRA_ID_ZONG);
                        intent.putExtra(CommonUtils.HUIBEN_IDS_sourceType, source_type);
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

    private void get_shidu_pop3() {
        popShiduPay3 = new PopPayForVip3(this, R.drawable.slb_popup_shidu3, new PopPayForVip3.OnFinishResultClickListener() {
            @Override
            public void onKaitonghuiyuan() {
                // 开通会员
                SlbLoginUtil2.get().loginTowhere(ReadBookActivity.this, new Runnable() {
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
                SlbLoginUtil2.get().loginTowhere(ReadBookActivity.this, new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent("hs.act.slbapp.OrderPayActivity");
                        SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, CommonUtils.HUIBEN_PAYSUCCESS_TAG2);
                        intent.putExtra(CommonUtils.HUIBEN_IDS, EXTRA_ID);
                        intent.putExtra(CommonUtils.HUIBEN_IDS_ZONG, EXTRA_ID_ZONG);
                        intent.putExtra(CommonUtils.HUIBEN_IDS_sourceType, source_type);
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

    // 试读完成弹窗bufen
    private PopForShare popSharePay;
    private String share_title = "";
    private String share_name = "";
    private String share_img = "";
    private String share_imgUrl = "";
    private String share_imgUrl2 = "";

    private void get_share_pop() {
        popSharePay = new PopForShare(this, share_title, share_name, share_img, share_imgUrl, share_imgUrl2, new PopForShare.OnFinishResultClickListener() {
            @Override
            public void onWeChat1() {
                // 微信小程序

            }

            @Override
            public void onWeChat2() {
                // 朋友圈

            }
        });

    }

    /**
     * ---------------------网络请求bufen------------------------
     */
    private SHuibenDetailBean sHuibenDetailBean1;
    private String next_id = "";

    // 绘本详情列表 音频+ 图片bufen
    @Override
    public void OnHuibenDetailComm1Success(SHuibenDetailBean sHuibenDetailBean) {
        ShowLoadingUtil.onDestory();
        sHuibenDetailBean1 = sHuibenDetailBean;
//        next_id = sHuibenDetailBean.getNextBookItem().getBookItemId();
        if (sHuibenDetailBean.getShareInfo() != null) {
            share_title = sHuibenDetailBean.getShareInfo().getTitle();
//            share_title = EXTRA_NAME;
            share_name = sHuibenDetailBean.getShareInfo().getDesc();
//            share_name = EXTRA_NAME;
            share_img = sHuibenDetailBean.getShareInfo().getImgUrl();
            share_imgUrl = sHuibenDetailBean.getShareInfo().getUrlForFriend();
            share_imgUrl2 = sHuibenDetailBean.getShareInfo().getUrlForMoments();
        }
        items = sHuibenDetailBean.getBookItemDetails();
        if (SPUtils.getInstance().getString(EXTRA_ID).equals(EXTRA_ID)) {
            // 未联网
            set_success();
            adapter.setData(chushihua_hashmap());// 拿出缓存bufen
            vp.setAdapter(adapter);
            if (chushihua_hashmap().size() == 0) {
                set_error();
            }
        } else {
            if (items.size() == 0) {
                set_error();
            } else {
                // 联网
                set_success();
                huiben_txt(items);//写入缓存bufen
                adapter.setData(items);// 网络
                vp.setAdapter(adapter);

            }
        }
        // 不需要存本地的字段bufen
        if (sHuibenDetailBean.getBookItem().getReadRight().equals(is_vip_content2) || sHuibenDetailBean1.getBookItem().getReadRight().equals(is_vip_content3) || sHuibenDetailBean.getBookItem().getReadRight().equals(is_vip_content4)) {
            is_vip = true;
            is_vip_tag = sHuibenDetailBean.getBookItem().getReadRight();
        } else {
            is_vip = false;
        }
        //
        mList_huancun = DownManager.getInstance(getApplicationContext()).getListBean(CommonUtils.TXTID_BEAN);
        if (mList_huancun != null && mList_huancun.size() > 0) {
            for (HuibenFileCacheBeanItem item : mList_huancun) {
                if (TextUtils.equals(item.getId(), EXTRA_ID)) {
                    tv_play.setVisibility(View.VISIBLE);
                    TXT_URL = item.getUrlImg();
                    is_heng_shu = item.isVertical();
                    source_type = item.getSource_type();
                    is_shoucang = item.isCollect();
                    if (is_shoucang) {
                        is_sc = true;
                        tv_sc1.setBackgroundResource(R.drawable.play_collectrb33);
                    } else {
                        is_sc = false;
                        tv_sc1.setBackgroundResource(R.drawable.play_collectrb3);
                    }
                    tv_strings.setText(getString(R.string.image_counts, 1, Integer.valueOf(sHuibenDetailBean1.getBookItem().getItemPages())));
                    sb_progressbar.setMax(adapter.getCount() - 1);
                    sb_progressbar.setProgress(0);
                }
            }
        } else {
            if (items.size() > 0) {
                tv_play.setVisibility(View.VISIBLE);
                TXT_URL = sHuibenDetailBean.getBookItem().getCoverImgA();
                is_heng_shu = sHuibenDetailBean.getBookItem().isVertical();
                source_type = sHuibenDetailBean.getBookItem().getSourceType();
                is_shoucang = sHuibenDetailBean.getBookItem().isCollect();
                if (is_shoucang) {
                    is_sc = true;
                    tv_sc1.setBackgroundResource(R.drawable.play_collectrb33);
                } else {
                    is_sc = false;
                    tv_sc1.setBackgroundResource(R.drawable.play_collectrb3);
                }
                tv_strings.setText(getString(R.string.image_counts, 1, Integer.valueOf(sHuibenDetailBean1.getBookItem().getItemPages())));
                sb_progressbar.setMax(adapter.getCount() - 1);
                sb_progressbar.setProgress(0);
            }
        }

    }

    @Override
    public void OnHuibenDetailComm1Nodata(String s) {
        ShowLoadingUtil.onDestory();
        if (SPUtils.getInstance().getString(EXTRA_ID).equals(EXTRA_ID)) {
            // 未联网
            set_success();
            adapter.setData(chushihua_hashmap());// 拿出缓存bufen
            vp.setAdapter(adapter);
        } else {
            set_error();
        }
        mList_huancun = DownManager.getInstance(getApplicationContext()).getListBean(CommonUtils.TXTID_BEAN);
        if (mList_huancun != null && mList_huancun.size() > 0) {
            for (HuibenFileCacheBeanItem item : mList_huancun) {
                if (TextUtils.equals(item.getId(), EXTRA_ID)) {
                    tv_play.setVisibility(View.VISIBLE);
                    is_heng_shu = item.isVertical();
                    source_type = item.getSource_type();
                    is_shoucang = item.isCollect();
                    if (is_shoucang) {
                        is_sc = true;
                        tv_sc1.setBackgroundResource(R.drawable.play_collectrb33);
                    } else {
                        is_sc = false;
                        tv_sc1.setBackgroundResource(R.drawable.play_collectrb3);
                    }
                    tv_strings.setText(getString(R.string.image_counts, 1, Integer.valueOf(sHuibenDetailBean1.getBookItem().getItemPages())));
                    sb_progressbar.setMax(adapter.getCount() - 1);
                    sb_progressbar.setProgress(0);
                }
            }
        }
    }

    @Override
    public void OnHuibenDetailComm1Fail(String s) {
        ShowLoadingUtil.onDestory();
        if (SPUtils.getInstance().getString(EXTRA_ID).equals(EXTRA_ID)) {
            // 未联网
            mEmptyView.success();
            adapter.setData(chushihua_hashmap());// 拿出缓存bufen
            vp.setAdapter(adapter);
        } else {
            set_error();
        }
        mList_huancun = DownManager.getInstance(getApplicationContext()).getListBean(CommonUtils.TXTID_BEAN);
        if (mList_huancun != null && mList_huancun.size() > 0) {
            for (HuibenFileCacheBeanItem item : mList_huancun) {
                if (TextUtils.equals(item.getId(), EXTRA_ID)) {
                    tv_play.setVisibility(View.VISIBLE);
                    is_heng_shu = item.isVertical();
                    source_type = item.getSource_type();
                    is_shoucang = item.isCollect();
                    if (is_shoucang) {
                        is_sc = true;
                        tv_sc1.setBackgroundResource(R.drawable.play_collectrb33);
                    } else {
                        is_sc = false;
                        tv_sc1.setBackgroundResource(R.drawable.play_collectrb3);
                    }
                    tv_strings.setText(getString(R.string.image_counts, 1, Integer.valueOf(sHuibenDetailBean1.getBookItem().getItemPages())));
                    sb_progressbar.setMax(adapter.getCount() - 1);
                    sb_progressbar.setProgress(0);
                }
            }
        }
    }

    // 最后一个页面的列表bufen

    @Override
    public void OnHuibenDetailTuijianSuccess(SHuibenDetailTuijianBean sHuibenDetailTuijianBean) {
        tj_bean11 = sHuibenDetailTuijianBean;
        for (int i = 0; i < tj_bean11.getResult().size(); i++) {
//            EXTRA_ID = "32";
            if (TextUtils.equals(EXTRA_ID, tj_bean11.getResult().get(i).getBookItemId())) {
                if (i + 1 == tj_bean11.getResult().size()) {
                    next_id = "";
                } else {
                    next_id = tj_bean11.getResult().get(i + 1).getBookItemId();
                }
            }
        }

    }

    @Override
    public void OnHuibenDetailTuijianNodata(String s) {

    }

    @Override
    public void OnHuibenDetailTuijianFail(String s) {

    }

    // 任务bufen
    @Override
    public void OnPopRenwuSuccess(SPopRenwuBean sPopRenwuBean) {
        if (sPopRenwuBean.isCompNewTask()) {
            // 弹出多个
            // 如果是横屏先退出小的再弹出窗
            set_pop_small();
            for (int i = 0; i < sPopRenwuBean.getCompInfos().size(); i++) {
                get_renwu_pop(sPopRenwuBean.getCompInfos().get(i));
            }
        } else {
            // 无操作
        }
    }

    @Override
    public void OnPopRenwuNodata(String s) {

    }

    @Override
    public void OnPopRenwuFail(String s) {

    }


    /**
     * 监听音乐变化bufen
     */
    private MessageReadBooksMusicReceiverIndex mMessageReceiver;

    /**
     * 监听第三方音乐变化bufen
     */
    public class MessageReadBooksMusicReceiverIndex extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (CommonUtils.RB_broadcastreceiver.equals(intent.getAction())) {
                    // 切换Fragment bufen
                    qita_stop_music();
                }

            } catch (Exception e) {
            }
        }
    }
}
