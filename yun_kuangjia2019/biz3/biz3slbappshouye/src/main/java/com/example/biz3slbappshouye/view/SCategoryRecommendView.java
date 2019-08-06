package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SCategoryBean1;
import com.example.biz3slbappshouye.bean.SCategoryRecommendBean;
import com.haier.cellarette.libmvp.mvp.IView;

import java.util.List;

public interface SCategoryRecommendView extends IView {

    void OnCategoryRecommendSuccess(SCategoryRecommendBean bean, String TAG);
    void OnCategoryRecommendNodata(String bean);
    void OnCategoryRecommendFail(String msg);

}
