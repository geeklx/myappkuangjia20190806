package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SConfigureBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SConfigureView extends IView {

    void OnConfigureSuccess(SConfigureBean bean);
    void OnConfigureNodata(String bean);
    void OnConfigureFail(String msg);

}
