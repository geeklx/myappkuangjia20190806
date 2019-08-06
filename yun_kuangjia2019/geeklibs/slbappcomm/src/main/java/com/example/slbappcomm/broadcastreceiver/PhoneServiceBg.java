package com.example.slbappcomm.broadcastreceiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
//import android.support.annotation.Nullable;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.haier.cellarette.baselibrary.common.BaseApp;

public class PhoneServiceBg extends Service {

    public static final String EXTRA_NOTIFICATION_ID = "extra_notification_id_for_updata";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(intent.getIntExtra(EXTRA_NOTIFICATION_ID, 1), she_notifichanged());

        stopForeground(true);
        stopSelf();

        return super.onStartCommand(intent, flags, startId);
    }

    public static final String PHONE_CHANNEL_ID = "PHONE_NOTIFY_ID";
    public static final String PHONE_CHANNEL_NAME = "PHONE_NOTIFY_NAME";

    public static Notification she_notifichanged() {
        NotificationManager notificationManager = (NotificationManager) BaseApp.get().getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(PHONE_CHANNEL_ID, PHONE_CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            notification = new NotificationCompat.Builder(BaseApp.get(), PHONE_CHANNEL_ID)
                    .setChannelId(PHONE_CHANNEL_ID)
                    .build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(BaseApp.get(), PHONE_CHANNEL_ID)
                    .setOngoing(true)
                    .setChannelId(PHONE_CHANNEL_ID);//无效
            notification = notificationBuilder.build();
        }
        return notification;
    }

}
