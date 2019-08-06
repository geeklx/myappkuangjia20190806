package com.example.shining.libglin.glin.cache;


import com.example.shining.libglin.glin.RawResult;
import com.example.shining.libglin.glin.Result;
import com.example.shining.libglin.glin.helper.Helper;
import com.example.shining.libglin.glin.helper.SerializeHelper;

import java.io.File;

public class DefaultCacheProvider implements ICacheProvider {

    private static final String SUFFIX = ".gc";

    private String mCachePath;
    private long mMaxCacheSize;

    public DefaultCacheProvider(String cachePath, long maxCacheSize) {
        mCachePath = cachePath.endsWith("/") ? cachePath : cachePath + "/";
        mMaxCacheSize = maxCacheSize;

        File cache = new File(mCachePath);
        if (!cache.exists()) {
            for (int i = 0; i < 3;i++) {
                if (cache.mkdirs()) { break;}
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Result<T> get(String key, Class<T> klass, boolean isList) {
        T cacheResult = SerializeHelper.unSerialize(mCachePath, key + SUFFIX);
        if (cacheResult == null) { return null;}

        Result<T> result = new Result<>();
        result.ok(true);
        result.setMessage("");
        result.setResult(cacheResult);
        result.setObj(200);
        result.setCache(true);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void put(String key, RawResult netResult, Result<T> result) {
        checkCacheSize();
        SerializeHelper.serialize(mCachePath, key + SUFFIX, result.getResult());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKey(String url, String params) {
        String cacheName = url + (params == null ? "" : params);
        cacheName = Helper.md5(cacheName);
        return cacheName == null ? "" : cacheName;
    }

    private void checkCacheSize() {
        File dir = new File(mCachePath);
        File[] files = dir.listFiles();

        long size = 0L;
        for (File item : files) {
            size += item.length();
        }

        if (mMaxCacheSize <= size) {
            clearAllCache(files);
        }
    }

    private void clearAllCache(File[] files) {
        TAG:
        for (File file : files) {
            for (int i = 0; i < 3; i++) {
                if (file.delete()) {continue TAG;}
            }
        }
    }
}
