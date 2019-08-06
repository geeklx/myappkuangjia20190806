package com.example.slbappcomm.videoplay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.example.slbappcomm.R;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.videoplay.gsy.JumpVideoPlayUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

//import android.support.annotation.Nullable;

public class VideoPlayActivity extends SlbBaseActivity implements View.OnClickListener {

    StandardGSYVideoPlayer videoPlayer;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_videoplay1;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
//        setxpop();
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv1.setTypeface(tfLight);
        tv2.setTypeface(tfLight);
        tv3.setTypeface(tfLight);
        tv4.setTypeface(tfLight);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setvideoplay1();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setvideoplay2();
            }

        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("hs.act.slbapp.HrMemberActivity"));
            }

        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("hs.act.slbapp.QuxiantuActivity"));
            }

        });
    }

    private void setvideoplay2() {
        Intent intent = new Intent("hs.act.slbapp.JiaoZiVideoActivity");
        intent.putExtra("videourl", "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4");
        intent.putExtra("goodsname", "测试");
        startActivity(intent);
    }

    private void setvideoplay1() {
        //简单的播放
        JumpVideoPlayUtils.goToPlayEmptyControlActivity(this, tv1);
//        startActivity(new Intent(this, SimplePlayer.class));
    }

    private void setxpop() {
        new XPopup.Builder(this).asConfirm("我是标题", "我是内容",
                new OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        ToastUtils.showLong("click confirm");
                    }
                })
                .show();
    }

    @Override
    public void onClick(View view) {

    }

}
