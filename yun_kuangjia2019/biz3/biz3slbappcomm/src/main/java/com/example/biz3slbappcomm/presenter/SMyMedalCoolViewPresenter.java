package com.example.biz3slbappcomm.presenter;

import com.example.biz3slbappcomm.api.SCommonApi;
import com.example.biz3slbappcomm.bean.SMyMedalCoolViewBean;
import com.example.biz3slbappcomm.view.SMyMedalView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SMyMedalCoolViewPresenter extends Presenter<SMyMedalView> {

    public static String TAG = SMyMedalCoolViewPresenter.class.getSimpleName();

    public void getMyMedalCoolViewData() {
//        JSONObject requestData = new JSONObject();
//        requestData.put("itemId", itemId);


//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SCommonApi.class, getIdentifier()).get_my_medal_new1(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken()).enqueue(new Callback<ResponseSlbBean<SMyMedalCoolViewBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SMyMedalCoolViewBean>> call, Response<ResponseSlbBean<SMyMedalCoolViewBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnMyMedalNodata(response.body().getMsg());
                    return;
                }
                getView().OnMyMedalSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SMyMedalCoolViewBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnMyMedalFail(string);
                call.cancel();
            }
        });

    }
}
