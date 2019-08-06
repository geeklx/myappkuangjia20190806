package com.haier.cellarette.baselibrary.changelanguage;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class ChangeLanguageSPUtil {

    private final String SP_NAME = "language_setting";
    private final String TAG_LANGUAGE = "language_select";
    private final String TAG_SYSTEM_LANGUAGE = "system_language";
    private static volatile ChangeLanguageSPUtil instance;

    private final SharedPreferences mSharedPreferences;

    private Locale systemCurrentLocal = Locale.ENGLISH;


    public ChangeLanguageSPUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }


    public void saveLanguage(int select) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putInt(TAG_LANGUAGE, select);
        edit.commit();
    }

    public int getSelectLanguage() {
        return mSharedPreferences.getInt(TAG_LANGUAGE, 0);
    }


    public Locale getSystemCurrentLocal() {
        return systemCurrentLocal;
    }

    public void setSystemCurrentLocal(Locale local) {
        systemCurrentLocal = local;
    }

    public static ChangeLanguageSPUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (ChangeLanguageSPUtil.class) {
                if (instance == null) {
                    instance = new ChangeLanguageSPUtil(context);
                }
            }
        }
        return instance;
    }
}
