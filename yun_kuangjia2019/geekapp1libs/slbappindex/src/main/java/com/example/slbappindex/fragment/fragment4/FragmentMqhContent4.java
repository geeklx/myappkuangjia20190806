package com.example.slbappindex.fragment.fragment4;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
//import android.support.annotation.Nullable;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappshouye.bean.SLB1HistoryBean;
import com.example.biz3slbappshouye.bean.SLB1HistoryBean1;
import com.example.biz3slbappshouye.bean.SMyBooksBean;
import com.example.biz3slbappshouye.bean.SMyBooksBean1;
import com.example.biz3slbappshouye.presenter.SLB1HistoryPresenter;
import com.example.biz3slbappshouye.presenter.SMyBooksPresenter;
import com.example.biz3slbappshouye.view.SLB1HistoryView;
import com.example.biz3slbappshouye.view.SMyBooksView;
import com.example.biz3slbappusercenter.bean.SLoginUserInfoBean;
import com.example.biz3slbappusercenter.bean.SUserImgBean;
import com.example.biz3slbappusercenter.presenter.SGetUserInfoPresenter;
import com.example.biz3slbappusercenter.presenter.SUploadUserImgPresenter;
import com.example.biz3slbappusercenter.view.SGetUserInfoView;
import com.example.biz3slbappusercenter.view.SUploadUserImgView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseFragment;
import com.example.slbappcomm.uploadimg.ClipImageActivity;
import com.example.slbappcomm.uploadimg.view.UploadImgGetFileUtil;
import com.example.slbappcomm.utils.LoginImgUtils;
import com.example.slbappcomm.utils.cutimg.ShareBitmapUtils;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment1.historypart.Fragment3MqhAdapter;
import com.example.slbappindex.fragment.fragment1.huibendetaillistpart.mybookslistpart.MyBooksListAdapter;
import com.example.slbappindex.fragment.fragment4.uploadimg.Fragment4UploadImgUtils;
import com.example.slbappindex.fragment.utils.GridSpacingItemDecoration;
import com.geek.libglide47.base.GlideImageView;
import com.geek.libglide47.base.util.DisplayUtil;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.BookPaddingDecoration;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;
import com.zhy.base.fileprovider.FileProvider7;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.jessyan.autosize.AutoSize;

import static android.app.Activity.RESULT_OK;

public class FragmentMqhContent4 extends SlbBaseFragment implements View.OnClickListener,
        SGetUserInfoView, SUploadUserImgView, SMyBooksView, SLB1HistoryView {

    private Context mContext;
    private NestedScrollView scrollView;
    private RefreshLayout refreshLayout;
    private ClassicsHeader smart_header1;
    private ImageView iv_setting;//设置
    private GlideImageView iv_head;//头像
    private ImageView iv_to_chongwu;//跳转到宠物
    private TextView tv_name;//昵称
    private TextView tv_age;//年龄
    private TextView tv_coin;//金币数
    private TextView tv_readcount;//阅读本数
    private TextView tv_readhours;//阅读时长
    private LinearLayout ll_jihuoma;//激活兑换码
    private LinearLayout ll_vip1;//vip
    private LinearLayout ll22;
    private ImageView iv_paopao1;//vip

    private TextView tv_bookshelf_more;//书架-更多

    private TextView tv_history_more;//记录-更多
    private TextView tv_vipEndDate;//vip到期时间
    private LinearLayout ll_medal;
    private TextView medalCountTv;//勋章数
    private SUploadUserImgPresenter presenterImg;
    private SGetUserInfoPresenter presenter;

    private RecyclerView rv_history;//记录列表
    private SLB1HistoryPresenter historyPresenter;
    private RelativeLayout rl_historyTitle;
    private Fragment3MqhAdapter historyAdapter;
    private List<SLB1HistoryBean1> historyList;

    private BookPaddingDecoration recycleViewDivider;
    private RelativeLayout rl_mybooks12;//书架列表
    private RecyclerView rv_bookshelf;//书架列表
    private SMyBooksPresenter bookshelfPresenter;
    private RelativeLayout rl_bookshelfTitle;
    private MyBooksListAdapter bookshelfAdapter;
    private List<SMyBooksBean1> bookList;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mqh_fragment4;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        AutoSize.autoConvertDensityOfGlobal(Objects.requireNonNull(getActivity())); //如果没有自定义需求用这个方法
        AutoSize.autoConvertDensity(getActivity(), 375, true); //如果有自定义需求就用这个方法
        super.setup(rootView, savedInstanceState);
        iv_setting = rootView.findViewById(R.id.iv_setting);
        iv_head = rootView.findViewById(R.id.iv_head);
        iv_to_chongwu = rootView.findViewById(R.id.iv_to_chongwu);
        tv_name = rootView.findViewById(R.id.tv_name);
        tv_age = rootView.findViewById(R.id.tv_age);
        tv_coin = rootView.findViewById(R.id.tv_coin);
        tv_readcount = rootView.findViewById(R.id.tv_readcount);
        tv_readhours = rootView.findViewById(R.id.tv_readhours);
        ll_jihuoma = rootView.findViewById(R.id.ll_jihuoma);
        ll_vip1 = rootView.findViewById(R.id.ll_vip1);
        ll22 = rootView.findViewById(R.id.ll22);
        iv_paopao1 = rootView.findViewById(R.id.iv_paopao1);
        tv_vipEndDate = rootView.findViewById(R.id.tv_vipEndDate);
        rl_mybooks12 = rootView.findViewById(R.id.rl_mybooks12);
        rv_bookshelf = rootView.findViewById(R.id.rv_bookshelf);
        tv_bookshelf_more = rootView.findViewById(R.id.tv_bookshelf_more);
        rv_history = rootView.findViewById(R.id.rv_history);
        tv_history_more = rootView.findViewById(R.id.tv_history_more);
        rl_historyTitle = rootView.findViewById(R.id.rl_historyTitle);
        rl_bookshelfTitle = rootView.findViewById(R.id.rl_bookshelfTitle);
        ll_medal = rootView.findViewById(R.id.ll_medal);
        medalCountTv = rootView.findViewById(R.id.medalCountTv);
        initsmart(rootView);
        // 我的书架
        recycleViewDivider = new BookPaddingDecoration(getActivity(), OrientationHelper.VERTICAL, R.drawable.bg_book_shelf_charge9);
        rv_bookshelf.setNestedScrollingEnabled(false);
        rv_bookshelf.addItemDecoration(recycleViewDivider);// 书架bufen
        rv_bookshelf.setLayoutManager(new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL, false));
        bookshelfAdapter = new MyBooksListAdapter(R.layout.activity_fragment1_mybooks_list_item_new1);
        rv_bookshelf.setAdapter(bookshelfAdapter);
        // 历史记录
        rv_history.setHasFixedSize(true);
        rv_history.setNestedScrollingEnabled(false);
        rv_history.setFocusable(false);
        rv_history.setLayoutManager(new GridLayoutManager(mContext, 4, RecyclerView.VERTICAL, false));
        rv_history.addItemDecoration(new GridSpacingItemDecoration(4, (int) (DisplayUtil.getScreenWidth(mContext) * 8f / 375), true));
        historyAdapter = new Fragment3MqhAdapter(R.layout.activity_mqh_fragment1_rec1history_item);
        rv_history.setAdapter(historyAdapter);

        setListener();
        // 获取头像bufen
        presenter = new SGetUserInfoPresenter();
        presenter.onCreate(this);
        // 上传头像bufen
        presenterImg = new SUploadUserImgPresenter();
        presenterImg.onCreate(this);
        // 我的书架bufen
        bookList = new ArrayList<>();
        bookshelfPresenter = new SMyBooksPresenter();
        bookshelfPresenter.onCreate(this);
        // 历史记录bufen
        historyList = new ArrayList<>();
        historyPresenter = new SLB1HistoryPresenter();
        historyPresenter.onCreate(this);
        initData();
    }

    @Override
    public void net_con_none() {
        super.net_con_none();
        Toasty.normal(Objects.requireNonNull(getActivity()), "网络异常，请检查网络连接！").show();
    }

    @Override
    public void net_con_success() {
        retryData();
    }

    @Override
    public void showNetPopup() {
        super.showNetPopup();
        netState.setUpdate(true);
        retryData();
//        ToastUtil.showToastCenter("当前是移动网络");
    }

    private void setListener() {
        tv_coin.setOnClickListener(this);
        iv_setting.setOnClickListener(this);
        iv_head.setOnClickListener(this);
        iv_to_chongwu.setOnClickListener(this);
        ll_vip1.setOnClickListener(this);
        iv_paopao1.setOnClickListener(this);
        tv_bookshelf_more.setOnClickListener(this);
        tv_history_more.setOnClickListener(this);
        ll_medal.setOnClickListener(this);
        bookshelfAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SMyBooksBean1 mBean = bookList.get(position);
                int i = view.getId();
                HiosHelper.resolveAd(getActivity(), getActivity(), mBean.getHios());

            }
        });
        historyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SLB1HistoryBean1 mBean = historyList.get(position);
                int i = view.getId();
                HiosHelper.resolveAd(getActivity(), getActivity(), mBean.getHios());

            }
        });
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrolly = scrollY;
            }
        });
    }

    private void initsmart(View rootView) {
        scrollView = rootView.findViewById(R.id.scrollView);
        refreshLayout = rootView.findViewById(R.id.refreshLayout);
        smart_header1 = rootView.findViewById(R.id.smart_header1);
        smart_header1.setEnableLastTime(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                // 业务逻辑bufen
                retryData();
            }
        });
    }

    @Override
    public void onResume() {
        retryData();
        MyLogUtil.e("--sssss--", "onResume");
        super.onResume();
    }

    private void retryData() {
        presenter.getGetUserInfoData();
//        rv_bookshelf.removeItemDecoration(recycleViewDivider);// 书架bufen
        bookshelfPresenter.getMyBooksData(1, 6, "0");
        historyPresenter.getLB1HistoryData(1, 4);
    }

    @Override
    public void onDestroyView() {
        presenter.onDestory();
        presenterImg.onDestory();
        bookshelfPresenter.onDestory();
        historyPresenter.onDestory();
        super.onDestroyView();

    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {
        MyLogUtil.e("--sssss--", "initData");
        if (is_first) {
            is_first = false;
            retryData();
        }

    }

    private boolean is_first = true;
    private int scrolly = 0;

    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(final String cateId, boolean isrefresh) {
//        if (!isrefresh) {
//            retryData();
//            return;
//        }
        if (isrefresh) {
            if (scrolly == 0) {
                if (refreshLayout != null) {
                    refreshLayout.autoRefresh();
                }
            } else {
                scrollView.scrollTo(0, 0);
            }
        }
        // 接口bufen
//        refreshLayout.autoRefresh();
    }

    /**
     * 当切换底部的时候通知每个fragment切换的id是哪个bufen
     *
     * @param cateId
     */
    public void give_id(String cateId) {
//        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦4").show();
//        if (Fragment4UploadImgUtils.getInstance(getActivity()).getPopupWindow() != null &&
//                Fragment4UploadImgUtils.getInstance(getActivity()).getPopupWindow().isShowing()) {
//            Fragment4UploadImgUtils.getInstance(getActivity()).getPopupWindow().dismiss();
//
//        }
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_head) {
            //上传头像bufen
//            Toasty.normal(getActivity(), "上传头像").show();
            MobclickAgent.onEvent(getActivity(), "minepage_avatar");
            if (!SlbLoginUtil2.get().isUserLogin()) {
                startActivity(new Intent("hs.act.slbapp.SlbLoginActivity"));
                return;
            }
            Fragment4UploadImgUtils.getInstance(getActivity()).
                    uploadHeadImage(getActivity(),
                            new File(CommonUtils.img_file_url, CommonUtils.img_file_name)
                            , new Fragment4UploadImgUtils.OnPopBackListener() {
                                @Override
                                public void onClick1(PopupWindow popupWindow) {
                                    //权限判断
                                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(getActivity(), UploadImgGetFileUtil.needPermissions, Fragment4UploadImgUtils.WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                                    } else {
                                        gotoCamera(new File(CommonUtils.img_file_url, CommonUtils.img_file_name));
                                    }
                                    popupWindow.dismiss();
                                }

                                @Override
                                public void onClick2(PopupWindow popupWindow) {
                                    //权限判断
                                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(getActivity(), UploadImgGetFileUtil.needPermissions, Fragment4UploadImgUtils.READ_EXTERNAL_STORAGE_REQUEST_CODE);
                                    } else {
                                        gotoPhoto();
                                    }
                                    popupWindow.dismiss();
                                }

                                @Override
                                public void onClick3(PopupWindow popupWindow) {
                                    popupWindow.dismiss();
                                }
                            });

        } else if (i == R.id.tv11) {
            // 精品绘本

        } else if (i == R.id.tv_coin) {
            // 分享截屏图片
            // 测试
//            Bitmap bitmap = ImgOtherToBitmap.drawableToBitmap(R.drawable.slb_jq2);
            ShareBitmapUtils shareBitmapUtils = new ShareBitmapUtils();
//            Bitmap bitmap = shareBitmapUtils.drawComBitmap(fl1, this);
//            Bitmap bitmap = shareBitmapUtils.loadBitmapFromView(scrollView, scrollView.getMeasuredWidth(), scrollView.getMeasuredHeight());
//            Bitmap bitmap = ShareImgCutUtil.activityShot(getActivity());
//            Bitmap bitmap1 = null;//截取长图
//            try {
//                bitmap1 = ShareImgCutUtil.makeQRImage("合象阅读", 375, 200);
//            } catch (WriterException e) {
//                e.printStackTrace();
//            }
//            Bitmap bitmap2 = ShareImgCutUtil.getLinearLayoutBitmap(ll22);//截取长图
//            Bitmap bitmap = ShareImgCutUtil.mergeBitmap(bitmap2, bitmap1);//截取长图
//
//            PopForShareImg popForShareImg = new PopForShareImg(getActivity(), bitmap, null);
        } else if (i == R.id.iv_paopao1) {
            // 激活码
            SlbLoginUtil2.get().loginTowhere(getActivity(), new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent("hs.act.slbapp.VipCodeActivity"));
                }
            });
        } else if (i == R.id.ll_vip1) {
            // 我的vip
            startActivity(new Intent("hs.act.slbapp.VipActivity"));
        } else if (i == R.id.tv_bookshelf_more) {
            // 我的书架
            Intent intent = new Intent("hs.act.slbapp.MyBooksListActivity");
            intent.putExtra(CommonUtils.HUIBEN_IDS, CommonUtils.HUIBEN_TAG2);
            intent.putExtra(CommonUtils.HUIBEN_TITLES, "我的书架");
            startActivity(intent);
        } else if (i == R.id.iv_setting) {
            // 设置
//            startActivity(new Intent("hs.act.slbapp.XmlyAct"));
            startActivity(new Intent("hs.act.slbapp.Fragment4SettingActivityNew1"));
        } else if (i == R.id.tv_history_more) {
            // 历史记录
            Intent intent = new Intent("hs.act.slbapp.HistoryListActivity");
            intent.putExtra(CommonUtils.HUIBEN_IDS, CommonUtils.HUIBEN_TAG3);
            intent.putExtra(CommonUtils.HUIBEN_TITLES, "历史记录");
            startActivity(intent);
        } else if (i == R.id.iv_to_chongwu) {
            // 宠物中心
            SlbLoginUtil2.get().loginTowhere(getActivity(), new Runnable() {
                @Override
                public void run() {
                    if (TextUtils.equals(SGetUserInfoPresenter.banben, "测试")) {
                        Intent intent = new Intent("hs.act.slbapp.HIOSAdActivityLinshi");
                        intent.putExtra("linshihuiben", "http://aic.sairobo.cn:8087/petcenter/");
                        startActivity(intent);
                    }
                    if (TextUtils.equals(SGetUserInfoPresenter.banben, "线上")) {
                        Intent intent = new Intent("hs.act.slbapp.HIOSAdActivityLinshi");
                        intent.putExtra("linshihuiben", "http://aic.sairobo.cn:8087/petcenter/");
//                            intent.putExtra("linshihuiben", "https://hexiang-app-api.sairobo.cn/petcenter/");
                        startActivity(intent);
                    }
                }
            });
        } else if (i == R.id.btn_sure) {
            // 退出登录
//            startActivity(new Intent(""));// 请求接口成功后跳转到登录页面

        } else if (i == R.id.btn_cancel) {
            // 关闭应用
            BaseAppManager.getInstance().closeApp();
        } else if (i == R.id.ll_medal) {
            SlbLoginUtil2.get().loginTowhere(getActivity(), new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent("hs.act.slbapp.MyMedalActivity"));
                }
            });
        }
    }

    /**
     * 跳转到相册
     */
    public void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), Fragment4UploadImgUtils.REQUEST_PICK);
    }

    /**
     * 跳转到照相机 File file = new File(ConstantsUtils.file_url, "uploadimg" + ".jpg")
     */
    public void gotoCamera(File file) {
        Log.d("evan", "*****************打开相机********************");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            Uri fileUri1 = FileProvider7.getUriForFile(getActivity(), file);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri1);
            startActivityForResult(takePictureIntent, Fragment4UploadImgUtils.REQUEST_CAPTURE);
        }
    }

    /**
     * 打开截图界面 File file = new File(ConstantsUtils.file_url, "uploadimg" + ".jpg")  Uri.fromFile(file)
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), ClipImageActivity.class);
        intent.putExtra("type", 1);
        intent.putExtra("img_file_url", CommonUtils.img_file_url);
        intent.putExtra("img_file_name", CommonUtils.img_file_name);
        intent.setData(uri);
        startActivityForResult(intent, Fragment4UploadImgUtils.REQUEST_CROP_PHOTO);
    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Fragment4UploadImgUtils.WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
//                Fragment4UploadImgUtils.getInstance(getActivity()).gotoCamera(getActivity(), new File(img_file_url, img_file_name));
                gotoCamera(new File(CommonUtils.img_file_url, CommonUtils.img_file_name));
            }
        } else if (requestCode == Fragment4UploadImgUtils.READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
//                Fragment4UploadImgUtils.getInstance(getActivity()).gotoPhoto(getActivity());
                gotoPhoto();
            }
        }
    }

    @Override
    public void onActResult(int requestCode, int resultCode, Intent data) {
        super.onActResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Fragment4UploadImgUtils.REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
//                    Fragment4UploadImgUtils.getInstance(getActivity()).gotoClipActivity(getActivity(), Uri.fromFile(new File(img_file_url, img_file_name)));
                    gotoClipActivity(Uri.fromFile(new File(CommonUtils.img_file_url, CommonUtils.img_file_name)));
                }
                break;
            case Fragment4UploadImgUtils.REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
//                    Fragment4UploadImgUtils.getInstance(getActivity()).gotoClipActivity(getActivity(), uri);
                    gotoClipActivity(uri);
                }
                break;
            case Fragment4UploadImgUtils.REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = Fragment4UploadImgUtils.getInstance(getActivity()).getRealFilePathFromUri(getActivity(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);

                    iv_head.loadImage(cropImagePath, R.drawable.ic_def_loading);

//                    if (TextUtils.isEmpty(cropImagePath)) {
//                        return;
//                    }
//                    if (bitMap == null) {
//                        return;
//                    }
//                    ivCenter.setImageBitmap(bitMap);
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......
//                    Toasty.normal(BaseApp.get(), "上传到服务器").show();
//                    presenterImg.getUploadUserImgData2(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), BitmapToBase64Utils.bitmapToBase64(bitMap));
                    File file = new File(CommonUtils.img_file_url + CommonUtils.img_file_name);
                    presenterImg.getUploadUserImgData(new File(cropImagePath));

                }
                break;
        }
    }


    @Override
    public void OnGetUserInfoSuccess(SLoginUserInfoBean sLoginUserInfoBean) {
        refreshLayout.finishRefresh();
        SPUtils.getInstance().put(CommonUtils.USER_TEL, sLoginUserInfoBean.getPbUser().getPhone());
//        SPUtils.getInstance().put(CommonUtils.USER_TOKEN, sLoginUserInfoBean.getToken());
        SPUtils.getInstance().put(CommonUtils.USER_NAME, sLoginUserInfoBean.getPbUser().getNickName());
        SPUtils.getInstance().put(CommonUtils.USER_IMG, sLoginUserInfoBean.getPbUser().getAvatar());
        tv_name.setText(TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_NAME)) ?
                (TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_TEL)) ? "访客" : SPUtils.getInstance().getString(CommonUtils.USER_TEL)) : SPUtils.getInstance().getString(CommonUtils.USER_NAME));
        if (TextUtils.isEmpty(sLoginUserInfoBean.getPbUser().getChildAge())) {
            tv_age.setText("");
        } else {
            tv_age.setText(sLoginUserInfoBean.getPbUser().getChildAgeStr());
        }
        if (TextUtils.isEmpty(sLoginUserInfoBean.getPbUser().getVipEndDate())) {
            ll_jihuoma.setVisibility(View.VISIBLE);
            tv_vipEndDate.setText("开通VIP");
        } else {
            ll_jihuoma.setVisibility(View.VISIBLE);
            tv_vipEndDate.setText(sLoginUserInfoBean.getPbUser().getVipEndDate());
        }
//        if (TextUtils.isEmpty(sLoginUserInfoBean.getPbUser().getChildAge())) {
//            tv_jinbi2.setVisibility(View.GONE);
//            tv_jinbi2.setText("");
//        } else {
//            tv_jinbi2.setVisibility(View.VISIBLE);
//            tv_jinbi2.setText(sLoginUserInfoBean.getPbUser().getVipEndDate());
//        }
        tv_readcount.setText(sLoginUserInfoBean.getPbUser().getReadBookCount());
        tv_readhours.setText(sLoginUserInfoBean.getPbUser().getReadDurationStr());
//        Glide.with(this).load(sAppUserInfoBean.getUserInfo().getAvatar()).into(ivCenter);
        LoginImgUtils.getInstance(getActivity().getApplicationContext()).get_head_icon(iv_head);
        medalCountTv.setText(TextUtils.isEmpty(sLoginUserInfoBean.getMedalCount()) ? "0" : sLoginUserInfoBean.getMedalCount());
    }

    @Override
    public void OnGetUserInfoNodata(String s) {
        refreshLayout.finishRefresh(false);

    }


    @Override
    public void OnGetUserInfoFail(String s) {
        refreshLayout.finishRefresh(false);
        Toasty.normal(Objects.requireNonNull(getActivity()), "网络异常，请检查网络连接！").show();
    }

    // 上传头像bufen
    @Override
    public void OnUploadUserImgSuccess(SUserImgBean bean) {
        SPUtils.getInstance().put(CommonUtils.USER_IMG, bean.getUrl());
        LoginImgUtils.getInstance(getActivity().getApplicationContext()).get_head_icon(iv_head);
//        Toasty.normal(Objects.requireNonNull(getActivity()), "上传成功").show();
    }

    @Override
    public void OnUploadUserImgNodata(String s) {
        Toasty.normal(Objects.requireNonNull(getActivity()), s).show();
    }

    @Override
    public void OnUploadUserImgFail(String s) {
        Toasty.normal(Objects.requireNonNull(getActivity()), s).show();
    }

    @Override
    public void OnMyBooksSuccess(SMyBooksBean sListCommBean, String s) {
        // 我的书架
        bookList = new ArrayList<>();
        bookList = sListCommBean.getList();
        bookshelfAdapter.setNewData(bookList);
//            mAdapter2.notifyDataSetChanged();
        if (bookList.size() > 0) {
            // 显示
            rl_bookshelfTitle.setVisibility(View.VISIBLE);
            rl_mybooks12.setVisibility(View.GONE);
        } else {
            // 隐藏
            rl_bookshelfTitle.setVisibility(View.VISIBLE);
            rl_mybooks12.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void OnMyBooksNodata(String s) {

    }

    @Override
    public void OnMyBooksFail(String s) {

    }

    // 历史记录
    @Override
    public void OnLB1HistorySuccess(SLB1HistoryBean slb1HistoryBean, String s) {
        historyList = new ArrayList<>();
        historyList = slb1HistoryBean.getList();
        historyAdapter.setNewData(historyList);
//            mAdapter3.notifyDataSetChanged();
        if (historyList.size() > 0) {
            // 显示
            rl_historyTitle.setVisibility(View.VISIBLE);
        } else {
            // 隐藏
            rl_historyTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public void OnLB1HistoryNodata(String s) {

    }

    @Override
    public void OnLB1HistoryFail(String s) {

    }
}
