package com.haier.cellarette.libretrofit.utils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;

public class BanbenUtils {

    private static volatile BanbenUtils instance;

    private BanbenUtils() {
    }

    public static BanbenUtils getInstance() {
        if (instance == null) {
            synchronized (BanbenUtils.class) {
                instance = new BanbenUtils();
            }
        }
        return instance;
    }

    //    public static final String banben = NetConfig.versionNameConfig;
    public String net_tips = "网络异常，请检查网络连接！";
    public String version = "";
    public String imei = "";
    public String token = "";

    public String getVersion() {
        return AppUtils.getAppVersionName(AppUtils.getAppPackageName());
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getImei() {
        return DeviceUtils.getAndroidID();
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getToken() {
        return SPUtils.getInstance().getString("用户的TOKEN", "");
    }

    public void setToken(String token) {
        this.token = token;
    }
}
