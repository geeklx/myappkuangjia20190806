package com.example.slbappreadbook;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.example.biz3slbappshouye.bean.SHuibenDetailBean;
import com.example.biz3slbappshouye.bean.SHuibenDetailBean2;
import com.example.biz3slbappshouye.bean.SHuibenDetailTuijianBean;
import com.example.biz3slbappshouye.presenter.SHuibenDetailPresenter;
import com.example.biz3slbappshouye.presenter.SHuibenDetailTuijianPresenter;
import com.example.biz3slbappshouye.view.SHuibenDetaiTuijianView;
import com.example.biz3slbappshouye.view.SHuibenDetailCommView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.playermusic.SLB4CategoryListDetailBean1Temp;
import com.example.slbappcomm.services.UpdataCommonservices;
import com.example.slbappreadbook.callback.PreparedCallBack;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;

import java.util.ArrayList;

public class RbMusicPlayerService extends Service implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, SHuibenDetailCommView, SHuibenDetaiTuijianView {

    public static final String TAG_EXTRA_ID_FIRST = "TAG_EXTRA_ID_FIRST";
    public static final String TAG_EXTRA_ID_ZONG_FIRST = "TAG_EXTRA_ID_ZONG_FIRST";

    private String TAG = getClass().getSimpleName();
    private MediaPlayer mPlayer = new MediaPlayer();
    private String mPath;
    //	private ArrayList<String> musicList;
    private ArrayList<SLB4CategoryListDetailBean1Temp> musicListbean;
    private ArrayList<String> musicList;
    private int current;
    private boolean flag;
    private boolean isPrepared;
    private PreparedCallBack mCallBack;
    private boolean isError;
    private int errorTimes;
    private MyBinder myBinder = new MyBinder();
    private SHuibenDetailPresenter presenter;
    private SHuibenDetailBean sHuibenDetailBean;
    private String EXTRA_ID_FIRST = "";
    private String EXTRA_ID_ZONG_FIRST = "";
    private String EXTRA_ID_ZONG = "";// 以商品的id为txt的名字
    private String EXTRA_NAME = "";// 以商品的id为txt的名字
    private String next_id = "";
    private ReadBookObservable readBookObservable;

    public MediaPlayer getmPlayer() {
        return mPlayer;
    }

    // 服务的生命周期中  创建的方法
    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnErrorListener(this);
        current = 0;
        flag = true;
        last_pId = "";
        last_ItemId = "";
        readBookObservable = new ReadBookObservable();
        //
        presenter = new SHuibenDetailPresenter();
        presenter.onCreate(this);
        //
        presenter11 = new SHuibenDetailTuijianPresenter();
        presenter11.onCreate(this);
    }

    //销毁
    @Override
    public void onDestroy() {
        presenter.onDestory();
        presenter11.onDestory();
        super.onDestroy();
        //在Activity 和其他有生命周期的一些控件里面发出网络请求，
        // 务必要在 这个 Activity或者控件销毁的时候 取消网络请求
        // 让后释放各种资源  释放资源的时候可以利用try catch 捕捉 程序所有异常
        flag = false;
        try {
            if (mPlayer != null) {
                if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                }
                // 释放流 释放资源
                mPlayer.release();
                mPlayer = null;
            }
        } catch (Exception e) {
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
            String action = intent.getAction();
//            MyLogUtil.d(TAG, "alarm set repeat " + Calendar.getInstance().getTime().toString() + "     " + action);
//            if (ConstantUtil.BROADCAST_ACTION_START_TIMER_LOOP.equals(action)) {
//
//            }
        }
        return START_STICKY;
    }

    //这个方法主要是在用bind的方式开启服务的时候调用
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: 音乐服务绑定成功");
        return myBinder;
    }

    // 传值给后台统计绘本阅读时间
    public void setToId(String EXTRA_ID) {
//        ShowLoadingUtil.showLoading(, "", 1000, null);
        presenter.getHuibenDetailData(EXTRA_ID);
    }

    // 点击任意item播放
    private String is_free_itemid = "";

    public void setToId22(String bookId, String bookItemId, String name) {
        is_free_itemid = bookItemId;
        setNext_id(bookItemId);
        setEXTRA_ID_ZONG(bookId);
        setEXTRA_NAME(name);
        presenter11.getHuibenDetailTuijianData(SPUtils.getInstance().getString(TAG_EXTRA_ID_ZONG_FIRST), SPUtils.getInstance().getString(TAG_EXTRA_ID_FIRST));
    }

    // 点击下一本播放
    public void setToId2(String bookId, String bookItemId, String name) {
        setNext_id(bookItemId);
        setEXTRA_ID_ZONG(bookId);
        setEXTRA_NAME(name);
        presenter11.getHuibenDetailTuijianData(SPUtils.getInstance().getString(TAG_EXTRA_ID_ZONG_FIRST), SPUtils.getInstance().getString(TAG_EXTRA_ID_FIRST));
    }

    @Override
    public void OnHuibenDetailComm1Success(SHuibenDetailBean sHuibenDetailBeans) {
        MyLogUtil.e("OnHuibenDetailComm1Success", sHuibenDetailBeans.getBookItemDetails().size() + "");
        sHuibenDetailBean = sHuibenDetailBeans;
        //
        current = 0;
//        next_id = sHuibenDetailBeans.getNextBookItem().getBookItemId();
        if (sHuibenDetailBean.getNextBookItem() != null && TextUtils.isEmpty(sHuibenDetailBean.getNextBookItem().getBookItemId())) {
            sHuibenDetailBean.getNextBookItem().setBookItemId(getNext_id());
            sHuibenDetailBean.getNextBookItem().setBookId(getEXTRA_ID_ZONG());
            sHuibenDetailBean.getNextBookItem().setItemName(getEXTRA_NAME());
        }
        readBookObservable.setData(sHuibenDetailBean);
        //
        ArrayList<SLB4CategoryListDetailBean1Temp> list11 = new ArrayList<>();
        ArrayList<String> list12 = new ArrayList<>();
        for (SHuibenDetailBean2 bean : sHuibenDetailBeans.getBookItemDetails()) {
            list11.add(new SLB4CategoryListDetailBean1Temp(bean.getAudio(), bean.getDltName(), bean.getBookItemId(), bean.getBookId(), "", "", ""));
            list12.add(bean.getAudio());
        }
        musicList = new ArrayList<>();
        musicListbean = new ArrayList<>();
        musicListbean = list11;
        musicList = list12;
        String path = musicList.get(current);
        if (!TextUtils.isEmpty(path)) {
            mPlayer.stop();
            mPlayer.reset();
            set_path(path);
        }

    }

    @Override
    public void OnHuibenDetailComm1Nodata(String s) {
        MyLogUtil.e("OnHuibenDetailComm1Nodata", s);
        current = musicList.size();
        Toasty.normal(BaseApp.get(), "系列绘本已阅读完毕，请选择推荐绘本阅读~").show();
    }

    @Override
    public void OnHuibenDetailComm1Fail(String s) {
        MyLogUtil.e("OnHuibenDetailComm1Fail", s);
        current = musicList.size();
        Toasty.normal(BaseApp.get(), "系列绘本已阅读完毕，请选择推荐绘本阅读~").show();
    }

    // 最后一个页面的列表bufen
    private SHuibenDetailTuijianPresenter presenter11;

    @Override
    public void OnHuibenDetailTuijianSuccess(SHuibenDetailTuijianBean sHuibenDetailTuijianBean) {
        String next_id1 = "";
        String next_id_zong1 = "";
        String next_id_name1 = "";
        for (int i = 0; i < sHuibenDetailTuijianBean.getResult().size(); i++) {
            if (TextUtils.equals(getNext_id(), sHuibenDetailTuijianBean.getResult().get(i).getBookItemId())) {
                if (i + 1 == sHuibenDetailTuijianBean.getResult().size()) {
                    if (TextUtils.isEmpty(is_free_itemid)) {
                        next_id1 = "";
                        next_id_zong1 = "";
                        next_id_name1 = "";
                    } else {
                        next_id1 = sHuibenDetailTuijianBean.getResult().get(i).getBookItemId();
                        next_id_zong1 = sHuibenDetailTuijianBean.getResult().get(i).getBookId();
                        next_id_name1 = sHuibenDetailTuijianBean.getResult().get(i).getBookName();
                        is_free_itemid = "";
                    }
                } else {
                    if (TextUtils.isEmpty(is_free_itemid)) {
                        next_id1 = sHuibenDetailTuijianBean.getResult().get(i + 1).getBookItemId();
                        next_id_zong1 = sHuibenDetailTuijianBean.getResult().get(i + 1).getBookId();
                        next_id_name1 = sHuibenDetailTuijianBean.getResult().get(i + 1).getBookName();
                    } else {
                        next_id1 = sHuibenDetailTuijianBean.getResult().get(i).getBookItemId();
                        next_id_zong1 = sHuibenDetailTuijianBean.getResult().get(i).getBookId();
                        next_id_name1 = sHuibenDetailTuijianBean.getResult().get(i).getBookName();
                        is_free_itemid = "";
                    }

                }
            }

        }
        setNext_id(next_id1);
        setEXTRA_ID_ZONG(next_id_zong1);
        setEXTRA_NAME(next_id_name1);
        setToId(getNext_id());
    }

    @Override
    public void OnHuibenDetailTuijianNodata(String s) {

    }

    @Override
    public void OnHuibenDetailTuijianFail(String s) {

    }

    @Override
    public String getIdentifier() {
        return System.currentTimeMillis() + "";
    }

    public class MyBinder extends Binder implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

        public ReadBookObservable getMyObservable() {
            return readBookObservable;
        }

        public RbMusicPlayerService getService() {
            return RbMusicPlayerService.this;
        }

        public void musicContinue() {
            if (mPlayer != null) {
                mPlayer.start();
            }
        }

        public void musicStart(Context context, String url) {

            if (mPlayer != null) {
                Log.e(TAG, "musicStart: 开始播放");
                try {
                    if (url.contains("http") || url.contains(Environment.getExternalStorageDirectory().getAbsolutePath())) {
                        mPlayer.setDataSource(url);
                    } else {
                        AssetManager assetManager = context.getAssets();
                        AssetFileDescriptor fileDescriptor = assetManager.openFd(url);
                        mPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());// 设置数据源

                    }
                    mPlayer.prepareAsync();
                } catch (Exception e) {
                }
            } else {
                Log.e(TAG, "musicStart: 创建新的MediaPlayer ---> 开始播放");
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setOnPreparedListener(this);
                    if (url.contains("http") || url.contains(Environment.getExternalStorageDirectory().getAbsolutePath())) {
                        mPlayer.setDataSource(url);
                    } else {
                        AssetManager assetManager = context.getAssets();
                        AssetFileDescriptor fileDescriptor = assetManager.openFd(url);
                        mPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());// 设置数据源

                    }
                    mPlayer.prepareAsync();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void musicPause() {
            if (mPlayer != null) {
                Log.e(TAG, "musicPause: 音乐暂停");
                mPlayer.pause();
            }
        }

        public void musicNext(Context context, String url) {
            if (mPlayer != null) {
                Log.e(TAG, "musicNext: 播放下一曲");

                try {
                    mPlayer.stop();
                    mPlayer.reset();
                    if (url.contains("http") || url.contains(Environment.getExternalStorageDirectory().getAbsolutePath())) {
                        mPlayer.setDataSource(url);
                    } else {
                        AssetManager assetManager = context.getAssets();
                        AssetFileDescriptor fileDescriptor = assetManager.openFd(url);
                        mPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());// 设置数据源

                    }
                    mPlayer.prepareAsync();
                } catch (Exception e) {
                }

            } else {
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setOnPreparedListener(this);
                    mPlayer.setOnCompletionListener(this);
                    mPlayer.setDataSource(url);
                    mPlayer.prepareAsync();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void musicStop() {
            if (mPlayer != null) {
                Log.e(TAG, "musicStop: 音乐停止");
                try {
                    if (mPlayer.isPlaying()) {
                        mPlayer.stop();
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }

        public void musicDestroy() {
            Log.e(TAG, "musicDestroy: MediaPlayer销毁");
            try {
                if (mPlayer != null) {

                    if (mPlayer.isPlaying()) {
                        mPlayer.stop();
                        Log.e(TAG, "musicDestroy: mPlayer.stop()");
                    }
                }
                if (mPlayer != null) {
                    mPlayer.setOnCompletionListener(null);
                    mPlayer.setOnPreparedListener(null);
                    mPlayer.release();
                    mPlayer = null;
                    Log.e(TAG, "musicDestroy: MediaPlayer 设置为==== null");
                }
            } catch (Exception e) {
            }
            // 统计页面开始时间 end next
//            if (current < musicListbean.size()) {
//                Intent it2 = new Intent(BaseApp.get(), UpdataCommonservices.class);
//                it2.setAction(UpdataCommonservices.HUIBEN_READINGTIME_ACTION);
//                it2.putExtra(UpdataCommonservices.id_zong, musicListbean.get(current).getpId());
//                it2.putExtra(UpdataCommonservices.id, musicListbean.get(current).getItemId());
//                it2.putExtra(UpdataCommonservices.type, UpdataCommonservices.type2);
//                it2.putExtra(UpdataCommonservices.sourceType, UpdataCommonservices.sourceType1);
//                it2.putExtra(UpdataCommonservices.time, System.currentTimeMillis() + "");
//                startService(it2);
//            }
        }

        public boolean isService() {
            return flag;
        }

        public boolean isPrepared() {
            return isPrepared;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            Log.d(TAG, "onPrepared: " + "new MediaPlayer 准备好了开始播放");
            mp.start();
            isPrepared = true;
            if (mCallBack != null) {
                mCallBack.isPrepared(true);
            }
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            Log.e(TAG, "musicEnd: binder播放结束");
            if (mp != null && mp.isPlaying()) {
                mp.stop();
                mp.release();
                mPlayer = null;
            }
            isPrepared = false;
            if (mCallBack != null) {
                mCallBack.isPrepared(false);
            }
            //处理错误
            if (doErrorThings()) {
                return;
            }
        }
    }

    // 准备好了的监听
    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(TAG, "onPrepared: " + "准备好了开始播放");
        mp.start();
        isPrepared = true;
        if (mCallBack != null) {
            mCallBack.isPrepared(true);
        }

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //        当前音乐正在播放 切换音乐时需要重置一下
        Log.e(TAG, "musicEnd: 外部播放结束");
        if (mp != null && mp.isPlaying()) {
            mp.stop();
            mp.release();
            mPlayer = null;
        }
        isPrepared = false;
        if (mCallBack != null) {
            mCallBack.isPrepared(false);
        }
        //处理错误
        if (doErrorThings()) {
            return;
        }
        current++;
        if (current >= musicList.size()) {
//            next_id = "";
            if (SPUtils.getInstance().getInt(CommonUtils.READBOOK_AUTOBUTTON, -1) == 1) {
                MyLogUtil.e("---geee454----getEXTRA_ID_FIRST()--", getEXTRA_ID_FIRST());
//                presenter11.getHuibenDetailTuijianData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), getEXTRA_ID_ZONG_FIRST(), getEXTRA_ID_FIRST());
                presenter11.getHuibenDetailTuijianData(SPUtils.getInstance().getString(TAG_EXTRA_ID_ZONG_FIRST), SPUtils.getInstance().getString(TAG_EXTRA_ID_FIRST));
            } else {
                current = musicList.size();
            }
//            current = musicList.size();
            return;
        }
        String path = musicList.get(current);
        if (!TextUtils.isEmpty(path)) {
            MyLogUtil.e("---path222----", path);
            if (mPlayer != null) {
                mPlayer.stop();
                mPlayer.reset();
                set_path(path);
            }
        }
//		current++;
//		if (current >= musicList.size()) {
//			current = 0;
//		}
//		String path = musicList.get(current);
//		if (!TextUtils.isEmpty(path)) {
//			try {
//				mPlayer.setDataSource(path);
//				// 异步准备
//				mPlayer.prepareAsync();
//			} catch (IOException e) {
//				Log.e(TAG, "onStartCommand: " + e.getMessage());
//				return;
//			}
////			EventBus.getDefault().post(new ChooseEvent(current));
//		}
    }

    // 设置播放路径
    public void set_path(String url) {
        set_timer_start_end();
        try {
            if (url.contains("http") || url.contains(Environment.getExternalStorageDirectory().getAbsolutePath())) {
                mPlayer.setDataSource(url);
            } else {
                AssetManager assetManager = BaseApp.get().getAssets();
                AssetFileDescriptor fileDescriptor = assetManager.openFd(url);
                mPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());// 设置数据源

            }
            mPlayer.prepareAsync();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private String last_pId = "";
    private String last_ItemId = "";

    public void set_timer_start_end() {
        if (TextUtils.isEmpty(last_pId)) {
            // 第一次进来start now
            last_pId = musicListbean.get(current).getpId();
            last_ItemId = musicListbean.get(current).getItemId();
//            set_updatecomm_services(last_pId, last_ItemId, UpdataCommonservices.type1);
        } else {
            // 统计页面开始时间 end now
            if (!TextUtils.equals(last_pId, musicListbean.get(current).getpId())) {
                set_updatecomm_services(last_pId, last_ItemId, UpdataCommonservices.type2);
                // start next
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 统计页面开始时间 star next
                        set_updatecomm_services(musicListbean.get(current).getpId(), musicListbean.get(current).getItemId(), UpdataCommonservices.type1);
                        last_pId = musicListbean.get(current).getpId();
                        last_ItemId = musicListbean.get(current).getItemId();
                    }
                }, 1000);
            }

        }
    }

    private void set_updatecomm_services(String pId, String ItemId, String type11) {
        Intent it = new Intent(this, UpdataCommonservices.class);
        it.setAction(UpdataCommonservices.HUIBEN_READINGTIME_ACTION);
        it.putExtra(UpdataCommonservices.id_zong, pId);
        it.putExtra(UpdataCommonservices.id, ItemId);
        it.putExtra(UpdataCommonservices.type, type11);
        it.putExtra(UpdataCommonservices.sourceType, UpdataCommonservices.sourceType2);
        it.putExtra(UpdataCommonservices.time, System.currentTimeMillis() + "");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForegroundService(it);
//        } else {
//            // Pre-O behavior.
//            startService(it);
//        }
        startService(it);
    }

    private boolean doErrorThings() {
        //超过十次停止
        if (12 > errorTimes && errorTimes > 10) {
//			EventBus.getDefault().post(new MessageEvent(false));
            if (mPlayer != null) {
                if (mPlayer.isPlaying()) {
                    mPlayer.stop();
                }
                // 释放流 释放资源
                mPlayer.release();
            }
            stopSelf();
        }
        //回调错误 返回
        if (isError) {
            errorTimes++;
            return true;
        }
        return false;
    }

    // 在视频或者音频播放的是出错了  回调的方法
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        //一般的情况就会在这里让用户从新加载（dialog Toast）
        Log.e(TAG, "onError: what= " + what + " ,extra=" + extra);
        isError = true;
        return false;
    }

    @Override
    public boolean stopService(Intent name) {
        Log.e(TAG, "stopService: 执行停止服务");
        return super.stopService(name);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: 解绑服务");
        flag = false;
        mPath = "";
        try {
            if (mPlayer != null) {
                if (mPlayer.isPlaying()) {
                    mPlayer.stop();
                }
                // 释放流 释放资源
                mPlayer.release();
            }
        } catch (Exception e) {
        }
        return super.onUnbind(intent);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        flag = false;
        Log.d(TAG, "unbindService: 执行解绑服务");
        try {
            if (mPlayer != null) {
                if (mPlayer.isPlaying()) {
                    mPlayer.stop();
                }
                // 释放流 释放资源
                mPlayer.release();
            }
        } catch (Exception e) {
        }
    }


    public void setOnPrepared(PreparedCallBack callBack) {
        this.mCallBack = callBack;
    }

    private boolean is_heng_shurb;
    private boolean is_setmusicplayrb;
    private int currentrb;

    public boolean isIs_heng_shurb() {
        return is_heng_shurb;
    }

    public void setIs_heng_shurb(boolean is_heng_shurb) {
        this.is_heng_shurb = is_heng_shurb;
    }

    public boolean isIs_setmusicplayrb() {
        return is_setmusicplayrb;
    }

    public void setIs_setmusicplayrb(boolean is_setmusicplayrb) {
        this.is_setmusicplayrb = is_setmusicplayrb;
    }

    public int getCurrentrb() {
        return currentrb;
    }

    public void setCurrentrb(int currentrb) {
        this.currentrb = currentrb;
    }

    public ArrayList<String> getMusicList() {
        return musicList;
    }

    public void setMusicList(ArrayList<String> musicList) {
        this.musicList = musicList;
    }

    public ArrayList<SLB4CategoryListDetailBean1Temp> getMusicListbean() {
        return musicListbean;
    }

    public void setMusicListbean(ArrayList<SLB4CategoryListDetailBean1Temp> musicListbean) {
        this.musicListbean = musicListbean;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public SHuibenDetailBean getsHuibenDetailBean() {
        return sHuibenDetailBean;
    }

    public void setsHuibenDetailBean(SHuibenDetailBean sHuibenDetailBean) {
        this.sHuibenDetailBean = sHuibenDetailBean;
    }

    public String getNext_id() {
        return next_id;
    }

    public void setNext_id(String next_id) {
        this.next_id = next_id;
    }

    public String getEXTRA_ID_FIRST() {
        return EXTRA_ID_FIRST;
    }

    public void setEXTRA_ID_FIRST(String EXTRA_ID_FIRST) {
        this.EXTRA_ID_FIRST = EXTRA_ID_FIRST;
    }

    public String getEXTRA_ID_ZONG_FIRST() {
        return EXTRA_ID_ZONG_FIRST;
    }

    public void setEXTRA_ID_ZONG_FIRST(String EXTRA_ID_ZONG_FIRST) {
        this.EXTRA_ID_ZONG_FIRST = EXTRA_ID_ZONG_FIRST;
    }

    public String getEXTRA_ID_ZONG() {
        return EXTRA_ID_ZONG;
    }

    public void setEXTRA_ID_ZONG(String EXTRA_ID_ZONG) {
        this.EXTRA_ID_ZONG = EXTRA_ID_ZONG;
    }

    public String getEXTRA_NAME() {
        return EXTRA_NAME;
    }

    public void setEXTRA_NAME(String EXTRA_NAME) {
        this.EXTRA_NAME = EXTRA_NAME;
    }


}


