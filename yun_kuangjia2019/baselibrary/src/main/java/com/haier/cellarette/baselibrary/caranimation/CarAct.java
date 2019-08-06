package com.haier.cellarette.baselibrary.caranimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

public class CarAct extends Activity {

    private TextView tv1;
    private CartAnim cartAnim;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_animation);
        tv1 = findViewById(R.id.tv1);
        cartAnim = CartAnim.obtain(CarAct.this);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                cartAnim.start(i + "", tv1, new float[]{-200.f, -40.f}, new long[]{600, 500, 300});

            }
        });
    }

    @Override
    protected void onDestroy() {
        cartAnim.cancel();
        super.onDestroy();

    }
}
