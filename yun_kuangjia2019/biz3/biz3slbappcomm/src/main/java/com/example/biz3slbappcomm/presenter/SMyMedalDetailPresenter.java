package com.example.biz3slbappcomm.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappcomm.api.SCommonApi;
import com.example.biz3slbappcomm.bean.SMyMedalDetailBean;
import com.example.biz3slbappcomm.view.SMyMedalDetailView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SMyMedalDetailPresenter extends Presenter<SMyMedalDetailView> {

    public static String TAG = SMyMedalDetailPresenter.class.getSimpleName();

    public void getMyMedalDetailData(String medalId) {
        JSONObject requestData = new JSONObject();
        requestData.put("medalId", medalId);


//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SCommonApi.class, getIdentifier()).get_my_medal_detail1(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SMyMedalDetailBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SMyMedalDetailBean>> call, Response<ResponseSlbBean<SMyMedalDetailBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnMyMedalDetailNodata(response.body().getMsg());
                    return;
                }
                getView().OnMyMedalDetailSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SMyMedalDetailBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnMyMedalDetailFail(string);
                call.cancel();
            }
        });

    }
}
