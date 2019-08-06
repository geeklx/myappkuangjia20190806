package com.example.biz3slbappdemo1.api;

import com.example.biz3slbappdemo1.bean.ResponseSlbBean;
import com.example.biz3slbappdemo1.bean.SlbIndexMyBooksBean;
import com.example.biz3slbappdemo1.bean.SlbMoreBooksBean;
import com.example.biz3slbappdemo1.bean.SlbMoreBooksCategoryBean;
import com.haier.cellarette.libvariants.NetConfig;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SlbCategoryApi {

    // 更多绘本
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "edu/books/listMoreBooks")
    Call<ResponseSlbBean<SlbMoreBooksBean>> get_index_more_books(@Body RequestBody body);

    // 绘本分类
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "edu/books/classify/listBooksClassify")
    Call<ResponseSlbBean<SlbMoreBooksCategoryBean>> get_index_more_books_category(@Body RequestBody body);

    // 首页我的书架
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(NetConfig.SERVER_ISERVICE + "edu/books/collection/listByPage")
    Call<ResponseSlbBean<SlbIndexMyBooksBean>> get_index_mybooks(@Body RequestBody body);

}
