package com.example.biz3slbapporder.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbapporder.api.SOrderApi;
import com.example.biz3slbapporder.bean.SOrderListBean;
import com.example.biz3slbapporder.view.SOrderListView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SOrderListPresenter extends Presenter<SOrderListView> {

    public static String TAG = SOrderListPresenter.class.getSimpleName();

    public void getOrderListData(int page, int limit) {
        JSONObject requestData = new JSONObject();
        requestData.put("page", page);
        requestData.put("limit", limit);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SOrderApi.class, getIdentifier()).get_my_orderlist(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SOrderListBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SOrderListBean>> call, Response<ResponseSlbBean<SOrderListBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnOrderListNodata(response.body().getMsg());
                    return;
                }
                getView().OnOrderListSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SOrderListBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnOrderListFail(string);
                call.cancel();
            }
        });

    }

}
