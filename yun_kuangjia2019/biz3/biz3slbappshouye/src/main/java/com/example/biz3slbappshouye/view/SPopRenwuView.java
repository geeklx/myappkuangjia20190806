package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SCategoryBean;
import com.example.biz3slbappshouye.bean.SPopRenwuBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SPopRenwuView extends IView {

    void OnPopRenwuSuccess(SPopRenwuBean bean);
    void OnPopRenwuNodata(String bean);
    void OnPopRenwuFail(String msg);

}
