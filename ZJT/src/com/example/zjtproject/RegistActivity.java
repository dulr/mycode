package com.example.zjtproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.zjtproject.network.CommonApi;
import com.example.zjtproject.network.DES;
import com.example.zjtproject.network.NetworkUtils;
import com.example.zjtproject.network.SuijiShu;
import com.example.zjtproject.network.UserApi;
import com.example.zjtproject.utils.Utils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class RegistActivity extends Activity {
	TextView mRegister_login, mRegister_back;
	Button mTijiao, mButtonYanzhengma;
	EditText meditTextname, meditTextpassword, meditTextmobile,
			meditTextyanzhengma;

	public void initEditText() {
		meditTextname = (EditText) findViewById(R.id.editTextname);
		meditTextpassword = (EditText) findViewById(R.id.editTextpassword);
		meditTextmobile = (EditText) findViewById(R.id.editTextmobile);
		meditTextyanzhengma = (EditText) findViewById(R.id.editTextyanzhengma);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initEditText();

		mRegister_login = (TextView) findViewById(R.id.Register_login);
		mRegister_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(RegistActivity.this, LoginActivity.class);
				startActivity(intent);
			}

		});
		mRegister_back = (TextView) findViewById(R.id.Register_back);
		mRegister_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}

		});
		// 发送验证码
		mButtonYanzhengma = (Button) findViewById(R.id.buttonYanzhengma);
		mButtonYanzhengma.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String moboile = meditTextmobile.getText().toString();
				if (moboile == null)
					return;// 加提示语
				List<NameValuePair> list = new ArrayList<NameValuePair>();

				list.add(new BasicNameValuePair("mobile", moboile));
				list.add(new BasicNameValuePair("content", "0"));
				try {
					list.add(new BasicNameValuePair(
							"token",
							DES.encryptDES(
									SuijiShu.getToken(MainActivity.mTimediffwithserver),
									DES.KEY)));
					CommonApi.SendShortMessage(list);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		//提交
		mTijiao = (Button) findViewById(R.id.tijiao);
		mTijiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stubmeditTextname, meditTextpassword, meditTextmobile,
				///meditTextyanzhengma;
				String nickname = meditTextname.getText().toString();
				String password = meditTextpassword.getText().toString();
				String moboile = meditTextmobile.getText().toString();
				String yanzhengma = meditTextyanzhengma.getText().toString();
				
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				list.add(new BasicNameValuePair("Nickname", nickname));
				list.add(new BasicNameValuePair("PWD", password));
				list.add(new BasicNameValuePair("Mobile",moboile));
				list.add(new BasicNameValuePair("VerificationCode", yanzhengma));
				try {
					list.add(new BasicNameValuePair(
							"token",
							DES.encryptDES(
									SuijiShu.getToken(MainActivity.mTimediffwithserver),
									DES.KEY)));
				String result =	UserApi.Register(list);
				//{"Success":true,"ErrorInfo":null,"ResponseData":false} 12333 111111 13911329715
				  JSONObject jsonObject = new JSONObject(result.toString());
				  String Success;
			        String ErrorInfo;
			        String ResponseData;

			        Success = jsonObject.getString("Success");
			        ErrorInfo = jsonObject.getString("ErrorInfo");

			        ResponseData = jsonObject.getString("ResponseData");

                    Utils.Logi(NetworkUtils.TAG, "Success=" + Success +ErrorInfo + ResponseData);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
	}
}
