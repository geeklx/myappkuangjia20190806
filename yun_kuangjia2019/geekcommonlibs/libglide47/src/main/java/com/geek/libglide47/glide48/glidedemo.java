package com.geek.libglide47.glide48;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.geek.libglide47.R;
import com.geek.libglide47.base.progress.GlideApp;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.lxj.xpermission.PermissionConstants;
import com.lxj.xpermission.XPermission;

import java.io.File;
import java.util.UUID;

//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.annotation.RequiresApi;

public class glidedemo {

    public void setglide1(Context context, ImageView imageView, String url) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.ic_defs_loading)
                .into(imageView);
    }

    public void setglide2(Context context, ImageView imageView, String url) {
        GlideApp.with(context)
                .load(url)
                .skipMemoryCache(true)
                .fitCenter()
                .placeholder(R.drawable.ic_defs_loading)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    public void setglide3(Context context, ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .signature(new ObjectKey(UUID.randomUUID().toString()))  // 重点在这行
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.ic_defs_loading)
                .error(R.drawable.ic_defs_loading)
                .fallback(R.drawable.ic_defs_loading); //url为空的时候,显示的图片;
//        if (FileUtils.isFileExists(CommonUtils.img_file_url + CommonUtils.img_file_name)) {
//            File file = new File(CommonUtils.img_file_url + CommonUtils.img_file_name);
//            Glide.with(context).load(file)
//                    .apply(options)
//                    .into(imageView);
//        } else {
//            Glide.with(context).load(SPUtils.getInstance().getString(CommonUtils.USER_NAME))
//                    .apply(options)
//                    .into(imageView);
//        }
    }

    public void setglide4(Context context, ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.ic_defs_loading)
                .error(R.drawable.ic_defs_loading)
                .override(Target.SIZE_ORIGINAL)
                .transform(new RoundedCorners(50))
                .fallback(R.drawable.ic_defs_loading); //url为空的时候,显示的图片;
        Glide.with(context).load(url).apply(options).into(imageView);

    }

    public void setglide5(Context context, ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.ic_defs_loading)
                .error(R.drawable.ic_defs_loading)
                .override(Target.SIZE_ORIGINAL)
                .transform(new RoundedCorners(50))
                .fallback(R.drawable.ic_defs_loading); //url为空的时候,显示的图片;
        Glide.with(context).load(url).apply(options).into(imageView);

    }

    public void setglide6(Context context, ImageView imageView, String url) {
        //1. 加载图片, 由于ImageView是centerCrop，必须指定Target.SIZE_ORIGINAL，禁止Glide裁剪图片；
        // 这样我就能拿到原始图片的Matrix，才能有完美的过渡效果
        Glide.with(imageView).load(url).apply(new RequestOptions().placeholder(R.drawable.ic_defs_loading)
                .override(Target.SIZE_ORIGINAL))
                .into(imageView);
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public void setglide7(Context context, ImageView imageView, String url) {
        // 加载本地图片
        File file = new File(context.getApplicationContext().getExternalCacheDir() + "/image.jpg");
        Glide.with(context).load(file).into(imageView);

        // 加载应用资源
        int resource = R.drawable.ic_defs_loading;
        Glide.with(context).load(resource).into(imageView);

        // 加载二进制流
        byte[] image = new byte[]{};
        Glide.with(context).load(image).into(imageView);

        // 加载Uri对象
        Uri imageUri = null;
        Glide.with(context).load(imageUri).into(imageView);

        // 加载圆形图
//        Glide.with(context)
//                .load(R.drawable.ic_defs_loading)
//                .apply(bitmapTransform(new CropCircleTransformation()))
//                .into(imageView);

        // 模糊过滤
//        Glide.with(context)
//                .load(R.drawable.ic_def_loading)
//                .apply(bitmapTransform(new BlurTransformation(25, 4)))
//                .into(imageView);

    }

    public void setglide8(Context context, ImageView imageView, String url) {
        Glide.with(context).downloadOnly().load(url).addListener(new RequestListener<File>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
//                progressBar.setVisibility(View.GONE);
                String errorMsg = "加载失败";
                if (e != null) {
                    errorMsg = errorMsg.concat(":\n").concat(e.getMessage());
                }
                if (errorMsg.length() > 200) {
                    errorMsg = errorMsg.substring(0, 199);
                }
//                ToastUtil.getInstance()._short(context.getApplicationContext(), errorMsg);
                return false;
            }

            @Override
            public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                String imagePath = resource.getAbsolutePath();
//                boolean isLongImage = ImageUtil.isLongImage(imagePath);
//                Print.d(TAG, "isLongImage = " + isLongImage);
//                if (isLongImage) {
//                    view.setOrientation(ImageUtil.getOrientation(imagePath));
//                    view.setMinimumScaleType(SubsamplingScaleImageViewDragClose.SCALE_TYPE_START);
//                }
//                view.setImage(ImageSource.uri(Uri.fromFile(new File(resource.getAbsolutePath()))));
//                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(new CommonTarget<File>() {
            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                super.onLoadStarted(placeholder);
//                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    public void setglide9(Context context, ImageView imageView, String url) {
        Glide.with(context).downloadOnly().load(url).into(new CommonTarget<File>() {
            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                super.onLoadStarted(placeholder);
                MyLogUtil.e("--glide48-CommonTarget-", "开始下载...");
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                MyLogUtil.e("--glide48-CommonTarget-", "保存失败");
            }

            @Override
            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                super.onResourceReady(resource, transition);
                MyLogUtil.e("--glide48-CommonTarget-", "成功保存");

            }
        });

    }

    // 完整的加载 保存到本地过程

    public void setglide10(final Context context, ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            url = "https://s2.51cto.com/wyfs02/M01/89/BA/wKioL1ga-u7QnnVnAAAfrCiGnBQ946_middle.jpg";
        }
        final Glide48ImageLoaderUtils.LxImageLoader lxImageLoader = new Glide48ImageLoaderUtils.LxImageLoader();
        Glide48ImageLoaderUtils.loadImg(context, lxImageLoader, imageView, url);
        //check permission
        final String finalUrl = url;
        XPermission.create(context, PermissionConstants.STORAGE)
                .callback(new XPermission.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        //save bitmap to album.
                        Glide48ImageLoaderUtils.saveBmpToAlbum(context, lxImageLoader, finalUrl);// 保存
                    }

                    @Override
                    public void onDenied() {
                        Toast.makeText(context, "没有保存权限，保存功能无法使用！", Toast.LENGTH_SHORT).show();
                    }
                }).request();
    }


}
