package com.example.zjtproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.zjtproject.data.UserMsgListItemData;
import com.example.zjtproject.network.MessageApi;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;

public class UserMsgList extends Activity 
{
	TextView mUser_msg_delete,mUser_msg_back;
    ListView mUserMsgList = null;
    public List<UserMsgListItemData> mUserMsgListItemData;
    UserMsgListAdapter mUserMsgListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_msg_list);
        mUser_msg_delete  = (TextView) findViewById(R.id.User_msg_delete);
        mUser_msg_delete.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
			}
        });
        
        //����
        mUser_msg_back  = (TextView) findViewById(R.id.User_msg_back);
        mUser_msg_back.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				finish();
			}
        });
        mUserMsgList = (ListView) findViewById(R.id.ListViewUserMsgList);
        mUserMsgListItemData = new ArrayList<UserMsgListItemData>();

        UserMsgListItemData data1 = new UserMsgListItemData();
        data1.mUserName = "1111";
        data1.mUserHeaderUri = "4";
        data1.mCurrentMsg = "333333333311111111111111aaa";        
        mUserMsgListItemData.add(data1);
        
        UserMsgListItemData data2 = new UserMsgListItemData();
        data2.mUserName = "1111";
        data2.mUserHeaderUri = "4";
        data2.mCurrentMsg = "3333333333";
        mUserMsgListItemData.add(data2);
        UserMsgListItemData data3 = new UserMsgListItemData();
        data3.mUserName = "1111";
        data3.mUserHeaderUri = "4";
        data3.mIsSendMsg = true;
        data3.mCurrentMsg = "3333333333";
        mUserMsgListItemData.add(data3);
       
        UserMsgListItemData data4 = new UserMsgListItemData();
        data4.mUserName = "1111";
        data4.mUserHeaderUri = "4";
        data4.mCurrentMsg = "3333333333";
        mUserMsgListItemData.add(data4);
        UserMsgListItemData data5 = new UserMsgListItemData();
        data5.mUserName = "1111";
        data5.mUserHeaderUri = "4";
        data5.mCurrentMsg = "3333333333";
        mUserMsgListItemData.add(data5);
        UserMsgListItemData data6 = new UserMsgListItemData();
        data6.mUserName = "1111";
        data6.mUserHeaderUri = "4";
        data6.mCurrentMsg = "3333333333";
        mUserMsgListItemData.add(data6);
        
        UserMsgListItemData data7 = new UserMsgListItemData();
        data7.mUserName = "1111";
        data7.mUserHeaderUri = "4";
        data7.mCurrentMsg = "3333333333";
        mUserMsgListItemData.add(data7);
        UserMsgListItemData data8 = new UserMsgListItemData();
        data8.mUserName = "1111aaaaaaaa";
        data8.mUserHeaderUri = "4";
        data8.mCurrentMsg = "3333333333ssssssss";
        data8.mIsSendMsg = true;
        data8.mIsSending = true;
        mUserMsgListItemData.add(data8);
        // mUserMsgListAdapter.setData(mUserMsgListItemData);
        // mUserMsgListAdapter.notifyDataSetChanged();

        mUserMsgListAdapter = new UserMsgListAdapter(this, mUserMsgListItemData);
        mUserMsgList.setAdapter(mUserMsgListAdapter);

        mUserMsgListAdapter.notifyDataSetChanged();
        
        initMessageButton();
    }
    
    
    Button mbuttonMessagesend;
    EditText meditTextMessageContent;

    void initMessageButton() {
        meditTextMessageContent = (EditText) findViewById(R.id.editTextMessageContent);
        mbuttonMessagesend = (Button) findViewById(R.id.buttonMessagesend);
        mbuttonMessagesend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final String content = meditTextMessageContent.getText().toString();
                if(content != null) {
                    new Thread() {
                        public void run() {
                           
                            List<NameValuePair> list = new ArrayList<NameValuePair>();

                            list.add(new BasicNameValuePair("Content", content));
                            list.add(new BasicNameValuePair("Nickname", "1"));
                            try {
                                list.add(new BasicNameValuePair("Cookie",
                                        MainActivity.mloginCookie));
//                                CommonApi.SendShortMessage(list);
                                MessageApi.Send(list);
//                                handler.sendEmptyMessage(2);
                            } catch (Exception e) {
                                e.printStackTrace();
//                                handler.sendEmptyMessage(3);
                            }
                        
                        }
                    }.start();
                }
            }
        });
    }
}
