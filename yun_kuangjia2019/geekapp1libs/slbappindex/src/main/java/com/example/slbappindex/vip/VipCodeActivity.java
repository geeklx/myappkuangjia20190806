package com.example.slbappindex.vip;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.biz3slbappusercenter.bean.SJihuomaBean;
import com.example.biz3slbappusercenter.presenter.SJihuomaPresenter;
import com.example.biz3slbappusercenter.view.SJihuomaView;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappindex.R;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;
import com.umeng.analytics.MobclickAgent;

//import android.support.annotation.Nullable;

public class VipCodeActivity extends SlbBaseActivity implements View.OnClickListener, SJihuomaView {

    private TextView tv1;
    private LinearLayout rl1;
    private LinearLayout ll1;
    private EditText edt1;
    private TextView tv2;
    //
    private SJihuomaPresenter presenter;
    private PopVipCodePay popVipCodePay;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_vipcode;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        //虚拟键
        if (NavigationBarUtil.hasNavigationBar(this)) {
//            NavigationBarUtil.initActivity(getWindow().getDecorView());
            NavigationBarUtil.hideBottomUIMenu(this);
        }
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        findview();
        onclick();
        donetwork();
        //
        MobclickAgent.onEvent(this, "VipCodeActivity");
    }

    private void donetwork() {
        presenter = new SJihuomaPresenter();
        presenter.onCreate(this);

    }

    private void onclick() {
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv1) {
            onBackPressed();
        } else if (i == R.id.tv2) {
            //
//            if (!NetworkUtils.isConnected()) {
//                Toasty.normal(getApplicationContext(), "网络异常，请检查网络连接！").show();
//                return;
//            }
            presenter.getJihuomaData(edt1.getText().toString().trim());
            edt1.getText().clear();
            hideSoftKeyboard();
        } else {
        }
    }

    private void findview() {
        tv1 = findViewById(R.id.tv1);
        rl1 = findViewById(R.id.rl1);
        ll1 = findViewById(R.id.ll1);
        edt1 = findViewById(R.id.edt1);
        tv2 = findViewById(R.id.tv2);
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("VipCodeActivity");
        super.onPause();
    }

    @Override
    protected void onResume() {
        MobclickAgent.onPageStart("VipCodeActivity");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        super.onDestroy();
    }

    @Override
    public void OnJihuomaSuccess(SJihuomaBean sJihuomaBean) {
        if (sJihuomaBean.getStatus().equals("0")) {
            Toasty.normal(VipCodeActivity.this, sJihuomaBean.getMsg()).show();
        } else if (sJihuomaBean.getStatus().equals("1")) {
            popVipCodePay = new PopVipCodePay(VipCodeActivity.this, sJihuomaBean.getMsg(), new PopVipCodePay.OnFinishResultClickListener() {
                @Override
                public void onResult() {
                    popVipCodePay.dismiss();
                    startActivity(new Intent("hs.act.slbapp.VipActivity"));
                    finish();
                }
            });
            popVipCodePay.showAtLocation(getWindow().getDecorView(),
                    Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
                    , 0, 0); // 设置layout在PopupWindow中显示的位置
        }
    }

    @Override
    public void OnJihuomaNodata(String s) {
        Toasty.normal(VipCodeActivity.this, s).show();
    }

    @Override
    public void OnJihuomaFail(String s) {
        Toasty.normal(VipCodeActivity.this, s).show();
    }
}
