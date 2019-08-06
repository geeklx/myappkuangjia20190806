package com.example.biz3slbappshouye.presenter;

import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SLB1CategoryBean;
import com.example.biz3slbappshouye.view.SLB1CategoryView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SLB1CategoryPresenter extends Presenter<SLB1CategoryView> {

    // 分类列表
    public void getLB1CategoryData() {
//        JSONObject requestData = new JSONObject();
//        requestData.put("page", page);
//        requestData.put("limit", limit);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_index_tingshucategoryList(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).enqueue(new Callback<ResponseSlbBean<SLB1CategoryBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SLB1CategoryBean>> call, Response<ResponseSlbBean<SLB1CategoryBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnLB1CategoryNodata(response.body().getMsg());
                    return;
                }
                getView().OnLB1CategorySuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SLB1CategoryBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnLB1CategoryFail(string);
                call.cancel();
            }
        });

    }
}
