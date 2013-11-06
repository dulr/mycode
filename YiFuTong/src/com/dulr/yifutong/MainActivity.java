package com.dulr.yifutong;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    EditText meditTextDingDanHao, meditTextJinEr,meditTextBeiZhu;
    Button mbuttonNext, mlogin, mZhuce;
    void initButtons() {
        meditTextDingDanHao = (EditText) findViewById(R.id.editTextDingDanHao);
        meditTextJinEr = (EditText) findViewById(R.id.editTextJinEr);
        meditTextBeiZhu = (EditText) findViewById(R.id.editTextBeiZhu);
        mbuttonNext = (Button) findViewById(R.id.buttonNext);
        mbuttonNext.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
               

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
