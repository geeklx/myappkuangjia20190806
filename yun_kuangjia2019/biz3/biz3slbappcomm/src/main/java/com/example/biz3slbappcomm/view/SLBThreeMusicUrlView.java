package com.example.biz3slbappcomm.view;

import com.example.biz3slbappcomm.bean.SLBThreeMusicUrlBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SLBThreeMusicUrlView extends IView {

    void OnLBThreeMusicUrlSuccess(SLBThreeMusicUrlBean bean);

    void OnLBThreeMusicUrlNodata(String bean);

    void OnLBThreeMusicUrlFail(String msg);

}
