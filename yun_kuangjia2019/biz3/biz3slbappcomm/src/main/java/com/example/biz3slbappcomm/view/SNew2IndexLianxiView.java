package com.example.biz3slbappcomm.view;

import com.example.biz3slbappcomm.bean.SNew2IndexLianxiBean;
import com.example.biz3slbappcomm.bean.SPayBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SNew2IndexLianxiView extends IView {

    void OnNew2IndexLianxiSuccess(SNew2IndexLianxiBean bean);
    void OnNew2IndexLianxiNodata(String bean);
    void OnNew2IndexLianxiFail(String msg);

}
