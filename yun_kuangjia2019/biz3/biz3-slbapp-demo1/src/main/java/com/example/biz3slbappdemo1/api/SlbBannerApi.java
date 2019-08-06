package com.example.biz3slbappdemo1.api;

import com.example.biz3slbappdemo1.bean.ResponseSlbBean;
import com.example.biz3slbappdemo1.bean.SlbBannerBean;
import com.haier.cellarette.libvariants.NetConfig;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SlbBannerApi {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "edu/books/listHomePageScroll")
    Call<ResponseSlbBean<SlbBannerBean>> get_index_lunbo(@Body RequestBody body);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/banner")
    Call<ResponseSlbBean<SlbBannerBean>> get_index_lunbo2(@Body RequestBody body);



}
