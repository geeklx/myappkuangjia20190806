package com.example.slbapplogin;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.slbappcomm.base.SlbBaseActivity;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;


public class LoginMainActivity extends SlbBaseActivity {

    private TextView tv1;


    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlbLoginUtil2.get().loginTowhere(LoginMainActivity.this,  new Runnable() {
                    @Override
                    public void run() {
                        //登录to
//                        ToastUtil.showToastCenter("可以跳转了~");
                        tv1.setText("更新UI~");
//                        startActivity(new Intent(LoginMainActivity.this, MainActivity2.class));
                    }
                });
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlbLoginUtil2.get().loginOutTowhere(LoginMainActivity.this,  new Runnable() {
                    @Override
                    public void run() {
                        //退出登录to
                        //登录to
//                        ToastUtil.showToastCenter("可以跳转了~");
                        tv1.setText("login");
//                        startActivity(new Intent(LoginMainActivity.this, MainActivity3.class));
                    }
                });
            }
        });
    }

    @Override
    protected void onActResult(int requestCode, int resultCode, Intent data) {
        super.onActResult(requestCode, resultCode, data);
        if (SlbLoginUtil2.get().login_activity_result(requestCode, resultCode, data)) {
////            if (LoginUtil.get().isUserLogin()) {
////                onUserLogined(LoginUtil.get().userId());
////            } else {
////                onUserLoginCanceled();
////            }
//            return;
        }
    }
}
