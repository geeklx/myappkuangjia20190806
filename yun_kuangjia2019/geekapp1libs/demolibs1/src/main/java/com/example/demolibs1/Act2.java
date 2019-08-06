package com.example.demolibs1;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.jessyan.autosize.AutoSizeCompat;

public class Act2 extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actmain2);
        tv1 = findViewById(R.id.tv1111);
        tv2 = findViewById(R.id.tv2222);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                tv2.setText("我是BUG");
                tv2.setText("我是修改过的BUG 云");
            }
        });
    }
}
