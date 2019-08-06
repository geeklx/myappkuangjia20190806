package com.geek.libglide47.glide48;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
//import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.geek.libglide47.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Glide48ImageLoaderUtils {

    private static Context mContext;

    public static class LxImageLoader implements LXImageLoader {
        @Override
        public void loadImage(@NonNull Object url, @NonNull ImageView imageView) {
            //必须指定Target.SIZE_ORIGINAL，否则无法拿到原图，就无法享用天衣无缝的动画
            Glide.with(imageView)
                    .load(url)
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher_round).override(Target.SIZE_ORIGINAL))
                    .into(imageView);
        }

        @Override
        public File getImageFile(@NonNull Context context, @NonNull Object uri) {
            try {
                return Glide.with(context).downloadOnly().load(uri).submit().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public interface LXImageLoader {
        void loadImage(@NonNull Object uri, @NonNull ImageView imageView);

        /**
         * 获取图片对应的文件
         *
         * @param context
         * @param uri
         * @return
         */
        File getImageFile(@NonNull Context context, @NonNull Object uri);
    }

    //加载图片
    public static void loadImg(final Context context, final LXImageLoader imageLoader, ImageView imageView, final Object uri) {
        imageLoader.loadImage(uri, imageView);// 加载
    }

    // 保存图片到本地相册
    public static void saveBmpToAlbum(final Context context, final LXImageLoader imageLoader, final Object uri) {
        final Handler mainHandler = new Handler(Looper.getMainLooper());
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        mContext = context;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                File source = imageLoader.getImageFile(mContext, uri);
                if (source == null) {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext, "图片不存在！", Toast.LENGTH_SHORT).show();
                            mContext = null;
                        }
                    });
                    return;
                }
                //1. create path
                String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Environment.DIRECTORY_PICTURES;
                File dirFile = new File(dirPath);
                if (!dirFile.exists()) dirFile.mkdirs();
                try {
                    Glide48ImageType type = Glide48ImageHeaderParser.getImageType(new FileInputStream(source));
                    String ext = getFileExt(type);
                    final File target = new File(dirPath, System.currentTimeMillis() + "." + ext);
                    if (target.exists()) target.delete();
                    target.createNewFile();
                    //2. save
                    writeFileFromIS(target, new FileInputStream(source));
                    //3. notify
                    MediaScannerConnection.scanFile(mContext, new String[]{target.getAbsolutePath()},
                            new String[]{"image/" + ext}, new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(final String path, Uri uri) {
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(mContext, "已保存到相册！", Toast.LENGTH_SHORT).show();
                                            mContext = null;
                                        }
                                    });
                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext, "没有保存权限，保存功能无法使用！", Toast.LENGTH_SHORT).show();
                            mContext = null;
                        }
                    });
                }
            }
        });
    }

    private static String getFileExt(Glide48ImageType type) {
        switch (type) {
            case GIF:
                return "gif";
            case PNG:
            case PNG_A:
                return "png";
            case WEBP:
            case WEBP_A:
                return "webp";
            case JPEG:
                return "jpeg";
        }
        return "jpeg";
    }

    private static boolean writeFileFromIS(final File file, final InputStream is) {
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte[] data = new byte[8192];
            int len;
            while ((len = is.read(data, 0, 8192)) != -1) {
                os.write(data, 0, len);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
