package org.linphone.zhizhiui;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.linphone.zhizhiui.data.YaoqingNetworkUtils;

import com.zhizhi.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ZhizhiYaoqingActivity extends Activity {

	protected static final String TAG = "ZhizhiYaoqingActivity";
	TextView textviewYaoqing;
	TextView textviewYaoqingzhong;
	ImageView ImageViewYaoqingzhong;
	Button buttonYaoqing;
	Button buttonQuxiao;
	Button buttonJieshouYaoqing;
	Button buttonRejectYaoqing;

	void initView() {
		textviewYaoqing = (TextView) findViewById(R.id.textviewYaoqing);
		textviewYaoqingzhong = (TextView) findViewById(R.id.textviewYaoqingzhong);
		ImageViewYaoqingzhong = (ImageView) findViewById(R.id.ImageViewYaoqingzhong);
		buttonYaoqing = (Button) findViewById(R.id.buttonYaoqing);
		buttonQuxiao = (Button) findViewById(R.id.buttonQuxiao);
		buttonJieshouYaoqing = (Button) findViewById(R.id.buttonJieshouYaoqing);
		buttonRejectYaoqing = (Button) findViewById(R.id.buttonRejectYaoqing);
	}

	String mname;
	String mMid;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhizhiyaoqing);
		mname = getIntent().getStringExtra("name");
		mMid = getIntent().getIntExtra("mid", 1) + "";
		// 编辑资料
		// Button buttonChongZhi2 = (Button) findViewById(R.id.buttonChongZhi);
		// buttonChongZhi2.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Intent intent = new Intent();
		// intent.setClass(TixianActivity.this, ChongzhiActivity.class);
		// startActivity(intent);
		// }
		// });
		initView();
		// TextView AboutZhizhiText = (TextView)
		// findViewById(R.id.AboutZhizhiText);
		// AboutZhizhiText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		// AboutZhizhiText.getPaint().setAntiAlias(true);// 抗锯齿
		// AboutZhizhiText.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Intent it = new Intent(Intent.ACTION_VIEW,
		// Uri.parse("http://open.zhizhi.com"));
		// // it.setClassName("com.android.browser",
		// "com.android.browser.BrowserActivity");
		// startActivity(it);
		// }
		// });
		//
		buttonYaoqing = (Button) findViewById(R.id.buttonYaoqing);
		buttonYaoqing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				textviewYaoqing.setVisibility(View.GONE);
				textviewYaoqingzhong.setText("您正在邀请" + mname
						+ "直接通过手机通话，请等待对方应答...");
				textviewYaoqingzhong.setVisibility(View.VISIBLE);
				ImageViewYaoqingzhong.setVisibility(View.VISIBLE);
				buttonYaoqing.setVisibility(View.GONE);

				try {
					// String str =
					// YaoqingNetworkUtils.noticepassResultForHttpGet(1);
					//
					// JSONObject jsonObject = new JSONObject(str.toString());
					// String state = jsonObject.getString("state");
					// String msg = jsonObject.getString("msg");
					// Log.i(TAG, "strmTotalUser=" + state);
					// Log.i(TAG, "strmOnlineUser=" + msg);

					String str = YaoqingNetworkUtils
							.noticesendResultForHttpGet(mMid, "aaa");
					JSONObject jsonObject = new JSONObject(str.toString());
					String state = jsonObject.getString("state");
					String msg = jsonObject.getString("msg");
					Log.i(TAG, "state=" + state);
					Log.i(TAG, "msg=" + msg);
					Toast.makeText(getApplicationContext(), msg, 1500).show();
					

					 str = YaoqingNetworkUtils
						.noticegetLastReceiveResultForHttpGet();
					 
					 str = YaoqingNetworkUtils
						.noticegetLastRequestResultForHttpGet();

				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		buttonQuxiao = (Button) findViewById(R.id.buttonQuxiao);
		buttonQuxiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String str;
				try {
					str = YaoqingNetworkUtils.noticepassResultForHttpGet(mMid,
							"N");
					JSONObject jsonObject = new JSONObject(str.toString());
					String state = jsonObject.getString("state");
					String msg = jsonObject.getString("msg");
					Log.i(TAG, "strmTotalUser=" + state);
					Log.i(TAG, "strmOnlineUser=" + msg);
					Toast.makeText(getApplicationContext(), msg, 1500).show();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				finish();
			}
		});

		buttonRejectYaoqing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String str;
				try {
					str = YaoqingNetworkUtils.noticepassResultForHttpGet(mMid,
							"N");
					JSONObject jsonObject = new JSONObject(str.toString());
					String state = jsonObject.getString("state");
					String msg = jsonObject.getString("msg");
					Log.i(TAG, "state=" + state);
					Log.i(TAG, "msg=" + msg);
					Toast.makeText(getApplicationContext(), msg, 1500).show();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				finish();
			}
		});

		buttonJieshouYaoqing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String str;
				try {
					str = YaoqingNetworkUtils.noticepassResultForHttpGet(mMid,
							"Y");
					JSONObject jsonObject = new JSONObject(str.toString());
					String state = jsonObject.getString("state");
					String msg = jsonObject.getString("msg");
					Log.i(TAG, "state=" + state);
					Log.i(TAG, "msg=" + msg);
					Toast.makeText(getApplicationContext(), msg, 1500).show();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				finish();
			}
		});

	}
}
