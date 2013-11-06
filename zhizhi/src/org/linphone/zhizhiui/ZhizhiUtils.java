package org.linphone.zhizhiui;

import android.app.ProgressDialog;
import android.content.Context;

public class ZhizhiUtils {
	
	public static ProgressDialog createProgressDialog(Context context) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("正在加载中");
        dialog.setIndeterminate(true);
		return dialog;
	}
	
	public static ProgressDialog createProgressDialog(Context context, String msg) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(msg);
        dialog.setIndeterminate(true);
		return dialog;
	}
}
