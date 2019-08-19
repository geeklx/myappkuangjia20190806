package com.example.slbappindex.vip;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.slbappindex.R;
import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;


// 兑换码弹窗
public class PopVipCodePay extends PopupWindow {

    private Activity activity;
    private View mMenuView;
    private LinearLayout ll23;
    private TextView tv2;
    private TextView tv3;
    private TextView tv1;

    public PopVipCodePay() {

    }

    public PopVipCodePay(final Activity activity, String msg, final OnFinishResultClickListener mListener) {
        super(activity);
//        AutoSizeConfig.getInstance().setCustomFragment(true);
//        AutoSize.autoConvertDensity((Activity) activity, 375, true);
        this.activity = activity;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_vipcode, null);
        ll23 = mMenuView.findViewById(R.id.ll23);
        tv2 = mMenuView.findViewById(R.id.tv2);
        tv3 = mMenuView.findViewById(R.id.tv3);
        tv1 = mMenuView.findViewById(R.id.tv1);
        //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv2.setText(Html.fromHtml(msg, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv2.setText(Html.fromHtml(msg));
        }
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
                    mListener.onResult();
                }
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
//        activity.getWindow().getDecorView().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                dismiss();
//            }
//        }, 3000);
//        pay_pop.showAtLocation(getWindow().getDecorView(),
//                Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
//                , 0, 0); // 设置layout在PopupWindow中显示的位置

    }


    private OnFinishResultClickListener mListener;

    public interface OnFinishResultClickListener {
        void onResult();
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


}
