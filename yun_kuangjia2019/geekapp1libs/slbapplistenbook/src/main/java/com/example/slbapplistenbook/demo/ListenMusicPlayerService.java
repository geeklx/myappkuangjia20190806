//package com.example.slbapplistenbook.service;
//
//import android.app.Service;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.content.res.AssetFileDescriptor;
//import android.content.res.AssetManager;
//import android.media.MediaPlayer;
//import android.os.Binder;
//import android.os.Build;
//import android.os.Environment;
//import android.os.IBinder;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.haier.cellarette.baselibrary.common.BaseApp;
//import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//
//public class ListenMusicPlayerService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
//    private String TAG = getClass().getSimpleName();
//    private MediaPlayer mPlayer = new MediaPlayer();
//    private String mPath;
//    private ArrayList<String> musicList;
//    private int current;
//    private int play_mode;// 0 列表播放 1 单曲循环
//    private boolean flag;
//    private boolean isPrepared;
//    private DemoPreparedCallBack mCallBack;
//    private boolean isError;
//    private int errorTimes;
//    private MyBinder myBinder = new MyBinder();
//    private boolean is_houtai;// false 正常 true 后台
//
//    public MediaPlayer getmPlayer() {
//        return mPlayer;
//    }
//
//    // 服务的生命周期中  创建的方法
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mPlayer.setOnPreparedListener(this);
//        mPlayer.setOnCompletionListener(this);
//        mPlayer.setOnErrorListener(this);
//        current = 0;
//        play_mode = 0;
//        flag = true;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (intent != null && TextUtils.isEmpty(intent.getAction())) {
//            String action = intent.getAction();
//            MyLogUtil.d(TAG, "alarm set repeat " + Calendar.getInstance().getTime().toString() + "     " + action);
//            if ("com.action".equals(action)) {
//                //业务逻辑bufen
//
//            }
//        }
//        return START_STICKY;
//    }
//
//    //这个方法主要是在用bind的方式开启服务的时候调用
//    @Override
//    public IBinder onBind(Intent intent) {
//        Log.e(TAG, "onBind: 音乐服务绑定成功");
//        return myBinder;
//    }
//
//    public class MyBinder extends Binder implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
//
//        public ListenMusicPlayerService getService() {
//            return ListenMusicPlayerService.this;
//        }
//
//        public void musicSeekTo(int progress) {
//            if (mPlayer != null) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    mPlayer.seekTo(Long.valueOf(progress + ""), MediaPlayer.SEEK_CLOSEST);
//                } else {
//                    mPlayer.seekTo(progress);
//                }
//                mPlayer.start();
//            }
//        }
//
//        public void musicContinue() {
//            if (mPlayer != null) {
//                mPlayer.start();
//            }
//        }
//
//        public void musicStart(String url) {
//            if (mPlayer != null) {
//                Log.e(TAG, "musicStart: 开始播放");
//                set_path(url);
//            } else {
//                Log.e(TAG, "musicStart: 创建新的MediaPlayer ---> 开始播放");
//                mPlayer = new MediaPlayer();
//                set_path(url);
//            }
//        }
//
//        public void musicPause() {
//            if (mPlayer != null) {
//                Log.e(TAG, "musicPause: 音乐暂停");
//                mPlayer.pause();
//            }
//        }
//
//        public void musicNext(String url) {
//            if (mPlayer != null) {
//                Log.e(TAG, "musicNext: 播放下一曲");
//                mPlayer.stop();
//                mPlayer.reset();
//                set_path(url);
//
//            } else {
//                mPlayer = new MediaPlayer();
//                mPlayer.setOnPreparedListener(this);
//                mPlayer.setOnCompletionListener(this);
//                set_path(url);
//            }
//        }
//
//        public void musicStop() {
//            if (mPlayer != null) {
//                Log.e(TAG, "musicStop: 音乐停止");
//                try {
//                    if (mPlayer.isPlaying()) {
//                        mPlayer.stop();
//                    }
//                } catch (Exception e) {
//                    e.getMessage();
//                }
//            }
//        }
//
//        public void musicDestroy() {
//            Log.e(TAG, "musicDestroy: MediaPlayer销毁");
//            if (mPlayer != null) {
//
//                if (mPlayer.isPlaying()) {
//                    mPlayer.stop();
//                    Log.e(TAG, "musicDestroy: mPlayer.stop()");
//                }
//            }
//            if (mPlayer != null) {
//                mPlayer.setOnCompletionListener(null);
//                mPlayer.setOnPreparedListener(null);
//                mPlayer.release();
//                mPlayer = null;
//                Log.e(TAG, "musicDestroy: MediaPlayer 设置为==== null");
//            }
//
//        }
//
//        public boolean isService() {
//            return flag;
//        }
//
//        public boolean isPrepared() {
//            return isPrepared;
//        }
//
//        @Override
//        public void onPrepared(MediaPlayer mp) {
//            Log.d(TAG, "onPrepared: " + "new MediaPlayer 准备好了开始播放");
//            mp.start();
//            isPrepared = true;
//            if (mCallBack != null) {
//                mCallBack.isPrepared(true);
//            }
//        }
//
//        @Override
//        public void onCompletion(MediaPlayer mp) {
//            Log.e(TAG, "musicEnd: binder播放结束");
//            if (mp != null && mp.isPlaying()) {
//                mp.stop();
//                mp.release();
//                mPlayer = null;
//            }
//            isPrepared = false;
//            if (mCallBack != null) {
//                mCallBack.isPrepared(false);
//            }
//            //处理错误
//            if (doErrorThings()) {
//                return;
//            }
//        }
//
//    }
//
//    // 准备好了的监听
//    @Override
//    public void onPrepared(MediaPlayer mp) {
//        Log.d(TAG, "onPrepared: " + "准备好了开始播放");
//        mp.start();
//        isPrepared = true;
//        if (mCallBack != null) {
//            mCallBack.isPrepared(true);
//        }
//
//    }
//
//    @Override
//    public void onCompletion(MediaPlayer mp) {
//        //        当前音乐正在播放 切换音乐时需要重置一下
//        Log.e(TAG, "musicEnd: 外部播放结束");
//        if (mp != null && mp.isPlaying()) {
//            mp.stop();
//            mp.release();
//            mPlayer = null;
//        }
//        isPrepared = false;
//        if (mCallBack != null) {
//            mCallBack.isPrepared(false);
//        }
//        //处理错误
//        if (doErrorThings()) {
//            return;
//        }
//        if (play_mode == 0) {
//            current++;
//        } else if (play_mode == 1) {
//            current = current;
//        }
//        if (current >= musicList.size()) {
//            if (play_mode == 0) {
//                current = 0;
//            } else if (play_mode == 1) {
//                current = current;
//            }
//            // 列表播放完成
//            if (playFinishCallBack != null) {
//                playFinishCallBack.isFinish(true, this.current);
//            }
//            return;
//        }
//        // 顺序播放bufen
//        String path = musicList.get(current);
//        if (!TextUtils.isEmpty(path)) {
//            // 列表播放完成
//            if (playFinishCallBack != null) {
//                playFinishCallBack.isFinish(false, current);
//            }
//            mPlayer.stop();
//            mPlayer.reset();
//            set_path(path);
//////			EventBus.getDefault().post(new ChooseEvent(current));
//        }
//    }
//
//    private boolean doErrorThings() {
//        //超过十次停止
//        if (12 > errorTimes && errorTimes > 10) {
////			EventBus.getDefault().post(new MessageEvent(false));
//            if (mPlayer != null) {
//                if (mPlayer.isPlaying()) {
//                    mPlayer.stop();
//                }
//                // 释放流 释放资源
//                mPlayer.release();
//            }
//            stopSelf();
//        }
//        //回调错误 返回
//        if (isError) {
//            errorTimes++;
//            return true;
//        }
//        return false;
//    }
//
//    // 在视频或者音频播放的是出错了  回调的方法
//    @Override
//    public boolean onError(MediaPlayer mp, int what, int extra) {
//        //一般的情况就会在这里让用户从新加载（dialog Toast）
//        Log.e(TAG, "onError: what= " + what + " ,extra=" + extra);
//        isError = true;
//        return false;
//    }
//
//    @Override
//    public boolean stopService(Intent name) {
//        Log.e(TAG, "stopService: 执行停止服务");
//        return super.stopService(name);
//    }
//
//    @Override
//    public boolean onUnbind(Intent intent) {
//        Log.e(TAG, "onUnbind: 解绑服务");
//        flag = false;
//        mPath = "";
//        try {
//            if (mPlayer != null) {
//                if (mPlayer.isPlaying()) {
//                    mPlayer.stop();
//                }
//                // 释放流 释放资源
//                mPlayer.release();
//            }
//        } catch (Exception e) {
//        }
//        return super.onUnbind(intent);
//    }
//
//    @Override
//    public void unbindService(ServiceConnection conn) {
//        super.unbindService(conn);
//        flag = false;
//        Log.d(TAG, "unbindService: 执行解绑服务");
//        try {
//            if (mPlayer != null) {
//                if (mPlayer.isPlaying()) {
//                    mPlayer.stop();
//                }
//                // 释放流 释放资源
//                mPlayer.release();
//            }
//        } catch (Exception e) {
//        }
//    }
//
//    //销毁
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //在Activity 和其他有生命周期的一些控件里面发出网络请求，
//        // 务必要在 这个 Activity或者控件销毁的时候 取消网络请求
//        // 让后释放各种资源  释放资源的时候可以利用try catch 捕捉 程序所有异常
//        flag = false;
//        try {
//            if (mPlayer != null) {
//                if (mPlayer.isPlaying()) {
//                    mPlayer.pause();
//                }
//                // 释放流 释放资源
//                mPlayer.release();
//                mPlayer = null;
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        }
//    }
//
//    // 获取正在播放的时间
//    public String get_time() {
//        int time = 0;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
//        if (mPlayer != null) {
//            time = mPlayer.getCurrentPosition();
//        }
//        MyLogUtil.e("--geek1--", time + "");
//        return simpleDateFormat.format(time);
//    }
//
//    // 获取正在播放的时间2
//    public int get_time2() {
//        int time = 0;
//        if (mPlayer != null) {
//            time = mPlayer.getCurrentPosition();
//        }
//        MyLogUtil.e("--geek1--", time + "");
//        return time;
//    }
//
//    // 获取总时长
//    public String get_maxtime() {
//        int time = 0;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
//        if (mPlayer != null) {
//            time = mPlayer.getDuration();
//        }
//        MyLogUtil.e("--geek2--", time + "");
//        return simpleDateFormat.format(time);
//    }
//
//    // 是否在播放
//    public boolean is_playing() {
//        if (getmPlayer().isPlaying()) {
//            return true;
//        }
//        return false;
//    }
//
//    // 设置播放路径
//    public void set_path(String url) {
//        try {
//            if (url.contains("http") || url.contains(Environment.getExternalStorageDirectory().getAbsolutePath())) {
//                mPlayer.setDataSource(url);
//            } else {
//                AssetManager assetManager = BaseApp.get().getAssets();
//                AssetFileDescriptor fileDescriptor = assetManager.openFd(url);
//                mPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());// 设置数据源
//
//            }
//            mPlayer.prepareAsync();
//        } catch (IOException e) {
//            e.getMessage();
//        }
//    }
//
//    public interface DemoPreparedCallBack {
//        void isPrepared(boolean prepared);
//    }
//
//    public void setOnPrepared(DemoPreparedCallBack callBack) {
//        this.mCallBack = callBack;
//    }
//
//    public int getCurrent() {
//        return current;
//    }
//
//    public void setCurrent(int current) {
//        this.current = current;
//    }
//
//    public int getPlay_mode() {
//        return play_mode;
//    }
//
//    public void setPlay_mode(int play_mode) {
//        this.play_mode = play_mode;
//    }
//
//    public boolean isIs_houtai() {
//        return is_houtai;
//    }
//
//    public void setIs_houtai(boolean is_houtai) {
//        this.is_houtai = is_houtai;
//    }
//
//    public ArrayList<String> getMusicList() {
//        return musicList;
//    }
//
//    public void setMusicList(ArrayList<String> musicList) {
//        this.musicList = musicList;
//    }
//
//    private PlayFinishCallBack playFinishCallBack;
//
//    public interface PlayFinishCallBack {
//        void isFinish(boolean isfinish, int path);
//    }
//
//    public void setOnPlayFinishCallBack(PlayFinishCallBack playFinishCallBack) {
//        this.playFinishCallBack = playFinishCallBack;
//    }
//
//}
//
//
