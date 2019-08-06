package com.example.biz3slbappdemo1.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappdemo1.api.SlbBannerApi;
import com.example.biz3slbappdemo1.bean.ResponseSlbBean;
import com.example.biz3slbappdemo1.bean.SlbBannerBean;
import com.example.biz3slbappdemo1.view.SlbBannerView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlbBannerPresenter extends Presenter<SlbBannerView> {

    public void getSlbBannerData(final int categoryId, final int homePage) {
        JSONObject requestData = new JSONObject();
        requestData.put("categoryId", categoryId);
        requestData.put("homePage", homePage);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SlbBannerApi.class, getIdentifier()).get_index_lunbo2(requestBody).enqueue(new Callback<ResponseSlbBean<SlbBannerBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SlbBannerBean>> call, Response<ResponseSlbBean<SlbBannerBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnNodata(response.body().getMsg());
                    return;
                }
                getView().OnSuccess(response.body().getData(), categoryId, homePage);
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SlbBannerBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
                String string = t.toString();
                getView().OnFail(string);
                call.cancel();
            }
        });

    }
}
