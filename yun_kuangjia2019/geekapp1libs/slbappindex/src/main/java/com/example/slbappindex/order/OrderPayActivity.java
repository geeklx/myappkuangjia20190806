package com.example.slbappindex.order;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.example.biz3slbappcomm.bean.SPayBean;
import com.example.biz3slbappcomm.presenter.SPayPresenter;
import com.example.biz3slbappcomm.view.SPayView;
import com.example.biz3slbapporder.bean.SOrderInfoBean;
import com.example.biz3slbapporder.presenter.SOrderInfoPresenter;
import com.example.biz3slbapporder.view.SOrderInfoView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.pay.AliPay2;
import com.example.slbappcomm.pop.anspay.PopYanzhengPay;
import com.example.slbappcomm.pop.bottompay.wechatutils.PopWeChatBean;
import com.example.slbappcomm.pop.bottompay.wechatutils.WeChat1Utils;
import com.example.slbappcomm.pop.bottompay.wechatutils.WxPayBean;
import com.example.slbappindex.R;
import com.example.slbapppay.wx.Constants;
import com.geek.libglide47.base.GlideImageView;
import com.haier.cellarette.baselibrary.loading.ShowLoadingUtil;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

// 订单确定页面
public class OrderPayActivity extends SlbBaseActivity implements View.OnClickListener, SOrderInfoView, SPayView {

    private TextView tvRight;
    private TextView tvCenter;
    private GlideImageView iv1;
    private TextView tv1;
    private TextView tv2;
    private RelativeLayout rl1;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private RelativeLayout rl2;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView tv11;
    private TextView tv12;
    private RelativeLayout rl3;
    private TextView tv13;
    private RelativeLayout rl4;
    private TextView tv14;
    private RelativeLayout rl5;
    private TextView tv15;
    private TextView tv16;

    private WxPayBean wxPay;
    private AliPay2 aliPay;
    private PopWeChatBean bean;
    private PopYanzhengPay pay_pop;
    private int tag_choose_act_qb;// 0 全套 1 本册
    private String tag_choose_act_qb_price1 = "";// 0 全套 1 本册
    private String tag_choose_act_qb_price2 = "";// 0 全套 1 本册
    private String payRight = "";// 付款购买权限(all:系列+单册 均可购买 / serial:只能购买系列全册 / single: 单集购买)
    private int tag_choose_act;// 0 微信 1 支付宝
    private String EXTRA_ID = "";// 以商品的id为txt的名字
    private String EXTRA_ID_ZONG = "";// 以商品的id为txt的名字
    private String EXTRA_ID_SOURCETYPE = "";// 以商品的id为txt的名字
    private SOrderInfoPresenter presenter;
    private SPayPresenter presenter2;

    @Override
    protected void onResume() {
        // 返回是如果支付成功就弹出成功 如果失败就关闭
        if (SPUtils.getInstance().getInt(CommonUtils.HUIBEN_PAYSUCCESS, -1) == CommonUtils.HUIBEN_PAYSUCCESS_TAG1) {
            // VIP跳出层 支付成功
            finish();
        }
        if (SPUtils.getInstance().getInt(CommonUtils.HUIBEN_PAYSUCCESS, -1) == CommonUtils.HUIBEN_PAYSUCCESS_TAG2) {
            // 订单跳出层 支付成功
            finish();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (pay_pop != null) {
            pay_pop.dismiss();
        }
        presenter.onDestory();
        presenter2.onDestory();
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_pay;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findview();
        onclick();
        EXTRA_ID = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS);
        EXTRA_ID_ZONG = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS_ZONG);
        EXTRA_ID_SOURCETYPE = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS_sourceType);
        presenter = new SOrderInfoPresenter();
        presenter.onCreate(this);
        presenter2 = new SPayPresenter();
        presenter2.onCreate(this);

        donetwork();

    }

    private void donetwork() {
        tvCenter.setText("订单支付");
        // 初始化选择bufen
        setData();
    }

    private void setData() {
        ShowLoadingUtil.showLoading(this, "", null);
        presenter.getOrderInfoData(EXTRA_ID_ZONG, EXTRA_ID, EXTRA_ID_SOURCETYPE);
    }

    private void onclick() {
        tvRight.setOnClickListener(this);
        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);

        rl4.setOnClickListener(this);
        rl5.setOnClickListener(this);

        tv16.setOnClickListener(this);

    }

    private void findview() {
        tvRight = findViewById(R.id.tv_right);
        tvCenter = findViewById(R.id.tv_center);
        iv1 = findViewById(R.id.iv1);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        rl1 = findViewById(R.id.rl1);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        rl2 = findViewById(R.id.rl2);
        tv8 = findViewById(R.id.tv8);
        tv9 = findViewById(R.id.tv9);
        tv10 = findViewById(R.id.tv10);
        tv11 = findViewById(R.id.tv11);
        tv12 = findViewById(R.id.tv12);
        rl3 = findViewById(R.id.rl3);
        tv13 = findViewById(R.id.tv13);
        rl4 = findViewById(R.id.rl4);
        tv14 = findViewById(R.id.tv14);
        rl5 = findViewById(R.id.rl5);
        tv15 = findViewById(R.id.tv15);
        tv16 = findViewById(R.id.tv16);


    }

    // 0 全套 1 本册
    private void set_gmfs_choose(int choose) {
        if (choose == 1) {
            tv7.setBackgroundResource(R.drawable.choose_no3);
            tv12.setBackgroundResource(R.drawable.choose_green3);
            tag_choose_act_qb = 1;
        } else if (choose == 0) {
            tv12.setBackgroundResource(R.drawable.choose_no3);
            tv7.setBackgroundResource(R.drawable.choose_green3);
            tag_choose_act_qb = 0;
        }
    }

    // 0 微信 1 支付宝
    private void set_zffs_choose(int choose) {
        if (choose == 0) {
            tv15.setBackgroundResource(R.drawable.choose_no3);
            tv14.setBackgroundResource(R.drawable.choose_green3);
            tag_choose_act = 0;
        } else if (choose == 1) {
            tv14.setBackgroundResource(R.drawable.choose_no3);
            tv15.setBackgroundResource(R.drawable.choose_green3);
            tag_choose_act = 1;
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_right) {
            onBackPressed();
        } else if (i == R.id.rl1) {
            set_gmfs_choose(0);
            tv2.post(new Runnable() {
                @Override
                public void run() {
                    tv2.setText(tag_choose_act_qb_price1);
                }
            });
        } else if (i == R.id.rl2) {
            set_gmfs_choose(1);
            tv2.post(new Runnable() {
                @Override
                public void run() {
                    tv2.setText(tag_choose_act_qb_price2);
                }
            });
        } else if (i == R.id.rl3) {
            // 跳转到VIP页面
            startActivity(new Intent("hs.act.slbapp.VipActivity"));
        } else if (i == R.id.rl4) {
            set_zffs_choose(0);

        } else if (i == R.id.rl5) {
            // 支付宝支付
            set_zffs_choose(1);

        } else if (i == R.id.tv16) {
            // 立即支付
            String tag_choose = "";
            if (tag_choose_act == 0) {
                tag_choose = "wechat";
                // 微信支付
                // 选择微信bufen
                if (!WeChat1Utils.getInstance(getApplicationContext()).isWeChatAppInstalled()) {
                    Toasty.normal(getApplicationContext(), "请先安装微信").show();
                    return;
                }
            } else if (tag_choose_act == 1) {
                tag_choose = "alipay";
            }
            String consumeType = "";// 全册购买是2，单册购买是3
            String prices1_id = "";
            if (EXTRA_ID_SOURCETYPE.equals("book") || EXTRA_ID_SOURCETYPE.equals("bookItem")) {
                if (tag_choose_act_qb == 0) {
                    prices1_id = tag_choose_act_qb_price1;
                    consumeType = "2";
                } else if (tag_choose_act_qb == 1) {
                    prices1_id = tag_choose_act_qb_price2;
                    consumeType = "3";
                }
            }

            if (EXTRA_ID_SOURCETYPE.equals("audio") || EXTRA_ID_SOURCETYPE.equals("audioItem")) {
                if (tag_choose_act_qb == 0) {
                    prices1_id = tag_choose_act_qb_price1;
                    consumeType = "4";
                } else if (tag_choose_act_qb == 1) {
                    prices1_id = tag_choose_act_qb_price2;
                    consumeType = "5";
                }
            }

//            presenter2.getPayData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN),
//                    EXTRA_ID_ZONG, EXTRA_ID, "2", tag_choose, prices1_id);
            presenter2.getPayData(EXTRA_ID_ZONG, EXTRA_ID, consumeType, tag_choose, null);

        } else {

        }
    }

    // 订单返回数据bufen
    @Override
    public void OnOrderInfoSuccess(SOrderInfoBean sOrderInfoBean) {
        ShowLoadingUtil.onDestory();
        iv1.loadImage(sOrderInfoBean.getCover(), R.drawable.ic_def_loading);
        tv1.setText(sOrderInfoBean.getName());
        tv2.setText(sOrderInfoBean.getFinalPkgPrice());
        tv3.setText(sOrderInfoBean.getPkgNameStr());
        if (!TextUtils.isEmpty(sOrderInfoBean.getPkgPriceStr())) {
            tv4.getPaint().setFlags(tv4.getPaint().getFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tv4.setText(sOrderInfoBean.getPkgPriceStr());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv6.setText(Html.fromHtml(sOrderInfoBean.getPkgFinalPriceStr(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv6.setText(Html.fromHtml(sOrderInfoBean.getPkgFinalPriceStr()));
        }
        tag_choose_act_qb_price1 = sOrderInfoBean.getFinalPkgPrice();
        tv8.setText(sOrderInfoBean.getSigNameStr());
        if (!TextUtils.isEmpty(sOrderInfoBean.getSigPriceStr())) {
            tv9.getPaint().setFlags(tv9.getPaint().getFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tv9.setText(sOrderInfoBean.getSigPriceStr());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv11.setText(Html.fromHtml(sOrderInfoBean.getSigFinalPriceStr(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv11.setText(Html.fromHtml(sOrderInfoBean.getSigFinalPriceStr()));
        }
        tag_choose_act_qb_price2 = sOrderInfoBean.getFinalPrice();
        if (TextUtils.equals(sOrderInfoBean.getShowVip(), "1")) {
            rl3.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(sOrderInfoBean.getShowVip(), "0")) {
            rl3.setVisibility(View.GONE);
        }
        if (sOrderInfoBean.getPayRight().equals("all")) {
            rl1.setVisibility(View.VISIBLE);
            rl2.setVisibility(View.VISIBLE);
            set_gmfs_choose(0);
            tv2.setText(tag_choose_act_qb_price1);
        } else if (sOrderInfoBean.getPayRight().equals("serial")) {
            // 全册
            rl1.setVisibility(View.VISIBLE);
            rl2.setVisibility(View.GONE);
            set_gmfs_choose(0);
            tv2.setText(tag_choose_act_qb_price1);
        } else if (sOrderInfoBean.getPayRight().equals("single")) {
            // 单册
            rl1.setVisibility(View.GONE);
            rl2.setVisibility(View.VISIBLE);
            set_gmfs_choose(1);
            tv2.setText(tag_choose_act_qb_price2);
        }
        set_zffs_choose(0);
    }

    @Override
    public void OnOrderInfoNodata(String s) {
        ShowLoadingUtil.onDestory();
    }

    @Override
    public void OnOrderInfoFail(String s) {
        ShowLoadingUtil.onDestory();
    }

    // 立即支付
    private SPayBean sPayBean1;

    @Override
    public void OnPaySuccess(SPayBean sPayBean) {
        sPayBean1 = sPayBean;
        pay_pop = new PopYanzhengPay(OrderPayActivity.this, new PopYanzhengPay.OnFinishResultClickListener() {
            @Override
            public void onSuccess() {
                if (tag_choose_act == 0) {
                    // 微信
                    if (sPayBean1 == null || sPayBean1.getWxRetRes() == null) {
                        return;
                    }
                    IWXAPI api = WXAPIFactory.createWXAPI(OrderPayActivity.this, Constants.APP_ID);
                    PayReq req = new PayReq();
                    req.appId = sPayBean1.getWxRetRes().getAppid();
                    req.partnerId = sPayBean1.getWxRetRes().getPartnerid();
                    req.prepayId = sPayBean1.getWxRetRes().getPrepayid();
                    req.nonceStr = sPayBean1.getWxRetRes().getNoncestr();
                    req.timeStamp = sPayBean1.getWxRetRes().getTimestamp();
//                            req.packageValue = "Sign=WXPay";
                    req.packageValue = sPayBean1.getWxRetRes().getPkg();
                    req.sign = sPayBean1.getWxRetRes().getSign();
                    req.extData = "app data"; // optional
                    api.sendReq(req);

                }
                if (tag_choose_act == 1) {
                    // 支付宝
                    if (sPayBean1 == null || sPayBean1.getWxRetRes() == null) {
                        return;
                    }
                    aliPay = new AliPay2(OrderPayActivity.this);
                    if (aliPay.isEmpty_orderInfo(sPayBean1.getZfbRetRes().getOrderStr())) {
                        aliPay.pay2(sPayBean1.getZfbRetRes().getOrderStr());
                    }

                }
                pay_pop.dismiss();
//                finish();
            }

            @Override
            public void onFail() {
                // 验证码错误重试

            }
        });
        pay_pop.showAtLocation(getWindow().getDecorView(),
                Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
                , 0, 0); // 设置layout在PopupWindow中显示的位置
    }

    @Override
    public void OnPayNodata(String s) {
        Toasty.normal(this, s).show();
    }

    @Override
    public void OnPayFail(String s) {
        Toasty.normal(this, s).show();
    }
}
