package com.example.demolibs1;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.slbappcomm.utils.BanbenCommonUtils;

import me.jessyan.autosize.AutoSizeCompat;

public class Act extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;

    @Override
    public Resources getResources() {
        //需要升级到 v1.1.2 及以上版本才能使用 AutoSizeCompat
        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()));//如果没有自定义需求用这个方法
        AutoSizeCompat.autoConvertDensity((super.getResources()), 667, false);//如果有自定义需求就用这个方法
        return super.getResources();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actmain);
        tv1 = findViewById(R.id.tv1111);
        tv2 = findViewById(R.id.tv2222);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                tv2.setText("我是BUG");
                tv2.setText(BanbenCommonUtils.banben_comm + "我是修改过的BUG 云");


            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("hs.act.slbapp.Act2"));
            }
        });
    }
}
