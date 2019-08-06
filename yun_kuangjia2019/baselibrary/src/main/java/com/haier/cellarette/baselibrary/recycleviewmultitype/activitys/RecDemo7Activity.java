package com.haier.cellarette.baselibrary.recycleviewmultitype.activitys;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo7.ItemDemo7;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo7.ItemDemo7ContentImg;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo7.ItemDemo7ContentText;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo7.ItemDemo7User;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo7.gson.WeiboJsonParser;
import com.haier.cellarette.baselibrary.recycleviewmultitype.viewholders.demo7.ItemDemo7SimpleImageViewBinder;
import com.haier.cellarette.baselibrary.recycleviewmultitype.viewholders.demo7.ItemDemo7SimpleTextViewBinder;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.Linker;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.MultiTypeAsserts;

public class RecDemo7Activity extends AppCompatActivity {

    private MultiTypeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Items items;//Items 等同于 ArrayList<Object>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_demo7);
        mRecyclerView = findViewById(R.id.list);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(OrientationHelper.VERTICAL);
//        mRecyclerView.setLayoutManager(layoutManager);
//        new LinearSnapHelper().attachToRecyclerView(mRecyclerView);

        mAdapter = new MultiTypeAdapter();
        mAdapter.register(ItemDemo7.class).to(
                new ItemDemo7SimpleTextViewBinder(),
                new ItemDemo7SimpleImageViewBinder()
        ).withLinker(new Linker<ItemDemo7>() {
            @Override
            public int index(int position, @NonNull ItemDemo7 itemDemo7) {
                if (itemDemo7.getContent() instanceof ItemDemo7ContentText) {
                    return 0;
                } else if (itemDemo7.getContent() instanceof ItemDemo7ContentImg) {
                    return 1;
                }
                return 0;
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        MultiTypeAsserts.assertHasTheSameAdapter(mRecyclerView, mAdapter);

        donetwork();
    }

    private void getData() {
        items = new Items();//Items 等同于 ArrayList<Object>
        //测试环境
        ItemDemo7User model1 = new ItemDemo7User("测试环境", R.drawable.img01);
        ItemDemo7ContentText model21 = new ItemDemo7ContentText("测试环境的文本信息");
        ItemDemo7ContentImg model22 = new ItemDemo7ContentImg(R.drawable.img02);
        for (int i = 0; i < 20; i++) {
            items.add(new ItemDemo7(model21, System.currentTimeMillis() + "", model1));
            items.add(new ItemDemo7(model22, System.currentTimeMillis() + "", model1));
        }
        //上线环境
        List<ItemDemo7> weiboList = WeiboJsonParser.fromJson(JSON_FROM_SERVICE);
        // atomically
        items = new Items(items);
        items.addAll(0, weiboList);
    }

    private void donetwork() {
        getData();
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
        MultiTypeAsserts.assertAllRegistered(mAdapter, items);
    }

    private static final String JSON_FROM_SERVICE =
            "[\n" +
                    "    {\n" +
                    "        \"content\":{\n" +
                    "            \"text\":\"上线环境的文本信息.\",\n" +
                    "            \"content_type\":\"simple_text\"\n" +
                    "        },\n" +
                    "        \"createTime\":\"Just now\",\n" +
                    "        \"user\":{\n" +
                    "            \"avatar\":2131230915,\n" +
                    "            \"name\":\"上线环境\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"content\":{\n" +
                    "            \"resId\":2131230916,\n" +
                    "            \"content_type\":\"simple_image\"\n" +
                    "        },\n" +
                    "        \"createTime\":\"Just now(JSON_FROM_SERVICE)\",\n" +
                    "        \"user\":{\n" +
                    "            \"avatar\":2131230915,\n" +
                    "            \"name\":\"上线环境\"\n" +
                    "        }\n" +
                    "    }\n" +
                    "]";
}
