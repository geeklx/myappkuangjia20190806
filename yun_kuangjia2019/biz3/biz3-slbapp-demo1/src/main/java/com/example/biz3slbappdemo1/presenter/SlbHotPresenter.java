package com.example.biz3slbappdemo1.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappdemo1.api.SlbHotApi;
import com.example.biz3slbappdemo1.bean.ResponseSlbBean;
import com.example.biz3slbappdemo1.bean.SlbHotBean;
import com.example.biz3slbappdemo1.bean.SlbHotModel1;
import com.example.biz3slbappdemo1.bean.SlbHotModel2;
import com.example.biz3slbappdemo1.bean.SlbHotModel3;
import com.example.biz3slbappdemo1.view.SlbHotView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlbHotPresenter extends Presenter<SlbHotView> {

    /**
     * 是否热门 bookHot:是否热书(0:是,1不是)
     * @param model
     */
    public void getSlbHotData(SlbHotModel1 model) {
//        JSONObject requestData = new JSONObject();
//        requestData.put("bookColumn", bookColumn);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));
        JSONObject requestData = (JSONObject) JSON.toJSON(model);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SlbHotApi.class, getIdentifier()).get_index_hot(requestBody).enqueue(new Callback<ResponseSlbBean<SlbHotBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SlbHotBean>> call, Response<ResponseSlbBean<SlbHotBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnHotNodata(response.body().getMsg());
                    return;
                }
                getView().OnHotSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SlbHotBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
                String string = t.toString();
                getView().OnHotFail(string);
                call.cancel();
            }
        });

    }

    /**
     * 分类
     * @param model
     */
    public void getSlbHotData2(SlbHotModel2 model) {
//        JSONObject requestData = new JSONObject();
//        requestData.put("bookColumn", bookColumn);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));
        JSONObject requestData = (JSONObject) JSON.toJSON(model);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SlbHotApi.class, getIdentifier()).get_index_hot(requestBody).enqueue(new Callback<ResponseSlbBean<SlbHotBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SlbHotBean>> call, Response<ResponseSlbBean<SlbHotBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnHotNodata(response.body().getMsg());
                    return;
                }
                getView().OnHotSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SlbHotBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
                String string = t.toString();
                getView().OnHotFail(string);
                call.cancel();
            }
        });

    }

 /**
     * 分类
     * @param model
     */
    public void getSlbHotData3(SlbHotModel3 model) {
//        JSONObject requestData = new JSONObject();
//        requestData.put("bookColumn", bookColumn);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));
        JSONObject requestData = (JSONObject) JSON.toJSON(model);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SlbHotApi.class, getIdentifier()).get_index_hot(requestBody).enqueue(new Callback<ResponseSlbBean<SlbHotBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SlbHotBean>> call, Response<ResponseSlbBean<SlbHotBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnHotNodata(response.body().getMsg());
                    return;
                }
                getView().OnHotSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SlbHotBean>> call, Throwable t) {
                String string = t.toString();
                getView().OnHotFail(string);
                call.cancel();
            }
        });

    }


}
