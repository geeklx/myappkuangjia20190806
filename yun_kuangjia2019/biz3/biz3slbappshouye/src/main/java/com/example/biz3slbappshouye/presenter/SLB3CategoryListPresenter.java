package com.example.biz3slbappshouye.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappshouye.api.SIndexApi;
import com.example.biz3slbappshouye.bean.SLB3CategoryListBean;
import com.example.biz3slbappshouye.view.SLB3CategoryListView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libretrofit.utils.BanbenUtils;
import com.haier.cellarette.libretrofit.utils.ResponseSlbBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SLB3CategoryListPresenter extends Presenter<SLB3CategoryListView> {

    public static String TAG = SLB3CategoryListPresenter.class.getSimpleName();

    // 分类列表3
    public void getLB3CategoryListData(int page, int limit, String pId, String itemId) {
        JSONObject requestData = new JSONObject();
        requestData.put("page", page);
        requestData.put("limit", limit);
        requestData.put("pId", pId);
        requestData.put("itemId", itemId);
//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(SIndexApi.class, getIdentifier()).get_index_gxjd3(BanbenUtils.getInstance().getVersion(), BanbenUtils.getInstance().getImei(), BanbenUtils.getInstance().getToken(), requestBody).enqueue(new Callback<ResponseSlbBean<SLB3CategoryListBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SLB3CategoryListBean>> call, Response<ResponseSlbBean<SLB3CategoryListBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnLB3CategoryListNodata(response.body().getMsg());
                    return;
                }
                getView().OnLB3CategoryListSuccess(response.body().getData(), TAG);
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SLB3CategoryListBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
//                String string = t.toString();
                String string = BanbenUtils.getInstance().net_tips;
                getView().OnLB3CategoryListFail(string);
                call.cancel();
            }
        });

    }
}
