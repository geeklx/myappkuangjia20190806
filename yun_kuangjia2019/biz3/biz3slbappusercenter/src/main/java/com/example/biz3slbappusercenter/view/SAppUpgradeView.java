package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SAppUpgradeBean;
import com.example.biz3slbappusercenter.bean.SAppUpgradeBean1;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SAppUpgradeView extends IView {

    void OnAppUpgradeSuccess(SAppUpgradeBean bean);
    void OnAppUpgradeNodata(String bean);
    void OnAppUpgradeFail(String msg);

}
