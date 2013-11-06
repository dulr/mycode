package org.linphone.zhizhiui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.zhizhi.R;
import org.linphone.zhizhiui.data.CommentsNetworkUtils;
import org.linphone.zhizhiui.data.CommnetsData;
import org.linphone.zhizhiui.data.JsonParser;
import org.linphone.zhizhiui.data.MyInfoData;
import org.linphone.zhizhiui.data.StaInfoData;
import org.linphone.zhizhiui.data.UploadFileUtils;
import org.linphone.zhizhiui.data.UserInfoData;
import org.linphone.zhizhiui.data.UserNetworkUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyspaceActivity extends Activity {
	protected static final String TAG = "MyspaceActivity";
	MyInfoData mMyInfoData;
	StaInfoData mStaInfoData;

	public void tryToGetStaInfo() {
		try {
			String str = UserNetworkUtils.getStaListResultForHttpGet();
			mStaInfoData = JsonParser.ParserStaInfoData(str);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	List<CommnetsData> mCommnetsData;
	int mCommentsCount;

	public void tryToGetCommentsInfo() {
		try {
			String str = CommentsNetworkUtils.getListcommentResultForHttpGet(1,
					mMyInfoData.mMid);
			JSONObject jsonObject;
			jsonObject = new JSONObject(str.toString());
			mCommentsCount = jsonObject.getInt("count");

			Log.i(TAG, "mCommentsCount=" + mCommentsCount);

			JSONArray jsonArray = jsonObject.getJSONArray("data");
			mCommnetsData = JsonParser.ParserCommentList(jsonArray);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	TextView[] mtextViewCommentsContent = new TextView[5];
	// TextView mtextViewCommentsContent2;
	// TextView mtextViewCommentsContent3;
	// TextView mtextViewCommentsContent4;
	// TextView mtextViewCommentsContent5;
	TextView[] mtextViewCommentsFrom = new TextView[5];

	// TextView mtextViewCommentsFrom2;
	// TextView mtextViewCommentsFrom3;
	// TextView mtextViewCommentsFrom4;
	// TextView mtextViewCommentsFrom5;

	void initCommnetsList() {
		mtextViewCommentsContent[0] = (TextView) findViewById(R.id.textViewCommentsContent1);
		mtextViewCommentsContent[1] = (TextView) findViewById(R.id.textViewCommentsContent2);
		mtextViewCommentsContent[2] = (TextView) findViewById(R.id.textViewCommentsContent3);
		mtextViewCommentsContent[3] = (TextView) findViewById(R.id.textViewCommentsContent4);
		mtextViewCommentsContent[4] = (TextView) findViewById(R.id.textViewCommentsContent5);
		mtextViewCommentsFrom[0] = (TextView) findViewById(R.id.textViewCommentsFrom1);
		mtextViewCommentsFrom[1] = (TextView) findViewById(R.id.textViewCommentsFrom2);
		mtextViewCommentsFrom[2] = (TextView) findViewById(R.id.textViewCommentsFrom3);
		mtextViewCommentsFrom[3] = (TextView) findViewById(R.id.textViewCommentsFrom4);
		mtextViewCommentsFrom[4] = (TextView) findViewById(R.id.textViewCommentsFrom5);
		if (mCommnetsData == null || mCommnetsData.size() == 0)
			return;
		for (int i = 0; i < mCommnetsData.size() && i < 5; i++) {
			mtextViewCommentsContent[i].setText(mCommnetsData.get(i).mscore
					+ "分" + mCommnetsData.get(i).mcontent);
			mtextViewCommentsContent[i].setVisibility(View.VISIBLE);
			mtextViewCommentsFrom[i].setText("来自" + mCommnetsData.get(i).mname
					+ "    " + mCommnetsData.get(i).mctime);
			mtextViewCommentsFrom[i].setVisibility(View.VISIBLE);
		}

		// mtextViewCommentsContent1;
		// mtextViewCommentsContent2;
		// mtextViewCommentsContent3;
		// mtextViewCommentsContent4;
		// mtextViewCommentsContent5;
		// mtextViewCommentsFrom1;
		// mtextViewCommentsFrom2;
		// mtextViewCommentsFrom3;
		// mtextViewCommentsFrom4;
		// mtextViewCommentsFrom5;
	}

//	TextView mtextViewmsgNum;
	TextView mtextViewcontactNum;
	TextView mtextViewfansNum;
	TextView mtextViewfollowNum;
	TextView mtextViewamount;
	TextView mtextViewpayTimesAndpayTotal;
	TextView mtextViewregtime;
	TextView mtextViewringTime;
	TextView mtextViewonlineTime;
	RatingBar mratingBarstar1;
	RatingBar mratingBarstar2;
	RatingBar mratingBarstar3;
	RatingBar mratingBarstar4;
	RatingBar mratingBarstar5;

	void initStaInfoComponent() {
//		mtextViewmsgNum = (TextView) findViewById(R.id.textViewmsgNum);
		mtextViewcontactNum = (TextView) findViewById(R.id.textViewcontactNum);
		mtextViewfansNum = (TextView) findViewById(R.id.textViewfansNum);
		mtextViewfollowNum = (TextView) findViewById(R.id.textViewfollowNum);
		mtextViewamount = (TextView) findViewById(R.id.textViewamount);
		mtextViewpayTimesAndpayTotal = (TextView) findViewById(R.id.textViewpayTimesAndpayTotal);
		mtextViewregtime = (TextView) findViewById(R.id.textViewregtime);
		mtextViewringTime = (TextView) findViewById(R.id.textViewringTime);
		mtextViewonlineTime = (TextView) findViewById(R.id.textViewonlineTime);
		mratingBarstar1 = (RatingBar) findViewById(R.id.ratingBarstar1);
		mratingBarstar2 = (RatingBar) findViewById(R.id.ratingBarstar2);
		mratingBarstar3 = (RatingBar) findViewById(R.id.ratingBarstar3);
		mratingBarstar4 = (RatingBar) findViewById(R.id.ratingBarstar4);
		mratingBarstar5 = (RatingBar) findViewById(R.id.ratingBarstar5);
		if (mStaInfoData == null)
			return;
//		mtextViewmsgNum.setText(mStaInfoData.mmsgNum);
		mtextViewcontactNum.setText(mStaInfoData.mcontactNum);
		mtextViewfansNum.setText(mStaInfoData.mfansNum);
		mtextViewfollowNum.setText(mStaInfoData.mfollowNum);
		mtextViewamount.setText(mStaInfoData.mamount);
		mtextViewpayTimesAndpayTotal.setText("已消费" + mStaInfoData.mpayTimes
				+ "次，共" + mStaInfoData.mpayTotal + "元");
		mtextViewregtime.setText("注册时间：" + mStaInfoData.mregtime);
		mtextViewringTime.setText("通话时长：" + mStaInfoData.mringTime);
		mtextViewonlineTime.setText("在线时长：" + mStaInfoData.monlineTime);
		mratingBarstar1.setRating((float) mStaInfoData.mstar1 / 10);
		mratingBarstar2.setRating((float) mStaInfoData.mstar2 / 10);
		mratingBarstar3.setRating((float) mStaInfoData.mstar3 / 10);
		mratingBarstar4.setRating((float) mStaInfoData.mstar4 / 10);
		mratingBarstar5.setRating((float) mStaInfoData.mstar5 / 10);
	}
	void showDialog() {
		final EditText et = new EditText(this);
		new AlertDialog.Builder(this).setTitle("请输入您的签名:")
				.setIcon(android.R.drawable.ic_dialog_info).setView(et)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// 数据获取
						mtextViewSign.setText(et.getText().toString());
						et.setInputType(InputType.TYPE_NULL) ;
						InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE) ;  
						  
						imm.hideSoftInputFromWindow(et.getWindowToken(), 0) ; 
//						 Toast.makeText(MyspaceActivity.this,
//						 et.getText().toString(),
//						 Toast.LENGTH_LONG).show();
						// mEditor.putString("ipadd", et.getText().toString());
						// 关键在这儿，获取输入框的数据，原来很简单！！
						// mEditor.commit();
					}
				}).setNegativeButton("取消", null).show();
	}
	public void tryToGetMyInfo() {
		try {
			String str = UserNetworkUtils.getMyInfoResultForHttpGet();
			mMyInfoData = JsonParser.ParserMyInfoData(str);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	TextView mtextViewName;
	TextView mtextViewMid;
	TextView mtextViewShenFen;
	TextView mtextViewPrice;
	TextView mtextViewPoint;
	TextView mtextViewLevel;
	TextView mtextViewSign;
	Button mbuttonBianji;
	public void initUserInfoComponent() {
		mtextViewName = (TextView) findViewById(R.id.textViewMyName);
		mtextViewMid = (TextView) findViewById(R.id.textViewMyMid);
		mtextViewShenFen = (TextView) findViewById(R.id.textViewMyShenFen);
		mtextViewPrice = (TextView) findViewById(R.id.textViewMyPrice);
		mtextViewPoint = (TextView) findViewById(R.id.textViewMyPoint);
		mtextViewLevel = (TextView) findViewById(R.id.textViewMyLevel);
		mtextViewSign = (TextView) findViewById(R.id.textViewMySign);
		mbuttonBianji = (Button) findViewById(R.id.buttonBianji);
		if (mMyInfoData == null)
			return;
		mtextViewName.setText(mMyInfoData.mName);
		mtextViewMid.setText(mMyInfoData.mMid);
		mtextViewShenFen
				.setText((mMyInfoData.mis_teach.equalsIgnoreCase("Y")) ? "实习教师"
						: "普通用户");
		mtextViewPrice.setText(mMyInfoData.mPrice);
		mtextViewPoint.setText("积分：" + mMyInfoData.mPoints);
		mtextViewLevel.setText("等级：" + mMyInfoData.mTeach_level);
		mtextViewSign.setText(mMyInfoData.mSign);
	}

	ProgressDialog mDialog;

	void tryToGetData() {
		new Thread() {
			public void run() {
				
				mHandler.sendEmptyMessage(999);
				tryToGetMyInfo();
				tryToGetStaInfo();
				tryToGetCommentsInfo();
				mHandler.sendEmptyMessage(1000);

			};
		}.start();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case 999:
				mDialog.show();
				break;
			case 1000:

				initUserInfoComponent();
				initStaInfoComponent();
				initCommnetsList();
				try {
					File file = new File(UploadFileUtils.filename, mMyInfoData.mMid+".zz");
					if (file.exists()) {
						FileInputStream stream = new FileInputStream(file);
						Bitmap bitmap = BitmapFactory.decodeStream(stream);
						personImage.setImageBitmap(bitmap);
						stream.close();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mDialog.dismiss();
				break;
			}
		};
	};
	
	ImageView personImage;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myspaceactivity);
		mDialog = ZhizhiUtils.createProgressDialog(MyspaceActivity.this);
		personImage  = (ImageView) findViewById(R.id.personImage);
		mbuttonBianji = (Button) findViewById(R.id.buttonBianji);
		mbuttonBianji.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog();
				
			}
		});
		
		Button buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		// 编辑资料
		Button mySpaceBianjiZiliao2 = (Button) findViewById(R.id.mySpaceBianjiZiliao);
		mySpaceBianjiZiliao2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MyspaceActivity.this,
						ModifyPersonInforActivity.class);
				startActivity(intent);
			}
		});

		// 充值
		Button buttonchongzhi2 = (Button) findViewById(R.id.buttonchongzhi);
		buttonchongzhi2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MyspaceActivity.this, ChongzhiActivity.class);
				startActivity(intent);
			}
		});

		// 进入帐户
		TextView textViewJinRu2 = (TextView) findViewById(R.id.textViewJinRu);
		textViewJinRu2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MyspaceActivity.this, AccountActivity.class);
				startActivity(intent);
			}
		});

		// 粉丝
		LinearLayout fensi2 = (LinearLayout) findViewById(R.id.fensi);
		fensi2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MyspaceActivity.this, WodeFensiActivity.class);
				startActivity(intent);
			}
		});

		// 联系人
		LinearLayout lianxiren = (LinearLayout) findViewById(R.id.lianxiren);
		lianxiren.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MyspaceActivity.this, MainViewActivity.class);
				intent.putExtra("tabstate", 1);
				intent.putExtra("pagestate", 1);
				startActivity(intent);
			}
		});
		
		// 关注的人
		LinearLayout guanzhuderen = (LinearLayout) findViewById(R.id.guanzhuderen);
		guanzhuderen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("tabstate", 2);
				intent.putExtra("pagestate", 1);
				intent.setClass(MyspaceActivity.this, MainViewActivity.class);
				startActivity(intent);
			}
		});
		
		tryToGetData();	
	}
}
