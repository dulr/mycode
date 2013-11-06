package com.example.zjtproject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKGeocoderAddressComponent;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.zjtproject.NearbyActivity.MyGeneralListener;
import com.example.zjtproject.NearbyActivity.MyLocationListenner;
import com.example.zjtproject.data.CityCode;
import com.example.zjtproject.jsonparser.JsonParser;
import com.example.zjtproject.network.AESEncryptor;
import com.example.zjtproject.network.Base64;
import com.example.zjtproject.network.CommonApi;
import com.example.zjtproject.network.DES;
import com.example.zjtproject.network.NearbyApi;
import com.example.zjtproject.network.NetworkUtils;
import com.example.zjtproject.network.OilApi;
import com.example.zjtproject.network.SimpleCrypto;
import com.example.zjtproject.network.SuijiShu;
import com.example.zjtproject.utils.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener
{	
	ImageView nearby_btn,edit_btn,dingwei_btn,shuaxin_btn,header_btn;
	RelativeLayout mRelativeLayoutHuoDong,mRelativeLayoutNearby ,mRelativeLayoutWeiZhang;
	RelativeLayout mRelativeLayoutHuDong,mRelativeLayoutInfor;
	
	public static long mTimediffwithserver;
	public static String mloginCookie;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLocation();
        TypedValue outValue = new TypedValue();
        outValue.type = 3;
        TypedValue outValue1 = new TypedValue();
        try {
            getResources().getValue(R.drawable.address_background, outValue1, true);
            
            getResources().getValue("res/drawable-hdpi/address_background.png", outValue, true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            getResources().getValue("res/drawable-hdpi/address_background.png", outValue, false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      

        new Thread() {
            public void run() {
                try {
                    String datestr = CommonApi.GetServerDatetime();
                    datestr =  (datestr.subSequence(1, datestr.length()-1)).toString();
                    Utils.Logi(NetworkUtils.TAG, "datestr=" + datestr);
                    SimpleDateFormat formatter = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    Date serverDate = null;
                    try {
                        serverDate = formatter.parse(datestr);
                    } catch (Exception e) {
                    }
                    long currentDate = System.currentTimeMillis();
                    mTimediffwithserver = serverDate.getTime() - currentDate;
                    Utils.Logi(NetworkUtils.TAG, "mTimediffwithserver=" + mTimediffwithserver);
//                    List<NameValuePair> list = new ArrayList<NameValuePair>();
//                    list.add(new BasicNameValuePair("mobile", "13681175201"));
//                    list.add(new BasicNameValuePair("content", "1"));
//                    list.add(new BasicNameValuePair("token",
//                            DES.encryptDES(SuijiShu.getToken(mTimediffwithserver),DES.KEY)));
//
//                    CommonApi.SendShortMessage(list);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };
        }.start();
        
        new Thread() {
            public void run() {
//                try {
//                  
//                } catch (ClientProtocolException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
            };
        }.start();
    
      
    }
    @Override
    protected void onDestroy() {
        if (mBMapMan != null) {
            mBMapMan.destroy();
            mBMapMan = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mBMapMan != null) {
            mBMapMan.stop();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mBMapMan != null) {
            mBMapMan.start();
        }
        super.onResume();
    }
    private void initView() {
    	dingwei_btn = (ImageView) findViewById(R.id.dingwei);
    	dingwei_btn.setOnClickListener(this);
    	shuaxin_btn = (ImageView) findViewById(R.id.shuaxin);
    	shuaxin_btn.setOnClickListener(this);
    	edit_btn = (ImageView) findViewById(R.id.edit);
    	edit_btn.setOnClickListener(this);
    	
    	header_btn = (ImageView) findViewById(R.id.imageViewheader);
    	header_btn.setOnClickListener(this);
//    	nearby_btn = (Button) findViewById(R.id.nearby);
//    	nearby_btn.setOnClickListener(this);
    	mRelativeLayoutHuoDong  = (RelativeLayout) findViewById(R.id.RelativeLayoutHuoDong);
    	mRelativeLayoutHuoDong.setOnClickListener(this);
    	mRelativeLayoutNearby = (RelativeLayout) findViewById(R.id.RelativeLayoutNearby);
    	mRelativeLayoutNearby.setOnClickListener(this);
    	mRelativeLayoutWeiZhang = (RelativeLayout) findViewById(R.id.RelativeLayoutWeiZhang);
    	mRelativeLayoutWeiZhang.setOnClickListener(this);	
    	mRelativeLayoutHuDong = (RelativeLayout) findViewById(R.id.RelativeLayoutHuDong);
    	mRelativeLayoutHuDong.setOnClickListener(this);
    	mRelativeLayoutInfor = (RelativeLayout) findViewById(R.id.RelativeLayoutInfor);
    	mRelativeLayoutInfor.setOnClickListener(this);
	}
    
    public void onClick(View v)
    {
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.imageViewheader://头像
		{
			Intent intent = new Intent();                
			intent.setClass(this, PersonInfoActivity.class);                
			startActivity(intent);
		}
		break;
		case R.id.edit:// 编辑
		{
			Intent intent = new Intent();                
			intent.setClass(this, LoginActivity.class);                
			startActivity(intent);
		}
			break;
		case R.id.dingwei:// 定位
		{
//			Intent intent = new Intent();                
//			intent.setClass(this, RegistActivity.class);                
//			startActivity(intent); 
		    mLocClient.start();
		    mLocClient.requestLocation();
		}
			break;
		case R.id.shuaxin:// 刷新
		{
			Intent intent = new Intent();                
			intent.setClass(this, NearbyDetailActivity.class);                
			startActivity(intent);
		}
			break;
		case R.id.RelativeLayoutHuoDong://活动
		{
			
		}
		break;
		case R.id.RelativeLayoutNearby: //附近
		{
			Intent intent = new Intent();                
			intent.setClass(this, NearbyActivity.class);                
			startActivity(intent); 
		}
		break;
		case R.id.RelativeLayoutWeiZhang://违章
		{
			Intent intent = new Intent();                
			intent.setClass(this, ViolationsList.class);                
			startActivity(intent);
		}
		break;
		case R.id.RelativeLayoutHuDong://互动
		{
			Intent intent = new Intent();   
			if(mloginCookie == null) intent.setClass(this, LoginActivity.class);          
			else
			intent.setClass(this, UserInteractionList.class);               
			startActivity(intent);
		}
		break;
		case R.id.RelativeLayoutInfor://消息
		{
			Intent intent = new Intent();                
			intent.setClass(this, ContactList.class);                
			startActivity(intent);
		}
		break;
		default:
			break;
		}
    }
    
    
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
            case GET_POSITION_SUCCESS:
                getOilList();
                break;

            case GET_OILLIST_SUCCESS:

                break;
            };

        };

    };
    
  
    String getCityCode() {
        String citycode = null;
        if (mCity != null)
            for (CityCode.CityInfo info : CityCode.sCityInfo) {
                if (mCity.contains(info.mCityName)) {
                    citycode = info.mCityCode;
                }
            }

        if (citycode == null && mprovince != null)
            for (CityCode.CityInfo info : CityCode.sCityInfo) {
                if (mprovince.contains(info.mCityName)) {
                    citycode = info.mCityCode;
                }
            }

        if (citycode == null)
            citycode = "100010";
        return citycode;
    }
    
    void getOilList() {
        new Thread() {
            public void run() {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("CityCode", getCityCode()));
                try {
                    list.add(new BasicNameValuePair(
                            "token",
                            DES.encryptDES(
                                    SuijiShu.getToken(MainActivity.mTimediffwithserver),
                                    DES.KEY)));

                    String str = OilApi.OilList(list);

                  
                   mHandler.sendEmptyMessage(GET_OILLIST_SUCCESS);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    
    
    
    static final int GET_POSITION_SUCCESS = 1;
    static final int GET_OILLIST_SUCCESS = 2;
    // 
    // 定位相关
    LocationClient mLocClient;
 public static   BMapManager mBMapMan = null;
    String mCity= null;
    String mprovince= null;
    public MyLocationListenner myListener = new MyLocationListenner();

    void initLocation() {
        mBMapMan = new BMapManager(this);
        mBMapMan.init("4C623EDB878F39B4A445B04422815CBB2228CCCD", null);
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(5000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    /**
     * 监听函数，又新位置的时候，格式化成字符串，输出到屏幕中
     */
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null)
                return;

            // locData.latitude = location.getLatitude();
            // locData.longitude = location.getLongitude();
            // locData.accuracy = location.getRadius();
            // locData.direction = location.getDerect();
            // myLocationOverlay.setData(locData);
            // mMapView.refresh();
            MKSearch mSearch = new MKSearch();
            mSearch.init(mBMapMan, new MySearchListener());
            mSearch.reverseGeocode(new GeoPoint(
                    (int) (location.getLatitude() * 1e6), (int) (location
                            .getLongitude() * 1e6)));
            // if(count >1 ) return;
            // mMapController.animateTo(new GeoPoint((int)(locData.latitude*
            // 1e6), (int)(locData.longitude * 1e6)),
            // mHandler.obtainMessage(1));

        }

        public void onReceivePoi(BDLocation poiLocation) {
            if (poiLocation == null) {
                return;
            }
        }
    }

    public class MySearchListener implements MKSearchListener {

        @Override
        public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
            MKGeocoderAddressComponent kk = arg0.addressComponents;
            mCity = kk.city;
            mprovince = kk.province;
            Utils.Logi(NetworkUtils.TAG, "city=" + mCity);
            Utils.Logi(NetworkUtils.TAG, "province=" +  kk.province);
            mLocClient.stop();
            mHandler.sendEmptyMessage(GET_POSITION_SUCCESS);
        }

        @Override
        public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
        }

        @Override
        public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {

        }

        @Override
        public void onGetPoiDetailSearchResult(int arg0, int arg1) {

        }

        @Override
        public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {

        }

        @Override
        public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {

        }

        @Override
        public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {

        }

        @Override
        public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {

        }
    }
}
