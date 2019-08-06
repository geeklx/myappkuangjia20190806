package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SCategoryBean;
import com.example.biz3slbappshouye.bean.SCategoryBean1;
import com.example.biz3slbappshouye.bean.SListCommBean;
import com.haier.cellarette.libmvp.mvp.IView;

import java.util.List;

public interface SCategoryView extends IView {

    void OnCategorySuccess(SCategoryBean bean, String TAG);
    void OnCategoryNodata(String bean);
    void OnCategoryFail(String msg);

}
