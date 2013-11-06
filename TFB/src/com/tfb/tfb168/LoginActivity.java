package com.tfb.tfb168;

import com.bbpos.cswiper.ui.CSwiperExampleUI;
import com.tfb.tfb168.network.NetworkUtils;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends Activity {
    Button mzhaohui, mlogin, mZhuce;
    ImageView mimageViewtCheck;
    boolean mIsPwRemembered = false;
    EditText meditTextUserPwd, meditTextUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        meditTextUserPwd = (EditText) findViewById(R.id.editTextUserPwd);
        meditTextUsername = (EditText) findViewById(R.id.editTextUsername);

        mzhaohui = (Button) findViewById(R.id.buttonZhaohui);
        mzhaohui.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, CSwiperExampleUI.class);
                startActivity(intent);
                finish();
            }
        });
        mZhuce = (Button) findViewById(R.id.buttonZhuce);
        mZhuce.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, ZhuCeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mlogin = (Button) findViewById(R.id.buttonLogin);
        mlogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                tryToLogin();
            }
        });

    }

    ProgressDialog mProgressDialog;
    static final int LOGIN_SUCCESS = 100;
    static final int LOGIN_FAILED = 101;

    void tryToLogin() {
        final String username = meditTextUsername.getText().toString();
        if (username == null || username.length() == 0) {
            showToastText("请输入用户名");
            return;
        }
        final String userpwd = meditTextUserPwd.getText().toString();
        if (userpwd == null || userpwd.length() == 0) {
            showToastText("请输入密码");
            return;
        }
        mProgressDialog = Utils.createProgressDialog(this, "正在登陆中");
        mProgressDialog.show();
        new Thread() {
            public void run() {
                try {
                    String datestr = NetworkUtils.getLoginResultForHttpGet(
                            username, userpwd);
                    if ("success".equals(datestr)) {
                        mHandler.sendEmptyMessageDelayed(LOGIN_SUCCESS, 200);
                    } else {
                        mHandler.sendMessage(mHandler.obtainMessage(
                                LOGIN_FAILED, datestr));
                        // mHandler.sendEmptyMessageDelayed(LOGIN_FAILED, 200);
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

            if (mProgressDialog != null)
                mProgressDialog.dismiss();
            switch (msg.what) {

            case LOGIN_SUCCESS: {
                showToastText("登陆成功");
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
                break;
            case LOGIN_FAILED:
                showToastText((String) msg.obj);
                break;
            }
            ;

        };

    };

    void showToastText(String text) {
        Toast.makeText(LoginActivity.this, text, Toast.LENGTH_LONG).show();
    }
}
