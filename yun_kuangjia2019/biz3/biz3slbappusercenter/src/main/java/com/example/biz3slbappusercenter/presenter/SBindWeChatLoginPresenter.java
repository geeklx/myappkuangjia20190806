package com.example.biz3slbappusercenter.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.bean.SLoginUserInfoBean;
import com.example.biz3slbappusercenter.view.SBindWeChatLoginView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SBindWeChatLoginPresenter extends Presenter<SBindWeChatLoginView> {

    public void getBindWeChatLoginData(String openid, String unionid, String gender, String nickName, String avatar) {
        JSONObject requestData = new JSONObject();
        requestData.put("openid", openid);
        requestData.put("unionid", unionid);
        requestData.put("gender", gender);
        requestData.put("nickName", nickName);
        requestData.put("avatar", avatar);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_bind_wechat(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SLoginUserInfoBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SLoginUserInfoBean>> call, Response<ResponseSlbBean<SLoginUserInfoBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnBindWeChatLoginNodata(response.body().getMsg());
                    return;
                }
                getView().OnBindWeChatLoginSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SLoginUserInfoBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnBindWeChatLoginFail(string);
                call.cancel();
            }
        });

    }

}
