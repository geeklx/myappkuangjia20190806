package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SLoginUserInfoBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SBindWeChatLoginView extends IView {

    void OnBindWeChatLoginSuccess(SLoginUserInfoBean bean);
    void OnBindWeChatLoginNodata(String bean);
    void OnBindWeChatLoginFail(String msg);

}
