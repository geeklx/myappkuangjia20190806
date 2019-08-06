package com.example.biz3slbappshouye.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SLB1HistoryBean;
import com.example.biz3slbappshouye.view.SLB1HistoryView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SLB1HistoryPresenter extends Presenter<SLB1HistoryView> {

    public static String TAG = SLB1HistoryPresenter.class.getSimpleName();

    public void getLB1HistoryData(int page, int limit) {
        JSONObject requestData = new JSONObject();
        requestData.put("page", page);
        requestData.put("limit", limit);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_index_history_new1(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SLB1HistoryBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SLB1HistoryBean>> call, Response<ResponseSlbBean<SLB1HistoryBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnLB1HistoryNodata(response.body().getMsg());
                    return;
                }
                getView().OnLB1HistorySuccess(response.body().getData(), TAG);
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SLB1HistoryBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnLB1HistoryFail(string);
                call.cancel();
            }
        });

    }
}
