package com.example.biz3slbappusercenter.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.bean.SLoginUserInfoBean;
import com.example.biz3slbappusercenter.view.SLoginView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SLoginPresenter extends Presenter<SLoginView> {

    public static String TAG = SLoginPresenter.class.getSimpleName();
    public static String TAG1 = "login_useful";
    public static String TAG2 = "login_wechat";

    public void getLoginData(String phone, String tempKey, String vcode) {
        JSONObject requestData = new JSONObject();
        requestData.put("phone", phone);
        requestData.put("tempKey", tempKey);
        requestData.put("vcode", vcode);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_login(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SLoginUserInfoBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SLoginUserInfoBean>> call, Response<ResponseSlbBean<SLoginUserInfoBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnLoginNodata(response.body().getMsg());
                    return;
                }
                getView().OnLoginSuccess(response.body().getData(), TAG1);
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SLoginUserInfoBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnLoginFail(string);
                call.cancel();
            }
        });

    }

    // 微信登录bufen
    public void getWeChatLoginData(String openid, String unionid, String gender, String nickName, String avatar) {

        JSONObject requestData = new JSONObject();
        requestData.put("openid", openid);
        requestData.put("unionid", unionid);
        requestData.put("gender", gender);
        requestData.put("nickName", nickName);
        requestData.put("avatar", avatar);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_login_wechat(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SLoginUserInfoBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SLoginUserInfoBean>> call, Response<ResponseSlbBean<SLoginUserInfoBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnLoginNodata(response.body().getMsg());
                    return;
                }
                getView().OnLoginSuccess(response.body().getData(), TAG2);
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SLoginUserInfoBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnLoginFail(string);
                call.cancel();
            }
        });

    }

}
