package com.example.biz3slbapporder.api;

import com.example.biz3slbapporder.bean.SOrderInfoBean;
import com.example.biz3slbapporder.bean.SOrderListBean;
import com.example.biz3slbapporder.bean.SVIPCardBean;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;
import com.haier.cellarette.libvariants.NetConfig;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SOrderApi {

    // 订单列表
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/orderList")
    Call<ResponseSlbBean<SOrderListBean>> get_my_orderlist(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // 订单支付页面
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/getPriceInfo")
    Call<ResponseSlbBean<SOrderInfoBean>> get_my_ordersure(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token, @Body RequestBody body);

    // VIP充值卡
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/priceList")
//    @POST("http://10.0.0.15:8081/picbook/app/priceList")
    Call<ResponseSlbBean<SVIPCardBean>> get_my_vipcard(@Header("hxAppVersion") String version, @Header("imei") String imei, @Header("token") String token);

}
