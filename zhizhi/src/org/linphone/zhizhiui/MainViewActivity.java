package org.linphone.zhizhiui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.graphics.Paint;
import android.graphics.Color;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.linphone.AboutActivity;
import org.linphone.LinphoneConfigException;
import org.linphone.LinphoneException;
import org.linphone.LinphonePreferencesActivity;
import org.linphone.LinphoneService;
import com.zhizhi.R;

import org.linphone.zhizhiui.data.CommentsNetworkUtils;
import org.linphone.zhizhiui.data.JsonParser;
import org.linphone.zhizhiui.data.MainViewListData;
import org.linphone.zhizhiui.data.MyInfoData;
import org.linphone.zhizhiui.data.NetworkUtils;
import org.linphone.zhizhiui.data.RegUtils;
import org.linphone.zhizhiui.data.UploadFileUtils;
import org.linphone.zhizhiui.data.UserNetworkUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainViewActivity extends Activity {
	final int CURRENT_PAGE_ALL = 0;
	final int CURRENT_PAGE_LIANXIREN = 1;
	int mCurrentPage = CURRENT_PAGE_ALL;

    final static int CURRENT_TAB_1 = 0;
    final static int CURRENT_TAB_2 = 1;
    public static int mCurrentTab = CURRENT_TAB_1;

	ProgressDialog mDialog;
	Spinner spinner1;
	ArrayAdapter<String> adapter;
	Spinner spinner2;
	ArrayAdapter<String> adapter2;
	   ArrayAdapter<String> adapter3;
	public static int[] personState = new int[] {
	    R.drawable.person_online,
		R.drawable.person_offline, 
		R.drawable.person_incall 
		};
	static String mTotalUser;
	static String mOnlineUser;
	
	static String mTotalStudentUser;
	static String mOnlineStudentUser;
	TextView mtextViewOnlinePersonNumber;
	private static final String[] str_spinner1 = {
	"照片", "性别", "年龄", "国籍"
	};
	
	
	private static final String[] str_spinner2 = {

	"按等级由高到低", "按等级由低到高", "按价格由高到低", "按价格由低到高", "按在线时长由长到短", "按在线时长由短到长",
			"按通话时长由长到短", "按通话时长由短到长" };
	   private static final String[] str_spinner3 = {

	       "按等级由高到低", "按等级由低到高",  "按在线时长由长到短", "按在线时长由短到长",
	               "按通话时长由长到短", "按通话时长由短到长" };
	protected static final String TAG = "MainViewActivity";

	void startService() {
		try {
			if (LinphoneService.instance() == null) {
				Intent lLinphoneServiceIntent = new Intent(Intent.ACTION_MAIN);
				lLinphoneServiceIntent.setClass(this, LinphoneService.class);
				startService(lLinphoneServiceIntent);
			}
			LinphoneService.instance().initFromConf();
		} catch (LinphoneConfigException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static MyInfoData mMyInfoData;
boolean	mReflash = true;
	void tryreferList() {
		new Thread() {
			public void run() {
//				while (mReflash)
				{

					try {
						String str = NetworkUtils.getSearchResultForHttpGet("teachlevel",
								"desc", 1);
						
						JSONObject jsonObject = new JSONObject(str.toString());
						MainViewActivity.mTotalUser = jsonObject.getString("total");
						MainViewActivity.mOnlineUser = jsonObject.getString("online");
						JSONArray jsonArray = jsonObject.getJSONArray("data");
						MainViewActivity.mListAllData = JsonParser
								.ParserMainViewListData(jsonArray);

						str = NetworkUtils.getSearchStudentResultForHttpGet("teachlevel",
			                    "desc", 1);
					     jsonObject = new JSONObject(str.toString());
					     MainViewActivity.mTotalStudentUser = jsonObject.getString("total");
					     MainViewActivity.mOnlineStudentUser = jsonObject.getString("online");
						
					     jsonArray = jsonObject.getJSONArray("data");
						mListAllStudentData = JsonParser
								.ParserMainViewListData(jsonArray);

						if (LinphoneService.instance().username != null) {
							str = UserNetworkUtils
									.getMyFollowResultForHttpGet(1);
							jsonArray = new JSONArray(str.toString());
							mListGuanZhuData = JsonParser
									.ParserMainViewListData(jsonArray);

							str = CommentsNetworkUtils
									.getLogResultForHttpGet(1);

							mListZuiJinData = JsonParser
									.ParserZuijinLianxirenListData(str);
						}
                        Log.i("MainViewActivity", "mCurrentPage = " +mCurrentPage+" mCurrentTab="+mCurrentTab);

					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						handler.sendEmptyMessage(1);
						Thread.sleep(1000 * 10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
	
	ListView mMainViewList = null;
	public static List<MainViewListData> mListAllData;
	public static List<MainViewListData> mListAllStudentData;
	public static List<MainViewListData> mListZuiJinData;
	public static List<MainViewListData> mListGuanZhuData;
	MainViewListItemAdapter mMainViewListAdapter;

	void initListView() {
		mMainViewList = (ListView) findViewById(R.id.ListViewMainView);
		// mListData = new ArrayList<MainViewListData>();
		// MainViewListData data = new MainViewListData();
		//
		// try {
		// String str = NetworkUtils.getSearchResultForHttpGet("teachlevel",
		// "desc", 1);
		// JSONObject jsonObject;
		// try {
		// jsonObject = new JSONObject(str.toString());
		//
		// // {"data":
		// // [
		// //
		// {"mid":"1","profile":"http:\/\/open.zhizhi.com\/data\/profile\/mid1.png","isonline":"Y","name":"nomit","price":"22.00","sign":null,"points":"1"},
		// //
		// {"mid":"2","profile":"http:\/\/open.zhizhi.com\/data\/default\/sex0.png","isonline":"Y","name":"test","price":"0.00","sign":"hahah","points":"2"}
		// // ],
		// // "total":"1","online":"3"}
		//
		// mTotalUser = jsonObject.getString("total");
		// mOnlineUser = jsonObject.getString("online");
		// Log.i(TAG, "mTotalUser=" + mTotalUser);
		// Log.i(TAG, "mOnlineUser=" + mOnlineUser);
		// mtextViewOnlinePersonNumber.setText("共 " + mTotalUser + "人, "
		// + mOnlineUser + "人在线");
		// // JSONObject jsonDataObject = jsonObject.getJSONObject("data");
		// JSONArray jsonArray = jsonObject.getJSONArray("data");
		// mListData = JsonParser.ParserMainViewListData(jsonArray);
		// } catch (JSONException e) {
		// //  Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// } catch (ClientProtocolException e) {
		// //  Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// //  Auto-generated catch block
		// e.printStackTrace();
		// }
		mtextViewOnlinePersonNumber.setText("共 " + mTotalUser   + "人, "
				+ mOnlineUser + "人在线");
		mMainViewListAdapter = new MainViewListItemAdapter(this, mListAllData);
		mMainViewList.setAdapter(mMainViewListAdapter);
	}

	TextView woguanzhude2;
	Button woguanzhudeLine;
	TextView quanbujiaoshihelianxiren2;

	LinearLayout lianxiren2 ;
//	LinearLayout lianxirenLine2 ;
	LinearLayout quanbujiaoshi2;

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int pagestate = intent.getIntExtra("pagestate", 0);
        int state = intent.getIntExtra("tabstate", 0);
        if (pagestate == CURRENT_PAGE_LIANXIREN) {
            mCurrentPage = CURRENT_PAGE_LIANXIREN;
            zuijinlianxiren1.setText("最近联系人");
            woguanzhude2.setText("我关注的人");
            quanbujiaoshihelianxiren2.setText("联络人");
            lianluoren2.setTextColor(Color.rgb(255, 255, 255));
            quanbu2.setTextColor(Color.rgb(0, 0, 0));
            if (state == 1) {
                mCurrentTab = CURRENT_TAB_1;
                lianxiren2.setVisibility(View.VISIBLE);
                quanbujiaoshi2.setVisibility(View.GONE);

                woguanzhude2.setTextColor(colorAAAA);
                zuijinlianxiren1.setTextColor(Color.rgb(255, 255, 255));
                woguanzhudeLine.setVisibility(View.INVISIBLE);
                zuijinlianxirenLine.setVisibility(View.VISIBLE);

                woguanzhudeLine.setVisibility(View.INVISIBLE);
                zuijinlianxirenLine.setVisibility(View.VISIBLE);
            } else if (state == 2) {

                lianxiren2.setVisibility(View.VISIBLE);
                // lianxirenLine2.setVisibility(View.VISIBLE);
                quanbujiaoshi2.setVisibility(View.GONE);
                woguanzhude2.setTextColor(Color.rgb(255, 255, 255));
                zuijinlianxiren1.setTextColor(colorAAAA);


                woguanzhudeLine.setVisibility(View.VISIBLE);
                zuijinlianxirenLine.setVisibility(View.INVISIBLE);
                mCurrentTab = CURRENT_TAB_2;
            }
        }
        tryreferList();
    }
    TextView quanbu2;
    TextView lianluoren2;
    int colorAAAA  = Color.rgb(28, 28, 28);

    protected void onResume() {
        super.onResume();
        tryreferList() ;
    }
    protected void onDestroy() {
    	 mReflash = false;
    	 super.onDestroy();
    }
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (MainViewActivity.mListAllData == null
				|| MainViewActivity.mListAllData.size() == 0) {
			Intent intent2 = new Intent();
			intent2.setClass(this, StartupActivity.class);
			startActivity(intent2);
			finish();
			return;
		}
		setContentView(R.layout.mainviewactivity);
		startService();
//		tryreferList() ;
		mDialog = ZhizhiUtils.createProgressDialog(MainViewActivity.this);
		mtextViewOnlinePersonNumber = (TextView) findViewById(R.id.textViewOnlinePersonNumber);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_spinner1);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter);

		adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str_spinner2);
	      adapter3 = new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item, str_spinner3);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);

		zuijinlianxiren1 = (TextView) findViewById(R.id.zuijinlianxiren);
		zuijinlianxirenLine = (Button) findViewById(R.id.zuijinlianxirenLine);
		woguanzhude2 = (TextView) findViewById(R.id.woguanzhude);
		woguanzhudeLine = (Button) findViewById(R.id.woguanzhudeLine);
		zuijinlianxiren1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				woguanzhude2.setTextColor(colorAAAA);
				woguanzhudeLine.setVisibility(View.INVISIBLE);
				zuijinlianxiren1.setTextColor(Color.rgb(255, 255, 255));
				zuijinlianxirenLine.setVisibility(View.VISIBLE);
				mCurrentTab = CURRENT_TAB_1;
				if (mCurrentPage == CURRENT_PAGE_ALL) {
					// Click teacher	    
				    mtextViewOnlinePersonNumber.setText("共 " + mTotalUser   + "人, "
             				+ mOnlineUser + "人在线");
			        spinner2.setAdapter(adapter2);
					 mMainViewListAdapter.setData(mListAllData);
					 mMainViewListAdapter.notifyDataSetChanged();
				} else if (mCurrentPage == CURRENT_PAGE_LIANXIREN) {
					// Click Zuijinlianxiren
					try {
						String str = CommentsNetworkUtils
								.getLogResultForHttpGet(1);
						
						mListZuiJinData = JsonParser
								.ParserZuijinLianxirenListData(str);
						mMainViewListAdapter.setData(mListZuiJinData);
						mMainViewListAdapter.notifyDataSetChanged();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		woguanzhude2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				woguanzhude2.setTextColor(Color.rgb(255, 255, 255));
				zuijinlianxiren1.setTextColor(colorAAAA);
				woguanzhudeLine.setVisibility(View.VISIBLE);
				zuijinlianxirenLine.setVisibility(View.INVISIBLE);
				mCurrentTab = CURRENT_TAB_2;
				if (mCurrentPage == CURRENT_PAGE_ALL) {
					// click student
				    spinner2.setAdapter(adapter3);
					try {
					    String str = NetworkUtils.getSearchStudentResultForHttpGet("teachlevel",
			                    "desc", 1);
					    JSONObject jsonObject = new JSONObject(str.toString());
					    MainViewActivity.mTotalStudentUser = jsonObject.getString("total");
					     MainViewActivity.mOnlineStudentUser = jsonObject.getString("online");
					     mtextViewOnlinePersonNumber.setText("共 " + mTotalStudentUser   + "人, "
                  				+ mOnlineStudentUser + "人在线");
					    JSONArray jsonArray = jsonObject.getJSONArray("data");
						mListAllStudentData = JsonParser
								.ParserMainViewListData(jsonArray);
						mMainViewListAdapter.setData(mListAllStudentData);
						mMainViewListAdapter.notifyDataSetChanged();
						tryToDownloadFile();
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (mCurrentPage == CURRENT_PAGE_LIANXIREN) {
					try {
						String str = UserNetworkUtils
								.getMyFollowResultForHttpGet(1);
						JSONArray jsonArray = new JSONArray(str.toString());
						mListGuanZhuData = JsonParser
								.ParserMainViewListData(jsonArray);
						mMainViewListAdapter.setData(mListGuanZhuData);
						mMainViewListAdapter.notifyDataSetChanged();
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		final TextView buttonShaixuan2 = (TextView) findViewById(R.id.buttonShaixuan);
		buttonShaixuan2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainViewActivity.this, ShaixuanActivity.class);
				startActivity(intent);
			}
		});

		 quanbujiaoshihelianxiren2 = (TextView) findViewById(R.id.quanbujiaoshihelianxiren);
		 lianluoren2 = (TextView) findViewById(R.id.lianluoren);
	     quanbu2 = (TextView) findViewById(R.id.quanbu);
		final TextView wodekongjian2 = (TextView) findViewById(R.id.wodekongjian);
		final TextView gengduo2 = (TextView) findViewById(R.id.gengduo);
		lianxiren2 = (LinearLayout) findViewById(R.id.lianxiren);
//		lianxirenLine2 = (LinearLayout) findViewById(R.id.lianxirenLine);
	    quanbujiaoshi2 = (LinearLayout) findViewById(R.id.quanbujiaoshi);
		gengduo2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openOptionsMenu();
			}
		});

		wodekongjian2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (LinphoneService.instance().username == null
						|| LinphoneService.instance().username.length() == 0) {
					Intent intent2 = new Intent();
					intent2.setClass(MainViewActivity.this, LoginActivity.class);
					startActivity(intent2);
				} else {
					try {
						String str = UserNetworkUtils
								.getMyInfoResultForHttpGet();
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					Intent intent = new Intent();
					intent.setClass(MainViewActivity.this,
							MyspaceActivity.class);
					startActivity(intent);
				}
			}
		});
		quanbu2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mCurrentPage = CURRENT_PAGE_ALL;
				mCurrentTab = CURRENT_TAB_1;
				zuijinlianxiren1.setText("教师");
				woguanzhude2.setText("学生");
				quanbujiaoshihelianxiren2.setText("社区用户");
				quanbujiaoshi2.setVisibility(View.VISIBLE);
//				lianxiren2.setVisibility(View.GONE);
//				lianxirenLine2.setVisibility(View.GONE);
				quanbu2.setTextColor(Color.rgb(255, 255, 255));
				lianluoren2.setTextColor(Color.rgb(0, 0, 0));
				woguanzhude2.setTextColor(colorAAAA);
				zuijinlianxiren1.setTextColor(Color.rgb(255, 255, 255));
				woguanzhudeLine.setVisibility(View.INVISIBLE);
				zuijinlianxirenLine.setVisibility(View.VISIBLE);
				mtextViewOnlinePersonNumber.setVisibility(View.VISIBLE);
				String str;
                try {
                    str = NetworkUtils.getSearchResultForHttpGet("teachlevel",
                            "desc", 1);
                    JSONObject jsonObject = new JSONObject(str.toString());
                    MainViewActivity.mTotalUser = jsonObject.getString("total");
                    MainViewActivity.mOnlineUser = jsonObject.getString("online");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    MainViewActivity.mListAllData = JsonParser
                            .ParserMainViewListData(jsonArray);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


				 mMainViewListAdapter.setData(mListAllData);
				 mMainViewListAdapter.notifyDataSetChanged();
			}
		});
		lianluoren2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (LinphoneService.instance().username == null
						|| LinphoneService.instance().username.length() == 0) {
					Intent intent2 = new Intent();
					intent2.setClass(MainViewActivity.this, LoginActivity.class);
					startActivity(intent2);
				} else {
					mCurrentPage = CURRENT_PAGE_LIANXIREN;
					zuijinlianxiren1.setText("最近联系人");
					woguanzhude2.setText("我关注的人");
					quanbujiaoshihelianxiren2.setText("联络人");
					lianxiren2.setVisibility(View.VISIBLE);
//					lianxirenLine2.setVisibility(View.VISIBLE);
					quanbujiaoshi2.setVisibility(View.GONE);
					lianluoren2.setTextColor(Color.rgb(255, 255, 255));
					quanbu2.setTextColor(Color.rgb(0, 0, 0));
					woguanzhude2.setTextColor(colorAAAA);
					zuijinlianxiren1.setTextColor(Color.rgb(255, 255, 255));
					woguanzhudeLine.setVisibility(View.INVISIBLE);
					zuijinlianxirenLine.setVisibility(View.VISIBLE);
					mtextViewOnlinePersonNumber.setVisibility(View.GONE);
					try {
						String str = CommentsNetworkUtils
								.getLogResultForHttpGet(1);
//						JSONArray jsonArray = new JSONArray(str.toString());
						 mListZuiJinData = JsonParser
						 .ParserZuijinLianxirenListData(str);
						 mMainViewListAdapter.setData(mListZuiJinData);
						 mMainViewListAdapter.notifyDataSetChanged();
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		initListView();
		registerForContextMenu(gengduo2);
		setItemclick();
	}

	TextView zuijinlianxiren1 = null;
	Button zuijinlianxirenLine = null;
	void setItemclick() {

		// 添加点击
		mMainViewList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				setTitle("点击第" + position + "个项目");
				Log.i(TAG, "点击第" + position + "个项目");
				// try {
				// String str = UserNetworkUtils.getStaListResultForHttpGet();
				// // mUserInfoData = JsonParser.ParserUserInfoData(str);
				// } catch (ClientProtocolException e) {
				// e.printStackTrace();
				// } catch (IOException e) {
				// e.printStackTrace();
				// }

				Intent intent = new Intent();
                if (mCurrentPage == CURRENT_PAGE_ALL) {
                    // Click teacher
                    if (mCurrentTab == CURRENT_TAB_1) {
                        intent.putExtra("mid", mListAllData.get(position).mMid);
                        intent.putExtra("item", mListAllData.get(position).mis_teach);
                        mtextViewOnlinePersonNumber.setText("共 " + mTotalUser   + "人, "
                				+ mOnlineUser + "人在线");
                    } else {
                        intent.putExtra("mid", mListAllStudentData.get(position).mMid);
                        intent.putExtra("item", mListAllStudentData.get(position).mis_teach);
                        mtextViewOnlinePersonNumber.setText("共 " + mTotalStudentUser   + "人, "
                				+ mOnlineStudentUser + "人在线");
                    }
                } else {
                	// Click teacher
                    if (mCurrentTab == CURRENT_TAB_1) {
                        intent.putExtra("mid", mListZuiJinData.get(position).mMid);
                        intent.putExtra("item", mListZuiJinData.get(position).mis_teach);
                    } else {
                        intent.putExtra("mid", mListGuanZhuData.get(position).mMid);
                        intent.putExtra("item", mListGuanZhuData.get(position).mis_teach);
                    }
                }
				intent.putExtra("position", position);
				intent.setClass(MainViewActivity.this, IntroduceActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.gengduo_main_activity_menu, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	public void onBackPressed() {
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.gengduo_main_activity_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.munu_shezhi:
			Intent exitIntent = new Intent();
			exitIntent.setClass(this, SettingActivity.class);
			startActivity(exitIntent);
			return true;
		case R.id.munu_tonghuashezhi:
			startprefActivity();
			return true;
		case R.id.munu_bangzhu:
			Intent intent = new Intent();
			intent.setClass(this, HelpActivity.class);
			startActivity(intent);
			break;
		case R.id.munu_guanyu:
			Intent in = new Intent(Intent.ACTION_MAIN);
			in.setClass(this, AboutZhizhiActivity.class);
			startActivity(in);
			return true;
		case R.id.munu_zijinzhanghu:
			if (LinphoneService.instance().username == null
					|| LinphoneService.instance().username.length() == 0) {
				Intent intent2 = new Intent();
				intent2.setClass(this, LoginActivity.class);
				startActivity(intent2);
			} else {
				Intent intent2 = new Intent();
				intent2.setClass(this, AccountActivity.class);
				startActivity(intent2);
			}
			return true;
		case R.id.munu_tuichu:
			if (LinphoneService.instance().username != null
					&& LinphoneService.instance().username.length() != 0) {
				try {
					String str = NetworkUtils.getExitResultForHttpGet();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			Intent aa = new Intent(Intent.ACTION_MAIN);
			aa.setClass(this, LinphoneService.class);
			stopService(aa);
			finish();
			return true;
		default:
			Log.e(LinphoneService.TAG, "Unknown menu item [" + item + "]");
			break;
		}

		return false;
	}
	
	protected void startprefActivity() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setClass(this, LinphonePreferencesActivity.class);
		startActivity(intent);
	}

	   void tryToDownloadFile() {
	        new Thread() {
	            public void run() {
	                for (int i = 0; i < MainViewActivity.mListAllStudentData.size(); i++) {
	                    try {
	                        String str = MainViewActivity.mListAllStudentData.get(i).mPersonImageDrawablePath;
	                        str = str.replace("open.zhizhi.com", NetworkUtils.HostDownLoadIP);
	                        NetworkUtils.download(str, UploadFileUtils.filename,
	                                MainViewActivity.mListAllStudentData.get(i).mMid + ".zz");
	                    } catch (Exception e1) {
	                        e1.printStackTrace();
	                    }
	                }
	            };
	        }.start();
	    }

	   Handler handler = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {

	            switch (msg.what) {
	            case 1:
	                if (mCurrentPage == CURRENT_PAGE_ALL) {
	                	mtextViewOnlinePersonNumber.setVisibility(View.VISIBLE);
                        if(mCurrentTab == CURRENT_TAB_1) {
                             mMainViewListAdapter.setData(mListAllData);
                             mMainViewListAdapter.notifyDataSetChanged();
                             mtextViewOnlinePersonNumber.setText("共 " + mTotalUser   + "人, "
                     				+ mOnlineUser + "人在线");
                        } else if(mCurrentTab == CURRENT_TAB_2){
                             mMainViewListAdapter.setData(mListAllStudentData);
                             mMainViewListAdapter.notifyDataSetChanged();
                             mtextViewOnlinePersonNumber.setText("共 " + mTotalStudentUser   + "人, "
                     				+ mOnlineStudentUser + "人在线");
                        }
                    } else if (mCurrentPage == CURRENT_PAGE_LIANXIREN) {
                    	mtextViewOnlinePersonNumber.setVisibility(View.GONE);
                        if(mCurrentTab == CURRENT_TAB_1) {
                            mMainViewListAdapter.setData(mListZuiJinData);
                            mMainViewListAdapter.notifyDataSetChanged();
                       } else if(mCurrentTab == CURRENT_TAB_2){
                            mMainViewListAdapter.setData(mListGuanZhuData);
                            mMainViewListAdapter.notifyDataSetChanged();
                       }
                    }
	                break;
	            case 2:

	                break;
	            case 3:
	                new Thread() {
	                    public void run() {
//	                        Intent aa = new Intent(Intent.ACTION_MAIN);
//	                        aa.setClass(StartupActivity.this, LinphoneService.class);
//	                        stopService(aa);
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
