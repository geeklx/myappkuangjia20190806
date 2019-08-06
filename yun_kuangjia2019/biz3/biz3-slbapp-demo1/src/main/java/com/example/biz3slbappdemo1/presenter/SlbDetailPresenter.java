package com.example.biz3slbappdemo1.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappdemo1.api.SlbDetailApi;
import com.example.biz3slbappdemo1.bean.ResponseSlbBean;
import com.example.biz3slbappdemo1.bean.SlbDetailBean;
import com.example.biz3slbappdemo1.bean.SlbDetailModel;
import com.example.biz3slbappdemo1.view.SlbDetailView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlbDetailPresenter extends Presenter<SlbDetailView> {

    /**
     * 绘本详情页面 轮播绘本
     *
     * @param model
     */
    public void getSlbDetailData(SlbDetailModel model) {

        JSONObject requestData = (JSONObject) JSON.toJSON(model);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SlbDetailApi.class, getIdentifier()).get_index_detail(requestBody).enqueue(new Callback<ResponseSlbBean<SlbDetailBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SlbDetailBean>> call, Response<ResponseSlbBean<SlbDetailBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnDetailNodata(response.body().getMsg());
                    return;
                }
                getView().OnDetailSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SlbDetailBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
                String string = t.toString();
                getView().OnDetailFail(string);
                call.cancel();
            }
        });

    }

}
