package com.example.slbappindex.fragment.fragment3;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.TabLayout;
//import androidx.core.app.Fragment;
//import androidx.core.app.FragmentManager;
//import androidx.core.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.slbappcomm.base.SlbBaseFragment;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment2.comm.Fragment2Factory;
import com.google.android.material.tabs.TabLayout;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.tablayout.TabSelectAdapter;
import com.haier.cellarette.baselibrary.tablayout.TabUtils;
import com.haier.cellarette.baselibrary.tablayout.ViewPagerSlide;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

import java.util.ArrayList;

public class FragmentContent3 extends SlbBaseFragment {

    private TextView tvCenter;
    private TextView tvRight;
    private TabLayout tablayoutMy;
    private String tab_id;
    private ViewPagerSlide mViewPager;

    private String tablayoutId;
    private boolean once;
    private String current_id = "";
    private String id1 = "1";//tablayout
    private String id2 = "2";

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            tablayoutId = getArguments().getString("tablayoutId");
        }
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        tvCenter = rootView.findViewById(R.id.tv_center);
        tvRight = rootView.findViewById(R.id.tv_right);
        tablayoutMy = rootView.findViewById(R.id.tablayout_my3);
        mViewPager = rootView.findViewById(R.id.viewpager_my3);
        tvCenter.setText("手机影像");
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.normal(BaseApp.get(), "编辑全选").show();

            }
        });
        initData();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment3;
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {
        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();
        ArrayList<String> titleDatas = new ArrayList<>();
        titleDatas.add("体验券");
        titleDatas.add("优惠券");
        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new FragmentContent31());
        fragmentList.add(new FragmentContent32());
        MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(getActivity().getSupportFragmentManager(), titleDatas, fragmentList);
        mViewPager.setScroll(true);
        mViewPager.setOffscreenPageLimit(Fragment2Factory.PAGE_COUNT);
        mViewPager.setAdapter(myViewPageAdapter);
        tablayoutMy.setupWithViewPager(mViewPager);
        tablayoutMy.addOnTabSelectedListener(new TabSelectAdapter() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TabUtils.tabSelect(tablayoutMy, tab);
                String tag = (String) tab.getTag();

            }
        });
        TabUtils.setIndicatorWidth(tablayoutMy, 60);
    }

    //手动设置ViewPager要显示的视图
    public void changeView(int desTab) {
        mViewPager.setCurrentItem(desTab, true);
    }


    public class MyViewPageAdapter extends FragmentPagerAdapter {
        private ArrayList<String> titleList;
        private ArrayList<Fragment> fragmentList;

        public MyViewPageAdapter(FragmentManager fm, ArrayList<String> titleList, ArrayList<Fragment> fragmentList) {
            super(fm);
            this.titleList = titleList;
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
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
        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();
        //推送入口bufen
        Intent intent = new Intent("hs.act.slbapp.JpushActivity");
//        intent.putExtra("type", Platform.ACTION_SHARE);//授权
        startActivity(intent);

    }

    /**
     * 当切换底部的时候通知每个fragment切换的id是哪个bufen
     *
     * @param cateId
     */
    public void give_id(String cateId) {
//        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    /**
     * --------------------------------业务逻辑bufen-------------------------------
     */


}
