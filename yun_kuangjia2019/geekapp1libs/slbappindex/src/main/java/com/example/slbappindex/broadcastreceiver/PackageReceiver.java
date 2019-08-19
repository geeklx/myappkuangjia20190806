package com.example.slbappindex.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PackageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_ADDED) || TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_REPLACED)) {
//            String packageName = intent.getData().getSchemeSpecificPart();
//            if (TextUtils.equals(packageName, "com.sairobo.hxpb") && !SlbLoginUtil2.get().isUserLogin()) {
//                SPUtils.getInstance().put("appInstall", "appInstall");
//                Intent intent2 = new Intent("hs.act.slbapp.SlbLoginActivity");
//                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent2);
//            }
//        }

    }
}
