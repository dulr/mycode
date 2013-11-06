package org.linphone.zhizhiui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.zhizhi.R;
import org.linphone.zhizhiui.data.MainViewListData;
import org.linphone.zhizhiui.data.UploadFileUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

class MainViewListItemAdapter extends BaseAdapter {

	public final class ViewHolder {
		String mPersonImageDrawablePath;
		public int mMid;
		public ImageView mPersonImage;
		public ImageView mStateImage;
		public ImageView mdengjiImage;
		public TextView mtextViewPersonName;
		public TextView mtextViewPersonInfo;
		public TextView mtextViewPersonPrice;
		// public Button viewBtn;
	}

	
	private LayoutInflater mInflater;

	private List<MainViewListData> mData;

	// private List<Map<String, Object>> mData;

	public MainViewListItemAdapter(Context context) {
		this.mInflater = LayoutInflater.from(context);
	}

	public MainViewListItemAdapter(Context context, List<MainViewListData> Data) {
		this.mInflater = LayoutInflater.from(context);
		mData = Data;
	}
	public void setData( List<MainViewListData> Data) {
		mData = Data;
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
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.mainviewlistitem, null);
			holder.mPersonImage = (ImageView) convertView
					.findViewById(R.id.personImage);
			holder.mStateImage = (ImageView) convertView
					.findViewById(R.id.stateImage);
			holder.mdengjiImage = (ImageView) convertView
					.findViewById(R.id.dengjiImage);
			holder.mtextViewPersonName = (TextView) convertView
					.findViewById(R.id.textViewPersonName);
			holder.mtextViewPersonInfo = (TextView) convertView
					.findViewById(R.id.textViewPersonInfo);
			holder.mtextViewPersonPrice = (TextView) convertView
					.findViewById(R.id.textViewPersonPrice);
			convertView.setTag(holder);
		
		

		try {
			 holder.mPersonImageDrawablePath=(mData.get(position).mPersonImageDrawablePath);
			 holder.mMid=(mData.get(position).mMid);
			File file = new File(UploadFileUtils.filename, holder.mMid+".zz");
			if (file.exists()) {
				FileInputStream stream = new FileInputStream(file);
				Bitmap bitmap = BitmapFactory.decodeStream(stream);
				holder.mPersonImage.setImageBitmap(bitmap);
				stream.close();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
				   
		// holder.mPersonImage
		// .setBackgroundDrawable(mData.get(position).mPersonImageDrawable);
		if(mData.get(position).mStateImageId.equals("Y")){
			holder.mStateImage.setBackgroundResource(MainViewActivity.personState[0]);
		} else {
			holder.mStateImage.setBackgroundResource(MainViewActivity.personState[1]);
		}
		// holder.mdengjiImage
		// .setBackgroundResource(mData.get(position).mdengjiImageId);
			holder.mtextViewPersonName
					.setText((String) mData.get(position).mPersonName);
			holder.mtextViewPersonInfo
					.setText(mData.get(position).mPersonInfo != null
							&& mData.get(position).mPersonInfo != "null" ? mData
							.get(position).mPersonInfo : "");
			if(mData.get(position).mPersonPrice != null)
			holder.mtextViewPersonPrice
					.setText((String) mData.get(position).mPersonPrice + "元/分钟");
			else
            holder.mtextViewPersonPrice.setText("");
		// holder.viewBtn.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// }
		// });
		} else {
			holder = (ViewHolder) convertView.getTag();
			try {
				 holder.mPersonImageDrawablePath=(mData.get(position).mPersonImageDrawablePath);
				 holder.mMid=(mData.get(position).mMid);
				File file = new File(UploadFileUtils.filename, holder.mMid+".zz");
				if (file.exists()) {
					FileInputStream stream = new FileInputStream(file);
					Bitmap bitmap = BitmapFactory.decodeStream(stream);
					holder.mPersonImage.setImageBitmap(bitmap);
					stream.close();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
					   
			// holder.mPersonImage
			// .setBackgroundDrawable(mData.get(position).mPersonImageDrawable);
			if(mData.get(position).mStateImageId.equals("Y")){
				holder.mStateImage.setBackgroundResource(MainViewActivity.personState[0]);
			} else {
				holder.mStateImage.setBackgroundResource(MainViewActivity.personState[1]);
			}
			// holder.mdengjiImage
			// .setBackgroundResource(mData.get(position).mdengjiImageId);
				holder.mtextViewPersonName
						.setText((String) mData.get(position).mPersonName);
				holder.mtextViewPersonInfo
						.setText(mData.get(position).mPersonInfo != null
								&& mData.get(position).mPersonInfo != "null" ? mData
								.get(position).mPersonInfo : "");
				if(mData.get(position).mPersonPrice != null)
				holder.mtextViewPersonPrice
						.setText((String) mData.get(position).mPersonPrice + "元/分钟");
				else
                holder.mtextViewPersonPrice.setText("");
		}
		return convertView;
	}
}
