package com.example.biz3slbappusercenter.presenter;

import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.bean.SFkzxBean;
import com.example.biz3slbappusercenter.view.SFkzxView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SFkzxPresenter extends Presenter<SFkzxView> {

    public void getFkzxData() {
        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_fkzx_info(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).
                enqueue(new Callback<ResponseSlbBean<SFkzxBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SFkzxBean>> call, Response<ResponseSlbBean<SFkzxBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnFkzxNodata(response.body().getMsg());
                    return;
                }
                getView().OnFkzxSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SFkzxBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnFkzxFail(string);
                call.cancel();
            }
        });

    }
}
