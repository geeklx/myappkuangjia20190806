package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SHuibenDetailListBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SHuibenDetailListCommView extends IView {

    void OnListComm1Success(SHuibenDetailListBean bean, String TAG);

    void OnListComm1Nodata(String bean);

    void OnListComm1Fail(String msg);

}
