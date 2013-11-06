package com.example.zjtproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.zjtproject.AsyncImageLoader.ImageCallback;
import com.example.zjtproject.data.UserInteractionListItemData;
import com.example.zjtproject.jsonparser.InteractionJsonParser;
import com.example.zjtproject.network.InteractionApi;
import com.example.zjtproject.utils.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

class UserInteractionListAdapter extends BaseAdapter {

    public final class ViewHolder {
        public ImageView mimageViewUserHeader;
        public ImageView mimageViewMessageImage;
        public ImageView mimageViewCollect;

        public TextView mtextViewUserName;
        public TextView mtextViewUserMsgDate;
        public TextView mtextViewUserMessage;
        public TextView mtextViewSmileCount;
        public TextView mtextViewAngryCount;
        public TextView mtextViewReplyCount;

        public LinearLayout mLinearLayoutsmiling_face;
        public LinearLayout mLinearLayoutangry_face;
        public LinearLayout mLinearLayoutcomment;

    }

    private LayoutInflater mInflater;
    private List<UserInteractionListItemData> mData;
    private AsyncImageLoader imageLoader = new AsyncImageLoader();
    Context mContext;

    public void setData(List<UserInteractionListItemData> Data) {
        mData = Data;
    }

    public UserInteractionListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public UserInteractionListAdapter(Context context,
            List<UserInteractionListItemData> data) {
        this.mInflater = LayoutInflater.from(context);
        mData = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(final int arg0, View convertView, ViewGroup arg2) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.activity_user_communication_listitem, null);

            holder.mimageViewUserHeader = (ImageView) convertView
                    .findViewById(R.id.imageViewUserHeader);
            holder.mimageViewMessageImage = (ImageView) convertView
                    .findViewById(R.id.imageViewMessageImage);
            holder.mimageViewCollect = (ImageView) convertView
                    .findViewById(R.id.imageViewCollect);
            
            holder.mtextViewUserName = (TextView) convertView
                    .findViewById(R.id.textViewUserName);
            holder.mtextViewUserMsgDate = (TextView) convertView
                    .findViewById(R.id.textViewUserMsgDate);
            holder.mtextViewUserMessage = (TextView) convertView
                    .findViewById(R.id.textViewUserMessage);
            holder.mtextViewSmileCount = (TextView) convertView
                    .findViewById(R.id.textViewSmileCount);
            holder.mtextViewAngryCount = (TextView) convertView
                    .findViewById(R.id.textViewAngryCount);
            holder.mtextViewReplyCount = (TextView) convertView
                    .findViewById(R.id.textViewReplyCount);

            holder.mLinearLayoutsmiling_face = (LinearLayout) convertView
                    .findViewById(R.id.LinearLayoutsmiling_face);
            holder.mLinearLayoutangry_face = (LinearLayout) convertView
                    .findViewById(R.id.LinearLayoutangry_face);
            holder.mLinearLayoutcomment = (LinearLayout) convertView
                    .findViewById(R.id.LinearLayoutcomment);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // TODO
        holder.mtextViewUserName.setText(mData.get(arg0).mNickName);
        holder.mtextViewUserMsgDate.setText(mData.get(arg0).mCreateTime);
        holder.mtextViewUserMessage.setText(mData.get(arg0).mContent);

        holder.mtextViewSmileCount.setText(mData.get(arg0).mTop + "");
        holder.mtextViewAngryCount.setText(mData.get(arg0).mSetp + "");
        holder.mtextViewReplyCount.setText(mData.get(arg0).mCount + "");
        if (mData.get(arg0).mimg == null || mData.get(arg0).mimg == "null") {
            holder.mimageViewMessageImage.setVisibility(View.GONE);
        } else {
            holder.mimageViewMessageImage.setVisibility(View.VISIBLE);
        }

        holder.mtextViewUserName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext,
                        UserMsgList.class);
                mContext.startActivity(intent);
            }
        });
        
        holder.mLinearLayoutsmiling_face
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SetInteractionAction("0", mData.get(arg0).mID);
                    }
                });
        holder.mLinearLayoutangry_face
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SetInteractionAction("1", mData.get(arg0).mID);
                    }
                });
        holder.mLinearLayoutcomment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        
        holder.mimageViewCollect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SetInteractiontCollect(mData.get(arg0).mID);
            }
        });

        // final ImageView imageViewMessageImage =
        // holder.mimageViewMessageImage;
        // imageLoader.loadDrawable(mData.get(arg0).mUserHeaderUri,
        // new ImageCallback() {
        //
        // public void imageLoaded(Drawable imageDrawable,
        // String imageUrl) {
        // imageViewMessageImage.setImageDrawable(imageDrawable);
        // }
        // });
        //
        // final ImageView imageViewUserHeader = holder.mimageViewUserHeader;
        // imageLoader.loadDrawable(mData.get(arg0).mUserHeaderUri,
        // new ImageCallback() {
        //
        // public void imageLoaded(Drawable imageDrawable,
        // String imageUrl) {
        // imageViewUserHeader.setImageDrawable(imageDrawable);
        // }
        // });

        return convertView;
    }

    // Type Byte 数据类型：0表示顶，1表示踩
    void SetInteractiontCollect(final String ID) {
        new Thread() {
            public void run() {

                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("Cookie",
                        MainActivity.mloginCookie));
                try {
                    list.add(new BasicNameValuePair("ID", ID));

                    String str = InteractionApi.SetInteractiontCollect(list);

                    JSONObject jsonObject = new JSONObject(str.toString());
                    String state = jsonObject.getString("Success");

                    if (state.equals("true")) {
                        handler.sendEmptyMessage(CollectSUCCESS);
                    } else {
                        // {"Success":false,
                        // "ErrorInfo":
                        // {"ErrorCode":31002,"ErrorText":"不能重复顶"},
                        // "ResponseData":false}
                        JSONObject jsonObject2 = jsonObject
                                .getJSONObject("ErrorInfo");
                        String ErrorCode = jsonObject2.getString("ErrorCode");
                        String ErrorText = jsonObject2.getString("ErrorText");
                        Message msg = new Message();
                        msg.what = CollectFailed;
                        msg.obj = ErrorText;
                        handler.sendMessage(msg);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                     handler.sendEmptyMessage(CollectFailed);
                }

                // handler.sendEmptyMessage(3);
            }
        }.start();
    }
    
    // Type Byte 数据类型：0表示顶，1表示踩
    void SetInteractionAction(final String Type, final String ID) {
        new Thread() {
            public void run() {

                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("Cookie",
                        MainActivity.mloginCookie));
                list.add(new BasicNameValuePair("Type", Type));
                try {
                    list.add(new BasicNameValuePair("ID", ID));

                    String str = InteractionApi.SetInteractionAction(list);

                    JSONObject jsonObject = new JSONObject(str.toString());
                    String state = jsonObject.getString("Success");

                    if (state.equals("true")) {
                        handler.sendEmptyMessage(SetInteractionActionSUCCESS);
                    } else {
                        // {"Success":false,
                        // "ErrorInfo":
                        // {"ErrorCode":31002,"ErrorText":"不能重复顶"},
                        // "ResponseData":false}
                        JSONObject jsonObject2 = jsonObject
                                .getJSONObject("ErrorInfo");
                        String ErrorCode = jsonObject2.getString("ErrorCode");
                        String ErrorText = jsonObject2.getString("ErrorText");
                        Message msg = new Message();
                        msg.what = SetInteractionActionFailed;
                
                        msg.obj = ErrorText;
                        handler.sendMessage(msg);
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

    final static int SetInteractionActionSUCCESS = 1;
    final static int SetInteractionActionFailed = 2;
    final static int CollectSUCCESS = 3;
    final static int CollectFailed = 4;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
            case SetInteractionActionSUCCESS:
                Toast.makeText(mContext, "发送成功", Toast.LENGTH_LONG).show();

                break;
            case SetInteractionActionFailed:
                Toast.makeText(mContext, (String) msg.obj, Toast.LENGTH_LONG)
                        .show();

                break;

            case CollectSUCCESS:
                Toast.makeText(mContext, "收藏成功", Toast.LENGTH_LONG).show();

                break;
            case CollectFailed:
                Toast.makeText(mContext, (String) msg.obj, Toast.LENGTH_LONG)
                        .show();

                break;
            
            }
        }
    };
}
