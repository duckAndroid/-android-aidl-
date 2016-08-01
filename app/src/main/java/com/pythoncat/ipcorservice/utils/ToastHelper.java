package com.pythoncat.ipcorservice.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by pythonCat on 2016/8/2.
 */
public class ToastHelper {

    private static Toast mToast;

    public static void show(Context c, String text) {
        cancel();
        if (mToast == null) {
            mToast = Toast.makeText(c, text, Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
