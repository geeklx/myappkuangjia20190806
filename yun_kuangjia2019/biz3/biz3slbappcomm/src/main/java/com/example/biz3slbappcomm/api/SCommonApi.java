package com.example.biz3slbappcomm.api;

import com.example.biz3slbappcomm.bean.SIndexAdvertisingBean;
import com.example.biz3slbappcomm.bean.SLBThreeMusicUrlBean;
import com.example.biz3slbappcomm.bean.SMyMedalCoolViewBean;
import com.example.biz3slbappcomm.bean.SMyMedalDetailBean;
import com.example.biz3slbappcomm.bean.SMyMedalDetailShareBean;
import com.example.biz3slbappcomm.bean.SNew2IndexLianxiBean;
import com.example.biz3slbappcomm.bean.SNew2IndexZhukeBean;
import com.example.biz3slbappcomm.bean.SPayBean;
import com.example.biz3slbappcomm.bean.SPaySuccessBean;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;
import com.haier.cellarette.libvariants.NetConfig;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SCommonApi {

    // 提交绘本阅读时间
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/updateReadTime")
    Call<ResponseSlbBean<Object>> get_my_huiben_time_new1(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 支付方式
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/unifedOrder")
    Call<ResponseSlbBean<SPayBean>> get_my_pay(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 支付成功页面
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/checkOrderStatus")
    Call<ResponseSlbBean<SPaySuccessBean>> get_my_paysuccess(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

    // 听书详情播放页面2 第三方音频地址
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/thirdPlay")
    Call<ResponseSlbBean<SLBThreeMusicUrlBean>> get_my_listenbookdetail_new1_threemusicurl(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 获取所有勋章列表
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/getMedalList")
    Call<ResponseSlbBean<SMyMedalCoolViewBean>> get_my_medal_new1(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

    // 获取勋章分享详情
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/getMedalShareDetails")
    Call<ResponseSlbBean<SMyMedalDetailShareBean>> get_my_medal_share1(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 获取勋章详情1
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/getMedalDetails")
    Call<ResponseSlbBean<SMyMedalDetailBean>> get_my_medal_detail1(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 获取勋章详情2
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/getLatestMedal")
    Call<ResponseSlbBean<SMyMedalDetailBean>> get_my_medal_detail2(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

    // 获取应用首页弹窗
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/homeBox")
    Call<ResponseSlbBean<SIndexAdvertisingBean>> get_index_advertising(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);


    // 练习列表
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE2 + "webapi/hxthought/listTagResource")
    Call<ResponseSlbBean<SNew2IndexLianxiBean>> get_index_new2_one(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 主课列表
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE2 + "webapi/hxthought/listMainLesson")
    Call<ResponseSlbBean<SNew2IndexZhukeBean>> get_index_new2_two(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 我的
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE2 + "picbook/audio/app/homeBox")
    Call<ResponseSlbBean<SIndexAdvertisingBean>> get_index_new2_three(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

}
