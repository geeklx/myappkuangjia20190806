package com.example.biz3slbappcomm.view;

import com.example.biz3slbappcomm.bean.SPayBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SPayView extends IView {

    void OnPaySuccess(SPayBean bean);
    void OnPayNodata(String bean);
    void OnPayFail(String msg);

}
