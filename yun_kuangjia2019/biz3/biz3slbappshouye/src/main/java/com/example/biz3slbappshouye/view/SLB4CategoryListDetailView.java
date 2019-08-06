package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SLB4CategoryListDetailBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SLB4CategoryListDetailView extends IView {

    void OnLB4CategoryListDetailSuccess(SLB4CategoryListDetailBean bean);
    void OnLB4CategoryListDetailNodata(String bean);
    void OnLB4CategoryListDetailFail(String msg);

}
