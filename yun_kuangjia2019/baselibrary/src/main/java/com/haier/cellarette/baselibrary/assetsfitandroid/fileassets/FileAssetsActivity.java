package com.haier.cellarette.baselibrary.assetsfitandroid.fileassets;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.assetsfitandroid.AssetsDemoBean;


import java.io.IOException;

public class FileAssetsActivity extends AppCompatActivity {

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assetsfiles);
        tv1 = findViewById(R.id.tv1);

    }

    // 打开ASSETS中的TXT2
    public void click_txt(View view) {
        String txt_url = GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).getJson(this, "txt/demo1.txt");
        tv1.setText(txt_url);
    }

    // 打开ASSETS中的GSON.JSON2
    public void click_gson(View view) {
        //测试gjson 获取assets区域的文件 bufen
        String gson_url = GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).getJson(this, "jsonbean/demobean2.json");
        AssetsDemoBean bean_gson = GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).JsonToObject(gson_url, AssetsDemoBean.class);
        tv1.setText(GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).ObjectToJson(bean_gson));
        Log.e("--geek--bean_gson--", bean_gson + "");
    }

    // 打开ASSETS中的FAST.JSON2
    public void click_fastjson(View view) {
        //测试fastjson 获取assets区域的文件 bufen
        try {
            String assets_content = GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).get_assets_content(this,"jsonbean/demobean22.json");
            AssetsDemoBean bean_fastjson = JSON.parseObject(assets_content, AssetsDemoBean.class);
            tv1.setText(JSON.toJSONString(bean_fastjson));
            Log.e("--geek--bean_fastjson--", bean_fastjson + "");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 打开ASSETS中的MP3
    public void click_mp3(View view) {
//        assets:   "mp3/demo2.mp3"
//        raw:      "android.resource://" + mContext.getPackageName() + "/" +uri
        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).playMusic(this, false, "mp3/demo.mp3");
//        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).playMusic(this, true, "android.resource://" + getPackageName() + "/" + R.raw.demo2);

    }

    // 打开RAW中的MP3
    public void click_mp32(View view) {
//        assets:   "mp3/demo2.mp3"
//        raw:      "android.resource://" + mContext.getPackageName() + "/" +uri
//        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).playMusic(this, false, "mp3/demo2.mp3");
        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).playMusic(this, true, "android.resource://" + getPackageName() + "/" + R.raw.demo);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).MusicDestory();
    }
}
