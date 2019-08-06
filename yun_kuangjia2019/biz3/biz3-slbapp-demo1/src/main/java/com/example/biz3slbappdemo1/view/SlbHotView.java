package com.example.biz3slbappdemo1.view;

import com.example.biz3slbappdemo1.bean.SlbHotBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SlbHotView extends IView {

    void OnHotSuccess(SlbHotBean bean);

    void OnHotNodata(String bean);

    void OnHotFail(String msg);

}
