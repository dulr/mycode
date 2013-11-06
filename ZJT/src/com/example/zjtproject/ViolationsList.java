package com.example.zjtproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.example.zjtproject.data.ViolationsListData;
import com.example.zjtproject.data.ViolationInfo;
import com.example.zjtproject.data.ViolationItemData;
import com.example.zjtproject.data.ZJTDatabaseHelper;
import com.example.zjtproject.jsonparser.JsonParser;
import com.example.zjtproject.network.DES;
import com.example.zjtproject.network.NearbyApi;
import com.example.zjtproject.network.SuijiShu;
import com.example.zjtproject.network.ViolationsApi;
import com.example.zjtproject.utils.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.AbsListView.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class ViolationsList extends Activity
{
	TextView mPeccancy_Refesh,mPeccancy_back;
	Button mbuttonAddcarInfo;
    ListView mViolationsList = null;
    public List<ViolationsListData> mViolationsListData;
    public List<ViolationInfo> mAllViolationInfo;
    public List<View> mFootView;
    ViolationsListAdapter mViolationsListAdapter;
    ViolationInfo mViolationInfo;
    ProgressDialog mProgressDialog = null;
    enum ListShowState {
        EShowItem,
        EShowDetail
    }
    ListShowState mListShowState = ListShowState.EShowItem;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peccancy_list);
        
        
        mViolationsList = (ListView) findViewById(R.id.ListViewViolationsList);
        mViolationsListData = new ArrayList<ViolationsListData>();
        mAllViolationInfo = new ArrayList<ViolationInfo>();
        mFootView= new ArrayList<View>();
        ViolationsListData data = new ViolationsListData();
        
//        data.mCarNum ="1111";
//        data.mWeizhangCounts = 4;
//        data.mWeizhangRiqi = "3333333333";
        
        refreshAllViolations ();
//        for(String[] Data : allCarinfo) {
//            data.mCarNum =Data[0]+ Data[1];
////            data.mWeizhangCounts = Data[1];
//            data.mWeizhangRiqi = Data[2];
//            mPeccancyListData.add(data);
//        }
        
//        mPeccancyListAdapter.setData(mPeccancyListData);
//        mPeccancyListAdapter.notifyDataSetChanged();
        
        mViolationsListAdapter = new ViolationsListAdapter(this,mViolationsListData);
        mViolationsList.setAdapter(mViolationsListAdapter);
        setItemclick();
        mViolationsListAdapter.notifyDataSetChanged();
        
        //刷新
        mPeccancy_Refesh  = (TextView) findViewById(R.id.Peccancy_Refesh);
        mPeccancy_Refesh.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			    refreshAllViolations ();				
			}
        	
        });

        //返回
        mPeccancy_back  = (TextView) findViewById(R.id.Peccancy_back);
        mPeccancy_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    onBackPressed();
			}
        	
        });
        
        mbuttonAddcarInfo = (Button) findViewById(R.id.buttonAddcarInfo);
        mbuttonAddcarInfo.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                createAddCarInfoDialog().show();
            }
            
        });
    }
    
    AddCarInfoDialog createAddCarInfoDialog () {

        final AddCarInfoDialog.Builder customBuilder = new
                AddCarInfoDialog.Builder(ViolationsList.this);
            customBuilder.setTitle("Custom title")
                .setMessage("Custom body")
                .setNegativeButton("取消", 
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", 
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       String CarRegion =customBuilder.mbuttonChePaiDi.getText().toString();
                        
                       String CarNo= customBuilder.medittextChepaiHao.getText().toString();
                       String EngineNo =customBuilder.meditTextFadongjiHao.getText().toString();
                               
                        ZJTDatabaseHelper.insertData(
                                getApplicationContext(), CarRegion, CarNo, EngineNo);
                        getViolationsQuery(CarNo,
                                EngineNo);
                        
                        
                        dialog.dismiss();
                        mProgressDialog = Utils.createProgressDialog(ViolationsList.this);
                        mProgressDialog.show();
                    }
                })
                ;
            AddCarInfoDialog dialog = customBuilder.create();
         return    dialog;
    }
    
    void refreshAllViolations () {
        List<String[]> allCarinfo = ZJTDatabaseHelper.queryAllData(getApplicationContext());
        if(allCarinfo.size() >0) {
            mProgressDialog = Utils.createProgressDialog(ViolationsList.this);
            mProgressDialog.show();
            getAllViolationsQuery(allCarinfo);
        }
    }
    void setItemclick() {

        // 添加点击
        mViolationsList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                if(mListShowState == ListShowState.EShowItem) {
//                    mPeccancyList.getAdapter().clear();
                    ViolationsListData data = new ViolationsListData();
                    mViolationInfo = mAllViolationInfo.get(position);
                    data.mCarNum = mViolationInfo.mCarNumber;
                    data.mWeizhangCounts = mViolationInfo.mViolationNum;
                    data.mWeizhangRiqi = mViolationInfo.mCarExamineValidity;
                    
                  
                    mViolationsList.setAdapter(null);
                    List<ViolationsListData> mData = new ArrayList<ViolationsListData>();
                    mData.add(data);
                    for(ViolationItemData itemData : mViolationInfo.mViolationListData) {
                        ViolationsListDetailItem item = new ViolationsListDetailItem(ViolationsList.this,itemData);
                        LayoutParams params = new LayoutParams(
                                LayoutParams.MATCH_PARENT,
                                LayoutParams.WRAP_CONTENT);
                        item.setLayoutParams(params);                        
                        mViolationsList.addFooterView(item);
                        mFootView.add(item);
                    }

                    mViolationsListAdapter.setData(mData);
                    mViolationsListAdapter.notifyDataSetChanged();
                    mViolationsList.setAdapter(mViolationsListAdapter);
                    mListShowState = ListShowState.EShowDetail;
                    mPeccancy_Refesh.setVisibility(View.GONE);
                    mbuttonAddcarInfo.setVisibility(View.GONE);
                }
            };
        });
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if(mListShowState == ListShowState.EShowDetail) {
            mViolationsList.setAdapter(null);
            for(View itemData : mFootView) {                      
                mViolationsList.removeFooterView(itemData);
                mFootView.remove(mFootView);
            }
            
            mViolationsListAdapter.setData(mViolationsListData);
            mViolationsList.setAdapter(mViolationsListAdapter);
//            mPeccancyList.removeFooterView(v);
            mViolationsListAdapter.notifyDataSetChanged();
            mListShowState = ListShowState.EShowItem;
            mPeccancy_Refesh.setVisibility(View.VISIBLE);
            mbuttonAddcarInfo.setVisibility(View.VISIBLE);
        } else
            
        super.onBackPressed();
    }
    void getAllViolationsQuery(final List<String[]> allCarinfo) {
        new Thread() {
            public void run() {
                for(String[] carinfo : allCarinfo) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();    
                list.add(new BasicNameValuePair("CarNo", carinfo[1]));
                list.add(new BasicNameValuePair("EngineNo", carinfo[2]));
                try {
                    list.add(new BasicNameValuePair(
                            "Token",
                            DES.encryptDES(
                                    SuijiShu.getToken(MainActivity.mTimediffwithserver),
                                    DES.KEY)));

                    String str = ViolationsApi.Query(list);
                    ViolationInfo  violationInfo =JsonParser.ViolationInfoParser(str);
                    mAllViolationInfo.add(violationInfo);

                    
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
//                    handler.sendEmptyMessage(2);
                } catch (Exception e){
                    e.printStackTrace();
//                    handler.sendEmptyMessage(2);
                }
                }
                
                handler.sendEmptyMessage(3);
            }
        }.start();
    }
    
    void getViolationsQuery(final String CarNo, final String EngineNo) {
        new Thread() {
            public void run() {
                List<NameValuePair> list = new ArrayList<NameValuePair>();    
                list.add(new BasicNameValuePair("CarNo", "P3FM52"));
                list.add(new BasicNameValuePair("EngineNo", "ba932289"));
                try {
                    list.add(new BasicNameValuePair(
                            "Token",
                            DES.encryptDES(
                                    SuijiShu.getToken(MainActivity.mTimediffwithserver),
                                    DES.KEY)));

                    String str = ViolationsApi.Query(list);
                    mViolationInfo =JsonParser.ViolationInfoParser(str);

                    mAllViolationInfo.add(mViolationInfo);
                    handler.sendEmptyMessage(3);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(2);
                } catch (Exception e){
                    e.printStackTrace();
                    handler.sendEmptyMessage(2);
                }
            }
        }.start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mProgressDialog != null)
                mProgressDialog.dismiss();
            switch (msg.what) {

            case 1:
                ViolationsListData data = new ViolationsListData();
                data.mCarNum = mViolationInfo.mCarNumber;
                data.mWeizhangCounts = mViolationInfo.mViolationNum;
                data.mWeizhangRiqi = mViolationInfo.mCarExamineValidity;
                mViolationsListData.add(data);
                mViolationsListAdapter.setData(mViolationsListData);
                mViolationsListAdapter.notifyDataSetChanged();
                break;
            case 3: {
                mViolationsListData.clear();
                for (ViolationInfo info : mAllViolationInfo) {
                    ViolationsListData data2 = new ViolationsListData();
                    data2.mCarNum = info.mCarNumber;
                    data2.mWeizhangCounts = info.mViolationNum;
                    data2.mWeizhangRiqi = info.mCarExamineValidity;
                    mViolationsListData.add(data2);
                }
                mViolationsListAdapter.setData(mViolationsListData);
                mViolationsList.setAdapter(mViolationsListAdapter);
                mViolationsListAdapter.notifyDataSetChanged();
            }
                break;
            }
        }
    };
}
