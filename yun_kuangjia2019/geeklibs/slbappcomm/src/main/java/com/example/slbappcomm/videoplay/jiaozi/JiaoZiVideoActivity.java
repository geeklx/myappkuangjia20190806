package com.example.slbappcomm.videoplay.jiaozi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.Md5FileNameGenerator;
import com.example.slbappcomm.R;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.haier.cellarette.baselibrary.smartbar.SmartBar;
import com.haier.cellarette.baselibrary.smartbar.SmartBarInject;

import cn.jzvd.JZUserAction;
import cn.jzvd.JZUserActionStandard;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


/**
 * BJColor
 */

public class JiaoZiVideoActivity extends SlbBaseActivity {


    private SmartBarInject smartBarInject;
    private String videoUrl;
    private String goodsName;
    private JZVideoPlayerStandard jzvideo;

    @Override
    protected int getLayoutId() {
        return R.layout.empty_layout_jiaozi;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        smartBarInject = SmartBarInject.inject(this);
        smartBarInject.show(SmartBar.BACK);
        videoUrl = getIntent().getStringExtra("videourl");
        goodsName = getIntent().getStringExtra("goodsname");
        jzvideo = findViewById(R.id.jzvideo);
        jzvideo.fullscreenButton.setVisibility(View.GONE);
//        MsgViewManager.setFloatViewGone();
//        饺子
        JZVideoPlayer.setJzUserAction(new MyUserActionStandard());
        JZVideoPlayerStandard.setJZBack(new JZVideoPlayerStandard.JZBack() {
            @Override
            public void setBack(View view) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JZVideoPlayer.backPress();
                        videoBackInitialization();
                    }
                });
            }
        });
        openAlwaysLight();
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        openVideo();
//        openVideo2();
    }


    private void openVideo() {
        jzvideo.setUp(getProxy(this).getProxyUrl(videoUrl)
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, goodsName);
        jzvideo.startVideo();
    }

    /**
     * 视频缓存部分代码
     */
    private HttpProxyCacheServer proxy;

    public HttpProxyCacheServer getProxy(Context context) {
        return proxy == null ? (proxy = newProxy()) : proxy;
    }

    private HttpProxyCacheServer newProxy() {
/*
        this.diskUsage = new TotalSizeLruDiskUsage(DEFAULT_MAX_SIZE);
        this.fileNameGenerator = new Md5FileNameGenerator();
*/
        return new HttpProxyCacheServer.Builder(this)
                .cacheDirectory(VideoUtils.getVideoCacheDir(this))
                .maxCacheSize(2048 * 1024 * 1024L)//2GB
                .fileNameGenerator(new Md5FileNameGenerator())
                .build();
    }

    /**
     * 开启屏幕常亮
     */
    PowerManager pm;// 屏幕开关
    PowerManager.WakeLock mWakeLock;

    @SuppressLint("InvalidWakeLockTag")
    public void openAlwaysLight() {
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        if (mWakeLock == null) {
            mWakeLock = pm.newWakeLock((PowerManager.FULL_WAKE_LOCK | PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "JiaoziVideoActivity");
        }
        if (!mWakeLock.isHeld()) {
            mWakeLock.acquire();
            Log.i("JiaoziVideoActivity", "Wakelock aquired!!");
        }
    }

    /**
     * 关闭屏幕常亮
     */
    public void closeAlwaysLight() {
        if (mWakeLock != null && mWakeLock.isHeld()) {
            mWakeLock.release();
        }
    }

//    private void openVideo2() {
////            //饺子
//        JZVideoPlayerStandard.startFullscreen(JiaoZiVideoActivity.this, JZVideoPlayerStandard.class,
//                MyApplication.getProxy(this).getProxyUrl(videoUrl), goodsName);
//    }

    class MyUserActionStandard implements JZUserActionStandard {

        @Override
        public void onEvent(int type, String url, int screen, Object... objects) {
            switch (type) {
                case JZUserAction.ON_CLICK_START_ICON:
                    Log.i("USER_EVENT", "ON_CLICK_START_ICON" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
//                    MsgViewManager.setFloatViewGone();
                    break;
                case JZUserAction.ON_CLICK_START_ERROR:
                    Log.i("USER_EVENT", "ON_CLICK_START_ERROR" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_CLICK_START_AUTO_COMPLETE:
                    Log.i("USER_EVENT", "ON_CLICK_START_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_CLICK_PAUSE:
                    Log.i("USER_EVENT", "ON_CLICK_PAUSE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_CLICK_RESUME:
                    Log.i("USER_EVENT", "ON_CLICK_RESUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_SEEK_POSITION:
                    Log.i("USER_EVENT", "ON_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_AUTO_COMPLETE:
                    //TODO JZVideoPlayer  700行可控制退出
                    videoBackInitialization();
                    Log.i("USER_EVENT", "ON_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_ENTER_FULLSCREEN:
                    Log.i("USER_EVENT", "ON_ENTER_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_QUIT_FULLSCREEN:
                    Log.i("USER_EVENT", "ON_QUIT_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_ENTER_TINYSCREEN:
                    Log.i("USER_EVENT", "ON_ENTER_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_QUIT_TINYSCREEN:
                    Log.i("USER_EVENT", "ON_QUIT_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_TOUCH_SCREEN_SEEK_VOLUME:
                    Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_VOLUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserAction.ON_TOUCH_SCREEN_SEEK_POSITION:
                    Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserActionStandard.ON_CLICK_START_THUMB:
                    Log.i("USER_EVENT", "ON_CLICK_START_THUMB" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JZUserActionStandard.ON_CLICK_BLANK:
                    Log.i("USER_EVENT", "ON_CLICK_BLANK" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                default:
                    Log.i("USER_EVENT", "unknow");
                    break;
            }
        }

    }


    @Override
    public void onBackPressed() {
        videoBackInitialization();
        finish();
    }

    public void videoBackInitialization() {
        closeAlwaysLight();
        JZVideoPlayer.releaseAllVideos();
//        ShowModule.showMsgViewManager(JiaoZiVideoActivity.this);
//        if (ConstantUtil.VERSION_INFO == VersionInfo.RESTAURANT) {
//            MsgViewManager.getLayout();
//        } else if (ConstantUtil.VERSION_INFO == VersionInfo.NATION) {
//            MsgViewManager.destory();
//        }

        super.onBackPressed();
    }

    //    饺子
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
        closeAlwaysLight();
        //Change these two variables back
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    }

}
