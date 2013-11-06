package com.zhihui.zhihuicity.ctrl;

import java.util.Collections;
import java.util.List;

import com.zhihui.zhihuicity.R;
import com.zhihui.zhihuicity.data.InfoCatBean;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.TextView;

public class InfoCatListAdapter extends BaseAdapter {
    private List<InfoCatBean> catList;
    private boolean isNoCat;
    private Context mContext;
    private int mSelectedTab=1000;

    public InfoCatListAdapter(Context paramContext,
            List<InfoCatBean> paramList, boolean paramBoolean) {
        this.isNoCat = paramBoolean;
        this.mContext = paramContext;
        // paramContext.obtainStyledAttributes(R.styleable.Gallery).recycle();
        if (paramList == null)
            paramList = Collections.emptyList();
        this.catList = paramList;
    }

    @Override
    public int getCount() {
        return catList.size()*1000;
    }

    @Override
    public Object getItem(int position) {
        return catList.get(position % this.catList.size());
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
        TextView localTextView = new TextView(this.mContext);
        if (paramView == null) {

            InfoCatBean localInfoCatBean = (InfoCatBean) this.catList
                    .get(paramInt % this.catList.size());
            localTextView.setTextColor(Color.WHITE);
            localTextView.setTextSize(18.0F);
            localTextView.setText(localInfoCatBean.name);
            localTextView.setLayoutParams(new Gallery.LayoutParams(-2, -1));
            localTextView.setGravity(17);
            localTextView.setPadding(15, 0, 15, 0);
            localTextView.setTag(localInfoCatBean);

        } else {
            localTextView = (TextView) paramView;
        }
        if (paramInt == this.mSelectedTab)
        localTextView.setBackgroundColor(Color.rgb(14, 41, 67));
        return localTextView;
    }

}
