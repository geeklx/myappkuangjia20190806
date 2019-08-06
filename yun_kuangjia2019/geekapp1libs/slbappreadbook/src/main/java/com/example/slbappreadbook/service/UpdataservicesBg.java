//package com.example.slbappreadbook.service;
//
//import android.app.Notification;
//import android.app.Service;
//import android.content.Intent;
//import android.os.IBinder;
//import android.support.annotation.Nullable;
//
//public class UpdataservicesBg extends Service {
//
//    public static final String EXTRA_NOTIFICATION_ID = "extra_notification_id_for_updata";
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
////        startForeground(intent.getIntExtra(EXTRA_NOTIFICATION_ID, 0), new Notification());
//
//        stopForeground(true);
//        stopSelf();
//
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//}
