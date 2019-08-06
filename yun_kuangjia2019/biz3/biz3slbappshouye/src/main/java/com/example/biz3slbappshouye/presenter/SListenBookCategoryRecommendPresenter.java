package com.example.biz3slbappshouye.presenter;

import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SListenBookCategoryRecommendBean;
import com.example.biz3slbappshouye.view.SListenBookCategoryRecommendView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SListenBookCategoryRecommendPresenter extends Presenter<SListenBookCategoryRecommendView> {

    public void getListenBookCategoryRecommendData() {
//        JSONObject requestData = new JSONObject();
//        requestData.put("page", page);
//        requestData.put("limit", limit);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_index2_categoryRecommend(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).enqueue(new Callback<ResponseSlbBean<SListenBookCategoryRecommendBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SListenBookCategoryRecommendBean>> call, Response<ResponseSlbBean<SListenBookCategoryRecommendBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnListenBookCategoryRecommendNodata(response.body().getMsg());
                    return;
                }
                getView().OnListenBookCategoryRecommendSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SListenBookCategoryRecommendBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnListenBookCategoryRecommendFail(string);
                call.cancel();
            }
        });

    }
}
