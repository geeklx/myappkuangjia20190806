package com.example.biz3slbappcomm.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappcomm.api.SCommonApi;
import com.example.biz3slbappcomm.bean.SMyMedalDetailShareBean;
import com.example.biz3slbappcomm.view.SMyMedalDetailShareView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SMyMedalDetailSharePresenter extends Presenter<SMyMedalDetailShareView> {

    public static String TAG = SMyMedalDetailSharePresenter.class.getSimpleName();

    public void getMyMedalDetailShareData(String medalId) {
        JSONObject requestData = new JSONObject();
        requestData.put("medalId", medalId);


//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SCommonApi.class, getIdentifier()).get_my_medal_share1(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SMyMedalDetailShareBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SMyMedalDetailShareBean>> call, Response<ResponseSlbBean<SMyMedalDetailShareBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnMyMedalDetailShareNodata(response.body().getMsg());
                    return;
                }
                getView().OnMyMedalDetailShareSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SMyMedalDetailShareBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnMyMedalDetailShareFail(string);
                call.cancel();
            }
        });

    }
}
