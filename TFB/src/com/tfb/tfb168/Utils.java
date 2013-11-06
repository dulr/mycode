package com.tfb.tfb168;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

public class Utils {

    public static ProgressDialog createProgressDialog(Context context) {
        // ProgressDialog dialog = new ProgressDialog(context);
        // dialog.setCancelable(true);
        // dialog.setCanceledOnTouchOutside(false);
        // dialog.setMessage("正在加载中");
        // dialog.setIndeterminate(true);
        return createProgressDialog(context, "正在加载中");
    }

    public static ProgressDialog createProgressDialog(Context context,
            String msg) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(msg);
        dialog.setIndeterminate(true);
        return dialog;
    }
    
    public static InfoDialog createInfoDialog(Context context,
            String msg) {
        return createInfoDialog(context, msg, false);
    }
    public static InfoDialog createInfoDialog(Context context,
            String msg, boolean showProgress) {
        InfoDialog.Builder builder = new InfoDialog.Builder(context);
        builder.setMessage(msg);
        builder.setprogressshow(showProgress);
        InfoDialog dialog = builder.create();
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(false);
       
        return dialog;
    }

    public static boolean mislogOpened = true;
//    public static boolean mislogOpened = false;
    
    public static void Logd(String TAG, String info) {
        if (mislogOpened)
            Log.d(TAG, info);
    }

    public static void Logi(String TAG, String info) {
        if (mislogOpened)
            Log.i(TAG, info);
    }
}
