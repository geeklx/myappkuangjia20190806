//package com.sairobo.alty;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.os.Environment;
//import android.text.TextUtils;
//
//import androidx.multidex.MultiDex;
//import androidx.multidex.MultiDexApplication;
//
//import com.blankj.utilcode.util.Utils;
//import com.bolex.autoEx.AutoEx;
//import com.example.shining.libglin.glin.interceptor.IResultInterceptor;
//import com.example.shining.libglin.juhenet.JuheNet;
//import com.example.shining.libglin.net.Net;
//import com.example.slbappcomm.broadcastreceiver.PhoneService;
//import com.example.slbappcomm.utils.BanbenCommonUtils;
//import com.haier.cellarette.baselibrary.changelanguage.LocalManageUtil;
//import com.haier.cellarette.baselibrary.common.BaseApp;
//import com.haier.cellarette.libretrofit.RetrofitNetNew;
//import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
//import com.haier.cellarette.libwebview.hois2.HiosHelper;
//import com.hjq.toast.ToastUtils;
//import com.meituan.android.walle.WalleChannelReader;
//import com.sairobo.alty.interceptor.Appdemo1ResultInterceptor;
//import com.tencent.bugly.Bugly;
//import com.umeng.commonsdk.UMConfigure;
//
//import cn.jiguang.analytics.android.api.JAnalyticsInterface;
//import cn.jiguang.share.android.api.JShareInterface;
//import cn.jiguang.share.android.api.PlatformConfig;
//import cn.jpush.android.api.JPushInterface;
//import me.jessyan.autosize.AutoSize;
//import me.jessyan.autosize.AutoSizeConfig;
//import me.jessyan.autosize.unit.Subunits;
//
////import android.support.multidex.MultiDex;
////import android.support.multidex.MultiDexApplication;
//
//
//public class AppApplication extends MultiDexApplication {
//    public static final String DIR_PROJECT = "/alty/app/";
//    public static final String DIR_CACHE = DIR_PROJECT + "cache/"; // 网页缓存路径
//    public static final String IMG_CACHE = DIR_PROJECT + "image/"; // image缓存路径
//    public static final String VIDEO_CACHE = DIR_PROJECT + "video/"; // video缓存路径
//    public static final String MUSIC_CACHE = DIR_PROJECT + "music/"; // music缓存路径
//
//    protected void attachBaseContext(Context base) {
//        //保存系统选择语言
//        LocalManageUtil.saveSystemCurrentLanguage(base);
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        //保存系统选择语言
//        LocalManageUtil.onConfigurationChanged(getApplicationContext());
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        // 腾讯bugly
//        configBugly();
//        //
//        LocalManageUtil.setApplicationLanguage(this);
////        MyLogUtil.on(true);
////        GlideOptionsFactory.init(this, R.drawable.ic_def_loading);
//        configHios();
//        configNet(true, new Appdemo1ResultInterceptor());
//        configJuheNet(true, new Appdemo1ResultInterceptor());
//        configRetrofitNet();
//        Utils.init(this);// com.blankj:utilcode:1.17.3
//
//        // AutoEx应用崩溃自动匹配Stack Overflow的解答
//        /*┌—————————————————————AutoEx——————————————————————
//        ├ 错误类型:android.content.res.Resources$NotFoundException: Resource ID #0x7f0b0056 type #0x12 is not valid。↑详细异常请往上滚动查看↑
//        ├ 推荐参考Stack Overflow上4条同类问题。↓点击下方连接查看↓
//        ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
//        ├ 标题:Android Resources$NotFoundException: Resource ID #0x7f030027
//        ├ 链接:https://stackoverflow.com/questions/21269502/android-resourcesnotfoundexception-resource-id-0x7f030027
//        ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
//        ├ 标题:android.content.res.Resources$NotFoundException: Resource ID #0x7f07007e
//        ├ 链接:https://stackoverflow.com/questions/48161713/android-content-res-resourcesnotfoundexception-resource-id-0x7f07007e
//        ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
//        ├ 标题:App crashes when adding an ImageView?
//        ├ 链接:https://stackoverflow.com/questions/47600747/app-crashes-when-adding-an-imageview
//        ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
//        ├ 标题:XML Android app will not load on phone
//        ├ 链接:https://stackoverflow.com/questions/48310838/xml-android-app-will-not-load-on-phone
//        └—————————————————————AutoEx——————————————————————*/
//        AutoEx.apply();
//
//        //初始化极光分享
//        configShare();
//        //初始化极光统计
//        configTongji();
//        //初始化极光推送
//        configTuisong();
//        // 初始化今日头条适配
//        configShipei();
//        // 播放听书
////        startService(new Intent(BaseApp.get(), ListenMusicPlayerService.class));
//        // 电话监听
////        cofigPhone();
//        //初始化Umeng统计
//        configUmengTongji();
//        // 为了横屏需求的toast
//        ToastUtils.init(this);
//    }
//
//    private void configUmengTongji() {
//        /**
//         * 设置walle当前渠道
//         */
//        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
//        MyLogUtil.e("--geek--",channel);
//        if (TextUtils.equals(BanbenCommonUtils.banben_comm, "测试")) {
//            UMConfigure.setLogEnabled(true);
//            UMConfigure.init(this, "5cfdbb010cafb2af14000480", channel, UMConfigure.DEVICE_TYPE_PHONE, null);
//        } else if (TextUtils.equals(BanbenCommonUtils.banben_comm, "预生产")) {
//            UMConfigure.setLogEnabled(false);
//            UMConfigure.init(this, "5cfdbb524ca357e9d900047b", channel, UMConfigure.DEVICE_TYPE_PHONE, null);
//        } else if (TextUtils.equals(BanbenCommonUtils.banben_comm, "线上")) {
//            UMConfigure.setLogEnabled(false);
//            UMConfigure.init(this, "5cfdbb524ca357e9d900047b", channel, UMConfigure.DEVICE_TYPE_PHONE, null);
//        }
//    }
//
//    private void cofigPhone() {
//        Intent intent = new Intent(BaseApp.get(), PhoneService.class);
//        startService(intent);
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            startForegroundService(intent);
////        } else {
////            startService(intent);
////        }
//    }
//
//    private void configBugly() {
//        /* Bugly SDK初始化
//         * 参数1：上下文对象
//         * 参数2：APPID，平台注册时得到,注意替换成你的appId
//         * 参数3：是否开启调试模式，调试模式下会输出'CrashReport'tag的日志
//         */
//        if (TextUtils.equals(BanbenCommonUtils.banben_comm, "测试")) {
////            CrashReport.initCrashReport(getApplicationContext(), "92e2540923", true);// 测试
//            Bugly.init(getApplicationContext(), "92e2540923", false);
//            MyLogUtil.on(true);
//            //
////            if (LeakCanary.isInAnalyzerProcess(this)) {
////                // This process is dedicated to LeakCanary for heap analysis.
////                // You should not init your app in this process.
////                return;
////            }
////            LeakCanary.install(this);
//        } else if (TextUtils.equals(BanbenCommonUtils.banben_comm, "预生产")) {
////            CrashReport.initCrashReport(getApplicationContext(), "99f9f601e0", true);// 预生产
//            Bugly.init(getApplicationContext(), "99f9f601e0", true);
//            MyLogUtil.on(true);
//        } else if (TextUtils.equals(BanbenCommonUtils.banben_comm, "线上")) {
////            CrashReport.initCrashReport(getApplicationContext(), "99f9f601e0", false);// 线上
//            Bugly.init(getApplicationContext(), "99f9f601e0", false);
//            MyLogUtil.on(false);
//        }
//    }
//
//    /**
//     * 注意!!! 布局时的实时预览在开发阶段是一个很重要的环节, 很多情况下 Android Studio 提供的默认预览设备并不能完全展示我们的设计图
//     * 所以我们就需要自己创建模拟设备, 以下链接是给大家的福利, 按照链接中的操作可以让预览效果和设计图完全一致!
//     *
//     * @see <a href="https://github.com/JessYanCoding/AndroidAutoSize/blob/master/README-zh.md#preview">dp、pt、in、mm 这四种单位的模拟设备创建方法</a>
//     * <p>
//     * v0.9.0 以后, AndroidAutoSize 强势升级, 将这个方案做到极致, 现在支持5种单位 (dp、sp、pt、in、mm)
//     * {1@link UnitsManager} 可以让使用者随意配置自己想使用的单位类型
//     * 其中 dp、sp 这两个是比较常见的单位, 作为 AndroidAutoSize 的主单位, 默认被 AndroidAutoSize 支持
//     * pt、in、mm 这三个是比较少见的单位, 只可以选择其中的一个, 作为 AndroidAutoSize 的副单位, 与 dp、sp 一起被 AndroidAutoSize 支持
//     * 副单位是用于规避修改 {1@link DisplayMetrics#density} 所造成的对于其他使用 dp 布局的系统控件或三方库控件的不良影响
//     * 您选择什么单位, 就在 layout 文件中用什么单位布局
//     * <p>
//     * 两个主单位和一个副单位, 可以随时使用 {1@link UnitsManager} 的方法关闭和重新开启对它们的支持
//     * 如果您想完全规避修改 {1@link DisplayMetrics#density} 所造成的对于其他使用 dp 布局的系统控件或三方库控件的不良影响
//     * 那请调用 {1@link UnitsManager#setSupportDP}、{1@link UnitsManager#setSupportSP} 都设置为 {@code false}
//     * 停止对两个主单位的支持 (如果开启 sp, 对其他三方库控件影响不大, 也可以不关闭对 sp 的支持)
//     * 并调用 {1@link UnitsManager#setSupportSubunits} 从三个冷门单位中选择一个作为副单位
//     * 三个单位的效果都是一样的, 按自己的喜好选择, 比如我就喜欢 mm, 翻译为中文是妹妹的意思
//     * 然后在 layout 文件中只使用这个副单位进行布局, 这样就可以完全规避修改 {1@link DisplayMetrics#density} 所造成的不良影响
//     * 因为 dp、sp 这两个单位在其他系统控件或三方库控件中都非常常见, 但三个冷门单位却非常少见
//     */
//    private void configShipei() {
//        //AndroidAutoSize 默认开启对 dp 的支持, 调用 UnitsManager.setSupportDP(false); 可以关闭对 dp 的支持
//        //主单位 dp 和 副单位可以同时开启的原因是, 对于旧项目中已经使用了 dp 进行布局的页面的兼容
//        //让开发者的旧项目可以渐进式的从 dp 切换到副单位, 即新页面用副单位进行布局, 然后抽时间逐渐的将旧页面的布局单位从 dp 改为副单位
//        //最后将 dp 全部改为副单位后, 再使用 UnitsManager.setSupportDP(false); 将 dp 的支持关闭, 彻底隔离修改 density 所造成的不良影响
//        //如果项目完全使用副单位, 则可以直接以像素为单位填写 AndroidManifest 中需要填写的设计图尺寸, 不需再把像素转化为 dp
//        AutoSizeConfig.getInstance().getUnitsManager()
//                .setSupportDP(true)
//
//                //AndroidAutoSize 默认开启对 sp 的支持, 调用 UnitsManager.setSupportSP(false); 可以关闭对 sp 的支持
//                //如果关闭对 sp 的支持, 在布局时就应该使用副单位填写字体的尺寸
//                //如果开启 sp, 对其他三方库控件影响不大, 也可以不关闭对 sp 的支持, 这里我就继续开启 sp, 请自行斟酌自己的项目是否需要关闭对 sp 的支持
////                .setSupportSP(false)
//
//                //AndroidAutoSize 默认不支持副单位, 调用 UnitsManager#setSupportSubunits() 可选择一个自己心仪的副单位, 并开启对副单位的支持
//                //只能在 pt、in、mm 这三个冷门单位中选择一个作为副单位, 三个单位的适配效果其实都是一样的, 您觉的哪个单位看起顺眼就用哪个
//                //您选择什么单位就在 layout 文件中用什么单位进行布局, 我选择用 mm 为单位进行布局, 因为 mm 翻译为中文是妹妹的意思
//                //如果大家生活中没有妹妹, 那我们就让项目中最不缺的就是妹妹!
//                .setSupportSubunits(Subunits.MM);
//        AutoSize.initCompatMultiProcess(this);
//    }
//
//    private void configTuisong() {
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//    }
//
//    private void configShare() {
//        JShareInterface.setDebugMode(true);
//        PlatformConfig platformConfig = new PlatformConfig()
//                .setWechat("wx211138e4dcf53523", "d6c11a12b785c4e0318f4d69984b17d2")// wxa3fa50c49fcd271c 746c2cd0f414de2c256c4f2095316bd0
//                .setQQ("1106011004", "YIbPvONmBQBZUGaN")
//                .setSinaWeibo("374535501", "baccd12c166f1df96736b51ffbf600a2", "https://www.jiguang.cn")
//                .setFacebook("1847959632183996", "JShareDemo")
//                .setTwitter("fCm4SUcgYI1wUACGxB2erX5pL", "NAhzwYCgm15FBILWqXYDKxpryiuDlEQWZ5YERnO1D89VBtZO6q")
//                .setJchatPro("1847959632183996");
//        JShareInterface.init(this, platformConfig);
//    }
//
//    private void configTongji() {
//        // 设置开启日志,发布时请关闭日志
//        JAnalyticsInterface.setDebugMode(true);
//        JAnalyticsInterface.init(this);
//
//    }
//
//    private void configHios() {
////        HiosRegister.load();
//        HiosHelper.config("slbapp.ad.web.page", "slbapp.web.page");
//        // 接收部分
//        // private int mAction; // default 0
//        // private String mSkuId; // maybe null
//        // private String mCategoryId;
//        // mAction = getIntent().getIntExtra("act", 0);
//        // mSkuId = getIntent().getStringExtra("sku_id");
//        // mCategoryId = getIntent().getStringExtra("category_id");
//    }
//
//    protected void configNet(boolean debug, IResultInterceptor interceptor) {
//        String cacheDir = Environment.getExternalStorageDirectory() + DIR_CACHE;
//
//        new Net.Builder()
//                .baseUrl("")
//                .debug(debug)
//                .timeout(30 * 1000)
//                .cacheDir(cacheDir)
//                .cacheSize(1024 * 1024)
//                .resultInterceptor(interceptor)
//                .build();
//    }
//
//    protected void configJuheNet(boolean debug, IResultInterceptor interceptor) {
//        String cacheDir = Environment.getExternalStorageDirectory() + DIR_CACHE;
//
//        new JuheNet.Builder()
//                .baseUrl("")
//                .debug(debug)
//                .timeout(30 * 1000)
//                .cacheDir(cacheDir)
//                .cacheSize(1024 * 1024)
//                .resultInterceptor(interceptor)
//                .build();
//    }
//
//    protected void configRetrofitNet() {
//        String cacheDir = Environment.getExternalStorageDirectory() + DIR_CACHE;
//        // https://api-cn.faceplusplus.com/
////        RetrofitNet.config();
//        RetrofitNetNew.config();
//    }
//
//}
