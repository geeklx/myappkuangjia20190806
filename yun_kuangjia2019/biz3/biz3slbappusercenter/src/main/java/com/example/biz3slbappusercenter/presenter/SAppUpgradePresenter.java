package com.example.biz3slbappusercenter.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.bean.SAppUpgradeBean;
import com.example.biz3slbappusercenter.view.SAppUpgradeView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SAppUpgradePresenter extends Presenter<SAppUpgradeView> {

    public static String TAG = SAppUpgradePresenter.class.getSimpleName();

    public void getAppUpgradeData(String appCode, String appName, String appMd5, String appPkgName) {
        JSONObject requestData = new JSONObject();
        requestData.put("appVersionCode", appCode);
        requestData.put("appName", appName);
        requestData.put("appMd5", appMd5);
        requestData.put("appPkgName", appPkgName);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_appupgrade(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SAppUpgradeBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SAppUpgradeBean>> call, Response<ResponseSlbBean<SAppUpgradeBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnAppUpgradeNodata(response.body().getMsg());
                    return;
                }
                getView().OnAppUpgradeSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SAppUpgradeBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnAppUpgradeFail(string);
                call.cancel();
            }
        });

    }
}
