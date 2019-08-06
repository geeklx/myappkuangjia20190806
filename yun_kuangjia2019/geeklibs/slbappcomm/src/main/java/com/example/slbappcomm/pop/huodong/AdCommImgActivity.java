package com.example.slbappcomm.pop.huodong;

import android.content.res.Resources;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.R;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;

import me.jessyan.autosize.AutoSizeCompat;

public class AdCommImgActivity extends SlbBaseActivity {

    private String id1;
    private String url1;
    private String img1;
    private TextView tv_adJumps;
    private ImageView iv1;

    @Override
    public Resources getResources() {
        //需要升级到 v1.1.2 及以上版本才能使用 AutoSizeCompat
        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()));//如果没有自定义需求用这个方法
        AutoSizeCompat.autoConvertDensity((super.getResources()), 667, false);//如果有自定义需求就用这个方法
        return super.getResources();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hios_adcomm_img_activity;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        //虚拟键
        if (NavigationBarUtil.hasNavigationBar(this)) {
//            NavigationBarUtil.initActivity(getWindow().getDecorView());
            NavigationBarUtil.hideBottomUIMenu(this);
        }
        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);// topbar
        id1 = getIntent().getExtras().getString("id1");
        url1 = getIntent().getExtras().getString("url1");
        img1 = getIntent().getExtras().getString("img1");
        //
        tv_adJumps = findViewById(R.id.tv_adJumps);
        iv1 = findViewById(R.id.iv1);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_def_loading)
                .error(R.drawable.ic_def_loading)
                .fallback(R.drawable.ic_def_loading); //url为空的时候,显示的图片;
        Glide.with(this).load(img1).apply(options).into(iv1);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtils.getInstance().put(CommonUtils.TAG_ADCOMMON_ID, id1);
                if (url1.contains("http")) {
                    // 网络请求bufen
                    if (url1.contains("condition=login")) {
//                        HiosHelper.resolveAd(AdCommImgActivity.this, AdCommImgActivity.this, url1);
                        SlbLoginUtil2.get().loginTowhere(AdCommImgActivity.this, new Runnable() {
                            @Override
                            public void run() {
                                HiosHelper.resolveAd(AdCommImgActivity.this, AdCommImgActivity.this, url1);
                            }
                        });
                    } else {
                        HiosHelper.resolveAd(AdCommImgActivity.this, AdCommImgActivity.this, url1);
                    }
                } else {
                    // hiosbufen
                    HiosHelper.resolveAd(AdCommImgActivity.this, AdCommImgActivity.this, url1);
                }
                if (url1.contains("condition=login")) {
                    if (SlbLoginUtil2.get().isUserLogin()) {
                        finish();
                    } else {
                        // 防止des之前就finish没效果
                        setIs_finish_login(true);
                    }
                } else {
                    finish();
                }
            }
        });
        tv_adJumps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtils.getInstance().put(CommonUtils.TAG_ADCOMMON_ID, id1);
                finish();
            }
        });
//        Log.e("gongshi", (Math.sqrt(Math.pow(812, 2) + (Math.pow(375, 2))) / 25.4 + ""));
//        Log.e("gongshi", (Math.sqrt(Math.pow(640, 2) + (Math.pow(360, 2))) / 25.4 + ""));
        Log.e("gongshi", (Math.sqrt(Math.pow(667, 2) + (Math.pow(375, 2))) / 25.4 + ""));

    }

}
