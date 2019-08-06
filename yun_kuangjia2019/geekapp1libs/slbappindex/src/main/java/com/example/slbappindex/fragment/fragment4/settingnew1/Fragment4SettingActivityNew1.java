package com.example.slbappindex.fragment.fragment4.settingnew1;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.example.biz3slbappusercenter.bean.SAppUpgradeBean;
import com.example.biz3slbappusercenter.bean.SLoginUserInfoBean;
import com.example.biz3slbappusercenter.presenter.SAppUpgradePresenter;
import com.example.biz3slbappusercenter.presenter.SBindWeChatLoginPresenter;
import com.example.biz3slbappusercenter.presenter.SGetUserInfoPresenter;
import com.example.biz3slbappusercenter.view.SAppUpgradeView;
import com.example.biz3slbappusercenter.view.SBindWeChatLoginView;
import com.example.biz3slbappusercenter.view.SGetUserInfoView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.huyan.Huyanservices;
import com.example.slbappindex.R;
import com.example.slbappshare.denglu.JPushDengluUtils;
import com.example.slbappshare.denglu.OnResultInfoLitener;
import com.geek.libglide47.base.GlideImageView;
import com.github.commonlibs.libupdateapputils.util.UpdateAppUtils;
import com.haier.cellarette.baselibrary.cacheutil.CacheUtil;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.qcode.ExpandViewRect;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.AlertView;
import com.haier.cellarette.baselibrary.widget.LxLinearLayout;
import com.haier.cellarette.baselibrary.widget.SwitchButton;
import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;
import com.haier.cellarette.baselibrary.zothers.StartHiddenManager;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;

import cn.jiguang.share.wechat.Wechat;

public class Fragment4SettingActivityNew1 extends SlbBaseActivity implements View.OnClickListener, SwitchButton.OnCheckedChangeListener,
        SAppUpgradeView, OnResultInfoLitener, SBindWeChatLoginView, SGetUserInfoView {

    private FrameLayout fl1;
    private LinearLayout ll_share1;
    private TextView tv_center;
    private TextView tv_demo1;
    private TextView tv_demo2;
    private TextView tv_right1;
    private RelativeLayout llBdwx;
    private RelativeLayout rlBdwx1;
    private TextView tv_jt1;
    private TextView tv_jt2;
    private TextView tvBdwx1;
    private GlideImageView ivBdwx1;
    private RelativeLayout llSjh;
    private TextView tvSjh1;
    private RelativeLayout llBbxx;
    private TextView tvBbxx1;
    private RelativeLayout llHyms;
    private SwitchButton slbSb1;
    private LxLinearLayout llHuyan1;
    private RelativeLayout llWddd;
    private RelativeLayout llQchc;
    private TextView tvQchc1;
    private RelativeLayout llJcgx;
    private TextView tvJcgx1;
    private RelativeLayout llKfzx;
    private RelativeLayout llGywm;
    private TextView tvLogin3out2;
    private SAppUpgradePresenter presenter;
    private String apkPath = "";
    private int serverVersionCode = 0;
    private String serverVersionName = "";
    private String updateInfoTitle = "";
    private String updateInfo = "";
    private String md5 = "";
    private String appPackageName = "";
    private JPushDengluUtils jPushDengluUtils;
    //
    private SBindWeChatLoginPresenter presenter1;
    private SGetUserInfoPresenter presenter2;
    private String openid;
    private String unionid;
    private String gender;
    private String nickName;
    private String avatar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment4_setting_new1;
    }

    @Override
    protected void onResume() {
        //        updateAppReceiver.setBr(this);
        setrefresh_data();
        super.onResume();
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        //虚拟键
        if (NavigationBarUtil.hasNavigationBar(this)) {
//            NavigationBarUtil.initActivity(getWindow().getDecorView());
            NavigationBarUtil.hideBottomUIMenu(this);
        }
        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        findview();
        onclick();
        donetwork();

    }

    private void donetwork() {
        if (SPUtils.getInstance().getBoolean("huyan")) {
            slbSb1.setChecked(true);
        } else {
            slbSb1.setChecked(false);
        }
        ExpandViewRect.expandViewTouchDelegate(tv_right1, 20, 20, 20, 20);
        try {
            tvQchc1.setText(CacheUtil.getTotalCacheSize(BaseApp.get()));
        } catch (Exception e) {
            tvQchc1.setText("0.00B");
        }
        //
        jPushDengluUtils = new JPushDengluUtils(this);
        if (SlbLoginUtil2.get().isUserLogin()) {
//            jPushDengluUtils.shezhi_shouquan_getinfo(Wechat.Name);
        } else {
            tvSjh1.setText("绑定手机");
            tvBdwx1.setText("去绑定");
            set_jt_vis(false);
            tvLogin3out2.setText(getResources().getString(R.string.slb_my_tip555));
        }
        //
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
        presenter1 = new SBindWeChatLoginPresenter();
        presenter1.onCreate(this);
        presenter2 = new SGetUserInfoPresenter();
        presenter2.onCreate(this);
    }

    private void onclick() {
//        tv_demo1.setOnClickListener(this);
//        tv_demo2.setOnClickListener(this);
        tv_right1.setOnClickListener(this);
        llBdwx.setOnClickListener(this);
        llSjh.setOnClickListener(this);
        llBbxx.setOnClickListener(this);
        llHuyan1.setOnClickListener(this);
        llWddd.setOnClickListener(this);
        llQchc.setOnClickListener(this);
        llJcgx.setOnClickListener(this);
        llKfzx.setOnClickListener(this);
        llGywm.setOnClickListener(this);
        tvLogin3out2.setOnClickListener(this);

        slbSb1.setOnCheckedChangeListener(this);

    }

    private void findview() {
        fl1 = findViewById(R.id.fl11);
        tv_center = findViewById(R.id.tv_center);
        tv_center.setText("设置");
        tv_demo1 = findViewById(R.id.tv_demo1);
        tv_demo2 = findViewById(R.id.tv_demo2);
        tv_right1 = findViewById(R.id.tv_right);
        fl1 = findViewById(R.id.fl11);
        ll_share1 = findViewById(R.id.ll_share1);
        llBdwx = findViewById(R.id.ll_bdwx);
        rlBdwx1 = findViewById(R.id.rl_bdwx1);
        tvBdwx1 = findViewById(R.id.tv_bdwx1);
        tv_jt1 = findViewById(R.id.tv_jt1);
        tv_jt2 = findViewById(R.id.tv_jt2);
        ivBdwx1 = findViewById(R.id.iv_bdwx1);
        llSjh = findViewById(R.id.ll_sjh);
        tvSjh1 = findViewById(R.id.tv_sjh1);
        llBbxx = findViewById(R.id.ll_bbxx);
        tvBbxx1 = findViewById(R.id.tv_bbxx1);
        llHyms = findViewById(R.id.ll_hyms);
        slbSb1 = findViewById(R.id.slb_sb1);
        llHuyan1 = findViewById(R.id.ll_huyan1);
        llWddd = findViewById(R.id.ll_wddd);
        llQchc = findViewById(R.id.ll_qchc);
        tvQchc1 = findViewById(R.id.tv_qchc1);
        llJcgx = findViewById(R.id.ll_jcgx);
        tvJcgx1 = findViewById(R.id.tv_jcgx1);
        llKfzx = findViewById(R.id.ll_kfzx);
        llGywm = findViewById(R.id.ll_gywm);
        tvLogin3out2 = findViewById(R.id.tv_login3out2);
        // 进入勋章bufen
        startHiddenManager = new StartHiddenManager(tv_demo1, tv_demo2, "hs.act.slbapp.Fragment4FankuiActivity");

    }

    private boolean is_wechat_toast;
    private StartHiddenManager startHiddenManager;

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_right) {
            onBackPressed();
        } else if (i == R.id.ll_bdwx) {
            // 绑定微信bufen
            if (SlbLoginUtil2.get().isUserLogin()) {
                is_wechat_toast = true;
                if (ivBdwx1.getVisibility() == View.GONE) {
                    // 还没绑定微信
                    // 去授权
                    jPushDengluUtils.shezhi_shouquan_getinfo(Wechat.Name);
                }
            } else {
                SlbLoginUtil2.get().loginTowhere(Fragment4SettingActivityNew1.this, new Runnable() {
                    @Override
                    public void run() {
                        // 刷新个人中心bufen
//                        tvLogin3out2.setText(getResources().getString(R.string.slb_my_tip555));
//                        setrefresh_data();
//                        onBackPressed();

                    }
                });
                // 去授权
//                is_wechat_toast = true;
//                jPushDengluUtils.shezhi_shouquan_getinfo(Wechat.Name);
            }
        } else if (i == R.id.ll_sjh) {
            // 绑定手机bufen
            if (SlbLoginUtil2.get().isUserLogin()) {

            } else {
                SlbLoginUtil2.get().loginTowhere(this, new Runnable() {
                    @Override
                    public void run() {
                        if (!TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_TEL))) {
                            tvSjh1.setText(SPUtils.getInstance().getString(CommonUtils.USER_TEL));
                        } else {
                            tvSjh1.setText("绑定手机");
                        }
                    }
                });
            }
        } else if (i == R.id.ll_bbxx) {
            // 修改资料bufen
            // jPushDengluUtils.shouquan_cancel(Wechat.Name);
            SlbLoginUtil2.get().loginTowhere(this, new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent("hs.act.slbapp.SlbLoginInfoActivity"));
                }
            });
        } else if (i == R.id.ll_huyan1) {
            // 设置护眼bufen
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 判断是否有WRITE_SETTINGS权限if(!Settings.System.canWrite(this))
                if (!Settings.canDrawOverlays(this)) {
                    final AlertView dialog = new AlertView(this, "温馨提示", "需要开启悬浮窗权限",
                            "取消", "确定");
                    dialog.show();
                    dialog.setClicklistener(new AlertView.ClickListenerInterface() {
                                                @Override
                                                public void doLeft() {
                                                    dialog.dismiss();
                                                }

                                                @Override
                                                public void doRight() {
                                                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                                            Uri.parse("package:" + Fragment4SettingActivityNew1.this.getPackageName()));
                                                    startActivityForResult(intent, 10021);
                                                    dialog.dismiss();

                                                }
                                            }
                    );
                } else {
                    set_check_huyan();
                }
            } else {
                set_check_huyan();
            }
        } else if (i == R.id.ll_wddd) {
            //我的订单bufen
            SlbLoginUtil2.get().loginTowhere(this, new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent("hs.act.slbapp.OrderActivity"));

                }
            });
        } else if (i == R.id.ll_qchc) {
            // 清除缓存bufen
            final AlertView dialog = new AlertView(this, "温馨提示", "清除缩略图及其他缓存文件",
                    "取消", "确定");
            dialog.show();
            dialog.setClicklistener(new AlertView.ClickListenerInterface() {
                                        @Override
                                        public void doLeft() {
                                            dialog.dismiss();
                                        }

                                        @Override
                                        public void doRight() {
                                            //TODO 清除应用缓存
                                            CacheUtil.clearAllCache(Fragment4SettingActivityNew1.this);
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        tvQchc1.setText(CacheUtil.getTotalCacheSize(BaseApp.get()));
                                                    } catch (Exception e) {
                                                        tvQchc1.setText("0.00B");
                                                    }
                                                }
                                            }, 500);
                                            dialog.dismiss();

                                        }
                                    }
            );
        } else if (i == R.id.ll_jcgx) {
            // 检查更新bufen
            UpdateAppUtils.from(this)
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

        } else if (i == R.id.ll_kfzx) {
            //反馈中心bufen
            startActivity(new Intent("hs.act.slbapp.Fragment4FankuiActivity"));
//            startActivity(new Intent("hs.act.slbapp.CategoryActivityAll"));
//            startActivity(new Intent("hs.act.slbapp.JpushActivity"));
        } else if (i == R.id.ll_gywm) {
            //关于我们bufen
//            if (!NetworkUtils.isConnected()) {
//                Toasty.normal(getApplicationContext(), "网络异常，请检查网络连接！").show();
//                return;
//            }
            startActivity(new Intent("hs.act.slbapp.Fragment4FankuiActivity"));
//            HiosHelper.resolveAd(Fragment4SettingActivityNew1.this, Fragment4SettingActivityNew1.this, "http://hexiang-app.sairobo.cn/user-notice/about-us.html");
//            HiosHelper.resolveAd(Fragment4SettingActivityNew1.this, Fragment4SettingActivityNew1.this, "http://hexiang-app.sairobo.cn/promotion/");
        } else if (i == R.id.tv_login3out2) {
            String aaa = tvLogin3out2.getText().toString().trim();
            if (TextUtils.equals(aaa, getResources().getString(R.string.slb_my_tip55))) {
                // 退出登录bufen
                SlbLoginUtil2.get().loginOutTowhere(Fragment4SettingActivityNew1.this, new Runnable() {
                    @Override
                    public void run() {
                        // 刷新个人中心bufen
                        tvLogin3out2.setText(getResources().getString(R.string.slb_my_tip555));
                        // 由于后台没有微信解绑操作 暂时本地处理
                        SPUtils.getInstance().put(CommonUtils.WECHAT_USER_IMG, "");
                        SPUtils.getInstance().put(CommonUtils.WECHAT_USER_NAME, "");
//                        setrefresh_data();
//                        onBackPressed();

                    }
                });
            }
            if (TextUtils.equals(aaa, getResources().getString(R.string.slb_my_tip555))) {
                // 登录bufen
                SlbLoginUtil2.get().loginTowhere(Fragment4SettingActivityNew1.this, new Runnable() {
                    @Override
                    public void run() {
                        // 刷新个人中心bufen
                        tvLogin3out2.setText(getResources().getString(R.string.slb_my_tip55));
//                        setrefresh_data();
//                        onBackPressed();

                    }
                });
            }
        } else {
        }
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        int i = view.getId();
        if (i == R.id.slb_sb1) {
            // 开启护眼bufen
            if (isChecked) {
                //设置开启
                startService(new Intent(this, Huyanservices.class));
                SPUtils.getInstance().put("huyan", true);
//                Toasty.normal(Fragment4SettingActivity.this, isChecked + "").show();
            } else {
                // 设置关闭
                stopService(new Intent(this, Huyanservices.class));
                SPUtils.getInstance().put("huyan", false);
//                Toasty.normal(Fragment4SettingActivity.this, isChecked + "").show();
            }
        }
    }

    @Override
    protected void onActResult(int requestCode, int resultCode, Intent data) {
        super.onActResult(requestCode, resultCode, data);
        if (requestCode == 10021) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 判断是否有WRITE_SETTINGS权限if(!Settings.System.canWrite(this))
                if (!Settings.canDrawOverlays(this)) {
                    // Permission Denied
                    Toasty.normal(Fragment4SettingActivityNew1.this, "请开启显示悬浮窗权限").show();
                } else {
                    set_check_huyan();
                }
            } else {
                set_check_huyan();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10021) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 判断是否有WRITE_SETTINGS权限if(!Settings.System.canWrite(this))
                if (!Settings.canDrawOverlays(this)) {
                    // Permission Denied
                    Toasty.normal(Fragment4SettingActivityNew1.this, "Permission Denied").show();
                } else {
                    set_check_huyan();
                }
            } else {
                set_check_huyan();
            }
        }
    }

//    private UpdateAppReceiver updateAppReceiver = new UpdateAppReceiver();

    private void setrefresh_data() {
        presenter2.getGetUserInfoData();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        presenter1.onDestory();
        jPushDengluUtils.ondes();
        startHiddenManager.onDestory();
//        updateAppReceiver.desBr(this);
//        stopService(new Intent(this, Huyanservices.class));
//        SPUtils.getInstance().put("huyan", false);
        super.onDestroy();
    }

    private void set_jt_vis(boolean is_weichat_login) {
        if (is_weichat_login) {
            ivBdwx1.setVisibility(View.VISIBLE);
            tv_jt1.setVisibility(View.GONE);
            tv_jt2.setVisibility(View.GONE);
        } else {
            ivBdwx1.setVisibility(View.GONE);
            tv_jt1.setVisibility(View.VISIBLE);
            tv_jt2.setVisibility(View.VISIBLE);
        }
    }

    private void set_check_huyan() {
        if (!slbSb1.isChecked()) {
            //设置开启
            slbSb1.setChecked(true);
        } else {
            // 设置关闭
            slbSb1.setChecked(false);
        }
    }

    @Override
    public void OnAppUpgradeSuccess(SAppUpgradeBean sAppUpgradeBean) {
        apkPath = sAppUpgradeBean.getAppUpgrade().getApkUrl();
        serverVersionCode = Integer.valueOf(sAppUpgradeBean.getAppUpgrade().getVersion());
//        serverVersionName = sAppUpgradeBean.getAppUpgrade().getName();
        tvJcgx1.setText("V" + serverVersionName);
        updateInfoTitle = sAppUpgradeBean.getAppUpgrade().getTitle();
        updateInfo = sAppUpgradeBean.getAppUpgrade().getUpgradeInfo();
    }

    @Override
    public void OnAppUpgradeNodata(String s) {
//        StringBuffer stringBuilder1 = new StringBuffer(serverVersionCode + "");//100 1.00
//        tv_versoncode.setText("V" + stringBuilder1.insert(1, ".").toString());
        tvJcgx1.setText("V" + serverVersionName);
    }

    @Override
    public void OnAppUpgradeFail(String s) {
        tvJcgx1.setText("V" + serverVersionName);
    }

    private String toastMsg1 = "";

    @Override
    public void onResults(String platform, String toastMsg, String data) {
//        Toast.makeText(getApplicationContext(), platform + "---" + toastMsg + "---" + data, Toast.LENGTH_LONG).show();
        toastMsg1 = toastMsg;
        if (TextUtils.equals(toastMsg, "获取个人信息成功:")) {
            // 微信登录成功bufen
            JSONObject jsonObject = JSONObject.parseObject(data);
            if (jsonObject == null) {
                return;
            }
            openid = (String) jsonObject.get("openid");
            nickName = (String) jsonObject.get("nickname");
            gender = (int) jsonObject.get("sex") + "";
            avatar = (String) jsonObject.get("headimgurl");
            unionid = (String) jsonObject.get("unionid");
//                ShowLoadingUtil.showLoading(SlbLoginActivity.this, "", null);
            presenter1.getBindWeChatLoginData(openid, unionid, gender, nickName, avatar);
        }
    }

    private void setRefresh() {
        if (SlbLoginUtil2.get().isUserLogin()) {
            tvLogin3out2.setText(getResources().getString(R.string.slb_my_tip55));
            tvSjh1.setText(SPUtils.getInstance().getString(CommonUtils.USER_TEL));
        } else {
            tvLogin3out2.setText(getResources().getString(R.string.slb_my_tip555));
            tvSjh1.setText("绑定手机");
        }
    }

    @Override
    public void OnBindWeChatLoginSuccess(SLoginUserInfoBean sLoginUserInfoBean) {
        SPUtils.getInstance().put(CommonUtils.WECHAT_USER_NAME, nickName);
        SPUtils.getInstance().put(CommonUtils.WECHAT_USER_IMG, avatar);
        tvBdwx1.setText(SPUtils.getInstance().getString(CommonUtils.WECHAT_USER_NAME));
        ivBdwx1.loadImage(SPUtils.getInstance().getString(CommonUtils.WECHAT_USER_IMG), R.drawable.ic_def_loading);
        set_jt_vis(true);
//        // 如果是微信登录则跳转到绑定手机页面
////            ShowLoadingUtil.onDestory();
//        if (sLoginUserInfoBean.getPbUser().isNoPhone()) {
//            // 没有手机号的时候 如果点击绑定微信 就跳转 如果只是刷新就显示手机号部分
//            if (is_wechat_toast) {
//                is_wechat_toast = false;
//                Intent intent = new Intent("hs.act.slbapp.SlbLoginTelActivity");
//                intent.putExtra("tempKey", sLoginUserInfoBean.getTempKey());
//                startActivity(intent);
//                finish();
//            } else {
//                // 已授权
//                tvBdwx1.setText(nickName);
//                ivBdwx1.loadImage(avatar, R.drawable.ic_def_loading);
//                ivBdwx1.setVisibility(View.VISIBLE);
//            }
//        } else {
//            // 有手机号的时候
//            // 已授权
//            tvBdwx1.setText(nickName);
//            ivBdwx1.loadImage(avatar, R.drawable.ic_def_loading);
//            ivBdwx1.setVisibility(View.VISIBLE);
//            SPUtils.getInstance().put(CommonUtils.USER_TEL, sLoginUserInfoBean.getPbUser().getPhone());
//            SPUtils.getInstance().put(CommonUtils.USER_TOKEN, sLoginUserInfoBean.getToken());
//            SPUtils.getInstance().put(CommonUtils.USER_NAME, sLoginUserInfoBean.getPbUser().getNickName());
//            SPUtils.getInstance().put(CommonUtils.USER_IMG, sLoginUserInfoBean.getPbUser().getAvatar());
//            setRefresh();
////                if (is_wechat_toast) {
////                    is_wechat_toast = false;
////                    Toasty.normal(Fragment4SettingActivityNew1.this, "已成功绑定微信").show();
////                }
//
//        }
    }

    @Override
    public void OnBindWeChatLoginNodata(String s) {
        Toasty.normal(this, s).show();
    }

    @Override
    public void OnBindWeChatLoginFail(String s) {
        Toasty.normal(this, s).show();
    }

    @Override
    public void OnGetUserInfoSuccess(SLoginUserInfoBean sLoginUserInfoBean) {
        SPUtils.getInstance().put(CommonUtils.USER_TEL, sLoginUserInfoBean.getPbUser().getPhone());
//        SPUtils.getInstance().put(CommonUtils.USER_TOKEN, sLoginUserInfoBean.getToken());
        SPUtils.getInstance().put(CommonUtils.USER_NAME, sLoginUserInfoBean.getPbUser().getNickName());
        SPUtils.getInstance().put(CommonUtils.USER_IMG, sLoginUserInfoBean.getPbUser().getAvatar());
        setRefresh();
        if (TextUtils.isEmpty(sLoginUserInfoBean.getPbUser().getOpenid())) {
            // 此手机没有绑定微信
            tvBdwx1.setText("去绑定");
            set_jt_vis(false);
        } else {
            // 此手机已绑定微信
            tvBdwx1.setText(sLoginUserInfoBean.getPbUser().getWxNickName());
            ivBdwx1.loadImage(sLoginUserInfoBean.getPbUser().getWxAvatar(), R.drawable.ic_def_loading);
            set_jt_vis(true);
//            if (!TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.WECHAT_USER_NAME))) {
//                tvBdwx1.setText(SPUtils.getInstance().getString(CommonUtils.WECHAT_USER_NAME));
//                ivBdwx1.loadImage(SPUtils.getInstance().getString(CommonUtils.WECHAT_USER_IMG), R.drawable.ic_def_loading);
//                ivBdwx1.setVisibility(View.VISIBLE);
//            } else {
//                if (TextUtils.equals(toastMsg1, "获取个人信息成功:")) {
//                    jPushDengluUtils.shezhi_shouquan_getinfo(Wechat.Name);
//                }
//            }
        }
    }

    @Override
    public void OnGetUserInfoNodata(String s) {
        setRefresh();
    }


    @Override
    public void OnGetUserInfoFail(String s) {
        setRefresh();
    }
}
