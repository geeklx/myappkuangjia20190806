package com.example.slbappindex.vip;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappcomm.bean.SPayBean;
import com.example.biz3slbapporder.bean.SVIPCardBean;
import com.example.biz3slbapporder.bean.SVIPCardBean1;
import com.example.biz3slbapporder.presenter.SVipCardPresenter;
import com.example.biz3slbapporder.view.SVipCardView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.pay.AliPay2;
import com.example.slbappcomm.pop.bottompay.ShareBottomDialog;
import com.example.slbappcomm.pop.bottompay.wechatutils.PopWeChatBean;
import com.example.slbappcomm.pop.bottompay.wechatutils.WeChat1Utils;
import com.example.slbappcomm.pop.bottompay.wechatutils.WxPayBean;
import com.example.slbappindex.R;
import com.example.slbapppay.wx.Constants;
import com.geek.libglide47.base.GlideImageView;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;
import com.haier.cellarette.baselibrary.qcode.ExpandViewRect;
import com.haier.cellarette.baselibrary.statusbar.StatusBarUtilV7;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

// 我的VIP页面
public class VipActivity extends SlbBaseActivity implements View.OnClickListener, SVipCardView {

    private TextView tvRight;
    private TextView tvCenter;
    private GlideImageView iv1;
    private RelativeLayout rl_login1;
    private TextView tv2_login;
    private RelativeLayout rl_login2;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv_url1;
    private RecyclerView recyclerview1;
    private VipAdapter mAdapter;
    private List<SVIPCardBean1> mList;
    private NestedScrollView scrollView1;
    private RelativeLayout rl11;
    private EmptyView mEmptyView;

    //
    private String tv_url11 = "http://hexiang-app.sairobo.cn/user-notice/user-agreement.html";
    private SVipCardPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!SlbLoginUtil2.get().isUserLogin()) {
//            startActivity(new Intent("hs.act.slbapp.SlbLoginActivity"));
//            finish();
//            return;
//        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment4_vip;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        StatusBarUtilV7.immersive(this, ContextCompat.getColor(this, R.color.transparent), 0.0f);// color_E5F7FF
        findview();
        onclick();
        presenter = new SVipCardPresenter();
        presenter.onCreate(this);
//        donetwork();
        //
        MobclickAgent.onEvent(this, "VipActivity");
//        SPUtils.getInstance().put(CommonUtils.HUIBEN_PAYSUCCESS, CommonUtils.HUIBEN_PAYSUCCESS_TAG1);

    }

    @Override
    protected void onResume() {
//        Toasty.normal(this, "刷新了").show();
        donetwork();
        super.onResume();
    }

    private void donetwork() {
        set_vis_login_vip();
        mEmptyView.loading();
        mList = new ArrayList<>();
        presenter.getVipCardData();

    }

    private void set_vis_login_vip() {
        if (SlbLoginUtil2.get().isUserLogin()) {
            rl_login1.setVisibility(View.GONE);
            rl_login2.setVisibility(View.VISIBLE);
        } else {
            rl_login1.setVisibility(View.VISIBLE);
            rl_login2.setVisibility(View.GONE);
        }
    }

    private final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime;

    private void onclick() {
        mEmptyView.notices(CommonUtils.TIPS_WUSHUJU, CommonUtils.TIPS_WUWANG, "小象正奔向故事里...", "");
        mEmptyView.bind(rl11).setRetryListener(new EmptyView.RetryListener() {
            @Override
            public void retry() {
                //
                donetwork();
            }
        });
        tvRight.setOnClickListener(this);
        tv2_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlbLoginUtil2.get().loginTowhere(VipActivity.this, new Runnable() {
                    @Override
                    public void run() {
                        donetwork();
                    }
                });
            }
        });
        tv_url1.setOnClickListener(this);
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                //item click
//                VipBean bean = (VipBean) adapter.getItem(position);
//                Toasty.normal(VipActivity.this, bean.getContent2() + "item click").show();
//            }
//        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                final SVIPCardBean1 bean = mList.get(position);
                int i = view.getId();
                if (i == R.id.tv4) {
//                    Toasty.normal(VipActivity.this, bean.getContent2() + "item click").show();
//                    SPUtils.getInstance().put("dialog_prices", bean.getContent2());
                    long curClickTime = System.currentTimeMillis();
                    if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                        // 超过点击间隔后再将lastClickTime重置为当前点击时间
                        lastClickTime = curClickTime;
                        SlbLoginUtil2.get().loginTowhere(VipActivity.this, new Runnable() {
                            @Override
                            public void run() {
                                set_vis_login_vip();
                                bottomDialog.setDataShow(getSupportFragmentManager(), bean.getId(), bean.getFinalPrice());
                            }
                        });
                    }
//                    startActivity(new Intent("hs.act.comm.PayActivity"));
                }
            }
        });
        bottomDialog.setShareBottomDialogListener(new ShareBottomDialog.OnOKClickListener() {
            @Override
            public void onOKClick(final String tag_choose, final SPayBean sPayBean) {
                // 去支付bufen
                if (TextUtils.equals(tag_choose, ShareBottomDialog.CHOOSE_TAG1)) {
                    // 微信
                    if (sPayBean == null || sPayBean.getWxRetRes() == null) {
                        return;
                    }
                    // 选择微信bufen
                    if (!WeChat1Utils.getInstance(getApplicationContext()).isWeChatAppInstalled()) {
                        Toasty.normal(getApplicationContext(), "请先安装微信").show();
                        return;
                    }
                    IWXAPI api = WXAPIFactory.createWXAPI(VipActivity.this, Constants.APP_ID);
                    PayReq req = new PayReq();
                    req.appId = sPayBean.getWxRetRes().getAppid();
                    req.partnerId = sPayBean.getWxRetRes().getPartnerid();
                    req.prepayId = sPayBean.getWxRetRes().getPrepayid();
                    req.nonceStr = sPayBean.getWxRetRes().getNoncestr();
                    req.timeStamp = sPayBean.getWxRetRes().getTimestamp();
//                            req.packageValue = "Sign=WXPay";
                    req.packageValue = sPayBean.getWxRetRes().getPkg();
                    req.sign = sPayBean.getWxRetRes().getSign();
                    req.extData = "app data"; // optional
                    api.sendReq(req);

                }
                if (TextUtils.equals(tag_choose, ShareBottomDialog.CHOOSE_TAG2)) {
                    // 支付宝
                    if (sPayBean == null || sPayBean.getWxRetRes() == null) {
                        return;
                    }
                    aliPay = new AliPay2(VipActivity.this);
                    if (aliPay.isEmpty_orderInfo(sPayBean.getZfbRetRes().getOrderStr())) {
                        aliPay.pay2(sPayBean.getZfbRetRes().getOrderStr());
                    }

                }
            }
        });
    }

    private ShareBottomDialog bottomDialog;
    private WxPayBean wxPay;
    private AliPay2 aliPay;
    private PopWeChatBean bean;
    private int tag_choose_act;

    private void findview() {
        tvRight = findViewById(R.id.tv_right);
        tvCenter = findViewById(R.id.tv_center);
        iv1 = findViewById(R.id.iv1);
        rl_login1 = findViewById(R.id.rl_login1);
        tv2_login = findViewById(R.id.tv2_login);
        rl_login2 = findViewById(R.id.rl_login2);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv_url1 = findViewById(R.id.tv_url1);
        scrollView1 = findViewById(R.id.scroll_view1);
        rl11 = findViewById(R.id.rl11);
        mEmptyView = findViewById(R.id.emptyview1);
        recyclerview1 = findViewById(R.id.recyclerview1);

        tvCenter.setText("VIP会员");
        ExpandViewRect.expandViewTouchDelegate(tvRight, 20, 20, 20, 20);

        recyclerview1.setHasFixedSize(true);
        recyclerview1.setNestedScrollingEnabled(false);
        recyclerview1.setFocusable(false);
        recyclerview1.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));
        mAdapter = new VipAdapter(R.layout.activity_fragment4_vip_item);
        recyclerview1.setAdapter(mAdapter);

        bottomDialog = new ShareBottomDialog(this);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_right) {
            onBackPressed();
        } else if (i == R.id.tv_url1) {
            HiosHelper.resolveAd(this, this, tv_url11);
        } else {
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        super.onDestroy();
    }

    @Override
    public void OnVipCardSuccess(SVIPCardBean svipCardBean) {
        mEmptyView.success();
        RequestOptions options = new RequestOptions()
//                .signature(new ObjectKey(UUID.randomUUID().toString()))  // 重点在这行
                .skipMemoryCache(false)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.head_moren2)
                .error(R.drawable.head_moren2)
                .fallback(R.drawable.head_moren2); //url为空的时候,显示的图片;
        Glide.with(BaseApp.get()).load(svipCardBean.getAvatar())
                .apply(options)
                .into(iv1);
        tv1.setText(svipCardBean.getNickName());
        tv2.setVisibility(View.VISIBLE);

        if (TextUtils.isEmpty(svipCardBean.getEndDateString())) {
            select_useful(tv2, R.drawable.slb_no_vip_date);
            tv2.setText("未开通");
        } else {
            select_useful(tv2, R.drawable.slb_vip_date);
            tv2.setText(svipCardBean.getEndDateString());
        }

        tv3.setText(svipCardBean.getVipRemark());
        tv_url11 = svipCardBean.getVipRuleUrl();
        //
        mList = svipCardBean.getPriceList();
        mAdapter.setNewData(mList);
        mAdapter.notifyDataSetChanged();

    }

    private void select_useful(TextView tv, int drawabless) {
        Drawable drawable = getResources().getDrawable(drawabless);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        tv.setCompoundDrawables(drawable, null, null, null);
    }

    @Override
    public void OnVipCardNodata(String s) {
        mEmptyView.errorNet();
    }

    @Override
    public void OnVipCardFail(String s) {
        mEmptyView.errorNet();
    }
}
