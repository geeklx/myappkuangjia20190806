package com.example.slbappindex.fragment.fragment1.popcommpart;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappindex.R;

import java.util.ArrayList;
import java.util.List;

public class PopCommActivity extends SlbBaseActivity implements View.OnClickListener {

    public static int requestCodes = 10101;
    public static int resultCodes = 10102;
    private String ageRange = "-1";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pop_comm;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        RecyclerView recycler_view1 = findViewById(R.id.recycler_view1);
        recycler_view1.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));
        final PopCommAdapter popCommAdapter = new PopCommAdapter(R.layout.pop_comm_item);
        recycler_view1.setAdapter(popCommAdapter);

        ageRange = getIntent().getStringExtra("ageRange");
        List<PopComm_ListBean> mList11 = new ArrayList<>();
        final String[] headers = {"0-3岁", "4-6岁", "7岁以上", "全部年龄"};
        mList11.add(new PopComm_ListBean("0", headers[0], 0, 0, false));
        mList11.add(new PopComm_ListBean("4", headers[1], 0, 0, false));
        mList11.add(new PopComm_ListBean("7", headers[2], 0, 0, false));
        mList11.add(new PopComm_ListBean("-1", headers[3], 0, 0, false));
        for (PopComm_ListBean bean1 : mList11) {
            if (bean1.getText_id().equals(ageRange)) {
                bean1.setEnselect(true);
            }
        }
        popCommAdapter.setNewData(mList11);
//        popCommAdapter.setCheckItem(mList11.get(mList11.size() - 1).getText_id());
        popCommAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                final PopComm_ListBean bean = (PopComm_ListBean) adapter.getData().get(position);
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popCommAdapter.setCheckItem(bean.getText_id());
                    }
                }, 1800);
//                Toasty.normal(PopCommActivity.this, bean.getText_content()).show();
                Intent intent = new Intent();
                intent.putExtra("bean", bean);
                setResult(resultCodes, intent);
                finish();

            }
        });

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
//        if (i == R.id.iv_del) {
//            onBackPressed();
//        }  else {
//
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */


}
