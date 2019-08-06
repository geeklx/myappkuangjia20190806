package com.example.biz3slbappshouye.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SPopRenwuBean;
import com.example.biz3slbappshouye.view.SPopRenwuView;
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

public class SPopRenwuPresenter extends Presenter<SPopRenwuView> {

    public static final String banben = NetConfig.versionNameConfig;

    public void getPopRenwuData(String bookItemId, String type) {
        JSONObject requestData = new JSONObject();
        requestData.put("bookItemId", bookItemId);
        requestData.put("type", type);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_my_poprenwu(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SPopRenwuBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SPopRenwuBean>> call, Response<ResponseSlbBean<SPopRenwuBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnPopRenwuNodata(response.body().getMsg());
                    return;
                }
                getView().OnPopRenwuSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SPopRenwuBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnPopRenwuFail(string);
                call.cancel();
            }
        });

    }
}
