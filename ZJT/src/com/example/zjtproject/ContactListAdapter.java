package com.example.zjtproject;

import java.util.List;


import com.example.zjtproject.AsyncImageLoader.ImageCallback;
import com.example.zjtproject.data.ContactListItemData;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ContactListAdapter extends BaseAdapter {

    public final class ViewHolder {
        public ImageView mimageViewUserHeader;
        public TextView mtextViewUserName;
        public TextView mtextViewUserLastMessage;
        public TextView mtextViewUserMsgDate;
    }

    private LayoutInflater mInflater;
    private List<ContactListItemData> mData;
    
    private AsyncImageLoader imageLoader = new AsyncImageLoader();
    
    public void setData( List<ContactListItemData> Data) {
        mData = Data;
    }
    public ContactListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public ContactListAdapter(Context context, List<ContactListItemData> data) {
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
            convertView = mInflater.inflate(
                    R.layout.activity_user_listitem, null);
            holder.mimageViewUserHeader = (ImageView) convertView
                    .findViewById(R.id.imageViewUserHeader);
            holder.mtextViewUserName = (TextView) convertView
                    .findViewById(R.id.textViewUserName);
            holder.mtextViewUserLastMessage = (TextView) convertView
                    .findViewById(R.id.textViewUserLastMessage);
            holder.mtextViewUserMsgDate = (TextView) convertView
                    .findViewById(R.id.textViewUserMsgDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       
        // TODO
//        holder.mimageViewUserHeader
        holder.mtextViewUserName.setText(mData.get(arg0).mUserName);
        holder.mtextViewUserLastMessage.setText(mData.get(arg0).mLastMsg);
        holder.mtextViewUserMsgDate.setText(mData.get(arg0).mLastMsgDate);
        
        final ImageView imageViewUserHeader = holder.mimageViewUserHeader;
        imageLoader.loadDrawable(mData.get(arg0).mUserHeaderUri, new ImageCallback() {

            public void imageLoaded(Drawable imageDrawable, String imageUrl) {
                imageViewUserHeader.setImageDrawable(imageDrawable);
            }
        });

        return convertView;
    }
}
