package com.haier.cellarette.baselibrary.assetsfitandroid.fileandroid;

import android.os.Environment;

import com.haier.cellarette.baselibrary.common.BaseApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileAssetsCopySd {

    public static String ImagePath;
    public static String VideoPath;

    public static void fagnfa() {
        new Thread() {
            @Override
            public void run() {
                File imageFile = copyResurces("img/demo1.png", "shiningandgeek_img.png", 0);
                File videoFile = copyResurces("apks/demoapk.apk", "shiningandgeek_test.apk", 0);
                if (imageFile != null) {
                    ImagePath = imageFile.getAbsolutePath();
                }

                if (videoFile != null) {
                    VideoPath = videoFile.getAbsolutePath();
                }

                super.run();
            }
        }.start();
    }

    public static File copyResurces(String src, String dest, int flag) {
        File filesDir = null;
        try {
            if (flag == 0) {//copy to sdcard
                filesDir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/shiningandgeek/" + dest);
                File parentDir = filesDir.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }
            } else {//copy to data
                filesDir = new File(BaseApp.get().getFilesDir(), dest);
            }
            if (!filesDir.exists()) {
                filesDir.createNewFile();
                InputStream open = BaseApp.get().getAssets().open(src);
                FileOutputStream fileOutputStream = new FileOutputStream(filesDir);
                byte[] buffer = new byte[4 * 1024];
                int len = 0;
                while ((len = open.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                }
                open.close();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (flag == 0) {
                filesDir = copyResurces(src, dest, 1);
            }
        }
        return filesDir;
    }
}
