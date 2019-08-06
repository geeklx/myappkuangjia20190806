package com.example.biz3slbapporder.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbapporder.api.SOrderApi;
import com.example.biz3slbapporder.bean.SOrderInfoBean;
import com.example.biz3slbapporder.view.SOrderInfoView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SOrderInfoPresenter extends Presenter<SOrderInfoView> {

    public static String TAG = SOrderInfoPresenter.class.getSimpleName();

    public void getOrderInfoData(String pId, String itemId, String sourceType) {
        JSONObject requestData = new JSONObject();
        requestData.put("pId", pId);
        requestData.put("itemId", itemId);
        requestData.put("sourceType", sourceType);
//        requestData.put("consumeType", consumeType);
//        requestData.put("payMethod", payMethod);
//        requestData.put("priceId", priceId);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SOrderApi.class, getIdentifier()).get_my_ordersure(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(),requestBody).enqueue(new Callback<ResponseSlbBean<SOrderInfoBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SOrderInfoBean>> call, Response<ResponseSlbBean<SOrderInfoBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnOrderInfoNodata(response.body().getMsg());
                    return;
                }
                getView().OnOrderInfoSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SOrderInfoBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnOrderInfoFail(string);
                call.cancel();
            }
        });

    }

}
