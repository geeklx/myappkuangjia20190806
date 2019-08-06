package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SAppUserInfoBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SAppUserInfoView extends IView {

    void OnAppUserInfoSuccess(SAppUserInfoBean bean);
    void OnAppUserInfoNodata(String bean);
    void OnAppUserInfoFail(String msg);

}
