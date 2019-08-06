package com.example.biz3slbappdemo1.view;

import com.example.biz3slbappdemo1.bean.SlbBannerBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SlbBannerView extends IView {

    void OnSuccess(SlbBannerBean bean, int categoryId, int homePage);

    void OnNodata(String bean);

    void OnFail(String msg);

}
