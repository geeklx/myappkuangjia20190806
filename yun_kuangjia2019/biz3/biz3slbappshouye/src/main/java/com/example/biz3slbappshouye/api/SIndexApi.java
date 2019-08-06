package com.example.biz3slbappshouye.api;

import com.example.biz3slbappshouye.bean.SBannerBean;
import com.example.biz3slbappshouye.bean.SCategoryBean;
import com.example.biz3slbappshouye.bean.SCategoryRecommendBean;
import com.example.biz3slbappshouye.bean.SHuibenDetailBean;
import com.example.biz3slbappshouye.bean.SHuibenDetailListBean;
import com.example.biz3slbappshouye.bean.SHuibenDetailTuijianBean;
import com.example.biz3slbappshouye.bean.SIndexCategoryRecommendBean;
import com.example.biz3slbappshouye.bean.SLB1CategoryBean;
import com.example.biz3slbappshouye.bean.SLB1HistoryBean;
import com.example.biz3slbappshouye.bean.SLB2CategoryListBean;
import com.example.biz3slbappshouye.bean.SLB3CategoryListBean;
import com.example.biz3slbappshouye.bean.SLB4CategoryListDetailBean;
import com.example.biz3slbappshouye.bean.SLBBannerBean;
import com.example.biz3slbappshouye.bean.SListCommBean;
import com.example.biz3slbappshouye.bean.SListenBookCategoryRecommendBean;
import com.example.biz3slbappshouye.bean.SListenBookDetailBean;
import com.example.biz3slbappshouye.bean.SMyBooksBean;
import com.example.biz3slbappshouye.bean.SPopRenwuBean;
import com.example.biz3slbappshouye.bean.SReadBookCategoryRecommendBean;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;
import com.haier.cellarette.libvariants.NetConfig;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SIndexApi {

    // 首页轮播图
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/banner")
    Call<ResponseSlbBean<SBannerBean>> get_index_lunbo(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 热门绘本
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/hotBookPage")
    Call<ResponseSlbBean<SListCommBean>> get_index_hot(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 国学经典
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/audio")
    Call<ResponseSlbBean<SListCommBean>> get_index_gxjd(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 我的书架
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/bookshelf")
    Call<ResponseSlbBean<SListCommBean>> get_index_mybooks(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 我的书架更多
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/bookshelfMore")
    Call<ResponseSlbBean<SListCommBean>> get_index_mybooksmore(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 历史记录
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/bookHis")
    Call<ResponseSlbBean<SListCommBean>> get_index_history(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 分类列表
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/categoryList")
    Call<ResponseSlbBean<SCategoryBean>> get_index_categoryList(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

    // 分类推荐
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/categoryRecommend")
    Call<ResponseSlbBean<SCategoryRecommendBean>> get_index_categoryRecommend(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

    // 绘本分类推荐
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/bookCategoryRecommend")
    Call<ResponseSlbBean<SReadBookCategoryRecommendBean>> get_index3_categoryRecommend(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

    // 绘本详情列表1
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/bookInfoNotPageList")
    Call<ResponseSlbBean<SHuibenDetailListBean>> get_my_huibenlist(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 绘本详情播放页面
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/bookItemInfo")
    Call<ResponseSlbBean<SHuibenDetailBean>> get_my_huibendetail(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 绘本详情播放页面推荐
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/recommendBook")
    Call<ResponseSlbBean<SHuibenDetailTuijianBean>> get_my_huibendetail_tuijian(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 听书详情播放页面
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/audio/play")
    Call<ResponseSlbBean<SListenBookDetailBean>> get_my_listenbookdetail(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 更新绘本阅读时间('触发升级'和'任务完成'事件时返回弹窗信息)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/updateReadTime")
    Call<ResponseSlbBean<SPopRenwuBean>> get_my_poprenwu(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // new 1.6
    // 首页听书轮播图
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/banner")
//    @POST("http://10.0.0.51:8081/picbook/audio/app/banner")
    Call<ResponseSlbBean<SLBBannerBean>> get_index_tingshulunbo(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 分类听书列表
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/categoryList")
//    @POST("http://10.0.0.51:8081/picbook/audio/app/categoryList")
    Call<ResponseSlbBean<SLB1CategoryBean>> get_index_tingshucategoryList(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

    // 听书分类推荐
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/categoryRecommend")
//    @POST("http://10.0.0.51:8081/picbook/audio/app/categoryRecommend")
    Call<ResponseSlbBean<SListenBookCategoryRecommendBean>> get_index2_categoryRecommend(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

    // 听书分类点击进入的列表1
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/audioList")
    Call<ResponseSlbBean<SLB2CategoryListBean>> get_index_gxjd2(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 听书分类点击进入的列表2
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/audioListByParent")
    Call<ResponseSlbBean<SLB3CategoryListBean>> get_index_gxjd3(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 听书详情播放页面2
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/audio/play2")
//    @POST("http://10.0.0.51:8081/picbook/audio/app/audio/play")
    Call<ResponseSlbBean<SLB4CategoryListDetailBean>> get_my_listenbookdetail_new1(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 历史记录new1
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/readHis")
    Call<ResponseSlbBean<SLB1HistoryBean>> get_index_history_new1(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 我的书架new1
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/audio/app/bookshelf")
    Call<ResponseSlbBean<SMyBooksBean>> get_index_mybooks_new1(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 首页分类推荐new2
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/v2/categoryRecommend")
//    @POST("http://10.0.0.15:8081/picbook/app/v2/categoryRecommend")
    Call<ResponseSlbBean<SIndexCategoryRecommendBean>> get_index_categoryRecommendnew2(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 全部分类列表new2
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/v2/categoryAll")
//    @POST("http://10.0.0.15:8081/picbook/app/v2/categoryAll")
    Call<ResponseSlbBean<SCategoryBean>> get_index_categoryListAllnew1(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 分类列表new2
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/v2/categoryEntry")
//    @POST("http://10.0.0.15:8081/picbook/app/v2/categoryEntry")
    Call<ResponseSlbBean<SCategoryBean>> get_index_categoryListnew1(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);


}
