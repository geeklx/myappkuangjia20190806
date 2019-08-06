package com.example.biz3slbappusercenter.api;

import com.example.biz3slbappusercenter.bean.SAppUpgradeBean;
import com.example.biz3slbappusercenter.bean.SAppUserInfoBean;
import com.example.biz3slbappusercenter.bean.SConfigureBean;
import com.example.biz3slbappusercenter.bean.SFkzxBean;
import com.example.biz3slbappusercenter.bean.SISSCBean;
import com.example.biz3slbappusercenter.bean.SJihuomaBean;
import com.example.biz3slbappusercenter.bean.SLB1SaveFavoritesBean;
import com.example.biz3slbappusercenter.bean.SLoginUserInfoBean;
import com.example.biz3slbappusercenter.bean.SNew1SearchBean;
import com.example.biz3slbappusercenter.bean.SSearchBean;
import com.example.biz3slbappusercenter.bean.SUserImgBean;
import com.example.biz3slbappusercenter.bean.SWelcomeSettingBean;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;
import com.haier.cellarette.libvariants.NetConfig;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface SMyApi {

    // app差量升级
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/appUpgrade")
    Call<ResponseSlbBean<SAppUpgradeBean>> get_my_appupgrade(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 提交绘本阅读时间
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/updateReadTime")
    Call<ResponseSlbBean<Object>> get_my_huiben_time(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 客户反馈
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/feedBack")
    Call<ResponseSlbBean<Object>> get_my_feedback(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 获取访客信息
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/appUserInfo")
    Call<ResponseSlbBean<SAppUserInfoBean>> get_my_appuserinfo(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

    // 收藏绘本
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/saveFavorites")
    Call<ResponseSlbBean<Object>> get_my_savefavorites(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 搜索
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/searchAll")
    Call<ResponseSlbBean<SSearchBean>> get_my_search(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 用户登录
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/login")
    Call<ResponseSlbBean<SLoginUserInfoBean>> get_my_login(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 微信登录
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/wxLoginUser")
    Call<ResponseSlbBean<SLoginUserInfoBean>> get_my_login_wechat(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 绑定微信
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/bindWeChat")
    Call<ResponseSlbBean<SLoginUserInfoBean>> get_my_bind_wechat(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 获取用户信息
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/getUserInfo")
    Call<ResponseSlbBean<SLoginUserInfoBean>> get_my_logininfo(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

    // 获取验证码
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/sendVCode")
    Call<ResponseSlbBean<Object>> get_my_verificationcode(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 退出登录
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/logout")
    Call<ResponseSlbBean<Object>> get_my_loginout(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 上传用户信息2
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/updateChildInfo")
    Call<ResponseSlbBean<Object>> get_my_uploadinfo2(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 上传用户信息2
//    @Multipart
////    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/updateUserInfo")
//    Call<ResponseSlbBean<Object>> get_my_uploadinfo(@Header("imei") String imei, @Header("token") String token,
//                                                    @Part("userinfo") RequestBody body1,
//                                                    @Part MultipartBody.Part body2);

    // 上传用户头像信息2
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @Multipart
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/upload")
    Call<ResponseSlbBean<SUserImgBean>> get_my_uploadimg(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token,
            /*@Part("userinfo") RequestBody body1,*/
                                                         @Part MultipartBody.Part body2);

    // 上传用户头像信息22
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/uploadBase64")
    Call<ResponseSlbBean<SUserImgBean>> get_my_uploadimg2(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token,
                                                          @Body RequestBody body);

    // 登录时上传
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/record/recordAppLogin")
    Call<ResponseSlbBean<Object>> get_my_loginonline(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 激活码
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/exchange")
    Call<ResponseSlbBean<SJihuomaBean>> get_my_jihuoma(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // new 1.6

    // 是否收藏听书
    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/getCollectAndShareInfo")
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/getCollectAndShareInfo")
    Call<ResponseSlbBean<SISSCBean>> get_my_is_savefavorites(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 收藏new1
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/saveFavorites")
    Call<ResponseSlbBean<SLB1SaveFavoritesBean>> get_my_save_tingshu_favorites(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 搜索new1
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/searchAll")
    Call<ResponseSlbBean<SNew1SearchBean>> get_my_search_new1(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 提交绘本阅读时间
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/updateReadTime")
    Call<ResponseSlbBean<Object>> get_my_huiben_time_new1(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 提交绘本阅读时间new1
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/updateReadTime")
    Call<ResponseSlbBean<Object>> get_my_huiben_time_new2(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 配置中心new1
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/getAdPage")
    Call<ResponseSlbBean<SWelcomeSettingBean>> get_welcome_setting(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 新配置中心
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/getConfigure")
    Call<ResponseSlbBean<SConfigureBean>> get_configure(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

    // 反馈中心联系信息
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/getContactInfo")
    Call<ResponseSlbBean<SFkzxBean>> get_fkzx_info(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

}
