package com.example.zjtproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;


import com.example.zjtproject.data.UserInteractionListItemData;
import com.example.zjtproject.data.ViolationsListData;
import com.example.zjtproject.data.UserInteractionCommentListItemData;
import com.example.zjtproject.jsonparser.InteractionJsonParser;
import com.example.zjtproject.network.InteractionApi;
import com.example.zjtproject.utils.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

public class UserInteractionCommentList extends Activity
{
	
	TextView mUserList_back,mUser_msg_comments;
    ListView mUserList = null;
    public List<UserInteractionCommentListItemData> mUserInteractionCommentsListItemData;
    UserInteractionCommentListAdapter mInteractionCommentListAdapter;

    String mID;int position;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_communication_comments_list);
        mID = getIntent().getStringExtra("ID");
        position = getIntent().getIntExtra("position",0);
        //����
        mUserList_back  = (TextView) findViewById(R.id.UserList_back);
        mUserList_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
        mUser_msg_comments  = (TextView) findViewById(R.id.User_msg_comments);
        mUser_msg_comments.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), 
                        UserSetInteractiontCommentActivity.class);
                intent.putExtra("ID", mID);
                startActivity(intent);
            }
            
        });
        
        mUserList = (ListView) findViewById(R.id.ListViewUserList);
        mUserInteractionCommentsListItemData = new ArrayList<UserInteractionCommentListItemData>();

//        UserInteractionCommentListItemData data = new UserInteractionCommentListItemData();
//        data.mNickName ="�û��ǳ�";
//        data.mComment = "�ⲻ�ǳ�����";
//        data.mCreateTime = "2013��5��14";
//        mUserInteractionCommentsListItemData.add(data);
//        data.mUserName ="�û��ǳ�";
//        data.mLastMsg = "�ǳ���";
//        data.mLastMsgDate = "2013��5��14";
//        mUserInteractionCommentsListItemData.add(data);
//        data.mUserName ="�û��ǳ�";
//        data.mLastMsg = "����";
//        data.mLastMsgDate = "2013��5��14";
//        mUserInteractionCommentsListItemData.add(data);
//        data.mUserName ="�û��ǳ�";
//        data.mLastMsg = "��ð�";
//        data.mLastMsgDate = "2013��5��14";
//        mUserInteractionCommentsListItemData.add(data);
//        data.mUserName ="�û��ǳ�";
//        data.mLastMsg = "С���";
//        data.mLastMsgDate = "2013��5��14";
//        mUserInteractionCommentsListItemData.add(data);
//        data.mUserName ="�û��ǳ�";
//        data.mLastMsg = "��";
//        data.mLastMsgDate = "2013��5��14";
//        mUserInteractionCommentsListItemData.add(data);
//        data.mUserName ="�û��ǳ�";
//        data.mLastMsg = "��";
//        data.mLastMsgDate = "2013��5��14";
//        mUserInteractionCommentsListItemData.add(data);
//        data.mUserName ="1111";
//        data.mLastMsg = "�߲��߰�";
//        data.mLastMsgDate = "2013��5��14";
//        mUserInteractionCommentsListItemData.add(data);
//        mPeccancyListAdapter.setData(mUserCommunicationCommentsListItemData);
//        mPeccancyListAdapter.notifyDataSetChanged();
        
        addHeaderView();
        
        
        mInteractionCommentListAdapter = new UserInteractionCommentListAdapter(this,mUserInteractionCommentsListItemData);
        mUserList.setAdapter(mInteractionCommentListAdapter);

        mInteractionCommentListAdapter.notifyDataSetChanged();
        
    }

    void addHeaderView() {
        View headerview = getLayoutInflater().inflate(
                R.layout.activity_user_communication_listitem, null);
        UserInteractionListItemData mData = UserInteractionList.mUserInteractionListItemData
                .get(position);

        // headerview
        // ( (ImageView) headerview
        // .findViewById(R.id.imageViewUserHeader));
        ;

        ((TextView) headerview.findViewById(R.id.textViewUserName))
                .setText(mData.mNickName);
        ((TextView) headerview.findViewById(R.id.textViewUserMsgDate))
                .setText(mData.mCreateTime);
        ((TextView) headerview.findViewById(R.id.textViewUserMessage))
                .setText(mData.mContent);
        ((TextView) headerview.findViewById(R.id.textViewSmileCount))
                .setText(mData.mTop + "");
        ((TextView) headerview.findViewById(R.id.textViewAngryCount))
                .setText(mData.mSetp + "");
        ((TextView) headerview.findViewById(R.id.textViewReplyCount))
                .setText(mData.mCount + "");

        if (mData.mimg == null || mData.mimg == "null") {
            ((ImageView) headerview.findViewById(R.id.imageViewMessageImage))
                    .setVisibility(View.GONE);
        } else {
            ((ImageView) headerview.findViewById(R.id.imageViewMessageImage))
                    .setVisibility(View.VISIBLE);
        }
        
        mUserList.addHeaderView(headerview);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        getInteractionCommentsList(1,mID);
    } 
    
    /*
     * @param index 分页当前页数从1开始
     * 
     * @param type 互动ID
     */
    void getInteractionCommentsList(final int index, final String ID) {
        mProgressDialog = Utils.createProgressDialog(UserInteractionCommentList.this);
        mProgressDialog.show();
        new Thread() {
            public void run() {

                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("Cookie",
                        MainActivity.mloginCookie));
                list.add(new BasicNameValuePair("Index", Integer
                        .toString(index)));
                try {
                    list.add(new BasicNameValuePair("ID", ID));

                    String str = InteractionApi.InteractionCommentList(list);

                    JSONObject jsonObject = new JSONObject(str.toString());
                    String state = jsonObject.getString("Success");
                    Utils.Logi(TAG, "ViolationInfoParser Success=" + state);
                    //{"Success":true,"ErrorInfo":null,
                    //"ResponseData":
                    //[
                    // {"ID":2,"UserID":600000057,"NickName":"12333","ParentID":24,"Comment":"亘古不变加精","CreateTime":"2013-06-26T10:36:04.983"},
                    // {"ID":1,"UserID":600000057,"NickName":"12333","ParentID":24,"Comment":"呵呵哈哈哈","CreateTime":"2013-06-26T10:32:53.397"}]}

                    if (state.equals("true")) {
                        JSONArray jsonArray = jsonObject
                                .getJSONArray("ResponseData");
                        mUserInteractionCommentsListItemData = InteractionJsonParser
                                .InteractionCommentListParser(jsonArray);
                        handler.sendEmptyMessage(GET_InteractionCommentsList_SUCCESS);
                    } else {
                        String msg = jsonObject.getString("ErrorInfo");
//                        Utils.Logi(TAG, "ViolationInfoParser msg=" + msg);
                        handler.sendEmptyMessage(GET_InteractionCommentsList_Failed);
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
    protected static final String TAG = "InteractionList";
    final static int GET_InteractionCommentsList_SUCCESS = 1;
    final static int GET_InteractionCommentsList_Failed = 2;

    ProgressDialog mProgressDialog = null;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mProgressDialog != null)
                mProgressDialog.dismiss();
            switch (msg.what) {
            case GET_InteractionCommentsList_SUCCESS:
                mInteractionCommentListAdapter
                        .setData(mUserInteractionCommentsListItemData);
                mUserList.setAdapter(mInteractionCommentListAdapter);

                mInteractionCommentListAdapter.notifyDataSetChanged();

                break;
            }
        }
    };
}
