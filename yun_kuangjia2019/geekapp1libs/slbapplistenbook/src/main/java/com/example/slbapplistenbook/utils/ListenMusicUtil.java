package com.example.slbapplistenbook.utils;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class ListenMusicUtil {

    private static volatile ListenMusicUtil instance;
    private ObjectAnimator objectAnimator;

    public int STATE_PLAYING = 1;//正在播放
    public int STATE_PAUSE = 2;//暂停
    public int STATE_STOP = 3;//停止
    public int state_ani;

    public int getState_ani() {
        return state_ani;
    }

    public void setState_ani(int state_ani) {
        this.state_ani = state_ani;
    }

    public static ListenMusicUtil getInstance() {
        if (instance == null) {
            synchronized (ListenMusicUtil.class) {
                if (instance == null) {
                    instance = new ListenMusicUtil();
                }
            }
        }
        return instance;
    }

    public ObjectAnimator initMusic(View view) {
        state_ani = STATE_STOP;
        objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);//添加旋转动画，旋转中心默认为控件中点
        objectAnimator.setDuration(8000);//设置动画时间
        objectAnimator.setInterpolator(new LinearInterpolator());//动画时间线性渐变
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);
        return objectAnimator;
    }

    public void playMusic(View view) {
        if (objectAnimator == null) {
            return;
        }
        if (state_ani == STATE_STOP) {
            objectAnimator.start();//动画开始
            state_ani = STATE_PLAYING;
        } else if (state_ani == STATE_PAUSE) {
            objectAnimator.resume();//动画重新开始
            state_ani = STATE_PLAYING;
        } else if (state_ani == STATE_PLAYING) {
            objectAnimator.pause();//动画暂停
            state_ani = STATE_PAUSE;
        }
    }

    public void playMusic2(View view) {
        objectAnimator.start();//动画开始
    }

    public void resumeMusic2(View view) {
        objectAnimator.resume();//动画重新开始
    }

    public void pauseMusic2(View view) {
        objectAnimator.pause();//动画暂停
    }

    public void stopMusic(View view) {
//        if (view != null) {
//            view.clearAnimation();
//        }
        if (objectAnimator != null) {
            objectAnimator.end();//动画结束
        }
        state_ani = STATE_STOP;
    }

}

