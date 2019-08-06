package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SIndexCategoryRecommendBean;
import com.example.biz3slbappshouye.bean.SListenBookCategoryRecommendBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SIndexCategoryRecommendView extends IView {

    void OnIndexCategoryRecommendSuccess(SIndexCategoryRecommendBean bean);

    void OnIndexCategoryRecommendNodata(String bean);

    void OnIndexCategoryRecommendFail(String msg);

}
