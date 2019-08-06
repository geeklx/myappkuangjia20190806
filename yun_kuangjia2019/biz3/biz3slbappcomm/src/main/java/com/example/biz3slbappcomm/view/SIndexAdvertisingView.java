package com.example.biz3slbappcomm.view;

import com.example.biz3slbappcomm.bean.SIndexAdvertisingBean;
import com.example.biz3slbappcomm.bean.SLBThreeMusicUrlBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SIndexAdvertisingView extends IView {

    void OnIndexAdvertisingSuccess(SIndexAdvertisingBean bean);
    void OnIndexAdvertisingNodata(String bean);
    void OnIndexAdvertisingFail(String msg);

}
