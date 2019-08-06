package com.example.biz3slbappusercenter.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.bean.SJihuomaBean;
import com.example.biz3slbappusercenter.view.SJihuomaView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SJihuomaPresenter extends Presenter<SJihuomaView> {

    public static String TAG = SJihuomaPresenter.class.getSimpleName();

    public void getJihuomaData(String code) {
        JSONObject requestData = new JSONObject();
        requestData.put("code", code);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_jihuoma(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SJihuomaBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SJihuomaBean>> call, Response<ResponseSlbBean<SJihuomaBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnJihuomaNodata(response.body().getMsg());
                    return;
                }
                getView().OnJihuomaSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SJihuomaBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnJihuomaFail(string);
                call.cancel();
            }
        });

    }
}
