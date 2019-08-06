package com.example.biz3slbappusercenter.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappusercenter.api.SMyApi;
import com.example.biz3slbappusercenter.bean.SISSCBean;
import com.example.biz3slbappusercenter.view.SISSaveFavoritesView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SISSaveFavoritesPresenter extends Presenter<SISSaveFavoritesView> {

    public static String TAG = SISSaveFavoritesPresenter.class.getSimpleName();

    public void getISSaveFavoritesData(String bookId, String itemId, String sourceType) {
        JSONObject requestData = new JSONObject();
        requestData.put("pId", bookId);
        requestData.put("itemId", itemId);
        requestData.put("sourceType", sourceType);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SMyApi.class, getIdentifier()).get_my_is_savefavorites(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SISSCBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SISSCBean>> call, Response<ResponseSlbBean<SISSCBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnISSaveFavoritesNodata(response.body().getMsg());
                    return;
                }
                getView().OnISSaveFavoritesSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SISSCBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnISSaveFavoritesFail(string);
                call.cancel();
            }
        });

    }
}
