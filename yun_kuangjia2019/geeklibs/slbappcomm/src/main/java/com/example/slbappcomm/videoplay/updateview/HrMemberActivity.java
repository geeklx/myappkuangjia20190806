package com.example.slbappcomm.videoplay.updateview;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.slbappcomm.R;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.videoplay.bt.BleService;
import com.example.slbappcomm.videoplay.gsy.EmptyControlVideo;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.greendao.demogreendao.bean.Member;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class HrMemberActivity extends SlbBaseActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {

    private HrCardView hrCardView;
    private EmptyControlVideo videoPlayer;
    private OrientationUtils orientationUtils;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.release();
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
        EventBus.getDefault().unregister(this);
        stopService(new Intent(this, BleService.class));

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onBackPressed() {
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        GSYVideoManager.releaseAllVideos();
        super.onBackPressed();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hrmember;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        hrCardView = findViewById(R.id.tv1);
        videoPlayer = findViewById(R.id.video_player);
        //
        String url = "rtmp://58.200.131.2:1935/livetv/hunantv";
        videoPlayer.setUp(url, true, "");
        videoPlayer.startPlayLogic();
        //
        EventBus.getDefault().register(this);
        startbt();
        // ceshi
        final List<Member> inClassMemberList = new ArrayList<>();
        inClassMemberList.add(new Member("1", "yun1",
                "https://s2.51cto.com/wyfs02/M01/89/BA/wKioL1ga-u7QnnVnAAAfrCiGnBQ946_middle.jpg",
                "m", 78.10, "1.78", 32, "1", "1",
                "1", "1", 1, 21.12, 18.26, 1.56, 1000,
                1000, 1000, 1, 1, 1));
        BleService.setInClassMemberList(inClassMemberList);
        hrCardView.setMember(null);
        hrCardView.setMember(inClassMemberList.get(0));

    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void updateUI(final MemberHrView hrView) {
        if (hrView != null && hrView.getHrCardView() != null && hrView.getMember() != null) {
            hrView.getHrCardView().setHr(hrView.getMember());
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBleDeviceMemberMatched(Member member) {
        if (member != null) {
//            for (HrCardView hrCardView : allHrCardViewList) {
//                if (hrCardView.getMember() != null && hrCardView.getMember().equals(member)) {
//                    EventBus.getDefault().post(new MemberHrView(hrCardView, member));
//                }
//            }
            EventBus.getDefault().post(new MemberHrView(hrCardView, member));

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private static final int RC_LOCATION = 122;

    @AfterPermissionGranted(RC_LOCATION)
    private void startbt() {
        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(HrMemberActivity.this, perms)) {
            startService(new Intent(this, BleService.class));
        } else {
            EasyPermissions.requestPermissions(this, "android6.0以上版本使用蓝牙需要同意授予定位权限",
                    RC_LOCATION, perms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        MyLogUtil.d("SMS1", "onPermissionsGranted:" + requestCode + ":" + perms.size());
        startService(new Intent(this, BleService.class));
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        MyLogUtil.d("SMS1", "onPermissionsDenied:" + requestCode + ":" + perms.size());
//        Toasty.normal(SlbLoginActivity.this, "您阻止了app读取您的短信，您可以自己手动输入验证码").show();
//        edt2.requestFocus();
    }

    @Override
    public void onClick(View view) {

    }

}
