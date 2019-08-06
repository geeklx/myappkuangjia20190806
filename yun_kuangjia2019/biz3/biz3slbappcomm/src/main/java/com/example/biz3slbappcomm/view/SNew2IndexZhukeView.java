package com.example.biz3slbappcomm.view;

import com.example.biz3slbappcomm.bean.SNew2IndexZhukeBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SNew2IndexZhukeView extends IView {

    void OnNew2IndexZhukeSuccess(SNew2IndexZhukeBean bean);
    void OnNew2IndexZhukeNodata(String bean);
    void OnNew2IndexZhukeFail(String msg);

}
