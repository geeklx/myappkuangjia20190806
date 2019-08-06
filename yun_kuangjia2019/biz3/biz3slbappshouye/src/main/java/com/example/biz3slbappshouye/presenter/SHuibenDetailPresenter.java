package com.example.biz3slbappshouye.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SHuibenDetailBean;
import com.example.biz3slbappshouye.view.SHuibenDetailCommView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SHuibenDetailPresenter extends Presenter<SHuibenDetailCommView> {

    public static String TAG = SHuibenDetailPresenter.class.getSimpleName();

    public void getHuibenDetailData(String bookItemId) {
        JSONObject requestData = new JSONObject();
        requestData.put("bookItemId", bookItemId);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_my_huibendetail(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SHuibenDetailBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SHuibenDetailBean>> call, Response<ResponseSlbBean<SHuibenDetailBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnHuibenDetailComm1Nodata(response.body().getMsg());
                    return;
                }
                getView().OnHuibenDetailComm1Success(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SHuibenDetailBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnHuibenDetailComm1Fail(string);
                call.cancel();
            }
        });

    }
}
