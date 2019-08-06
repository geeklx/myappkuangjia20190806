package com.example.slbappindex.fragment.fragment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.LinearSnapHelper;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappshouye.bean.SCategoryBean;
import com.example.biz3slbappshouye.bean.SCategoryBean1;
import com.example.biz3slbappshouye.bean.SCommonCategoryRecommendBean1;
import com.example.biz3slbappshouye.bean.SCommonCategoryRecommendBean2;
import com.example.biz3slbappshouye.bean.SIndexCategoryRecommendBean;
import com.example.biz3slbappshouye.bean.SLBBannerBean;
import com.example.biz3slbappshouye.bean.SLBBannerBean1;
import com.example.biz3slbappshouye.presenter.SIndexCategoryListPresenter;
import com.example.biz3slbappshouye.presenter.SIndexCategoryRecommendPresenter;
import com.example.biz3slbappshouye.presenter.SLBBannerPresenter;
import com.example.biz3slbappshouye.view.SIndexCategoryListView;
import com.example.biz3slbappshouye.view.SIndexCategoryRecommendView;
import com.example.biz3slbappshouye.view.SLBBannerView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseFragment;
import com.example.slbappcomm.utils.LoginImgUtils;
import com.example.slbappcomm.widgets.recyclerviewnice.XRecyclerView;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment1.allhuibenpart.part1.MoreTingshuCategoryAdapter;
import com.example.slbappindex.fragment.fragment1.allhuibenpart.part2.commonnew2.CategoryDiffAdapterNew2;
import com.example.slbappindex.fragment.fragment1.allhuibenpart.part2.commonnew2.CategoryDiffBeanNew2;
import com.example.slbappindex.fragment.fragment1.allhuibenpart.part2.linstenbooknew1.MoreLBImgBeanNew1;
import com.geek.libglide47.base.GlideImageView;
import com.haier.cellarette.baselibrary.bannerviewquan.LXBannerView;
import com.haier.cellarette.baselibrary.bannerviewquan.holder.LXHolderCreator;
import com.haier.cellarette.baselibrary.bannerviewquan.holder.LXViewHolder;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FragmentContent2New1 extends SlbBaseFragment implements View.OnClickListener, SLBBannerView, SIndexCategoryListView,
        SIndexCategoryRecommendView {

    private GlideImageView ivTitle1;
    private TextView tvTitleName1;
    private TextView tvTitleName2;
    private RelativeLayout rlTitle1;
    private TextView tvTitleCwzx1;
    private RelativeLayout rl_jinbin_new1;

    //
    private NestedScrollView scroll_view1;

    private List<SLBBannerBean1> mListbanner1;
    private LXBannerView banner1;

    private XRecyclerView recyclerView0;
    private MoreTingshuCategoryAdapter mAdapter0;
    private List<SCategoryBean1> mList0;

    private XRecyclerView recyclerView1;
    private CategoryDiffAdapterNew2 mAdapter1;
    private List<CategoryDiffBeanNew2> mList1;
    private EmptyView mEmptyView;
    // banner
    private SLBBannerPresenter presenter1;
    private int bookColumn1 = 2;
    private int bookColumn2 = 3;
    // 分类1
    private SIndexCategoryListPresenter presenter2;
    // 分类2
    private SIndexCategoryRecommendPresenter presenter4;

    private String tablayoutId;

    private int mOffset = 0;
    private int mScrollY = 0;
    //    private View parallax;
//    private NestedScrollView scrollView;
    private RefreshLayout refreshLayout;
    private ClassicsHeader smart_header1;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            tablayoutId = getArguments().getString("tablayoutId");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(BaseApp.get(), R.color.color_BEDFFF));
//        StatusBarUtilV7.immersive(getActivity(), ContextCompat.getColor(getActivity(), R.color.color_E5F7FF), 1.0f);
//        StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(BaseApp.get(), R.color.color_BEDFFF),0);
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        findview(rootView);
        initsmart(rootView);
        onclick();
        mListbanner1 = new ArrayList<>();
        // 接口
        presenter1 = new SLBBannerPresenter();
        presenter1.onCreate(this);
        mList0 = new ArrayList<>();
        presenter2 = new SIndexCategoryListPresenter();
        presenter2.onCreate(this);
        // 接口
        presenter4 = new SIndexCategoryRecommendPresenter();
        presenter4.onCreate(this);
        donetwork();

    }

    @Override
    public void net_con_success() {
        super.net_con_success();
//        donetwork();
    }

    @Override
    public void showNetPopup() {
        super.showNetPopup();
//        netState.setUpdate(true);
//        donetwork();
    }

    @Override
    public void net_con_none() {
//        mEmptyView.errorNet();
        Toasty.normal(Objects.requireNonNull(getActivity()), "网络异常，请检查网络连接！").show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment2_new1;
    }

    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(String cateId, boolean isrefresh) {

        if (!isrefresh) {
            // 从缓存中拿出头像bufen
            if (ivTitle1 == null) {
                return;
            }
            LoginImgUtils.getInstance(getActivity()).get_head_icon(ivTitle1);
            tvTitleName1.setText(TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_NAME)) ?
                    (TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_TEL)) ? "访客" : SPUtils.getInstance().getString(CommonUtils.USER_TEL)) : SPUtils.getInstance().getString(CommonUtils.USER_NAME));

            return;
        }
//        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();
        if (scrolly == 0) {
            if (refreshLayout != null) {
                refreshLayout.autoRefresh();
            }
        } else {
            scroll_view1.smoothScrollTo(0, 0);
        }
    }

    private int scrolly = 0;

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
        banner1.pause();
        presenter1.onDestory();
        presenter2.onDestory();
        presenter4.onDestory();
        super.onDestroyView();

    }


    private void initsmart(View rootView) {
//        toolbar = rootView.findViewById(R.id.toolbar22);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                finish();
//            }
//        });

        //状态栏透明和间距处理
//        StatusBarUtilV7.immersive(getActivity());
//        StatusBarUtilV7.setPaddingSmart(getActivity(), toolbar);

//        parallax = rootView.findViewById(R.id.parallax);
//        scrollView = rootView.findViewById(R.id.scrollView);
        refreshLayout = rootView.findViewById(R.id.refreshLayout22);
        smart_header1 = rootView.findViewById(R.id.smart_header22);
        smart_header1.setEnableLastTime(false);
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = offset / 2;
//                parallax.setTranslationY(mOffset - mScrollY);
//                toolbar.setAlpha(1 - Math.min(percent, 1));
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = offset / 2;
//                parallax.setTranslationY(mOffset - mScrollY);
//                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });
        scroll_view1.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            private int lastScrollY = 0;
            private int h = DensityUtil.dp2px(170);
            private int color = ContextCompat.getColor(getActivity(), R.color.colorPrimary) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrolly = scrollY;
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
//                    buttonBar.setAlpha(1f * mScrollY / h);
//                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
//                    parallax.setTranslationY(mOffset - mScrollY);
                }
                lastScrollY = scrollY;
            }
        });
//        buttonBar.setAlpha(0);
//        buttonBar.setVisibility(View.GONE);
//        toolbar.setBackgroundColor(0);

    }

    /**
     * 第一次进来加载bufen
     */
    private void donetwork() {
//        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();
        mEmptyView.loading();
        retryData();
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_left) {
//            onBackPressed();
        } else if (i == R.id.tv_left2) {
//            onBackPressed();
        } else {

        }
    }

    private void onclick() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                retryData();
            }
        });
        mEmptyView.notices(CommonUtils.TIPS_WUSHUJU, CommonUtils.TIPS_WUWANG, "小象正奔向故事里...", "");
        mEmptyView.bind(scroll_view1).setRetryListener(new EmptyView.RetryListener() {
            @Override
            public void retry() {
                //
                retryData();
            }
        });
        mAdapter0.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //item click
                SCategoryBean1 bean = (SCategoryBean1) adapter.getItem(position);
                // String categoryId, String columnId, String type
                assert bean != null;
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("name", bean.getName());
//        map.put("quantity","3");
                MobclickAgent.onEvent(getActivity(), "listenbookpage_category", map);
                HiosHelper.resolveAd(getActivity(), getActivity(), bean.getHios());
//                Intent intent = new Intent("hs.act.slbapp.ListenBooksListActivity");
//                intent.putExtra(CommonUtils.LB_IDS1, bean.getCategoryId());
//                intent.putExtra(CommonUtils.LB_IDS2, bean.getId());
//                intent.putExtra(CommonUtils.LB_NAME1, bean.getTitle());
//                intent.putExtra(CommonUtils.LB_TYPE, bean.getType());
//                startActivity(intent);

            }
        });
        mAdapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CategoryDiffBeanNew2 bean = mList1.get(position);
                int i = view.getId();
                if (i == R.id.iv_provider3 || i == R.id.tv_provider3 || i == R.id.tv_provider31 || i == R.id.iv1) {
                    HiosHelper.resolveAd(getActivity(), getActivity(), bean.getmBean().getHios());
                }
            }
        });
//        mAdapter1.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
//            @Override
//            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, final int position) {
//                int i = view.getId();
//
//                return true;
//            }
//        });

    }

    private void retryData() {
//        mEmptyView.loading();
        presenter1.getLBBannerData("0");
        presenter2.getIndexCategoryListData("audio");
        presenter4.getIndexCategoryRecommendData("audio");
    }

    private void findview(View rootView) {
        ivTitle1 = rootView.findViewById(R.id.iv_title1);
        ivTitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //头像bufen
                MobclickAgent.onEvent(getActivity(), "listenbookpage_avatar");
                if (SlbLoginUtil2.get().isUserLogin()) {
                    Intent msgIntent = new Intent();
                    msgIntent.setAction(CommonUtils.index_action);
                    msgIntent.putExtra(CommonUtils.index_action, 2);
                    LocalBroadcastManagers.getInstance(getActivity().getApplicationContext()).sendBroadcast(msgIntent);
                } else {
                    SlbLoginUtil2.get().loginTowhere(getActivity(), new Runnable() {
                        @Override
                        public void run() {
//                        Toasty.normal(getActivity(), "登录成功，刷新页面").show();

                        }
                    });
                }
            }
        });
        tvTitleName1 = rootView.findViewById(R.id.tv_title_name1);
        rl_jinbin_new1 = rootView.findViewById(R.id.rl_jinbin_new1);
        tvTitleName2 = rootView.findViewById(R.id.tv_title_name2);
        rlTitle1 = rootView.findViewById(R.id.rl_title1);
        rlTitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                MobclickAgent.onEvent(getActivity(), "listenbookpage_search");
                Intent intent = new Intent("hs.act.slbapp.SearchListActivity");
                intent.putExtra("search_key", "");
                startActivity(intent);
            }
        });
        tvTitleCwzx1 = rootView.findViewById(R.id.tv_title_cwzx1);
        tvTitleCwzx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 宠物中心bufen
                SlbLoginUtil2.get().loginTowhere(getActivity(), new Runnable() {
                    @Override
                    public void run() {
                        if (TextUtils.equals(SLBBannerPresenter.banben, "测试")) {
                            Intent intent = new Intent("hs.act.slbapp.HIOSAdActivityLinshi");
                            intent.putExtra("linshihuiben", "http://aic.sairobo.cn:8087/petcenter/");
                            startActivity(intent);
                        }
                        if (TextUtils.equals(SLBBannerPresenter.banben, "线上")) {
                            Intent intent = new Intent("hs.act.slbapp.HIOSAdActivityLinshi");
                            intent.putExtra("linshihuiben", "http://aic.sairobo.cn:8087/petcenter/");
//                            intent.putExtra("linshihuiben", "https://hexiang-app-api.sairobo.cn/petcenter/");
                            startActivity(intent);
                        }
                    }
                });
            }
        });
        //
        scroll_view1 = rootView.findViewById(R.id.scroll_view22);
        mEmptyView = rootView.findViewById(R.id.emptyview1);
        banner1 = rootView.findViewById(R.id.banner1);
        recyclerView0 = rootView.findViewById(R.id.recycler_view0);
        recyclerView1 = rootView.findViewById(R.id.recycler_view1);
        //
        banner1.setDelayedTime(6000);
        banner1.setmIsCanLoop(true);
        banner1.setIndicatorRes(R.drawable.indicator_normal, R.drawable.indicator_selected);
        banner1.setIndicatorVisible(false);
        banner1.setIndicatorAlign(LXBannerView.IndicatorAlign.CENTER);
        banner1.getmIndicatorContainer().setPadding(40, 10, 40, 10);
        banner1.getmIndicatorContainer().setBackgroundResource(R.drawable.indicator_bg_trans);
        //
        recyclerView0.setHasFixedSize(true);
        recyclerView0.setNestedScrollingEnabled(false);
        recyclerView0.setFocusable(false);
        recyclerView0.setLayoutManager(new GridLayoutManager(getActivity(), 5, RecyclerView.VERTICAL, false));
        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
        mLinearSnapHelper.attachToRecyclerView(recyclerView0);
        mAdapter0 = new MoreTingshuCategoryAdapter(R.layout.activity_morehuiben_rec0_item);
        recyclerView0.setAdapter(mAdapter0);
        //
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setNestedScrollingEnabled(false);
        recyclerView1.setFocusable(false);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
        mAdapter1 = new CategoryDiffAdapterNew2(mList1);
        mAdapter1.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
//                int type = mList1.get(position).type;
                int type = Objects.requireNonNull(mAdapter1.getItem(position)).type;
                if (type == MoreLBImgBeanNew1.style1) {
                    return 2;
                } else if (type == MoreLBImgBeanNew1.style2) {
                    return 1;
                } else if (type == MoreLBImgBeanNew1.style3) {
                    return 2;
                } else if (type == MoreLBImgBeanNew1.style4) {
                    return 2;
                } else {
                    return 2;
                }
            }
        });
        recyclerView1.setAdapter(mAdapter1);

    }

    /**
     * ----------------------banner-----------------------
     */
    private void setBanner() {
        banner1.setPages(mListbanner1, new LXHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }

    public class BannerViewHolder implements LXViewHolder<SLBBannerBean1> {
        private GlideImageView mImageView;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_fragment1_banner_item, null);
            mImageView = view.findViewById(R.id.remote_item_image);
            return view;
        }

        @Override
        public void onBind(final Context context, final int i, final SLBBannerBean1 mBean) {
            Log.e("geek", "current position:" + i);
            mImageView.loadImage(mBean.getBannerImg(), R.drawable.slb_lunbo1);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("position", (i + 1) + "");
//        map.put("quantity","3");
                    MobclickAgent.onEvent(getActivity(), "listenbookpage_banner", map);
                    HiosHelper.resolveAd(getActivity(), getActivity(), mBean.getHios());

                }
            });
        }
    }

    @Override
    public void onPause() {
        banner1.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        banner1.start();
        // 从缓存中拿出头像bufen
        LoginImgUtils.getInstance(getActivity().getApplicationContext()).get_head_icon(ivTitle1);
        tvTitleName1.setText(TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_NAME)) ?
                (TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_TEL)) ? "访客" : SPUtils.getInstance().getString(CommonUtils.USER_TEL)) : SPUtils.getInstance().getString(CommonUtils.USER_NAME));
        super.onResume();
    }


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */

    /**
     * ----------------------banner-----------------------
     */
    @Override
    public void OnLBBannerSuccess(SLBBannerBean bean) {
        refreshLayout.finishRefresh();
        mListbanner1 = bean.getBannerList();
        if (mListbanner1 == null || mListbanner1.size() == 0) {
            return;
        }
        if (mListbanner1.size() == 1) {
            setBanner();
            banner1.setIndicatorVisible(false);
            banner1.setmIsCanLoop(false);
            return;
        }
        if (mListbanner1.size() > 0) {
            setBanner();
            banner1.setIndicatorVisible(false);
            banner1.setmIsCanLoop(true);
            banner1.start();
        }
    }

    @Override
    public void OnLBBannerNodata(String s) {
        refreshLayout.finishRefresh(false);
//        Toasty.normal(getActivity(), s).show();
    }

    @Override
    public void OnLBBannerFail(String s) {
        refreshLayout.finishRefresh(false);
//        Toasty.normal(getActivity(), s).show();
    }

    /**
     * ----------------------分类-----------------------
     */
    @Override
    public void OnIndexCategoryListSuccess(SCategoryBean list) {
        mEmptyView.success();
        mList0 = list.getCategoryViewList();
        mAdapter0.setNewData(mList0);
    }

    @Override
    public void OnIndexCategoryListNodata(String s) {
        mEmptyView.nodata();
//        Toasty.normal(this, s).show();
    }

    @Override
    public void OnIndexCategoryListFail(String s) {
        mEmptyView.errorNet();
        Toasty.normal(Objects.requireNonNull(getActivity()), "网络异常，请检查网络连接！").show();
//        Toasty.normal(this, s).show();
    }

    /**
     * -----------------------分类推荐-----------------------
     */
    @Override
    public void OnIndexCategoryRecommendSuccess(SIndexCategoryRecommendBean sCategoryRecommendBean) {
//        mEmptyView.success();
        mList1 = new ArrayList<>();
        for (int i = 0; i < sCategoryRecommendBean.getRecommendList().size(); i++) {
            // 一级bean  包含title和list<SlbMoreBooksBean2> 例如有2条数据
            SCommonCategoryRecommendBean1 bean = sCategoryRecommendBean.getRecommendList().get(i);
            // 二级bean 只有list<SlbMoreBooksBean2>   例如有三条数据
            for (int j = 0; j < bean.getSimpleShowV2Views().size(); j++) {
                if (j == 0) {
                    SCommonCategoryRecommendBean2 slbMoreBooksBean2 = new SCommonCategoryRecommendBean2();
                    slbMoreBooksBean2.setTitle(bean.getClassfyName());
//                    slbMoreBooksBean2.setBookId("-1");
                    mList1.add(new CategoryDiffBeanNew2(CategoryDiffBeanNew2.style1, slbMoreBooksBean2));
                }
                if (bean.getSimpleShowV2Views().get(j).getLayoutTag().equals("2")) {
                    mList1.add(new CategoryDiffBeanNew2(CategoryDiffBeanNew2.style2, bean.getSimpleShowV2Views().get(j)));
                } else if (bean.getSimpleShowV2Views().get(j).getLayoutTag().equals("1")) {
                    mList1.add(new CategoryDiffBeanNew2(CategoryDiffBeanNew2.style3, bean.getSimpleShowV2Views().get(j)));
                } else if (bean.getSimpleShowV2Views().get(j).getLayoutTag().equals("5")) {
                    mList1.add(new CategoryDiffBeanNew2(CategoryDiffBeanNew2.style4, bean.getSimpleShowV2Views().get(j)));
                }
            }
        }
        mAdapter1.setNewData(mList1);// mAdapter1 = new MoreLBImgAdapterNew1(mList1);
    }

    @Override
    public void OnIndexCategoryRecommendNodata(String s) {
//        mEmptyView.nodata();
//        Toasty.normal(this, s).show();
    }

    @Override
    public void OnIndexCategoryRecommendFail(String s) {
//        mEmptyView.errorNet();
//        Toasty.normal(this, s).show();
    }

}
