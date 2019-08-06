package com.example.slbappindex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ServiceUtils;
import com.bumptech.glide.Glide;
import com.example.biz3slbappusercenter.bean.SAppUpgradeBean;
import com.example.biz3slbappusercenter.presenter.SAppUpgradePresenter;
import com.example.biz3slbappusercenter.view.SAppUpgradeView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivityNoDoubleClickOne;
import com.example.slbappcomm.broadcastreceiver.PhoneService;
import com.example.slbappcomm.playermusic.floatbutton.floatbutton3.DragFloatActionButton;
import com.example.slbappcomm.services.UpdataCommonservices;
import com.example.slbappindex.adapter.RecycleAdapter;
import com.example.slbappindex.domain.Biaoge_listBean;
import com.example.slbappindex.fragment.fragment1.FragmentContent1New1;
import com.example.slbappindex.fragment.fragment2.FragmentContent2;
import com.example.slbappindex.fragment.fragment2.FragmentContent2New1;
import com.example.slbappindex.fragment.fragment2.FragmentContent2New2;
import com.example.slbappindex.fragment.fragment2.comm.Fragment2Factory;
import com.example.slbappindex.fragment.fragment2.utils.DownloadServiceMp4;
import com.example.slbappindex.fragment.fragment3.FragmentContent3;
import com.example.slbappindex.fragment.fragment4.FragmentContent4New2;
import com.example.slbappindex.fragment.fragment4.FragmentMqhContent4;
import com.example.slbappindex.fragment.fragment4.uploadimg.Fragment4UploadImgUtils;
import com.example.slbappstatistics.utils.JEventUtils;
import com.github.commonlibs.libupdateapputils.util.UpdateAppReceiver;
import com.github.commonlibs.libupdateapputils.util.UpdateAppUtils;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.download.DownLoadDialog;
import com.haier.cellarette.baselibrary.statusbar.StatusBarUtil;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.analytics.android.api.CountEvent;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jpush.android.api.JPushInterface;
import cn.jzvd.JZVideoPlayerStandard;
import me.jessyan.autosize.AutoSizeCompat;

//import android.support.annotation.Nullable;
//import androidx.core.app.FragmentManager;
//import androidx.core.app.FragmentTransaction;
//import androidx.core.content.ContextCompat;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;

public class IndexMainActivity extends SlbBaseActivityNoDoubleClickOne implements Handler.Callback, SAppUpgradeView {

    private List<Biaoge_listBean> mList;
    private static final String LIST_TAG0 = "list0";
    private static final String LIST_TAG1 = "list1";
    private static final String LIST_TAG2 = "list2";
    private static final String LIST_TAG3 = "list3";
    private static final String LIST_TAG4 = "list4";
    //    private String id1 = "http://jiuzhidao.com/shop/index.php?act=index&op=index&screen=1";
    public static final String id1 = "http://jiuzhidao.com/wap/";
    public static final String id2 = "2";
    public static final String id3 = "3";
    public static final String id4 = "4";

    private void addList() {
        mList.add(new Biaoge_listBean(id1, "练习", R.drawable.control_pobiji_guoshu_normal, R.drawable.control_pobiji_guoshu_select, true));
        mList.add(new Biaoge_listBean(id2, "主课", R.drawable.control_pobiji_jiangliao_normal, R.drawable.control_pobiji_jiangliao_select, false));
//        mList.add(new Biaoge_listBean(id3, "演示点", R.drawable.control_pobiji_lengyin_normal, R.drawable.control_pobiji_lengyin_select, false));
        mList.add(new Biaoge_listBean(id4, "我的", R.drawable.control_pobiji_mofen_normal, R.drawable.control_pobiji_mofen_select, false));
    }

    private void initList() {
        for (int i = 0; i < mList.size(); i++) {
            Biaoge_listBean item = mList.get(i);
            if (item.isEnselect()) {
                item.setEnselect(false);
            }
        }
    }

    @Override
    public Resources getResources() {
        //需要升级到 v1.1.2 及以上版本才能使用 AutoSizeCompat
        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()));//如果没有自定义需求用这个方法
        AutoSizeCompat.autoConvertDensity((super.getResources()), 667, false);//如果有自定义需求就用这个方法
        return super.getResources();
    }

    //数据解析bufen
    private RecyclerView recyclerView;
    private RecycleAdapter mAdapter;
    private List<Biaoge_listBean> mratings;

    private FragmentContent1New1 mFragment1; //
    private FragmentContent2New1 mFragment2; //
    private FragmentContent3 mFragment3; //
    private FragmentMqhContent4 mFragment4; //
//    private FragmentContent4New2 mFragment4; //

    private FragmentManager mFragmentManager;

    /**
     * 监听本地图片变化bufen
     */
    private MessageReceiverIndex mMessageReceiver;

    /**
     * 监听本地图片变化bufen
     */
    public class MessageReceiverIndex extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (CommonUtils.index_action.equals(intent.getAction())) {
                    // 切换Fragment bufen
                    //点击item
                    if (intent.getIntExtra(CommonUtils.index_action, 0) == 0) {
                        current_pos = 0;
                    } else if (intent.getIntExtra(CommonUtils.index_action, 0) == 1) {
                        current_pos = 1;
                    } else if (intent.getIntExtra(CommonUtils.index_action, 0) == 2) {
                        current_pos = 2;
                    }
                    footer_onclick();
                }

            } catch (Exception e) {
            }
        }
    }

    private UpdateAppReceiver updateAppReceiver;// 强制升级
    private SAppUpgradePresenter presenter;
    private String apkPath = "";
    private int serverVersionCode = 0;
    private String serverVersionName = "";
    private String updateInfoTitle = "";
    private String updateInfo = "";
    private String md5 = "";
    private String appPackageName = "";
    //    private MyButton mybtn2;
    private DragFloatActionButton mybtn2;
    private String float_id_zong;
    private String float_id;
    private String float_name;
    private ArrayList<String> float_xmly = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demomain;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtilV7.immersive(this);
        // 避免从桌面启动程序后，会重新实例化入口类的activity
        if (!this.isTaskRoot()) {
            Intent intent = getIntent();
            if (intent != null) {
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                    finish();
                    return;
                }
            }
        }
        apkPath = "";
        serverVersionCode = AppUtils.getAppVersionCode();
        serverVersionName = AppUtils.getAppVersionName();
        appPackageName = AppUtils.getAppPackageName();
        updateInfoTitle = "";
        updateInfo = "";
        md5 = AppUtils.getAppSignatureMD5(appPackageName);
        presenter = new SAppUpgradePresenter();
        presenter.onCreate(this);
        presenter.getAppUpgradeData(serverVersionCode + "", serverVersionName, md5, appPackageName);
        updateAppReceiver = new UpdateAppReceiver();

    }

    /**
     * 升级bufen
     *
     * @param sAppUpgradeBean
     */
    @Override
    public void OnAppUpgradeSuccess(SAppUpgradeBean sAppUpgradeBean) {
        apkPath = sAppUpgradeBean.getAppUpgrade().getApkUrl();
        serverVersionCode = Integer.valueOf(sAppUpgradeBean.getAppUpgrade().getVersion());
        serverVersionName = sAppUpgradeBean.getAppUpgrade().getName();
        updateInfoTitle = sAppUpgradeBean.getAppUpgrade().getTitle();
        updateInfo = sAppUpgradeBean.getAppUpgrade().getUpgradeInfo();
        if (TextUtils.equals(sAppUpgradeBean.getAppUpgrade().getForce(), "1")) {
            // 检查更新bufen
            UpdateAppUtils.from(IndexMainActivity.this)
                    .serverVersionCode(serverVersionCode)
                    .serverVersionName(serverVersionName)
                    .downloadPath("apk/" + getPackageName() + ".apk")
                    .showProgress(true)
                    .isForce(true)
                    .apkPath(apkPath)
                    .downloadBy(UpdateAppUtils.DOWNLOAD_BY_APP)    //default
                    .checkBy(UpdateAppUtils.CHECK_BY_VERSION_CODE) //default
                    .updateInfoTitle(updateInfoTitle)
                    .updateInfo(updateInfo.replace("|", "\n"))
//                    .showNotification(true)
//                    .needFitAndroidN(true)
//                    .updateInfoTitle("新版本已准备好")
//                    .updateInfo("版本：1.01" + "    " + "大小：10.41M\n" + "1.修改已知问题\n2.加入动态绘本\n3.加入小游戏等你来学习升级")
                    .update();
        } else {
            // 检查更新bufen
            UpdateAppUtils.from(IndexMainActivity.this)
                    .serverVersionCode(serverVersionCode)
                    .serverVersionName(serverVersionName)
                    .downloadPath("apk/" + getPackageName() + ".apk")
                    .showProgress(true)
                    .apkPath(apkPath)
                    .downloadBy(UpdateAppUtils.DOWNLOAD_BY_APP)    //default
                    .checkBy(UpdateAppUtils.CHECK_BY_VERSION_CODE) //default
                    .updateInfoTitle(updateInfoTitle)
                    .updateInfo(updateInfo.replace("|", "\n"))
//                    .showNotification(true)
//                    .needFitAndroidN(true)
//                    .updateInfoTitle("新版本已准备好")
//                    .updateInfo("版本：1.01" + "    " + "大小：10.41M\n" + "1.修改已知问题\n2.加入动态绘本\n3.加入小游戏等你来学习升级")
                    .update();
        }

    }

    @Override
    public void OnAppUpgradeNodata(String s) {

    }

    @Override
    public void OnAppUpgradeFail(String s) {

    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);// 华为底部导航栏问题
        //状态栏透明和间距处理
//        StatusBarUtilV7.immersive(this, 0xff000000, 0.1f);
//        StatusBarUtilV7.immersive(this);
//        StatusBarUtil.setPaddingSmart(this, toolbar);
//        StatusBarUtilV7.immersive(this, ContextCompat.getColor(this, R.color.color_F58585), 0.5f);
//        getWindow().getDecorView().setFitsSystemWindows(true);
        //透明状态栏 @顶部
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        StatusBarUtil.

        findview();
        onclick();
        mFragmentManager = getSupportFragmentManager();
        // 首页切换到我的
        mMessageReceiver = new MessageReceiverIndex();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(CommonUtils.index_action);
        LocalBroadcastManagers.getInstance(getApplicationContext()).registerReceiver(mMessageReceiver, filter);

        // 解决fragment布局重叠错乱
        if (savedInstanceState != null) {
            mFragment1 = (FragmentContent1New1) mFragmentManager.findFragmentByTag(LIST_TAG1);
            mFragment2 = (FragmentContent2New1) mFragmentManager.findFragmentByTag(LIST_TAG2);
//            mFragment3 = (FragmentContent3) mFragmentManager.findFragmentByTag(LIST_TAG3);
//            mFragment4 = (FragmentContent4) mFragmentManager.findFragmentByTag(LIST_TAG4);
            mFragment4 = (FragmentMqhContent4) mFragmentManager.findFragmentByTag(LIST_TAG4);
        }
        doNetWork();
        configTuisong();
//        xiazai_jindutiao();

    }

    private void configListenBook() {
        Intent intent = new Intent(this, PhoneService.class);
        startService(intent);
        //手动控制
//        //修改显示位置
//        FloatWindow.get().updateX(100);
//        FloatWindow.get().updateY(100);
//        //销毁
//        FloatWindow.destroy();
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        if (SPUtils.getInstance().getBoolean(CommonUtils.LISTENBOOK_TAG1, false) && audio.isMusicActive()) {
        if (SPUtils.getInstance().getBoolean(CommonUtils.LISTENBOOK_TAG1, false)
                && ServiceUtils.isServiceRunning("com.example.slbappcomm.playermusic.ListenMusicPlayerService")) {
            mybtn2.setVisibility(View.VISIBLE);
        } else {
            mybtn2.setVisibility(View.GONE);
        }
    }


    private void configTuisong() {
        JPushInterface.onResume(this);
        addEvent();
    }

    private void addEvent() {
        CountEvent cEvent = new CountEvent("clickcode");
        JAnalyticsInterface.onEvent(this, cEvent);
        JEventUtils.onLoginEvent(this);
        JEventUtils.onBrowseEvent(this);
        JEventUtils.onCountEvent(this);
    }

    /**
     * 设置皮肤bufen
     *
     * @param aa
     */
    private void pifu(String aa) {
//        int aa = new Random().nextInt(3);
        int color = R.color.oneKeyRepair;
        if (aa.equals(id1)) {
            color = R.color.oneKeyRepair;
        } else if (aa.equals(id2)) {
            color = R.color.color_568EC0;
        } else if (aa.equals(id3)) {
            color = R.color.e04832;
        } else if (aa.equals(id4)) {
            color = R.color.orange_red;
        }
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, color));

    }


    private void doNetWork() {
        //请求数据bufen
        mList = new ArrayList<Biaoge_listBean>();
        addList();

//        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(this);
//        mLinearLayoutManager1.setOrientation(OrientationHelper.HORIZONTAL);
//        recyclerView.setLayoutManager(mLinearLayoutManager1);
        recyclerView.setLayoutManager(new GridLayoutManager(this, mList.size(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

//        ShowLoadingUtil.showLoading(this, "", null);
        mAdapter.setContacts(mList);
        mAdapter.notifyDataSetChanged();
        //设置Fragmentbufen
//        showFragment(id1, false);
        current_pos = 1;
        footer_onclick();
        //giftbufen
//        show_popgift();
    }

    private void show_popgift() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent("hs.act.slbapp.PopGiftActivity"));
            }
        }, 2500);
    }

    private int current_pos = 0;
    private boolean firstIn = true;

    private void onclick() {
        mAdapter.setOnItemClickLitener(new RecycleAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击item
                current_pos = position;
                footer_onclick();
            }
        });
    }

    //点击item
    private void footer_onclick() {
        // 勋章弹窗bufen
        if (firstIn) {
            firstIn = false;
        } else if (current_pos != 2) {
            checkLatestMedal();
        }
        Biaoge_listBean model = (Biaoge_listBean) mAdapter.getItem(current_pos);
        if (model.isEnselect()) {
            set_footer_nochange(model);
        } else {
            set_footer_change(model);
        }

    }

    private void findview() {
        mybtn2 = findViewById(R.id.mybtn2);
        Glide.with(this).load(R.drawable.slb_playgif2).into(mybtn2);
        mybtn2.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                // 通过广播传回来听书页面的值点击进入听书当前item页面
                Intent intent = new Intent("hs.act.slbapp.ListenMusicActivity");
                intent.putExtra(CommonUtils.HUIBEN_IDS_ZONG, SPUtils.getInstance().getString("float_id_zong"));
                intent.putExtra(CommonUtils.HUIBEN_IDS, SPUtils.getInstance().getString("float_id"));
                intent.putExtra(CommonUtils.HUIBEN_TITLES, SPUtils.getInstance().getString("float_name"));
                intent.putStringArrayListExtra(CommonUtils.HUIBEN_XMLY, null);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.recycler_view1);

        mAdapter = new RecycleAdapter(this);
////        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(this);
////        mLinearLayoutManager1.setOrientation(OrientationHelper.HORIZONTAL);
////        recyclerView.setLayoutManager(mLinearLayoutManager1);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 5, OrientationHelper.VERTICAL, false));
//        recyclerView.setAdapter(mAdapter);
    }

    private String tag_ids;

    private void showFragment(String tag, boolean isrefresh) {
        tag_ids = tag;
        //pifubufen
//        pifu(id2);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);

        if (tag.equalsIgnoreCase("-1")) { //
//            if (mFragment1 == null) {
//                mFragment1 = new FragmentContent1();
//                transaction.add(R.id.container, mFragment1, LIST_TAG0);
//            } else {
//                transaction.show(mFragment1);
//                mFragment1.initData();
//            }
        } else if (tag.equalsIgnoreCase(id1)) {
            if (mFragment1 == null) {
                mFragment1 = new FragmentContent1New1();
                Bundle args = new Bundle();
                args.putString("tablayoutId", tag);
                mFragment1.setArguments(args);
                transaction.add(R.id.container, mFragment1, LIST_TAG1);
            } else {
                transaction.show(mFragment1);
                mFragment1.getCate(tag, isrefresh);
            }
        } else if (tag.equalsIgnoreCase(id2)) {
            if (mFragment2 == null) {
                mFragment2 = new FragmentContent2New1();
                Bundle args = new Bundle();
                args.putString("tablayoutId", tag);
                mFragment2.setArguments(args);
                transaction.add(R.id.container, mFragment2, LIST_TAG2);
            } else {
                transaction.show(mFragment2);
                mFragment2.getCate(tag, isrefresh);
            }
        } else if (tag.equalsIgnoreCase(id3)) {
            if (mFragment3 == null) {
                mFragment3 = new FragmentContent3();
                Bundle args = new Bundle();
                args.putString("tablayoutId", tag);
                mFragment3.setArguments(args);
                transaction.add(R.id.container, mFragment3, LIST_TAG3);
            } else {
                transaction.show(mFragment3);
                mFragment3.getCate(tag, isrefresh);
            }
        } else if (tag.equalsIgnoreCase(id4)) {
            if (mFragment4 == null) {
                mFragment4 = new FragmentMqhContent4();
                Bundle args = new Bundle();
                args.putString("tablayoutId", tag);
                mFragment4.setArguments(args);
                transaction.add(R.id.container, mFragment4, LIST_TAG4);
            } else {
                transaction.show(mFragment4);
                mFragment4.getCate(tag, isrefresh);
            }
        }

        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mFragment1 != null) {
            transaction.hide(mFragment1);
            mFragment1.give_id(tag_ids);
        }
        if (mFragment2 != null) {
            transaction.hide(mFragment2);
            mFragment2.give_id(tag_ids);
        }
        if (mFragment3 != null) {
            transaction.hide(mFragment3);
            mFragment3.give_id(tag_ids);
        }
        if (mFragment4 != null) {
            transaction.hide(mFragment4);
            mFragment4.give_id(tag_ids);
        }

    }

    private void checkLatestMedal() {
        Intent intent = new Intent(this, UpdataCommonservices.class);
        intent.setAction(UpdataCommonservices.LATEST_MEDAL_ACTION);
        startService(intent);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForegroundService(intent);
//        } else {
//            // Pre-O behavior.
//            startService(intent);
//        }
    }

    private long exitTime;

//    @Override
//    public void onBackPressed() {
//        if (mDrawerLayout.isDrawerOpen(mLayout)) {
//            mDrawerLayout.closeDrawer(GravityCompat.START);
//
//        } else {
////            mDrawerLayout.openDrawer(Gravity.START);
//            if (System.currentTimeMillis() - firstTime < 3000) {
//                super.onBackPressed();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        MobileLifeApplication.closeApp();
//                    }
//                });
//            } else {
//                firstTime = System.currentTimeMillis();
//                ToastUtil.showToastLong(getString(R.string.main_close));
//            }
//        }
//        if ((System.currentTimeMillis() - exitTime) < 3000) {
//            super.onBackPressed();
//            new Handler().post(new Runnable() {
//                @Override
//                public void run() {
//                    BaseAppManager.getInstance().closeApp();
//                }
//            });
//        } else {
//            Toasty.warning(getApplicationContext(), "再次点击退出程序哟 ~").show();
//            exitTime = System.currentTimeMillis();
//        }
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (JZVideoPlayerStandard.backPress()) {
            return;
        }
        // 头像上传bufen
        if (Fragment4UploadImgUtils.getInstance(this).getPopupWindow() != null &&
                Fragment4UploadImgUtils.getInstance(this).getPopupWindow().isShowing()) {
            Fragment4UploadImgUtils.getInstance(this).getPopupWindow().dismiss();
            return;
        }
//        ShowLoadingUtil.onDestory();
        Biaoge_listBean model = (Biaoge_listBean) mAdapter.getItem(1);
        if (model != null && !tag_ids.equals(model.getText_id())) {
            set_footer_change(model);
        } else {
            if ((System.currentTimeMillis() - exitTime) < 1500) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        BaseAppManager.getInstance().closeApp();

                    }
                });
            } else {
                Toasty.normal(getApplicationContext(), "再次点击退出程序哟 ~").show();
                exitTime = System.currentTimeMillis();

            }
        }
    }

    @Override
    protected void onResume() {
        configListenBook();
        updateAppReceiver.setBr(this);
        super.onResume();
        // 勋章弹窗bufen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (current_pos != 2) {
                    checkLatestMedal();
                }
            }
        }, 1500);

    }

    @Override
    protected void onDestroy() {
//        ShowLoadingUtil.onDestory();
        LocalBroadcastManagers.getInstance(getApplicationContext()).unregisterReceiver(mMessageReceiver);
        if (updateAppReceiver != null) {
            updateAppReceiver.desBr(this);
        }
        if (presenter != null) {
            presenter.onDestory();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (TextUtils.equals(tag_ids, id2)) {
            Fragment2Factory.getInstance(this).set_brc_fragment21and22_change(this);
        }
        JZVideoPlayerStandard.releaseAllVideos();
    }

    // 不切换当前的item点击 刷新当前页面
    private void set_footer_nochange(Biaoge_listBean model) {
        //停留在当前的Fragment 并且刷新
        showFragment(model.getText_id(), true);
    }

    // 切换到另一个item
    private void set_footer_change(Biaoge_listBean model) {
        //设置为选中
        initList();
        model.setEnselect(true);
        mAdapter.setContacts(mList);
        mAdapter.notifyDataSetChanged();
        //切换Fragment不刷新
        showFragment(model.getText_id(), false);
    }

    /**
     * -----------------下载进度条bufen---------------------
     *
     * @param message
     * @return
     */
    private DownLoadDialog customDialog;

    private void xiazai_jindutiao() {
        customDialog = new DownLoadDialog(this);
        DownloadServiceMp4.setUIHandler(new Handler(this));
    }

    int i = 0;

    @Override
    public boolean handleMessage(Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case DownloadServiceMp4.WHAT_DOWNLOAD_FINISHED:
                    if (TextUtils.equals(tag_ids, id2)) {
                        Fragment2Factory.getInstance(this).set_brc_fragment21and22_change(this);
                    }
                    customDialog.dismiss();
                    break;
                case DownloadServiceMp4.WHAT_DOWNLOAD_STARTED:
                    int progress = (int) msg.obj;
                    // 更新进度
                    MyLogUtil.e("ssssssssssss", customDialog.isShowing() + "");
                    if (customDialog != null && !customDialog.isShowing()) {
                        customDialog.show();
                    }
                    customDialog.openDialog(progress);
                    if (progress >= 100) {
                        customDialog.closeDialog();
                    }
                    break;
            }
        }
        return true;
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        try {
//            if (intent.getExtras() != null && intent.getExtras().containsKey(CommonUtils.index_action)) {
//                // 切换Fragment bufen
//                //点击item
//                if (intent.getIntExtra(CommonUtils.index_action, 0) == 0) {
//                    current_pos = 0;
//                } else if (intent.getIntExtra(CommonUtils.index_action, 0) == 1) {
//                    current_pos = 1;
//                } else if (intent.getIntExtra(CommonUtils.index_action, 0) == 2) {
//                    current_pos = 2;
//                }
//                footer_onclick();
//            }
//        } catch (Exception e) {
//        }
//    }

}
