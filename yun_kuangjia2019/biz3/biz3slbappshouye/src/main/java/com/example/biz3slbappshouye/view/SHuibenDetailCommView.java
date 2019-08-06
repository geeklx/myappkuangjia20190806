package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SHuibenDetailBean;
import com.example.biz3slbappshouye.bean.SHuibenDetailListBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SHuibenDetailCommView extends IView {

    void OnHuibenDetailComm1Success(SHuibenDetailBean bean);
    void OnHuibenDetailComm1Nodata(String bean);
    void OnHuibenDetailComm1Fail(String msg);

}
