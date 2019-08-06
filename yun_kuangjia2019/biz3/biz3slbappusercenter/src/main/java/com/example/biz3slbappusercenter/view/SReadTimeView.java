package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SSearchBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SReadTimeView extends IView {

    void OnReadTimeSuccess(String bean);
    void OnReadTimeNodata(String bean);
    void OnReadTimeFail(String msg);

}
