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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BankInfoActivity extends Activity {

	private static final String[] str_bank = {

	"中国银行", "中国建设银行", "中国农业银行", "中国工商银行"

	};
	EditText mbankCardID;
	EditText maccountName;
	EditText mbankAddress;
	String mStringBankName = "中国银行";
	String mStringBankCardId = null;
	String mStringBankAddress = null;
	String mStringBankAccountName = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bankinfo);
		mbankCardID = (EditText) findViewById(R.id.bankCardID);
		maccountName = (EditText) findViewById(R.id.accountName);
		mbankAddress = (EditText) findViewById(R.id.bankAddress);

		Button buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		Spinner spinner2 = (Spinner) findViewById(R.id.xuanZeYinhang);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_bank);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter);
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mStringBankName = parent.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
		// quding
		Button buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
		buttonConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mStringBankCardId = mbankCardID.getText().toString();
				if (mStringBankCardId == null
						|| mStringBankCardId.length() == 0) {
					Toast.makeText(getBaseContext(), "请输入银行卡号", 1500).show();
					return;
				}
				mStringBankAccountName = maccountName.getText().toString();
				if (mStringBankAccountName == null
						|| mStringBankAccountName.length() == 0) {
					Toast.makeText(getBaseContext(), "请输入户名", 1500).show();
					return;
				}
				mStringBankAddress = mbankAddress.getText().toString();
				if (mStringBankAddress == null
						|| mStringBankAddress.length() == 0) {
					Toast.makeText(getBaseContext(), "请输入开户地址", 1500).show();
					return;
				}
				try {

					RechargeNetworkUtils.setBankResultForHttpGet(
							mStringBankName, mStringBankCardId,
							mStringBankAccountName, mStringBankAddress);
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
