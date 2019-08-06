package com.example.biz3slbappusercenter.presenter;

import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.bean.SConfigureBean;
import com.example.biz3slbappusercenter.view.SConfigureView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SConfigurePresenter extends Presenter<SConfigureView> {

    public void getConfigureData() {
        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_configure(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).enqueue(new Callback<ResponseSlbBean<SConfigureBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SConfigureBean>> call, Response<ResponseSlbBean<SConfigureBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnConfigureNodata(response.body().getMsg());
                    return;
                }
                getView().OnConfigureSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SConfigureBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnConfigureFail(string);
                call.cancel();
            }
        });

    }
}
