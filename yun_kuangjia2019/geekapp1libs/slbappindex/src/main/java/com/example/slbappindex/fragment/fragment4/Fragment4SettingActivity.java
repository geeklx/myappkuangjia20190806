package com.example.slbappindex.fragment.fragment4;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.example.biz3slbappusercenter.bean.SAppUpgradeBean;
import com.example.biz3slbappusercenter.presenter.SAppUpgradePresenter;
import com.example.biz3slbappusercenter.view.SAppUpgradeView;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.huyan.Huyanservices;
import com.example.slbappindex.R;
import com.github.commonlibs.libupdateapputils.util.UpdateAppUtils;
import com.haier.cellarette.baselibrary.cacheutil.CacheUtil;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.qcode.ExpandViewRect;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.AlertView;
import com.haier.cellarette.baselibrary.widget.LxLinearLayout;
import com.haier.cellarette.baselibrary.widget.SwitchButton;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;

public class Fragment4SettingActivity extends SlbBaseActivity implements View.OnClickListener, SwitchButton.OnCheckedChangeListener,
        SAppUpgradeView {

    private TextView tv_left;
    private TextView tvCenter;
    private LxLinearLayout ll_huyan1;
    private SwitchButton slbSb1;
    private LinearLayout ll1;
    private TextView tv1;
    private TextView tv_versoncode;
    private LinearLayout ll_xgmm;
    private LinearLayout ll_tcdl;
    private TextView tv_login3out2;
    private LinearLayout ll_jcgx;
    private SAppUpgradePresenter presenter;
    private String apkPath = "";
    private int serverVersionCode = 0;
    private String serverVersionName = "";
    private String updateInfoTitle = "";
    private String updateInfo = "";
    private String md5 = "";
    private String appPackageName = "";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment4_setting;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
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
        tv_left.setVisibility(View.VISIBLE);
        tvCenter.setText("设置");
        ExpandViewRect.expandViewTouchDelegate(tv_left, 20, 20, 20, 20);
        try {
            tv1.setText(CacheUtil.getTotalCacheSize(BaseApp.get()));
        } catch (Exception e) {
            tv1.setText("0.00B");
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

    }

    private void onclick() {
        tv_left.setOnClickListener(this);
        ll1.setOnClickListener(this);
        ll_xgmm.setOnClickListener(this);
        ll_tcdl.setOnClickListener(this);
        ll_jcgx.setOnClickListener(this);
        slbSb1.setOnCheckedChangeListener(this);
        ll_huyan1.setOnClickListener(this);

    }

    private void findview() {
        tv_left = findViewById(R.id.tv_left);
        tvCenter = findViewById(R.id.tv_center);
        ll_huyan1 = findViewById(R.id.ll_huyan1);
        ll_huyan1.setTouch(true);
        slbSb1 = findViewById(R.id.slb_sb1);
        ll1 = findViewById(R.id.ll1);
        ll_xgmm = findViewById(R.id.ll_xgmm);
        ll_tcdl = findViewById(R.id.ll_tcdl);
        tv_login3out2 = findViewById(R.id.tv_login3out2);
        ll_jcgx = findViewById(R.id.ll_jcgx);
        tv1 = findViewById(R.id.tv1);
        tv_versoncode = findViewById(R.id.tv_versoncode);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_left) {
            onBackPressed();
        } else if (i == R.id.ll_xgmm) {
            // 修改资料bufen
            // 微信登录
//            JPushDengluUtils JPushDengluUtils = new JPushDengluUtils(new OnResultInfoLitener() {
//                @Override
//                public void onResults(String platform, String toastMsg, String data) {
//                    Toasty.normal(BaseApp.get(), platform + "---" + toastMsg + "---" + data).show();
//                }
//            });
//            JPushDengluUtils.shezhi_shouquan_getinfo("Wechat");
            // 分享
//            JPushShareUtils jPushShareUtils = new JPushShareUtils(new OnShareResultInfoLitener() {
//                @Override
//                public void onResults(String platform, String toastMsg, String data) {
//                    Toasty.normal(getApplicationContext(), toastMsg, Toast.LENGTH_LONG).show();
//                }
//            });
//            JShareInterface.share("Wechat",
//                    WeixinBeanParam.share_web2(
//                            jPushShareUtils.share_title,
//                            jPushShareUtils.share_text,
//                            jPushShareUtils.share_url,
//                            jPushShareUtils.drawableToBitmap(Fragment4SettingActivity.this, getDrawable(R.drawable.geek_icon))),
//                    jPushShareUtils.mShareListener1);
            //
//            startActivity(new Intent("hs.act.slbapp.ShareIndexActivity"));
            SlbLoginUtil2.get().loginTowhere(this, new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent("hs.act.slbapp.SlbLoginInfoActivity"));

                }
            });

        } else if (i == R.id.ll_tcdl) {
            String aaa = tv_login3out2.getText().toString().trim();
            if (TextUtils.equals(aaa, getResources().getString(R.string.slb_my_tip55))) {
                // 退出登录bufen
                SlbLoginUtil2.get().loginOutTowhere(Fragment4SettingActivity.this, new Runnable() {
                    @Override
                    public void run() {
                        // 刷新个人中心bufen
                        tv_login3out2.setText(getResources().getString(R.string.slb_my_tip555));
                        onBackPressed();

                    }
                });
            }
            if (TextUtils.equals(aaa, getResources().getString(R.string.slb_my_tip555))) {
                // 登录bufen
                SlbLoginUtil2.get().loginTowhere(Fragment4SettingActivity.this, new Runnable() {
                    @Override
                    public void run() {
                        // 刷新个人中心bufen
                        tv_login3out2.setText(getResources().getString(R.string.slb_my_tip55));
                        onBackPressed();

                    }
                });
            }
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
                                                            Uri.parse("package:" + Fragment4SettingActivity.this.getPackageName()));
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
        } else if (i == R.id.ll1) {
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
                                            CacheUtil.clearAllCache(BaseApp.get());
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        tv1.setText(CacheUtil.getTotalCacheSize(BaseApp.get()));
                                                    } catch (Exception e) {
                                                        tv1.setText("0.00B");
                                                    }
                                                }
                                            }, 500);
                                            dialog.dismiss();

                                        }
                                    }
            );
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
                    Toasty.normal(Fragment4SettingActivity.this, "请开启显示悬浮窗权限").show();
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
                    Toasty.normal(Fragment4SettingActivity.this, "Permission Denied").show();
                } else {
                    set_check_huyan();
                }
            } else {
                set_check_huyan();
            }
        }
    }

//    private UpdateAppReceiver updateAppReceiver = new UpdateAppReceiver();

    @Override
    protected void onResume() {
//        updateAppReceiver.setBr(this);
        if (SlbLoginUtil2.get().isUserLogin()) {
            tv_login3out2.setText(getResources().getString(R.string.slb_my_tip55));
        } else {
            tv_login3out2.setText(getResources().getString(R.string.slb_my_tip555));
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
//        updateAppReceiver.desBr(this);
//        stopService(new Intent(this, Huyanservices.class));
//        SPUtils.getInstance().put("huyan", false);
        super.onDestroy();
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
        serverVersionName = sAppUpgradeBean.getAppUpgrade().getName();
        tv_versoncode.setText("V" + serverVersionName);
        updateInfoTitle = sAppUpgradeBean.getAppUpgrade().getTitle();
        updateInfo = sAppUpgradeBean.getAppUpgrade().getUpgradeInfo();
    }

    @Override
    public void OnAppUpgradeNodata(String s) {
//        StringBuffer stringBuilder1 = new StringBuffer(serverVersionCode + "");//100 1.00
//        tv_versoncode.setText("V" + stringBuilder1.insert(1, ".").toString());
        tv_versoncode.setText("V" + serverVersionName);
    }

    @Override
    public void OnAppUpgradeFail(String s) {
        tv_versoncode.setText("V" + serverVersionName);
    }

}
