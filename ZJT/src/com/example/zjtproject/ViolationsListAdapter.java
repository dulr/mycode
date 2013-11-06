package com.example.zjtproject;

import java.util.List;


import com.example.zjtproject.data.ViolationsListData;
import com.example.zjtproject.data.ZJTDatabaseHelper;
import com.example.zjtproject.utils.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ViolationsListAdapter extends BaseAdapter {

    public final class ViewHolder {
        public ImageView mimageViewDelete;
        public ImageView mimageViewEdit;
        public TextView mtextViewCarNum;
        public TextView mtextViewWeizhangCounts;
        public TextView mtextViewWeizhangRiqi;
    }

    private LayoutInflater mInflater;
    private Context mContext;
    private List<ViolationsListData> mData;
    public void setData( List<ViolationsListData> Data) {
        mData = Data;
    }
    public ViolationsListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public ViolationsListAdapter(Context context, List<ViolationsListData> data) {
        this.mInflater = LayoutInflater.from(context);
        mContext = context;
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
    public View getView(final int arg0, View convertView, ViewGroup arg2) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.activity_peccancy_listitem, null);
            holder.mimageViewDelete = (ImageView) convertView
                    .findViewById(R.id.imageViewDelete);
            holder.mimageViewEdit = (ImageView) convertView
                    .findViewById(R.id.imageViewEdit);
            holder.mtextViewCarNum = (TextView) convertView
                    .findViewById(R.id.textViewCarNum);
            holder.mtextViewWeizhangCounts = (TextView) convertView
                    .findViewById(R.id.textViewWeizhangCounts);
            holder.mtextViewWeizhangRiqi = (TextView) convertView
                    .findViewById(R.id.textViewWeizhangRiqi);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mimageViewDelete.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                createDeleteCarInfoDialog(mData.get(arg0).mCarNum, arg0).show();
            }
            
        });
        holder.mimageViewEdit.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                createEditCarInfoDialog(mData.get(arg0).mCarNum,"").show();
            }
            
        });
//        holder.mimageViewDelete
        holder.mtextViewCarNum.setText(mData.get(arg0).mCarNum);
        holder.mtextViewWeizhangCounts.setText(Integer.toString(mData.get(arg0).mWeizhangCounts));
        holder.mtextViewWeizhangRiqi.setText(mData.get(arg0).mWeizhangRiqi);
        return convertView;
    }
    
    AddCarInfoDialog createEditCarInfoDialog (final String ChepaiHao,final String FadongjiHao) {

        final AddCarInfoDialog.Builder customBuilder = new
                AddCarInfoDialog.Builder(mContext);
            customBuilder.setTitle("Custom title")
                .setMessage("Custom body")
                .setNegativeButton("取消", 
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
               
                .setPositiveButton("确定", 
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       String CarRegion =customBuilder.mbuttonChePaiDi.getText().toString();
                        
                       String CarNo= customBuilder.medittextChepaiHao.getText().toString();
                       String EngineNo =customBuilder.meditTextFadongjiHao.getText().toString();
                               
                        ZJTDatabaseHelper.updateData(
                                mContext,  CarNo, EngineNo);
//                        getViolationsQuery(CarNo,
//                                EngineNo);
                        notifyDataSetChanged();
                        
                        dialog.dismiss();
//                        mProgressDialog = Utils.createProgressDialog(PeccancyList.this);
//                        mProgressDialog.show();
                    }
                }) .setCarInfo(ChepaiHao, FadongjiHao)
                ;
            AddCarInfoDialog dialog = customBuilder.create();
         return    dialog;
    }
    
    DeleteCarInfoDialog createDeleteCarInfoDialog(final String title, final int pos) {

        final DeleteCarInfoDialog.Builder customBuilder = new
                DeleteCarInfoDialog.Builder(mContext);
            customBuilder.setTitle("你确定要要删除 "+title+"的车辆信息吗？")
//                .setMessage("Custom body")
                .setNegativeButton("取消", 
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
               
                .setPositiveButton("删除", 
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                     
                        ZJTDatabaseHelper.deleteData(
                                mContext,  mData.get(pos).mCarNum);
//                        getViolationsQuery(CarNo,
//                                EngineNo);
                        mData.remove(pos);
                        notifyDataSetChanged();
                        dialog.dismiss();
//                        mProgressDialog = Utils.createProgressDialog(PeccancyList.this);
//                        mProgressDialog.show();
                    }
                })
                ;
            return customBuilder.create();
     
    }
}
