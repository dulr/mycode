package org.linphone.zhizhiui;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.linphone.LinphoneConfigException;
import org.linphone.LinphoneException;
import org.linphone.LinphonePreferencesActivity;
import org.linphone.LinphoneService;
import com.zhizhi.R;
import org.linphone.zhizhiui.data.JsonParser;
import org.linphone.zhizhiui.data.MainViewListData;
import org.linphone.zhizhiui.data.NetworkUtils;
import org.linphone.zhizhiui.data.UploadFileUtils;
import org.linphone.zhizhiui.data.UserNetworkUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class StartupActivity extends Activity {
	protected static final String TAG = "StartupActivity";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (LinphoneService.instance() != null) {
			LoadMainActivity();
			finish();
		}
		setContentView(R.layout.startupactivity);

//		 handler.sendEmptyMessageDelayed(1, 2000);
		Intent lLinphoneServiceIntent = new Intent(Intent.ACTION_MAIN);
		lLinphoneServiceIntent.setClass(this, LinphoneService.class);
		startService(lLinphoneServiceIntent);
		new Thread() {
			public void run() {
				tryDownData();
			};
		}.start();
		
	}

	void LoadMainActivity() {
		Intent intent = new Intent();
		intent.setClass(StartupActivity.this, MainViewActivity.class);
		startActivity(intent);
		finish();
	}
    boolean isDownFinish = false;
	void tryDownData() {
		MainViewListData data = new MainViewListData();

		try {
			handler.sendEmptyMessageDelayed(2, 30000);
			String str = NetworkUtils.getSearchResultForHttpGet("teachlevel",
					"desc", 1);
			
			JSONObject jsonObject = new JSONObject(str.toString());

			// {"data":
			// [
			// {"mid":"1","profile":"http:\/\/open.zhizhi.com\/data\/profile\/mid1.png","isonline":"Y","name":"nomit","price":"22.00","sign":null,"points":"1"},
			// {"mid":"2","profile":"http:\/\/open.zhizhi.com\/data\/default\/sex0.png","isonline":"Y","name":"test","price":"0.00","sign":"hahah","points":"2"}
			// ],
			// "total":"1","online":"3"}

			MainViewActivity.mTotalUser = jsonObject.getString("total");
			MainViewActivity.mOnlineUser = jsonObject.getString("online");
			Log.i(TAG, "mTotalUser=" + MainViewActivity.mTotalUser);
			Log.i(TAG, "mOnlineUser=" + MainViewActivity.mOnlineUser);

//			JSONObject jsonDataObject = jsonObject.getJSONObject("data");
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			MainViewActivity.mListAllData = JsonParser
					.ParserMainViewListData(jsonArray);
			isDownFinish = true;
			handler.sendEmptyMessageDelayed(1, 1000);
			tryToDownloadFile();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		handler.sendEmptyMessage(1);
	}

	void downLoadPersonImage() {
		for (int i = 0; i < MainViewActivity.mListAllData.size(); i++) {
			try {
				String str = MainViewActivity.mListAllData.get(i).mPersonImageDrawablePath;
				str = str.replace("open.zhizhi.com", NetworkUtils.HostDownLoadIP);
				NetworkUtils.download(str, UploadFileUtils.filename,
						MainViewActivity.mListAllData.get(i).mMid + ".zz");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
//		handler.sendEmptyMessage(1);
	}

	void tryToDownloadFile() {
		new Thread() {
			public void run() {
				downLoadPersonImage();
			};
		}.start();
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 1:
				LoadMainActivity();
				break;
			case 2:
				if (!isDownFinish) {
					Toast.makeText(StartupActivity.this, "网络连接失败，请检查网络设置", 1500)
							.show();
					handler.sendEmptyMessageDelayed(3, 1000);
				}
					
				break;
			case 3:
				new Thread() {
					public void run() {
						Intent aa = new Intent(Intent.ACTION_MAIN);
						aa.setClass(StartupActivity.this, LinphoneService.class);
						stopService(aa);
					};
				}.start();
				finish();
				break;
			case -1:

				break;
			}

		}
	};
	
}
