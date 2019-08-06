package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SNew1SearchBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SNew1SearchView extends IView {

    void OnNew1SearchSuccess(SNew1SearchBean bean, int which);
    void OnNew1SearchNodata(String bean, int which);
    void OnNew1SearchFail(String msg, int which);

}
