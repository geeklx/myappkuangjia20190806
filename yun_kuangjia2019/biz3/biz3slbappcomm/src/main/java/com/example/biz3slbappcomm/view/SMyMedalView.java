package com.example.biz3slbappcomm.view;

import com.example.biz3slbappcomm.bean.SMyMedalCoolViewBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SMyMedalView extends IView {

    void OnMyMedalSuccess(SMyMedalCoolViewBean bean);
    void OnMyMedalNodata(String bean);
    void OnMyMedalFail(String msg);

}
