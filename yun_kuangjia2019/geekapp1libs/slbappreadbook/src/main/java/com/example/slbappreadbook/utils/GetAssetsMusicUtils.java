package com.example.slbappreadbook.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class GetAssetsMusicUtils implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private static volatile GetAssetsMusicUtils instance;
    private static Context mContext;

    private GetAssetsMusicUtils(Context context) {
        mContext = context;
    }

    public static GetAssetsMusicUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (GetAssetsMusicUtils.class) {
                instance = new GetAssetsMusicUtils(context);
            }
        }
        return instance;
    }

    public MediaPlayer mediaPlayer;

    /**
     * 获取assets/xx目录下的json文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    /**
     * 将Json字符串转换为对象
     *
     * @param json
     * @param type
     * @return
     */
    public static <T> T JsonToObject(String json, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * 将对象转换为字符串
     *
     * @param type
     * @return
     */
    public static String ObjectToJson(Object type) {
        Gson gson = new Gson();
        return gson.toJson(type);
    }

    /**
     * 获取assets下json文件的路径
     *
     * @param context
     * @param assets_file_name
     * @return
     */
    public static String get_assets_content(Context context, String assets_file_name) throws IOException {
        return new String(InputStreamToByte(getAssetsInput(context, assets_file_name)));
    }

    /**
     * 传入assets下文件名字获取InputStream
     *
     * @param context
     * @param assets_file_name
     * @return
     */
    public static InputStream getAssetsInput(Context context, String assets_file_name) throws IOException {
        AssetManager assset = context.getAssets();
        InputStream inputStream = null;
        inputStream = assset.open(assets_file_name);
        return inputStream;
    }

    public static byte[] InputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            bytestream.write(ch);
        }
        byte[] imgdata = bytestream.toByteArray();
        bytestream.close();
        return imgdata;

    }

    /**
     * 获取assets/xx路径下mp3 获取raw路径下MP3
     * assets:   "mp3/demo2.mp3"
     * raw:      Uri.parse("android.resource://" + mContext.getPackageName() + "/" +uri)
     *
     * @param context
     * @param assets_or_raw
     * @param uri_or_assetsurl
     */
    public void playMusic(Context context, boolean assets_or_raw, String uri_or_assetsurl) {
        Log.e("playMusic:", "playMusic");
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.reset();
//                initMediaPlayer();
            }
            if (assets_or_raw) {
                mediaPlayer.setDataSource(context, Uri.parse(uri_or_assetsurl));
            } else {
                AssetManager assetManager = context.getAssets();
                AssetFileDescriptor fileDescriptor = assetManager.openFd(uri_or_assetsurl);
                mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());// 设置数据源
            }
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e("playMusic:error:", e.toString());
            e.printStackTrace();
        }

//        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);

    }

    public void setDataPlayer(Context context,String uri_or_assetsurl){
        try {
            mediaPlayer.setDataSource(context, Uri.parse(uri_or_assetsurl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MusicDestory() {
        if (null != mediaPlayer) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.e("playMusic:", "start");
//        mediaPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.e("playMusic:", "onCompletion");
        MusicDestory();
    }

}
