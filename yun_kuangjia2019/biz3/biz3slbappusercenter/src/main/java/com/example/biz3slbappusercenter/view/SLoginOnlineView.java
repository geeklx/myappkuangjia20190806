package com.example.biz3slbappusercenter.view;

import com.haier.cellarette.libmvp.mvp.IView;

public interface SLoginOnlineView extends IView {

    void OnLoginOnlineSuccess(String msg);
    void OnLoginOnlineNodata(String msg);
    void OnLoginOnlineFail(String msg);

}
