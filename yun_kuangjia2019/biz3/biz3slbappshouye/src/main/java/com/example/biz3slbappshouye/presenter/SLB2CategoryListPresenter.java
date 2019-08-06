package com.example.biz3slbappshouye.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SLB2CategoryListBean;
import com.example.biz3slbappshouye.view.SLB2CategoryListView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SLB2CategoryListPresenter extends Presenter<SLB2CategoryListView> {

    public static String TAG = SLB2CategoryListPresenter.class.getSimpleName();

    // 分类列表2
    public void getLB2CategoryListData(String ageRange, int page, int limit, String categoryId, String columnId, String type) {
        JSONObject requestData = new JSONObject();
        requestData.put("ageRange", ageRange);
        requestData.put("page", page);
        requestData.put("limit", limit);
        requestData.put("categoryId", categoryId);
        requestData.put("id", columnId);
        requestData.put("type", type);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_index_gxjd2(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SLB2CategoryListBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SLB2CategoryListBean>> call, Response<ResponseSlbBean<SLB2CategoryListBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnLB2CategoryListNodata(response.body().getMsg());
                    return;
                }
                getView().OnLB2CategoryListSuccess(response.body().getData(), TAG);
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SLB2CategoryListBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnLB2CategoryListFail(string);
                call.cancel();
            }
        });

    }
}
