package com.example.biz3slbappshouye.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SHuibenDetailListBean;
import com.example.biz3slbappshouye.view.SHuibenDetailListCommView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SHuibenDetailListPresenter extends Presenter<SHuibenDetailListCommView> {

    public static String TAG = SHuibenDetailListPresenter.class.getSimpleName();

    public void getHuibenDetailListData(String ageRange, String bookId, String categoryId, int page, int limit) {
        JSONObject requestData = new JSONObject();
        requestData.put("ageRange", ageRange);
        requestData.put("bookId", bookId);
        requestData.put("categoryId", categoryId);
        requestData.put("page", page);
        requestData.put("limit", limit);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_my_huibenlist(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SHuibenDetailListBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SHuibenDetailListBean>> call, Response<ResponseSlbBean<SHuibenDetailListBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnListComm1Nodata(response.body().getMsg());
                    return;
                }
                getView().OnListComm1Success(response.body().getData(), TAG);
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SHuibenDetailListBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnListComm1Fail(string);
                call.cancel();
            }
        });

    }
}
