package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SLB1HistoryBean;
import com.example.biz3slbappshouye.bean.SListCommBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SLB1HistoryView extends IView {

    void OnLB1HistorySuccess(SLB1HistoryBean bean, String TAG);
    void OnLB1HistoryNodata(String bean);
    void OnLB1HistoryFail(String msg);

}
