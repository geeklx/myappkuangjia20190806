package com.example.biz3slbappdemo1.view;

import com.example.biz3slbappdemo1.bean.SlbDetailBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SlbDetailView extends IView {

    void OnDetailSuccess(SlbDetailBean bean);

    void OnDetailNodata(String bean);

    void OnDetailFail(String msg);

}
