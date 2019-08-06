package com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo7.gson;


import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo7.ItemDemo7;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo7.ItemDemo7ContentTextImgCommon;

import java.util.ArrayList;
import java.util.List;

public final class WeiboJsonParser {

    private WeiboJsonParser() {
    }

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ItemDemo7ContentTextImgCommon.class, new WeiboContentDeserializer())
            .create();

    public static List<ItemDemo7> fromJson(@NonNull String json) {
        return GSON.fromJson(json, new TypeToken<ArrayList<ItemDemo7>>() {
        }.getType());
    }
}
