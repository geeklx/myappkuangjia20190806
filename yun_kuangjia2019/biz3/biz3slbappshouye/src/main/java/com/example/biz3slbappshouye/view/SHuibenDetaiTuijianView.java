package com.example.biz3slbappshouye.view;

import com.example.biz3slbappshouye.bean.SHuibenDetailTuijianBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SHuibenDetaiTuijianView extends IView {

    void OnHuibenDetailTuijianSuccess(SHuibenDetailTuijianBean bean);
    void OnHuibenDetailTuijianNodata(String bean);
    void OnHuibenDetailTuijianFail(String msg);

}
