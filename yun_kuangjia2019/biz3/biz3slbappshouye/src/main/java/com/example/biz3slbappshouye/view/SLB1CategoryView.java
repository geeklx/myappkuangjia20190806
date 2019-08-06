package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SLB1CategoryBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SLB1CategoryView extends IView {

    void OnLB1CategorySuccess(SLB1CategoryBean bean);
    void OnLB1CategoryNodata(String bean);
    void OnLB1CategoryFail(String msg);

}
