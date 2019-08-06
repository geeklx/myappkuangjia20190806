package com.example.biz3slbappshouye.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SBannerBean;
import com.example.biz3slbappshouye.view.SBannerView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;
import com.haier.cellarette.libvariants.NetConfig;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SBannerPresenter extends Presenter<SBannerView> {

    public static final String banben = NetConfig.versionNameConfig;

    public void getBannerData(final int categoryId, final int bannerType) {
        JSONObject requestData = new JSONObject();
        requestData.put("categoryId", categoryId);
        requestData.put("bannerType", bannerType);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_index_lunbo(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SBannerBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SBannerBean>> call, Response<ResponseSlbBean<SBannerBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnNodata(response.body().getMsg());
                    return;
                }
                getView().OnSuccess(response.body().getData(), categoryId, bannerType);
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SBannerBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnFail(string);
                call.cancel();
            }
        });

    }

}
