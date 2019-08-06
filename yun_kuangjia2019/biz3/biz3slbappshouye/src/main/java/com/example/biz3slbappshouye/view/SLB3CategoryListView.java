package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SLB3CategoryListBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SLB3CategoryListView extends IView {

    void OnLB3CategoryListSuccess(SLB3CategoryListBean bean, String TAG);
    void OnLB3CategoryListNodata(String bean);
    void OnLB3CategoryListFail(String msg);

}
