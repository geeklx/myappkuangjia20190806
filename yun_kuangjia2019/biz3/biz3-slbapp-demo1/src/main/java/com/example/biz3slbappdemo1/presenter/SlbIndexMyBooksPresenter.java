package com.example.biz3slbappdemo1.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappdemo1.api.SlbCategoryApi;
import com.example.biz3slbappdemo1.bean.ResponseSlbBean;
import com.example.biz3slbappdemo1.bean.SlbDetailModel;
import com.example.biz3slbappdemo1.bean.SlbIndexMyBooksBean;
import com.example.biz3slbappdemo1.bean.SlbIndexMyBooksModel;
import com.example.biz3slbappdemo1.view.SlbIndexMyBooksView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlbIndexMyBooksPresenter extends Presenter<SlbIndexMyBooksView> {

    /**
     * 绘本首页 -> 我的书架
     *
     * @param model
     */
    public void getSlbIndexMyBooksData(SlbIndexMyBooksModel model) {

        JSONObject requestData = (JSONObject) JSON.toJSON(model);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SlbCategoryApi.class, getIdentifier()).get_index_mybooks(requestBody).enqueue(new Callback<ResponseSlbBean<SlbIndexMyBooksBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SlbIndexMyBooksBean>> call, Response<ResponseSlbBean<SlbIndexMyBooksBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnIndexMyBooksNodata(response.body().getMsg());
                    return;
                }
                getView().OnIndexMyBooksSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SlbIndexMyBooksBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
                String string = t.toString();
                getView().OnIndexMyBooksFail(string);
                call.cancel();
            }
        });

    }

}
