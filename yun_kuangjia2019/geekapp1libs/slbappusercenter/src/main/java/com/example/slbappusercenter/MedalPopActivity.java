package com.example.slbappusercenter;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
//import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.biz3slbappcomm.bean.SMyMedalDetailBean;
import com.example.biz3slbappcomm.bean.SMyMedalDetailBean1;
import com.example.biz3slbappcomm.presenter.SMyMedalDetailPresenter;
import com.example.biz3slbappcomm.view.SMyMedalDetailView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.pop.share.PopForShareImg;
import com.example.slbappcomm.utils.animation.FrameAnimationDrawable;
import com.geek.libglide47.base.GlideImageView;
import com.haier.cellarette.baselibrary.assetsfitandroid.fileassets.GetAssetsFileMP3TXTJSONAPKUtil;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;
import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;

import java.io.IOException;

public class MedalPopActivity extends SlbBaseActivity implements SMyMedalDetailView, View.OnClickListener, MediaPlayer.OnCompletionListener {

    private ImageView lightIv;
    //    private ImageView wingIv;
    private ImageView medalIv;
    private GlideImageView centerIv;
    private TextView medalNameTv;
    private TextView detailTv;
    private TextView dateTv;
    private TextView btnTv;
    private LottieAnimationView animationView1;
    private LottieAnimationView wingLav;
    private SMyMedalDetailBean1 bean;
    private String beanId;
    private SMyMedalDetailPresenter sMyMedalDetailPresenter;
    private SMyMedalDetailBean1 sMyMedalDetailBean1;
    private View ll_animation_view1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_medal_pop;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        if (NavigationBarUtil.hasNavigationBar(this)) {
//            NavigationBarUtil.initActivity(getWindow().getDecorView());
            NavigationBarUtil.hideBottomUIMenu(this);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        bean = (SMyMedalDetailBean1) getIntent().getSerializableExtra(CommonUtils.TAG_MEDALBEAN1);
        beanId = getIntent().getStringExtra(CommonUtils.TAG_MEDALID1);
        initView();
        setListener();
        donetwork();
    }

    private void initView() {
//        animationView1 = findViewById(R.id.animation_view1);
//        wingIv = findViewById(R.id.wingIv);
        wingLav = findViewById(R.id.wingLav);
        medalIv = findViewById(R.id.medalIv);
        centerIv = findViewById(R.id.centerIv);
        medalNameTv = findViewById(R.id.medalNameTv);
        detailTv = findViewById(R.id.detailTv);
        dateTv = findViewById(R.id.dateTv);
        lightIv = findViewById(R.id.lightIv);
        btnTv = findViewById(R.id.btnTv);
        ll_animation_view1 = findViewById(R.id.ll_animation_view1);
    }

    private void setListener() {
//        ll_animation_view1.setOnClickListener(this);
        btnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beanId != null && sMyMedalDetailBean1 != null) {
                    if (TextUtils.equals("1", sMyMedalDetailBean1.getStatus())) {
                        // new
                        finish();
                        new PopForShareImg(BaseAppManager.getInstance().top(), beanId, null, null);
                        // old
//                        new PopForShareImg(BaseAppManager.getInstance().getAll().elementAt(BaseAppManager.getInstance().getAll().size() - 2), beanId, null, null);
//                        finish();
                    } else {
//                        HiosHelper.resolveAd(BooksListActivity.this, BooksListActivity.this, bean.getHios());
//                        onHomePressed();
                        int index_fragment = 0;
                        if (sMyMedalDetailBean1.getBtnStr().contains("绘本")) {
                            index_fragment = 0;
                        } else if (sMyMedalDetailBean1.getBtnStr().contains("听书")) {
                            index_fragment = 1;
                        }
//                        Intent intent = new Intent("hs.act.slbapp.index");
//                        intent.putExtra(CommonUtils.index_action, index_fragment);
//                        startActivity(intent);
                        try {
                            Class<? extends Activity> klass = (Class<? extends Activity>) Class.forName("com.example.slbappindex.IndexMainActivity");
                            ActivityUtils.finishToActivity(klass, false);
                            Intent msgIntent = new Intent();
                            msgIntent.setAction(CommonUtils.index_action);
                            msgIntent.putExtra(CommonUtils.index_action, index_fragment);
                            LocalBroadcastManagers.getInstance(getApplicationContext()).sendBroadcast(msgIntent);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                } else {
                    finish();
                }
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        Intent msgIntent = new Intent();
        msgIntent.putExtra("focusChange", 1);
        msgIntent.setAction(CommonUtils.LB_broadcastreceiver);
        LocalBroadcastManagers.getInstance(getApplicationContext()).sendBroadcast(msgIntent);
    }

    private FrameAnimationDrawable wingAnim;
    private FrameAnimationDrawable lightAnim;
    private Handler handler = new Handler();

    private void initData(SMyMedalDetailBean1 sMyMedalDetailBean1) {
        this.sMyMedalDetailBean1 = sMyMedalDetailBean1;
//        wingAnim = new FrameAnimationDrawable(true, R.drawable.ani_widget, wingIv);
        lightAnim = new FrameAnimationDrawable(R.drawable.ani_light, lightIv);
        if (sMyMedalDetailBean1 != null) {
            medalNameTv.setText(sMyMedalDetailBean1.getTitle());
            btnTv.setText(sMyMedalDetailBean1.getBtnStr());
            detailTv.setText(sMyMedalDetailBean1.getTips());
            dateTv.setText(sMyMedalDetailBean1.getGainTime());
            if ("0".equals(sMyMedalDetailBean1.getStatus())) {//未获得
                medalIv.setImageResource(R.drawable.medal2);
                wingLav.setVisibility(View.GONE);
                startTimer();
            } else {//已获得
                wingLav.setVisibility(View.VISIBLE);
                medalIv.setImageResource(R.drawable.medal1);
                wingLav.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        finish();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

            }
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.ic_def_loading)
                    .error(R.drawable.ic_def_loading)
                    .fallback(R.drawable.ic_def_loading); //url为空的时候,显示的图片;
            Glide.with(this).load(sMyMedalDetailBean1.getImgUrl())
                    .apply(options)
                    .into(centerIv);
            playMusic(BaseApp.get(), true, "android.resource://" + getPackageName() + "/" + R.raw.mymedal_qianzou);
        } else {
            Glide.with(this).load(R.drawable.ic_def_loading).into(centerIv);
            startTimer();
        }
    }

    public MediaPlayer mediaPlayer;

    public void playMusic(Context context, boolean assets_or_raw, String uri_or_assetsurl) {
        Log.e("playMusic:", "playMusic");
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.reset();
//                initMediaPlayer();
            }
            if (assets_or_raw) {
                if (uri_or_assetsurl.toLowerCase().contains("http")) {
                    // 播放网络benfen
                    mediaPlayer.setDataSource(uri_or_assetsurl);
                } else {
                    // 播放本地bufen
                    mediaPlayer.setDataSource(context, Uri.parse(uri_or_assetsurl));
                }
            } else {
                AssetManager assetManager = context.getAssets();
                AssetFileDescriptor fileDescriptor = assetManager.openFd(uri_or_assetsurl);
                mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());// 设置数据源
            }
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e("playMusic:error:", e.toString());
            e.printStackTrace();
        }

//        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.e("playMusic:", "onCompletion");
        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(getApplicationContext()).playMusic(this, true, sMyMedalDetailBean1.getAudio());
    }

    public void MusicDestory() {
        if (null != mediaPlayer) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void donetwork() {
        sMyMedalDetailPresenter = new SMyMedalDetailPresenter();
        sMyMedalDetailPresenter.onCreate(this);
        if (bean != null) {
            initData(bean);
        } else {
            sMyMedalDetailPresenter.getMyMedalDetailData(beanId);
        }
    }

    @Override
    protected void onDestroy() {
        if (lightAnim != null) {
            lightAnim.stopAnimation(true);
        }
        if (wingLav != null && wingLav.isAnimating()) {
            wingLav.cancelAnimation();
        }
        MusicDestory();
        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(getApplicationContext()).MusicDestory();
//        wingAnim.stopAnimation(true);
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        sMyMedalDetailPresenter.onDestory();
        // 循环播放
        Intent msgIntent = new Intent();
        msgIntent.setAction(MyMedalActivity.playmusic_action);
        msgIntent.putExtra(MyMedalActivity.playmusic_action, 0);// 0 循环 1 结束
        LocalBroadcastManagers.getInstance(getApplicationContext()).sendBroadcast(msgIntent);
        super.onDestroy();
    }

    @Override
    public void OnMyMedalDetailSuccess(SMyMedalDetailBean sMyMedalDetailBean) {
        initData(sMyMedalDetailBean.getDetails());
    }

    @Override
    public void OnMyMedalDetailNodata(String s) {
        initData(null);
    }

    @Override
    public void OnMyMedalDetailFail(String s) {
        initData(null);
    }

    private void startTimer() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 15000);
    }

    @Override
    public void onClick(View v) {

    }
}
