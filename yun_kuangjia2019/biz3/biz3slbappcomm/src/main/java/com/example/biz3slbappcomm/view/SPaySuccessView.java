package com.example.biz3slbappcomm.view;

import com.example.biz3slbappcomm.bean.SPaySuccessBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SPaySuccessView extends IView {

    void OnPaySuccessSuccess(SPaySuccessBean bean);
    void OnPaySuccessNodata(String bean);
    void OnPaySuccessFail(String msg);

}
