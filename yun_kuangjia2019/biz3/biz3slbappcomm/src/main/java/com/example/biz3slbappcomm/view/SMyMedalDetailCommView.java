package com.example.biz3slbappcomm.view;

import com.example.biz3slbappcomm.bean.SMyMedalDetailBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SMyMedalDetailCommView extends IView {

    void OnMyMedalDetailCommSuccess(SMyMedalDetailBean bean);
    void OnMyMedalDetailCommNodata(String bean);
    void OnMyMedalDetailCommFail(String msg);

}
