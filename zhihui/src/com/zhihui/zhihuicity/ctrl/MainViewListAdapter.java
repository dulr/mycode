package com.zhihui.zhihuicity.ctrl;

import java.util.Collections;
import java.util.List;

import com.zhihui.zhihuicity.R;
import com.zhihui.zhihuicity.data.MainViewListItemData;
import com.zhihui.zhihuicity.utils.AsyncImageLoader;
import com.zhihui.zhihuicity.utils.AsyncImageLoader.ImageCallback;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainViewListAdapter extends BaseAdapter {
    private List<MainViewListItemData> mMainViewListData;
    private boolean isNoCat;
    private Context mContext;
    private int mSelectedTab = 1000;
    private LayoutInflater mInflater;
    private AsyncImageLoader imageLoader = new AsyncImageLoader();

    public MainViewListAdapter(Context paramContext,
            List<MainViewListItemData> paramList, boolean paramBoolean) {
        this.isNoCat = paramBoolean;
        this.mContext = paramContext;
        this.mInflater = LayoutInflater.from(paramContext);
        // paramContext.obtainStyledAttributes(R.styleable.Gallery).recycle();
        if (paramList == null)
            paramList = Collections.emptyList();
        this.mMainViewListData = paramList;
    }

    @Override
    public int getCount() {
        return mMainViewListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mMainViewListData.get(position % this.mMainViewListData.size());
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 110;
    }

    public int getSelectedTab() {
        return this.mSelectedTab;
    }

    public void setSelectedTab(int paramInt) {
        if (paramInt != this.mSelectedTab) {
            this.mSelectedTab = paramInt;
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {

        ViewHolder holder = null;
        if (paramView == null) {

            holder = new ViewHolder();
            paramView = mInflater.inflate(R.layout.mainview_list_item, null);
            holder.mimageView_item_image = (ImageView) paramView
                    .findViewById(R.id.imageView_item_image);
            holder.mtextView_title = (TextView) paramView
                    .findViewById(R.id.textView_title);
            holder.mtextView_content = (TextView) paramView
                    .findViewById(R.id.textView_content);
            paramView.setTag(holder);

        } else {
            holder = (ViewHolder) paramView.getTag();
        }
        holder.mtextView_title.setText(mMainViewListData.get(paramInt).mTitle);
        holder.mtextView_content
                .setText(mMainViewListData.get(paramInt).mContent);

        final ImageView imageViewUserHeader = holder.mimageView_item_image;
        imageLoader.loadDrawable(mMainViewListData.get(paramInt).mImageUri,
                new ImageCallback() {

                    public void imageLoaded(Drawable imageDrawable,
                            String imageUrl) {
                        imageViewUserHeader.setImageDrawable(imageDrawable);
                    }
                });
        return paramView;
    }

    public final class ViewHolder {

        public ImageView mimageView_item_image;
        // public ImageView mStateImage;
        // public ImageView mdengjiImage;
        public TextView mtextView_title;
        public TextView mtextView_content;
        // public TextView mtextViewPersonPrice;
        // public Button viewBtn;
    }

}
