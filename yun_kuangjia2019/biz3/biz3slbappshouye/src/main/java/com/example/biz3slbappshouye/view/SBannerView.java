package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SBannerBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SBannerView extends IView {

    void OnSuccess(SBannerBean bean, int categoryId, int homePage);

    void OnNodata(String bean);

    void OnFail(String msg);

}
