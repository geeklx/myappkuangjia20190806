//package com.example.slbappreadbook.service;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.Binder;
//import android.os.IBinder;
//import android.support.annotation.Nullable;
//import android.text.TextUtils;
//
//import com.blankj.utilcode.util.DeviceUtils;
//import com.blankj.utilcode.util.SPUtils;
//import com.example.biz3slbappusercenter.presenter.SReadTimePresenter;
//import com.example.biz3slbappusercenter.view.SReadTimeView;
//import com.example.slbappcomm.CommonUtils;
//import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
//
//public class Updataservices extends Service implements SReadTimeView {
//
//    // 统计页面
//    public static final String HUIBEN_READINGTIME_ACTION = "HUIBEN_READINGTIME_ACTION";
//    public static final String HUIBEN_READINGTIME = "统计绘本阅读页面时长";
//    public static final String id_zong = "Updataservices_id_zong";
//    public static final String id = "Updataservices_id";
//    public static final String type = "Updataservices_type";
//    public static final String type1 = "start";
//    public static final String type2 = "end";
//    public static final String time = "Updataservices_time";
//    public static final int UPDATA_MANAGE_NOTIFICATION_ID = 1001611;
//    private SReadTimePresenter presenter;
//
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return new MsgBinder();
//    }
//
//    @Override
//    public void OnReadTimeSuccess(String s) {
//        MyLogUtil.e("OnReadTimeSuccess", s);
////        Toasty.normal(BaseApp.get(), "").show();
//    }
//
//    @Override
//    public void OnReadTimeNodata(String s) {
//        MyLogUtil.e("OnReadTimeNodata", s);
////        Toasty.normal(BaseApp.get(), s).show();
//    }
//
//    @Override
//    public void OnReadTimeFail(String s) {
//        MyLogUtil.e("OnReadTimeFail", s);
////        Toasty.normal(BaseApp.get(), s).show();
//    }
//
//    @Override
//    public String getIdentifier() {
//        return System.currentTimeMillis() + "";
//    }
//
//    public class MsgBinder extends Binder {
//        public Updataservices getService() {
//            return Updataservices.this;
//        }
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        presenter = new SReadTimePresenter();
//        presenter.onCreate(this);
//
//    }
//
//    // 传值给后台统计绘本阅读时间
//    private void set_timerTo(String itemId, String pId, String type, String time) {
//        presenter.getReadTimeData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), itemId, pId, type, time);
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
////        startForeground(UPDATA_MANAGE_NOTIFICATION_ID, new Notification());
////
////        Intent it = new Intent(this, UpdataservicesBg.class);
////        it.putExtra(UpdataservicesBg.EXTRA_NOTIFICATION_ID, UPDATA_MANAGE_NOTIFICATION_ID);
////        startService(it);
//
//        if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
//            String action = intent.getAction();
//            if (action.equals(HUIBEN_READINGTIME_ACTION)) {
//                String id2 = intent.getStringExtra(id_zong);
//                String id1 = intent.getStringExtra(id);
//                String type1 = intent.getStringExtra(type);
//                String time1 = intent.getStringExtra(time);
//                set_timerTo(id2, id1, type1, time1);
//            }
//        }
//
//        return START_STICKY;
//    }
//
//    @Override
//    public void onDestroy() {
//        presenter.onDestory();
//        super.onDestroy();
//
//    }
//
//}
