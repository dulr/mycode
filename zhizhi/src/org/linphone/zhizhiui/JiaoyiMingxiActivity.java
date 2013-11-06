package org.linphone.zhizhiui;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import com.zhizhi.R;
import org.linphone.zhizhiui.data.RechargeNetworkUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JiaoyiMingxiActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jiaoyimingxi);
		try {
			RechargeNetworkUtils.getPayHistoryResultForHttpGet(1);
			// mStep1.setVisibility(View.GONE);
			// mStep2.setVisibility(View.VISIBLE);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		Button buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
