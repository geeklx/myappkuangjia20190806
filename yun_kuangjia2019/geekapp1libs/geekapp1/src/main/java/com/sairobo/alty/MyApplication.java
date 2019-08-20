package com.sairobo.alty;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Environment;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.bolex.autoEx.AutoEx;
import com.example.shining.libglin.glin.interceptor.IResultInterceptor;
import com.example.shining.libglin.juhenet.JuheNet;
import com.example.shining.libglin.net.Net;
import com.example.slbappcomm.broadcastreceiver.PhoneService;
import com.example.slbappcomm.utils.BanbenCommonUtils;
import com.haier.cellarette.baselibrary.changelanguage.LocalManageUtil;
import com.haier.cellarette.baselibrary.mmkv.MmkvUtils;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.hjq.toast.ToastUtils;
import com.meituan.android.walle.WalleChannelReader;
import com.sairobo.alty.interceptor.Appdemo1ResultInterceptor;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.umeng.commonsdk.UMConfigure;

import java.util.Locale;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatformConfig;
import cn.jpush.android.api.JPushInterface;
import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

//import com.meituan.android.walle.WalleChannelReader;

/**
 * 自定义ApplicationLike类.
 * <p>
 * 注意：这个类是Application的代理类，以前所有在Application的实现必须要全部拷贝到这里<br/>
 *
 * @author wenjiewu
 * @since 2016/11/7
 */
public class MyApplication extends MultiDexApplication {

    public static final String TAG = "Tinker.SampleApplicationLike";
    public static final String DIR_PROJECT = "/alty/app/";
    public static final String DIR_CACHE = DIR_PROJECT + "cache/"; // 网页缓存路径
    public static final String IMG_CACHE = DIR_PROJECT + "image/"; // image缓存路径
    public static final String VIDEO_CACHE = DIR_PROJECT + "video/"; // video缓存路径
    public static final String MUSIC_CACHE = DIR_PROJECT + "music/"; // music缓存路径

    @Override
    public void onCreate() {
        super.onCreate();
        // tinker
        configTinker();
        // 腾讯bugly
        configBugly();
        // 语言切换
        LocalManageUtil.setApplicationLanguage(this);
//        GlideOptionsFactory.init(this, R.drawable.ic_def_loading);
        configHios();
        configNet(true, new Appdemo1ResultInterceptor());
        configJuheNet(true, new Appdemo1ResultInterceptor());
        configRetrofitNet();
        Utils.init(this);// com.blankj:utilcode:1.17.3
        AutoEx.apply();
        //初始化极光分享
        configShare();
        //初始化极光统计
        configTongji();
        //初始化极光推送
        configTuisong();
        // 初始化今日头条适配
        configShipei();
        // 播放听书
//        startService(new Intent(BaseApp.get(), ListenMusicPlayerService.class));
        // 电话监听
//        cofigPhone();
        //初始化Umeng统计
        configUmengTongji();
        // 为了横屏需求的toast
        ToastUtils.init(this);
        // ndk
        configNDK();
        // mmkv
        configmmkv();
    }

    private void configmmkv() {
        MmkvUtils.getInstance(this).get("");
        MmkvUtils.getInstance(this).get_demo();
    }

    private void configNDK() {
        JNIUtils jniUtils = new JNIUtils();
        MyLogUtil.e("--JNIUtils--", jniUtils.stringFromJNI());
    }

    private void configTinker() {
        setStrictMode();
        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁，默认为true
        Beta.canAutoDownloadPatch = true;
        // 设置是否自动合成补丁，默认为true
        Beta.canAutoPatch = true;
        // 设置是否提示用户重启，默认为false
        Beta.canNotifyUserRestart = true;
        // 补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                Toast.makeText(getApplicationContext(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Toast.makeText(getApplicationContext(),
                        String.format(Locale.getDefault(), "%s %d%%",
                                Beta.strNotificationDownloading,
                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String msg) {
                Toast.makeText(getApplicationContext(), "补丁下载成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadFailure(String msg) {
                Toast.makeText(getApplicationContext(), "补丁下载失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onApplySuccess(String msg) {
                Toast.makeText(getApplicationContext(), "补丁应用成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String msg) {
                Toast.makeText(getApplicationContext(), "补丁应用失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {

            }
        };
    }

    @Override
    protected void attachBaseContext(Context base) {
        //保存系统选择语言
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(base);
        MultiDex.install(this);
        // TODO: 安装tinker
        Beta.installTinker(this);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //保存系统选择语言
        LocalManageUtil.onConfigurationChanged(this);
    }

    protected void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Beta.unInit();
    }


    private void configUmengTongji() {
        /**
         * 设置walle当前渠道
         */
        String channel = WalleChannelReader.getChannel(this);
        MyLogUtil.e("--geek--", channel);
        if (TextUtils.equals(BanbenCommonUtils.banben_comm, "测试")) {
            UMConfigure.setLogEnabled(true);
            UMConfigure.init(this, "5cfdbb010cafb2af14000480", channel, UMConfigure.DEVICE_TYPE_PHONE, null);
        } else if (TextUtils.equals(BanbenCommonUtils.banben_comm, "预生产")) {
            UMConfigure.setLogEnabled(true);
            UMConfigure.init(this, "5cfdbb524ca357e9d900047b", channel, UMConfigure.DEVICE_TYPE_PHONE, null);
        } else if (TextUtils.equals(BanbenCommonUtils.banben_comm, "线上")) {
            UMConfigure.setLogEnabled(false);
            UMConfigure.init(this, "5cfdbb524ca357e9d900047b", channel, UMConfigure.DEVICE_TYPE_PHONE, null);
        }
    }

    private void cofigPhone() {
        Intent intent = new Intent(this, PhoneService.class);
        startService(intent);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForegroundService(intent);
//        } else {
//            startService(intent);
//        }
    }

    private void configBugly() {
        // 设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
        Bugly.setIsDevelopmentDevice(this, true);
        // 多渠道需求塞入
        // String channel = WalleChannelReader.getChannel(this);
        // Bugly.setAppChannel(this, channel);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
//        Bugly.init(this, "e0b1ba785f", true);
        if (TextUtils.equals(BanbenCommonUtils.banben_comm, "测试")) {
//            CrashReport.initCrashReport(this, "92e2540923", true);// 测试
            Bugly.init(this, "92e2540923", true);
            MyLogUtil.on(true);
            //
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        } else if (TextUtils.equals(BanbenCommonUtils.banben_comm, "预生产")) {
//            CrashReport.initCrashReport(this, "99f9f601e0", true);// 预生产
            Bugly.init(this, "99f9f601e0", true);
            MyLogUtil.on(true);
        } else if (TextUtils.equals(BanbenCommonUtils.banben_comm, "线上")) {
//            CrashReport.initCrashReport(this, "99f9f601e0", false);// 线上
            Bugly.init(this, "99f9f601e0", false);
            MyLogUtil.on(false);
        }
    }

    private void configShipei() {
        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(true)
                .setSupportSubunits(Subunits.MM);
        AutoSize.initCompatMultiProcess(this);
    }

    private void configTuisong() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    private void configShare() {
        JShareInterface.setDebugMode(true);
        PlatformConfig platformConfig = new PlatformConfig()
                .setWechat("wx211138e4dcf53523", "d6c11a12b785c4e0318f4d69984b17d2")// wxa3fa50c49fcd271c 746c2cd0f414de2c256c4f2095316bd0
                .setQQ("1106011004", "YIbPvONmBQBZUGaN")
                .setSinaWeibo("374535501", "baccd12c166f1df96736b51ffbf600a2", "https://www.jiguang.cn")
                .setFacebook("1847959632183996", "JShareDemo")
                .setTwitter("fCm4SUcgYI1wUACGxB2erX5pL", "NAhzwYCgm15FBILWqXYDKxpryiuDlEQWZ5YERnO1D89VBtZO6q")
                .setJchatPro("1847959632183996");
        JShareInterface.init(this, platformConfig);
    }

    private void configTongji() {
        // 设置开启日志,发布时请关闭日志
        JAnalyticsInterface.setDebugMode(true);
        JAnalyticsInterface.init(this);

    }

    private void configHios() {
//        HiosRegister.load();// 静态注册部分 已弃用
        HiosHelper.config("slbapp.ad.web.page", "slbapp.web.page");
    }

    protected void configNet(boolean debug, IResultInterceptor interceptor) {
        String cacheDir = Environment.getExternalStorageDirectory() + DIR_CACHE;

        new Net.Builder()
                .baseUrl("https://www.baidu.com")
                .debug(debug)
                .timeout(30 * 1000)
                .cacheDir(cacheDir)
                .cacheSize(1024 * 1024)
                .resultInterceptor(interceptor)
                .build();
    }

    protected void configJuheNet(boolean debug, IResultInterceptor interceptor) {
        String cacheDir = Environment.getExternalStorageDirectory() + DIR_CACHE;

        new JuheNet.Builder()
                .baseUrl("https://www.baidu.com")
                .debug(debug)
                .timeout(30 * 1000)
                .cacheDir(cacheDir)
                .cacheSize(1024 * 1024)
                .resultInterceptor(interceptor)
                .build();
    }

    protected void configRetrofitNet() {
        String cacheDir = Environment.getExternalStorageDirectory() + DIR_CACHE;
        // https://api-cn.faceplusplus.com/
//        RetrofitNet.config();
        RetrofitNetNew.config();
    }

}
