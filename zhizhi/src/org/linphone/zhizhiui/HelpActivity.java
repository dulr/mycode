package org.linphone.zhizhiui;

import com.zhizhi.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HelpActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helpactivity);

		// 常见问题
		final TextView frequentlyQuestions = (TextView) findViewById(R.id.FrequentlyQuestions);
		frequentlyQuestions.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HelpActivity.this,
						FrequentlyQuestionsActivity.class);
				startActivity(intent);
			}
		});
		// 关于软件
		final TextView settingPayPassword = (TextView) findViewById(R.id.aboutSoftware);
		settingPayPassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HelpActivity.this, AboutZhizhiActivity.class);
				startActivity(intent);
			}
		});
		// 意见反馈
		final TextView settingPersoninfor = (TextView) findViewById(R.id.yijianFankui);
		settingPersoninfor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog();
			}
		});
		// 呼叫服务
		final TextView hujiaofuwu = (TextView) findViewById(R.id.Hujiaofuwu);
		hujiaofuwu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ "13581606890"));
				startActivity(intent);
			}
		});
		// 返回
		Button buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	void showDialog() {
		final EditText et = new EditText(this);
		new AlertDialog.Builder(this).setTitle("请输入您的意见:")
				.setIcon(android.R.drawable.ic_dialog_info).setView(et)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// 数据获取
						Toast.makeText(HelpActivity.this,
								et.getText().toString(), Toast.LENGTH_LONG)
								.show();
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

						imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
						// mEditor.putString("ipadd", et.getText().toString());
						// 关键在这儿，获取输入框的数据，原来很简单！！
						// mEditor.commit();
					}
				}).setNegativeButton("取消", null).show();
	}
}
