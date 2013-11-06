package com.example.zjtproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.example.zjtproject.data.ViolationsListData;
import com.example.zjtproject.data.ContactListItemData;
import com.example.zjtproject.data.ViolationItemData;
import com.example.zjtproject.network.InteractionApi;
import com.example.zjtproject.network.MessageApi;
import com.example.zjtproject.utils.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

public class ContactList extends Activity {

    TextView mUserList_back;
    ListView mContactList = null;
    public List<ContactListItemData> mContactListItemData;
    ContactListAdapter mContactListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        // ����
        mUserList_back = (TextView) findViewById(R.id.UserList_back);
        mUserList_back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }

        });

        mContactList = (ListView) findViewById(R.id.ListViewUserList);
        mContactListItemData = new ArrayList<ContactListItemData>();

        ContactListItemData data = new ContactListItemData();
        data.mUserName = "�û��ǳ�";
        data.mLastMsg = "�ⲻ�ǳ�����";
        data.mLastMsgDate = "2013��5��14";
        mContactListItemData.add(data);
        data.mUserName = "�û��ǳ�";
        data.mLastMsg = "�ǳ���";
        data.mLastMsgDate = "2013��5��14";
        mContactListItemData.add(data);
        data.mUserName = "�û��ǳ�";
        data.mLastMsg = "����";
        data.mLastMsgDate = "2013��5��14";
        mContactListItemData.add(data);
        data.mUserName = "�û��ǳ�";
        data.mLastMsg = "��ð�";
        data.mLastMsgDate = "2013��5��14";
        mContactListItemData.add(data);
        data.mUserName = "�û��ǳ�";
        data.mLastMsg = "С���";
        data.mLastMsgDate = "2013��5��14";
        mContactListItemData.add(data);
        data.mUserName = "�û��ǳ�";
        data.mLastMsg = "��";
        data.mLastMsgDate = "2013��5��14";
        mContactListItemData.add(data);
        data.mUserName = "�û��ǳ�";
        data.mLastMsg = "��";
        data.mLastMsgDate = "2013��5��14";
        mContactListItemData.add(data);
        data.mUserName = "1111";
        data.mLastMsg = "�߲��߰�";
        data.mLastMsgDate = "2013��5��14";
        mContactListItemData.add(data);
        // mPeccancyListAdapter.setData(mUserListItemData);
        // mPeccancyListAdapter.notifyDataSetChanged();

        mContactListAdapter = new ContactListAdapter(this, mContactListItemData);
        mContactList.setAdapter(mContactListAdapter);

        mContactListAdapter.notifyDataSetChanged();
        getContactList(0);
        setItemclick();
    }

    /*
     * @param LastID 上一页最后编号：如果是第一页则为0
     * 
     */
    void getContactList(final int LastID) {
        mProgressDialog = Utils.createProgressDialog(ContactList.this);
        mProgressDialog.show();
        new Thread() {
            public void run() {

                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("Cookie",
                        MainActivity.mloginCookie));
                list.add(new BasicNameValuePair("LastID", Integer
                        .toString(LastID)));
                try {

                    String str = MessageApi.ContactList(list);
                    // ViolationInfo violationInfo
                    // =JsonParser.ViolationInfoParser(str);
                    // mAllViolationInfo.add(violationInfo);

                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                    // handler.sendEmptyMessage(2);
                } catch (Exception e) {
                    e.printStackTrace();
                    // handler.sendEmptyMessage(2);
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
    
    
    
    void setItemclick() {

        // 添加点击
        mContactList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
              Intent intent = new Intent();                
              intent.setClass(ContactList.this, UserMsgList.class);                
              startActivity(intent);
            };
        });
    }
}
