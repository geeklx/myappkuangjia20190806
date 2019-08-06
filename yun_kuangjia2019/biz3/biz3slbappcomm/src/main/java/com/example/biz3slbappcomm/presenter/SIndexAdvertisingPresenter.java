package com.example.biz3slbappcomm.presenter;

import com.example.biz3slbappcomm.api.SCommonApi;
import com.example.biz3slbappcomm.bean.SIndexAdvertisingBean;
import com.example.biz3slbappcomm.view.SIndexAdvertisingView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SIndexAdvertisingPresenter extends Presenter<SIndexAdvertisingView> {

    public void getIndexAdvertisingData() {
//        JSONObject requestData = new JSONObject();
//        requestData.put("thirdItemId", thirdItemId);//
//        requestData.put("thirdPid", thirdPid);//
//        requestData.put("source", source);//

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SCommonApi.class, getIdentifier()).get_index_advertising(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).enqueue(new Callback<ResponseSlbBean<SIndexAdvertisingBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SIndexAdvertisingBean>> call, Response<ResponseSlbBean<SIndexAdvertisingBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnIndexAdvertisingNodata(response.body().getMsg());
                    return;
                }
                getView().OnIndexAdvertisingSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SIndexAdvertisingBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnIndexAdvertisingFail(string);
                call.cancel();
            }
        });

    }

}
