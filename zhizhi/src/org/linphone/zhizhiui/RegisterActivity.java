package org.linphone.zhizhiui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
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
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	private static final String[] str_chushangnian = { "2012", "2011", "2010",
			"2009", "2008", "2007", "2006", "2005", "2004", "2003","2002","2001","2000",
			"1999","1998","1997","1996","1995","1994","1993","1992","1991","1990",
			"1989","1988","1987","1986","1985","1984","1983","1982","1981","1980",
			"1979","1978","1977","1976","1975","1974","1973","1972","1971","1970",
			"1969","1968","1967","1966","1965","1964","1963","1962","1961","1960",
			"1959","1958","1957","1956","1955","1954","1953","1952","1951","1950",
			"1949","1948","1947","1946","1945","1944","1943","1942","1941","1940",
			"1939","1938","1937","1936","1935","1934","1933","1932","1931","1930"};
	private static final String[] str_chushanyue = { "01", "02", "03", "04",
			"05", "06", "07", "08", "09", "10", "11", "12" };
	private static final String[] str_guoji = { "中国", "英国", "美国", "加拿大", "日本",
			"法国", "德国", "印度", "其他" };
	private static final String[] str_muqiansuozaidi = { "北京", "上海", "广州",
			"深圳", "国内其他城市", "国外其他 城市" };
	final static String TAG = "RegisterActivity";
	RadioButton mTeacher;
	RadioButton mStudent;
	boolean mIsTeacherReg;
	TextView mRegisterStep;

	Button mbuttonBendiZhaopian;
	Button mbuttonPaizhaoShangchuan;

	void setRegisterStepText(String step) {
		mRegisterStep.setText(step);
	}
	Spinner spinnerBirthYear;
	Spinner spinnerBirthMonth;
	Spinner spinnerCountry;
	Spinner spinnerCurrentPlace;
	RadioGroup radiogroupSex;
	RadioButton radiobuttonfeMale;
	RadioButton radiobuttonSexMale;
	RadioGroup radiogroupEnglishLevel;
	RadioButton radiobutton1;
	RadioButton radiobutton2;
	RadioButton radiobutton3;
	RadioButton radiobutton4;
	RadioButton radiobutton5;
	
	String mBirthYear = "2012";
	String mBirthMonth = "01";
	String mSex = "U";
	String mCountry = "中国";
	String mCurrentPlace = "北京";
	String mEnglishLevel = "中级（4~6级水平）";
	void initStep3() {
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

		radiogroupEnglishLevel = (RadioGroup) findViewById(R.id.radiogroupEnglishLevel);
		radiobutton1 = (RadioButton) findViewById(R.id.radiobutton1);
		radiobutton2 = (RadioButton) findViewById(R.id.radiobutton2);
		radiobutton3 = (RadioButton) findViewById(R.id.radiobutton3);
		radiobutton4 = (RadioButton) findViewById(R.id.radiobutton4);
		radiobutton5 = (RadioButton) findViewById(R.id.radiobutton5);
		radiogroupEnglishLevel
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// 根据ID判断选择的按钮
						switch (checkedId) {
						case R.id.radiobutton1: {
							mEnglishLevel = radiobutton1.getText().toString();
						}
							break;
						case R.id.radiobutton2: {
							mEnglishLevel = radiobutton2.getText().toString();
						}
							break;
						case R.id.radiobutton3: {
							mEnglishLevel = radiobutton3.getText().toString();
						}
							break;
						case R.id.radiobutton4: {
							mEnglishLevel = radiobutton4.getText().toString();
						}
							break;
						case R.id.radiobutton5: {
							mEnglishLevel = radiobutton5.getText().toString();
						}
							break;
						}
					}
				});

	 
	spinnerBirthYear = (Spinner) findViewById(R.id.spinnerBirthYear);
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, str_chushangnian);
	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	spinnerBirthYear.setAdapter(adapter);
	spinnerBirthYear.setOnItemSelectedListener(new OnItemSelectedListener() {
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
	spinnerBirthMonth = (Spinner) findViewById(R.id.spinnerBirthMonth);
	ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, str_chushanyue);
	adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	spinnerBirthMonth.setAdapter(adapter2);
	spinnerBirthMonth.setOnItemSelectedListener(new OnItemSelectedListener() {
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
	
	spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
	ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, str_guoji);
	adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	spinnerCountry.setAdapter(adapter3);
	spinnerCountry.setOnItemSelectedListener(new OnItemSelectedListener() {
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
	
	spinnerCurrentPlace = (Spinner) findViewById(R.id.spinnerCurrentPlace);
	ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, str_muqiansuozaidi);
	adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	spinnerCurrentPlace.setAdapter(adapter4);
	spinnerCurrentPlace.setOnItemSelectedListener(new OnItemSelectedListener() {
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
}
	void init() {
		personImageHeader = (ImageView) findViewById(R.id.personImageHeader);
		mtextviewPhoneNum = (EditText) findViewById(R.id.editTextPhoneNum);
		meditTextPetName = (EditText) findViewById(R.id.editTextPetName);
		meditTextPassWord = (EditText) findViewById(R.id.editTextPassWord);
		editTextGenRenJianJie = (EditText) findViewById(R.id.editTextGenRenJianJie);
		
		Button buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mTeacher = (RadioButton) findViewById(R.id.radiobuttonTeacher);
		mStudent = (RadioButton) findViewById(R.id.radiobuttonStudent);

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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	EditText mtextviewPhoneNum;
	EditText meditTextPetName;
	EditText meditTextPassWord;
	String mPhoneNum;
	String mUserName;
	String mPassWord;

	ImageView personImageHeader;
	Bitmap uploadBm = null;
	ProgressDialog mDialog;
	EditText editTextGenRenJianJie;
	
	LinearLayout registerStep11;
	LinearLayout registerStep12;
	LinearLayout registerStep13;
	LinearLayout registerStepWanshanZiliao4;
	LinearLayout registerStepGerenJianJie;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registeractivity);
		mDialog = ZhizhiUtils.createProgressDialog(RegisterActivity.this);
		init();
		
		initStep3();
		
		mRegisterStep = (TextView) findViewById(R.id.textViewRegisterStep);

		registerStep11 = (LinearLayout) findViewById(R.id.registerStep1);
		registerStep12 = (LinearLayout) findViewById(R.id.registerStep2);
		registerStep13 = (LinearLayout) findViewById(R.id.registerStep3);
		registerStepWanshanZiliao4 = (LinearLayout) findViewById(R.id.registerStepWanshanZiliao);
		registerStepGerenJianJie = (LinearLayout) findViewById(R.id.registerStepGerenJianJie);
		registerStep11.setVisibility(View.VISIBLE);

		Button buttonStep1Next1 = (Button) findViewById(R.id.buttonStep1Next);
		Button buttonStep2Next1 = (Button) findViewById(R.id.buttonStep2Next);
		Button buttonStep3Next1 = (Button) findViewById(R.id.buttonStep3Next);
		Button buttonWanchengZiliaoNex1 = (Button) findViewById(R.id.buttonWanchengZiliaoNext);
		Button buttonGeRenJianJieFinish = (Button) findViewById(R.id.buttonGeRenJianJieFinish);
		TextView textviewTiaoguoTouXiang = (TextView) findViewById(R.id.textviewTiaoguoTouXiang);
		buttonStep1Next1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setRegisterStepText("第二步");
				mIsTeacherReg = mTeacher.isChecked();
				registerStep11.setVisibility(View.GONE);
				registerStep12.setVisibility(View.VISIBLE);
				registerStep13.setVisibility(View.GONE);
				registerStepWanshanZiliao4.setVisibility(View.GONE);
			}
		});
		buttonStep2Next1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				mPhoneNum = mtextviewPhoneNum.getText().toString();
				if (mPhoneNum == null || mPhoneNum.length() == 0) {
					Toast.makeText(getBaseContext(), "请输入手机号码", 1000).show();
					return;
				}
				mUserName = meditTextPetName.getText().toString();
				if (mUserName == null || mUserName.length() == 0) {
					Toast.makeText(getBaseContext(), "请输入昵称", 1000).show();
					return;
				}
				mPassWord = meditTextPassWord.getText().toString();
				if (mPassWord == null || mPassWord.length() == 0) {
					Toast.makeText(getBaseContext(), "请输入密码", 1000).show();
					return;
				}
				mDialog = ZhizhiUtils.createProgressDialog(RegisterActivity.this,"正在验证用户名");
				mDialog.show();
				tryToRegisterStep2();
			}

			
		});
		buttonStep3Next1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDialog = ZhizhiUtils.createProgressDialog(RegisterActivity.this,"正在注册...");
				mDialog.show();
				tryToRegisterStep3();
			}
		});
		buttonWanchengZiliaoNex1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(uploadBm != null) {
					mDialog = ZhizhiUtils.createProgressDialog(RegisterActivity.this,"正在上传中");
					mDialog.show();
					tryToUploadFile4();
				} else {
					Toast.makeText(RegisterActivity.this, "请选择图片", 1000).show();
				}
			}
		});
		textviewTiaoguoTouXiang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setRegisterStepText("完成");
				registerStep11.setVisibility(View.GONE);
				registerStep12.setVisibility(View.GONE);
				registerStep13.setVisibility(View.GONE);
				registerStepWanshanZiliao4.setVisibility(View.GONE);
				registerStepGerenJianJie.setVisibility(View.VISIBLE);}
		});
		
		buttonGeRenJianJieFinish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDialog = ZhizhiUtils.createProgressDialog(RegisterActivity.this,"正在完成注册...");
				mDialog.show();
				tryToRegisterStep5();
			}
		});
	}

	void tryToRegisterStep5() {
		String resume = editTextGenRenJianJie.getText().toString();
		int msgNum = 5001;
		if (resume != null) {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("resume", resume));

			try {
				String result = RegUtils.stuRegStep5ResultForHttpPost(list);
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
		}
//		msgNum = 5000;
		mHandler.sendEmptyMessage(msgNum);
	}
	void tryToUploadFile4() {
		new Thread() {
			public void run() {
				File tempFile = UploadFileUtils.savaBitmap(uploadBm);
				int msg = 4001;
				try {
					boolean uploaded = RegUtils.stuRegStep4ResultForHttpPost(tempFile);	
					if(uploaded) {
						msg =4000;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tempFile.delete();
				mHandler.sendEmptyMessage(msg);

			};
		}.start();
	}
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mDialog.dismiss();
			switch (msg.what) {
			case 999:
				mDialog.show();
				break;
			case 2000:
				LinphoneService.instance().username = mUserName;
				LinphoneService.instance().password = mPassWord;
				// LinphoneService.instance().domain =
				// "192.168.1.102";
				if (mIsTeacherReg) {
					Intent intent = new Intent();
					intent.putExtra("UserName", mUserName);
					intent.putExtra("PassWord", mPassWord);
					intent.setClass(RegisterActivity.this,
							RegisterTeacherActivity.class);
					startActivity(intent);
					finish();
				} else {
					setRegisterStepText("第三步");
					registerStep11.setVisibility(View.GONE);
					registerStep12.setVisibility(View.GONE);
					registerStep13.setVisibility(View.VISIBLE);
					registerStepWanshanZiliao4
							.setVisibility(View.GONE);
				}
			break;
			case 2001:
				Toast.makeText(getApplicationContext(), "注册失败", 1000).show();
				NetworkUtils.cookie = null;
				break;
			case 3000:
				setRegisterStepText("第四步");
				Toast.makeText(getApplicationContext(), "注册成功", 1000).show();
				registerStep11.setVisibility(View.GONE);
				registerStep12.setVisibility(View.GONE);
				registerStep13.setVisibility(View.GONE);
				registerStepWanshanZiliao4.setVisibility(View.VISIBLE);
				break;
			case 3001:
				Toast.makeText(getApplicationContext(), "注册失败", 1000).show();
				break;
			case 4000:
				Toast.makeText(RegisterActivity.this, "上传成功", 1000).show();
				setRegisterStepText("完成");
				registerStep11.setVisibility(View.GONE);
				registerStep12.setVisibility(View.GONE);
				registerStep13.setVisibility(View.GONE);
				registerStepWanshanZiliao4.setVisibility(View.GONE);
				registerStepGerenJianJie.setVisibility(View.VISIBLE);
				break;
			case 4001:
				Toast.makeText(RegisterActivity.this, "上传失败", 1000).show();
				break;
			case 5000:
				setRegisterStepText("完成");
				registerStep11.setVisibility(View.GONE);
				registerStep12.setVisibility(View.GONE);
				registerStep13.setVisibility(View.GONE);
				registerStepWanshanZiliao4.setVisibility(View.GONE);
				registerStepGerenJianJie.setVisibility(View.GONE);
				Intent intent = new Intent();
				intent.setClass(RegisterActivity.this, MyspaceActivity.class);
				startActivity(intent);
				finish();
				break;
			case 5001:
				Toast.makeText(RegisterActivity.this, "上传简介失败", 1000).show();
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

	private static Uri tmpuri;
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
					uri = RegisterActivity.tmpuri;
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
				uploadBm = intent.getParcelableExtra("data");
				personImageHeader.setImageBitmap(uploadBm);

				if (RegisterActivity.tmpuri != null) {
					File myImageDir = new File(
							RegisterActivity.tmpuri.getPath());
					Log.i("RegisterActivity", "myImageDir.exists()"
							+ myImageDir.exists());
					if (myImageDir.exists())
						myImageDir.delete();
				}
			}

		}
	}

	private void tryToRegisterStep3() {
		int msgNum = 3001;
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("isTeacher", "N"));
		list.add(new BasicNameValuePair("username", mUserName));
		list.add(new BasicNameValuePair("password", mPassWord));
		list.add(new BasicNameValuePair("sex", mSex));
		list.add(new BasicNameValuePair("country", mCountry));
		list.add(new BasicNameValuePair("BirthYear", mBirthYear));
		list.add(new BasicNameValuePair("BirthMonth", mBirthMonth));
		list.add(new BasicNameValuePair("CurrentPlace", mCurrentPlace));
		list.add(new BasicNameValuePair("EnglishLevel", mEnglishLevel));
		
		try {
			String result = RegUtils.stuRegStep3ResultForHttpPost(list);
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
//				Toast.makeText(getApplicationContext(), msg, 1000).show();
				if ("true".equalsIgnoreCase(state)) {
					msgNum = 3000;
				}
			}
		} catch (Exception e) {

		}
//		msgNum = 3000;
		mHandler.sendEmptyMessage(msgNum);
	}

	private void tryToRegisterStep2() {
		int msgNum = 2001;
		try {
			String str = NetworkUtils.getRegisterResultForHttpGet(mUserName,
					mPassWord);
			if ("error".equalsIgnoreCase(str)) {
				Log.i(TAG, "msg=" + "error");
			} else {
				JSONObject jsonObject;

				jsonObject = new JSONObject(str.toString());

				// {"state":true,"msg":"login success"}
				String state;
				String msg;
				state = jsonObject.getString("state");
				msg = jsonObject.getString("msg");
				Log.i(TAG, "state=" + state);
				Log.i(TAG, "msg=" + msg);
//				Toast.makeText(getApplicationContext(), msg, 1000).show();
				if ("true".equalsIgnoreCase(state)) {
					msgNum = 2000;
				}
			}
		} catch (Exception e) {

		}
		mHandler.sendEmptyMessage(msgNum);
	}
}
