package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SWelcomeSettingBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SWelcomeSettingView extends IView {

    void OnWelcomeSettingSuccess(SWelcomeSettingBean bean);
    void OnWelcomeSettingNodata(String bean);
    void OnWelcomeSettingFail(String msg);

}
