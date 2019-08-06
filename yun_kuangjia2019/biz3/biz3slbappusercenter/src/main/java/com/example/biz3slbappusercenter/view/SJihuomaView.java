package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SJihuomaBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SJihuomaView extends IView {

    void OnJihuomaSuccess(SJihuomaBean msg);
    void OnJihuomaNodata(String msg);
    void OnJihuomaFail(String msg);

}
