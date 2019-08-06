package com.example.biz3slbappshouye.presenter;

import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SCategoryBean;
import com.example.biz3slbappshouye.view.SCategoryView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SCategoryPresenter extends Presenter<SCategoryView> {

    public static String TAG = SCategoryPresenter.class.getSimpleName();

    public void getCategoryData() {
//        JSONObject requestData = new JSONObject();
//        requestData.put("page", page);
//        requestData.put("limit", limit);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_index_categoryList(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).enqueue(new Callback<ResponseSlbBean<SCategoryBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SCategoryBean>> call, Response<ResponseSlbBean<SCategoryBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnCategoryNodata(response.body().getMsg());
                    return;
                }
                getView().OnCategorySuccess(response.body().getData(), TAG);
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SCategoryBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnCategoryFail(string);
                call.cancel();
            }
        });

    }
}
