package org.linphone.zhizhiui;

import com.zhizhi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ShaixuanActivity extends Activity {
	
	private static final String[] str_keshifei = {
		"不限","<0.2元/分钟", "0.2～0.5元/分钟", "0.5～1.0元/分钟", "1.0～1.5元/分钟", "1.5～2.0元/分钟", "2.0～3.0元/分钟", 
		"3.0～5.0元/分钟", ">5.0元/分钟"
	};
	private static final String[] str_isonline = {
		"不限","是", "否"
		};
	private static final String[] str_guoji = {
		"不限","中国", "美国", "英国", "加拿大", "澳大利亚", "新西兰", "菲律宾", "印度", "法国","德国","意大利","俄罗斯","其他"
		};
	private static final String[] str_suozaichengshi = {
		"不限","北京", "上海", "广州", "深圳", "国内其他城市", "国外其他城市"
		};
	private static final String[] str_muyu = {
		"不限","汉语", "英语", "法语", "德语", 
		};
	private static final String[] str_jiaoxuejingyan = {
		"不限","1年以下", "1~3年", "4~5年", "6~10年","10年以上", 
		};
	private static final String[] str_xueli = {
		"不限","汉语", "英语", "法语", "德语", 
		};
	private static final String[] str_photo = {
		"不限", "有", "无", 
		};
	private static final String[] str_xingbie = {
		"不限", "男", "女", 
		};
	private static final String[] str_zhongwenshuiping = {
		"不限", "零基础", "初级", "中级", "高级", 
		};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(MainViewActivity.mCurrentTab == 0 ? R.layout.shaixuan
                : R.layout.shaixuanxuesheng);

		Spinner spinnerIsonline = (Spinner) findViewById(R.id.spinnerIsonline);
		ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_isonline);
		adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerIsonline.setAdapter(adapter9);	
	
		Spinner spinnerJiaoshiZheng = (Spinner) findViewById(R.id.spinnerJiaoshiZheng);
		ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_photo);
		adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerJiaoshiZheng.setAdapter(adapter8);	
		
		Spinner spinnerZhongwenShuiping = (Spinner) findViewById(R.id.spinnerZhongwenShuiping);
		ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_zhongwenshuiping);
		adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerZhongwenShuiping.setAdapter(adapter7);	
		
		Spinner spinnerJingyan = (Spinner) findViewById(R.id.spinnerJingyan);
		ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_jiaoxuejingyan);
		adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerJingyan.setAdapter(adapter6);	
		
		Spinner spinnerKeshiFei = (Spinner) findViewById(R.id.spinnerKeshiFei);
		ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_keshifei);
		adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerKeshiFei.setAdapter(adapter5);	
		
		Spinner spinnerGuoji = (Spinner) findViewById(R.id.spinnerGuoji);
		ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_guoji);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerGuoji.setAdapter(adapter4);	
		
		Spinner spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_suozaichengshi);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerCity.setAdapter(adapter3);	
		
		
		Spinner spinnerXingbie = (Spinner) findViewById(R.id.spinnerXingbie);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_xingbie);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerXingbie.setAdapter(adapter2);	
		
		
		Spinner spinnerPhoto = (Spinner) findViewById(R.id.spinnerPhoto);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_photo);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerPhoto.setAdapter(adapter);

		// 确定
		Button btnOK = (Button) findViewById(R.id.buttonOK);
		btnOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent();
				// intent.setClass(TixianActivity.this, ChongzhiActivity.class);
				// startActivity(intent);
				finish();
			}
		});
	}
}
