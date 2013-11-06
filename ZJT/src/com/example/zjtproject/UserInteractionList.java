package com.example.zjtproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.zjtproject.data.UserInteractionListItemData;
import com.example.zjtproject.data.ViolationInfo;
import com.example.zjtproject.jsonparser.InteractionJsonParser;
import com.example.zjtproject.jsonparser.JsonParser;
import com.example.zjtproject.network.DES;
import com.example.zjtproject.network.InteractionApi;
import com.example.zjtproject.network.SuijiShu;
import com.example.zjtproject.network.ViolationsApi;
import com.example.zjtproject.utils.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

public class UserInteractionList extends Activity {
    protected static final String TAG = "UserInteractionList";
    TextView mUser_msg_publish, mUser_msg_back;
    ListView mUserCommunicationList = null;
    static public List<UserInteractionListItemData> mUserInteractionListItemData;
    UserInteractionListAdapter mUserInteractionListAdapter;

    enum FocusToptextview {
        FocusUserNicheng, FocusTopQiuzhu, FocusTopFenxiang
    }
    FocusToptextview mFocusToptextview = FocusToptextview.FocusUserNicheng;
    void setFocusTopmTextView(FocusToptextview focus) {
        mFocusToptextview = focus;
        switch (focus) {
        case FocusUserNicheng:
            mlinearLayoutTopQiuzhuFenxiang.setVisibility(View.GONE);
            mtextViewUserNicheng.setVisibility(View.VISIBLE);
            break;
        case FocusTopQiuzhu:

            mlinearLayoutTopQiuzhuFenxiang.setVisibility(View.VISIBLE);
            mtextViewUserNicheng.setVisibility(View.GONE);
            mtextViewTopQiuzhu
                    .setBackgroundResource(R.drawable.title_mid_select);
            mtextViewTopFenxiang
                    .setBackgroundResource(R.drawable.title_mid_unselect);
            break;
        case FocusTopFenxiang:

            mlinearLayoutTopQiuzhuFenxiang.setVisibility(View.VISIBLE);
            mtextViewUserNicheng.setVisibility(View.GONE);
            mtextViewTopFenxiang
                    .setBackgroundResource(R.drawable.title_mid_select);
            mtextViewTopQiuzhu
                    .setBackgroundResource(R.drawable.title_mid_unselect);

            break;
        }
    }

    TextView mtextViewUserNicheng, mtextViewTopQiuzhu, mtextViewTopFenxiang;
    LinearLayout mlinearLayoutTopQiuzhuFenxiang;

    void initTopTextview() {

        mlinearLayoutTopQiuzhuFenxiang = (LinearLayout) findViewById(R.id.linearLayoutTopQiuzhuFenxiang);
        mtextViewTopQiuzhu = (TextView) findViewById(R.id.textViewTopQiuzhu);
        mtextViewTopQiuzhu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setFocusTopmTextView(FocusToptextview.FocusTopQiuzhu);
            }
        });

        mtextViewTopFenxiang = (TextView) findViewById(R.id.textViewTopFenxiang);
        mtextViewTopFenxiang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setFocusTopmTextView(FocusToptextview.FocusTopFenxiang);
            }
        });

        mtextViewUserNicheng = (TextView) findViewById(R.id.textViewUserNicheng);
        mtextViewUserNicheng.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setFocusTopmTextView(FocusToptextview.FocusUserNicheng);
            }
        });
    }

    //
    TextView mtextviewBottomQiuzhu, mtextviewBottomFenxiang,
            mtextviewBottomWode, mtextviewBottomShoucang;

    enum FocusBottomTextView {
        FocusQiuzhu, FocusFenxiang, FocusWode, FocusShoucang
    }
    FocusBottomTextView mFocusBottomTextView = FocusBottomTextView.FocusQiuzhu;
    void setFocusBottomTextView(FocusBottomTextView focus) {
        mFocusBottomTextView = focus;
        switch (focus) {
        case FocusQiuzhu:
            mtextviewBottomQiuzhu
                    .setBackgroundResource(R.drawable.button_small_green);
            mtextviewBottomFenxiang
                    .setBackgroundResource(R.drawable.button_small_gray);
            mtextviewBottomWode
                    .setBackgroundResource(R.drawable.button_small_gray);
            mtextviewBottomShoucang
                    .setBackgroundResource(R.drawable.button_small_gray);
            mtextViewUserNicheng.setText("车友求助");
            break;
        case FocusFenxiang:
            mtextviewBottomFenxiang
                    .setBackgroundResource(R.drawable.button_small_green);
            mtextviewBottomQiuzhu
                    .setBackgroundResource(R.drawable.button_small_gray);
            mtextviewBottomWode
                    .setBackgroundResource(R.drawable.button_small_gray);
            mtextviewBottomShoucang
                    .setBackgroundResource(R.drawable.button_small_gray);
            mtextViewUserNicheng.setText("车友分享");
            break;
        case FocusWode:
            mtextviewBottomWode
                    .setBackgroundResource(R.drawable.button_small_green);
            mtextviewBottomQiuzhu
                    .setBackgroundResource(R.drawable.button_small_gray);
            mtextviewBottomFenxiang
                    .setBackgroundResource(R.drawable.button_small_gray);
            mtextviewBottomShoucang
                    .setBackgroundResource(R.drawable.button_small_gray);
            break;
        case FocusShoucang:
            mtextviewBottomShoucang
                    .setBackgroundResource(R.drawable.button_small_green);
            mtextviewBottomQiuzhu
                    .setBackgroundResource(R.drawable.button_small_gray);
            mtextviewBottomFenxiang
                    .setBackgroundResource(R.drawable.button_small_gray);
            mtextviewBottomWode
                    .setBackgroundResource(R.drawable.button_small_gray);
            break;
        }
    }

    void initBottomTextView() {
        mtextviewBottomFenxiang = (TextView) findViewById(R.id.textviewBottomFenxiang);
        mtextviewBottomFenxiang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setFocusBottomTextView(FocusBottomTextView.FocusFenxiang);
                setFocusTopmTextView(FocusToptextview.FocusUserNicheng);
                getInteractionList(1, "0");
            }
        });

        mtextviewBottomWode = (TextView) findViewById(R.id.textviewBottomWode);
        mtextviewBottomWode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setFocusBottomTextView(FocusBottomTextView.FocusWode);
                setFocusTopmTextView(FocusToptextview.FocusTopQiuzhu);
            }
        });

        mtextviewBottomShoucang = (TextView) findViewById(R.id.textviewBottomShoucang);
        mtextviewBottomShoucang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setFocusBottomTextView(FocusBottomTextView.FocusShoucang);
                setFocusTopmTextView(FocusToptextview.FocusTopQiuzhu);
            }
        });

        mtextviewBottomQiuzhu = (TextView) findViewById(R.id.textviewBottomQiuzhu);
        mtextviewBottomQiuzhu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setFocusBottomTextView(FocusBottomTextView.FocusQiuzhu);
                setFocusTopmTextView(FocusToptextview.FocusUserNicheng);
                getInteractionList(1, "1");
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_communication_list);
        mUser_msg_publish = (TextView) findViewById(R.id.User_msg_publish);
        mUser_msg_publish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),
                        UserSetInteractiontActivity.class);
                startActivity(intent);
            }
        });

        // ����
        mUser_msg_back = (TextView) findViewById(R.id.User_msg_back);
        mUser_msg_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        initTopTextview();
        initBottomTextView();
        mUserCommunicationList = (ListView) findViewById(R.id.ListViewUserCommunicationList);
        mUserInteractionListItemData = new ArrayList<UserInteractionListItemData>();
        curPage = 1;
        getInteractionList(1, "1");
        curPage++;
        // UserInteractionListItemData data1 = new
        // UserInteractionListItemData();
        // data1.mNickName = "1111";
        // data1.mimg = "4";
        // data1.mCreateTime = "2014-14-14";
        // data1.mContent = "333333333311111111111111aaa";
        // mUserInteractionListItemData.add(data1);

        mUserInteractionListAdapter = new UserInteractionListAdapter(this,
                mUserInteractionListItemData);
        mUserCommunicationList.setAdapter(mUserInteractionListAdapter);
        // mUserCommunicationList.addFooterView(v);
        // mUserCommunicationList.addHeaderView(v);
        mUserInteractionListAdapter.notifyDataSetChanged();

        setlistviewItemclick();

    }

    int lastItem, firstItem, curPage, count;

    void setlistviewItemclick() {

        // 添加点击
        mUserCommunicationList
                .setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {

                        setTitle("点击第" + position + "个项目");
                        Utils.Logi(TAG, "点击第" + position + "个项目");

                        Intent intent = new Intent();
                        intent.putExtra("position", position);
                        intent.setClass(UserInteractionList.this,
                                UserInteractionCommentList.class);
                        intent.putExtra("ID",
                                mUserInteractionListItemData.get(position).mID);
                        startActivity(intent);
                    }
                });
        mUserCommunicationList.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {
                // DebugUtil.debug("firstVisibleItem=" + firstVisibleItem);
                // DebugUtil.debug("visibleItemCount=" + visibleItemCount);
                // DebugUtil.debug("totalItemCount=" + totalItemCount);
                // 这里要减二，因为我加了header footer
                 lastItem = firstVisibleItem + visibleItemCount;
                 firstItem = firstVisibleItem;
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // DebugUtil.debug("onScrollStateChanged");
                // 当滚动停止且滚动的总数等于数据的总数，去加载
                if (lastItem == count && scrollState == SCROLL_STATE_IDLE) {

                    // 加载数据
                    // myHandler.sendEmptyMessage(LOAD);
                    getPageData() ;
                    return;
                }
                // 当往上拉时更新数据，将data清空然后去重新加载
                if (firstItem == 0 && scrollState == SCROLL_STATE_IDLE) {
                    curPage = 1;
                    getPageData() ;
                }
            }

        });

    }

    void getPageData() {

        switch (mFocusBottomTextView) {
        case FocusQiuzhu:
//            getInteractionList(curPage, "1");
            break;
        case FocusFenxiang:
//            getInteractionList(curPage, "0");
            break;
        case FocusWode:
//            getInteractionList(curPage, "0");
            break;
        case  FocusShoucang:
//            getInteractionList(curPage, "0");
            break;
        }
        curPage++;
    }
    /*
     * @param index 分页当前页数从1开始
     * 
     * @param type 0表示互动，1表示求助
     */
    void getInteractionList(final int index, final String type) {
        mProgressDialog = Utils.createProgressDialog(UserInteractionList.this);
        mProgressDialog.show();
        new Thread() {
            public void run() {

                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("Cookie",
                        MainActivity.mloginCookie));
                list.add(new BasicNameValuePair("Index", Integer
                        .toString(index)));
                try {
                    list.add(new BasicNameValuePair("Type", type));

                    String str = InteractionApi.InteractionList(list);

                    JSONObject jsonObject = new JSONObject(str.toString());
                    String state = jsonObject.getString("Success");
                    Utils.Logi(TAG, "ViolationInfoParser Success=" + state);
                    if (state.equals("true")) {
                        JSONArray jsonArray = jsonObject
                                .getJSONArray("ResponseData");
                        if (jsonArray.length() > 0)
                            mUserInteractionListItemData = InteractionJsonParser
                                    .InteractionListParser(jsonArray);
                        handler.sendEmptyMessage(GET_InteractionList_SUCCESS);
                    } else {
                        String msg = jsonObject.getString("ErrorInfo");
                        Utils.Logi(TAG, "ViolationInfoParser msg=" + msg);
                        handler.sendEmptyMessage(GET_InteractionList_Failed);
                    }

                    // {"Success":true,
                    // "ErrorInfo":null,
                    // "ResponseData":[
                    // {"ID":17,"UserID":600000055,"NickName":"13681175201",
                    // "ContentType":0,"Content":"www","Top":0,"Setp":0,"Count":0,
                    // "CreateTime":"2013-06-25T15:22:03.903","img":null,"imgType":null,
                    // "IsAnonymous":0,"MemberFace":null},
                    // {"ID":15,"UserID":600000012,"NickName":null,"ContentType":0,"Content":"为什么1111",
                    // "Top":0,"Setp":0,"Count":0,"CreateTime":"2013-04-24T00:18:07.73","img":null,"imgType":"jpg",
                    // "IsAnonymous":0,"MemberFace":null},
                    // {"ID":14,"UserID":600000012,"NickName":null,"ContentType":0,"Content":"为什么1111",
                    // "Top":0,"Setp":0,"Count":0,"CreateTime":"2013-04-24T00:16:47.457","img":null,"imgType":null,
                    // "IsAnonymous":0,"MemberFace":null},
                    // {"ID":13,"UserID":600080012,"NickName":null,"ContentType":0,"Content":"为什么1111","Top":0,
                    // "Setp":0,"Count":0,"CreateTime":"2013-04-24T00:15:10.123","img":null,"imgType":null,
                    // "IsAnonymous":0,"MemberFace":null},
                    // /{"ID":12,"UserID":600070012,"NickName":null,"ContentType":0,"Content":"为什么1111","Top":0,"Setp":0,"Count":0,"CreateTime":"2013-04-24T00:08:42.3","img":null,"imgType":null,"IsAnonymous":0,"MemberFace":null},
                    // {"ID":5,"UserID":600001012,"NickName":null,"ContentType":0,"Content":"123213","Top":0,"Setp":0,"Count":0,"CreateTime":"2013-04-23T00:44:23.52","img":null,"imgType":null,"IsAnonymous":0,"MemberFace":null},
                    // {"ID":7,"UserID":600030012,"NickName":null,"ContentType":0,"Content":"为什么1111","Top":0,"Setp":0,"Count":0,"CreateTime":"2013-04-23T00:44:23.52","img":null,"imgType":null,"IsAnonymous":0,"MemberFace":null},
                    // {"ID":8,"UserID":600040012,"NickName":null,"ContentType":0,"Content":"为什么1111","Top":1,"Setp":0,"Count":0,"CreateTime":"2013-04-23T00:44:23.52","img":null,"imgType":null,"IsAnonymous":0,"MemberFace":null},
                    // {"ID":9,"UserID":600050012,"NickName":null,"ContentType":0,"Content":"为什么1111","Top":1,"Setp":0,"Count":0,"CreateTime":"2013-04-23T00:44:23.52","img":null,"imgType":null,"IsAnonymous":0,"MemberFace":null},
                    // {"ID":11,"UserID":60006012,"NickName":null,"ContentType":0,"Content":"为什么1111","Top":1,"Setp":0,"Count":0,"CreateTime":"2013-04-23T00:44:23.52","img":null,"imgType":null,"IsAnonymous":0,"MemberFace":null}
                    // ]
                    // }

                    // ViolationInfo violationInfo
                    // =JsonParser.ViolationInfoParser(str);
                    // mAllViolationInfo.add(violationInfo);

               
                } catch (Exception e) {
                    e.printStackTrace();
                     handler.sendEmptyMessage(ERROR);
                }

                // handler.sendEmptyMessage(3);
            }
        }.start();
    }

    final static int GET_InteractionList_SUCCESS = 1;
    final static int GET_InteractionList_Failed = 2;
    final static int ERROR = 9999;
    
    ProgressDialog mProgressDialog = null;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mProgressDialog != null)
                mProgressDialog.dismiss();
            switch (msg.what) {
            case GET_InteractionList_SUCCESS:
                mUserInteractionListAdapter
                        .setData(mUserInteractionListItemData);
                count = mUserInteractionListItemData.size();
                mUserCommunicationList.setAdapter(mUserInteractionListAdapter);

                mUserInteractionListAdapter.notifyDataSetChanged();

                break;
            }
        }
    };
}
