package com.example.zjtproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.example.zjtproject.network.InteractionApi;
import com.example.zjtproject.utils.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.app.ProgressDialog;

//发表互动/求助
public class UserSetInteractiontActivity extends Activity
{
	TextView mRegister_login ,mRegister_back;
	FocusToptextview mFocusToptextview = FocusToptextview.FocusTopQiuzhu;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_publish_info);
        
        mRegister_login  = (TextView) findViewById(R.id.User_publish_finish);
        mRegister_login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
//				Intent intent = new Intent();                
//				intent.setClass(UserPublishInfoActivity.this, LoginActivity.class);                
//				startActivity(intent);
			    String content = meditTextContent.getText().toString();
			    if(content == null) return;
			    switch (mFocusToptextview) {
		        case FocusTopQiuzhu:
		            SetQiuzhuInteractiont(content);
		            break;
		        case FocusTopFenxiang:
		            SetFenxiangInteractiont(content);
		            break;
		            }
			    
			}
        	
        });
        mRegister_back  = (TextView) findViewById(R.id.User_publish_back);
        mRegister_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
        	
        });
        initTopTextview();
    }
    
    enum FocusToptextview {
    	FocusTopQiuzhu,
    	FocusTopFenxiang
    }
    
    void SetQiuzhuInteractiont(final String Content) {
        SetInteractiont(Content, "1");
    }

    void SetFenxiangInteractiont(final String Content) {
        SetInteractiont(Content, "0");
    }
    /*
     * @param Content 发表的内容,最大长度500
     * 
     * @param type 0表示互动，1表示求助
     */
    void SetInteractiont(final String Content,final String type) {
        mProgressDialog = Utils.createProgressDialog(UserSetInteractiontActivity.this );
        mProgressDialog.show();
        new Thread() {
            public void run() {
               
                List<NameValuePair> list = new ArrayList<NameValuePair>();    
                list.add(new BasicNameValuePair("Cookie", MainActivity.mloginCookie));
                list.add(new BasicNameValuePair("Content", Content));
                try {
                    list.add(new BasicNameValuePair(
                            "Type",type));

                    String str = InteractionApi.SetInteractiont(list);
//                    ViolationInfo  violationInfo =JsonParser.ViolationInfoParser(str);
//                    mAllViolationInfo.add(violationInfo);

                    
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
//                    handler.sendEmptyMessage(2);
                } catch (Exception e){
                    e.printStackTrace();
//                    handler.sendEmptyMessage(2);
                }
                
                
                handler.sendEmptyMessage(3);
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
            }
        }
    };
    
    
    TextView  mtextViewTopQiuzhu,mtextViewTopFenxiang;
    EditText meditTextContent;
	void initTopTextview() {
	    meditTextContent  = (EditText) findViewById(R.id.editTextContent);
	    
		mtextViewTopQiuzhu  = (TextView) findViewById(R.id.textViewTopQiuzhu);
		mtextViewTopQiuzhu.setOnClickListener(new OnClickListener()
         {
 			@Override
 			public void onClick(View v)
 			{
 				setFocusTopmTextView(FocusToptextview.FocusTopQiuzhu);
 			}
         });
		
		mtextViewTopFenxiang  = (TextView) findViewById(R.id.textViewTopFenxiang);
		mtextViewTopFenxiang.setOnClickListener(new OnClickListener()
         {
 			@Override
 			public void onClick(View v)
 			{
 				setFocusTopmTextView(FocusToptextview.FocusTopFenxiang);
 			}
         });
		
		
		
	}
    
    void setFocusTopmTextView(FocusToptextview focus) {
		switch (focus) {
		case FocusTopQiuzhu:
			mtextViewTopQiuzhu.setBackgroundResource(R.drawable.title_mid_select);
			mtextViewTopFenxiang.setBackgroundResource(R.drawable.title_mid_unselect);
			mFocusToptextview = FocusToptextview.FocusTopQiuzhu;
			break;
		case FocusTopFenxiang:
			mtextViewTopFenxiang.setBackgroundResource(R.drawable.title_mid_select);
			mtextViewTopQiuzhu.setBackgroundResource(R.drawable.title_mid_unselect);
			mFocusToptextview = FocusToptextview.FocusTopFenxiang;
			break;
		}
	}
}
