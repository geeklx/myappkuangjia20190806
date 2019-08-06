package com.example.biz3slbappdemo1.api;

import com.example.biz3slbappdemo1.bean.ResponseSlbBean;
import com.example.biz3slbappdemo1.bean.SlbDetailBean;
import com.haier.cellarette.libvariants.NetConfig;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SlbDetailApi {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "edu/books/details/listByPage")
    Call<ResponseSlbBean<SlbDetailBean>> get_index_detail(@Body RequestBody body);


}
