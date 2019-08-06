package com.example.biz3slbappusercenter.view;

import com.haier.cellarette.libmvp.mvp.IView;

public interface SSaveFavoritesView extends IView {

    void OnSaveFavoritesSuccess(String bean);
    void OnSaveFavoritesNodata(String bean);
    void OnSaveFavoritesFail(String msg);

}
