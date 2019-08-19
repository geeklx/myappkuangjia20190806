package com.example.slbapplogin.winter;

import android.content.Context;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.example.biz3slbappusercenter.presenter.SLoginoutPresenter;
import com.example.biz3slbappusercenter.view.SLoginoutView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbapplogin.R;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;

public class SlbLoginOutActivity extends SlbBaseActivity implements SLoginoutView {

    private LinearLayout ll_cancel;
    private LinearLayout ll_ok;
    private SLoginoutPresenter presenter_loginout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_slbloginout;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findview();
        onclick();
        donetwork();
    }


    private void donetwork() {
        presenter_loginout = new SLoginoutPresenter();
        presenter_loginout.onCreate(this);

    }

    // 退出登录bufen
    @Override
    public void OnLoginoutSuccess(String s) {
        Toasty.normal(BaseApp.get(), s).show();
        donetloginout();
    }

    @Override
    public void OnLoginoutNodata(String s) {
        Toasty.normal(this, "请先登录").show();
//        SlbLoginUtil2.get().loginTowhere(this, new Runnable() {
//            @Override
//            public void run() {
////                Toasty.normal(SlbLoginOutActivity.this, "登录成功").show();
//
//            }
//        });
    }

    @Override
    public void OnLoginoutFail(String s) {
        Toasty.normal(this, s).show();
    }

    private void onclick() {
        ll_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginCanceled();
            }
        });
        ll_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter_loginout.getLoginoutData(SPUtils.getInstance().getString(CommonUtils.USER_TEL));

            }
        });
    }

    private void onLoginSuccess() {
        setResult(SlbLoginUtil2.LOGINOUT_RESULT_OK);
        finish();
    }

    private void onLoginCanceled() {
        setResult(SlbLoginUtil2.LOGINOUT_RESULT_CANCELED);
        finish();
    }

    /**
     * 登出操作
     */
    private void donetloginout() {
        //step 请求服务器成功后清除sp中的数据
        SPUtils.getInstance().put(CommonUtils.USER_TEL, "");
        SPUtils.getInstance().put(CommonUtils.USER_TOKEN, "");
        SPUtils.getInstance().put(CommonUtils.USER_NAME, "");
        SPUtils.getInstance().put(CommonUtils.USER_IMG, "");
        SPUtils.getInstance().put(CommonUtils.READBOOK_AUTOBUTTON, 1);// 设置绘本连播开启
        onLoginSuccess();
    }

    private void findview() {
        ll_ok = findViewById(R.id.ll_ok);
        ll_cancel = findViewById(R.id.ll_cancel);
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
    }

    @Override
    protected void onDestroy() {
        hideSoftKeyboard();
        presenter_loginout.onDestory();
        super.onDestroy();
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
}
