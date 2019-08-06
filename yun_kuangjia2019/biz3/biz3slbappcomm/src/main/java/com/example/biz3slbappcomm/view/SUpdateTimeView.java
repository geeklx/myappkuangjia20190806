package com.example.biz3slbappcomm.view;

import com.haier.cellarette.libmvp.mvp.IView;

public interface SUpdateTimeView extends IView {

    void OnUpdateTimeSuccess(String bean);
    void OnUpdateTimeNodata(String bean);
    void OnUpdateTimeFail(String msg);

}
