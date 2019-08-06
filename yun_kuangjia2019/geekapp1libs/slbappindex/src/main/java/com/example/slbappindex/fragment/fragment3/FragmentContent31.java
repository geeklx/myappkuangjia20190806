package com.example.slbappindex.fragment.fragment3;

import android.content.Context;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.slbappcomm.base.SlbBaseFragment;
import com.example.slbappindex.R;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

public class FragmentContent31 extends SlbBaseFragment {

    private String tablayoutId;
    private Context mContext;
    private TextView tv1;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            tablayoutId = getArguments().getString("tablayoutId");
        }
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        tv1 = rootView.findViewById(R.id.tv1);
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment31;
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {

//        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();

    }


    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(String cateId, boolean isrefresh) {

        if (!isrefresh) {
            return;
        }
        Toasty.normal(getActivity(), "下拉刷新啦").show();

    }

}
