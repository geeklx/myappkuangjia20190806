package com.example.biz3slbappusercenter.view;

import com.haier.cellarette.libmvp.mvp.IView;

public interface SSendVCodeView extends IView {

    void OnSendVCodeSuccess(String bean);
    void OnSendVCodeNodata(String bean);
    void OnSendVCodeFail(String msg);

}
