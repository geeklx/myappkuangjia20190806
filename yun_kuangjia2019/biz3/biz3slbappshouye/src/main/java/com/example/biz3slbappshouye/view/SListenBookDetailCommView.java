package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SListenBookDetailBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SListenBookDetailCommView extends IView {

    void OnHuibenDetailComm1Success(SListenBookDetailBean bean);
    void OnHuibenDetailComm1Nodata(String bean);
    void OnHuibenDetailComm1Fail(String msg);

}
