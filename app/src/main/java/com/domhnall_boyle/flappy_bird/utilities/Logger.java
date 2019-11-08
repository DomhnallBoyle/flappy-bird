package com.domhnall_boyle.flappy_bird.utilities;

import android.content.res.Resources;
import android.util.Log;

import com.domhnall_boyle.flappy_bird.R;

public class Logger {

    private static final String appName = Resources.getSystem().getString(R.string.app_name);

    public static void debug(String message) {
        Log.d(appName, message);
    }

    public static void error(String message) {
        Log.e(appName, message);
    }
}
