package com.example.biz3slbappshouye.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SLB4CategoryListDetailBean;
import com.example.biz3slbappshouye.view.SLB4CategoryListDetailView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SLB4CategoryListDetailPresenter extends Presenter<SLB4CategoryListDetailView> {

    public static String TAG = SLB4CategoryListDetailPresenter.class.getSimpleName();

    // 分类列表4 -> 听书播放页面
    public void getLB4CategoryListDetailData(String pId, String itemId) {
        JSONObject requestData = new JSONObject();
        requestData.put("pId", pId);
        requestData.put("itemId", itemId);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_my_listenbookdetail_new1(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SLB4CategoryListDetailBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SLB4CategoryListDetailBean>> call, Response<ResponseSlbBean<SLB4CategoryListDetailBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnLB4CategoryListDetailNodata(response.body().getMsg());
                    return;
                }
                getView().OnLB4CategoryListDetailSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SLB4CategoryListDetailBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnLB4CategoryListDetailFail(string);
                call.cancel();
            }
        });

    }
}
