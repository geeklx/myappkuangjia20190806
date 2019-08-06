package com.example.slbappcomm.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.IdRes;
//import android.support.annotation.Nullable;
import androidx.annotation.IdRes;
//import androidx.core.app.Fragment;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slbappcomm.R;
import com.haier.cellarette.baselibrary.common.BaseViewHelper;
import com.haier.cellarette.baselibrary.networkview.NetState;
import com.haier.cellarette.baselibrary.networkview.NetconListener2;
import com.haier.cellarette.baselibrary.statusbar.StatusBarUtilV7;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;


public abstract class SlbBaseFragment extends Fragment implements NetconListener2 {

    private long mCurrentMs = System.currentTimeMillis();
    protected NetState netState;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        setup(rootView, savedInstanceState);
//        StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.color_568EC0));

        netState = new NetState();
        netState.setNetStateListener(this, getActivity());
        return rootView;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        HookUtil.hookClick(this);

//        AnnotateUtils.injectViews(getActivity());
//        StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(BaseApp.get(), R.color.color_BEDFFF));
        StatusBarUtilV7.immersive(getActivity(), ContextCompat.getColor(getActivity(), R.color.color_4DA3FE), 1.0f);// color_E5F7FF
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);

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
    public void onResume() {
        super.onResume();
//        MobEvent.onEventStart(getActivity(), EventIdConst.PAGE_LOAD_DURATION, TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobEvent.onEventEnd(getActivity(), EventIdConst.PAGE_LOAD_DURATION, TAG);
    }


    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {

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

    protected <T extends View> T f(View rootView, @IdRes int resId) {
        return BaseViewHelper.f(rootView, resId);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    public void startActivity(Class<? extends Activity> activity) {
        startActivity(new Intent(getActivity(), activity));
    }

    public void startActivity(String action) {
        Intent intent = new Intent(action);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void startActivityForResult(Class<? extends Activity> activity, int requestCode) {
        startActivityForResult(new Intent(getActivity(), activity), requestCode);
    }

    public void startActivityForResult(String action, int requestCode) {
        Intent intent = new Intent(action);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 跳转到指定activity，如果未登录，则弹出登录窗口
     *
     * @param activity
     */
    public void targetToIfLogin(final Class<? extends SlbBaseActivity> activity) {
//        UserUtils.get().loginToDo(getActivity(), new Runnable() {
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
//        UserUtils.get().loginToDo(getActivity(), new Runnable() {
//            @Override
//            public void run() {
//                startActivity(intent);
//            }
//        });
    }

    public void targetToIfLogin(final String action) {
//        UserUtils.get().loginToDo(getActivity(), new Runnable() {
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
//        UserUtils.get().loginToDo(getActivity(), new Runnable() {
//            @Override
//            public void run() {
//                startActivityForResult(intent, requestCode);
//            }
//        });
    }

    public void targetToForResultIfLogin(final String action, final int requestCode) {
//        UserUtils.get().loginToDo(getActivity(), new Runnable() {
//            @Override
//            public void run() {
//                startActivityForResult(action, requestCode);
//            }
//        });
    }

    @Override
    public final void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (UserUtils.get().onActivityResult(requestCode, resultCode, data)) {
//            onUserLogined(UserUtils.get().userId());
//            return;
//        }
        //登录和未登录成功状态
        if (SlbLoginUtil2.get().login_activity_result(requestCode, resultCode, data)) {
//            if (LoginUtil.get().isUserLogin()) {
//                onUserLogined(LoginUtil.get().userId());
//            } else {
//                onUserLoginCanceled();
//            }
            return;
        }
        onActResult(requestCode, resultCode, data);
    }

    public void onUserLogined(String userId) {
    }

    public void onActResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void onDestroy() {
//        ShowLoadingUtil.onDestory();
//        Objects.requireNonNull(getActivity()).unregisterReceiver(networkChangeListener);
        super.onDestroy();
        if (netState != null) {
            netState.unregisterReceiver();
        }
    }

    public String getIdentifier() {
        return getClass().getName() + mCurrentMs;
    }

}
