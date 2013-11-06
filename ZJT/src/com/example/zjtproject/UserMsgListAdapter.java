package com.example.zjtproject;

import java.util.List;

import com.example.zjtproject.AsyncImageLoader.ImageCallback;
import com.example.zjtproject.data.UserMsgListItemData;
import com.example.zjtproject.utils.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

class UserMsgListAdapter extends BaseAdapter {

    public final class ViewHolder {
        public ImageView mimageViewUserMsgHeader;
        public ImageView mtextViewUserMsgphoto;
        public TextView mtextViewUserMsgContent;
        // public TextView mtextViewUserLastMessage;
        public ProgressBar mprogressBarSending;
    }

    private LayoutInflater mInflater;
    private List<UserMsgListItemData> mData;
    private AsyncImageLoader imageLoader = new AsyncImageLoader();

    public void setData(List<UserMsgListItemData> Data) {
        mData = Data;
    }

    public UserMsgListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public UserMsgListAdapter(Context context, List<UserMsgListItemData> data) {
        this.mInflater = LayoutInflater.from(context);
        mData = data;
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
    public View getView(int arg0, View convertView, ViewGroup arg2) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater
                    .inflate(
                            mData.get(arg0).mIsSendMsg ? R.layout.activity_user_msg_send_listitem
                                    : R.layout.activity_user_msg_receive_listitem,
                            null);
            holder.mimageViewUserMsgHeader = (ImageView) convertView
                    .findViewById(R.id.imageViewUserMsgHeader);
            holder.mtextViewUserMsgContent = (TextView) convertView
                    .findViewById(R.id.textViewUserMsgContent);
            holder.mtextViewUserMsgphoto = (ImageView) convertView
                    .findViewById(R.id.textViewUserMsgphoto);
            holder.mprogressBarSending = (ProgressBar) convertView
                    .findViewById(R.id.progressBarSending);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // TODO
        // holder.mimageViewUserMsgHeader
        holder.mtextViewUserMsgContent.setText(mData.get(arg0).mCurrentMsg);
        // holder.mtextViewUserLastMessage.setText(mData.get(arg0).mLastMsg);
        // holder.mtextViewUserMsgDate.setText(mData.get(arg0).mLastMsgDate);

//        if (mData.get(arg0).mIsSendMsg)
            
        if (mData.get(arg0).mIsSendMsg && mData.get(arg0).mIsSending) {
            Utils.Logi("UserMsgListAdapter", "mData arg0 = " + arg0);
            convertView.findViewById(R.id.progressBarSending).setVisibility(
                    View.VISIBLE);
        } else {
            convertView.findViewById(R.id.progressBarSending).setVisibility(
                    View.GONE);
        }
           

        final ImageView imageViewUserHeader = holder.mimageViewUserMsgHeader;
        imageLoader.loadDrawable(mData.get(arg0).mUserHeaderUri,
                new ImageCallback() {

                    public void imageLoaded(Drawable imageDrawable,
                            String imageUrl) {
                        imageViewUserHeader.setImageDrawable(imageDrawable);
                    }
                });
        return convertView;
    }
}
