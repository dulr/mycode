package com.example.zjtproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
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
import com.example.zjtproject.data.ShopInfo;
import com.example.zjtproject.jsonparser.JsonParser;
import com.example.zjtproject.network.DES;
import com.example.zjtproject.network.NearbyApi;
import com.example.zjtproject.network.SuijiShu;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

public class NearbyActivity extends Activity {
    TextView mtextViewCreate;
    TextView mNearby_back, mNearby_Classification;

    static MapView mMapView = null;

    private MapController mMapController = null;

    public MKMapViewListener mMapListener = null;
    FrameLayout mMapViewContainer = null;

    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    public NotifyLister mNotifyer = null;

    Button testUpdateButton = null;

    EditText indexText = null;
    MyLocationOverlay myLocationOverlay = null;
    int index = 0;
    LocationData locData = null;

//    BMapManager mBMapMan = null;

    
    ShopInfoOverlay mShopInfoOverlay;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MainActivity.mBMapMan == null) {
            MainActivity.mBMapMan = new BMapManager(this);
            MainActivity.mBMapMan.init(
                    "4C623EDB878F39B4A445B04422815CBB2228CCCD",
                    new MyGeneralListener());
        }
        // 注意：请在试用setContentView前初始化BMapManager对象，否则会报错

        setContentView(R.layout.activity_nearby);
        mMapView = (MapView) findViewById(R.id.bmapsView);
        // mMapView.setBuiltInZoomControls(true);
        // 设置启用内置的缩放控件
        mMapController = mMapView.getController();
        // 得到mMapView的控制权,可以用它控制和驱动平移和缩放
        // GeoPoint point =new GeoPoint((int)(39.915* 1E6),(int)(116.404* 1E6));
        // 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
        // mMapController.setCenter(point);//设置地图中心点

        initMapView();

        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(5000);
        mLocClient.setLocOption(option);
        mLocClient.start();
        mMapView.getController().setZoom(14);
        mMapView.getController().enableClick(true);

        mMapView.setBuiltInZoomControls(true);
        mMapListener = new MKMapViewListener() {

            @Override
            public void onMapMoveFinish() {
                // TODO Auto-generated method stub
            }

            @Override
            public void onClickMapPoi(MapPoi mapPoiInfo) {
                // TODO Auto-generated method stub
                String title = "";
                if (mapPoiInfo != null) {
                    title = mapPoiInfo.strText;
                    // Toast.makeText(LocationOverlayDemo.this,title,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onGetCurrentMap(Bitmap b) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onMapAnimationFinish() {
                // TODO Auto-generated method stub

            }
        };
        mMapView.regMapViewListener(MainActivity.mBMapMan, mMapListener);
        myLocationOverlay = new MyLocationOverlay(mMapView);
        locData = new LocationData();
        myLocationOverlay.setData(locData);
        mMapView.getOverlays().add(myLocationOverlay);
        myLocationOverlay.enableCompass();
        mMapView.refresh();

        mLocClient.requestLocation();
        addShopInfoOverlay();
        
        
        mtextViewCreate = (TextView) findViewById(R.id.textViewCreate);
        mtextViewCreate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                
            }
        });

        mNearby_Classification = (TextView) findViewById(R.id.Nearby_Classification);
        mNearby_Classification.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	mlinearLayoutOptionMenu.setVisibility(View.VISIBLE);
            }

        });

        mNearby_back = (TextView) findViewById(R.id.Nearby_back);
        mNearby_back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }

        });
        initOptionMenu();
    }

    @Override
    public void onBackPressed() {
        if (mlinearLayoutOptionMenu.getVisibility() == View.VISIBLE)
            mlinearLayoutOptionMenu.setVisibility(View.GONE);
        else {
            super.onBackPressed();
        }
    }
    
    LinearLayout mlinearLayoutOptionMenu;
    TextView mtextViewxiche;
    TextView mtextViewTiemo;
    TextView mtextViewDumo;
    TextView mtextViewDala;
    TextView mtextViewFengyou;
    TextView mtextViewQuxiao;
    private void initOptionMenu() {
        mlinearLayoutOptionMenu = (LinearLayout) findViewById(R.id.linearLayoutOptionMenu);
  
        mtextViewxiche = (TextView) findViewById(R.id.textViewxiche);
        mtextViewxiche.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        
        mtextViewTiemo = (TextView) findViewById(R.id.textViewTiemo);
        mtextViewTiemo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        
        mtextViewDumo = (TextView) findViewById(R.id.textViewDumo);
        mtextViewDumo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        
        mtextViewDala = (TextView) findViewById(R.id.textViewDala);
        mtextViewDala.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        
        mtextViewFengyou = (TextView) findViewById(R.id.textViewFengyou);
        mtextViewFengyou.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        
        mtextViewQuxiao = (TextView) findViewById(R.id.textViewQuxiao);
        mtextViewQuxiao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mlinearLayoutOptionMenu.setVisibility(View.GONE);
            }
        });
        
    }
    private void initMapView() {
        mMapView.setLongClickable(true);
        // mMapController.setMapClickEnable(true);
        // mMapView.setSatellite(false);
    }

    /**
     * 监听函数，又新位置的时候，格式化成字符串，输出到屏幕中
     */
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null)
                return;
            if(count >0 ) return;
            locData.latitude = location.getLatitude();
            locData.longitude = location.getLongitude();
            locData.accuracy = location.getRadius();
            locData.direction = location.getDerect();
            myLocationOverlay.setData(locData);
            mMapView.refresh();
//            MKSearch mSearch = new MKSearch();
//            mSearch.ini
//            mSearch.reverseGeocode(new GeoPoint((int)(location.getLatitude()*1e6),(int)(location.getLongitude()*1e6)));
            
            mMapController.animateTo(new GeoPoint((int)(locData.latitude* 1e6), (int)(locData.longitude *  1e6)),
                    mHandler.obtainMessage(1));
        
        }

        public void onReceivePoi(BDLocation poiLocation) {
            if (poiLocation == null) {
                return;
            }
        }
  }

    
    public class NotifyLister extends BDNotifyListener {
        public void onNotify(BDLocation mlocation, float distance) {
        }
    }
    static int count = 0;
   public static List<OverlayItem> mGeoList = new ArrayList<OverlayItem>();
   public static List<ShopInfo> mShopInfolist;
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            
//            Toast.makeText(getApplicationContext(), "msg:" + msg.what,
//                    Toast.LENGTH_SHORT).show();
            if(count >1 ) return;
           switch (msg.what) {
           case 1:

           count ++;
            new Thread() {
                public void run() {
                    List<NameValuePair> list = new ArrayList<NameValuePair>();
                    list.add(new BasicNameValuePair("Distance", "5.0"));
                    int cLat = 39909230 ;
                    int cLon = 116397428 ;
//                    double mLat1 = 39.90923; // point1纬度
//                    double mLon1 = 116.357428; // point1经度
//                    list.add(new BasicNameValuePair("X", Double
//                          .toString(mLat1)));
//                    list.add(new BasicNameValuePair("Y", Double
//                            .toString(mLon1)));

                    list.add(new BasicNameValuePair("X", Double
                            .toString(locData.latitude)));
                    list.add(new BasicNameValuePair("Y", Double
                            .toString(locData.longitude)));

                    try {
                        list.add(new BasicNameValuePair(
                                "token",
                                DES.encryptDES(
                                        SuijiShu.getToken(MainActivity.mTimediffwithserver),
                                        DES.KEY)));

                        String str = NearbyApi.Nearby(list);
                        mShopInfolist =JsonParser.ShopInfoParser(str);
                        if(mShopInfolist == null || mShopInfolist.size() == 0) return;
                        // overlay 数量 
                        int iSize = mShopInfolist.size();
                        double pi = 3.1415926 ;
                   
                        // overlay半径
                        int r = 50000;
                        
                        mGeoList.clear();
                        // 准备overlay 数据
                        for (int i=0; i<iSize ; i++){
//                            int lat = (int) (cLat + r*Math.cos(2*i*pi/iSize));
//                            int lon = (int) (cLon + r*Math.sin(2*i*pi/iSize));
                            
                            int lat = (int) (mShopInfolist.get(i).mX * 1e6);
                            int lon = (int) (mShopInfolist.get(i).mY * 1e6);
                            OverlayItem item= new OverlayItem(new GeoPoint( lon,lat),
                                    mShopInfolist.get(i).mName,mShopInfolist.get(i).mName);
                            item.setMarker(getResources().getDrawable(R.drawable.icon_marka));
                            mGeoList.add(item);
                       
                        }
                        sendEmptyMessage(2);
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();
        break;
           case 2:
               if(mShopInfoOverlay !=null)
               mShopInfoOverlay.addItem(mGeoList);
               if(mMapView !=null)
               mMapView.refresh();
               break;
           }
        
        };
    };

    @Override
    protected void onDestroy() {
        mMapView.destroy();
//        if (mBMapMan != null) {
//            mBMapMan.destroy();
//            mBMapMan = null;
//        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
//        if (mBMapMan != null) {
//            mBMapMan.stop();
//        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
//        if (mBMapMan != null) {
//            mBMapMan.start();
//        }
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_nearby, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 1: {
            Intent intent = new Intent();
            intent.setClass(this, NearbyDetailActivity.class);
            startActivity(intent);
        }
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    // 常用事件监听，用来处理通常的网络错误，授权验证错误等
    class MyGeneralListener implements MKGeneralListener {

        @Override
        public void onGetNetworkState(int iError) {
            if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
                Toast.makeText(getApplicationContext(), "您的网络出错啦！",
                        Toast.LENGTH_LONG).show();
            } else if (iError == MKEvent.ERROR_NETWORK_DATA) {
                Toast.makeText(getApplicationContext(), "输入正确的检索条件！",
                        Toast.LENGTH_LONG).show();
            }
            // ...
        }

        @Override
        public void onGetPermissionState(int iError) {
            if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
                // 授权Key错误：
                // Toast.makeText(DemoApplication.getInstance().getApplicationContext(),
                // "请在 DemoApplication.java文件输入正确的授权Key！",
                // Toast.LENGTH_LONG).show();
                // DemoApplication.getInstance().m_bKeyRight = false;
            }
        }
    }

    void addShopInfoOverlay() {
        Drawable marker = this.getResources().getDrawable(R.drawable.icon_marka);
//        mMapView.getOverlays().clear();
        mShopInfoOverlay = new ShopInfoOverlay(marker, this,mMapView); 
        mMapView.getOverlays().add(mShopInfoOverlay);
    }

   

   
    
    public class MySearchListener implements MKSearchListener {

        @Override
        public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
            // TODO Auto-generated method stub
            MKGeocoderAddressComponent kk = arg0.addressComponents;
            String city = kk.city;
        }

        @Override
        public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onGetPoiDetailSearchResult(int arg0, int arg1) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {
            // TODO Auto-generated method stub
            
        }
    }
    
    
    
    

}






class ShopInfoOverlay extends ItemizedOverlay<OverlayItem> {
    public List<OverlayItem> mGeoList = new ArrayList<OverlayItem>();
    private Context mContext = null;
    static PopupOverlay pop = null;

    Toast mToast = null;
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        return bitmap;
    }
    public ShopInfoOverlay(Drawable marker,Context context, MapView mapView){
        super(marker,mapView);
        this.mContext = context;
        pop = new PopupOverlay( NearbyActivity.mMapView,new PopupClickListener() {
            
            @Override
            public void onClickedPopup(int index) {
//                if (null == mToast)
//                    mToast = Toast.makeText(mContext, "popup item :" + index + " is clicked.", Toast.LENGTH_SHORT);
//                else mToast.setText("popup item :" + index + " is clicked.");
//                mToast.show();
                
                Intent intent = new Intent();                
                intent.setClass(mContext, NearbyDetailActivity.class);  
                for (ShopInfo info : NearbyActivity.mShopInfolist) {
                    if (info.mName.equals(getItem(mIndex).getTitle())) {
                        intent.putExtra("ID", info.mID);
                        break;
                    }
                }

                mContext.startActivity(intent);
            }
        });
       // 自2.1.1 开始，使用 add/remove 管理overlay , 无需调用以下接口.
       // populate();
        
    }
    static int mIndex;
    protected boolean onTap(int index) {
        System.out.println("item onTap: "+index);
        mIndex = index;
        TextView view = new TextView(mContext);
        view.setBackgroundResource(R.drawable.black);
        view.setText(getItem(index).getTitle());
        
        Bitmap[] bmps = new Bitmap[3];
        if (index %2 == 0) {
            try {
                bmps[0] = BitmapFactory.decodeStream(mContext.getAssets().open("marker1.png"));
//                bmps[1] = BitmapFactory.decodeStream(mContext.getAssets().open("marker2.png"));
                bmps[1] = convertViewToBitmap(view);
                bmps[2] = BitmapFactory.decodeStream(mContext.getAssets().open("marker3.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                bmps[2] = BitmapFactory.decodeStream(mContext.getAssets().open("marker1.png"));
//                bmps[1] = BitmapFactory.decodeStream(mContext.getAssets().open("marker2.png"));
                bmps[1] = convertViewToBitmap(view);
                bmps[0] = BitmapFactory.decodeStream(mContext.getAssets().open("marker3.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        pop.showPopup(bmps, getItem(index).getPoint(), 32);
        if (null == mToast)
            mToast = Toast.makeText(mContext, getItem(index).getTitle(), Toast.LENGTH_SHORT);
        else mToast.setText(getItem(index).getTitle());
        mToast.show();
        
        
        return true;
    }
    public boolean onTap(GeoPoint pt, MapView mapView){
        if (pop != null){
            pop.hidePop();
        }
        super.onTap(pt,mapView);
        return false;
    }
    
    // 自2.1.1 开始，使用 add/remove 管理overlay , 无需重写以下接口
    /*
    @Override
    protected OverlayItem createItem(int i) {
        return mGeoList.get(i);
    }
    
    @Override
    public int size() {
        return mGeoList.size();
    }
    */
}
