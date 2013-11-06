package org.linphone.zhizhiui;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import com.zhizhi.R;
import org.linphone.zhizhiui.data.NetworkUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyLoginPasswordActivity extends Activity {
	EditText meditTextNewPw;
	EditText meditTextCurrentPW;

	String mCurrentPw;
	String mNewPw;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modifyloginpasswordactivity);
		meditTextNewPw = (EditText) findViewById(R.id.editTextNewPw);
		meditTextCurrentPW = (EditText) findViewById(R.id.editTextCurrentPW);

		Button buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		Button buttonQueding = (Button) findViewById(R.id.buttonQueding);
		buttonQueding.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mCurrentPw = meditTextCurrentPW.getText().toString();
				if (mCurrentPw == null || mCurrentPw.length() == 0) {
					Toast.makeText(getBaseContext(), "请输入当前密码", 1500).show();
					return;
				}
				mNewPw = meditTextNewPw.getText().toString();
				if (mNewPw == null || mNewPw.length() == 0) {
					Toast.makeText(getBaseContext(), "请输入新密码", 1500).show();
					return;
				}
				try {
					String str = NetworkUtils.getResetPWResultForHttpGet(
							mCurrentPw, mNewPw);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finish();
			}
		});
	}
}
