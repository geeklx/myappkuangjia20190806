package com.example.biz3slbappcomm.view;

import com.example.biz3slbappcomm.bean.SMyMedalCoolViewBean;
import com.example.biz3slbappcomm.bean.SMyMedalDetailBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SMyMedalDetailView extends IView {

    void OnMyMedalDetailSuccess(SMyMedalDetailBean bean);
    void OnMyMedalDetailNodata(String bean);
    void OnMyMedalDetailFail(String msg);

}
