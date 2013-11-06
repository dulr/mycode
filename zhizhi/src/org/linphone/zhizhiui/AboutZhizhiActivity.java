package org.linphone.zhizhiui;

import com.zhizhi.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AboutZhizhiActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutzhizhi);

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
		TextView AboutZhizhiText  = (TextView) findViewById(R.id.AboutZhizhiText);
		AboutZhizhiText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		AboutZhizhiText.getPaint().setAntiAlias(true);// 抗锯齿
		AboutZhizhiText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("http://open.zhizhi.com"));
//		        it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
		       startActivity(it);
			}
		});
		
		Button buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
