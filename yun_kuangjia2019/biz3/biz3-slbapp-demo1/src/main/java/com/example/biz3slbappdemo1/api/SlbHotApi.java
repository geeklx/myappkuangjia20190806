package com.example.biz3slbappdemo1.api;

import com.example.biz3slbappdemo1.bean.ResponseSlbBean;
import com.example.biz3slbappdemo1.bean.SlbBannerBean;
import com.example.biz3slbappdemo1.bean.SlbHotBean;
import com.haier.cellarette.libvariants.NetConfig;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SlbHotApi {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "edu/books/listBooksPage")
    Call<ResponseSlbBean<SlbHotBean>> get_index_hot(@Body RequestBody body);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "picbook/app/hotBookPage")
    Call<ResponseSlbBean<SlbHotBean>> get_index_hot2(@Body RequestBody body);



}
