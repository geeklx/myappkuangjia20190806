package com.example.biz3slbappusercenter.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.bean.SNew1SearchBean;
import com.example.biz3slbappusercenter.view.SNew1SearchView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SNew1SearchPresenter extends Presenter<SNew1SearchView> {

    public static String TAG = SNew1SearchPresenter.class.getSimpleName();

    public void getNew1SearchData(String keyword, String limit, final int which) {
        JSONObject requestData = new JSONObject();
        requestData.put("keyword", keyword);
        requestData.put("limit", limit);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_search_new1(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SNew1SearchBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SNew1SearchBean>> call, Response<ResponseSlbBean<SNew1SearchBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnNew1SearchNodata(response.body().getMsg(), which);
                    return;
                }
                getView().OnNew1SearchSuccess(response.body().getData(), which);
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SNew1SearchBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnNew1SearchFail(string, which);
                call.cancel();
            }
        });

    }
}
