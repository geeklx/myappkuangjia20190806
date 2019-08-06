package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SCategoryBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SIndexCategoryListView extends IView {

    void OnIndexCategoryListSuccess(SCategoryBean bean);
    void OnIndexCategoryListNodata(String bean);
    void OnIndexCategoryListFail(String msg);

}
