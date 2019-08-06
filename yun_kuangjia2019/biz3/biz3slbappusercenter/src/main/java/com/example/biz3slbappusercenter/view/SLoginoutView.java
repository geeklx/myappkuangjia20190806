package com.example.biz3slbappusercenter.view;

import com.haier.cellarette.libmvp.mvp.IView;

public interface SLoginoutView extends IView {

    void OnLoginoutSuccess(String bean);
    void OnLoginoutNodata(String bean);
    void OnLoginoutFail(String msg);

}
