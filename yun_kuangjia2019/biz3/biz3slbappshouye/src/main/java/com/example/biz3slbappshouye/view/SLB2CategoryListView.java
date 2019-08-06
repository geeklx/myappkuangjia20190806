package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SLB2CategoryListBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SLB2CategoryListView extends IView {

    void OnLB2CategoryListSuccess(SLB2CategoryListBean bean, String TAG);
    void OnLB2CategoryListNodata(String bean);
    void OnLB2CategoryListFail(String msg);

}
