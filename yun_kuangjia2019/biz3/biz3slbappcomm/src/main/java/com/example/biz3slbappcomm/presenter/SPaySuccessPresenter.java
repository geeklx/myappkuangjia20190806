package com.example.biz3slbappcomm.presenter;

import com.example.biz3slbappcomm.api.SCommonApi;
import com.example.biz3slbappcomm.bean.SPaySuccessBean;
import com.example.biz3slbappcomm.view.SPaySuccessView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SPaySuccessPresenter extends Presenter<SPaySuccessView> {

    public static String TAG = SPaySuccessPresenter.class.getSimpleName();

    public void getPaySuccessData() {
//        JSONObject requestData = new JSONObject();
//        requestData.put("bookId", bookId);
//        requestData.put("bookItemId", bookItemId);
//        requestData.put("consumeType", consumeType);
//        requestData.put("payMethod", payMethod);
//        requestData.put("priceId", priceId);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SCommonApi.class, getIdentifier()).get_my_paysuccess(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).enqueue(new Callback<ResponseSlbBean<SPaySuccessBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SPaySuccessBean>> call, Response<ResponseSlbBean<SPaySuccessBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnPaySuccessNodata(response.body().getMsg());
                    return;
                }
                getView().OnPaySuccessSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SPaySuccessBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnPaySuccessFail(string);
                call.cancel();
            }
        });

    }

}
