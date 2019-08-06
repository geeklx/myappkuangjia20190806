package com.example.biz3slbappshouye.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SListCommBean;
import com.example.biz3slbappshouye.view.SListCommView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SHotPresenter extends Presenter<SListCommView> {

    public static String TAG = SHotPresenter.class.getSimpleName();

    public void getHotData(int page, int limit) {
        JSONObject requestData = new JSONObject();
        requestData.put("page", page);
        requestData.put("limit", limit);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_index_hot(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SListCommBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SListCommBean>> call, Response<ResponseSlbBean<SListCommBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnListCommNodata(response.body().getMsg());
                    return;
                }
                getView().OnListCommSuccess(response.body().getData(), TAG);
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SListCommBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnListCommFail(string);
                call.cancel();
            }
        });

    }
}
