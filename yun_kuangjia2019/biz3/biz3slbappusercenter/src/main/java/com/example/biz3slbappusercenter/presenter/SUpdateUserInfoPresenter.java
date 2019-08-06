package com.example.biz3slbappusercenter.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.view.SUpdateUserInfoView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SUpdateUserInfoPresenter extends Presenter<SUpdateUserInfoView> {

    public static String TAG = SUpdateUserInfoPresenter.class.getSimpleName();

    public void getUpdateUserInfoData2(String birthDate, String gender, String nickName) {
        JSONObject requestData = new JSONObject();
//        requestData.put("avatar", avatar);
        requestData.put("birthDate", birthDate);
        requestData.put("gender", gender);
        requestData.put("nickName", nickName);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_uploadinfo2(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<Object>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<Object>> call, Response<ResponseSlbBean<Object>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnUpdateUserInfoNodata(response.body().getMsg());
                    return;
                }
                getView().OnUpdateUserInfoSuccess(response.body().getMsg());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<Object>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnUpdateUserInfoFail(string);
                call.cancel();
            }
        });

    }

//    public void getUpdateUserInfoData(String imei, String token, File avatar, String birthDate, String gender, String nickName) {
//        JSONObject requestData = new JSONObject();
//        requestData.put("avatar", avatar);
//        requestData.put("birthDate", birthDate);
//        requestData.put("gender", gender);
//        requestData.put("nickName", nickName);
//        RequestBody requestBody1 = RequestBody.create(MediaType.parse("text/plain"), requestData.toString());
//        MultipartBody.Part requestBody2 = MultipartBody.Part.createFormData("img_info", avatar.getName(), RequestBody.create(MediaType.parse("image/*"), avatar));
//
//        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_uploadinfo(imei, token, requestBody1, requestBody2).enqueue(new Callback<ResponseSlbBean<Object>>() {
//            @Override
//            public void onResponse(Call<ResponseSlbBean<Object>> call, Response<ResponseSlbBean<Object>> response) {
//                if (!hasView()) {
//                    return;
//                }
//                if (response.body() == null) {
//                    return;
//                }
//                if (response.body().getCode() != 0) {
//                    getView().OnUpdateUserInfoNodata(response.body().getMsg());
//                    return;
//                }
//                getView().OnUpdateUserInfoSuccess(response.body().getMsg());
//                call.cancel();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseSlbBean<Object>> call, Throwable t) {
//                if (!hasView()) {
//                    return;
//                }
//                String string = t.toString();
//                getView().OnUpdateUserInfoFail(string);
//                call.cancel();
//            }
//        });
//
//    }
}
