package com.tfb.tfb168.jinrong;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;

import com.rongpay.util.PayActivity;
import com.tfb.tfb168.MainActivity;
import com.tfb.tfb168.R;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ShouKuanActivity extends Activity {

    LinearLayout mRelativeLayout1, mRelativeLayout2;
    EditText meditText_Danwei, meditText_Jiner;
    Button mbuttonQueDing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoukuan);
        meditText_Danwei = (EditText) findViewById(R.id.editText_Danwei);
        meditText_Jiner = (EditText) findViewById(R.id.editText_Jiner);
        mbuttonQueDing = (Button) findViewById(R.id.buttonQueDing);
        mbuttonQueDing.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
 
                String danwei = meditText_Danwei.getText().toString();
                String jiner = meditText_Jiner.getText().toString();
                if(danwei!=null && jiner != null) {
                    Intent intent = new Intent();
                  intent.putExtra("title", "收款");
                  intent.putExtra("body", danwei);
                  intent.putExtra("total_fee", jiner);
                  intent.setClass(ShouKuanActivity.this, PayActivity.class);
                  startActivity(intent);
                }
            }
        });
        
        
        
        mRelativeLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        mRelativeLayout1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                startJiaoFeiActivity(ShuiFeiActivity.class);
            }
        });
        
        mRelativeLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        mRelativeLayout2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                startJiaoFeiActivity(DianFeiActivity.class);
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
        intent.setClass(ShouKuanActivity.this, a);
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
                intent.setClass(ShouKuanActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            ;

        };

    };
}
