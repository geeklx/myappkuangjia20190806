package cc.shinichi.library.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
//import android.support.annotation.NonNull;
//import androidx.core.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.R;
import cc.shinichi.library.bean.ImageInfo;
import cc.shinichi.library.glide.ImageLoader;
import cc.shinichi.library.glide.engine.SimpleFileTarget;
import cc.shinichi.library.view.helper.FingerDragHelper;
import cc.shinichi.library.view.helper.ImageSource;
import cc.shinichi.library.view.helper.SubsamplingScaleImageViewDragClose;
import cc.shinichi.sherlockutillibrary.utility.common.NetworkUtil;
import cc.shinichi.sherlockutillibrary.utility.common.Print;
import cc.shinichi.sherlockutillibrary.utility.image.ImageUtil;

public class ImagePreviewAdapter extends PagerAdapter {

  private static final String TAG = "ImagePreview";
  private Activity activity;
  private List<ImageInfo> imageInfo;
  private HashMap<String, SubsamplingScaleImageViewDragClose> imageHashMap = new HashMap<>();
  private String finalLoadUrl = "";// 最终加载的图片url
  private int phoneHeight = 0;

  public ImagePreviewAdapter(Activity activity, @NonNull List<ImageInfo> imageInfo) {
    super();
    this.imageInfo = imageInfo;
    this.activity = activity;
    WindowManager windowManager = (WindowManager) activity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics metric = new DisplayMetrics();
    windowManager.getDefaultDisplay().getMetrics(metric);
    this.phoneHeight = metric.heightPixels;
  }

  public void closePage() {
    try {
      if (imageHashMap != null && imageHashMap.size() > 0) {
        for (Object o : imageHashMap.entrySet()) {
          Map.Entry entry = (Map.Entry) o;
          if (entry != null && entry.getValue() != null) {
            ((SubsamplingScaleImageViewDragClose) entry.getValue()).recycle();
            Glide.clear((SubsamplingScaleImageViewDragClose) entry.getValue());
          }
        }
        imageHashMap.clear();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override public int getCount() {
    return imageInfo.size();
  }

  /**
   * 加载原图
   */
  public void loadOrigin(final ImageInfo imageInfo) {
    if (imageHashMap.get(imageInfo.getOriginUrl()) != null) {
      final SubsamplingScaleImageViewDragClose imageView = imageHashMap.get(imageInfo.getOriginUrl());
      File cacheFile = ImageLoader.getGlideCacheFile(activity, imageInfo.getOriginUrl());
      if (cacheFile != null && cacheFile.exists()) {
        String thumbnailUrl = imageInfo.getThumbnailUrl();
        File smallCacheFile = ImageLoader.getGlideCacheFile(activity, thumbnailUrl);
        ImageSource small = null;
        if (smallCacheFile != null && smallCacheFile.exists()) {
          String smallImagePath = smallCacheFile.getAbsolutePath();
          small = ImageSource.bitmap(ImageUtil.getImageBitmap(smallImagePath, ImageUtil.getBitmapDegree(smallImagePath)));
          int widSmall = ImageUtil.getWidthHeight(smallImagePath)[0];
          int heiSmall = ImageUtil.getWidthHeight(smallImagePath)[1];
          small.dimensions(widSmall, heiSmall);
        }

        String imagePath = cacheFile.getAbsolutePath();
        ImageSource origin = ImageSource.uri(imagePath);
        int widOrigin = ImageUtil.getWidthHeight(imagePath)[0];
        int heiOrigin = ImageUtil.getWidthHeight(imagePath)[1];
        origin.dimensions(widOrigin, heiOrigin);

        boolean isLongImage = ImageUtil.isLongImage(imagePath);
        Print.d(TAG, "isLongImage = " + isLongImage);
        if (isLongImage) {
          imageView.setOrientation(ImageUtil.getOrientation(imagePath));
          imageView.setMinimumScaleType(SubsamplingScaleImageViewDragClose.SCALE_TYPE_START);
        }
        imageView.setImage(origin, small);
      }
    } else {
      notifyDataSetChanged();
    }
  }

  @NonNull @Override
  public Object instantiateItem(@NonNull ViewGroup container, final int position) {
    View convertView = View.inflate(activity, R.layout.item_photoview, null);
    final ProgressBar progressBar = convertView.findViewById(R.id.progress_view);
    final SubsamplingScaleImageViewDragClose imageView = convertView.findViewById(R.id.photo_view);
    final FingerDragHelper fingerDragHelper = convertView.findViewById(R.id.fingerDragHelper);

    imageView.setDoubleTapZoomDuration(ImagePreview.getInstance().getZoomTransitionDuration());
    imageView.setMinScale(ImagePreview.getInstance().getMinScale());
    imageView.setMaxScale(ImagePreview.getInstance().getMaxScale());
    imageView.setDoubleTapZoomScale(ImagePreview.getInstance().getMediumScale());

    if (ImagePreview.getInstance().isEnableClickClose()) {
      imageView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          activity.finish();
        }
      });
    }

    if (ImagePreview.getInstance().isEnableDragClose()) {
      fingerDragHelper.setOnAlphaChangeListener(new FingerDragHelper.onAlphaChangedListener() {
        @Override public void onTranslationYChanged(MotionEvent event, float translationY) {
          float yAbs = Math.abs(translationY);
          float percent = yAbs / phoneHeight;
          float number = 1.0F - percent;

          if (activity instanceof ImagePreviewActivity) {
            ((ImagePreviewActivity) activity).setAlpha(number);
          }

          imageView.setScaleX(number);
          imageView.setScaleY(number);
        }
      });
    }

    final ImageInfo info = this.imageInfo.get(position);
    final String originPathUrl = info.getOriginUrl();
    final String thumbPathUrl = info.getThumbnailUrl();

    finalLoadUrl = thumbPathUrl;
    ImagePreview.LoadStrategy loadStrategy = ImagePreview.getInstance().getLoadStrategy();

      imageHashMap.remove(originPathUrl);
    imageHashMap.put(originPathUrl, imageView);

    File cacheFile = ImageLoader.getGlideCacheFile(activity, originPathUrl);
    if (cacheFile != null && cacheFile.exists()) {
      Glide.with(activity).load(cacheFile).downloadOnly(new SimpleFileTarget() {
        @Override
        public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
          super.onResourceReady(resource, glideAnimation);
          String imagePath = resource.getAbsolutePath();
          boolean isLongImage = ImageUtil.isLongImage(imagePath);
          Print.d(TAG, "isLongImage = " + isLongImage);
          if (isLongImage) {
            imageView.setOrientation(ImageUtil.getOrientation(imagePath));
            imageView.setMinimumScaleType(SubsamplingScaleImageViewDragClose.SCALE_TYPE_START);
          }
          imageView.setImage(ImageSource.uri(Uri.fromFile(new File(resource.getAbsolutePath()))));
          progressBar.setVisibility(View.GONE);
        }
      });
    } else {
      // 根据当前加载策略判断，需要加载的url是哪一个
      if (loadStrategy == ImagePreview.LoadStrategy.Default) {
        finalLoadUrl = thumbPathUrl;
      } else if (loadStrategy == ImagePreview.LoadStrategy.AlwaysOrigin) {
        finalLoadUrl = originPathUrl;
      } else if (loadStrategy == ImagePreview.LoadStrategy.AlwaysThumb) {
        finalLoadUrl = thumbPathUrl;
      } else if (loadStrategy == ImagePreview.LoadStrategy.NetworkAuto) {
        if (NetworkUtil.isWiFi(activity)) {
          finalLoadUrl = originPathUrl;
        } else {
          finalLoadUrl = thumbPathUrl;
        }
      }
      finalLoadUrl = finalLoadUrl.trim();
      Print.d(TAG, "finalLoadUrl == " + finalLoadUrl);

      Glide.with(activity).load(finalLoadUrl).downloadOnly(new SimpleFileTarget() {
        @Override public void onLoadStarted(Drawable placeholder) {
          super.onLoadStarted(placeholder);
          progressBar.setVisibility(View.VISIBLE);
        }

        @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
          super.onLoadFailed(e, errorDrawable);
          // glide会有时加载失败，这不是本框架的问题，具体看：https://github.com/bumptech/glide/issues/2894
          Glide.with(activity).load(finalLoadUrl).downloadOnly(new SimpleFileTarget() {
            @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
              super.onLoadFailed(e, errorDrawable);
              progressBar.setVisibility(View.GONE);
            }

            @Override public void onResourceReady(File resource,
                GlideAnimation<? super File> glideAnimation) {
              String imagePath = resource.getAbsolutePath();
              boolean isLongImage = ImageUtil.isLongImage(imagePath);
              Print.d(TAG, "isLongImage = " + isLongImage);
              if (isLongImage) {
                imageView.setOrientation(ImageUtil.getOrientation(imagePath));
                imageView.setMinimumScaleType(SubsamplingScaleImageViewDragClose.SCALE_TYPE_START);
              }
              imageView.setImage(
                  ImageSource.uri(Uri.fromFile(new File(resource.getAbsolutePath()))));
              progressBar.setVisibility(View.GONE);
            }
          });
        }

        @Override
        public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
          String imagePath = resource.getAbsolutePath();
          boolean isLongImage = ImageUtil.isLongImage(imagePath);
          Print.d(TAG, "isLongImage = " + isLongImage);
          if (isLongImage) {
            imageView.setOrientation(ImageUtil.getOrientation(imagePath));
            imageView.setMinimumScaleType(SubsamplingScaleImageViewDragClose.SCALE_TYPE_START);
          }
          imageView.setImage(ImageSource.uri(Uri.fromFile(new File(resource.getAbsolutePath()))));
          progressBar.setVisibility(View.GONE);
        }
      });
    }
    container.addView(convertView);
    return convertView;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    try {
      container.removeView((View) object);
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      ImageLoader.clearMemory(activity);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override public void setPrimaryItem(ViewGroup container, int position, final Object object) {
    super.setPrimaryItem(container, position, object);
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public int getItemPosition(Object object) {
    return POSITION_NONE;
  }
}