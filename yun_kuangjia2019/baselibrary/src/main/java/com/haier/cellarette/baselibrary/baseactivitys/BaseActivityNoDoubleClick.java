/**
 * Copyright 2016,Smart Haier.All rights reserved
 */
package com.haier.cellarette.baselibrary.baseactivitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.LayoutInflaterFactory;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.common.BaseViewHelper;
import com.haier.cellarette.baselibrary.smartbar.IBaseAction;
import com.haier.cellarette.baselibrary.statusbar.StatusBarUtilV7;
import com.wolearn.hooklistener.HookCore;
import com.wolearn.hooklistener.HookListenerContract;
import com.wolearn.hooklistener.ListenerManager;


public abstract class BaseActivityNoDoubleClick extends AppCompatActivity implements IBaseAction {

    public static final String REQUEST_CODE = "request_code";

    private long mCurrentMs = System.currentTimeMillis();
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        interceptCreateView();
//        hideBottomUIMenu();
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        BaseAppManager.getInstance().add(this);
//        AnnotateUtils.injectViews(this);
        handler = new Handler();
        setContentView(getLayoutId());
        setup(savedInstanceState);
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.color_568EC0));
        StatusBarUtilV7.immersive(this, ContextCompat.getColor(this, R.color.color_343533), 1.0f);// color_E5F7FF
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

        ListenerManager.Builer builer = new ListenerManager.Builer();
        builer.buildOnClickListener(new HookListenerContract.OnClickListener() {
            @Override
            public void doInListener(final View v) {
//                Toast.makeText(BaseActivity.this, "单击时我执行", Toast.LENGTH_SHORT).show();
                v.setEnabled(false);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        v.setEnabled(true);
                    }
                }, 500);
//                if (!NodouleClickUtils.isFastClick()) {
////                    // 进行点击事件后的逻辑操作
//                    v.setEnabled(false);
//                }
            }
        }).buildOnLongClickListener(new HookListenerContract.OnLongClickListener() {
            @Override
            public void doInListener(View v) {
//                Toast.makeText(BaseActivity.this, "长按时我执行", Toast.LENGTH_SHORT).show();
            }
        }).buildOnFocusChangeListener(new HookListenerContract.OnFocusChangeListener() {
            @Override
            public void doInListener(View v, boolean hasFocus) {
//                Toast.makeText(BaseActivity.this, "焦点变化时我执行", Toast.LENGTH_SHORT).show();
            }
        });
        HookCore.getInstance().startHook(this, ListenerManager.create(builer));
    }

    public abstract class OnMultiClickListener implements View.OnClickListener {
        // 两次点击按钮之间的点击间隔不能少于1000毫秒
        private final int MIN_CLICK_DELAY_TIME = 1000;
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
//        MobEvent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobEvent.onPause(this);
    }

    protected abstract int getLayoutId();

    protected void setup(@Nullable Bundle savedInstanceState) {

    }

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
    public void targetToIfLogin(final Class<? extends BaseActivityNoDoubleClick> activity) {
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
//        if (SlbLoginUtil2.get().login_activity_result(requestCode, resultCode, data)) {
////            if (LoginUtil.get().isUserLogin()) {
////                onUserLogined(LoginUtil.get().userId());
////            } else {
////                onUserLoginCanceled();
////            }
//            return;
//        }
        onActResult(requestCode, resultCode, data);
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
    }

    @Override
    protected void onDestroy() {
        BaseAppManager.getInstance().remove(this);
//        ShowLoadingUtil.onDestory();
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

        BaseAppManager.getInstance().clear();
        Intent intent = new Intent("hs.act.phone.index");
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

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    /*| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN*/;
            decorView.setSystemUiVisibility(uiOptions);
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
