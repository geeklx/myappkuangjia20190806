package com.haier.cellarette.baselibrary.recycleviewmultitype.activitys;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.LinearLayoutManager;
//import androidx.appcompat.widget.LinearSnapHelper;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;


import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo5.ItemDemo5;
import com.haier.cellarette.baselibrary.recycleviewmultitype.viewholders.demo5.ItemDemo5DataType1ViewBinder;
import com.haier.cellarette.baselibrary.recycleviewmultitype.viewholders.demo5.ItemDemo5DataType2ViewBinder;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.Linker;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.MultiTypeAsserts;

public class RecDemo5Activity extends AppCompatActivity {

    private MultiTypeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    Items items = new Items();//Items 等同于 ArrayList<Object>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_demo5);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        List<ItemDemo5> dataList = getDataFromService();
        mAdapter.setItems(dataList);
        mAdapter.notifyDataSetChanged();
        MultiTypeAsserts.assertAllRegistered(mAdapter, dataList);
    }

    private void onclick() {

    }

    private void findview() {
        mRecyclerView = findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
        new LinearSnapHelper().attachToRecyclerView(mRecyclerView);

        mAdapter = new MultiTypeAdapter();
        mAdapter.register(ItemDemo5.class).to(
                new ItemDemo5DataType1ViewBinder(),
                new ItemDemo5DataType2ViewBinder()
        ).withLinker(new Linker<ItemDemo5>() {
            @Override
            public int index(int position, @NonNull ItemDemo5 itemDemo5) {
                if (itemDemo5.getContent2() == ItemDemo5.TYPE_1) {
                    return 0;
                } else if (itemDemo5.getContent2() == ItemDemo5.TYPE_2) {
                    return 1;
                }
                return 0;
            }
        });

//        mAdapter.register(ItemDemo5.class).to(
//                new ItemDemo5DataType1ViewBinder(),
//                new ItemDemo5DataType2ViewBinder())
//                .withClassLinker(new ClassLinker<ItemDemo5>() {
//                    @NonNull
//                    @Override
//                    public Class<? extends ItemViewBinder<ItemDemo5, ?>> index(int position, @NonNull ItemDemo5 itemDemo5) {
//                        if (itemDemo5.getContent2() == ItemDemo5.TYPE_1) {
//                            return ItemDemo5DataType1ViewBinder.class;
//                        } else if (itemDemo5.getContent2() == ItemDemo5.TYPE_2) {
//                            return ItemDemo5DataType2ViewBinder.class;
//                        }
//                        return ItemDemo5DataType1ViewBinder.class;
//                    }
//                });

        mRecyclerView.setAdapter(mAdapter);
        MultiTypeAsserts.assertHasTheSameAdapter(mRecyclerView, mAdapter);
    }

    private List<ItemDemo5> getDataFromService() {
        List<ItemDemo5> list = new ArrayList<>();
        for (int i = 0; i < 30; i = i + 2) {
            list.add(new ItemDemo5("title: " + i, ItemDemo5.TYPE_1));
            list.add(new ItemDemo5("title: " + i + 1, ItemDemo5.TYPE_2));
        }
        return list;
    }
}
