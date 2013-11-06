package com.example.zjtproject;

import com.example.zjtproject.data.ViolationItemData;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViolationsListDetailItem extends LinearLayout {
    private Context               mContext                     = null;
    public ViolationsListDetailItem(Context context, AttributeSet attrs,
            int defStyle) {
       
        super(context, attrs, defStyle);
        mContext = context;

    }

    public ViolationsListDetailItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }

    public ViolationsListDetailItem(Context context) {
        super(context);
        mContext = context;
    }

    public ViolationsListDetailItem(Context context, ViolationItemData a) {
        super(context);
        mContext = context;
        data=a;
//        setOrientation(HORIZONTAL);  

//      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(  
//
//       LinearLayout.LayoutParams.FILL_PARENT,  
//
//         LinearLayout.LayoutParams.WRAP_CONTENT);  
//
//       setLayoutParams(params);  

 
        LayoutInflater.from(context).inflate(R.layout.activity_peccancydetail_listitem, this, true);

           ((TextView) findViewById(R.id.textViewViolationsDate)).setText("时间："+data.mViolationsDate);
        ((TextView) findViewById(R.id.textViewViolationsAddress)).setText("地点："+data.mViolationsAddress);
        
        ((TextView) findViewById(R.id.textViewViolationsID)).setText("代码："+data.mViolationsID+data.mViolationsType);
        ((TextView) findViewById(R.id.textViewViolationsPoint)).setText(data.mViolationsPoint);
        ((TextView) findViewById(R.id.textViewViolationsMoney)).setText(data.mViolationsMoney);
        ((TextView) findViewById(R.id.textViewViolationsDispose)).setText(data.mViolationsDispose);
        ((TextView) findViewById(R.id.textViewViolationsCharge)).setText(data.mViolationsCharge);
//          mtextViewViolationsAddress;
//          mtextViewViolationsID;
//          mtextViewViolationsPoint;
//          mtextViewViolationsMoney;
//          mtextViewViolationsDispose;
//          mtextViewViolationsCharge;
    }
    
    ViolationItemData data;
//    TextView mtextViewViolationsDate;
//    TextView mtextViewViolationsAddress;
//    TextView mtextViewViolationsID;
//    TextView mtextViewViolationsPoint;
//    TextView mtextViewViolationsMoney;
//    TextView mtextViewViolationsDispose;
//    TextView mtextViewViolationsCharge;
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        mtextViewViolationsDate.setText("时间："+data.mViolationsDate);
//        ((TextView) findViewById(R.id.textViewViolationsAddress)).setText("地点："+data.mViolationsAddress);
//        
//        ((TextView) findViewById(R.id.textViewViolationsID)).setText("代码："+data.mViolationsID+data.mViolationsType);
//        ((TextView) findViewById(R.id.textViewViolationsPoint)).setText("地点："+data.mViolationsPoint);
//        ((TextView) findViewById(R.id.textViewViolationsMoney)).setText("地点："+data.mViolationsMoney);
//        ((TextView) findViewById(R.id.textViewViolationsDispose)).setText("地点："+data.mViolationsDispose);
//        ((TextView) findViewById(R.id.textViewViolationsCharge)).setText("地点："+data.mViolationsCharge);
    }
}
