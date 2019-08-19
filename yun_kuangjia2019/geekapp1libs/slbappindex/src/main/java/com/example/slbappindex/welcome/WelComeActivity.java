package com.example.slbappindex.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ServiceUtils;
import com.example.biz3slbappusercenter.bean.SConfigureBean;
import com.example.biz3slbappusercenter.bean.SWelcomeSettingBean;
import com.example.biz3slbappusercenter.presenter.SConfigurePresenter;
import com.example.biz3slbappusercenter.presenter.SLoginOnlinePresenter;
import com.example.biz3slbappusercenter.presenter.SLoginoutPresenter;
import com.example.biz3slbappusercenter.view.SConfigureView;
import com.example.biz3slbappusercenter.view.SLoginOnlineView;
import com.example.biz3slbappusercenter.view.SLoginoutView;
import com.example.biz3slbappusercenter.view.SWelcomeSettingView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.nativendk.JNIUtils2;
import com.example.slbappcomm.nativendk.KeyReflectUtils;
import com.example.slbappindex.R;
import com.geek.libglide47.demo.commonutil.AppUtil;
import com.haier.cellarette.baselibrary.assetsfitandroid.fileassets.GetAssetsFileMP3TXTJSONAPKUtil;
import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;
import com.haier.cellarette.libutils.utilslib.app.MobileUtils;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libutils.utilslib.data.SpUtils;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;

//import android.support.annotation.Nullable;


public class WelComeActivity extends SlbBaseActivity implements SLoginoutView, SLoginOnlineView, SWelcomeSettingView, SConfigureView {

    private int delayMillis = 15000;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            startActivity(new Intent("hs.act.slbapp.HIOSAdActivity"));
            startActivity(new Intent("hs.act.slbapp.index"));
            finish();
        }
    };
    private int key_token;
    private SLoginoutPresenter presenter_loginout;
    private SLoginOnlinePresenter presenter_loginonline;
    //    private SWelcomeSettingPresenter sWelcomeSettingPresenter;
    private SConfigurePresenter sConfigurePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //虚拟键
        if (NavigationBarUtil.hasNavigationBar(this)) {
//            NavigationBarUtil.initActivity(getWindow().getDecorView());
            NavigationBarUtil.hideBottomUIMenu(this);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, -1);// 支付跳转判断
        SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS, -1);// 支付弹窗判断


        //
        presenter_loginout = new SLoginoutPresenter();
        presenter_loginout.onCreate(this);
        presenter_loginonline = new SLoginOnlinePresenter();
        presenter_loginonline.onCreate(this);
//        sWelcomeSettingPresenter = new SWelcomeSettingPresenter();
//        sWelcomeSettingPresenter.onCreate(this);

        sConfigurePresenter = new SConfigurePresenter();
        sConfigurePresenter.onCreate(this);

        // 听书关闭
        if (!ServiceUtils.isServiceRunning("com.example.slbappcomm.playermusic.ListenMusicPlayerService")) {
            SPUtils.getInstance().put(CommonUtils.LISTENBOOK_TAG1, false);
        } else {
            startActivity(new Intent("hs.act.slbapp.index"));
            finish();
            return;
        }

        if (!MobileUtils.isNetworkConnected(this)) {
            startActivity(new Intent("hs.act.slbapp.index"));
            finish();
            return;
        }
        presenter_loginonline.getLoginOnlineData("start");
//        sWelcomeSettingPresenter.getWelcomeSettingData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), "startSound");
        sConfigurePresenter.getConfigureData();

//        key_token = (int) SpUtils.getInstance(this).get("key_token", -1);
//        JumpWhere();

//        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).playMusic(BaseApp.get(), true, "android.resource://" + getPackageName() + "/" + R.raw.appstart);
        configNDK();
        JNIUtils2 jniUtils = new JNIUtils2();
        MyLogUtil.e("--JNIUtils2--", jniUtils.stringFromJNI());
    }

    private void configNDK() {
        try {
            String aa = (String) KeyReflectUtils.getInstance().invokeMethod("com.sairobo.alty.JNIUtils", "stringFromJNI",
                    new Object[]{});
            MyLogUtil.e("--JNIUtils-反射-", aa);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogUtil.e("--JNIUtils-反射-", e.getMessage());
        }
    }

    private void JumpWhere() {
        //联网 MobileUtils.isNetworkConnected(this)

        if (key_token == -1) {
            SpUtils.getInstance(this).put("key_token", 0);
//            SPUtils.getInstance().put(CommonUtils.READBOOK_AUTOBUTTON, 1);// 设置绘本连播开启
//            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS_TAG, -1);// 支付跳转判断
//            SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS, -1);// 支付弹窗判断
//            presenter_loginout.getLoginoutData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), SPUtils.getInstance().getString(CommonUtils.USER_TEL));

        } else {
            startActivity(new Intent("hs.act.slbapp.index"));
            finish();
        }

        //handler.postDelayed(runnable, delayMillis);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void onDestroy() {
        presenter_loginout.onDestory();
        presenter_loginonline.onDestory();
//        sWelcomeSettingPresenter.onDestory();
        sConfigurePresenter.onDestory();

        super.onDestroy();
        //handler.removeCallbacks(runnable);


    }

    // 退出登录bufen
    @Override
    public void OnLoginoutSuccess(String s) {
        //step 请求服务器成功后清除sp中的数据
        SPUtils.getInstance().put(CommonUtils.USER_TEL, "");
        SPUtils.getInstance().put(CommonUtils.USER_TOKEN, "");
        SPUtils.getInstance().put(CommonUtils.USER_NAME, "");
        SPUtils.getInstance().put(CommonUtils.USER_IMG, "");
        SPUtils.getInstance().put(CommonUtils.READBOOK_AUTOBUTTON, 1);// 设置绘本连播开启
        //
        startActivity(new Intent("hs.act.slbapp.SlbLoginActivity"));
        finish();
    }

    @Override
    public void OnLoginoutNodata(String s) {

    }

    @Override
    public void OnLoginoutFail(String s) {

    }

    @Override
    public void OnLoginOnlineSuccess(String s) {
        MyLogUtil.e("--presenter_loginonline-OnLoginOnlineSuccess-", s);

    }

    @Override
    public void OnLoginOnlineNodata(String s) {
        MyLogUtil.e("--presenter_loginonline-OnLoginOnlineNodata-", s);

    }

    @Override
    public void OnLoginOnlineFail(String s) {
        MyLogUtil.e("--presenter_loginonline-OnLoginOnlineFail-", s);

    }

    // 强制更新bufen
    @Override
    public void OnWelcomeSettingSuccess(SWelcomeSettingBean sWelcomeSettingBean) {
//        if (TextUtils.equals(sWelcomeSettingBean.getAdPage().getKey(), "startSound")) {
//            GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).playMusic(BaseApp.get(), true, sWelcomeSettingBean.getAdPage().getValue());
//            sWelcomeSettingPresenter.getWelcomeSettingData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), "forceLogin");
//            return;
//        }
//        if (TextUtils.equals(sWelcomeSettingBean.getAdPage().getKey(), "forceLogin")) {
//            // 登录
//            if (TextUtils.equals(sWelcomeSettingBean.getAdPage().getValue(), "1")) {
//                SPUtils.getInstance().put("forceLogin", 1);
//                presenter_loginout.getLoginoutData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), SPUtils.getInstance().getString(CommonUtils.USER_TEL));
//            } else if (TextUtils.equals(sWelcomeSettingBean.getAdPage().getValue(), "0")) {
//                startActivity(new Intent("hs.act.slbapp.index"));
//                finish();
//            }
//        } else {
//            startActivity(new Intent("hs.act.slbapp.index"));
//            finish();
//        }

    }

    @Override
    public void OnWelcomeSettingNodata(String s) {
        startActivity(new Intent("hs.act.slbapp.index"));
        finish();
    }

    @Override
    public void OnWelcomeSettingFail(String s) {
        startActivity(new Intent("hs.act.slbapp.index"));
        finish();
    }

    @Override
    public void OnConfigureSuccess(SConfigureBean sConfigureBean) {
        SPUtils.getInstance().put(CommonUtils.START_COUNT, sConfigureBean.getStartSound());
        SPUtils.getInstance().put(CommonUtils.USER_FORCE_LOGIN, sConfigureBean.getForceLogin());
        SPUtils.getInstance().put(CommonUtils.USER_FORCE_CHILD, sConfigureBean.getForceChild());
        commonHandle();
    }

    @Override
    public void OnConfigureNodata(String s) {
//        errorHandle();
        commonHandle();
    }

    @Override
    public void OnConfigureFail(String s) {
//        errorHandle();
        commonHandle();
    }

    private void errorHandle() {
        String startSound = SPUtils.getInstance().getString(CommonUtils.START_COUNT);
        if (!TextUtils.isEmpty(startSound)) {
            GetAssetsFileMP3TXTJSONAPKUtil.getInstance(getApplicationContext()).playMusic(this, true, startSound);
        }
        if (!SlbLoginUtil2.get().isUserLogin() && SPUtils.getInstance().getInt(CommonUtils.APP_VERSION_CODE, -1) != AppUtil.getVersionCode(this)) {//未登录且是新安装（覆盖安装）
            startActivity(new Intent("hs.act.slbapp.SlbLoginActivity"));
        } else {
            startActivity(new Intent("hs.act.slbapp.index"));
        }
        finish();
    }

    private void commonHandle() {
        String startSound = SPUtils.getInstance().getString(CommonUtils.START_COUNT);
        if (!TextUtils.isEmpty(startSound)) {
            GetAssetsFileMP3TXTJSONAPKUtil.getInstance(getApplicationContext()).playMusic(this, true, startSound);
        }
        //新安装或者覆盖安装
        boolean isnNewOrUpdateApp = SPUtils.getInstance().getInt(CommonUtils.APP_VERSION_CODE, -1) != AppUtil.getVersionCode(this);
        if (isnNewOrUpdateApp) {
            SPUtils.getInstance().put(CommonUtils.READBOOK_AUTOBUTTON, 1);// 设置绘本连播开启
        }
        if (isnNewOrUpdateApp && !SlbLoginUtil2.get().isUserLogin()) {//新安装或者覆盖安装  且未登录
            startActivity(new Intent("hs.act.slbapp.SlbLoginActivity"));//去登录
        } else {
            startActivity(new Intent("hs.act.slbapp.index"));//去首页
        }
        finish();
    }
}