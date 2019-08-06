package com.geek.libglide47;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.geek.libglide47.base.GlideImageLoader;
import com.geek.libglide47.base.GlideImageView;
import com.geek.libglide47.base.ShapeImageView;
import com.geek.libglide47.base.progress.CircleProgressView;
import com.geek.libglide47.base.progress.OnGlideImageViewListener;
import com.geek.libglide47.base.progress.OnProgressListener;
import com.geek.libglide47.demo.imageutil.SingleImageActivity;
import com.geek.libglide47.demo.imageutil.SingleImagePhotoViewActivity;


public class GlideMainActivityalluse extends AppCompatActivity {

    String url = "https://s3.51cto.com/wyfs02/M02/06/ED/wKiom1nAst7gJXLWAApAOtlw0r4105.jpg";
    private GlideImageView image11;
    private GlideImageView image12;
    private GlideImageView image13;
    private GlideImageView image14;
    private String url1 = "https://s3.51cto.com/wyfs02/M01/89/BA/wKioL1ga-u7QnnVnAAAfrCiGnBQ946_middle.jpg";

    private GlideImageView image21;
    private GlideImageView image22;
    private GlideImageView image23;
    private GlideImageView image24;

    private GlideImageView image31;
    private GlideImageView image32;
    private GlideImageView image33;
    private GlideImageView image34;
    private String gif1 = "https://img.zcool.cn/community/013bce592505d3b5b3086ed49f70e6.gif";

    private GlideImageView image41;
    private CircleProgressView progressView1;
    private String image41BigUrl = "https://raw.githubusercontent.com/wasabeef/art/master/twitter.png";
    private String image41SmallUrl = "https://raw.githubusercontent.com/wasabeef/art/master/twitter.png";

    private GlideImageView image42;
    private CircleProgressView progressView2;
    private String image42BigUrl = "http://wx1.sinaimg.cn/large/60d2c107ly1ftwko7cqwrg20b4069u0x.gif";
    private String image42SmallUrl = "http://wx1.sinaimg.cn/large/60d2c107ly1ftwko7cqwrg20b4069u0x.gif";

    public static boolean isLoadAgain = false; // Just for fun when loading images!


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_main_glide);

        //Glide
        findview();

//        isLoadAgain = new Random().nextInt(3) == 1;
        isLoadAgain = true;

        line1();
        line2();
        line3();
        line41();
        line42();
    }

    private void findview() {
        image11 = findViewById(R.id.image11);
        image12 = findViewById(R.id.image12);
        image13 = findViewById(R.id.image13);
        image14 = findViewById(R.id.image14);

        image21 = findViewById(R.id.image21);
        image22 = findViewById(R.id.image22);
        image23 = findViewById(R.id.image23);
        image24 = findViewById(R.id.image24);

        image31 = findViewById(R.id.image31);
        image32 = findViewById(R.id.image32);
        image33 = findViewById(R.id.image33);
        image34 = findViewById(R.id.image34);

        image41 = findViewById(R.id.image41);
        progressView1 = findViewById(R.id.progressView1);
        image42 = findViewById(R.id.image42);
        progressView2 = findViewById(R.id.progressView2);
    }

    @SuppressLint("CheckResult")
    private void line41() {
        image41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GlideMainActivityalluse.this, SingleImageActivity.class);
                intent.putExtra(SingleImageActivity.KEY_IMAGE_URL, image41BigUrl);
                intent.putExtra(SingleImageActivity.KEY_IMAGE_URL_THUMBNAIL, image41SmallUrl);
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(GlideMainActivityalluse.this, image41, getString(R.string.transitional_image));
                ActivityCompat.startActivity(GlideMainActivityalluse.this, intent, compat.toBundle());
            }
        });

        RequestOptions requestOptions = image41.requestOptions(R.color.placeholder_color).centerCrop();
        if (isLoadAgain) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
        }

        // 第一种方式加载
        image41.load(image41SmallUrl, requestOptions).listener(new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressView1.setProgress(percent);
                progressView1.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });
    }

    private void line42() {
        image42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GlideMainActivityalluse.this, SingleImagePhotoViewActivity.class);
                intent.putExtra(SingleImagePhotoViewActivity.KEY_IMAGE_URL, image42BigUrl);
                intent.putExtra(SingleImagePhotoViewActivity.KEY_IMAGE_URL_THUMBNAIL, image42SmallUrl);
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(GlideMainActivityalluse.this, image42, getString(R.string.transitional_image));
                ActivityCompat.startActivity(GlideMainActivityalluse.this, intent, compat.toBundle());
            }
        });

        RequestOptions requestOptions = image42.requestOptions(R.color.placeholder_color).centerCrop();
        if (isLoadAgain) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
        }

        // 第二种方式加载：可以解锁更多功能
        GlideImageLoader imageLoader = image42.getImageLoader();
        imageLoader.setOnGlideImageViewListener(image42SmallUrl, new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressView2.setProgress(percent);
                progressView2.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });
        imageLoader.requestBuilder(image42SmallUrl, requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image42);
    }

    private void line3() {
//        GlideApp.with(this).downloadOnly().centerCrop().dontAnimate().placeholder(R.drawable.ic_defs_loading).into(image31);
        image31.loadLocalImage(R.drawable.gif_robot_walk, R.drawable.ic_defs_loading);

        image32.loadCircleImage(gif1, R.mipmap.ic_launcher).listener(new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                Log.d("--->image32", "percent: " + percent + " isDone: " + isDone);
            }
        });

        image33.loadImage(gif1, R.drawable.ic_defs_loading);
        image34.loadImage(gif1, R.drawable.ic_defs_loading);
    }

    private void line2() {
        image21.loadImage(url1, R.drawable.ic_defs_loading);
        image22.loadImage("", R.drawable.ic_defs_loading);
        image23.loadImage(url1, R.color.placeholder_color);
        image24.loadImage(url1, R.color.placeholder_color);
    }

    private void line1() {
        image11.loadImage(url1, R.color.black).listener(new OnProgressListener() {
            @Override
            public void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone, GlideException exception) {
                Log.d("--->image11", "bytesRead: " + bytesRead + " totalBytes: " + totalBytes + " isDone: " + isDone);
            }
        });
        image12.setShapeType(ShapeImageView.ShapeType.CIRCLE);
        image12.setBorderWidth(3);
        image12.setBorderColor(R.color.transparent20);
        image12.loadCircleImage(url1, R.color.black);
        image12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GlideMainActivityalluse.this, "image12", Toast.LENGTH_SHORT).show();
            }
        });

        image13.setShapeType(ShapeImageView.ShapeType.RECTANGLE);
        image13.setRadius(15);
        image13.setBorderWidth(3);
        image13.setBorderColor(R.color.blue);
        image13.setPressedAlpha(0.3f);
        image13.setPressedColor(R.color.blue);
        image13.loadImage(url1, R.color.placeholder_color);

        image14.setShapeType(ShapeImageView.ShapeType.CIRCLE);
        image14.setBorderWidth(3);
        image14.setBorderColor(R.color.blue);
        image14.setPressedAlpha(0.2f);
        image14.setPressedColor(R.color.black);
        image14.loadImage(url1, R.color.placeholder_color);

    }


}
