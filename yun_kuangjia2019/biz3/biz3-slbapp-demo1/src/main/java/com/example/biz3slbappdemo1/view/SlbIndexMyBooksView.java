package com.example.biz3slbappdemo1.view;

import com.example.biz3slbappdemo1.bean.SlbIndexMyBooksBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SlbIndexMyBooksView extends IView {

    void OnIndexMyBooksSuccess(SlbIndexMyBooksBean bean);

    void OnIndexMyBooksNodata(String bean);

    void OnIndexMyBooksFail(String msg);

}
