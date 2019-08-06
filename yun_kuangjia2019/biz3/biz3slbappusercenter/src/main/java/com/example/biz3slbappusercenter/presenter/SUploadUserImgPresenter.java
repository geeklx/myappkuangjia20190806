package com.example.biz3slbappusercenter.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.bean.SUserImgBean;
import com.example.biz3slbappusercenter.view.SUploadUserImgView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SUploadUserImgPresenter extends Presenter<SUploadUserImgView> {

    public static String TAG = SUploadUserImgPresenter.class.getSimpleName();

    // file方式上传bufen
    public void getUploadUserImgData(File imgfile) {
//        JSONObject requestData = new JSONObject();
//        requestData.put("nickName", "");
//        RequestBody requestBody1 = RequestBody.create(MediaType.parse("text/plain"), requestData.toString());
        MultipartBody.Part requestBody = MultipartBody.Part.createFormData("file", imgfile.getName(), RequestBody.create(MediaType.parse("image/*"), imgfile));
        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_uploadimg(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(),  requestBody).enqueue(new Callback<ResponseSlbBean<SUserImgBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SUserImgBean>> call, Response<ResponseSlbBean<SUserImgBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnUploadUserImgNodata(response.body().getMsg());
                    return;
                }
                getView().OnUploadUserImgSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SUserImgBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnUploadUserImgFail(string);
                call.cancel();
            }
        });

    }

    // Base64上传bufen
    public void getUploadUserImgData2(String base64) {
        JSONObject requestData = new JSONObject();
        requestData.put("base64", base64);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());
        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_uploadimg2(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SUserImgBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SUserImgBean>> call, Response<ResponseSlbBean<SUserImgBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnUploadUserImgNodata(response.body().getMsg());
                    return;
                }
                getView().OnUploadUserImgSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SUserImgBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnUploadUserImgFail(string);
                call.cancel();
            }
        });

    }
}
