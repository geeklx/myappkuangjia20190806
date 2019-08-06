package com.example.biz3slbappdemo1.view;

import com.example.biz3slbappdemo1.bean.SlbMoreBooksBean;
import com.haier.cellarette.libmvp.mvp.IView;

import java.util.List;

public interface SlbMoreBooksView extends IView {

    void OnMoreBooksSuccess(SlbMoreBooksBean bean);

    void OnMoreBooksNodata(String bean);

    void OnMoreBooksFail(String msg);

}
