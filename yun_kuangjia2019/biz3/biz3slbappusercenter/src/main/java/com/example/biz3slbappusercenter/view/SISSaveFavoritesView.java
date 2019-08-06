package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SISSCBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SISSaveFavoritesView extends IView {

    void OnISSaveFavoritesSuccess(SISSCBean bean);
    void OnISSaveFavoritesNodata(String bean);
    void OnISSaveFavoritesFail(String msg);

}
