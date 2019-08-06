package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SListenBookCategoryRecommendBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SListenBookCategoryRecommendView extends IView {

    void OnListenBookCategoryRecommendSuccess(SListenBookCategoryRecommendBean bean);
    void OnListenBookCategoryRecommendNodata(String bean);
    void OnListenBookCategoryRecommendFail(String msg);

}
