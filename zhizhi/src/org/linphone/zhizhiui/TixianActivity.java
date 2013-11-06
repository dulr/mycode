package org.linphone.zhizhiui;

import com.zhizhi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TixianActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tixian);
		Button buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		// 编辑资料
		Button buttonSetBankInfo = (Button) findViewById(R.id.buttonSetBankInfo);
		buttonSetBankInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TixianActivity.this, BankInfoActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
