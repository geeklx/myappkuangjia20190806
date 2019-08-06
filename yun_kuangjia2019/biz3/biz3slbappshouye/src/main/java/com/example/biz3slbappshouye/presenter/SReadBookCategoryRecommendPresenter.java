package com.example.biz3slbappshouye.presenter;

import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SReadBookCategoryRecommendBean;
import com.example.biz3slbappshouye.view.SReadBookCategoryRecommendView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SReadBookCategoryRecommendPresenter extends Presenter<SReadBookCategoryRecommendView> {

    public void getReadBookCategoryRecommendData() {
//        JSONObject requestData = new JSONObject();
//        requestData.put("page", page);
//        requestData.put("limit", limit);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_index3_categoryRecommend(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).enqueue(new Callback<ResponseSlbBean<SReadBookCategoryRecommendBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SReadBookCategoryRecommendBean>> call, Response<ResponseSlbBean<SReadBookCategoryRecommendBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnReadBookCategoryRecommendNodata(response.body().getMsg());
                    return;
                }
                getView().OnReadBookCategoryRecommendSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SReadBookCategoryRecommendBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnReadBookCategoryRecommendFail(string);
                call.cancel();
            }
        });

    }
}
