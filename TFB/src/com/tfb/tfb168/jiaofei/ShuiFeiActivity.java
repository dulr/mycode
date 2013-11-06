package com.tfb.tfb168.jiaofei;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;

import com.tfb.tfb168.MainActivity;
import com.tfb.tfb168.R;
import com.tfb.tfb168.network.OFCARDApi;
import com.tfb.tfb168.network.tfpayServiceApi;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class ShuiFeiActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuifei);
//        mHandler.sendEmptyMessageDelayed(1, 2000);
        
        
        
        
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
                intent.setClass(ShuiFeiActivity.this, MainActivity.class);                
                startActivity(intent);
                finish();
                break;
            };

        };

    };
}
