package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SLoginUserInfoBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SLoginView extends IView {

    void OnLoginSuccess(SLoginUserInfoBean bean, String TAG1);
    void OnLoginNodata(String bean);
    void OnLoginFail(String msg);

}
