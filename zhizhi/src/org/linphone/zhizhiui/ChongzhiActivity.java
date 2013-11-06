package org.linphone.zhizhiui;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import com.zhizhi.R;
import org.linphone.zhizhiui.data.RechargeNetworkUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ChongzhiActivity extends Activity {

	LinearLayout mStep1;
	LinearLayout mStep2;
	LinearLayout mStep3;

	EditText mexitTextChongzhiJinEr;
	EditText meditTextYanzhengma;

	String mChongzhiJiner;
	String mYanzhengma;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chongzhi);
		mexitTextChongzhiJinEr = (EditText) findViewById(R.id.exitTextChongzhiJinEr);
		meditTextYanzhengma = (EditText) findViewById(R.id.editTextYanzhengma);

		mStep1 = (LinearLayout) findViewById(R.id.chongzhiStep1);
		mStep2 = (LinearLayout) findViewById(R.id.chongzhiStep2);
		mStep3 = (LinearLayout) findViewById(R.id.chongzhiStep3);
		Button buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		Button buttonStep1Confirm = (Button) findViewById(R.id.buttonStep1Confirm);
		buttonStep1Confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mChongzhiJiner = mexitTextChongzhiJinEr.getText().toString();
				if (mChongzhiJiner == null || mChongzhiJiner.length() == 0) {
					Toast.makeText(getBaseContext(), "��������", 1500).show();
					return;
				}
				mYanzhengma = meditTextYanzhengma.getText().toString();
				if (mYanzhengma == null || mYanzhengma.length() == 0) {
					Toast.makeText(getBaseContext(), "��������֤��", 1500)
							.show();
					return;
				}
				try {
					RechargeNetworkUtils.doRechargeResultForHttpGet(
							mChongzhiJiner, mYanzhengma);
					// mStep1.setVisibility(View.GONE);
					// mStep2.setVisibility(View.VISIBLE);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
