package com.example.biz3slbappusercenter.presenter;

import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.bean.SAppUserInfoBean;
import com.example.biz3slbappusercenter.view.SAppUserInfoView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SAppUserInfoPresenter extends Presenter<SAppUserInfoView> {

    public static String TAG = SAppUserInfoPresenter.class.getSimpleName();

    public void getAppUserInfoData() {
//        JSONObject requestData = new JSONObject();
//        requestData.put("contact", contact);
//        requestData.put("content", content);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_appuserinfo(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).enqueue(new Callback<ResponseSlbBean<SAppUserInfoBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SAppUserInfoBean>> call, Response<ResponseSlbBean<SAppUserInfoBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnAppUserInfoNodata(response.body().getMsg());
                    return;
                }
                getView().OnAppUserInfoSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SAppUserInfoBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnAppUserInfoFail(string);
                call.cancel();
            }
        });

    }
}
