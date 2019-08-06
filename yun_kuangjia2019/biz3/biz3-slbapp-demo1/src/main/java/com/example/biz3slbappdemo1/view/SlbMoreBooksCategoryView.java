package com.example.biz3slbappdemo1.view;

import com.example.biz3slbappdemo1.bean.SlbMoreBooksCategoryBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SlbMoreBooksCategoryView extends IView {

    void OnMoreBooksCategorySuccess(SlbMoreBooksCategoryBean bean);

    void OnMoreBooksCategoryNodata(String bean);

    void OnMoreBooksCategoryFail(String msg);

}
