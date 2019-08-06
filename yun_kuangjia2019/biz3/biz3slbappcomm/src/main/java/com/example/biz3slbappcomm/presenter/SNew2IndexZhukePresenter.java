package com.example.biz3slbappcomm.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappcomm.api.SCommonApi;
import com.example.biz3slbappcomm.bean.SNew2IndexLianxiBean;
import com.example.biz3slbappcomm.bean.SNew2IndexZhukeBean;
import com.example.biz3slbappcomm.view.SNew2IndexLianxiView;
import com.example.biz3slbappcomm.view.SNew2IndexZhukeView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SNew2IndexZhukePresenter extends Presenter<SNew2IndexZhukeView> {

    public void getNew2IndexZhukeData(int page, int limit) {
        JSONObject requestData = new JSONObject();
        requestData.put("page", page);
        requestData.put("limit", limit);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SCommonApi.class, getIdentifier()).get_index_new2_two(BanbenUtils.getInstance().getVersion(),
                BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(),requestBody).enqueue(new Callback<ResponseSlbBean<SNew2IndexZhukeBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SNew2IndexZhukeBean>> call, Response<ResponseSlbBean<SNew2IndexZhukeBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnNew2IndexZhukeNodata(response.body().getMsg());
                    return;
                }
                getView().OnNew2IndexZhukeSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SNew2IndexZhukeBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnNew2IndexZhukeFail(string);
                call.cancel();
            }
        });

    }

}
