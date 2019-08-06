package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SSearchBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SSearchView extends IView {

    void OnSearchSuccess(SSearchBean bean, int which);
    void OnSearchNodata(String bean, int which);
    void OnSearchFail(String msg, int which);

}
