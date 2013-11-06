package org.linphone.zhizhiui;

import com.zhizhi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CallEndActivity extends Activity {
    TextView textViewCallTimeLong;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callend);
        textViewCallTimeLong = (TextView)findViewById(R.id.textViewCallTimeLong);


        String calltime = getIntent().getStringExtra("calltime");
        textViewCallTimeLong.setText("本次通话时长："+calltime);
        // 编辑资料
        Button QueDing = (Button)findViewById(R.id.buttonQueDing);
        QueDing.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Intent intent = new Intent();
                // intent.setClass(TixianActivity.this, ChongzhiActivity.class);
                // startActivity(intent);
                finish();
            }
        });
    }
}
