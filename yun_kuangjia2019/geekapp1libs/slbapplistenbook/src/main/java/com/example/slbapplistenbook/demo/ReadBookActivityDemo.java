//package com.example.slbapplistenbook.demo;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.content.ComponentName;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.content.pm.ActivityInfo;
//import android.content.pm.PackageManager;
//import android.content.res.Configuration;
//import android.graphics.Bitmap;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.PowerManager;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.widget.CardView;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.blankj.utilcode.util.AppUtils;
//import com.blankj.utilcode.util.DeviceUtils;
//import com.blankj.utilcode.util.FileUtils;
//import com.blankj.utilcode.util.SPUtils;
//import com.blankj.utilcode.util.ScreenUtils;
//import com.blankj.utilcode.util.Utils;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.target.SimpleTarget;
//import com.bumptech.glide.request.transition.Transition;
//import com.example.biz3slbappshouye.bean.SHuibenDetailBean;
//import com.example.biz3slbappshouye.bean.SHuibenDetailBean2;
//import com.example.biz3slbappshouye.presenter.SHuibenDetailPresenter;
//import com.example.biz3slbappshouye.view.SHuibenDetailCommView;
//import com.example.biz3slbappusercenter.presenter.SSaveFavoritesPresenter;
//import com.example.biz3slbappusercenter.view.SSaveFavoritesView;
//import com.example.slbappcomm.CommonUtils;
//import com.example.slbappcomm.base.SlbBaseActivity;
//import com.example.slbappreadbook.pop.PopRenwuPay;
//import com.example.slbappcomm.pop.renwupay.payforvip.PopPayForVip2;
//import com.example.slbappcomm.pop.successpay.PopSuccessPay;
//import com.example.slbappreadbook.service.Updataservices;
//import com.example.slbappcomm.usersdk.SlbLoginUtil;
//import com.example.slbapplistenbook.R;
//import com.example.slbappreadbook.adapter.BasemusicAdapter2;
//import com.example.slbappreadbook.callback.ImgCallBack;
//import com.example.slbappreadbook.callback.PreparedCallBack;
//import com.example.slbappreadbook.callback.SeekBarListener;
//import com.example.slbappreadbook.domain.HuibenFileCacheBean;
//import com.example.slbappreadbook.domain.HuibenFileCacheBeanItem;
//import com.example.slbappreadbook.down.DownHuibenUtils;
//import com.example.slbappreadbook.huancun.DownManager;
//import com.example.slbappreadbook.huancun.HuibenFlieCacheBaseBean2Manager;
//import com.example.slbappreadbook.pagertransformer.AccordionTransformer2;
//import com.example.slbappreadbook.utils.HuibenListUtils;
//import com.example.slbappreadbook.utils.RingReadActControl;
//import com.example.slbappreadbook.widget.MyCoolViewPager;
//import com.example.slbappshare.fenxiang.JPushShareUtils;
//import com.example.slbappshare.fenxiang.OnShareResultInfoLitener;
//import com.example.slbappshare.fenxiang.beans.WeixinBeanParam;
//import com.haier.cellarette.baselibrary.assetsfitandroid.fileandroid.FitAndroidAssetsToCacheUtil;
//import com.haier.cellarette.baselibrary.assetsfitandroid.playlistmusic.MusicPlayerService;
//import com.haier.cellarette.baselibrary.common.ConstantsUtils;
//import com.haier.cellarette.baselibrary.emptyview.EmptyView;
//import com.haier.cellarette.baselibrary.likebutton.LikeButton;
//import com.haier.cellarette.baselibrary.likebutton.widgets.OnAnimationEndListener;
//import com.haier.cellarette.baselibrary.likebutton.widgets.OnLikeListener;
//import com.haier.cellarette.baselibrary.toasts2.Toasty;
//import com.haier.cellarette.baselibrary.widget.LxLinearLayout;
//import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;
//import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
//import com.haier.cellarette.libwebview.hois2.HiosHelper;
//import com.huanhailiuxin.coolviewpager.CoolViewPager;
//import com.liulishuo.filedownloader.BaseDownloadTask;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.jiguang.analytics.android.api.JAnalyticsInterface;
//import cn.jiguang.share.android.api.JShareInterface;
//import me.jessyan.autosize.AutoSize;
//
//public class ReadBookActivityDemo extends SlbBaseActivity implements ImgCallBack, OnShareResultInfoLitener, SHuibenDetailCommView, SSaveFavoritesView {
//
//    private int TIME_SETTING_LAYOUT = 6000;
//    private int current = 0;
//    private boolean is_first;
//    private MusicPlayerService.MyBinder mBinder;
//    private boolean flag;
//    private List<SHuibenDetailBean2> items;
//    private MyCoolViewPager vp;
//    private FrameLayout fl1;
//    private TextView tv_suo2;
//    private CardView card_view1;
//    private LinearLayout ll2;
//    //    private TextView tv_back;
//    private RelativeLayout rl20;
//    private LxLinearLayout ll_dianzan1;
//    private TextView tv_home;
//    private ImageView iv_stop1;
//    private LikeButton thumbButton;
//    private TextView tv_share;
//    private TextView tv_down;
//    private TextView tv_volume;
//    private RingReadActControl ringReadActControl;
//    private boolean is_vol;// true 为静音模式 false 为非
//    private TextView tv_play;
//    private SeekBar sb_progressbar;
//    private TextView tv_strings;
//    private BasemusicAdapter2 adapter;
//    private FrameLayout flindex1;
//    private EmptyView mEmptyView;
//
//    private int mVideoWidth;
//    private int mVideoHeight;
//    private boolean is_dianji;// 点击变大变小视图bufen
//    private boolean is_heng_shu;// 是否为横屏或竖屏bufen
//    private boolean is_shoucang;// 是否收藏bufen
//    private String EXTRA_ID = "";// 以商品的id为txt的名字
//    private String EXTRA_NAME = "";// 以商品的id为txt的名字
//    private String TXT_URL = "";// 以商品的id为txt的路径
//    private ArrayList<HuibenFileCacheBean> mGoodsList;// 保存路径ConstantsUtils.file_url_wenjian下的绘本txt
//    private ArrayList<HuibenFileCacheBeanItem> mList_huancun;// 保存路径下的一本绘本的bean的txt
//    private JPushShareUtils jPushShareUtils;
//
//    //
//    private SHuibenDetailPresenter presenter;
//    //
//    private SSaveFavoritesPresenter presenter1;
//    //
//    private PowerManager.WakeLock mWakeLock;
//    // 试读三页
//    private boolean is_vip;// false 继续读 true 只能看三页   0代表无需购买  1代表vip用户无需购买，2代表需购买
//
//    @Override
//    protected void onDestroy() {
////        ShowLoadingUtil.onDestory();
//
//        presenter.onDestory();
//        presenter1.onDestory();
//        JAnalyticsInterface.onPageEnd(this, "阿迷虎的故事");//this.getClass().getCanonicalName());
//        if (handler22 != null) {
//            handler22.removeCallbacks(runnable);
//        }
//        if (handler33 != null) {
//            handler33.removeCallbacks(runnable33);
//        }
//        jPushShareUtils.ondes();
//
//        // 写入缓存bufen
//        if (mGoodsList != null && mGoodsList.size() > 0) {
//            HuibenFlieCacheBaseBean2Manager.getInstance().AddHashMap(EXTRA_ID, mGoodsList);
//        }
//        if (downLoaderCallBack != null) {
//            DownHuibenUtils.getInstance().pause(downLoaderCallBack);
//        }
//        // 息屏
//        HuibenListUtils.getInstance(this).set_off_light(this);
//        // 统计页面关闭时间
//        Intent intent = new Intent(this, Updataservices.class);
//        intent.setAction(Updataservices.HUIBEN_READINGTIME_ACTION);
//        intent.putExtra(Updataservices.HUIBEN_READINGTIME, System.currentTimeMillis() + "");
//        startService(intent);
//        super.onDestroy();
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (is_dianji) {
//            //缩小动画
//            is_dianji = false;
//            set_hengshu(true);
//            // 缩小
//            dianji_onplay();
//            return;
//        }
//        super.onBackPressed();
//
//    }
//
//    private boolean is_suo = false;// 加锁
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (is_suo) {
//                suoping2();
//                return true;
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //虚拟键
//        if (NavigationBarUtil.hasNavigationBar(this)) {
////            NavigationBarUtil.initActivity(getWindow().getDecorView());
//            NavigationBarUtil.hideBottomUIMenu(this);
//        }
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (flag) {
//            if (mBinder != null && mBinder.isService()) {
////                mBinder.musicDestroy();
//                mBinder.getService().setIs_heng_shurb(is_heng_shu);
//                mBinder.getService().setIs_setmusicplayrb(is_setmusicplay);
//                mBinder.getService().setCurrentrb(current);
//                unbindService(conn);
////            stopService(new Intent(this, MusicPlayerService.class));
////            this.finish();
//            } else {
////            this.finish();
//            }
//            flag = false;
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        if (SPUtils.getInstance().getInt(CommonUtils.HUIBEN_PAYSUCCESS, -1) == CommonUtils.HUIBEN_PAYSUCCESS_TAG1) {
//            // VIP跳出层 支付成功
//            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, -1);
//            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS, -1);
//            setDataBean();
//
//        }
//        if (SPUtils.getInstance().getInt(CommonUtils.HUIBEN_PAYSUCCESS, -1) == CommonUtils.HUIBEN_PAYSUCCESS_TAG2) {
//            // 订单跳出层 支付成功
//            PopSuccessPay popSuccessPay = new PopSuccessPay(this);
//            popSuccessPay.showAtLocation(getWindow().getDecorView(),
//                    Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
//                    , 0, 0); // 设置layout在PopupWindow中显示的位置
//            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, -1);
//            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS, -1);
//            setDataBean();
//
//        }
//        super.onResume();
//        // 音量监听
//        ringReadActControl.ringStartListenSystemVol();
//
//    }
//
//
//    @Override
//    protected void onPause() {
//        // 统计页面置空时间
//        Intent intent = new Intent(this, Updataservices.class);
//        intent.setAction(Updataservices.HUIBEN_READINGTIME_ACTION);
//        intent.putExtra(Updataservices.HUIBEN_READINGTIME, System.currentTimeMillis() + "");
//        startService(intent);
//        super.onPause();
//        // 音量监听
//        ringReadActControl.ringFinishListenSystemVol();
//        // 切换前后台控制声音的播放bufen
//        AppUtils.registerAppStatusChangedListener(this, new Utils.OnAppStatusChangedListener() {
//            @Override
//            public void onForeground() {
//                // 前台
////                if (mBinder != null && mBinder.getService() != null && !mBinder.getService().getmPlayer().isPlaying()) {
////                    // 打开
//////                    is_setmusicplay = true;
//////                    iv_stop1.setImageResource(R.drawable.slb_playrb22);
////                    mBinder.musicContinue();
////                }
//            }
//
//            @Override
//            public void onBackground() {
//                // 后台
////                if (mBinder != null && mBinder.getService() != null && mBinder.getService().getmPlayer().isPlaying()) {
////                    // 关了
//////                    is_setmusicplay = false;
//////                    iv_stop1.setImageResource(R.drawable.slb_playrb2);
////                    mBinder.musicPause();
////                }
//            }
//        });
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_readbook;
//    }
//
//    @Override
//    protected void setup(@Nullable Bundle savedInstanceState) {
//        super.setup(savedInstanceState);
//        // 统计时间start bufen
//        JAnalyticsInterface.onPageStart(this, "阿迷虎的故事");//this.getClass().getCanonicalName());
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        findview();
//        onclick();
//        donetwork();
//        // 亮屏
//        HuibenListUtils.getInstance(this).set_on_light(this);
//        // 统计页面开始时间
//        Intent it = new Intent(this, Updataservices.class);
//        it.setAction(Updataservices.HUIBEN_READINGTIME_ACTION);
//        it.putExtra(Updataservices.HUIBEN_READINGTIME, System.currentTimeMillis());
//        startService(it);
//        // 音乐听书保存状态
//
//    }
//
//
//    private void findview() {
//        flindex1 = findViewById(R.id.flindex1);
//        mEmptyView = findViewById(R.id.empty_view);
//        mEmptyView.bind(fl1).setRetryListener(new EmptyView.RetryListener() {
//            @Override
//            public void retry() {
//                // 重试bufen
//                setDataBean();
//            }
//        });
//        vp = findViewById(R.id.vp);
//        tv_suo2 = findViewById(R.id.tv_suo2);
//        tv_strings = findViewById(R.id.tv_strings);
//        sb_progressbar = findViewById(R.id.sb_progressbar);
//        fl1 = findViewById(R.id.fl1);
//        card_view1 = findViewById(R.id.card_view1);
//        card_view1.setUseCompatPadding(true);
//        ll2 = findViewById(R.id.ll2);
////        tv_back = findViewById(R.id.tv_back);
//        rl20 = findViewById(R.id.rl20);
//        ll_dianzan1 = findViewById(R.id.ll_dianzan1);
//        tv_home = findViewById(R.id.tv_home);
//        tv_share = findViewById(R.id.tv_share);
//        tv_down = findViewById(R.id.tv_down);
//        tv_volume = findViewById(R.id.tv_volume);
//        ringReadActControl = new RingReadActControl(this, tv_volume, new Handler());
//        ringReadActControl.setViewOnClick2();
//        tv_volume.setOnClickListener(new OnMultiClickListener() {
//            @Override
//            public void onMultiClick(View v) {
//                // 设置静音模式bufen
////                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
////                        && !notificationManager.isNotificationPolicyAccessGranted()) {
////                    Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
////                    getApplicationContext().startActivity(intent);
////                    return;
////                }
//                if (ContextCompat.checkSelfPermission(ReadBookActivity.this, Manifest.permission.ACCESS_NOTIFICATION_POLICY) != PackageManager.PERMISSION_GRANTED) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        ActivityCompat.requestPermissions(ReadBookActivity.this, new String[]{Manifest.permission.ACCESS_NOTIFICATION_POLICY}, FitAndroidAssetsToCacheUtil.REQ_PERMISSION_CODE_SDCARD);
//                    }
//                } else {
//                    ringReadActControl.setViewOnClick();
//                }
//
//            }
//        });
//        tv_play = findViewById(R.id.tv_play);
//        iv_stop1 = findViewById(R.id.iv_stop1);
//        thumbButton = findViewById(R.id.thumb_button);
//
//        tv_play.setVisibility(View.GONE);
////        tv_back.setVisibility(View.GONE);
//        ll_dianzan1.setVisibility(View.VISIBLE);
//        rl20.setVisibility(View.VISIBLE);
//        rl20.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));// grayCCCECECE
////        tv_home.setVisibility(View.VISIBLE);
////        tv_share.setVisibility(View.VISIBLE);
////        tv_down.setVisibility(View.VISIBLE);
//        setlayout_small();
////        card_view1.setPreventCornerOverlap(true);
////        card_view1.setUseCompatPadding(true);
//        ll2.setVisibility(View.VISIBLE);
//        iv_stop1.setVisibility(View.GONE);
//        ll2.setBackgroundResource(R.drawable.footerbg2);
//        tv_strings.setText("");
//        tv_strings.setTextColor(ContextCompat.getColor(ReadBookActivity.this, R.color.black_000));
//
//        sb_progressbar.setProgressDrawable(getDrawable(R.drawable.books_bg_progress_black));
//        handler22 = new Handler();
//        handler33 = new Handler();
//
//        vp.setScrollMode(CoolViewPager.ScrollMode.HORIZONTAL);
//        vp.setAutoScroll(false);
//        vp.setAutoScrollDirection(CoolViewPager.AutoScrollDirection.BACKWARD);
//        vp.setInfiniteLoop(false);
//        vp.setScrollDuration(true, 600);
//        vp.setDrawEdgeEffect(true);
//        vp.setEdgeEffectColor(getResources().getColor(R.color.colorPrimary));
////        vp.toggleAutoScrollDirection();
////        vp.setPageTransformer(false, new ZoomOutTransformer2());
//        vp.setPageTransformer(false, new AccordionTransformer2());
//
//        items = new ArrayList<>();
//        adapter = new BasemusicAdapter2(ReadBookActivity.this, items, this);
//        vp.setAdapter(adapter);
//    }
//
//    private void onclick() {
//        tv_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        tv_suo2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 锁屏bufen
//                if (!is_suo) {
//                    is_suo = true;
//                    tv_suo2.setBackgroundResource(R.drawable.slb_suo2);
//                    // 锁屏
////                    // 功能栏
////                    set_isvis_seekbar(false);
//                    ll_dianzan1.setVisibility(View.GONE);
//                    rl20.setVisibility(View.GONE);
//                    ll2.setVisibility(View.GONE);
//                } else {
//                    is_suo = false;
//                    tv_suo2.setBackgroundResource(R.drawable.slb_suo1);
//                    if (handler33 != null) {
//                        handler33.removeCallbacks(runnable33);
//                    }
//                    // 解锁
//                    // 功能栏
//                    set_isvis_seekbar(true);
//
//                }
//            }
//        });
//        tv_share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 分享bufen
//                qita_wx_share();
//            }
//        });
//        iv_stop1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 暂停bufen
//                qita_stop_music();
//            }
//        });
//        tv_down.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 下载bufen
//                if (SPUtils.getInstance().getString(EXTRA_ID).equals(EXTRA_ID)) {
//                    Toasty.normal(ReadBookActivity.this, "已下载完成").show();
//                } else {
//                    qita_down();
//                }
//
//            }
//        });
//        if (SlbLoginUtil.get().isUserLogin()) {
//            //denglu
//            ll_dianzan1.setTouch(false);
//        } else {
//            // bu  denglu
//            ll_dianzan1.setTouch(true);
//        }
//        ll_dianzan1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //
//                onBackpress();
//                SlbLoginUtil.get().loginTowhere(ReadBookActivity.this, new Runnable() {
//                    @Override
//                    public void run() {
//                        presenter1.getSaveFavoritesData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), EXTRA_ID, is_shoucang);
//                        //denglu
//                        ll_dianzan1.setTouch(false);
//                        thumbButton.setLiked(!is_shoucang);
//
//                    }
//                });
//            }
//        });
//        thumbButton.setOnLikeListener(new OnLikeListener() {
//            @Override
//            public void liked(LikeButton likeButton) {
//                presenter1.getSaveFavoritesData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), EXTRA_ID, false);
//            }
//
//            @Override
//            public void unLiked(LikeButton likeButton) {
//                presenter1.getSaveFavoritesData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), EXTRA_ID, true);
//            }
//        });
//        thumbButton.setOnAnimationEndListener(new OnAnimationEndListener() {
//            @Override
//            public void onAnimationEnd(LikeButton likeButton) {
//                Log.e("----thumbButton----", "Animation End for %s" + likeButton);
//            }
//        });
////        tv_back.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                onBackPressed();
////            }
////        });
//        adapter.setLastView(new BasemusicAdapter2.OnLastPagerListener() {
//            @Override
//            public void OnResult(View view, int pos) {
//                if (view == null) {
//                    return;
//                }
//                RelativeLayout rl2 = view.findViewById(R.id.rl2);
//                RelativeLayout rllast1 = view.findViewById(R.id.rllast1);
//                TextView tv_share1 = view.findViewById(R.id.tv_share1);
//                TextView tv_re1 = view.findViewById(R.id.tv_re1);
//                TextView tv_next1 = view.findViewById(R.id.tv_next1);
//                //lastye
//                if (pos == adapter.getCount() - 1) {
//                    rl2.setVisibility(View.VISIBLE);
//                    rllast1.setVisibility(View.VISIBLE);
//                    tv_share1.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            set_pop_small();
//                            // 分享
//                            qita_wx_share();
//                        }
//                    });
//                    tv_re1.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            // 回到第一页
//                            current = 0;
//                            vp.setCurrentItem(current);
//                            if (mVideoWidth < ScreenUtils.getScreenWidth() && mVideoHeight < ScreenUtils.getScreenHeight()) {
//                                if (current < 0) {
//                                    return;
//                                }
//                                is_dianji = true;
////                                vp.setPageTransformer(false, new AccordionTransformer2());
//                                // 进来是竖屏要设置为横屏
//                                set_hengshu(is_heng_shu);
//                                dianji_play();
////                                view.postDelayed(new Runnable() {
////                                    @Override
////                                    public void run() {
////                                        dianji_play();
////                                    }
////                                }, 500);
//
//                            }
//                        }
//                    });
//                } else {
//                    rllast1.setVisibility(View.GONE);
//                    rl2.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//        vp.addOnPageChangeListener(new CoolViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//                MyLogUtil.e("----第几个pos--onPageScrolled--", i + "");
//                current = i;
//                SHuibenDetailBean2 model = adapter.getContacts(i);
//                //播放音乐bufen
//                if (!is_first) {
//                    is_first = true;
//                    return;
//                }
//                // 数据显示bufen
//                tv_strings.setText(getString(R.string.image_counts, current + 1, adapter.getCount()));
//                sb_progressbar.setProgress(current);
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                MyLogUtil.e("----第几个pos--onPageSelected--", i + "");
//                SHuibenDetailBean2 model = adapter.getContacts(i);
////                09-21 13:54:44.969 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged点击、滑屏
////                09-21 13:54:47.321 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged释放
////                09-21 13:54:48.326 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged无动作
////                09-21 13:54:51.440 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged点击、滑屏
////                09-21 13:54:51.600 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged释放
////                09-21 13:54:51.600 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageSelected
////                09-21 13:54:51.855 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->onPageScrollStateChanged无动作
////                09-21 13:54:51.855 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->instantiateItem
////                09-21 13:54:51.856 3830-3830/com.example.administrator.viewpager I/MainActivity: ---->destroyItem
//                // 音乐bufen
//                if (mBinder != null) {
////                        mBinder.musicNext(ReadBookActivity.this, "mp3/" + model.getId() + ".mp3");
//                    // wangluo
//                    // 这里根据需求加入判断如果开了bar就不继续读 手动翻页
//
//                    mBinder.musicNext(ReadBookActivity.this, adapter.getContacts(i).getAudio());
//                }
//                // 试读bufen
//                if ((Integer.valueOf(model.getId()) == adapter.getLastItem()) && is_vip) {
//                    // 如果是横屏先退出小的再弹出窗
//                    set_pop_small();
//                    get_shidu_pop();
//                }
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//                MyLogUtil.e("----第几个pos--onPageScrollStateChanged--", i + "");
//                switch (i) {
//                    case CoolViewPager.SCROLL_STATE_IDLE:
//                        //无动作、初始状态
//                        Log.i("--onPageChanged--", "---->onPageScrollStateChanged无动作");
//                        break;
//                    case CoolViewPager.SCROLL_STATE_DRAGGING:
//                        //点击、滑屏
//                        Log.i("--onPageChanged--", "---->onPageScrollStateChanged点击、滑屏");
//                        break;
//                    case CoolViewPager.SCROLL_STATE_SETTLING:
//                        //释放
//                        Log.i("--onPageChanged--", "---->onPageScrollStateChanged释放");
//                        // 音乐bufen
////                        if (mBinder != null) {
////                            mBinder.musicNext(ReadBookActivity.this, adapter.getContacts(i).getAudio());
////                        }
//                        break;
//                }
//            }
//        });
//        sb_progressbar.setOnSeekBarChangeListener(new SeekBarListener() {
//            @SuppressLint("LongLogTag")
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
////                int type = (int) seekBar.getTag();
//                Log.e("-seekbar-onProgressChanged--", "");
////                seekBar.setTag(progress);
//                if (fromUser) {
//                    vp.setCurrentItem(progress);
//                }
//            }
//
//            @SuppressLint("LongLogTag")
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                Log.e("-seekbar-onStartTrackingTouch--", "");
//                if (is_dianji) {
////                    isontouching = true;
//                    if (handler22 != null) {
//                        handler22.removeCallbacks(runnable);
//                    }
//                }
//
//            }
//
//            @SuppressLint("LongLogTag")
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.e("-seekbar-onStopTrackingTouch--", "");
//                if (is_dianji) {
////                    isontouching = false;
//                    set_isvis_seekbar(true);
//                }
//                //
////                int type = (int) seekBar.getTag();
////                if (is_dianji && mBinder != null && mBinder.getService().getmPlayer().isPlaying()) {
////                    mBinder.musicStop();
////                }
//
//            }
//        });
//
//    }
//
//    private void donetwork() {
////        ShowLoadingUtil.showLoading(this, "", null);
//        EXTRA_ID = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS);
//        EXTRA_NAME = getIntent().getStringExtra(CommonUtils.HUIBEN_TITLES);
//        if (SPUtils.getInstance().getString(EXTRA_ID).equals(EXTRA_ID)) {
//            tv_down.setBackgroundResource(R.drawable.slbdowned);// 下载了
//        } else {
//            tv_down.setBackgroundResource(R.drawable.slbdown);// 没下载
//        }
//        jPushShareUtils = new JPushShareUtils(this);
//        presenter = new SHuibenDetailPresenter();
//        presenter.onCreate(this);
//        presenter1 = new SSaveFavoritesPresenter();
//        presenter1.onCreate(this);
//        setDataBean();
//    }
//
//    private void setDataBean() {
//        mEmptyView.loading();
//        items = new ArrayList<>();
//        mGoodsList = new ArrayList<>();
//        mList_huancun = new ArrayList<>();
//        presenter.getHuibenDetailData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), EXTRA_ID);
//
//    }
//
//    // 设置跳转到哪一个页面services
//    private void set_which_pager(boolean rb_is_heng_shu, int rb_current, boolean rb_is_setmusicplay) {
//        // 视图放大操作 测试认为这里就是缩小进来 先放大再滚动到那一页 继续播放
//        if (mVideoWidth < ScreenUtils.getScreenWidth() && mVideoHeight < ScreenUtils.getScreenHeight()) {
//            if (rb_current > 0) {
//                is_dianji = true;
//                set_hengshu(rb_is_heng_shu);
//                dianji_play();
//            }
//        }
//        // 滚动到哪一页操作
////        if (rb_current + 1 > adapter.getCount() - 1) {
//////                    ToastUtil.showToastLong("最后一页");
////            return;
////        }
//        vp.setCurrentItem(rb_current);
//        SHuibenDetailBean2 model = adapter.getNext(current);
//        // 根据需求这里如果设置了暂停就不会自动滚动 需要手动翻页
////        if (rb_is_setmusicplay) {
////            vp.setCurrentItem(rb_current + 1);
////        }
//    }
//
//    /**
//     * ---------------------网络请求bufen------------------------
//     *
//     * @return
//     */
//
//    // 绘本详情列表 音频+ 图片bufen
//    @Override
//    public void OnHuibenDetailComm1Success(SHuibenDetailBean sHuibenDetailBean) {
//        items = sHuibenDetailBean.getBookItemDetails();
//        if (SPUtils.getInstance().getString(EXTRA_ID).equals(EXTRA_ID)) {
//            // 未联网
//            set_success();
//            adapter.setData(chushihua_hashmap());// 拿出缓存bufen
//            vp.setAdapter(adapter);
//            if (chushihua_hashmap().size() == 0) {
//                set_error();
//            }
//        } else {
//            if (items.size() == 0) {
//                set_error();
//            } else {
//                // 联网
//                set_success();
//                huiben_txt(items);//写入缓存bufen
//                adapter.setData(items);// 网络
//                vp.setAdapter(adapter);
//
//            }
//        }
//        // 不需要存本地的字段bufen
//        if (sHuibenDetailBean.getBookItem().getReadRight().equals("2")) {
//            is_vip = true;
//        } else {
//            is_vip = false;
//        }
//        //
//        mList_huancun = DownManager.getInstance().getListBean(CommonUtils.TXTID_BEAN);
//        if (mList_huancun != null && mList_huancun.size() > 0) {
//            for (HuibenFileCacheBeanItem item : mList_huancun) {
//                if (TextUtils.equals(item.getId(), EXTRA_ID)) {
//                    tv_play.setVisibility(View.VISIBLE);
//                    TXT_URL = item.getUrlImg();
//                    is_heng_shu = item.isVertical();
//                    is_shoucang = item.isCollect();
//                    thumbButton.setLiked(is_shoucang);
//                    tv_strings.setText(getString(R.string.image_counts, 1, adapter.getCount()));
//                    sb_progressbar.setMax(adapter.getCount() - 1);
//                    sb_progressbar.setProgress(0);
//                }
//            }
//        } else {
//            if (items.size() > 0) {
//                tv_play.setVisibility(View.VISIBLE);
//                TXT_URL = sHuibenDetailBean.getBookItem().getCoverImgA();
//                is_heng_shu = sHuibenDetailBean.getBookItem().isVertical();
//                is_shoucang = sHuibenDetailBean.getBookItem().isCollect();
//                thumbButton.setLiked(is_shoucang);
//                tv_strings.setText(getString(R.string.image_counts, 1, adapter.getCount()));
//                sb_progressbar.setMax(adapter.getCount() - 1);
//                sb_progressbar.setProgress(0);
//            }
//        }
//        //
//        if (!flag) {
//            Intent intent = new Intent(this, MusicPlayerService.class);
//            intent.putExtra("flag", flag);
//            bindService(intent, conn, BIND_AUTO_CREATE);
//        }
//    }
//
//    @Override
//    public void OnHuibenDetailComm1Nodata(String s) {
//        if (SPUtils.getInstance().getString(EXTRA_ID).equals(EXTRA_ID)) {
//            // 未联网
//            set_success();
//            adapter.setData(chushihua_hashmap());// 拿出缓存bufen
//            vp.setAdapter(adapter);
//        } else {
//            set_error();
//        }
//        mList_huancun = DownManager.getInstance().getListBean(CommonUtils.TXTID_BEAN);
//        if (mList_huancun != null && mList_huancun.size() > 0) {
//            for (HuibenFileCacheBeanItem item : mList_huancun) {
//                if (TextUtils.equals(item.getId(), EXTRA_ID)) {
//                    tv_play.setVisibility(View.VISIBLE);
//                    is_heng_shu = item.isVertical();
//                    is_shoucang = item.isCollect();
//                    thumbButton.setLiked(is_shoucang);
//                    tv_strings.setText(getString(R.string.image_counts, 1, adapter.getCount()));
//                    sb_progressbar.setMax(adapter.getCount() - 1);
//                    sb_progressbar.setProgress(0);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void OnHuibenDetailComm1Fail(String s) {
//        if (SPUtils.getInstance().getString(EXTRA_ID).equals(EXTRA_ID)) {
//            // 未联网
//            mEmptyView.success();
//            adapter.setData(chushihua_hashmap());// 拿出缓存bufen
//            vp.setAdapter(adapter);
//        } else {
//            set_error();
//        }
//        mList_huancun = DownManager.getInstance().getListBean(CommonUtils.TXTID_BEAN);
//        if (mList_huancun != null && mList_huancun.size() > 0) {
//            for (HuibenFileCacheBeanItem item : mList_huancun) {
//                if (TextUtils.equals(item.getId(), EXTRA_ID)) {
//                    tv_play.setVisibility(View.VISIBLE);
//                    is_heng_shu = item.isVertical();
//                    is_shoucang = item.isCollect();
//                    thumbButton.setLiked(is_shoucang);
//                    tv_strings.setText(getString(R.string.image_counts, 1, adapter.getCount()));
//                    sb_progressbar.setMax(adapter.getCount() - 1);
//                    sb_progressbar.setProgress(0);
//                }
//            }
//        }
//    }
//
//    private void set_success() {
//        mEmptyView.success();
//        card_view1.setVisibility(View.VISIBLE);
//        ll2.setVisibility(View.VISIBLE);
//    }
//
//    private void set_error() {
//        mEmptyView.errorNet();
//        card_view1.setVisibility(View.GONE);
//        ll2.setVisibility(View.GONE);
//    }
//
//    // 从缓存中取出bufen
//    private List<SHuibenDetailBean2> chushihua_hashmap() {
//        List<SHuibenDetailBean2> mList = new ArrayList<>();
//
//        mGoodsList = HuibenFlieCacheBaseBean2Manager.getInstance().getListBean(EXTRA_ID);
//        for (int i = 0; i < mGoodsList.size(); i++) {
////            Bitmap bitmap = HuibenImgOtherToBitmap.downloadUrlToBitmap();
//            SHuibenDetailBean2 baseBean2 = new SHuibenDetailBean2();
//            baseBean2.setBookId(mGoodsList.get(i).getId());
//            baseBean2.setPic(mGoodsList.get(i).getUrl());
//            baseBean2.setAudio(mGoodsList.get(i).getUrl_mp3());
//            mList.add(baseBean2);
//        }
//        return mList;
//    }
//
//    // 写入缓存中的TXT 文件bufen
//    public void huiben_txt(List<SHuibenDetailBean2> items) {
//
//        for (int i = 0; i < items.size(); i++) {
//            mGoodsList.add(new HuibenFileCacheBean(items.get(i).getId() + "", items.get(i).getPic(), items.get(i).getAudio()));
//        }
//        HuibenFlieCacheBaseBean2Manager.getInstance().AddHashMap(EXTRA_ID, mGoodsList);
//        chushihua_hashmap();
//    }
//
//
//    /**
//     * ------------------视图缩小放大操作bufen----------------
//     */
//
//    /**
//     * 视图缩小放大操作bufen
//     *
//     * @param view
//     */
//    public void FL1(View view) {
//        if (mVideoWidth < ScreenUtils.getScreenWidth() && mVideoHeight < ScreenUtils.getScreenHeight()) {
//            if (current < 0) {
//                return;
//            }
//            is_dianji = true;
////            vp.setPageTransformer(false, new AccordionTransformer2());
//            // 进来是竖屏要设置为横屏
//            set_hengshu(is_heng_shu);
//            // 放大
//            dianji_play();
//        }
//    }
//
//    //放大
//    private void dianji_play() {
//        setlayout_big();
////        fl1.animate().scaleX(1.0f).scaleY(1.0f).start();
//        card_view1.setUseCompatPadding(false);
//        set_isvis_seekbar(true);
//        tv_play.setVisibility(View.GONE);
//        iv_stop1.setVisibility(View.VISIBLE);
//        tv_suo2.setVisibility(View.VISIBLE);
//        ll2.setVisibility(View.VISIBLE);
//        ll2.setBackgroundResource(R.drawable.footerbg22);
//        tv_strings.setTextColor(ContextCompat.getColor(ReadBookActivity.this, R.color.white));
//        ll_dianzan1.setVisibility(View.VISIBLE);
//        rl20.setVisibility(View.VISIBLE);
//        rl20.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent50));// grayCCCECECE
//        sb_progressbar.setProgressDrawable(getDrawable(R.drawable.books_bg_progress_white));
//        // 音乐bufen
//        if (flag) {
//            if (current > 0) {
//                mBinder.musicNext(ReadBookActivity.this, adapter.getContacts(current).getAudio());
//            } else {
//                mBinder.musicStart(ReadBookActivity.this, adapter.getContacts(current).getAudio());
//            }
//        }
//    }
//
//    //缩小
//    private void dianji_onplay() {
//        setlayout_small();
////        fl1.animate().scaleX(1.0f).scaleY(1.0f).start();
//        card_view1.setUseCompatPadding(true);
//        set_isvis_seekbar(false);
//        tv_play.setVisibility(View.VISIBLE);
//        iv_stop1.setVisibility(View.GONE);
//        tv_suo2.setVisibility(View.GONE);
//        ll2.setVisibility(View.VISIBLE);
//        if (!ScreenUtils.isLandscape()) {
//            ll2.setBackgroundResource(R.drawable.footerbg2);
//        } else {
//            ll2.setBackgroundResource(R.drawable.footerbg20);// 横屏会叠加圆角 不知道原因
//        }
//        tv_strings.setTextColor(ContextCompat.getColor(ReadBookActivity.this, R.color.black_000));
//        ll_dianzan1.setVisibility(View.VISIBLE);
//        rl20.setVisibility(View.VISIBLE);
//        rl20.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));// grayCCCECECE
//        sb_progressbar.setProgressDrawable(getDrawable(R.drawable.books_bg_progress_black));
////        tv_home.setVisibility(View.VISIBLE);
////        tv_share.setVisibility(View.VISIBLE);
////        tv_down.setVisibility(View.VISIBLE);
//        if (handler22 != null) {
//            handler22.removeCallbacks(runnable);
//        }
////        if (mBinder != null && mBinder.isService()) {
////            mBinder.musicDestroy();
////            unbindService(conn);
//////            stopService(new Intent(this, MusicPlayerService.class));
////            mBinder = null;
////        }
//    }
//
//    //屏幕方向发生改变的回调方法
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Log.e("--屏幕方向--", "当前屏幕为横屏");
//            vp.notifyDataSetChanged();
//        } else {
//            Log.e("--屏幕方向--", "当前屏幕为竖屏");
//            vp.notifyDataSetChanged();
//        }
//        super.onConfigurationChanged(newConfig);
//        Log.e("TAG", "onConfigurationChanged");
//        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  //设置横屏
//    }
//
//    // 横竖屏bufen
//    private void set_hengshu(boolean is_hs) {
//        if (is_hs) {
//            // 竖屏
////            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        } else {
//            // 横屏
//            getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }
//
//    }
//
//    private void setlayout_small() {
//        FrameLayout.LayoutParams ll_param = (FrameLayout.LayoutParams) fl1.getLayoutParams();
//        Double w = new Double((ScreenUtils.getScreenWidth() * 0.7));
//        Double h = new Double((ScreenUtils.getScreenHeight() * 0.6));
//        ll_param.width = w.intValue();
//        ll_param.height = h.intValue();
//
//        mVideoWidth = ll_param.width;
//        mVideoHeight = ll_param.height;
//        fl1.setLayoutParams(ll_param);
//    }
//
//    private void setlayout_big() {
//        FrameLayout.LayoutParams ll_param = (FrameLayout.LayoutParams) fl1.getLayoutParams();
//        ll_param.width = FrameLayout.LayoutParams.WRAP_CONTENT;
//        ll_param.height = FrameLayout.LayoutParams.MATCH_PARENT;
//        Double w = new Double((ScreenUtils.getScreenWidth()));
//        Double h = new Double((ScreenUtils.getScreenHeight()));
//        mVideoWidth = w.intValue();
//        mVideoHeight = h.intValue();
//        fl1.setLayoutParams(ll_param);
//    }
//
//    private ServiceConnection conn = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            mBinder = (MusicPlayerService.MyBinder) service;
//            flag = true;
//            boolean is_heng_shurb = mBinder.getService().isIs_heng_shurb();
//            boolean is_setmusicplayrb = mBinder.getService().isIs_setmusicplayrb();
//            int currentrb = mBinder.getService().getCurrentrb();
//            current = currentrb;
//            set_which_pager(is_heng_shurb, currentrb, is_setmusicplayrb);
//
//            mBinder.getService().setOnPrepared(pcb);
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            flag = false;
//        }
//    };
//
//    private PreparedCallBack pcb = new PreparedCallBack() {
//        @Override
//        public void isPrepared(boolean prepared) {
//            if (!prepared) {
//                //next
//                if (current + 1 > adapter.getCount() - 1) {
////                    ToastUtil.showToastLong("最后一页");
//
//                    return;
//                }
//                SHuibenDetailBean2 model = adapter.getNext(current);
//                // 根据需求这里如果设置了暂停就不会自动滚动 需要手动翻页
//                if (is_setmusicplay) {
//                    vp.setCurrentItem(current + 1);
//                }
//
//            }
//        }
//    };
//
//    /**
//     * 图片点击回到控制页面的进度条显示
//     *
//     * @param action
//     */
//    @Override
//    public void onImgClick(int action) {
//        if (!is_dianji) {
//            return;
//        }
//        // 锁屏
//        if (is_suo) {
//            if (tv_suo2.getVisibility() == View.VISIBLE) {
//                suoping1();
//            } else {
//                suoping2();
//            }
//            return;
//        }
//        // 功能栏
//        if (rl20.getVisibility() == View.VISIBLE) {
//            set_isvis_seekbar(false);
//        } else {
//            set_isvis_seekbar(true);
//        }
//    }
//
//    // 设置锁屏1bufen
//    private void suoping1() {
//        tv_suo2.setVisibility(View.GONE);
//        if (handler33 != null) {
//            handler33.removeCallbacks(runnable33);
//        }
//    }
//
//    // 设置锁屏2bufen
//    private void suoping2() {
//        tv_suo2.setVisibility(View.VISIBLE);
//        handler33.removeCallbacks(runnable33);
//        handler33.postDelayed(runnable33, TIME_SETTING_LAYOUT);
//    }
//
//    /**
//     * 设置seekbar全屏状态下显示和隐藏2bufen
//     *
//     * @param is_vis
//     */
//    private void set_isvis_seekbar(boolean is_vis) {
//        if (is_vis) {
//            // 显示5秒后隐藏
//            ll_dianzan1.setVisibility(View.VISIBLE);
//            rl20.setVisibility(View.VISIBLE);
//            rl20.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent50));// grayCCCECECE
//            ll2.setVisibility(View.VISIBLE);
//            tv_suo2.setVisibility(View.VISIBLE);
//            handler22.removeCallbacks(runnable);
//            handler22.postDelayed(runnable, TIME_SETTING_LAYOUT);
//
//        } else {
//            // 隐藏
//            if (is_heng_shu) {
//                getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);// topbar
//            }
//            ll_dianzan1.setVisibility(View.GONE);
//            rl20.setVisibility(View.GONE);
//            rl20.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent50));// grayCCCECECE
//            ll2.setVisibility(View.GONE);
//            tv_suo2.setVisibility(View.GONE);
//
//        }
//        //虚拟键
//        if (NavigationBarUtil.hasNavigationBar(this)) {
////            NavigationBarUtil.initActivity(getWindow().getDecorView());
//            NavigationBarUtil.hideBottomUIMenu(this);
//        }
//    }
//
//    private Handler handler22;
//    private Handler handler33;
//    //    private boolean isontouching;
//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
////            if (isontouching) {
////                return;
////            }
//            if (is_heng_shu) {
//                getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);// topbar
//            }
//            ll_dianzan1.setVisibility(View.GONE);
//            rl20.setVisibility(View.GONE);
//            ll2.setVisibility(View.GONE);
//            tv_suo2.setVisibility(View.GONE);
////            iv_stop1.setVisibility(View.VISIBLE);
////            ll2.setBackgroundResource(R.drawable.footerbg22);
////            tv_strings.setTextColor(ContextCompat.getColor(ReadBookActivity.this, R.color.white));
////            sb_progressbar.setProgressDrawable(getDrawable(R.drawable.books_bg_progress_white));
//
//        }
//    };
//    private Runnable runnable33 = new Runnable() {
//        @Override
//        public void run() {
//            tv_suo2.setVisibility(View.GONE);
//        }
//    };
//
//    // 如果是大就变小bufen
//    private void onBackpress() {
//        if (is_dianji) {
//            //缩小动画
//            is_dianji = false;
//            set_hengshu(true);
//            // 缩小
//            dianji_onplay();
//        }
//    }
//
//    /**
//     * ------------------分享-------------------
//     */
//    /**
//     * 其他方式分享 - 微信
//     */
//    private void qita_wx_share() {
//        Glide.with(this).asBitmap().load(jPushShareUtils.share_imageurl).into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                JShareInterface.share("Wechat",
//                        WeixinBeanParam.share_web2(
//                                jPushShareUtils.share_title,
//                                jPushShareUtils.share_text,
//                                jPushShareUtils.share_url,
//                                resource),
//                        jPushShareUtils.mShareListener1);
//            }
//        });
////        JShareInterface.share("Wechat",
////                WeixinBeanParam.share_web2(
////                        jPushShareUtils.share_title,
////                        jPushShareUtils.share_text,
////                        jPushShareUtils.share_url,
////                        jPushShareUtils.drawableToBitmap(ReadBookActivity.this, getDrawable(R.drawable.geek_icon))),
////                jPushShareUtils.mShareListener1);
//    }
//
//    @Override
//    public void onResults(String platform, String toastMsg, String data) {
//        Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_LONG).show();
////        finish();
//    }
//
//    /**
//     * ------------------下载绘本bufen----------------
//     */
//
//    // 下载绘本bufen
//    private void qita_down() {
//        // 保存列表
//        // 1.取出缓存txt文件信息
//        // 2.修改mp3的地址为本地
//        // 3.保存为TXT_ID的副本下次进来检查是否缓存过来读取文件信息
//        List<String> mList_url = new ArrayList<>();
//        mGoodsList = HuibenFlieCacheBaseBean2Manager.getInstance().getListBean(EXTRA_ID);
//        for (int i = 0; i < mGoodsList.size(); i++) {
//            mList_url.add(mGoodsList.get(i).getUrl_mp3());
//        }
//        // 下载bufen
//        String file_url = ConstantsUtils.file_url_mp3_huiben + EXTRA_ID + ConstantsUtils.FILE_SEP;
//        if (FileUtils.createOrExistsDir(file_url)) {
//            DownHuibenUtils.getInstance().start(this, mGoodsList, mList_url, file_url, downLoaderCallBack);
//        }
//    }
//
//    private DownHuibenUtils.FileDownLoaderCallBack downLoaderCallBack = new DownHuibenUtils.FileDownLoaderCallBack() {
//        @Override
//        public void downLoadComplated(BaseDownloadTask task) {
//            Log.e("------taskPath---", task.getTargetFilePath());
//            Log.e("------taskFilename---", task.getFilename());
//            HuibenFileCacheBean bean = (HuibenFileCacheBean) task.getTag();
//            bean.setUrl_mp3(task.getTargetFilePath());
//            if (bean.getId().equals(mGoodsList.get(mGoodsList.size() - 1).getId())) {
//                down_finish();
//            }
//        }
//
//        @Override
//        public void downLoadWarn(BaseDownloadTask task) {
//            Log.e("------warn---", "warn");
//            Toasty.normal(ReadBookActivity.this, "已在下载队列中").show();
//            while (task.getSmallFileSoFarBytes() != task.getSmallFileTotalBytes()) {
//                int percent = (int) ((double) task.getSmallFileSoFarBytes() / (double) task.getSmallFileTotalBytes() * 100);
////                textView.setText("("+percent+"%"+")");
//            }
//        }
//
//        @Override
//        public void downLoadError(BaseDownloadTask task, Throwable e) {
//            Log.e("------error---", e.toString());
//        }
//
//        @Override
//        public void downLoadProgress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//            int percent = (int) ((double) soFarBytes / (double) totalBytes * 100);
////            textView.setText("("+percent+"%"+")");
//            Log.e("------progress---", "");
//        }
//    };
//
//    // 下载完成后要改变的状态bufen
//    private void down_finish() {
//        //
//        tv_down.setBackgroundResource(R.drawable.slbdowned);
//        SPUtils.getInstance().put(EXTRA_ID, EXTRA_ID);// 为了后面判断是否下载过绘本 将绘本id存着
//        // 保存bean
//        mList_huancun = DownManager.getInstance().getListBean(CommonUtils.TXTID_BEAN);
//        if (mList_huancun.size() == 0) {
//            ArrayList<HuibenFileCacheBeanItem> mList1 = new ArrayList<>();
//            mList1.add(new HuibenFileCacheBeanItem(EXTRA_ID, EXTRA_NAME, TXT_URL, "", is_heng_shu, is_shoucang));
//            DownManager.getInstance().AddHashMap(CommonUtils.TXTID_BEAN, mList1);
//        } else {
//            mList_huancun.add(new HuibenFileCacheBeanItem(EXTRA_ID, EXTRA_NAME, TXT_URL, "", is_heng_shu, is_shoucang));
//            DownManager.getInstance().AddHashMap(CommonUtils.TXTID_BEAN, mList_huancun);
//        }
//        Toasty.normal(ReadBookActivity.this, "已下载完成").show();
//    }
//
//    // 暂停bufen
//    private boolean is_setmusicplay = true;// true 能自动滚动播放 false 不能自动滚动播放
//
//    private void qita_stop_music() {
//        if (mBinder != null && mBinder.getService() != null && mBinder.getService().getmPlayer().isPlaying()) {
//            // 关了
//            is_setmusicplay = false;
//            iv_stop1.setImageResource(R.drawable.slb_playrb2);
//            mBinder.musicPause();
//        } else if (mBinder != null && mBinder.getService() != null && !mBinder.getService().getmPlayer().isPlaying()) {
//            // 打开
//            is_setmusicplay = true;
//            iv_stop1.setImageResource(R.drawable.slb_playrb22);
//            mBinder.musicContinue();
//        }
//    }
//
//    // 点击收藏bufen
//    @Override
//    public void OnSaveFavoritesSuccess(String s) {
//        Toasty.normal(ReadBookActivity.this, s).show();
//    }
//
//    @Override
//    public void OnSaveFavoritesNodata(String s) {
//        Toasty.normal(ReadBookActivity.this, s).show();
//    }
//
//    @Override
//    public void OnSaveFavoritesFail(String s) {
//        Toasty.normal(ReadBookActivity.this, s).show();
//    }
//
//    // 如果是横屏先退出小的再弹出窗
//    private void set_pop_small() {
//
//        if (ScreenUtils.isLandscape()) {
//            AutoSize.autoConvertDensityOfGlobal(ReadBookActivity.this); //如果没有自定义需求用这个方法
//            AutoSize.autoConvertDensity(ReadBookActivity.this, 375, false); //如果有自定义需求就用这个方法
//        } else {
//            AutoSize.autoConvertDensityOfGlobal(ReadBookActivity.this); //如果没有自定义需求用这个方法
//            AutoSize.autoConvertDensity(ReadBookActivity.this, 375, true); //如果有自定义需求就用这个方法
//        }
//        onBackpress();
//        // AutoSize.autoConvertDensity((Activity) ReadBookActivity.this, 375, true);// 667
//    }
//
//    // 恭喜任务完成bufen
//    private void get_renwu_pop() {
//        // 恭喜读完弹窗
//        PopRenwuPay popRenwuPay = new PopRenwuPay(ReadBookActivity.this, new PopRenwuPay.OnFinishResultClickListener() {
//            @Override
//            public void onResult() {
//                //
//                HiosHelper.resolveAd(ReadBookActivity.this, ReadBookActivity.this, "http://liangxiao.blog.51cto.com/");
//                finish();
//            }
//        });
//        popRenwuPay.showAtLocation(ReadBookActivity.this.getWindow().getDecorView(),
//                Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
//                , 0, 0); // 设置layout在PopupWindow中显示的位置
//    }
//
//    // 试读完成弹窗bufen
//    private PopPayForVip2 popRenwuPay;
//
//    private void get_shidu_pop() {
//        popRenwuPay = new PopPayForVip2(this, new PopPayForVip2.OnFinishResultClickListener() {
//            @Override
//            public void onKaitonghuiyuan() {
//                // 开通会员
//                SlbLoginUtil.get().loginTowhere(ReadBookActivity.this, new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent("hs.act.slbapp.VipActivity");
//                        SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, CommonUtils.HUIBEN_PAYSUCCESS_TAG1);
//                        startActivity(intent);
////                        finish();
//                        popRenwuPay.dismiss();
//                    }
//                });
//            }
//
//            @Override
//            public void onGoumaihuiben() {
//                // 购买绘本
//                SlbLoginUtil.get().loginTowhere(ReadBookActivity.this, new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent("hs.act.slbapp.OrderPayActivity");
//                        SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, CommonUtils.HUIBEN_PAYSUCCESS_TAG2);
//                        intent.putExtra(CommonUtils.HUIBEN_IDS, EXTRA_ID);
//                        startActivity(intent);
////                        finish();
//                        popRenwuPay.dismiss();
//                    }
//                });
//            }
//        });
//        popRenwuPay.showAtLocation(getWindow().getDecorView(),
//                Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
//                , 0, 0); // 设置layout在PopupWindow中显示的位置
//    }
//
//
//}
