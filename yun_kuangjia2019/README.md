# 博客：http://blog.51cto.com/liangxiao
# Android业务组件库：http://blog.51cto.com/liangxiao/2146536
# APP框架2019年版本
多渠道打包和闭包，支持分包安装，UI独立，网络请求独立
此项目还有一些不完善的地方，可以留言或如有疑问请看地址：https://blog.csdn.net/qibin0506/article/details/71307301

# 另外新增：支持androidx，版本号到最新：28  28.0.3  3.4.2  5.1.1

1.支持美团walle多渠道，不会的看地址：https://www.jianshu.com/p/c516cf9138ea
地址：https://blog.csdn.net/yinxing2008/article/details/82588075

2.支持tinker1.9.3.2多渠道热修复，不会的看地址：http://v.qq.com/vplus/bugly

3.支持护眼模式：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\huyan

4.支持类网易云播放器后台保活：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\playermusic

5.支持HIOS3.0版本，支持scheme协议，不会的参考地址：https://www.cnblogs.com/whoislcj/p/5825333.html
地址：https://www.jianshu.com/p/57f79fc83233
地址：https://www.cnblogs.com/zhang-cb/p/7093769.html
地址：https://blog.csdn.net/cheng545/article/details/80099137
地址：https://www.cnblogs.com/whoislcj/p/5825333.html

6.支持电话，第三方语音视频打断音乐：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\broadcastreceiver

7.支持打分星星和半星：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\ratingstarview

8.支持一行集成支付宝，微信，银联支付：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\pop\bottompay

9.支持一行分享到第三方：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\pop\share

10.支持截长图到bitmap：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\utils\cutimg\ShareBitmapUtils.java

11.支持蓝牙传输数据交互：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\videoplay\bt

12.支持GSY播放器：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\videoplay\gsy

13.支持饺子播放器：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\videoplay\jiaozi
附：全局搜索查看demo用法：JZVideoPlayerStandard

14.支持XCL-Charts曲线图：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\videoplay\quxiantu

15.支持EvenBus（全局搜索：EventBus.getDefault()）

16.支持LxCoolViewPager：优化100页卡顿，滑动翻书效果
附：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\viewpager\LxCoolViewPager.java

17.支持打字效果：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\widgets\dazi

18.支持图片懒加载：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\widgets\recyclerviewnice\XRecyclerView.java

19.支持图片四个角圆形自定义：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\widgets\CustomRoundAngleImageView.java

20.支持EasyPermission权限：D:\githubproject\yun_kuangjia2019\geeklibs\slbappcomm\src\main\java\com\example\slbappcomm\base\EasyPermissionMainFragment.java

21.支持自定义webview2.0：新增js退出交互操作D:\githubproject\yun_kuangjia2019\geeklibs\libwebview

22.支持一行jpush推送：D:\githubproject\yun_kuangjia2019\geeklibs\slbappjpush

23.支持一行jpush统计：D:\githubproject\yun_kuangjia2019\geeklibs\slbappstatistics

24.支持一行Umeng统计：D:\githubproject\yun_kuangjia2019\geeklibs\slbappumengstatistics

25.支持Glide4.9.0：D:\githubproject\yun_kuangjia2019\geekcommonlibs\libglide47

26.支持自定义升级：D:\githubproject\yun_kuangjia2019\geekcommonlibs\libupdateapputils

27.支持bugly升级：https://github.com/BuglyDevTeam/Bugly-Android-Demo/issues

28.支持Cmake最新NDK写法：D:\githubproject\yun_kuangjia2019\geekapp1libs\geekapp1\build.gradle

29.支持LottieAnimationView：全局搜索：LottieAnimationView

附：打版方式：
APP打版步骤：
1.切换publish_config下的0 1 2 ：
 defaultVariantIndex = 2
    // 测试，预生产，线上
    variants = ['BXNT_101_NATION', 'BXNY_101_NATION', 'BXN_101_NATION']

2.在Terminal中执行顺序：
gradlew chVar
gradlew publishDefault或publish
前者是打一个 后者是全量

3.每个独立lib下面的//    /**rep*/implementation (name:'biz-identification_bxn_nation', ext:'aar')
引用都不能换行，保证aar切换环境正常。

### ![梁肖51CTO](https://wx1.sinaimg.cn/mw690/60d2c107ly1g5q12j1qb0j20em0ktjs2.jpg "梁肖51CTO")

附demo用法
地址：https://github.com/geeklx/MyApplication
地址：https://github.com/geeklx/myapplication2018


### 此类放置自定义View和第三方控件集合：（按顺序）

## anroomcrash：验证APP崩溃和内存溢出的方法

## assetsfitandroid：1.拍照上传
                  2.复制assets目录到本地缓存cache目录文件
                  3.访问assets/cache中的文件方法
                  4.缓存购物车写法 写入txt到缓存

## bannerview：防止OOM写法的bannerview

## bannerviewquan：市面大部分bannerview写法

## baseactivitys：加了堆栈管理和678android权限的base类

## cacheutil：清除缓存方法

## caranimation：购物车添加动画（防止多次点击的OOM）

## changelanguage：切换语言

## common：BaseApp BaseAppManager BaseViewHelper

## emptyview：正在加载 暂无数据 有数据 无网络 布局切换

## expandableview：非常好用的Expandableview

## fileprovider：鸿洋的6.0 7.0 8.0 权限管理类

## flowlayout：鸿洋的FlowLayout（支持标签布局选中）

## glidenetpicpressnormal：支持后台返回两张图片 touch显示不同的图片效果

## handleralluse：面试必会的handler所有用法

## likebutton：点赞效果

## loading：加载中（防止OOM）

## musicutils：播放raw util

## networkview：根据WIFI开关来广播切换布局 斗鱼视频联网播放功能

## qcode：高斯生成二维码效果

## recycleviewalluses：最强RecycleView写法（待更新）

## recycleviewgallery：炫酷的RecycleView Gallery

## recycleviewmultitype：一个比较好的第三方RecycleView写法 很稳定

## ringutil：系统声音（待优化）

## scrollview：黑暗之魂3阅读条文效果

## shoppingcar：非常好用 仿饿了么购物车一套代码

## size：dp转换px工具类

## smartbar：返回|主页 工具类 一行解决你的问题

## splash：欢迎页仿猎聘

## statusbar：浸透式topbar

## tablayout：最全tablayout

## toasts：弃用（可参考）

## toasts2：丰富多彩的Toast

## updateapk：XML配置升级APK

## uploadpic：最强微信图片上传（无bug版 修改了loaderManager两次finish的问题）

## usersdk：登录回调处理逻辑跳转

## widget： 1.AlertView：仿IOS弹窗
         2.AutofitTextView：自适应的textview
         3.CircleImageView：圆形ImageView
         4.CircularSeekBar：中规中矩的SeekBar 圆形进度条
         5.SmoothCheckBox：中规中矩的CheckBox
         6.SwitchButton：中规中矩的开关

## zothers：1.AutoHideInputMethodFrameLayout：非常好用的键盘隐藏工具layout
         6.SpannableStringUtils：给textview设置超链接
          ((TextView) helper.getView(R.id.brademo1_tweetText)).setText(SpannableStringUtils.getBuilder(item.getText()).
                         append("点击查看博客链接").setClickSpan(new ClickableSpan() {
                     @Override
                     public void onClick(View widget) {
                         Uri url = Uri.parse("http://blog.51cto.com/liangxiao");
                         Intent intent = new Intent(Intent.ACTION_VIEW);
                         intent.setData(url);
                         mContext.startActivity(intent);
                     }
                 }).create());
         2.ClickableMovementMethod：给textview设置超链接
          ((TextView) helper.getView(R.id.brademo1_tweetText)).setMovementMethod(ClickableMovementMethod.getInstance());
                 helper.getView(R.id.brademo1_tweetText).setFocusable(false);
                 helper.getView(R.id.brademo1_tweetText).setClickable(true);
                 helper.getView(R.id.brademo1_tweetText).setLongClickable(false);
         3.DaojishiUtil：非常好用倒计时的工具类
         4.NoDoubleClickListener：防止double click
         5.SimpleTagImageView：给ImageView打标签

         7.StartHiddenManager：点A B两点跳转逻辑方法

## zuni：非常好的阻尼效果

## 下面是部分效果图：更多用法请移步：http://blog.51cto.com/liangxiao

### ![梁肖51CTO](https://wx4.sinaimg.cn/mw690/60d2c107ly1ftudgzst4og20ba0k0x51.gif "梁肖51CTO")  
### ![梁肖51CTO](https://wx3.sinaimg.cn/mw690/60d2c107ly1ftudgzpoufg20ba0k0jxc.gif "梁肖51CTO")  
### ![梁肖51CTO](https://wx1.sinaimg.cn/mw690/60d2c107ly1ftudgzqnajg20ba0k012m.gif "梁肖51CTO") 
### ![梁肖51CTO](https://wx1.sinaimg.cn/mw690/60d2c107ly1ftnbay0mqbg20dw0opkhi.gif "梁肖51CTO")
### ![梁肖51CTO](https://wx4.sinaimg.cn/mw690/60d2c107ly1ftnbc8mxzyg20dw0opwym.gif "梁肖51CTO")
### ![梁肖51CTO](https://wx3.sinaimg.cn/mw690/60d2c107ly1ftnce3s0mrg20ba0k07ic.gif "梁肖51CTO")
### ![梁肖51CTO](https://wx1.sinaimg.cn/mw690/60d2c107ly1ftncc2d7h3g20ba0k0ajm.gif "梁肖51CTO")
### ![梁肖51CTO](https://wx2.sinaimg.cn/mw690/60d2c107ly1ftnb7pdk7wg20dw0op4qq.gif "梁肖51CTO")
### ![梁肖51CTO](https://s3.51cto.com/wyfs02/M00/9E/B7/wKioL1mVDj6T2v-1AARlCOkAPj8669.gif "梁肖51CTO")

### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531893717234223.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531893822431543.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894411995588.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894417966982.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894423572051.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894430136803.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894448685899.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894462941636.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894466158206.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894471153683.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894477885689.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894545670445.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894563399011.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894577397951.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894591879580.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894597102229.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894697219346.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894702569453.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894707637124.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")


























