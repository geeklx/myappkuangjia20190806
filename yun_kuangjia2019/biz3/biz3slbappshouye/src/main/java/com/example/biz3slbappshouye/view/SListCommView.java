package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SListCommBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SListCommView extends IView {

    void OnListCommSuccess(SListCommBean bean, String TAG);

    void OnListCommNodata(String bean);

    void OnListCommFail(String msg);

}
