package com.haier.cellarette.libglide37.glide;

import android.app.Fragment;
import android.content.Context;
//import androidx.core.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;

public class GlideUtil {

    public static void display(Context context, final ImageView imageView, final String url) {
        display(context, imageView, url, GlideOptionsFactory.get(GlideOptionsFactory.Type.DEFAULT));
    }

    public static void display(Context context, final ImageView imageView, final String url, GlideOptions op) {
        if (context == null) {
            return;
        }
        if (context instanceof AppCompatActivity) {
            display((AppCompatActivity) context, imageView, url, op);
            return;
        }

        DrawableRequestBuilder<?> builder = Glide.with(context).load(url);
        display(builder, imageView, op);
    }

    public static void display(AppCompatActivity activity, final ImageView imageView, final String url) {
        display(activity, imageView, url, GlideOptionsFactory.get(GlideOptionsFactory.Type.DEFAULT));
    }

    public static void display(AppCompatActivity activity, final ImageView imageView, final String url, GlideOptions op) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        DrawableRequestBuilder<?> builder = Glide.with(activity).load(url);
        display(builder, imageView, op);
    }

    public static void display(Fragment fragment, final ImageView imageView, final String url) {
        display(fragment, imageView, url, GlideOptionsFactory.get(GlideOptionsFactory.Type.DEFAULT));
    }

    public static void display(Fragment fragment, final ImageView imageView, final String url, GlideOptions op) {
        if (fragment == null || fragment.isRemoving() || fragment.getActivity() == null) {
            return;
        }
        DrawableRequestBuilder<?> builder = Glide.with(fragment).load(url);
        display(builder, imageView, op);
    }

    public static void display(final DrawableRequestBuilder<?> builder, final ImageView imageView, GlideOptions op) {
//        imageView.setImageResource(op.getReplaceImage());
        builder.placeholder(op.getReplaceImage()).error(op.getReplaceImage());
        builder.dontAnimate();//.crossFade();
        builder.diskCacheStrategy(DiskCacheStrategy.RESULT);
        BitmapTransformation bt = new CenterCrop(imageView.getContext());//new TransformerFactory(imageView.getContext());
        if (op.getRadius() == 0) {
            builder.bitmapTransform(bt);
        } else {
            builder.bitmapTransform(bt, new RoundedCornersTransformation(imageView.getContext(),
                    op.getRadius(), 0));
        }

        ViewGroup.LayoutParams p = imageView.getLayoutParams();
        int width = imageView.getMeasuredWidth();
        int height = imageView.getMeasuredHeight();
        if (p != null && p.width > 0 && p.height > 0) {
            width = p.width;
            height = p.height;
        }

        if (width == 0 || height == 0) {
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    MyLogUtil.v("glide", "delay display...");
                    builder.into(imageView);
                }
            });
        } else {
            MyLogUtil.v("glide", "display...");
            builder.into(imageView);
        }
    }

    public static void clearMemoryCache(Context ctx) {
        Glide.get(ctx).clearMemory();
    }

    public static void clearDiskCache(Context ctx) {
        Glide.get(ctx).clearDiskCache();
    }
}
