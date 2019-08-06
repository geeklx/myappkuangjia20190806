package com.example.biz3slbappusercenter.presenter;

import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.bean.SLoginUserInfoBean;
import com.example.biz3slbappusercenter.view.SGetUserInfoView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;
import com.haier.cellarette.libvariants.NetConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SGetUserInfoPresenter extends Presenter<SGetUserInfoView> {

    public static String TAG = SGetUserInfoPresenter.class.getSimpleName();
    public static final String banben = NetConfig.versionNameConfig;

    public void getGetUserInfoData() {

        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_logininfo(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).enqueue(new Callback<ResponseSlbBean<SLoginUserInfoBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SLoginUserInfoBean>> call, Response<ResponseSlbBean<SLoginUserInfoBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnGetUserInfoNodata(response.body().getMsg());
                    return;
                }
                getView().OnGetUserInfoSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SLoginUserInfoBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnGetUserInfoFail(string);
                call.cancel();
            }
        });

    }
}
