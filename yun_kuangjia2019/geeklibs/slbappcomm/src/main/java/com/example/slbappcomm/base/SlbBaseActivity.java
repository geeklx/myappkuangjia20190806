/**
 * Copyright 2016,Smart Haier.All rights reserved
 */
package com.example.slbappcomm.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
//import android.support.annotation.IdRes;
//import android.support.annotation.Nullable;
//import android.support.v4.content.ContextCompat;
//import android.support.v4.view.LayoutInflaterCompat;
//import android.support.v4.view.LayoutInflaterFactory;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.LayoutInflaterFactory;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.example.slbappcomm.R;
import com.example.slbappcomm.huyan.Huyanservices;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.common.BaseViewHelper;
import com.haier.cellarette.baselibrary.networkview.NetState;
import com.haier.cellarette.baselibrary.networkview.NetconListener2;
import com.haier.cellarette.baselibrary.smartbar.IBaseAction;
import com.haier.cellarette.baselibrary.statusbar.StatusBarUtilV7;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;
import com.umeng.analytics.MobclickAgent;

import cn.jpush.android.api.JPushInterface;


public abstract class SlbBaseActivity extends AppCompatActivity implements IBaseAction, NetconListener2 {

    public static final String REQUEST_CODE = "request_code";

    private long mCurrentMs = System.currentTimeMillis();
//    private Handler handler;
    protected NetState netState;
    protected Typeface tfLight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        interceptCreateView();// 横屏时禁止输入法全屏
//        hideBottomUIMenu();
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        BaseAppManager.getInstance().add(this);
//        AnnotateUtils.injectViews(this);
//        handler = new Handler();
        setContentView(getLayoutId());
        //        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.color_568EC0));
        StatusBarUtilV7.immersive(this, ContextCompat.getColor(this, R.color.color_4DA3FE), 1.0f);// color_E5F7FF
        setup(savedInstanceState);

        //网络监听
        netState = new NetState();
        netState.setNetStateListener(this, this);
        // 听书监听
//        set_lb_floatbutton_init();
        // 字体
        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");


    }

    private void interceptCreateView() {
        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                AppCompatDelegate delegate = getDelegate();
                View view = delegate.createView(parent, name, context, attrs);
                if (view != null && view instanceof EditText) {
                    Log.d("***", "IME_FLAG_NO_EXTRACT_UI");
                    EditText et = (EditText) view;
                    et.setImeOptions(et.getImeOptions() | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
                    return et;
                }
                return view;
            }
        });
    }

    private long lastTime;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) { HookUtil.hookClick(this);}

    }

    public abstract class OnMultiClickListener implements View.OnClickListener {
        // 两次点击按钮之间的点击间隔不能少于1000毫秒
        private final int MIN_CLICK_DELAY_TIME = 1500;
        private long lastClickTime;

        public abstract void onMultiClick(View v);

        @Override
        public void onClick(View v) {
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                // 超过点击间隔后再将lastClickTime重置为当前点击时间
                lastClickTime = curClickTime;
                onMultiClick(v);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
//            MobEventHelper.onEvent(this, "effective_click");
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
//        MobEvent.onResume(this);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
        MobclickAgent.onPause(this);
//        MobEvent.onPause(this);
        AppUtils.registerAppStatusChangedListener(this, new Utils.OnAppStatusChangedListener() {
            @Override
            public void onForeground() {
                // 前台
                // 护眼模式bufen
                if (SPUtils.getInstance().getBoolean("huyan", false)) {
                    startService(new Intent(SlbBaseActivity.this, Huyanservices.class));
                }
            }

            @Override
            public void onBackground() {
                // 后台
                if (SPUtils.getInstance().getBoolean("huyan", false)) {
                    stopService(new Intent(SlbBaseActivity.this, Huyanservices.class));
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    protected abstract int getLayoutId();

    protected void setup(@Nullable Bundle savedInstanceState) {

    }


    @Override
    public void net_con_none() {

    }

    @Override
    public void net_con_success() {
    }

    @Override
    public void showNetPopup() {
    }

//    /**
//     * 监听听书悬浮框变化bufen
//     */
//    private FloatButtonReceiverListenBooks floatButtonReceiverListenBooks;
//
//    private void set_lb_floatbutton_init() {
//        // 听书部分
//        floatButtonReceiverListenBooks = new FloatButtonReceiverListenBooks();
//        IntentFilter filter_lb = new IntentFilter();
//        filter_lb.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
//        filter_lb.addAction(CommonUtils.listen_action);
//        LocalBroadcastManagers.getInstance(BaseApp.get()).registerReceiver(floatButtonReceiverListenBooks, filter_lb);
//        //
//        if (TextUtils.equals("com.example.slbappindex.order.OrderPayActivity", this.getClass().getName()) ||
//                TextUtils.equals("com.example.slbapplistenbook.actvity.ListenMusicActivity", this.getClass().getName())) {
//            return;
//        }
//        DragFloatActionInject.inject(this);
//    }
//
//    /**
//     * 监听本地图片变化bufen
//     */
//    public class FloatButtonReceiverListenBooks extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            try {
//                if (CommonUtils.listen_action.equals(intent.getAction())) {
//                    // 播放听书
//                    String float_id_zong = intent.getStringExtra(CommonUtils.HUIBEN_IDS_ZONG);
//                    String float_id = intent.getStringExtra(CommonUtils.HUIBEN_IDS);
//                    String float_name = intent.getStringExtra(CommonUtils.HUIBEN_TITLES);
//                    ArrayList<String> float_xmly = intent.getStringArrayListExtra(CommonUtils.HUIBEN_XMLY);
//                    // 听书悬浮框监听
//                    DragFloatActionInject.inject((Activity) context).setFloat_action("hs.act.slbapp.ListenMusicActivity");
//                    DragFloatActionInject.inject((Activity) context).setFloat_id_zong(float_id_zong);
//                    DragFloatActionInject.inject((Activity) context).setFloat_id(float_id);
//                    DragFloatActionInject.inject((Activity) context).setFloat_name(float_name);
//                    DragFloatActionInject.inject((Activity) context).setFloat_xmly(float_xmly);
//                }
//            } catch (Exception e) {
//            }
//        }
//    }

    protected final <T extends View> T f(@IdRes int resId) {
        return BaseViewHelper.f(this, resId);
    }

    public void startActivity(Class<? extends Activity> activity) {
        startActivity(new Intent(this, activity));
    }

    public void startActivity(String action) {
        startActivity(new Intent(action));
    }

    public void startActivityForResult(Class<? extends Activity> activity, int requestCode) {
        startActivityForResult(new Intent(this, activity), requestCode);
    }

    public void startActivityForResult(String action, int requestCode) {
        startActivityForResult(new Intent(action), requestCode);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (intent.resolveActivity(getPackageManager()) == null) {
            Log.e("Activity", "No Activity found to handle intent " + intent);
            return;
        }

        if (requestCode != -1 && intent.getIntExtra(REQUEST_CODE, -1) == -1) {
            intent.putExtra(REQUEST_CODE, requestCode);
        }

        super.startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转到指定activity，如果未登录，则弹出登录窗口
     *
     * @param activity
     */
    public void targetToIfLogin(final Class<? extends SlbBaseActivity> activity) {
//        UserUtils.get().loginToDo(this, new Runnable() {
//            @Override
//            public void run() {
//                startActivity(activity);
//            }
//        });
    }

    /**
     * 跳转到指定activity，如果未登录，则弹出登录窗口
     *
     * @param intent
     */
    public void targetToIfLogin(final Intent intent) {
//        UserUtils.get().loginToDo(this, new Runnable() {
//            @Override
//            public void run() {
//                startActivity(intent);
//            }
//        });
    }

    public void targetToIfLogin(final String action) {
//        UserUtils.get().loginToDo(this, new Runnable() {
//            @Override
//            public void run() {
//                startActivity(action);
//            }
//        });
    }

    /**
     * 跳转到指定activity，如果未登录，则弹出登录窗口
     *
     * @param intent
     */
    public void targetToForResultIfLogin(final Intent intent, final int requestCode) {
//        UserUtils.get().loginToDo(this, new Runnable() {
//            @Override
//            public void run() {
//                startActivityForResult(intent, requestCode);
//            }
//        });
    }

    public void targetToForResultIfLogin(final String action, final int requestCode) {
//        UserUtils.get().loginToDo(this, new Runnable() {
//            @Override
//            public void run() {
//                startActivityForResult(action, requestCode);
//            }
//        });
    }

    @Override
    protected final void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (UserUtils.get().onActivityResult(requestCode, resultCode, data)) {
//            if (UserUtils.get().isUserLogin()) {
//                onUserLogined(UserUtils.get().userId());
//            }
//            return;
//        }
//        if (LoginUtil.get().login_activity_result(requestCode, resultCode, data)) {
////            if (LoginUtil.get().isUserLogin()) {
////                onUserLogined(LoginUtil.get().userId());
////            } else {
////                onUserLoginCanceled();
////            }
//            return;
//        }
        //登录和未登录成功状态
        if (SlbLoginUtil2.get().login_activity_result(requestCode, resultCode, data)) {
//            if (LoginUtil.get().isUserLogin()) {
//                onUserLogined(LoginUtil.get().userId());
//            } else {
//                onUserLoginCanceled();
//            }
            if (isIs_finish_login()) {
                finish();
                setIs_finish_login(false);
            } else {
            }
            return;
        }
        //正常状态
        onActResult(requestCode, resultCode, data);
    }

    public boolean is_finish_login;

    public boolean isIs_finish_login() {
        return is_finish_login;
    }

    public void setIs_finish_login(boolean is_finish_login) {
        this.is_finish_login = is_finish_login;
    }

    protected void onUserLogined(String userId) {
    }

    protected void onActResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void finish() {
        hideSoftKeyboard();
//        ShowLoadingUtil.onDestory();
        super.finish();
        BaseAppManager.getInstance().remove(this);
//        overridePendingTransition(R.anim.open_main, R.anim.close_next);
        // 听书悬浮框bufen
//        LocalBroadcastManagers.getInstance(BaseApp.get()).unregisterReceiver(floatButtonReceiverListenBooks);
    }

    @Override
    protected void onDestroy() {
        BaseAppManager.getInstance().remove(this);
        if (netState != null) {
            netState.unregisterReceiver();
        }
//        ShowLoadingUtil.onDestory();
        AppUtils.unregisterAppStatusChangedListener(this);
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onHomePressed() {
//        finish();
//        while (!BaseAppManager.getInstance().getAll().isEmpty()) {
//            BaseAppManager.getInstance().top().finish();
//        }

//        Stack<Activity> all = BaseAppManager.getInstance().getAll();
//        for (Iterator<Activity> iterator = all.iterator(); iterator.hasNext();) {
//            iterator.next().finish();
//        }
        while (!BaseAppManager.getInstance().getAll().isEmpty()) {
            BaseAppManager.getInstance().top().finish();
        }
        BaseAppManager.getInstance().clear();
        Intent intent = new Intent("hs.act.slbapp.index");
        startActivity(intent);
        finish();

//        Application app = BaseApp.get();
//        if (app instanceof AndroidApplication) {
//            ((AndroidApplication) app).onHomePressed();
//        }
    }

    /**
     * 隐藏软键盘
     */
    protected void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public Activity who() {
        return this;
    }

    public String getIdentifier() {
        return getClass().getName() + mCurrentMs;
    }
}
