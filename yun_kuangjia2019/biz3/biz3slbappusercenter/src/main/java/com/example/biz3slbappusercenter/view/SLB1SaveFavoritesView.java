package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SLB1SaveFavoritesBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SLB1SaveFavoritesView extends IView {

    void OnLB1SaveFavoritesSuccess(SLB1SaveFavoritesBean bean);
    void OnLB1SaveFavoritesNodata(SLB1SaveFavoritesBean bean);
    void OnLB1SaveFavoritesFail(String msg);

}
