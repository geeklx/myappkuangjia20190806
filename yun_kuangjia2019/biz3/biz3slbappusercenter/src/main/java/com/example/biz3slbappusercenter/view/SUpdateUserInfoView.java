package com.example.biz3slbappusercenter.view;

import com.haier.cellarette.libmvp.mvp.IView;

public interface SUpdateUserInfoView extends IView {

    void OnUpdateUserInfoSuccess(String bean);
    void OnUpdateUserInfoNodata(String bean);
    void OnUpdateUserInfoFail(String msg);

}
