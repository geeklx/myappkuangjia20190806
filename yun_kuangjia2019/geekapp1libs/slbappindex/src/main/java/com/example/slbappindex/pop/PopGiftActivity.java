package com.example.slbappindex.pop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
//import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappindex.R;
import com.haier.cellarette.baselibrary.assetsfitandroid.fileassets.GetAssetsFileMP3TXTJSONAPKUtil;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;

public class PopGiftActivity extends SlbBaseActivity implements View.OnClickListener {

    private ImageView ivDel;
    private RelativeLayout llLottie1;
    private LottieAnimationView animationView1;
    private LottieAnimationView animationView2;
    private LinearLayout ll_animation_view1;
    private LinearLayout ll_animation_view2;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_popgift;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        ll_animation_view1.setVisibility(View.VISIBLE);
        ll_animation_view2.setVisibility(View.GONE);
        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(BaseApp.get()).playMusic(BaseApp.get(), true, "android.resource://" + getPackageName() + "/" + R.raw.gift);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_del) {
            finish();
        } else if (i == R.id.ll_animation_view1) {
            //
            ll_animation_view2.setVisibility(View.VISIBLE);
            ll_animation_view1.setVisibility(View.GONE);
            GetAssetsFileMP3TXTJSONAPKUtil.getInstance(BaseApp.get()).playMusic(BaseApp.get(), true, "android.resource://" + getPackageName() + "/" + R.raw.have_gift);
            Intent msgIntent = new Intent();
            msgIntent.setAction(CommonUtils.index_action);
            msgIntent.putExtra(CommonUtils.index_action, 2);
            LocalBroadcastManagers.getInstance(getApplicationContext()).sendBroadcast(msgIntent);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (PopGiftActivity.this.equals(BaseAppManager.getInstance().top())) {
                        finish();
                    }
                }
            }, 8000);
        } else if (i == R.id.ll_animation_view2) {
            //

        } else {

        }
    }

    private void onclick() {
        ivDel.setOnClickListener(this);
        ll_animation_view1.setOnClickListener(this);
        ll_animation_view2.setOnClickListener(this);
    }

    private void findview() {
        ivDel = findViewById(R.id.iv_del);
        llLottie1 = findViewById(R.id.ll_lottie1);
        animationView1 = findViewById(R.id.animation_view1);
        animationView2 = findViewById(R.id.animation_view2);
        ll_animation_view1 = findViewById(R.id.ll_animation_view1);
        ll_animation_view2 = findViewById(R.id.ll_animation_view2);

    }

    @Override
    protected void onDestroy() {
        animationView1.cancelAnimation();
        animationView2.cancelAnimation();
        super.onDestroy();

    }


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */


}
