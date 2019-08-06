package com.example.biz3slbappusercenter.view;

import com.example.biz3slbappusercenter.bean.SUserImgBean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface SUploadUserImgView extends IView {

    void OnUploadUserImgSuccess(SUserImgBean bean);

    void OnUploadUserImgNodata(String bean);

    void OnUploadUserImgFail(String msg);

}
