package com.example.slbappxmly;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.category.CategoryList;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;

import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

public class Act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.Ext.init(getApplication());
        x.Ext.setDebug(true); // 是否输出debug日志, 开启debug会影响性能.
        CommonRequest mXimalaya = CommonRequest.getInstanse();
        String mAppSecret = "8646d66d6abe2efd14f2891f9fd1c8af";
        mXimalaya.setAppkey("9f9ef8f10bebeaa83e71e62f935bede8");
        mXimalaya.setPackid("com.app.test.android");
        mXimalaya.init(this, mAppSecret);
        // setcate1();
        // setcate2();
        setcate3();

    }

    private void setcate3() {
        // CommonRequest.getInstanse().setDefaultPagesize(int size)
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.ALBUM_ID, "4519297");
//        map.put(DTransferConstants.SORT, "asc");
        map.put(DTransferConstants.PAGE_SIZE, "20");
        CommonRequest.getTracks(map, new IDataCallBack<TrackList>() {

            @Override
            public void onSuccess(@Nullable TrackList trackList) {
                String aaa = "";
            }

            @Override
            public void onError(int i, String s) {
                String aaa = "";
            }
        });

    }

    private void setcate1() {
        CommonRequest.getCategories(null, new IDataCallBack<CategoryList>() {

            @Override
            public void onSuccess(@Nullable CategoryList categoryList) {
                String aaa = "";
                Toasty.normal(Act.this, "进来了~").show();
            }

            @Override
            public void onError(int i, String s) {
                String aaa = "";
                Toasty.normal(Act.this, "没进来了~").show();
            }
        });
    }

    private void setcate2() {
        //
        Map<String, String> param = new HashMap<String, String>();
        param.put(DTransferConstants.PAGE, "" + 1);
        param.put(DTransferConstants.PAGE_SIZE, "" + 100);
        param.put(DTransferConstants.ALBUM_ID, 239463 + "");
        CommonRequest.getTracks(param, new IDataCallBack<TrackList>() {
            @Override
            public void onSuccess(TrackList trackList) {
                if (trackList != null && trackList.getTracks() != null && trackList.getTracks().size() != 0) {
//                    mPageId++;
//
//                    if(mTrackHotList == null) {
//                        mTrackHotList = trackList;
//                    } else {
//                        trackList.getTracks().addAll(0 ,mTrackHotList.getTracks());
//                        mTrackHotList = trackList;
//                    }
//
//                    mTrackAdapter.notifyDataSetChanged();
                }
//                mLoading = false;
            }

            @Override
            public void onError(int i, String s) {
                String aaaa = "";
//                mLoading = false;
            }
        });
    }

}
