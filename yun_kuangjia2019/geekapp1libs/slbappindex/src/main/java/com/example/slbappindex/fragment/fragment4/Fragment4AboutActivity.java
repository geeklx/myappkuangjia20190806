package com.example.slbappindex.fragment.fragment4;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappindex.R;
import com.haier.cellarette.baselibrary.qcode.ExpandViewRect;
import com.haier.cellarette.libwebview.hois2.HiosHelper;

public class Fragment4AboutActivity extends SlbBaseActivity implements View.OnClickListener {

    private TextView tvRight;
    private TextView tvCenter;
    private ImageView iv_zhaoliying;
    private TextView tv1;
    private TextView tv11;
    private TextView tv_tips10;
//    private TextView img_url;
//    private TextView video_url;
//    private RelativeLayout rl1;
//    private LinearLayout ll1;
//    private LinearLayout ll2;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment4_about;
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
        tvCenter.setText("关于我们");
        tv1.setText("版本:V1.4.2");
        tv1.setVisibility(View.GONE);
//        tv_tips10.setText(Html.fromHtml(getResources().getString(R.string.slb_my_tip10) + "\n\n" +
//                "<font color=\"#979797\">" + getResources().getString(R.string.slb_my_tip11) + "</font>"));
//        img_url.setText(ConstantsUtils.file_url_img);
//        video_url.setText(ConstantsUtils.file_url_mp4);
        ExpandViewRect.expandViewTouchDelegate(tvRight, 20, 20, 20, 20);
//        GlideOptions glideOptions = new GlideOptions(R.drawable.slb_icon, R.drawable.slb_icon, 20);
//        GlideUtil.display(this, iv1, "https://s2.51cto.com//wyfs02/M01/89/BA/wKioL1ga-u7QnnVnAAAfrCiGnBQ946_middle.jpg", glideOptions);

    }

    private void onclick() {
        tvRight.setOnClickListener(this);
        tv11.setOnClickListener(this);
        iv_zhaoliying.setOnClickListener(this);
        tvCenter.setOnClickListener(this);
//        ll1.setOnClickListener(this);
//        ll2.setOnClickListener(this);
    }

    private void findview() {
        tvRight = findViewById(R.id.tv_right);
        tvCenter = findViewById(R.id.tv_center);
        iv_zhaoliying = findViewById(R.id.iv_zhaoliying);
        tv1 = findViewById(R.id.tv1);
        tv11 = findViewById(R.id.tv11);
        tv_tips10 = findViewById(R.id.tv_tips10);
//        img_url = findViewById(R.id.img_url);
//        video_url = findViewById(R.id.video_url);
//        rl1 = findViewById(R.id.rl1);
//        ll1 = findViewById(R.id.ll1);
//        ll2 = findViewById(R.id.ll2);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_right) {
            onBackPressed();
        } else if (i == R.id.iv_zhaoliying) {

        } else if (i == R.id.tv11) {
            HiosHelper.resolveAd(this, this, "http://hexiang-app.sairobo.cn/user-notice/about-us.html");

        } else if (i == R.id.ll1) {
            // 临时跳转到GLide47bufen
            startActivity(new Intent("hs.act.GlideMainActivity"));
        } else if (i == R.id.tv_center) {
            //推送入口bufen
//            Intent intent = new Intent("hs.act.slbapp.JpushActivity");
////        intent.putExtra("type", Platform.ACTION_SHARE);//授权
//            startActivity(intent);
        } else {
        }
    }

}
