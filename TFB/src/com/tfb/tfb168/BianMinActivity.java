package com.tfb.tfb168;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;

import com.tfb.tfb168.jiaofei.DianFeiActivity;
import com.tfb.tfb168.jiaofei.GongJiaoKaActivity;
import com.tfb.tfb168.jiaofei.HuaFeiActivity;
import com.tfb.tfb168.jiaofei.RanQiFeiActivity;
import com.tfb.tfb168.jiaofei.ShuiFeiActivity;
import com.tfb.tfb168.jiaofei.WeiZhangFeiActivity;
import com.tfb.tfb168.network.OFCARDApi;
import com.tfb.tfb168.network.tfpayServiceApi;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BianMinActivity extends Activity {

    RelativeLayout mRelativeLayout1, mRelativeLayout2, mRelativeLayout3,
            mRelativeLayout4, mRelativeLayout5, mRelativeLayout6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bianmin);

        
        mRelativeLayout1 = (RelativeLayout) findViewById(R.id.linearLayout1);
        mRelativeLayout1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startJiaoFeiActivity(ShuiFeiActivity.class);
            }
        });
        
        mRelativeLayout2 = (RelativeLayout) findViewById(R.id.linearLayout2);
        mRelativeLayout2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startJiaoFeiActivity(DianFeiActivity.class);
            }
        });
        
        mRelativeLayout3 = (RelativeLayout) findViewById(R.id.linearLayout3);
        mRelativeLayout3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startJiaoFeiActivity(RanQiFeiActivity.class);
            }
        });
        
        mRelativeLayout4 = (RelativeLayout) findViewById(R.id.linearLayout4);
        mRelativeLayout4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startJiaoFeiActivity(GongJiaoKaActivity.class);
            }
        });
        
        mRelativeLayout5 = (RelativeLayout) findViewById(R.id.linearLayout5);
        mRelativeLayout5.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startJiaoFeiActivity(HuaFeiActivity.class);
            }
        });
        
        mRelativeLayout6 = (RelativeLayout) findViewById(R.id.linearLayout6);
        mRelativeLayout6.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startJiaoFeiActivity(WeiZhangFeiActivity.class);
            }
        });
        // new Thread() {
        // public void run() {
        // try {
        // String datestr = tfpayServiceApi.QueryCity();
        // } catch (ClientProtocolException e) {
        // e.printStackTrace();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // };
        // }.start();
    }

    void startJiaoFeiActivity(Class<?> a) {
        Intent intent = new Intent();
//        intent.putExtra("BianMinItem", index);
        intent.setClass(BianMinActivity.this, a);
        startActivity(intent);
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
                intent.setClass(BianMinActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            ;

        };

    };
}
