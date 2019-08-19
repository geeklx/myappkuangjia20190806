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
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
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
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment4.uploadimg.Fragment4UploadImgUtils;
import com.example.slbappshare.fenxiang.JPushShareUtils;
import com.example.slbappshare.fenxiang.OnShareResultInfoLitener;
import com.geek.libglide47.base.GlideImageView;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zhy.base.fileprovider.FileProvider7;

import java.io.File;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class FragmentContent4New2 extends SlbBaseFragment implements View.OnClickListener, SGetUserInfoView, SUploadUserImgView {

    private String tablayoutId;
    private Context mContext;
    private TextView tv1;// 访客登录
    private TextView tv3;//
    private TextView tv4;//
    private RelativeLayout rlvip1;//
    private RelativeLayout rl3;//
    private RelativeLayout rl1;//
    //    private CustomImageView ivCenter;
//    private UploadImgCircleImageView ivCenter;
    private GlideImageView ivCenter;
    //    private UploadImgCircleImageView headImage1;//头像1

    private SUploadUserImgPresenter presenterImg;
    private SGetUserInfoPresenter presenter;
    private JPushShareUtils jPushShareUtils;

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
    protected int getLayoutId() {
        return R.layout.activity_fragment4new2;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        // 伸缩的视图
        initsmart(rootView);
        presenter = new SGetUserInfoPresenter();
        presenter.onCreate(this);

        presenterImg = new SUploadUserImgPresenter();
        presenterImg.onCreate(this);

        initData();

    }

    private int mOffset = 0;
    private int mScrollY = 0;
    private View parallax;
    private View buttonBar;
    private NestedScrollView scrollView;
    private RefreshLayout refreshLayout;
    private ClassicsHeader smart_header1;
    private Toolbar toolbar;

    private void initsmart(View rootView) {
        ivCenter = rootView.findViewById(R.id.iv_center);
        ivCenter.setOnClickListener(this);
        tv1 = rootView.findViewById(R.id.tv1);
        tv3 = rootView.findViewById(R.id.tv3);
        tv4 = rootView.findViewById(R.id.tv4);
        rlvip1 = rootView.findViewById(R.id.rlvip1);
        rlvip1.setOnClickListener(this);
        rl3 = rootView.findViewById(R.id.rl3);
        rl3.setOnClickListener(this);
        rl1 = rootView.findViewById(R.id.rl1);
        rl1.setOnClickListener(this);
        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
            }
        });


        //状态栏透明和间距处理
//        StatusBarUtilV7.immersive(getActivity());
//        StatusBarUtilV7.setPaddingSmart(getActivity(), toolbar);

        parallax = rootView.findViewById(R.id.parallax);
        buttonBar = rootView.findViewById(R.id.buttonBarLayout);
        scrollView = rootView.findViewById(R.id.scrollView);
        refreshLayout = rootView.findViewById(R.id.refreshLayout);
        smart_header1 = rootView.findViewById(R.id.smart_header1);
        smart_header1.setEnableLastTime(false);
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = offset / 2;
                parallax.setTranslationY(mOffset - mScrollY);
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = offset / 2;
                parallax.setTranslationY(mOffset - mScrollY);
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            private int lastScrollY = 0;
            private int h = DensityUtil.dp2px(170);
            private int color = ContextCompat.getColor(getActivity(), R.color.colorPrimary) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    buttonBar.setAlpha(1f * mScrollY / h);
                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    parallax.setTranslationY(mOffset - mScrollY);
                }
                lastScrollY = scrollY;
            }
        });
        buttonBar.setAlpha(0);
//        buttonBar.setVisibility(View.GONE);
        toolbar.setBackgroundColor(0);
        //状态栏透明和间距处理
//        StatusBarUtilV7.immersive(getActivity());
//        StatusBarUtilV7.setMargin(getActivity(), smart_header1);
//        StatusBarUtil.setPaddingSmart(getActivity(), root.findViewById(R.id.toolbar));
//        StatusBarUtilV7.setPaddingSmart(getActivity(), scrollView);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                // 业务逻辑bufen
                retryData();
            }
        });
        //
        jPushShareUtils = new JPushShareUtils(new OnShareResultInfoLitener() {
            @Override
            public void onResults(String platform, String toastMsg, String data) {
                // 分享回调bufen
                Toast.makeText(getActivity(), toastMsg, Toast.LENGTH_LONG).show();
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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestory();
        presenterImg.onDestory();
    }


    /**
     * 第一次进来加载bufen
     */
    public void initData() {

//        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();

        // 从缓存中拿出头像bufen
//        if (FileUtils.isFileExists(CommonUtils.img_file_url + CommonUtils.img_file_name)) {
//            Bitmap bitMap = BitmapFactory.decodeFile(CommonUtils.img_file_url + CommonUtils.img_file_name);
//            if (bitMap != null) {
//                ivCenter.setImageBitmap(bitMap);
//            }
//        }
        MyLogUtil.e("--sssss--", "initData");
        // 接口bufen
        if (is_first) {
            is_first = false;
            refreshLayout.autoRefresh();
        }

    }

    private boolean is_first = true;


    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(final String cateId, boolean isrefresh) {

        if (!isrefresh) {
            retryData();
            return;
        }
        // 接口bufen
        refreshLayout.autoRefresh();
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
        if (i == R.id.iv_center) {
            //上传头像bufen
//            Toasty.normal(getActivity(), "上传头像").show();
            if (!SlbLoginUtil2.get().isUserLogin()) {
                startActivity(new Intent("hs.act.slbapp.SlbLoginActivity"));
                return;
            }
//            if (TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_NAME))) {
//                startActivity(new Intent("hs.act.slbapp.SlbLoginInfoActivity"));
//                return;
//            }
//            if (!FileUtils.createOrExistsFile(CommonUtils.img_file_url + CommonUtils.img_file_name)) {
//                return;
//            }
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

        } else if (i == R.id.rlvip1) {
            SlbLoginUtil2.get().loginTowhere(getActivity(), new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent("hs.act.slbapp.SlbLoginInfoActivity"));
                }
            });
        } else if (i == R.id.rl3) {
            if (!NetworkUtils.isConnected()) {
                Toasty.normal(getActivity(), "网络异常，请检查网络连接！").show();
                return;
            }
            HiosHelper.resolveAd(getActivity(), getActivity(), "http://hexiang-app.sairobo.cn/user-notice/about-us.html");

        } else if (i == R.id.rl1) {
            startActivity(new Intent("hs.act.slbapp.Fragment4SettingActivityNew1"));
        } else {
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

                    ivCenter.loadImage(cropImagePath, R.drawable.ic_def_loading);

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
        tv1.setText(TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_NAME)) ?
                (TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_TEL)) ? "访客" : SPUtils.getInstance().getString(CommonUtils.USER_TEL)) : SPUtils.getInstance().getString(CommonUtils.USER_NAME));
        tv3.setText(sLoginUserInfoBean.getPbUser().getReadBookCount());
        tv4.setText(sLoginUserInfoBean.getPbUser().getReadDuration());
//        Glide.with(this).load(sAppUserInfoBean.getUserInfo().getAvatar()).into(ivCenter);
        LoginImgUtils.getInstance(getActivity().getApplicationContext()).get_head_icon(ivCenter);
    }

    @Override
    public void OnGetUserInfoNodata(String s) {
        refreshLayout.finishRefresh(false);

    }


    @Override
    public void OnGetUserInfoFail(String s) {
        refreshLayout.finishRefresh(false);
        Toasty.normal(Objects.requireNonNull(getActivity()), "请检查网络是否正常").show();
    }

    // 上传头像bufen
    @Override
    public void OnUploadUserImgSuccess(SUserImgBean bean) {
        SPUtils.getInstance().put(CommonUtils.USER_IMG, bean.getUrl());
        LoginImgUtils.getInstance(getActivity().getApplicationContext()).get_head_icon(ivCenter);
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
}
