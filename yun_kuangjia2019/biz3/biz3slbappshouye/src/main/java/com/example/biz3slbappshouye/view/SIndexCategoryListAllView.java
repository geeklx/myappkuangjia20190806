package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SCategoryBean;
import com.example.biz3slbappshouye.bean.SIndexCategoryRecommendBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SIndexCategoryListAllView extends IView {

    void OnIndexCategoryListSuccess(SCategoryBean bean);
    void OnIndexCategoryListNodata(String bean);
    void OnIndexCategoryListFail(String msg);

}
