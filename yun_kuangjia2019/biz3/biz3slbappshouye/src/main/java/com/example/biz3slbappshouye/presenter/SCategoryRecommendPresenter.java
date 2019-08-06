package com.example.biz3slbappshouye.presenter;

import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SCategoryRecommendBean;
import com.example.biz3slbappshouye.view.SCategoryRecommendView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SCategoryRecommendPresenter extends Presenter<SCategoryRecommendView> {

    public static String TAG = SCategoryRecommendPresenter.class.getSimpleName();

    public void getCategoryRecommendData() {
//        JSONObject requestData = new JSONObject();
//        requestData.put("page", page);
//        requestData.put("limit", limit);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_index_categoryRecommend(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).enqueue(new Callback<ResponseSlbBean<SCategoryRecommendBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SCategoryRecommendBean>> call, Response<ResponseSlbBean<SCategoryRecommendBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnCategoryRecommendNodata(response.body().getMsg());
                    return;
                }
                getView().OnCategoryRecommendSuccess(response.body().getData(), TAG);
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SCategoryRecommendBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnCategoryRecommendFail(string);
                call.cancel();
            }
        });

    }
}
