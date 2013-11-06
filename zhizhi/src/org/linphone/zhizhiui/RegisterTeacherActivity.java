package org.linphone.zhizhiui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.linphone.LinphoneService;
import com.zhizhi.R;
import org.linphone.zhizhiui.data.NetworkUtils;
import org.linphone.zhizhiui.data.RegUtils;
import org.linphone.zhizhiui.data.UploadFileUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class RegisterTeacherActivity extends Activity {
	final static String TAG = "RegisterTeacherActivity";
	ProgressDialog mDialog;
	private static final String[] str_chushangnian = { "2012", "2011", "2010",
		"2009", "2008", "2007", "2006", "2005", "2004", "2003","2002","2001","2000",
		"1999","1998","1997","1996","1995","1994","1993","1992","1991","1990",
		"1989","1988","1987","1986","1985","1984","1983","1982","1981","1980",
		"1979","1978","1977","1976","1975","1974","1973","1972","1971","1970",
		"1969","1968","1967","1966","1965","1964","1963","1962","1961","1960",
		"1959","1958","1957","1956","1955","1954","1953","1952","1951","1950",
		"1949","1948","1947","1946","1945","1944","1943","1942","1941","1940",
		"1939","1938","1937","1936","1935","1934","1933","1932","1931","1930" };
	private static final String[] str_chushanyue = { "01", "02", "03", "04",
			"05", "06", "07", "08", "09", "10", "11", "12" };
	private static final String[] str_guoji = { "中国", "英国", "美国", "加拿大", "日本",
			"法国", "德国", "印度", "其他" };
	private static final String[] str_muqiansuozaidi = { "北京", "上海", "广州",
			"深圳", "国内其他城市", "国外其他城市" };
	private static final String[] str_muyu = { "英语", "汉语", "其他"};
	private static final String[] str_qitayuyan = { "汉语", "英语","俄语","法语","西班牙语", "其他"};
	private static final String[] str_jiaoxuejingyan = { "0~1年", "1~2年",
			"2~3年", "3~5年", "5~6年", "8年以上", };
	private static final String[] str_xueli = { "高中", "学士", "硕士", "博士","其他" };
	String mUserName;
	String mPassWord;
	String mSex = "U";
	String mBirthYear = "2012";
	String mBirthMonth = "01";
	String mCountry = "中国";
	String mCurrentPlace = "北京";
	String mLanguage = "英语";
	String mOther_Language = "汉语";
	String mChineseLevel = "初级（会简单会话）";
	String mExperience    = "0~1";
	
	RadioGroup radiogroupSex;
	RadioButton radiobuttonfeMale;
	RadioButton radiobuttonSexMale;
	RadioGroup radiogroupEnglishLevel;
	RadioButton radiobutton1;
	RadioButton radiobutton2;
	RadioButton radiobutton3;
	RadioButton radiobutton4;
	RadioButton radiobutton5;
	
	LinearLayout applytoteacherStep11;
	LinearLayout applytoteacherStep12;
	LinearLayout applytoteacherStep13;
	LinearLayout applytoteacherStep14;
	LinearLayout applytoteacherStep15;
	LinearLayout applytoteacherStep16;
	LinearLayout applytoteacherStep17;
	LinearLayout applytoteacherStep18;

	ImageView personImageHeader;
	ImageView imageViewPersonalCertificate;
	ImageView imageViewTeacherCertificate;
	Bitmap uploadPersonBm = null;
	Bitmap uploadPersonalCertificate = null;
	Bitmap uploadTeacherCertificate = null;
	
	
	//step5 基本信息
	EditText editTextZhuanYe;
	EditText  editTextZhiYe;
	 // 特长
	CheckBox radiobutton411;
	CheckBox radiobutton412;
	CheckBox radiobutton413;
	CheckBox radiobutton414;
	CheckBox radiobutton415;
	CheckBox radiobutton416;
	CheckBox radiobutton417;
	// 可教学生类型
	CheckBox radiobutton421;
	CheckBox radiobutton422;
	CheckBox radiobutton423;
	CheckBox radiobutton424;
	CheckBox radiobutton425;
	CheckBox radiobutton426;
	CheckBox radiobutton427;
	//可教学生程度
	CheckBox radiobutton431;
	CheckBox radiobutton432;
	CheckBox radiobutton433;
	
	String mEducation = "高中";
	String mProfession = "";
	String mCareer = "";
	String mSpeciality = "";
	String mTeachStudentType = "";
	String mTeachStudentLevel = "";
	void initStep5() {
		editTextZhuanYe = (EditText) findViewById(R.id.editTextZhuanYe);
		editTextZhiYe = (EditText) findViewById(R.id.editTextZhiYe);
		// 特长
		radiobutton411 = (CheckBox) findViewById(R.id.radiobutton411);
		radiobutton412 = (CheckBox) findViewById(R.id.radiobutton412);
		radiobutton413 = (CheckBox) findViewById(R.id.radiobutton413);
		radiobutton414 = (CheckBox) findViewById(R.id.radiobutton414);
		radiobutton415 = (CheckBox) findViewById(R.id.radiobutton415);
		radiobutton416 = (CheckBox) findViewById(R.id.radiobutton416);
		radiobutton417 = (CheckBox) findViewById(R.id.radiobutton417);
		// 可教学生类型
		radiobutton421 = (CheckBox) findViewById(R.id.radiobutton421);
		radiobutton422 = (CheckBox) findViewById(R.id.radiobutton422);
		radiobutton423 = (CheckBox) findViewById(R.id.radiobutton423);
		radiobutton424 = (CheckBox) findViewById(R.id.radiobutton424);
		radiobutton425 = (CheckBox) findViewById(R.id.radiobutton425);
		radiobutton426 = (CheckBox) findViewById(R.id.radiobutton426);
		radiobutton427 = (CheckBox) findViewById(R.id.radiobutton427);
		// 可教学生程度
		radiobutton431 = (CheckBox) findViewById(R.id.radiobutton431);
		radiobutton432 = (CheckBox) findViewById(R.id.radiobutton432);
		radiobutton433 = (CheckBox) findViewById(R.id.radiobutton433);

		Spinner xueli2 = (Spinner) findViewById(R.id.xueli);
		ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_xueli);
		adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		xueli2.setAdapter(adapter8);
		xueli2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mExperience = parent.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	
	//个人简介
	EditText editTextPersonResume;
	String mPersonResume = "";
	void initStep6() {
		editTextPersonResume = (EditText) findViewById(R.id.editTextPersonResume);
	}
	TextView textviewTiaoGuoTouxiang;
	TextView textviewTiaoGuoPersonZhengjian;
	TextView textviewTiaoGuoTeacherCertificate;
	TextView textviewTiaoGuojinBenxinxi;
	
	TextView textviewShangYibugeRenJianjie;
	TextView textviewShangYibuJibenxinxi;
	TextView textviewShangYibuTeacherCertificate;
	TextView textviewShangyiBuPersonZhengjian;
	void initTiaoGuo() {
		textviewTiaoGuojinBenxinxi = (TextView) findViewById(R.id.textviewTiaoGuojinBenxinxi);
		textviewTiaoGuojinBenxinxi.setOnClickListener(new OnClickListener() {

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
		
		textviewTiaoGuoTeacherCertificate = (TextView) findViewById(R.id.textviewTiaoGuoTeacherCertificate);
		textviewTiaoGuoTeacherCertificate.setOnClickListener(new OnClickListener() {

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
		textviewShangYibugeRenJianjie = (TextView) findViewById(R.id.textviewShangYibugeRenJianjie);
		textviewShangYibugeRenJianjie.setOnClickListener(new OnClickListener() {

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
		textviewShangYibuJibenxinxi = (TextView) findViewById(R.id.textviewShangYibuJibenxinxi);
		textviewShangYibuJibenxinxi.setOnClickListener(new OnClickListener() {

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

		textviewTiaoGuoPersonZhengjian = (TextView) findViewById(R.id.textviewTiaoGuoPersonZhengjian);
		textviewTiaoGuoPersonZhengjian.setOnClickListener(new OnClickListener() {

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

		textviewTiaoGuoTouxiang = (TextView) findViewById(R.id.textviewTiaoGuoTouxiang);
		textviewTiaoGuoTouxiang.setOnClickListener(new OnClickListener() {

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
		textviewShangYibuTeacherCertificate = (TextView) findViewById(R.id.textviewShangYibuTeacherCertificate);
		textviewShangYibuTeacherCertificate.setOnClickListener(new OnClickListener() {

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
		textviewShangyiBuPersonZhengjian = (TextView) findViewById(R.id.textviewShangyiBuPersonZhengjian);
		textviewShangyiBuPersonZhengjian.setOnClickListener(new OnClickListener() {

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
	}
	RadioButton radiobuttonJiaosheZiGezheng1;
	RadioButton radiobuttonJiaosheZiGezheng2;
	RadioGroup radiogroupJiaosheZiGezheng1;
	void initJiaoShiZiGezheng() {
		radiobuttonJiaosheZiGezheng1 = (RadioButton) findViewById(R.id.radiobuttonJiaosheZiGezheng1);
		radiobuttonJiaosheZiGezheng2 = (RadioButton) findViewById(R.id.radiobuttonJiaosheZiGezheng2);
		radiogroupJiaosheZiGezheng1 = (RadioGroup) findViewById(R.id.radiogroupJiaosheZiGezheng1);
		radiogroupJiaosheZiGezheng1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// 根据ID判断选择的按钮
				if (checkedId == R.id.radiobuttonJiaosheZiGezheng1) {
					buttonBendiZhaopianTeacherCertificate.setVisibility(View.VISIBLE);
					buttonPaizhaoShangchuanTeacherCertificate.setVisibility(View.VISIBLE);
					imageViewTeacherCertificate.setVisibility(View.VISIBLE);
				} else {
					buttonBendiZhaopianTeacherCertificate.setVisibility(View.INVISIBLE);
					buttonPaizhaoShangchuanTeacherCertificate.setVisibility(View.INVISIBLE);
					imageViewTeacherCertificate.setVisibility(View.INVISIBLE);
				}
			}
		});
		
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerteacheractivity);
		mUserName = getIntent().getStringExtra("UserName");
		mPassWord = getIntent().getStringExtra("PassWord");
		personImageHeader = (ImageView) findViewById(R.id.personImageHeader);
		imageViewPersonalCertificate = (ImageView) findViewById(R.id.imageViewPersonalCertificate);
		imageViewTeacherCertificate = (ImageView) findViewById(R.id.imageViewTeacherCertificate);
		initStep5();
		initStep6();
		initTiaoGuo();
		initJiaoShiZiGezheng();
		Spinner chushengnian2 = (Spinner) findViewById(R.id.chushengnian);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_chushangnian);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		chushengnian2.setAdapter(adapter);
		chushengnian2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mBirthYear = parent.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
		Spinner chushengyue2 = (Spinner) findViewById(R.id.chushengyue);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_chushanyue);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		chushengyue2.setAdapter(adapter2);
		chushengyue2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mBirthMonth = parent.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
		Spinner guoji2 = (Spinner) findViewById(R.id.guoji);
		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_guoji);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		guoji2.setAdapter(adapter3);
		guoji2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mCountry = parent.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
		Spinner muqiansuozaidi2 = (Spinner) findViewById(R.id.muqiansuozaidi);
		ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_muqiansuozaidi);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		muqiansuozaidi2.setAdapter(adapter4);
		muqiansuozaidi2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mCurrentPlace = parent.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
		Spinner muyu2 = (Spinner) findViewById(R.id.muyu);
		ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_muyu);
		adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		muyu2.setAdapter(adapter5);
		muyu2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mLanguage = parent.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
		Spinner qitayuyan2 = (Spinner) findViewById(R.id.qitayuyan);
		ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_qitayuyan);
		adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		qitayuyan2.setAdapter(adapter6);
		qitayuyan2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mOther_Language = parent.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
		Spinner jiaoxuejingyan2 = (Spinner) findViewById(R.id.jiaoxuejingyan);
		ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_jiaoxuejingyan);
		adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		jiaoxuejingyan2.setAdapter(adapter7);
		jiaoxuejingyan2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mExperience = parent.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
		
		radiogroupSex = (RadioGroup) findViewById(R.id.radiogroupSex);
		radiobuttonfeMale = (RadioButton) findViewById(R.id.radiobuttonfeMale);
		radiobuttonSexMale = (RadioButton) findViewById(R.id.radiobuttonSexMale);
		radiogroupSex.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// 根据ID判断选择的按钮
				if (checkedId == R.id.radiobuttonfeMale) {
					mSex = "F";
				} else {
					mSex = "M";
				}
			}
		});
		
		radiogroupEnglishLevel = (RadioGroup) findViewById(R.id.radiogroupChineseLevel);
		radiobutton1 = (RadioButton) findViewById(R.id.radiobutton1);
		radiobutton2 = (RadioButton) findViewById(R.id.radiobutton2);
		radiobutton3 = (RadioButton) findViewById(R.id.radiobutton3);
		radiobutton4 = (RadioButton) findViewById(R.id.radiobutton4);
		radiogroupEnglishLevel
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// 根据ID判断选择的按钮
						switch (checkedId) {
						case R.id.radiobutton1: {
							mChineseLevel = radiobutton1.getText().toString();
						}
							break;
						case R.id.radiobutton2: {
							mChineseLevel = radiobutton2.getText().toString();
						}
							break;
						case R.id.radiobutton3: {
							mChineseLevel = radiobutton3.getText().toString();
						}
							break;
						case R.id.radiobutton4: {
							mChineseLevel = radiobutton4.getText().toString();
						}
							break;

						}
					}
				});

		applytoteacherStep11 = (LinearLayout) findViewById(R.id.applytoteacherStep1);
		applytoteacherStep12 = (LinearLayout) findViewById(R.id.applytoteacherStep2);
		applytoteacherStep13 = (LinearLayout) findViewById(R.id.applytoteacherStep3);
		applytoteacherStep14 = (LinearLayout) findViewById(R.id.applytoteacherStep4);
		applytoteacherStep15 = (LinearLayout) findViewById(R.id.applytoteacherStep5);
		applytoteacherStep16 = (LinearLayout) findViewById(R.id.applytoteacherStep6);
		applytoteacherStep17 = (LinearLayout) findViewById(R.id.applytoteacherStep7);
		applytoteacherStep18 = (LinearLayout) findViewById(R.id.applytoteacherStep8);

		final Button buttonApplytoTeacherStep1Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep1Next);
		final Button buttonApplytoTeacherStep2Wanshanziliao1 = (Button) findViewById(R.id.buttonApplytoTeacherStep2Wanshanziliao);
		final Button buttonApplytoTeacherStep2PutongDenglu1 = (Button) findViewById(R.id.buttonApplytoTeacherStep2PutongDenglu);
		final Button buttonApplytoTeacherStep3Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep3Next);
		final Button buttonApplytoTeacherStep4Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep4Next);
		final Button buttonApplytoTeacherStep5Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep5Next);
		final Button buttonApplytoTeacherStep6Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep6Next);
		final Button buttonApplytoTeacherStep7Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep7Next);
		final Button buttonApplytoTeacherStep8Next1 = (Button) findViewById(R.id.buttonApplytoTeacherStep8Next);

		buttonApplytoTeacherStep2PutongDenglu1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(RegisterTeacherActivity.this,
								LoginActivity.class);
						startActivity(intent);
						finish();
					}
				});

		buttonApplytoTeacherStep1Next1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						CheckBox box = (CheckBox)	findViewById(R.id.checkBoxReadServiceItem);
						if(!box.isChecked()) {
							Toast.makeText(RegisterTeacherActivity.this, "请阅读服务条款！", 1000).show();
							return;
						}
						mDialog = ZhizhiUtils.createProgressDialog(RegisterTeacherActivity.this,"正在注册...");
						mDialog.show();
						tryToRegTeacherStep1();
						
					}
				});
		buttonApplytoTeacherStep2Wanshanziliao1
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
						if(uploadPersonBm != null) {
							mDialog = ZhizhiUtils.createProgressDialog(RegisterTeacherActivity.this,"正在上传中");
							mDialog.show();
							tryToUploadFilePersonImage();
						} else {
							Toast.makeText(RegisterTeacherActivity.this, "请选择图片", 1000).show();
						}						
					}
				});
		buttonApplytoTeacherStep4Next1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if(uploadPersonalCertificate != null) {
							mDialog = ZhizhiUtils.createProgressDialog(RegisterTeacherActivity.this,"正在上传中");
							mDialog.show();
							tryToUploadFilePersonalCertificate();
						} else {
							Toast.makeText(RegisterTeacherActivity.this, "请选择图片", 1000).show();
						}
					}
				});
		buttonApplytoTeacherStep5Next1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if(uploadTeacherCertificate != null) {
							mDialog = ZhizhiUtils.createProgressDialog(RegisterTeacherActivity.this,"正在上传中");
							mDialog.show();
							tryToUploadFileTeacherCertificate();
						} else {
							Toast.makeText(RegisterTeacherActivity.this, "请选择图片", 1000).show();
						}
					}
				});
		buttonApplytoTeacherStep6Next1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						mProfession = editTextZhuanYe.getText().toString();
//						if (mProfession == null || mProfession.length() == 0) {
//							Toast.makeText(getBaseContext(), "请输入专业", 1000)
//									.show();
//							return;
//						}
						mCareer = editTextZhiYe.getText().toString();
//						if (mCareer == null || mCareer.length() == 0) {
//							Toast.makeText(getBaseContext(), "请输入职业", 1000)
//									.show();
//							return;
//						}
						mDialog = ZhizhiUtils.createProgressDialog(
								RegisterTeacherActivity.this, "正在上传中");
						mDialog.show();
						if (radiobutton411.isChecked()) {
							mSpeciality += radiobutton411.getText().toString()
									+ ",";
						}
						if (radiobutton412.isChecked()) {
							mSpeciality += radiobutton412.getText().toString()
									+ ",";
						}

						if (radiobutton413.isChecked()) {
							mSpeciality += radiobutton413.getText().toString()
									+ ",";
						}
						if (radiobutton414.isChecked()) {
							mSpeciality += radiobutton414.getText().toString()
									+ ",";
						}
						if (radiobutton415.isChecked()) {
							mSpeciality += radiobutton415.getText().toString()
									+ ",";
						}
						if (radiobutton416.isChecked()) {
							mSpeciality += radiobutton416.getText().toString()
									+ ",";
						}
						if (radiobutton417.isChecked()) {
							mSpeciality += radiobutton417.getText().toString()
									+ ",";
						}
						if (radiobutton421.isChecked()) {
							mTeachStudentType += radiobutton421.getText()
									.toString() + ",";
						}
						if (radiobutton422.isChecked()) {
							mTeachStudentType += radiobutton422.getText()
									.toString() + ",";
						}
						if (radiobutton423.isChecked()) {
							mTeachStudentType += radiobutton423.getText()
									.toString() + ",";
						}
						if (radiobutton424.isChecked()) {
							mTeachStudentType += radiobutton424.getText()
									.toString() + ",";
						}
						if (radiobutton425.isChecked()) {
							mTeachStudentType += radiobutton425.getText()
									.toString() + ",";
						}
						if (radiobutton426.isChecked()) {
							mTeachStudentType += radiobutton426.getText()
									.toString() + ",";
						}
						if (radiobutton427.isChecked()) {
							mTeachStudentType += radiobutton427.getText()
									.toString() + ",";
						}
						if (radiobutton431.isChecked()) {
							mTeachStudentLevel += radiobutton431.getText()
									.toString() + ",";
						}
						if (radiobutton432.isChecked()) {
							mTeachStudentLevel += radiobutton432.getText()
									.toString() + ",";
						}
						if (radiobutton433.isChecked()) {
							mTeachStudentLevel += radiobutton433.getText()
									.toString() + ",";
						}
						tryToRegTeacherStep5();
					}
				});
		buttonApplytoTeacherStep7Next1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						mPersonResume = editTextPersonResume.getText().toString();
						if (mPersonResume == null || mPersonResume.length() == 0) {
							Toast.makeText(getBaseContext(), "请输入个人简介", 1000)
									.show();
							return;
						}
						mDialog = ZhizhiUtils.createProgressDialog(
								RegisterTeacherActivity.this, "正在上传中");
						mDialog.show();
						tryToRegTeacherStep6();
					}
				});
		buttonApplytoTeacherStep8Next1
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
						intent.setClass(RegisterTeacherActivity.this,
								MyspaceActivity.class);
						startActivity(intent);
						finish();
					}
				});
		
		mbuttonBendiZhaopian = (Button) findViewById(R.id.buttonBendiZhaopian);
		mbuttonPaizhaoShangchuan = (Button) findViewById(R.id.buttonPaizhaoShangchuan);
		mbuttonBendiZhaopian.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getPicFromGallery();
			}
		});
		mbuttonPaizhaoShangchuan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					getPicFromCapture();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		buttonBendiZhaopianPersonalCertificate = (Button) findViewById(R.id.buttonBendiZhaopianPersonalCertificate);
		buttonPaizhaoShangchuanPersonalCertificate = (Button) findViewById(R.id.buttonPaizhaoShangchuanPersonalCertificate);
		buttonBendiZhaopianPersonalCertificate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getPicFromGallery();
			}
		});
		buttonPaizhaoShangchuanPersonalCertificate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					getPicFromCapture();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		buttonBendiZhaopianTeacherCertificate = (Button) findViewById(R.id.buttonBendiZhaopianTeacherCertificate);
		buttonPaizhaoShangchuanTeacherCertificate = (Button) findViewById(R.id.buttonPaizhaoShangchuanTeacherCertificate);
		buttonBendiZhaopianTeacherCertificate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getPicFromGallery();
			}
		});
		buttonPaizhaoShangchuanTeacherCertificate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					getPicFromCapture();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	Button mbuttonBendiZhaopian;
	Button mbuttonPaizhaoShangchuan;
	
	Button buttonBendiZhaopianPersonalCertificate;
	Button buttonPaizhaoShangchuanPersonalCertificate;
	
	Button buttonBendiZhaopianTeacherCertificate;
	Button buttonPaizhaoShangchuanTeacherCertificate;
	void tryToRegTeacherStep1() {
		int msgNum = 1001;
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("isTeacher", "Y"));
		list.add(new BasicNameValuePair("username", mUserName));
		list.add(new BasicNameValuePair("password", mPassWord));
		list.add(new BasicNameValuePair("sex", mSex));
		list.add(new BasicNameValuePair("BirthYear", mBirthYear));
		list.add(new BasicNameValuePair("BirthMonth", mBirthMonth));
		list.add(new BasicNameValuePair("country", mCountry));
		list.add(new BasicNameValuePair("CurrentPlace", mCurrentPlace));
		list.add(new BasicNameValuePair("Language", mLanguage));
		list.add(new BasicNameValuePair("Other_Language", mOther_Language));
		list.add(new BasicNameValuePair("Chinese_Level", mChineseLevel));
		list.add(new BasicNameValuePair("Experience", mExperience));
		try {
			String result = RegUtils.teachRegStep1ResultForHttpPost(list);
			if ("error".equalsIgnoreCase(result)) {
				Log.i(TAG, "msg=" + "error");
			} else {
				JSONObject jsonObject;
				jsonObject = new JSONObject(result.toString());
				// {"state":true,"msg":"login success"}
				String state;
				String msg;
				state = jsonObject.getString("state");
				msg = jsonObject.getString("msg");
				Log.i(TAG, "state=" + state);
				Log.i(TAG, "msg=" + msg);
				
				if ("true".equalsIgnoreCase(state)) {
					msgNum = 1000;
				}
			}
		} catch (Exception e) {

		}
//		msgNum = 1000;
		mHandler.sendEmptyMessage(msgNum);
	}
	void tryToUploadFilePersonImage() {
		new Thread() {
			public void run() {
				File tempFile = UploadFileUtils.savaBitmap(uploadPersonBm);
				int msg = 2001;
				try {
					boolean uploaded = RegUtils.teachRegStep2ResultForHttpPost(tempFile);	
					if(uploaded) {
						msg =2000;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tempFile.delete();
//				msg =2000;
				mHandler.sendEmptyMessage(msg);

			};
		}.start();
	}
	void tryToUploadFilePersonalCertificate() {
		new Thread() {
			public void run() {
				File tempFile = UploadFileUtils.savaBitmap(uploadPersonalCertificate);
				int msg = 3001;
				try {
					boolean uploaded = RegUtils.teachRegStep3ResultForHttpPost(tempFile);	
					if(uploaded) {
						msg =3000;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tempFile.delete();
//				msg =3000;
				mHandler.sendEmptyMessage(msg);

			};
		}.start();
	}
	void tryToUploadFileTeacherCertificate() {
		new Thread() {
			public void run() {
				File tempFile = UploadFileUtils.savaBitmap(uploadTeacherCertificate);
				int msg = 4001;
				try {
					boolean uploaded = RegUtils.teachRegStep4ResultForHttpPost(tempFile);	
					if(uploaded) {
						msg =4000;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tempFile.delete();
//				msg =4000;
				mHandler.sendEmptyMessage(msg);

			};
		}.start();
	}
	void tryToRegTeacherStep5() {
		int msgNum = 5001;
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("edu", mEducation));
		list.add(new BasicNameValuePair("major", mProfession));
		list.add(new BasicNameValuePair("Career", mCareer));
		list.add(new BasicNameValuePair("goodat", mSpeciality));
		list.add(new BasicNameValuePair("teach_type", mTeachStudentType));
		list.add(new BasicNameValuePair("teach_level", mTeachStudentLevel));
		try {
			String result = RegUtils.teachRegStep5ResultForHttpPost(list);
			if ("error".equalsIgnoreCase(result)) {
				Log.i(TAG, "msg=" + "error");
			} else {
				JSONObject jsonObject;
				jsonObject = new JSONObject(result.toString());
				// {"state":true,"msg":"login success"}
				String state;
				String msg;
				state = jsonObject.getString("state");
				msg = jsonObject.getString("msg");
				Log.i(TAG, "state=" + state);
				Log.i(TAG, "msg=" + msg);
				Toast.makeText(getApplicationContext(), msg, 1000).show();
				if ("true".equalsIgnoreCase(state)) {
					msgNum = 5000;
				}
			}
		} catch (Exception e) {

		}
//		msgNum = 5000;
		mHandler.sendEmptyMessage(msgNum);
	}
	void tryToRegTeacherStep6() {
		int msgNum = 6001;
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("teach_descript", mPersonResume));
		
		try {
			String result = RegUtils.teachRegStep6ResultForHttpPost(list);
			if ("error".equalsIgnoreCase(result)) {
				Log.i(TAG, "msg=" + "error");
			} else {
				JSONObject jsonObject;
				jsonObject = new JSONObject(result.toString());
				// {"state":true,"msg":"login success"}
				String state;
				String msg;
				state = jsonObject.getString("state");
				msg = jsonObject.getString("msg");
				Log.i(TAG, "state=" + state);
				Log.i(TAG, "msg=" + msg);
				Toast.makeText(getApplicationContext(), msg, 1000).show();
				if ("true".equalsIgnoreCase(state)) {
					msgNum = 6000;
				}
			}
		} catch (Exception e) {

		}
//		msgNum = 6000;
		mHandler.sendEmptyMessage(msgNum);
	}
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mDialog.dismiss();
			switch (msg.what) {
			case 1000:
				Toast.makeText(getApplicationContext(), "注册成功", 1000).show();
				applytoteacherStep11.setVisibility(View.GONE);
				applytoteacherStep12.setVisibility(View.VISIBLE);
				applytoteacherStep13.setVisibility(View.GONE);
				applytoteacherStep14.setVisibility(View.GONE);
				applytoteacherStep15.setVisibility(View.GONE);
				applytoteacherStep16.setVisibility(View.GONE);
				applytoteacherStep17.setVisibility(View.GONE);
				applytoteacherStep18.setVisibility(View.GONE);
				break;
			case 1001:
				Toast.makeText(getApplicationContext(), "注册失败", 1000).show();
				break;
			case 2000:
				Toast.makeText(getApplicationContext(), "上传成功", 1000).show();
				applytoteacherStep11.setVisibility(View.GONE);
				applytoteacherStep12.setVisibility(View.GONE);
				applytoteacherStep13.setVisibility(View.GONE);
				applytoteacherStep14.setVisibility(View.VISIBLE);
				applytoteacherStep15.setVisibility(View.GONE);
				applytoteacherStep16.setVisibility(View.GONE);
				applytoteacherStep17.setVisibility(View.GONE);
				break;
			case 2001:
				Toast.makeText(getApplicationContext(), "上传失败", 1000).show();
				break;	
			case 3000:
				Toast.makeText(getApplicationContext(), "上传成功", 1000).show();
				applytoteacherStep11.setVisibility(View.GONE);
				applytoteacherStep12.setVisibility(View.GONE);
				applytoteacherStep13.setVisibility(View.GONE);
				applytoteacherStep14.setVisibility(View.GONE);
				applytoteacherStep15.setVisibility(View.VISIBLE);
				applytoteacherStep16.setVisibility(View.GONE);
				applytoteacherStep17.setVisibility(View.GONE);
				break;
			case 3001:
				Toast.makeText(getApplicationContext(), "上传失败", 1000).show();
				break;
			case 4000:
				Toast.makeText(getApplicationContext(), "上传成功", 1000).show();
				applytoteacherStep11.setVisibility(View.GONE);
				applytoteacherStep12.setVisibility(View.GONE);
				applytoteacherStep13.setVisibility(View.GONE);
				applytoteacherStep14.setVisibility(View.GONE);
				applytoteacherStep15.setVisibility(View.GONE);
				applytoteacherStep16.setVisibility(View.VISIBLE);
				applytoteacherStep17.setVisibility(View.GONE);
				break;
			case 4001:
				Toast.makeText(getApplicationContext(), "上传失败", 1000).show();
				break;
			case 5000:
				Toast.makeText(getApplicationContext(), "上传成功", 1000).show();
				applytoteacherStep11.setVisibility(View.GONE);
				applytoteacherStep12.setVisibility(View.GONE);
				applytoteacherStep13.setVisibility(View.GONE);
				applytoteacherStep14.setVisibility(View.GONE);
				applytoteacherStep15.setVisibility(View.GONE);
				applytoteacherStep16.setVisibility(View.GONE);
				applytoteacherStep17.setVisibility(View.VISIBLE);
				break;
			case 5001:
				Toast.makeText(getApplicationContext(), "上传失败", 1000).show();
				break;
			case 6000:
				Toast.makeText(getApplicationContext(), "上传成功", 1000).show();
				applytoteacherStep11.setVisibility(View.GONE);
				applytoteacherStep12.setVisibility(View.GONE);
				applytoteacherStep13.setVisibility(View.GONE);
				applytoteacherStep14.setVisibility(View.GONE);
				applytoteacherStep15.setVisibility(View.GONE);
				applytoteacherStep16.setVisibility(View.GONE);
				applytoteacherStep17.setVisibility(View.GONE);
				applytoteacherStep18.setVisibility(View.VISIBLE);
				break;
			case 6001:
				Toast.makeText(getApplicationContext(), "上传失败", 1000).show();
				break;
			}
		};
	};
	
	// 调用系统拍照
	void getPicFromCapture() throws IOException {
		Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		String TEMP_TAKE_PHOTO_FILE_PATH = "sdcard";
		File myImageDir = new File(TEMP_TAKE_PHOTO_FILE_PATH);
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))// sd存在并可写
			// 创建图片保存目录
			if (!myImageDir.exists()) {
				// Mylog.d(THIS, "Create the path:" +
				// TEMP_TAKE_PHOTO_FILE_PATH);
				myImageDir.mkdirs();
			}

		// 根据时间来命名
		File imagFile = File.createTempFile("" + System.currentTimeMillis(),
				".jpg", myImageDir);

		tmpuri = Uri.fromFile(imagFile);

		i.putExtra(MediaStore.EXTRA_OUTPUT, tmpuri);

		startActivityForResult(i, TAKE_PHOTO_REQUEST_CODE);
	}

	// 从图库选择图片
	void getPicFromGallery() {
		Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT); // "android.intent.action.GET_CONTENT"

		innerIntent.setType("image/*"); // 查看类型

		// StringIMAGE_UNSPECIFIED="image/*";详细的类型在com.google.android.mms.ContentType中

		Intent wrapperIntent = Intent.createChooser(innerIntent, null);

		startActivityForResult(wrapperIntent, SELECT_PHOTO_REQUEST_CODE);
	}

	static Uri tmpuri;
	static int TAKE_PHOTO_REQUEST_CODE = 1;
	static int SELECT_PHOTO_REQUEST_CODE = 2;
	static int CUT_PHOTO_REQUEST_CODE = 3;

	// 返回后接收并调用系统裁剪工具

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (requestCode == RegisterActivity.TAKE_PHOTO_REQUEST_CODE
				|| requestCode == RegisterActivity.SELECT_PHOTO_REQUEST_CODE) {

			if (resultCode == RESULT_OK) {
				Uri uri = null;
				if (requestCode == RegisterActivity.SELECT_PHOTO_REQUEST_CODE) {
					uri = intent.getData();
				} else if (requestCode == RegisterActivity.TAKE_PHOTO_REQUEST_CODE) {
					uri = RegisterTeacherActivity.tmpuri;
				}
				Log.i("RegisterActivity", uri.toString());
				if (uri != null) {

					final Intent intent1 = new Intent(
							"com.android.camera.action.CROP");
					intent1.setDataAndType(uri, "image/*");
					intent1.putExtra("crop", "true");
					intent1.putExtra("aspectX", 1);
					intent1.putExtra("aspectY", 1);
					intent1.putExtra("outputX", 256);
					intent1.putExtra("outputY", 256);
					intent1.putExtra("return-data", true);
					startActivityForResult(intent1,
							RegisterActivity.CUT_PHOTO_REQUEST_CODE);

				}
			}
		} else if (requestCode == RegisterActivity.CUT_PHOTO_REQUEST_CODE) {
			if (resultCode == RESULT_OK && intent != null) {

				Uri uri = intent.getData();
				if (applytoteacherStep13.getVisibility() == View.VISIBLE) {
					uploadPersonBm = intent.getParcelableExtra("data");
					personImageHeader.setImageBitmap(uploadPersonBm);
				} else if (applytoteacherStep14.getVisibility() == View.VISIBLE) {
					uploadPersonalCertificate = intent
							.getParcelableExtra("data");
					imageViewPersonalCertificate
							.setImageBitmap(uploadPersonalCertificate);
				} else if (applytoteacherStep15.getVisibility() == View.VISIBLE) {
					uploadTeacherCertificate = intent
							.getParcelableExtra("data");
					imageViewTeacherCertificate
							.setImageBitmap(uploadTeacherCertificate);
				}
				if (RegisterTeacherActivity.tmpuri != null) {
					File myImageDir = new File(
							RegisterTeacherActivity.tmpuri.getPath());
					Log.i("RegisterActivity", "myImageDir.exists()"
							+ myImageDir.exists());
					if (myImageDir.exists())
						myImageDir.delete();
				}
			}

		}
	}
}
