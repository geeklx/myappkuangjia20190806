package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SMyBooksBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SMyBooksView extends IView {

    void OnMyBooksSuccess(SMyBooksBean bean, String TAG);

    void OnMyBooksNodata(String bean);

    void OnMyBooksFail(String msg);

}
