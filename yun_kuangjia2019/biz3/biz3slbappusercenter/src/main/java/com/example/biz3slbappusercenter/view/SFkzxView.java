package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SFkzxBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SFkzxView extends IView {

    void OnFkzxSuccess(SFkzxBean bean);
    void OnFkzxNodata(String bean);
    void OnFkzxFail(String msg);

}
