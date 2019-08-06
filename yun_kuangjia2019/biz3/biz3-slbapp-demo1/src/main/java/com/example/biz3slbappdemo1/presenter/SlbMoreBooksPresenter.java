package com.example.biz3slbappdemo1.presenter;

import com.example.biz3slbappdemo1.api.SlbCategoryApi;
import com.example.biz3slbappdemo1.bean.ResponseSlbBean;
import com.example.biz3slbappdemo1.bean.SlbMoreBooksBean;
import com.example.biz3slbappdemo1.view.SlbMoreBooksView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlbMoreBooksPresenter extends Presenter<SlbMoreBooksView> {


    public void getSlbMoreBooksData() {
//        JSONObject requestData = new JSONObject();
//        requestData.put("bookColumn", bookColumn);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));
//        JSONObject requestData = (JSONObject) JSON.toJSON(model);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), "");

        RetrofitNetNew.build(SlbCategoryApi.class, getIdentifier()).get_index_more_books(requestBody).enqueue(new Callback<ResponseSlbBean<SlbMoreBooksBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SlbMoreBooksBean>> call, Response<ResponseSlbBean<SlbMoreBooksBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnMoreBooksNodata(response.body().getMsg());
                    return;
                }
                getView().OnMoreBooksSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SlbMoreBooksBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
                String string = t.toString();
                getView().OnMoreBooksFail(string);
                call.cancel();
            }
        });

    }
}
