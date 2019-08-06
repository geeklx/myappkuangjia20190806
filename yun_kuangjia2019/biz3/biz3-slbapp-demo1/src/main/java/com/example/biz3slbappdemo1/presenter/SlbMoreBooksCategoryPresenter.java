package com.example.biz3slbappdemo1.presenter;

import com.example.biz3slbappdemo1.api.SlbCategoryApi;
import com.example.biz3slbappdemo1.bean.ResponseSlbBean;
import com.example.biz3slbappdemo1.bean.SlbMoreBooksBean;
import com.example.biz3slbappdemo1.bean.SlbMoreBooksCategoryBean;
import com.example.biz3slbappdemo1.view.SlbMoreBooksCategoryView;
import com.example.biz3slbappdemo1.view.SlbMoreBooksView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlbMoreBooksCategoryPresenter extends Presenter<SlbMoreBooksCategoryView> {


    public void getSlbMoreBooksCategoryData() {
//        JSONObject requestData = new JSONObject();
//        requestData.put("bookColumn", bookColumn);

//        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
//        requestData.put("params", JSON.toJSON(params));
//        JSONObject requestData = (JSONObject) JSON.toJSON(model);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), "");

        RetrofitNetNew.build(SlbCategoryApi.class, getIdentifier()).get_index_more_books_category(requestBody).enqueue(new Callback<ResponseSlbBean<SlbMoreBooksCategoryBean>>() {
            @Override
            public void onResponse(Call<ResponseSlbBean<SlbMoreBooksCategoryBean>> call, Response<ResponseSlbBean<SlbMoreBooksCategoryBean>> response) {
                if (!hasView()) {
                    return;
                }
                if (response.body() == null) {
                    return;
                }
                if (response.body().getCode() != 0) {
                    getView().OnMoreBooksCategoryNodata(response.body().getMsg());
                    return;
                }
                getView().OnMoreBooksCategorySuccess(response.body().getData());
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseSlbBean<SlbMoreBooksCategoryBean>> call, Throwable t) {
                if (!hasView()) {
                    return;
                }
                String string = t.toString();
                getView().OnMoreBooksCategoryFail(string);
                call.cancel();
            }
        });

    }
}
