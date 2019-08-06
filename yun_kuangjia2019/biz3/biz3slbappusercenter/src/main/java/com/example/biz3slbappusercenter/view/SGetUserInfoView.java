package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SLoginUserInfoBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SGetUserInfoView extends IView {

    void OnGetUserInfoSuccess(SLoginUserInfoBean bean);
    void OnGetUserInfoNodata(String bean);
    void OnGetUserInfoFail(String msg);

}
