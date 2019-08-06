package com.example.biz3slbappshouye.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SIndexCategoryRecommendBean;
import com.example.biz3slbappshouye.bean.SListenBookCategoryRecommendBean;
import com.example.biz3slbappshouye.view.SIndexCategoryRecommendView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SIndexCategoryRecommendPresenter extends Presenter<SIndexCategoryRecommendView> {

    public void getIndexCategoryRecommendData(String type) {
        JSONObject requestData = new JSONObject();
        requestData.put("type", type);
//        requestData.put("limit", limit);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_index_categoryRecommendnew2(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SIndexCategoryRecommendBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SIndexCategoryRecommendBean>> call, Response<ResponseSlbBean<SIndexCategoryRecommendBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnIndexCategoryRecommendNodata(response.body().getMsg());
                    return;
                }
                getView().OnIndexCategoryRecommendSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SIndexCategoryRecommendBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnIndexCategoryRecommendFail(string);
                call.cancel();
            }
        });

    }
}
