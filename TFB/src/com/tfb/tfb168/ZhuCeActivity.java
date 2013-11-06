package com.tfb.tfb168;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;

import com.tfb.tfb168.network.NetworkUtils;
import com.tfb.tfb168.network.OFCARDApi;
import com.tfb.tfb168.network.tfpayServiceApi;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ZhuCeActivity extends Activity {
    static final int REGISTER_SUCCESS = 200;
    static final int REGISTER_FAILED = 201;
    ProgressDialog mProgressDialog;
    EditText meditTextRegUserPwd,meditTextRegUsername;
    
    Button  mbuttonZhuce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        
        
        meditTextRegUserPwd = (EditText) findViewById(R.id.editTextRegUserPwd);
        meditTextRegUsername = (EditText) findViewById(R.id.editTextRegUsername);
        
        mbuttonZhuce = (Button) findViewById(R.id.buttonZhuce);
        mbuttonZhuce.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                tryToRegister();
            }
        });
        
    }

    void tryToRegister() {
        
        final String username = meditTextRegUsername.getText().toString();
        if(username ==null || username.length() == 0) {
            showToastText("请输入用户名");return;
        }
        final String userpwd = meditTextRegUserPwd.getText().toString();
        if(userpwd ==null || userpwd.length() == 0) {
            showToastText("请输入密码");return;
            }
        mProgressDialog = Utils.createProgressDialog(this, "正在注册中");
        mProgressDialog.show();
        
        
        new Thread() {
            public void run() {
                try {
                    String datestr = NetworkUtils.getRegisterResultForHttpGet("duleren","123456");
                    if("success".equals(datestr)) {
                        mHandler.sendEmptyMessageDelayed(REGISTER_SUCCESS, 200);
                    } else {
                        mHandler.sendMessage(mHandler.obtainMessage(REGISTER_FAILED, datestr));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }.start();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if(mProgressDialog!=null) mProgressDialog.dismiss();
            switch (msg.what) {
            case REGISTER_SUCCESS: {
                showToastText("注册成功");
//                Intent intent = new Intent();                
//                intent.setClass(ZhuCeActivity.this, LoginActivity.class);                
//                startActivity(intent);
//                finish();
            }
                break;
            case REGISTER_FAILED:
                showToastText((String)msg.obj);
                break;
//            case 1:
//                Intent intent = new Intent();                
//                intent.setClass(ZhuCeActivity.this, MainActivity.class);                
//                startActivity(intent);
//                finish();
//                break;
            };

        };

    };
    
    void showToastText(String text) {
        Toast.makeText(ZhuCeActivity.this, text, Toast.LENGTH_LONG).show();
    }
}
