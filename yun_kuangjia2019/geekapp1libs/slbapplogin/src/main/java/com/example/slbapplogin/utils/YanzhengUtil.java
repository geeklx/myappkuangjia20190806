package com.example.slbapplogin.utils;

import android.app.Activity;
import android.os.CountDownTimer;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slbapplogin.R;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

public class YanzhengUtil {


    public static boolean isPhone(String phone, TextView view) {
        if (TextUtils.isEmpty(phone)) {
            view.setText("手机号不能为空");
            return false;
        }
        if (!TextUtils.isDigitsOnly(phone)) {
            view.setText("手机号格式错误，仅支持纯数字");
            Toasty.normal(BaseApp.get(), "手机号格式错误，仅支持纯数字").show();

            return false;
        }
        if (phone.length() != 11) {
            view.setText("手机号格式错误，应为11位纯数字");
            return false;
        }
        return true;
    }

    /**
     * 设置眼睛显隐bufen
     *
     * @param edt
     * @param ivEyes
     */
    public static void set_mima_vis(EditText edt, ImageView ivEyes) {
        TransformationMethod type = edt.getTransformationMethod();
        if (PasswordTransformationMethod.getInstance().equals(type)) {
            edt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            edt.setSelection(edt.getText().toString().trim().length());
            ivEyes.setImageResource(R.drawable.eyes_icon_open);
        } else {
            edt.setTransformationMethod(PasswordTransformationMethod.getInstance());
            edt.setSelection(edt.getText().toString().trim().length());
//                   edPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            ivEyes.setImageResource(R.drawable.eyes_icon_close);
        }
    }


    /**
     * 倒计时控件
     */
    private static CountDownTimer timer;

    /**
     * 从x开始倒计时
     *
     * @param x
     */
    public static void startTime(long x, final Button btnHqyzm) {
        if (timer != null) {
            timer.cancel();
        }
        timer = new CountDownTimer(x, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int remainTime = (int) (millisUntilFinished / 1000L);
                btnHqyzm.setEnabled(false);
//                btnHqyzm.setBackgroundResource(R.drawable.slb_btncomm_enpressed2);
                btnHqyzm.setText(BaseApp.get().getResources().getString(R.string.yhzc_tip502, remainTime));
                btnHqyzm.setTextColor(ContextCompat.getColor(BaseApp.get(), R.color.color4DA3FE));
            }

            @Override
            public void onFinish() {
                btnHqyzm.setEnabled(true);
//                btnHqyzm.setBackgroundResource(R.drawable.slb_btncomm_pressed2);
                btnHqyzm.setText(BaseApp.get().getResources().getString(R.string.yhzc_tip5));
                btnHqyzm.setTextColor(ContextCompat.getColor(BaseApp.get(), R.color.color5F738F));
            }
        };
        timer.start();
    }

    public static void timer_des() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

}
