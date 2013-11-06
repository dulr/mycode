package com.example.zjtproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.zjtproject.jsonparser.InteractionJsonParser;
import com.example.zjtproject.network.InteractionApi;
import com.example.zjtproject.utils.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.app.ProgressDialog;

// 互动评论
public class UserSetInteractiontCommentActivity extends Activity
{
	TextView mUser_comments_finish, mRegister_back;
	String mID;
	EditText meditTextCommentscontent;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_comments_info);
        mID = getIntent().getStringExtra("ID");
        
        meditTextCommentscontent  = (EditText) findViewById(R.id.editTextCommentscontent);
		mUser_comments_finish  = (TextView) findViewById(R.id.User_comments_finish);
        mUser_comments_finish.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent();                
//				intent.setClass(UserPublishInfoActivity.this, LoginActivity.class);                
//				startActivity(intent);
			    SetInteractiontComment();
			}
        	
        });
        mRegister_back  = (TextView) findViewById(R.id.User_comments_back);
        mRegister_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
    }
    
    

    void SetInteractiontComment() {
        if(meditTextCommentscontent.getText().toString() == null) return;
        mProgressDialog = Utils.createProgressDialog(UserSetInteractiontCommentActivity.this);
        mProgressDialog.show();
        new Thread() {
            public void run() {

                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("Cookie",
                        MainActivity.mloginCookie));
                list.add(new BasicNameValuePair("ID",mID));
                try {
                    list.add(new BasicNameValuePair("Content", meditTextCommentscontent.getText().toString()));

                    String str = InteractionApi.SetInteractiontComment(list);

                    JSONObject jsonObject = new JSONObject(str.toString());
                    String state = jsonObject.getString("Success");
                    if (state.equals("true")) {
                       
                        handler.sendEmptyMessage(GET_InteractionList_SUCCESS);
                    } else {
                        String msg = jsonObject.getString("ErrorInfo");
                        handler.sendEmptyMessage(GET_InteractionList_Failed);
                    }


                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                    // handler.sendEmptyMessage(2);
                } catch (Exception e) {
                    e.printStackTrace();
                    // handler.sendEmptyMessage(2);
                }

                // handler.sendEmptyMessage(3);
            }
        }.start();
    }

    final static int GET_InteractionList_SUCCESS = 1;
    final static int GET_InteractionList_Failed = 2;

    ProgressDialog mProgressDialog = null;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mProgressDialog != null)
                mProgressDialog.dismiss();
            switch (msg.what) {
            case GET_InteractionList_SUCCESS:
              finish();

                break;
            }
        }
    };
}
