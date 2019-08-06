package com.example.slbappreadbook.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappshouye.bean.SPopRenwuBean1;
import com.example.biz3slbappshouye.bean.SPopRenwuBean2;
import com.example.slbappreadbook.R;
import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;

import java.util.ArrayList;
import java.util.List;

// 读书任务弹窗
public class PopRenwuPay extends PopupWindow {

    private Activity activity;
    private View mMenuView;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private RecyclerView recyclerview1;
    private PopRenwuPayAdapter mAdapter;
    private List<PopRenwuPayBean> mList;
    private List<SPopRenwuBean2> mList1;

    private List<PopRenwuPayBean> getList() {
        List<PopRenwuPayBean> list1 = new ArrayList<>();
        list1.add(new PopRenwuPayBean("1", "", "10"));
        list1.add(new PopRenwuPayBean("2", "http://tvax2.sinaimg.cn/crop.0.0.495.495.180/b88d1dc4ly8fxed1g7od4j20dr0drdhq.jpg", "20"));
        list1.add(new PopRenwuPayBean("3", "", "30"));
        list1.add(new PopRenwuPayBean("4", "http://tvax2.sinaimg.cn/crop.0.0.495.495.180/b88d1dc4ly8fxed1g7od4j20dr0drdhq.jpg", "40"));
        list1.add(new PopRenwuPayBean("5", "http://tvax2.sinaimg.cn/crop.0.0.495.495.180/b88d1dc4ly8fxed1g7od4j20dr0drdhq.jpg", "50"));
        return list1;
    }

    public PopRenwuPay() {

    }

    public PopRenwuPay(final Activity activity, final SPopRenwuBean1 sPopRenwuBean, final OnFinishResultClickListener mListener) {
        super(activity);
//        AutoSizeConfig.getInstance().setCustomFragment(true);
//        AutoSize.autoConvertDensity((Activity) activity, 375, true);
        this.activity = activity;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_renwupay, null);
        tv1 = mMenuView.findViewById(R.id.tv1);
        tv2 = mMenuView.findViewById(R.id.tv2);
        tv3 = mMenuView.findViewById(R.id.tv3);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onResult(sPopRenwuBean.getJumpUrl());
                }
            }
        });
        //
//        mList = new ArrayList<>();
//        mList = getList();
        tv2.setText(sPopRenwuBean.getTitle());
        mList1 = new ArrayList<>();
        mList1 = sPopRenwuBean.getRewards();
        recyclerview1 = mMenuView.findViewById(R.id.recyclerview1);
        recyclerview1.setLayoutManager(new GridLayoutManager(activity, 1, OrientationHelper.HORIZONTAL, false));
        mAdapter = new PopRenwuPayAdapter(R.layout.pop_renwupay_item);
        recyclerview1.setAdapter(mAdapter);

        mAdapter.setNewData(mList1);
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //item click
                PopRenwuPayBean bean = (PopRenwuPayBean) adapter.getItem(position);
//                Toasty.normal(activity, bean.getContent() + "item click").show();
            }
        });

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
//        Bitmap shot = BitmapUtils.takeScreenShot(activity.getWindow().getDecorView());
//        Bitmap blur = BitmapUtils.blur(activity, shot);
//        this.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), blur));
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
//        mMenuView.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View v, MotionEvent event) {
//
////				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
////				int music_seek_bar_cycle = (int) event.getY();
////				if (event.getAction() == MotionEvent.ACTION_UP) {
////					if (music_seek_bar_cycle < height) {
////						dismiss();
////					}
////				}
//                dismiss();
//                return true;
//            }
//        });
        showPopupWindow();
        showAtLocation(activity.getWindow().getDecorView(),
                Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
                , 0, 0); // 设置layout在PopupWindow中显示的位置

    }

    public void showPopupWindow() {
//        showAtLocation(activity.getWindow().getDecorView(),
//                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
//                , 0, 0); // 设置layout在PopupWindow中显示的位置
        setFocusable(false);
        update();
        showAtLocation(activity.getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, 0);
        NavigationBarUtil.fullScreenImmersive(getContentView());
        setFocusable(true);
        update();

    }

    private OnFinishResultClickListener mListener;

    public interface OnFinishResultClickListener {
        void onResult(String jumpUrl);
    }

}
