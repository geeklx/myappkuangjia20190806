package com.example.biz3slbapporder.view;

import com.example.biz3slbapporder.bean.SVIPCardBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SVipCardView extends IView {
    void OnVipCardSuccess(SVIPCardBean bean);
    void OnVipCardNodata(String bean);
    void OnVipCardFail(String msg);

}
