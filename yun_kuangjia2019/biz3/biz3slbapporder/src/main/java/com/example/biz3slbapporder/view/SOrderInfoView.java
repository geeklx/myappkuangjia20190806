package com.example.biz3slbapporder.view;

import com.example.biz3slbapporder.bean.SOrderInfoBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SOrderInfoView extends IView {
    void OnOrderInfoSuccess(SOrderInfoBean bean);
    void OnOrderInfoNodata(String bean);
    void OnOrderInfoFail(String msg);

}
