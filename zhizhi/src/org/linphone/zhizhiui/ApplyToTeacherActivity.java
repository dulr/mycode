package org.linphone.zhizhiui;

import com.zhizhi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class ApplyToTeacherActivity extends Activity {

	private static final String[] str_chushangnian = { "2012", "2011", "2010",
			"2009", "2008", "2007", "2006", "2005", "2004", "2003" };
	private static final String[] str_chushanyue = { "01", "02", "03", "04",
			"05", "06", "07", "08", "09", "10", "11", "12" };
	private static final String[] str_guoji = { "中国", "英国", "美国", "加拿大", "日本",
			"法国", "德国", "印度", "其他" };
	private static final String[] str_muqiansuozaidi = { "北京", "上海", "广州",
			"深圳", "国内其他城市", "国外其他 城市" };
	private static final String[] str_muyu = { "汉语", "英语", "法语", "德语", };
	private static final String[] str_jiaoxuejingyan = { "0~1年", "1~2年",
			"2~3年", "3~5年", "5~6年", "8年以上", };
	private static final String[] str_xueli = { "汉语", "英语", "法语", "德语", };

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.applytoteacheractivity);

		// Button buttonBack = (Button) findViewById(R.id.buttonBack);
		// buttonBack.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// finish();
		// }
		// });
		Spinner chushengnian2 = (Spinner) findViewById(R.id.chushengnian);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_chushangnian);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		chushengnian2.setAdapter(adapter);

		Spinner chushengyue2 = (Spinner) findViewById(R.id.chushengyue);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_chushanyue);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		chushengyue2.setAdapter(adapter2);

		Spinner guoji2 = (Spinner) findViewById(R.id.guoji);
		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_guoji);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		guoji2.setAdapter(adapter3);

		Spinner muqiansuozaidi2 = (Spinner) findViewById(R.id.muqiansuozaidi);
		ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_muqiansuozaidi);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		muqiansuozaidi2.setAdapter(adapter4);

		Spinner muyu2 = (Spinner) findViewById(R.id.muyu);
		ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_muyu);
		adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		muyu2.setAdapter(adapter5);

		Spinner qitayuyan2 = (Spinner) findViewById(R.id.qitayuyan);
		ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_muyu);
		adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		qitayuyan2.setAdapter(adapter6);

		Spinner jiaoxuejingyan2 = (Spinner) findViewById(R.id.jiaoxuejingyan);
		ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_jiaoxuejingyan);
		adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		jiaoxuejingyan2.setAdapter(adapter7);

		Spinner xueli2 = (Spinner) findViewById(R.id.xueli);
		ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_xueli);
		adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		xueli2.setAdapter(adapter8);

		final LinearLayout applytoteacherStep11 = (LinearLayout) findViewById(R.id.applytoteacherStep1);
		final LinearLayout applytoteacherStep12 = (LinearLayout) findViewById(R.id.applytoteacherStep2);
		final LinearLayout applytoteacherStep13 = (LinearLayout) findViewById(R.id.applytoteacherStep3);
		final LinearLayout applytoteacherStep14 = (LinearLayout) findViewById(R.id.applytoteacherStep4);
		final LinearLayout applytoteacherStep15 = (LinearLayout) findViewById(R.id.applytoteacherStep5);
		final LinearLayout applytoteacherStep16 = (LinearLayout) findViewById(R.id.applytoteacherStep6);
		final LinearLayout applytoteacherStep17 = (LinearLayout) findViewById(R.id.applytoteacherStep7);

		final Button buttonApplytoTeacherStep1Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep1Next);
		final Button buttonApplytoTeacherStep2Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep2Next);
		final Button buttonApplytoTeacherStep3Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep3Next);
		final Button buttonApplytoTeacherStep4Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep4Next);
		final Button buttonApplytoTeacherStep5Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep5Next);
		final Button buttonApplytoTeacherStep6Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep6Next);
		final Button buttonApplytoTeacherStep7Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep7Next);

		buttonApplytoTeacherStep1Next1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						applytoteacherStep11.setVisibility(View.GONE);
						applytoteacherStep12.setVisibility(View.VISIBLE);
						applytoteacherStep13.setVisibility(View.GONE);
						applytoteacherStep14.setVisibility(View.GONE);
						applytoteacherStep15.setVisibility(View.GONE);
						applytoteacherStep16.setVisibility(View.GONE);
						applytoteacherStep17.setVisibility(View.GONE);
					}
				});
		buttonApplytoTeacherStep2Next1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						applytoteacherStep11.setVisibility(View.GONE);
						applytoteacherStep12.setVisibility(View.GONE);
						applytoteacherStep13.setVisibility(View.VISIBLE);
						applytoteacherStep14.setVisibility(View.GONE);
						applytoteacherStep15.setVisibility(View.GONE);
						applytoteacherStep16.setVisibility(View.GONE);
						applytoteacherStep17.setVisibility(View.GONE);
					}
				});
		buttonApplytoTeacherStep3Next1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						applytoteacherStep11.setVisibility(View.GONE);
						applytoteacherStep12.setVisibility(View.GONE);
						applytoteacherStep13.setVisibility(View.GONE);
						applytoteacherStep14.setVisibility(View.VISIBLE);
						applytoteacherStep15.setVisibility(View.GONE);
						applytoteacherStep16.setVisibility(View.GONE);
						applytoteacherStep17.setVisibility(View.GONE);
					}
				});
		buttonApplytoTeacherStep4Next1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						applytoteacherStep11.setVisibility(View.GONE);
						applytoteacherStep12.setVisibility(View.GONE);
						applytoteacherStep13.setVisibility(View.GONE);
						applytoteacherStep14.setVisibility(View.GONE);
						applytoteacherStep15.setVisibility(View.VISIBLE);
						applytoteacherStep16.setVisibility(View.GONE);
						applytoteacherStep17.setVisibility(View.GONE);
					}
				});
		buttonApplytoTeacherStep5Next1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						applytoteacherStep11.setVisibility(View.GONE);
						applytoteacherStep12.setVisibility(View.GONE);
						applytoteacherStep13.setVisibility(View.GONE);
						applytoteacherStep14.setVisibility(View.GONE);
						applytoteacherStep15.setVisibility(View.GONE);
						applytoteacherStep16.setVisibility(View.VISIBLE);
						applytoteacherStep17.setVisibility(View.GONE);
					}
				});
		buttonApplytoTeacherStep6Next1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						applytoteacherStep11.setVisibility(View.GONE);
						applytoteacherStep12.setVisibility(View.GONE);
						applytoteacherStep13.setVisibility(View.GONE);
						applytoteacherStep14.setVisibility(View.GONE);
						applytoteacherStep15.setVisibility(View.GONE);
						applytoteacherStep16.setVisibility(View.GONE);
						applytoteacherStep17.setVisibility(View.VISIBLE);
					}
				});
		buttonApplytoTeacherStep7Next1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						applytoteacherStep11.setVisibility(View.GONE);
						applytoteacherStep12.setVisibility(View.GONE);
						applytoteacherStep13.setVisibility(View.GONE);
						applytoteacherStep14.setVisibility(View.GONE);
						applytoteacherStep15.setVisibility(View.GONE);
						applytoteacherStep16.setVisibility(View.GONE);
						applytoteacherStep17.setVisibility(View.GONE);

						Intent intent = new Intent();
						intent.setClass(ApplyToTeacherActivity.this,
								LoginActivity.class);
						startActivity(intent);
						finish();
					}
				});
	}
}
