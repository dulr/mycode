package org.linphone.zhizhiui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.linphone.CallManager;
import org.linphone.DialerActivity;
import org.linphone.LinphoneService;
import org.linphone.OutgoingCallReceiver;
import com.zhizhi.R;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
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
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class IntroduceActivity extends Activity {
	protected static final String TAG = "IntroduceActivity";
	UserInfoData mUserInfoData;
	MyInfoData mMyInfoData;

	public void tryToGetUserInfo(int mid, String isteacher) {
		try {
			String str = UserNetworkUtils.getUserInfoResultForHttpGet(mid);
			if (isteacher.equalsIgnoreCase("Y")) {
				mUserInfoData = JsonParser.ParserUserInfoData(str);
			} else {
				mMyInfoData = JsonParser.ParserMyInfoData(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// finish();
		}
	}

	StaInfoData mStaInfoData;

	public void tryToGetStaInfo(int mid) {
		try {
			String str = UserNetworkUtils.getStaListResultForHttpGet(mid + "");

			mStaInfoData = JsonParser.ParserStaInfoData(str);

		} catch (Exception e) {
			e.printStackTrace();
			// finish();
		}
	}

	// TextView mtextViewmsgNum;
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
		// mtextViewmsgNum = (TextView) findViewById(R.id.textViewmsgNum);
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

		// mtextViewmsgNum.setText(mStaInfoData.mmsgNum);
		// mtextViewcontactNum.setText(mStaInfoData.mcontactNum);
		mtextViewfansNum.setText("粉丝  " + mStaInfoData.mfansNum);
		mtextViewfollowNum.setText("关注  " + mStaInfoData.mfollowNum);
		// mtextViewamount.setText(mStaInfoData.mamount);
		// mtextViewpayTimesAndpayTotal.setText("已消费"+mStaInfoData.mpayTimes+"次，共"+mStaInfoData.mpayTotal+"元");
		mtextViewregtime.setText("注册时间：" + mStaInfoData.mregtime);
		mtextViewringTime.setText("通话时长：" + mStaInfoData.mringTime);
		mtextViewonlineTime.setText("在线时长：" + mStaInfoData.monlineTime);
		mratingBarstar1.setRating((float) mStaInfoData.mstar1 / 10);
		mratingBarstar2.setRating((float) mStaInfoData.mstar2 / 10);
		mratingBarstar3.setRating((float) mStaInfoData.mstar3 / 10);
		mratingBarstar4.setRating((float) mStaInfoData.mstar4 / 10);
		mratingBarstar5.setRating((float) mStaInfoData.mstar5 / 10);
	}

	TextView mtextViewName;
	TextView mtextViewMid;
	TextView mtextViewShenFen;
	TextView mtextViewPrice;
	TextView mtextViewPoint;
	TextView mtextViewLevel;
	TextView mtextViewSign;
	
	TextView textViewAge;
	TextView textViewSex;
	TextView mtextViewcountry;
	TextView mtextViewcity;
	TextView mtextViewlanguage;
	TextView mtextViewother_language;
	TextView mtextViewedu;
	TextView mtextViewmajor;
	TextView mtextViewjob;
	TextView mtextViewgoodat;
	TextView mtextViewteach_descript;
	TextView textViewUsername;
	// TextView m;
	// TextView m;

	public void initComponent() {
		mtextViewName = (TextView) findViewById(R.id.textViewName);
		mtextViewMid = (TextView) findViewById(R.id.textViewMid);
		mtextViewShenFen = (TextView) findViewById(R.id.textViewShenFen);
		mtextViewPrice = (TextView) findViewById(R.id.textViewPrice);
		mtextViewPoint = (TextView) findViewById(R.id.textViewPoint);
		mtextViewLevel = (TextView) findViewById(R.id.textViewLevel);
		mtextViewSign = (TextView) findViewById(R.id.textViewSign);

		textViewAge = (TextView) findViewById(R.id.textViewAge);
		textViewSex = (TextView) findViewById(R.id.textViewSex);
		mtextViewcountry = (TextView) findViewById(R.id.textViewcountry);
		mtextViewcity = (TextView) findViewById(R.id.textViewcity);
		mtextViewlanguage = (TextView) findViewById(R.id.textViewlanguage);
		mtextViewother_language = (TextView) findViewById(R.id.textViewother_language);
		mtextViewedu = (TextView) findViewById(R.id.textViewedu);
		mtextViewmajor = (TextView) findViewById(R.id.textViewmajor);
		mtextViewjob = (TextView) findViewById(R.id.textViewjob);
		mtextViewgoodat = (TextView) findViewById(R.id.textViewgoodat);
		textViewUsername = (TextView) findViewById(R.id.textViewUsername);
		
		mtextViewteach_descript = (TextView) findViewById(R.id.textViewteach_descript);

		if (mUserInfoData != null) {
			mtextViewName.setText(mUserInfoData.mName);
			textViewUsername.setText(mUserInfoData.mName);
			mtextViewMid.setText(mUserInfoData.mMid);
			mtextViewShenFen.setText((mUserInfoData.mis_teach
					.equalsIgnoreCase("Y")) ? "实习教师" : "普通用户");
			mtextViewPrice.setText(mUserInfoData.mPrice);
			if (isteacher.equalsIgnoreCase("Y")) {
				bodadianhua2.setText("拨打电话(" + mUserInfoData.mPrice + "元/分钟)");
			} else {
				bodadianhua2.setText("拨打电话(" +  "免费)");
			}
			mtextViewPoint.setText("积分：" + mUserInfoData.mPoints);
			mtextViewLevel.setText("等级：" + mUserInfoData.mTeach_level);
			mtextViewSign.setText(mUserInfoData.mSign);
			
			textViewAge.setText("年龄：" + mUserInfoData.mage);
			if( mUserInfoData.mSex.equalsIgnoreCase("M"))
			   textViewSex.setText("性别：男" );
			else if( mUserInfoData.mSex.equalsIgnoreCase("F"))
				   textViewSex.setText("性别：女" );
			else
				   textViewSex.setText("性别：未知" );
			
			mtextViewcity.setText("所在城市：" + mUserInfoData.mcity);
			mtextViewcountry.setText("国籍：" + mUserInfoData.mcountry);
			mtextViewcity.setText("所在城市：" + mUserInfoData.mcity);
			mtextViewlanguage.setText("母语：" + mUserInfoData.mlanguage);
			mtextViewother_language.setText("其他语言："
					+ mUserInfoData.mother_language);
			mtextViewedu.setText("学历： " + mUserInfoData.medu);
			mtextViewmajor.setText("专业： " + mUserInfoData.mmajor);
			mtextViewjob.setText("职业： " + mUserInfoData.mjob);
			mtextViewgoodat.setText("特长： " + mUserInfoData.mgoodat);

			mtextViewteach_descript.setText("" + mUserInfoData.mteach_descript);
			
			if(mUserInfoData.isonline.equals("Y")){
				stateImage.setBackgroundResource(MainViewActivity.personState[0]);
			} else {
				stateImage.setBackgroundResource(MainViewActivity.personState[1]);
			}
		} else if (mMyInfoData != null) {
			mtextViewName.setText(mMyInfoData.mName);
			mtextViewMid.setText(mMyInfoData.mMid);
			mtextViewShenFen.setText((mMyInfoData.mis_teach
					.equalsIgnoreCase("Y")) ? "实习教师" : "普通用户");
			mtextViewPrice.setText(mMyInfoData.mPrice);
			mtextViewPoint.setText("积分：" + mMyInfoData.mPoints);
			mtextViewLevel.setText("等级：" + mMyInfoData.mTeach_level);
			mtextViewSign.setText(mMyInfoData.mSign);
			textViewAge.setText("年龄：" + mMyInfoData.mage);
            if( mMyInfoData.mSex.equalsIgnoreCase("M"))
               textViewSex.setText("性别：男" );
            else if( mMyInfoData.mSex.equalsIgnoreCase("F"))
                   textViewSex.setText("性别：女" );
            else
                   textViewSex.setText("性别：未知" );

            mtextViewcity.setText("所在城市：" + mMyInfoData.mcity);
            mtextViewcountry.setText("国籍：" + mMyInfoData.mcountry);
            mtextViewteach_descript.setText("" + mMyInfoData.mresume);
            
            if(mMyInfoData.isonline.equals("Y")){
    			stateImage.setBackgroundResource(MainViewActivity.personState[0]);
    		} else {
    			stateImage.setBackgroundResource(MainViewActivity.personState[1]);
    		}
		}
	}

	List<CommnetsData> mCommnetsData;
	int mCommentsCount;

	public void tryToGetCommentsInfo(String mid) {
		try {
			String str = CommentsNetworkUtils.getListcommentResultForHttpGet(1,
					mid);
			JSONObject jsonObject;
			jsonObject = new JSONObject(str.toString());
			mCommentsCount = jsonObject.getInt("count");

			Log.i(TAG, "mCommentsCount=" + mCommentsCount);

			JSONArray jsonArray = jsonObject.getJSONArray("data");
			mCommnetsData = JsonParser.ParserCommentList(jsonArray);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
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
	ProgressDialog mDialog;

	void tryToGetData() {
		new Thread() {
			public void run() {
				
				mHandler.sendEmptyMessage(999);
				tryToGetUserInfo(mid, isteacher);
				if (isteacher.equalsIgnoreCase("Y")) {
					tryToGetCommentsInfo(mid + "");
				}
				tryToGetStaInfo(mid);
				
				try {
				String str=	UserNetworkUtils.isfollowResultForHttpGet(mid);
				JSONObject jsonObject = new JSONObject(str.toString());
				String state;
				String msg;

				state = jsonObject.getString("state");
				msg = jsonObject.getString("msg");
				Log.i("NetworkUtils", msg);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
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
				initComponent();
				initStaInfoComponent();
				if (isteacher.equalsIgnoreCase("Y")) {
					initCommnetsList();
				}
				initGuanzhu();
				try {
					File file = new File(UploadFileUtils.filename, mid+".zz");
					if (file.exists()) {
						FileInputStream stream = new FileInputStream(file);
						Bitmap bitmap = BitmapFactory.decodeStream(stream);
						personImage.setImageBitmap(bitmap);
						stream.close();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				mDialog.dismiss();
				break;
			case 3001:
				Intent intent = new Intent();
				intent.setClass(IntroduceActivity.this, CallEndActivity.class);
				startActivity(intent);
				break;
			}
		};
	};
	String isteacher;
	int mid;
	int position;
	ImageView personImage;

	Button buttonguanzhu;
	boolean isGuanZhu = false;

	void initGuanzhu() {
		buttonguanzhu = (Button) findViewById(R.id.buttonguanzhu);
		try {
			if (isGuanZhu) {
				buttonguanzhu.setText("取消关注");
			} else {
				if (mUserInfoData.mSex.equals("F"))
					buttonguanzhu.setText("关注她");
				else buttonguanzhu.setText("关注他");
			}
		} catch (Exception e) {

		}
		buttonguanzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (LinphoneService.instance().username == null
						|| LinphoneService.instance().username.length() == 0) {
					Intent intent2 = new Intent();
					intent2.setClass(IntroduceActivity.this,
							LoginActivity.class);
					startActivity(intent2);
				} else {
					if (isGuanZhu) {
						isGuanZhu = false;
						if (mUserInfoData.mSex.equals("F"))
							buttonguanzhu.setText("关注她");
						else
							buttonguanzhu.setText("关注他");
						
						try {
							UserNetworkUtils.cancelfollowResultForHttpGet(mid);
						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						isGuanZhu = true;
						buttonguanzhu.setText("取消关注");
						try {
							UserNetworkUtils.followResultForHttpGet(mid);
						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
	}
	Button bodadianhua2;
	ImageView stateImage;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isteacher = getIntent().getStringExtra("item");
		mid = getIntent().getIntExtra("mid", 1);
		position = getIntent().getIntExtra("position", 0);
		
		
		if (isteacher.equalsIgnoreCase("Y")) {
			setContentView(R.layout.introduceactivity);
		} else {
			setContentView(R.layout.introducestudentactivity);
		}
		personImage  = (ImageView) findViewById(R.id.personImage);
		stateImage  = (ImageView) findViewById(R.id.stateImage);
		
		
		mDialog = ZhizhiUtils.createProgressDialog(IntroduceActivity.this);
		tryToGetData();
		bodadianhua2 = (Button) findViewById(R.id.bodadianhua);
		bodadianhua2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (LinphoneService.instance().username != null) {
					showDialog(10000);
				} else {
					Intent intent = new Intent();
					intent.setClass(IntroduceActivity.this, LoginActivity.class);
					intent.putExtra("mid", mid);
					startActivity(intent);
				}
//				Intent intent = new Intent(Intent.ACTION_CALL, Uri
////						.parse("tel:" + "13581606890"));
//				        .parse("tel:" + "10086"));
//				startActivity(intent);
//				mHandler.sendEmptyMessageDelayed(3001, 1500);
				
				
			}
		});

		Button buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		Button faxiaoxi = (Button) findViewById(R.id.faxiaoxi);
		faxiaoxi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(IntroduceActivity.this, XiaoxiActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 10000:
			return createDisableDataAccessAlertDialog(0);

		}
		return null;

	}

	private Dialog createDisableDataAccessAlertDialog(int msgRes) {
		AlertDialog.Builder dataDisableAlertbuilder = new AlertDialog.Builder(
				this);
		dataDisableAlertbuilder
				.setOnCancelListener(new DialogInterface.OnCancelListener() {

					@Override
					public void onCancel(DialogInterface arg0) {
						finish();
					}
				});

		dataDisableAlertbuilder.setPositiveButton("无线网络电话",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						if (LinphoneService.instance().username != null) {
							String callnum ;
//							if (LinphoneService.instance().username
//									.equals("10010"))
//								callnum = "dule742";
//							else callnum = "dule741";
//							newOutgoingCall(mUserInfoData.SIPNumber, "");
						    if (isteacher.equalsIgnoreCase("Y")) {
						        callnum= mUserInfoData.mName;
//						        callnum = mUserInfoData.SIPNumber;
                            }
                            else{
                                callnum = mMyInfoData.mName;
//                                callnum = mMyInfoData.SIPNumber;
                            }

							newOutgoingCall(callnum, "");
							Intent intent = new Intent();
							intent.setClass(IntroduceActivity.this,
									CallingActivity.class);
							intent.putExtra("mid", mid);
							if (isteacher.equalsIgnoreCase("Y")) {
						    	intent.putExtra("name", mUserInfoData.mName);
							}
							else{
							    intent.putExtra("name", mMyInfoData.mName);
							}
							startActivity(intent);
						} else {
							Intent intent = new Intent();
							intent.setClass(IntroduceActivity.this,
									LoginActivity.class);
							intent.putExtra("mid", mid);
							startActivity(intent);
						}
					}
				});
		dataDisableAlertbuilder.setNegativeButton("直拨电话",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					    String num;
						if (isteacher.equalsIgnoreCase("Y")) {
						    num = mUserInfoData.mPhoneNumber;
                        }
                        else{
                            num = mMyInfoData.mPhoneNumber;
                        }
						Intent intent = new Intent(Intent.ACTION_CALL, Uri
                                .parse("tel:" + num));
						startActivity(intent);

//						Intent intent = new Intent();
//						if (isteacher.equalsIgnoreCase("Y")) {
//                            intent.putExtra("name", mUserInfoData.mName);
//                        }
//                        else{
//                            intent.putExtra("name", mMyInfoData.mName);
//                        }
//						intent.putExtra("mid", mid);
//						intent.setClass(IntroduceActivity.this, ZhizhiYaoqingActivity.class);
//						startActivity(intent);
					}
				});
		dataDisableAlertbuilder.setCancelable(true);
		dataDisableAlertbuilder
				.setMessage("注：无线网络电话只需耗费手机上网流量，无其他花费（建议在WIFI环境下使用）；直拨电话则需额外付出手机通话费用。");
		return dataDisableAlertbuilder.create();
	}

	public synchronized void newOutgoingCall(String aTo, String displayName) {
		String lto = aTo;
		if (aTo.contains(OutgoingCallReceiver.TAG)) {
			lto = aTo.replace(OutgoingCallReceiver.TAG, "");
		}

		LinphoneCore lLinphoneCore = LinphoneService.instance()
				.getLinphoneCore();
		if (lLinphoneCore.isIncall()) {
			// Toast toast = Toast.makeText(DialerActivity.this,
			// getString(R.string.warning_already_incall), Toast.LENGTH_LONG);
			// toast.show();
			return;
		}
		LinphoneAddress lAddress;
		try {
			lAddress = lLinphoneCore.interpretUrl(lto);
		} catch (LinphoneCoreException e) {
			// Toast toast = Toast.makeText(DialerActivity.this
			// ,String.format(getString(R.string.warning_wrong_destination_address),mAddress.getText().toString())
			// , Toast.LENGTH_LONG);
			// toast.show();
			return;
		}
		// lAddress.setDisplayName(mDisplayName);

		try {
			// boolean prefVideoEnable =
			// mPref.getBoolean(getString(R.string.pref_video_enable_key),
			// false);
			// boolean prefInitiateWithVideo =
			// mPref.getBoolean(getString(R.string.pref_video_initiate_call_with_video_key),
			// false);
			// resetCameraFromPreferences();
			CallManager.getInstance().inviteAddress(lAddress, false);

		} catch (LinphoneCoreException e) {
			// Toast toast = Toast.makeText(DialerActivity.this
			// ,String.format(getString(R.string.error_cannot_get_call_parameters),mAddress.getText().toString())
			// ,Toast.LENGTH_LONG);
			// toast.show();
			return;
		}
	}
}
