package com.example.slbappreadbook.utils;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings;
import android.widget.TextView;

import com.example.slbappreadbook.R;
import com.haier.cellarette.baselibrary.ringutil.RingUtil;

public class RingReadActControl {

    private SettingsContentObserver settingsContentObserver;//监听设置变化
    private AudioManager audioManager;
    private Context context;
    private TextView tv_volume;

    public RingReadActControl(Context context, TextView tv_volume, Handler handler) {
        this.context = context;
        this.tv_volume = tv_volume;
        settingsContentObserver = new SettingsContentObserver(handler);
//        audioManager = (AudioManager) BaseApp.get().getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * 当前Activity处于Running状态时，获取音量状态变化
     */
    private class SettingsContentObserver extends ContentObserver {
        public SettingsContentObserver(Handler handler) {
            super(handler);
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return false;
        }

        @Override
        public void onChange(boolean selfChange) {
            setViewOnClick2();
        }
    }

    /**
     * 开始监听系统音量，进行相应的变化
     */
    public void ringStartListenSystemVol() {
        context.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, settingsContentObserver);
    }

    /**
     * 结束对系统音量的监听
     */
    public void ringFinishListenSystemVol() {
        context.getContentResolver().unregisterContentObserver(settingsContentObserver);
    }

    public void setViewOnClick() {
        if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) > 0) {
            // 静音
            jingyin_open();
        } else {
            // 非
            jingyin_close();
        }
    }
    public void setViewOnClick2() {
        if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) > 0) {
            // 静音
            tv_volume.setBackgroundResource(R.drawable.slb_jyen);
        } else {
            // 非
            tv_volume.setBackgroundResource(R.drawable.slb_jy);
        }
    }

    // 音量bufen
    //静音
    public void jingyin_open() {
        tv_volume.setBackgroundResource(R.drawable.slb_jy);
        RingUtil.silentSwitchOn(context);
    }

    // 非
    public void jingyin_close() {
        tv_volume.setBackgroundResource(R.drawable.slb_jyen);
        RingUtil.silentSwitchOff(context);
    }


}
