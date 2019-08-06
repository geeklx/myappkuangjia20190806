package com.example.biz3slbappcomm.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappcomm.api.SCommonApi;
import com.example.biz3slbappcomm.bean.SPayBean;
import com.example.biz3slbappcomm.view.SPayView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SPayPresenter extends Presenter<SPayView> {

    public static String TAG = SPayPresenter.class.getSimpleName();

    /**
     * 支付
     *
     * @param pId         如果是绘本购买的消费类型，购买整册，传入bookId
     * @param itemId      如果是绘本购买的消费类型，购买单册，传入bookItemId
     * @param consumeType 消费类型，1会员订购，2绘本购买
     * @param payMethod   付款方式，alipay为支付宝，wechat为微信
     * @param priceId     如果是会员订购，需传价格列表中priceId
     */
    public void getPayData(String pId, String itemId, String consumeType, String payMethod, String priceId) {
        JSONObject requestData = new JSONObject();
        requestData.put("pId", pId);
        requestData.put("itemId", itemId);
        requestData.put("consumeType", consumeType);
        requestData.put("payMethod", payMethod);
        requestData.put("priceId", priceId);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SCommonApi.class, getIdentifier()).get_my_pay(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SPayBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SPayBean>> call, Response<ResponseSlbBean<SPayBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnPayNodata(response.body().getMsg());
                    return;
                }
                getView().OnPaySuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SPayBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnPayFail(string);
                call.cancel();
            }
        });

    }

}
