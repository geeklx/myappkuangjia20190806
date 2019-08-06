package com.example.biz3slbappusercenter.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.bean.SLB1SaveFavoritesBean;
import com.example.biz3slbappusercenter.view.SLB1SaveFavoritesView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SLB1SaveFavoritesPresenter extends Presenter<SLB1SaveFavoritesView> {

    public static String TAG = SLB1SaveFavoritesPresenter.class.getSimpleName();

    public void getLB1SaveFavoritesData(String itemId, String pId, boolean collect, String sourceType) {
        JSONObject requestData = new JSONObject();
        requestData.put("itemId", itemId);
        requestData.put("pId", pId);
        requestData.put("collect", collect);
        requestData.put("sourceType", sourceType);// 绘本用bookItem，音频用audio

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_save_tingshu_favorites(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SLB1SaveFavoritesBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SLB1SaveFavoritesBean>> call, Response<ResponseSlbBean<SLB1SaveFavoritesBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnLB1SaveFavoritesNodata(response.body().getData());
                    return;
                }
                getView().OnLB1SaveFavoritesSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SLB1SaveFavoritesBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnLB1SaveFavoritesFail(string);
                call.cancel();
            }
        });

    }
}
