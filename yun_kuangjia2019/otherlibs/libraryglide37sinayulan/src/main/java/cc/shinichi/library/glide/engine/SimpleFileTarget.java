package cc.shinichi.library.glide.engine;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import cc.shinichi.sherlockutillibrary.utility.common.Print;

/**
 * @author 工藤
 * @email gougou@16fan.com
 * com.fan16.cn.loader.glide.engine
 * create at 2018/5/17  13:39
 * description:SimpleFileTarget
 */
public class SimpleFileTarget implements Target<File> {

  private static final String TAG = "SimpleFileTarget";

  @Override public void onLoadStarted(Drawable placeholder) {
    Print.d(TAG, "onLoadStarted");
  }

  @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
    if (e != null) {
      Print.d(TAG, "onLoadFailed e--->" + e.toString());
    } else {
      Print.d(TAG, "onLoadFailed");
    }
  }

  @Override
  public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
    Print.d(TAG, "onResourceReady " + resource.getAbsolutePath());
  }

  @Override public void onLoadCleared(Drawable placeholder) {

  }

  @Override public void getSize(SizeReadyCallback cb) {
    cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
  }

  @Override public Request getRequest() {
    return null;
  }

  @Override public void setRequest(Request request) {

  }

  @Override public void onStart() {

  }

  @Override public void onStop() {

  }

  @Override public void onDestroy() {

  }
}