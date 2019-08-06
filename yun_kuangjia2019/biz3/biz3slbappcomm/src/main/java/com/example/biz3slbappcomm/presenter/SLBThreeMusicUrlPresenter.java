package com.example.biz3slbappcomm.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappcomm.api.SCommonApi;
import com.example.biz3slbappcomm.bean.SLBThreeMusicUrlBean;
import com.example.biz3slbappcomm.view.SLBThreeMusicUrlView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;
import com.haier.cellarette.libvariants.NetConfig;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SLBThreeMusicUrlPresenter extends Presenter<SLBThreeMusicUrlView> {

    public static final String banben = NetConfig.versionNameConfig;

    public void getLBThreeMusicUrlData(String thirdItemId, String thirdPid, String source) {
        JSONObject requestData = new JSONObject();
        requestData.put("thirdItemId", thirdItemId);//
        requestData.put("thirdPid", thirdPid);//
        requestData.put("source", source);//

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SCommonApi.class, getIdentifier()).get_my_listenbookdetail_new1_threemusicurl(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SLBThreeMusicUrlBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SLBThreeMusicUrlBean>> call, Response<ResponseSlbBean<SLBThreeMusicUrlBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnLBThreeMusicUrlNodata(response.body().getMsg());
                    return;
                }
                getView().OnLBThreeMusicUrlSuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SLBThreeMusicUrlBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnLBThreeMusicUrlFail(string);
                call.cancel();
            }
        });

    }

}
