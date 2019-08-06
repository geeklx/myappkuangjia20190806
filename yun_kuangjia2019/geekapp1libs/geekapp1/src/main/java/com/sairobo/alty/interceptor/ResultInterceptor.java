package com.sairobo.alty.interceptor;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;

import com.example.shining.libglin.glin.Result;
import com.example.shining.libglin.glin.interceptor.IResultInterceptor;


public class ResultInterceptor implements IResultInterceptor {

    public static final int TOKEN_NULL = 9998;
    public static final int TOKEN_INVALID = 9999;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean intercept(Result<?> result) {
        if (!result.isOK()) {
            // check token
            Object obj = result.getObj();
            if (obj != null && TextUtils.isDigitsOnly(obj.toString())) {
                int code = Integer.parseInt(obj.toString());
                if (code == TOKEN_INVALID) {

                }
            }
            // check fridge_id  DataProvider.getFridge_id()
            if (obj != null && TextUtils.isEmpty("")) {
            }
        }

        return false;
    }

    protected String fridgeId() {
        return "";
    }

    protected String authToken() {
        return "";
    }
}
