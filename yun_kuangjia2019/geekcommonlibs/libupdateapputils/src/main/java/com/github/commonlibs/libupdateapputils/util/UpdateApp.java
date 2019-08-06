/**
 * Copyright 2016,Smart Haier.All rights reserved
 */
package com.github.commonlibs.libupdateapputils.util;

import android.app.Application;

/**
 * <p class="note">used as getApplication</p>
 */

public class UpdateApp {
    private static Application sInstance;

    /**
     * get Application
     *
     * @return Application
     */
    public static Application get() {
        if (sInstance == null) {
            Application app = null;
            try {
                app = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null);
                if (app == null) {
                    throw new IllegalStateException("Static initialization of Applications must be on main thread.");
                }
            } catch (final Exception e) {
                try {
                    app = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
                } catch (final Exception ex) {
                    e.printStackTrace();
                }
            } finally {
                sInstance = app;
            }
        }

        return sInstance;
    }
}
