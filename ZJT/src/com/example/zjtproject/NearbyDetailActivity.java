package com.example.zjtproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.zjtproject.data.ShopDiscountInfo;
import com.example.zjtproject.jsonparser.JsonParser;
import com.example.zjtproject.network.DES;
import com.example.zjtproject.network.NearbyApi;
import com.example.zjtproject.network.SuijiShu;
import com.example.zjtproject.utils.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.app.Activity;
import android.app.ProgressDialog;

public class NearbyDetailActivity extends Activity
{
	TextView mNearby_detail_comment,mNearby_detail_back;
	int mID;
	ProgressDialog mProgressDialog;
	ShopDiscountInfo mShopDiscountInfo;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_detail);
        mID = getIntent().getIntExtra("ID",0);
        mProgressDialog = Utils.createProgressDialog(this);
        mProgressDialog.show();
        initViews();
        getDetailInfo();
        //����
        mNearby_detail_comment  = (TextView) findViewById(R.id.Nearby_detail_comment);
        mNearby_detail_comment.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
			}
        });
        
        //����
        mNearby_detail_back  = (TextView) findViewById(R.id.Nearby_detail_back);
        mNearby_detail_back.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				finish();
			}
        });
    }
    
    TextView mtextViewName;
    TextView mtextViewAddress;
    TextView mtextViewContact;
    TextView mtextViewPriceOriginal;
    TextView mtextViewPrice;
    TextView mtextViewSUVOriginal;
    TextView mtextViewSUV;
    TextView mtextViewMPVOriginal;
    TextView mtextViewMPV;
    TextView mtextViewPastingDiscount;
    TextView mtextViewCoatingDiscount;
    TextView mtextViewWaxingDiscount;
    TextView mtextViewGlazingDiscount;
    void initViews() {
        mtextViewName  = (TextView) findViewById(R.id.textViewName);
        mtextViewAddress  = (TextView) findViewById(R.id.textViewAddress);
        mtextViewContact  = (TextView) findViewById(R.id.textViewContact);
        mtextViewPriceOriginal  = (TextView) findViewById(R.id.textViewPriceOriginal);
        mtextViewPrice  = (TextView) findViewById(R.id.textViewPrice);
        mtextViewSUVOriginal  = (TextView) findViewById(R.id.textViewSUVOriginal);
        mtextViewSUV  = (TextView) findViewById(R.id.textViewSUV);
        mtextViewMPVOriginal  = (TextView) findViewById(R.id.textViewMPVOriginal);
        mtextViewMPV  = (TextView) findViewById(R.id.textViewMPV);
        mtextViewPastingDiscount  = (TextView) findViewById(R.id.textViewPastingDiscount);
        mtextViewCoatingDiscount  = (TextView) findViewById(R.id.textViewCoatingDiscount);
        mtextViewWaxingDiscount  = (TextView) findViewById(R.id.textViewWaxingDiscount);
        mtextViewGlazingDiscount  = (TextView) findViewById(R.id.textViewGlazingDiscount);
    }
    void getDetailInfo() {
        new Thread() {
            public void run() {
                List<NameValuePair> list = new ArrayList<NameValuePair>();    
                list.add(new BasicNameValuePair("ID", Integer
                        .toString(mID)));
                try {
                    list.add(new BasicNameValuePair(
                            "token",
                            DES.encryptDES(
                                    SuijiShu.getToken(MainActivity.mTimediffwithserver),
                                    DES.KEY)));

                    String str = NearbyApi.Discount(list);
                    mShopDiscountInfo =JsonParser.ShopDiscountInfoParser(str);

                    handler.sendEmptyMessage(1);
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
            mProgressDialog.dismiss();
            switch (msg.what) {
            case 1:
                mtextViewName.setText(mShopDiscountInfo.mName);
                mtextViewAddress.setText(mShopDiscountInfo.mAddress);
                mtextViewContact.setText(mShopDiscountInfo.mContact);
                mtextViewPriceOriginal.setText(mShopDiscountInfo.mPriceOriginal+"");
                mtextViewPrice.setText(mShopDiscountInfo.mPrice+"");
                mtextViewSUVOriginal.setText(mShopDiscountInfo.mSUVOriginal+"");
                mtextViewSUV.setText(mShopDiscountInfo.mSUV+"");
                mtextViewMPVOriginal.setText(mShopDiscountInfo.mMPVOriginal+"");
                mtextViewMPV.setText(mShopDiscountInfo.mMPV+"");
                mtextViewPastingDiscount
                        .setText(mShopDiscountInfo.mPastingDiscount == null ?"无" :mShopDiscountInfo.mPastingDiscount);
                mtextViewCoatingDiscount
                        .setText(mShopDiscountInfo.mCoatingDiscount== null ?"无" :mShopDiscountInfo.mCoatingDiscount);
                mtextViewWaxingDiscount
                        .setText(mShopDiscountInfo.mWaxingDiscount== null ?"无" :mShopDiscountInfo.mWaxingDiscount);
                mtextViewGlazingDiscount
                        .setText(mShopDiscountInfo.mGlazingDiscount== null ?"无" :mShopDiscountInfo.mGlazingDiscount);
                break;
            }
        }
    };
}
