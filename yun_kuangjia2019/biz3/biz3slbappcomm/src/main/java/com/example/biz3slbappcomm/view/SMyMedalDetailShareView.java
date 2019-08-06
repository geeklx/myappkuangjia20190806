package com.example.biz3slbappcomm.view;

import com.example.biz3slbappcomm.bean.SMyMedalDetailShareBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SMyMedalDetailShareView extends IView {

    void OnMyMedalDetailShareSuccess(SMyMedalDetailShareBean bean);
    void OnMyMedalDetailShareNodata(String bean);
    void OnMyMedalDetailShareFail(String msg);

}
