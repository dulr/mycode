package com.tfb.tfb168;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewImageAdapter extends BaseAdapter {
    // 定义Context
    private Context mContext;
    // 定义整型数组 即图片源
    private Integer[] mImageIds = { 
            R.drawable.home_item_receipt_money,
            R.drawable.home_item_balance_query, 
            R.drawable.home_item_trade_mgr, 
            R.drawable.home_item_credit_card, 
            R.drawable.home_item_phone_charge,
            R.drawable.home_item_phone_caip, 
            R.drawable.home_item_user_center, 
            R.drawable.home_item_more_info, 
//            R.drawable.home_item_more_info, 
            };

    public GridViewImageAdapter(Context c) {
        mContext = c;
    }

    // 获取图片的个数
    public int getCount() {
        return mImageIds.length;
    }

    // 获取图片在库中的位置
    public Object getItem(int position) {
        return position;
    }

    // 获取图片ID
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // 给ImageView设置资源
            imageView = new ImageView(mContext);
            // 设置布局 图片120×120显示
            imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            // 设置显示比例类型
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mImageIds[position]);
        return imageView;
    }

}
