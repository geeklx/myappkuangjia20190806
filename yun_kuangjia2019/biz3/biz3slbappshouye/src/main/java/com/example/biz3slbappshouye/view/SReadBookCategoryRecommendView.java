package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SReadBookCategoryRecommendBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SReadBookCategoryRecommendView extends IView {

    void OnReadBookCategoryRecommendSuccess(SReadBookCategoryRecommendBean bean);
    void OnReadBookCategoryRecommendNodata(String bean);
    void OnReadBookCategoryRecommendFail(String msg);

}
