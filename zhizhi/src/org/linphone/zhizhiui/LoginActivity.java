package org.linphone.zhizhiui;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.linphone.LinphoneConfigException;
import org.linphone.LinphoneException;
import org.linphone.LinphonePreferencesActivity;
import org.linphone.LinphoneService;
import com.zhizhi.R;

import org.linphone.zhizhiui.data.JsonParser;
import org.linphone.zhizhiui.data.MyInfoData;
import org.linphone.zhizhiui.data.NetworkUtils;
import org.linphone.zhizhiui.data.UserNetworkUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Paint;
import android.graphics.Color;
public class LoginActivity extends Activity {
	private SharedPreferences mPref;
	final static String TAG = "LoginActivity";
	static final public String sSIPServerIP = "sip.linphone.org";
//	static final public String sSIPServerIP = "220.181.69.232:10606";
//	static final public String sSIPServerIP = "192.168.1.101";

	static final public String sisfirst = "isFirstStartup";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginactivity);
		mPref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		boolean first = mPref.getBoolean(sisfirst, true);
		if (first) {
			mPref.edit().putBoolean(sisfirst, false).commit();
			handleBadConfig();
		}
		final EditText userName = (EditText) findViewById(R.id.editTextUserName);
		final EditText passWord = (EditText) findViewById(R.id.editTextPassword);
		final Button Confirm = (Button) findViewById(R.id.buttonConfirm);

		Confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String user = userName.getText().toString();
				String pw = passWord.getText().toString();

				mPref.edit().putString(getString(R.string.pref_username_key),
						user).commit();
				mPref.edit().putString(getString(R.string.pref_passwd_key), pw).commit();
				mPref.edit().putString(getString(R.string.pref_domain_key),
						sSIPServerIP).commit();

				try {
					String str = NetworkUtils
							.getLoginResultForHttpGet(user, pw);
					if ("error".equalsIgnoreCase(str)) {
						Toast.makeText(getApplicationContext(), "登录失败", 1000)
								.show();
						Log.i(TAG, "msg=" + "error");
					} else {
						JSONObject jsonObject = new JSONObject(str.toString());
						// {"state":true,"msg":"login success"}
						String state;
						String msg;
						state = jsonObject.getString("state");
						msg = jsonObject.getString("msg");
						Log.i(TAG, "state=" + state);
						Log.i(TAG, "msg=" + msg);

						if ("true".equalsIgnoreCase(state)) {
							// Intent intent = new Intent();
							// intent.setClass(LoginActivity.this,
							// MainViewActivity.class);
							// startActivity(intent);
							Toast.makeText(getApplicationContext(), "登录成功", 1000)
							.show();

							try {
					            String straaaa = UserNetworkUtils.getMyInfoResultForHttpGet();
					            MyInfoData mMyInfoData = JsonParser.ParserMyInfoData(straaaa);
					            LinphoneService.instance().username = mMyInfoData.mName;
	                            LinphoneService.instance().password = "123456";
					        } catch (ClientProtocolException e) {
					            e.printStackTrace();
					        } catch (IOException e) {
					            e.printStackTrace();
					        } catch (JSONException e) {
					            e.printStackTrace();
					        }

//                            if (user.equals("10010")) {
//                              LinphoneService.instance().username = "dule741";
//                            } else {
//                                LinphoneService.instance().username = "dule742";
//                            }

//							LinphoneService.instance().username = user;
//							LinphoneService.instance().password = pw;
//                            LinphoneService.instance().password = "123456";
							LinphoneService.instance().domain = sSIPServerIP;
							 try {
									if (LinphoneService.instance() == null) {
										Intent lLinphoneServiceIntent = new Intent(Intent.ACTION_MAIN);
										lLinphoneServiceIntent.setClass(LoginActivity.this, LinphoneService.class);
										startService(lLinphoneServiceIntent);
									}
									LinphoneService.instance().initFromConf();
									
//									public void tryToGetMyInfo() 
									{
										try {
											str = UserNetworkUtils.getMyInfoResultForHttpGet();
											MainViewActivity.mMyInfoData = JsonParser.ParserMyInfoData(str);
										} catch (ClientProtocolException e) {
											e.printStackTrace();
										} catch (IOException e) {
											e.printStackTrace();
										} catch (JSONException e) {
											e.printStackTrace();
										}
									}
								} catch (Exception e) {

										handleBadConfig();
										finish();
										return;

								}
								
							finish();
						} else {
							Toast.makeText(getApplicationContext(), "登录失败",
									1000).show();
						}
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "登录失败", 1000)
							.show();
				}

				finish();
			}
		});
		final Button Cancel = (Button) findViewById(R.id.buttonCancel);

		Cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		final TextView Register = (TextView) findViewById(R.id.buttonRegister);
		Register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		Register.getPaint().setAntiAlias(true);// 抗锯齿
		Register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
				finish();
			}
		});
		final TextView Forgot = (TextView) findViewById(R.id.buttonForgot);
		Forgot.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		Forgot.getPaint().setAntiAlias(true);// 抗锯齿
		Forgot.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent();
				// intent.setClass(LoginActivity.this, MainViewActivity.class);
				// startActivity(intent);
				finish();
			}
		});
	}
	public void initFromConf() throws LinphoneException {
		try {
			LinphoneService.instance().initFromConf();
		} catch (LinphoneConfigException e) {
			handleBadConfig();
		}
	}

	private void handleBadConfig() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setClass(this, LinphonePreferencesActivity.class);
		startActivity(intent);
		finish();
	}

}
