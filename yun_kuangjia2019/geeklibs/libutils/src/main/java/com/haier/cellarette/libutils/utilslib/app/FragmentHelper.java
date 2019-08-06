package com.haier.cellarette.libutils.utilslib.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
//import androidx.core.app.Fragment;

public class FragmentHelper {
    /**
     * 新建fragment实例
     * @param fragmentKlass
     * @param bundle
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T newFragment(Class<T> fragmentKlass, Bundle bundle) {
        T res = null;
        try {
            res = fragmentKlass.newInstance();
            if (bundle != null) {
                res.setArguments(bundle);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 根据fragment的完整包名+名称实例化fragment
     * @param className
     * @param bundle
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T newFragment(String className, Bundle bundle) {
        T res = null;
        try {
            res = (T) Class.forName(className).newInstance();
            if (bundle != null) {
                res.setArguments(bundle);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }
}
