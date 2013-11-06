package com.example.zjtproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.zjtproject.network.CommonApi;
import com.example.zjtproject.network.DES;
import com.example.zjtproject.network.MessageApi;
import com.example.zjtproject.network.NetworkUtils;
import com.example.zjtproject.network.SuijiShu;
import com.example.zjtproject.network.UserApi;
import com.example.zjtproject.utils.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

public class LoginActivity extends Activity {
    TextView mLogin_register, mLogin_back;
    Button mzhaohui, mlogin;
    EditText meditTextzhanghao, meditTextpassword;

    public void initEditText() {
        meditTextzhanghao = (EditText) findViewById(R.id.editTextzhanghao);
        meditTextpassword = (EditText) findViewById(R.id.editTextpassword);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initEditText();

        mLogin_register = (TextView) findViewById(R.id.Login_register);
        mLogin_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });

        mLogin_back = (TextView) findViewById(R.id.Login_back);
        mLogin_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        mzhaohui = (Button) findViewById(R.id.zhaohui);
        mzhaohui.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                tryToGetPwd();
            }
        });
        mlogin = (Button) findViewById(R.id.login);
        mlogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                tryToLogin();
            }
        });
    }

    void tryToGetPwd() {
        mProgressDialog = Utils.createProgressDialog(LoginActivity.this,
                "正在登陆中");
        mProgressDialog.show();
        new Thread() {
            public void run() {
                String moboile = meditTextzhanghao.getText().toString();
                if (moboile == null)
                    return;// 加提示语
                List<NameValuePair> list = new ArrayList<NameValuePair>();

                list.add(new BasicNameValuePair("mobile", moboile));
                list.add(new BasicNameValuePair("content", "1"));
                try {
                    list.add(new BasicNameValuePair(
                            "token",
                            DES.encryptDES(
                                    SuijiShu.getToken(MainActivity.mTimediffwithserver),
                                    DES.KEY)));
                    CommonApi.SendShortMessage(list);
                    handler.sendEmptyMessage(2);
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(3);
                }
            
            }
        }.start();
    }
    void tryToLogin() {
        mProgressDialog = Utils.createProgressDialog(LoginActivity.this,
                "正在登陆中");
        mProgressDialog.show();
        new Thread() {
            public void run() {
                String zhanghao = meditTextzhanghao.getText().toString();
                String password = meditTextpassword.getText().toString();

                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("UID", zhanghao));
                try {
                    list.add(new BasicNameValuePair("PWD", DES.encryptDES(
                            password, DES.KEY)));
               
                    list.add(new BasicNameValuePair(
                            "token",
                            DES.encryptDES(
                                    SuijiShu.getToken(MainActivity.mTimediffwithserver),
                                    DES.KEY)));
                    String result = UserApi.Login(list);
                    // {"Success":true,"ErrorInfo":null,"ResponseData":false}
                    // 12333 111111 13911329715
                    
                    //{"Success":true,
                    //"ErrorInfo":null,
                    //"ResponseData":"Iz7ByeNScX2RD07ZnHHHdlSkV/2TkP5GTLdR/mLgo/4GtNcknRIqDytbiEuPA9JGret08ec0+2MhZAjjPrZ1BgVX/qfbyyNZoh1L9959tRLEje0mpo4bjmEMbILkaY46Mn6bEFujCoXuPlzII3KNjg=="}

                    JSONObject jsonObject = new JSONObject(result.toString());
                    String Success;
                    String ErrorInfo;
                    String responseData;

                    Success = jsonObject.getString("Success");
                    ErrorInfo = jsonObject.getString("ErrorInfo");

                    responseData = jsonObject.getString("ResponseData");

                    Utils.Logi(NetworkUtils.TAG, "Success=" + Success + ErrorInfo
                            + responseData);
                    if(Success.equals("true")) {
                        responseData = jsonObject.getString("ResponseData");
                        MainActivity.mloginCookie = responseData;
//                        String deresponseData = DES.decryptDES(responseData, DES.KEY);

//                        String deresponseData = DES.decrypt(responseData.getBytes(), DES.KEY.getBytes()).toString();
//                        List<NameValuePair> list2 = new ArrayList<NameValuePair>();
//                        list2.add(new BasicNameValuePair("Cookie", responseData));
                        //{"Success":true,"ErrorInfo":null,
                        //"ResponseData":
                        // {"ID":600000057,"Mobile":"13911329715",
                        //  "NickName":"12333","SilverCost":0,
                        //   "GoldCost":0,"FaceData":""}}

//                        UserApi.GetProfile(list2);
                        
//                        list2.add(new BasicNameValuePair("LastID", "0"));
//                        MessageApi.MsgList(list2);
//                        Utils.Logi(NetworkUtils.TAG, "deResponseData=" + deresponseData );
                        handler.sendEmptyMessage(1);
                    }
                    else
                        handler.sendEmptyMessage(4);
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(3);
                }

            }
        }.start();
    }

    ProgressDialog mProgressDialog = null;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mProgressDialog != null)
                mProgressDialog.dismiss();
            switch (msg.what) {
            case 1://登陆成功
                finish();
                break;
            case 2://找回密码短信发送成
                break;
            case 3:// 失败
                break;
            case 4:// 登陆失败
                break;
            }
        }
    };
}
