package com.example.slbapplogin.winter;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.biz3slbappusercenter.bean.SConfigureBean;
import com.example.biz3slbappusercenter.bean.SLoginUserInfoBean;
import com.example.biz3slbappusercenter.presenter.SConfigurePresenter;
import com.example.biz3slbappusercenter.presenter.SLoginOnlinePresenter;
import com.example.biz3slbappusercenter.presenter.SLoginPresenter;
import com.example.biz3slbappusercenter.presenter.SSendVCodePresenter;
import com.example.biz3slbappusercenter.view.SConfigureView;
import com.example.biz3slbappusercenter.view.SLoginOnlineView;
import com.example.biz3slbappusercenter.view.SLoginView;
import com.example.biz3slbappusercenter.view.SSendVCodeView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbapplogin.R;
import com.example.slbapplogin.utils.AppPermissionUtil;
import com.example.slbapplogin.utils.SMSBroadcastReceiver;
import com.example.slbapplogin.utils.SmsObserver;
import com.example.slbapplogin.utils.YanzhengUtil;
import com.example.slbappshare.denglu.JPushDengluUtils;
import com.example.slbappshare.denglu.OnResultInfoLitener;
import com.geek.libglide47.demo.commonutil.AppUtil;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.loading.ShowLoadingUtil;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.AlertView;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class SlbLoginActivity extends SlbBaseActivity implements View.OnClickListener, SLoginView, SSendVCodeView, SLoginOnlineView, SConfigureView, EasyPermissions.PermissionCallbacks {

    private RelativeLayout afl1;
    private TextView tv_tips12;
    private ImageView iv1;
    private ImageView iv_bg1;
    private ImageView iv_wx;
    private RelativeLayout rl1;
    private EditText edt1;
    private EditText edt2;
    private Button tv_hqyzm;
    private Button tv1;

    private SLoginPresenter presenter;
    private SSendVCodePresenter presentercode;
    private SLoginOnlinePresenter presenter_loginonline;
    private SConfigurePresenter sConfigurePresenter;
    //
    private JPushDengluUtils jPushDengluUtils;
    private String openid;
    private String unionid;
    private String gender;
    private String nickName;
    private String avatar;
    private String other_login_name = "Wechat";
    private HandlerThread mHandlerThread;
    private Handler mHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_slblogin;
    }

//    /**
//     * 点击软键盘外面的区域关闭软键盘
//     *
//     * @param ev
//     * @return
//     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
//            View v = getCurrentFocus();
//            if (isShouldHideInput(v, ev)) {
//                if (getCurrentFocus() != null) {
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(),
//                            InputMethodManager.HIDE_NOT_ALWAYS);
//                }
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }
//
//    /**
//     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
//     *
//     * @param v
//     * @param event
//     * @return
//     */
//    private boolean isShouldHideInput(View v, MotionEvent event) {
//        if (v != null && (v instanceof EditText)) {
//            int[] l = {0, 0};
//            v.getLocationInWindow(l);
//            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
//                    + v.getWidth();
//            if (event.getX() > left && event.getX() < right
//                    && event.getY() > top && event.getY() < bottom) {
//                // 点击EditText的事件，忽略它。
//                return false;
//            } else {
//                return true;
//            }
//        }
//        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
//        return false;
//    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findview();
        onclick();
        donetwork();
        //
        MobclickAgent.onEvent(this, "SlbLoginActivity");
    }

    private void donetwork() {
        //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_tips12.setText(Html.fromHtml("登录即代表您同意" + "<font color=\"#4EC0FF\">用户协议</font>" + "和" + "<font color=\"#4EC0FF\">隐私政策</font>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv_tips12.setText(Html.fromHtml("登录即代表您同意" + "<font color=\"#4EC0FF\">用户协议</font>" + "和" + "<font color=\"#4EC0FF\">隐私政策</font>"));
        }
        //
        if (TextUtils.equals("1", SPUtils.getInstance().getString(CommonUtils.USER_FORCE_LOGIN))) {
            iv1.setVisibility(View.INVISIBLE);
        } else {
            iv1.setVisibility(View.VISIBLE);
        }
        presenter = new SLoginPresenter();
        presenter.onCreate(this);
        presentercode = new SSendVCodePresenter();
        presentercode.onCreate(this);
        presenter_loginonline = new SLoginOnlinePresenter();
        presenter_loginonline.onCreate(this);

        sConfigurePresenter = new SConfigurePresenter();
        sConfigurePresenter.onCreate(this);
        sConfigurePresenter.getConfigureData();
//        jPushDengluUtils.shouquan_cancel(other_login_name);
        set_sms2();
    }

//    private void set_sms() {
//        mHandlerThread = new HandlerThread("myHandlerThreadSlbLoginActivity");
//        mHandlerThread.start();
//        //在这个线程中创建一个handler对象
//        mHandler = new Handler(mHandlerThread.getLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                if (msg.what == MSG_RECEIVED_CODE) {
//                    String code = (String) msg.obj;
//                    edt2.setText(code);
//                }
//            }
//        };
//        mObserver = new SmsObserver(SlbLoginActivity.this, mHandler);
//        Uri uri = Uri.parse("content://sms");
//        //注册短信的监听
//        getContentResolver().registerContentObserver(uri, true, mObserver);
//    }

    private void onclick() {
        tv_tips12.setOnClickListener(this);
        iv1.setOnClickListener(this);
        iv_wx.setOnClickListener(this);
        tv_hqyzm.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                // 获取验证码bufen
                hqyzm();
                // 自动填写短信
//                smsTask();
                //
//                PermissionsUtil.requestPermission(SlbLoginActivity.this, new com.github.dfqin.grantor.PermissionListener() {
//                    @Override
//                    public void permissionGranted(@NonNull String[] permissions) {
////                        Toast.makeText(MainActivity.this, "访问消息", Toast.LENGTH_LONG).show();
//                        // 获取验证码bufen
//                        hqyzm();
//                    }
//
//                    @Override
//                    public void permissionDenied(@NonNull String[] permissions) {
////                        Toast.makeText(MainActivity.this, "用户拒绝了读取消息权限", Toast.LENGTH_LONG).show();
////                        Toasty.normal(SlbLoginActivity.this, "您阻止了app读取您的短信，您可以自己手动输入验证码").show();
//                        edt2.requestFocus();
//                    }
//                }, new String[]{
//                        Manifest.permission.READ_SMS
//                }, false, null);
                //
//                if (ContextCompat.checkSelfPermission(SlbLoginActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(SlbLoginActivity.this, new String[]{
//                            Manifest.permission.READ_SMS,
//                            Manifest.permission.RECEIVE_SMS
////                            Manifest.permission.READ_CONTACTS,
//                    }, REQUEST_PERMISSION_CODE);
//                } else {
//                    // 获取验证码bufen
//                    String aaaa = "";
//                    hqyzm();
//                }
            }
        });
        tv1.setOnClickListener(this);
        jPushDengluUtils = new JPushDengluUtils(new OnResultInfoLitener() {
            @Override
            public void onResults(String platform, String toastMsg, String data) {
//                Toasty.normal(BaseApp.get(), platform + "---" + toastMsg + "---" + data).show();
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
                presenter.getWeChatLoginData(openid, unionid, gender, nickName, avatar);
            }
        });
        edt1.addTextChangedListener(textWatcher);
        edt2.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String a = edt1.getText().toString().trim();
            String b = edt2.getText().toString().trim();
            if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(b) && charSequence.length() > 0) {
                tv1.setEnabled(true);
                tv1.setBackgroundResource(R.drawable.btn_denglu2);
            } else {
                tv1.setEnabled(false);
                tv1.setBackgroundResource(R.drawable.btn_denglu2_enpressed);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void findview() {
        afl1 = findViewById(R.id.afl1);
        tv_tips12 = findViewById(R.id.tv_tips12);
        iv1 = findViewById(R.id.iv1);
        iv_bg1 = findViewById(R.id.iv_bg1);
        iv_wx = findViewById(R.id.iv_wx);
        rl1 = findViewById(R.id.rl1);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        tv_hqyzm = findViewById(R.id.tv_hqyzm);
        tv1 = findViewById(R.id.tv1);
        tv1.setEnabled(false);
        tv1.setBackgroundResource(R.drawable.btn_denglu2_enpressed);

    }

    public static final int MSG_RECEIVED_CODE = 1;
    private static final int REQUEST_PERMISSION_CODE = 1005;
    private SmsObserver mObserver;


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private static final int RC_SMS_PERM = 122;

    @AfterPermissionGranted(RC_SMS_PERM)
    private void smsTask() {
        if (EasyPermissions.hasPermissions(SlbLoginActivity.this, Manifest.permission.READ_SMS)) {
            // Have permission, do the thing!
//            Toast.makeText(SlbLoginActivity.this, "TODO: SMS things", Toast.LENGTH_LONG).show();
            // 获取验证码bufen
            hqyzm();
        } else {
            // Request one permission
//            Toasty.normal(SlbLoginActivity.this, "您阻止了app读取您的短信，您可以自己手动输入验证码").show();
//           EasyPermissions.requestPermissions(SlbLoginActivity.this, "合象阅读正在尝试读取短信", RC_SMS_PERM, Manifest.permission.READ_SMS);
            final AlertView dialog = new AlertView(this, "温馨提示", "需要开启读取短信相关的权限",
                    "取消", "确定");
            dialog.show();
            dialog.setClicklistener(new AlertView.ClickListenerInterface() {
                                        @Override
                                        public void doLeft() {
                                            dialog.dismiss();
                                        }

                                        @Override
                                        public void doRight() {
                                            AppPermissionUtil.getInstance(getApplicationContext()).startAppSettings();
                                            dialog.dismiss();

                                        }
                                    }
            );
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        MyLogUtil.d("SMS1", "onPermissionsGranted:" + requestCode + ":" + perms.size());
        // 获取验证码bufen
        hqyzm();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        MyLogUtil.d("SMS1", "onPermissionsDenied:" + requestCode + ":" + perms.size());
//        Toasty.normal(SlbLoginActivity.this, "您阻止了app读取您的短信，您可以自己手动输入验证码").show();
        edt2.requestFocus();
    }

    @Override
    protected void onActResult(int requestCode, int resultCode, Intent data) {
        super.onActResult(requestCode, resultCode, data);
        if (requestCode == 10016) {
            // 获取验证码bufen
            hqyzm();
        }
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv1) {
            // 登录bufen
            //
            MobclickAgent.onEvent(this, "login_button");
            if (!is_login()) {
                return;
            }
            String b = edt2.getText().toString().trim();
            if (TextUtils.isEmpty(b)) {
                Toasty.normal(this, "请输入验证码").show();
                return;
            }
            denglu();
        } else if (i == R.id.iv1) {
            // 关闭bufen
            MobclickAgent.onEvent(this, "login_close_button");
            no_denglu();
        } else if (i == R.id.iv_wx) {
            // 微信登录
            wxdl();
        } else if (i == R.id.tv_tips12) {
            // 隐私
            HiosHelper.resolveAd(SlbLoginActivity.this, SlbLoginActivity.this, "http://hexiang-app.sairobo.cn/user-notice/privacy-policy.html");
        } else {

        }
    }

    private void onLoginSuccess() {
        setResult(SlbLoginUtil2.LOGIN_RESULT_OK);
        if (BaseAppManager.getInstance().getAll().size() == 1) {
            startActivity(new Intent("hs.act.slbapp.index"));
        }
        finish();
    }

    private void onLoginCanceled() {
        setResult(SlbLoginUtil2.LOGIN_RESULT_CANCELED);
        if (BaseAppManager.getInstance().getAll().size() == 1) {
            startActivity(new Intent("hs.act.slbapp.index"));
        }
        finish();
    }

    /**
     * 不登录操作
     */
    private void donetloginno() {
        //step 请求服务器成功后清除sp中的数据
//        SpUtils.get(this).get("", "");
        onLoginCanceled();
    }

    private boolean is_login() {
        String a = edt1.getText().toString().trim();
        if (TextUtils.isEmpty(a)) {
            Toasty.normal(this, "请输入您的手机号").show();
            return false;
        }
        if (!RegexUtils.isMobileSimple(a)) {
            Toasty.normal(this, "手机号格式不正确").show();
            return false;
        }
        return true;
    }

    /**
     * 获取验证码
     */
    private void hqyzm() {
        if (!is_login()) {
            return;
        }
//        tv1.setEnabled(true);
//        tv1.setBackgroundResource(R.drawable.slb_btncomm_pressed2);
        YanzhengUtil.startTime(60 * 1000, tv_hqyzm);
        //接口通了后执行下一步
        presentercode.getSendVCodeData(edt1.getText().toString().trim());

    }

    private SMSBroadcastReceiver mSMSBroadcastReceiver = new SMSBroadcastReceiver();

    private void set_sms2() {
        // 注册广播
        IntentFilter intentFilter = new IntentFilter(SMSBroadcastReceiver.SMS_RECEIVED_ACTION);
        // 设置优先级
        intentFilter.setPriority(Integer.MAX_VALUE);
        registerReceiver(mSMSBroadcastReceiver, intentFilter);
        mSMSBroadcastReceiver.setOnReceiveSMSListener(new SMSBroadcastReceiver.OnReceiveSMSListener() {
            @Override
            public void onReceived(String message) {
                edt2.setText(message + "");
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        presentercode.onDestory();
        presenter_loginonline.onDestory();
        YanzhengUtil.timer_des();
        jPushDengluUtils.ondes();
        sConfigurePresenter.onDestory();
        SPUtils.getInstance().put(CommonUtils.APP_VERSION_CODE, AppUtil.getVersionCode(this));
        if (mHandler != null) {
            mHandlerThread.quit();
            mHandler.removeCallbacksAndMessages(null);
        }
        //解除注册短信的监听
        if (mObserver != null) {
            getContentResolver().unregisterContentObserver(mObserver);
        }
        unregisterReceiver(mSMSBroadcastReceiver);
        super.onDestroy();

    }

    /**
     *
     *
     * ----------------------------------下面为业务逻辑--分割线------------------------------
     *
     */


    /**
     * 登录
     */
    private void denglu() {
//        Toasty.normal(this, "登录").show();
        presenter.getLoginData(edt1.getText().toString().trim(), "", edt2.getText().toString().trim());

    }

    /**
     * 不登录 返回原来的
     */
    private void no_denglu() {
//        Toasty.normal(this, "不登录").show();
        donetloginno();
//        startActivity(new Intent("hs.act.slbapp.index"));

    }


    @Override
    public void OnLoginSuccess(SLoginUserInfoBean sLoginUserInfoBean, String tag) {
        ShowLoadingUtil.onDestory();
        // 微信登录bufen
        if (tag.equals(SLoginPresenter.TAG2)) {
            // 如果是微信登录则跳转到绑定手机页面
//            ShowLoadingUtil.onDestory();
            if (sLoginUserInfoBean.getPbUser().isNoPhone()) {
                //
                Intent intent = new Intent("hs.act.slbapp.SlbLoginTelActivity");
                intent.putExtra("tempKey", sLoginUserInfoBean.getTempKey());
                startActivity(intent);
                finish();
                return;
            }
        }
        //step 手机号登录bufen
        SPUtils.getInstance().put(CommonUtils.USER_TEL, sLoginUserInfoBean.getPbUser().getPhone());
        SPUtils.getInstance().put(CommonUtils.USER_TOKEN, sLoginUserInfoBean.getToken());
        presenter_loginonline.getLoginOnlineData("login");
        SPUtils.getInstance().put(CommonUtils.USER_NAME, sLoginUserInfoBean.getPbUser().getNickName());
        SPUtils.getInstance().put(CommonUtils.USER_IMG, sLoginUserInfoBean.getPbUser().getAvatar());
        if (!sLoginUserInfoBean.getPbUser().isHasChild()) {
            startActivity(new Intent("hs.act.slbapp.SlbLoginInfoActivity"));
            setResult(SlbLoginUtil2.LOGIN_RESULT_OK);
            finish();
        } else {
            onLoginSuccess();
        }

    }

    @Override
    public void OnLoginNodata(String s) {
        Toasty.normal(this, s).show();
    }

    @Override
    public void OnLoginFail(String s) {
        Toasty.normal(this, s).show();
    }

    // 获取验证码bufen
    @Override
    public void OnSendVCodeSuccess(String s) {
        Toasty.normal(this, s).show();
        edt2.requestFocus();
    }

    @Override
    public void OnSendVCodeNodata(String s) {
        Toasty.normal(this, s).show();
    }

    @Override
    public void OnSendVCodeFail(String s) {
        Toasty.normal(this, s).show();
    }


    // 微信登录
    private void wxdl() {
//        jPushDengluUtils.shouquan(other_login_name);
        ShowLoadingUtil.showLoading(SlbLoginActivity.this, "", null);
        jPushDengluUtils.shezhi_shouquan_getinfo(other_login_name);
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

    @Override
    public void OnConfigureSuccess(SConfigureBean sConfigureBean) {
//        SPUtils.getInstance().put(CommonUtils.START_COUNT, sConfigureBean.getStartSound());
//        SPUtils.getInstance().put(CommonUtils.USER_FORCE_LOGIN, sConfigureBean.getForceLogin());
//        SPUtils.getInstance().put(CommonUtils.USER_FORCE_CHILD, sConfigureBean.getForceChild());
//        if (TextUtils.equals(sConfigureBean.getForceLogin(), "1")) {
//            tv1.setVisibility(View.INVISIBLE);
//        } else {
//            tv1.setVisibility(View.VISIBLE);
//        }
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.slb_lunbo1)
                .error(R.drawable.slb_login_bg1)
                .fallback(R.drawable.slb_login_bg1); //url为空的时候,显示的图片;
        Glide.with(this).load(sConfigureBean.getAppLoginBg()).apply(options).into(iv_bg1);

    }

    @Override
    public void OnConfigureNodata(String s) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.slb_login_bg1)
                .error(R.drawable.slb_login_bg1)
                .fallback(R.drawable.slb_login_bg1); //url为空的时候,显示的图片;
        Glide.with(this).load("").apply(options).into(iv_bg1);
    }

    @Override
    public void OnConfigureFail(String s) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.slb_login_bg1)
                .error(R.drawable.slb_login_bg1)
                .fallback(R.drawable.slb_login_bg1); //url为空的时候,显示的图片;
        Glide.with(this).load("").apply(options).into(iv_bg1);
    }
}
