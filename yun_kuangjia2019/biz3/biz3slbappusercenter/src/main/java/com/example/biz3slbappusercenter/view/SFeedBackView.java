package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SAppUpgradeBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SFeedBackView extends IView {

    void OnFeedBackSuccess(String bean);
    void OnFeedBackNodata(String bean);
    void OnFeedBackFail(String msg);

}
