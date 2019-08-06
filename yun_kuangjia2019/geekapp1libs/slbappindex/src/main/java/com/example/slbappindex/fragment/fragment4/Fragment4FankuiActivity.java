package com.example.slbappindex.fragment.fragment4;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.biz3slbappusercenter.bean.SFkzxBean;
import com.example.biz3slbappusercenter.presenter.SFeedBackPresenter;
import com.example.biz3slbappusercenter.presenter.SFkzxPresenter;
import com.example.biz3slbappusercenter.view.SFeedBackView;
import com.example.biz3slbappusercenter.view.SFkzxView;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappindex.R;
import com.haier.cellarette.baselibrary.qcode.ExpandViewRect;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.umeng.analytics.MobclickAgent;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class Fragment4FankuiActivity extends SlbBaseActivity implements View.OnClickListener, SFeedBackView, SFkzxView {

    private TextView tvRight;
    private TextView tvCenter;
    private TextView tv_num1;
    private TextView tv_num2;
    private TextView tv_num3;
    private LinearLayout ll_tj1;
    private EditText edt1;
    private EditText edt2;
    private Button btnSure;
    private TextView tv_ed_content;
    private SFeedBackPresenter presenter;
    private SFkzxPresenter sFkzxPresenter;
    private String tel;
    private String wx;
    private String qq;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment4_fankui;
    }

    @Override
    protected void onResume() {
        MobclickAgent.onEvent(this, "Fragment4FankuiActivity");
        super.onResume();
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        findview();
        onclick();
        donetwork();

    }

    private void donetwork() {
        tvCenter.setText("反馈中心");
        ExpandViewRect.expandViewTouchDelegate(tvRight, 20, 20, 20, 20);
//        edt1.clearFocus();
//        edt2.clearFocus();
//        hideSoftKeyboard();
        KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                // some code depending on keyboard visiblity status
                if (isOpen) {
                    // 此处为得到焦点时的处理内容
                    ll_tj1.setVisibility(View.GONE);
                } else {
                    // 此处为失去焦点时的处理内容
                    ll_tj1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ll_tj1.setVisibility(View.VISIBLE);
                        }
                    }, 100);
                }
            }
        });
        edt1.addTextChangedListener(mTextWatcher);
        presenter = new SFeedBackPresenter();
        presenter.onCreate(this);
        sFkzxPresenter = new SFkzxPresenter();
        sFkzxPresenter.onCreate(this);
        sFkzxPresenter.getFkzxData();
        //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_ed_content.setText(Html.fromHtml("还能输入" + "<font color=\"#FEA914\">" + MAX_TEXT + "</font>" + "个字", Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv_ed_content.setText(Html.fromHtml("还能输入" + "<font color=\"#FEA914\">" + MAX_TEXT + "</font>" + "个字"));
        }

    }

    private int MAX_TEXT = 100;
    private TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv_ed_content.setText(Html.fromHtml("还能输入" + "<font color=\"#FEA914\">" + (MAX_TEXT - s.length()) + "</font>" + "个字", Html.FROM_HTML_MODE_COMPACT));
            } else {
                tv_ed_content.setText(Html.fromHtml("还能输入" + "<font color=\"#FEA914\">" + (MAX_TEXT - s.length()) + "</font>" + "个字"));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = edt1.getSelectionStart();
            editEnd = edt1.getSelectionEnd();
            if (temp.length() > MAX_TEXT) {
                Toasty.normal(Fragment4FankuiActivity.this, "限制输入" + MAX_TEXT + "字符!").show();
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                edt1.setText(s);
                edt1.setSelection(tempSelection);
            }
        }
    };

    private void onclick() {
        tvRight.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        tv_num1.setOnClickListener(this);
        tv_num2.setOnClickListener(this);
        tv_num3.setOnClickListener(this);
        //用于嵌套Scrollview滚动冲突问题bufen
//        edt1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    scl1.requestDisallowInterceptTouchEvent(false);
//                } else {
//                    scl1.requestDisallowInterceptTouchEvent(true);
//                }
//                return false;
//            }
//        });
    }

    private void findview() {
        tvRight = findViewById(R.id.tv_right);
        tvCenter = findViewById(R.id.tv_center);
        tv_num1 = findViewById(R.id.tv_num1);
        tv_num2 = findViewById(R.id.tv_num2);
        tv_num3 = findViewById(R.id.tv_num3);
        tv_ed_content = findViewById(R.id.tv_ed_content);
        ll_tj1 = findViewById(R.id.ll_tj1);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btnSure = findViewById(R.id.btn_sure);
    }

    /**
     * 复制内容到剪切板
     *
     * @param copyStr
     * @return
     */
    private boolean copy(String copyStr) {
        try {
            //获取剪贴板管理器
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", copyStr);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_right) {
            onBackPressed();
        } else if (i == R.id.tv_num1) {
            // 拨号
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + tel));
            startActivity(intent);
        } else if (i == R.id.tv_num2) {
            // 微信
            if (copy(wx)) {
                Toasty.normal(getApplicationContext(), "已复制到剪切板").show();
            }
        } else if (i == R.id.tv_num3) {
            // QQ
            if (copy(qq)) {
                Toasty.normal(getApplicationContext(), "已复制到剪切板").show();
            }
        } else if (i == R.id.btn_sure) {
            //发送bufen
            String content = edt1.getText().toString().trim();
            String contact = edt2.getText().toString().trim();
            if (TextUtils.isEmpty(content)) {
                Toasty.normal(Fragment4FankuiActivity.this, "请填写问题描述").show();
                return;
            }
            if (TextUtils.isEmpty(contact)) {
                Toasty.normal(Fragment4FankuiActivity.this, "请填写常用联系方式").show();
                return;
            }
//            Toasty.normal(Fragment4FankuiActivity.this, "发送成功，请等待回复").show();
//            startActivity(new Intent("hs.act.comm.PayActivity"));
//            if (TextUtils.isEmpty(contact)) {
//                contact = "联系人未填写联系方式";
//            }
            presenter.getFeedBackData(contact, content);
        } else {
        }
    }

    @Override
    public void OnFeedBackSuccess(String s) {
        edt1.clearFocus();
        edt2.clearFocus();
        edt1.getText().clear();
        edt2.getText().clear();
        Toasty.normal(Fragment4FankuiActivity.this, s).show();
    }

    @Override
    public void OnFeedBackNodata(String s) {
        Toasty.normal(Fragment4FankuiActivity.this, s).show();
    }

    @Override
    public void OnFeedBackFail(String s) {
        Toasty.normal(Fragment4FankuiActivity.this, s).show();
    }

    @Override
    public void OnFkzxSuccess(SFkzxBean sFkzxBean) {
        tel = sFkzxBean.getTel();
        wx = sFkzxBean.getWechat();
        qq = sFkzxBean.getQq();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_num1.setText(Html.fromHtml("电话:" + "<font color=\"#4DA3FE\">" + tel + "</font>", Html.FROM_HTML_MODE_COMPACT));
            tv_num2.setText(Html.fromHtml("微信:" + "<font color=\"#4DA3FE\">" + wx + "</font>", Html.FROM_HTML_MODE_COMPACT));
            tv_num3.setText(Html.fromHtml("QQ:" + "<font color=\"#4DA3FE\">" + qq + "</font>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv_num1.setText(Html.fromHtml("电话:" + "<font color=\"#4DA3FE\">" + tel + "</font>"));
            tv_num2.setText(Html.fromHtml("微信:" + "<font color=\"#4DA3FE\">" + wx + "</font>"));
            tv_num3.setText(Html.fromHtml("QQ:" + "<font color=\"#4DA3FE\">" + qq + "</font>"));
        }
    }

    @Override
    public void OnFkzxNodata(String s) {

    }

    @Override
    public void OnFkzxFail(String s) {

    }
}
