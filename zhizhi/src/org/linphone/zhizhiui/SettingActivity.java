package org.linphone.zhizhiui;

import com.zhizhi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SettingActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settingactivity);
		Button buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		// 设置登录密码
		final TextView settingLonginPssword = (TextView) findViewById(R.id.SettingLonginPassword);
		settingLonginPssword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SettingActivity.this,
						ModifyLoginPasswordActivity.class);
				startActivity(intent);
			}
		});
		// 设置支付密码
		final TextView settingPayPassword = (TextView) findViewById(R.id.SettingPayPassword);
		settingPayPassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SettingActivity.this,
						ModifyPayPasswordActivity.class);
				startActivity(intent);
			}
		});
		// 设置银行信息
		final TextView settingBankinfor = (TextView) findViewById(R.id.SettingBankinfor);
		settingBankinfor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SettingActivity.this, BankInfoActivity.class);
				startActivity(intent);
			}
		});
		// 修改个人信息
		final TextView settingPersoninfor = (TextView) findViewById(R.id.SettingPersoninfor);
		settingPersoninfor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SettingActivity.this,
						ModifyPersonInforActivity.class);
				startActivity(intent);
			}
		});
		// 申请称为教师
		final TextView applyToTercher = (TextView) findViewById(R.id.ApplyToTercher);
		applyToTercher.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SettingActivity.this,
						ApplyToTeacherActivity.class);
				startActivity(intent);
			}
		});

		// 设置通话价格
		final TextView setCallPrice = (TextView) findViewById(R.id.SetCallPrice);
		setCallPrice.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SettingActivity.this,
						SetCallPriceActivity.class);
				startActivity(intent);
			}
		});
	}
}
