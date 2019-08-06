package com.example.biz3slbapporder.presenter;

import com.example.biz3slbapporder.api.SOrderApi;
import com.example.biz3slbapporder.bean.SVIPCardBean;
import com.example.biz3slbapporder.view.SVipCardView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SVipCardPresenter extends Presenter<SVipCardView> {

    public static String TAG = SVipCardPresenter.class.getSimpleName();

    public void getVipCardData() {
//        JSONObject requestData = new JSONObject();
//        requestData.put("bookId", bookId);
//        requestData.put("bookItemId", bookItemId);
//        requestData.put("consumeType", consumeType);
//        requestData.put("payMethod", payMethod);
//        requestData.put("priceId", priceId);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SOrderApi.class, getIdentifier()).get_my_vipcard(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).enqueue(new Callback<ResponseSlbBean<SVIPCardBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SVIPCardBean>> call, Response<ResponseSlbBean<SVIPCardBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnVipCardNodata(response.body().getMsg());
                    return;
                }
                getView().OnVipCardSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SVIPCardBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnVipCardFail(string);
                call.cancel();
            }
        });

    }

}
