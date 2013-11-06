package com.tfb.tfb168;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;

import com.tfb.tfb168.network.OFCARDApi;
import com.tfb.tfb168.network.tfpayServiceApi;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class JiaoFeiActivity extends Activity {
    RelativeLayout mRelativeLayout1, mRelativeLayout2, mRelativeLayout3,
    mRelativeLayout4, mRelativeLayout5;
    LinearLayout mLinearLayout6;
    TextView mtextViewTitle;
    int mItemID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiaofei);
        
        mItemID= getIntent().getIntExtra("BianMinItem", 1);
        mtextViewTitle = (TextView) findViewById(R.id.textViewTitle);
       mRelativeLayout1 = (RelativeLayout) findViewById(R.id.linearLayout1);
       mRelativeLayout1.setOnClickListener(new OnClickListener() {

           @Override
           public void onClick(View v) {
//               startJiaoFeiActivity(1);
           }
       });
       
       mRelativeLayout2 = (RelativeLayout) findViewById(R.id.linearLayout2);
       mRelativeLayout2.setOnClickListener(new OnClickListener() {

           @Override
           public void onClick(View v) {
//               startJiaoFeiActivity(2);
           }
       });
       
       mRelativeLayout3 = (RelativeLayout) findViewById(R.id.linearLayout3);
       mRelativeLayout3.setOnClickListener(new OnClickListener() {

           @Override
           public void onClick(View v) {
//               startJiaoFeiActivity(3);
           }
       });
       
       mRelativeLayout4 = (RelativeLayout) findViewById(R.id.linearLayout4);
       mRelativeLayout4.setOnClickListener(new OnClickListener() {

           @Override
           public void onClick(View v) {
//               startJiaoFeiActivity(4);
           }
       });
       
       mRelativeLayout5 = (RelativeLayout) findViewById(R.id.linearLayout5);
       mRelativeLayout5.setOnClickListener(new OnClickListener() {

           @Override
           public void onClick(View v) {
//               startJiaoFeiActivity(5);
           }
       });
       mLinearLayout6 = (LinearLayout) findViewById(R.id.linearLayout6);
//        new Thread() {
//            public void run() {
//                try {
//                    String datestr = tfpayServiceApi.QueryCity();
//                } catch (ClientProtocolException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            };
//        }.start();
       initItem();
    }

    
    void initItem() {
        switch (mItemID) {
        case 1:mtextViewTitle.setText("水费缴纳");
            break;
        case 2:mtextViewTitle.setText("电费缴纳");
            break;
        case 3:mtextViewTitle.setText("燃气费缴纳");
            mRelativeLayout5.setVisibility(View.GONE);
            break;
        case 4:mtextViewTitle.setText("公交卡缴纳");
            mRelativeLayout5.setVisibility(View.GONE);
            break;
        case 5:mtextViewTitle.setText("话费缴纳");
            mRelativeLayout4.setVisibility(View.GONE);
            mRelativeLayout5.setVisibility(View.GONE);
            mLinearLayout6.setVisibility(View.VISIBLE);
            break;

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {

            case 1:
                Intent intent = new Intent();                
                intent.setClass(JiaoFeiActivity.this, MainActivity.class);                
                startActivity(intent);
                finish();
                break;
            };
        };
    };
}
