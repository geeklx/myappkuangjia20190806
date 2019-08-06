package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SLBBannerBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SLBBannerView extends IView {

    void OnLBBannerSuccess(SLBBannerBean bean);
    void OnLBBannerNodata(String bean);
    void OnLBBannerFail(String msg);

}
