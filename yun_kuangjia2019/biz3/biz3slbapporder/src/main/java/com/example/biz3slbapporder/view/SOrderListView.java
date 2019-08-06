package com.example.biz3slbapporder.view;

import com.example.biz3slbapporder.bean.SOrderListBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SOrderListView extends IView {

    void OnOrderListSuccess(SOrderListBean bean);

    void OnOrderListNodata(String bean);

    void OnOrderListFail(String msg);

}
