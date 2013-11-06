package com.example.zjtproject.jsonparser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.zjtproject.data.ViolationsListData;
import com.example.zjtproject.data.ShopDiscountInfo;
import com.example.zjtproject.data.ShopInfo;
import com.example.zjtproject.data.ViolationInfo;
import com.example.zjtproject.data.ViolationItemData;
import com.example.zjtproject.utils.Utils;

public class JsonParser {
    final static String TAG = "JsonParser";
    //附近店铺列表解析函数
    public static List<ShopInfo> ShopInfoParser(String str)
            throws JSONException {
        if (str == null ) return null;
        List<ShopInfo> mListData = new ArrayList<ShopInfo>();
        JSONObject jsonObject = new JSONObject(str.toString());

        String state = jsonObject.getString("Success");
        Utils.Logi(TAG, "ShopInfoParser Success=" + state);
        String msg = jsonObject.getString("ErrorInfo");
        Utils.Logi(TAG, "ShopInfoParser msg=" + msg);
        JSONArray jsonArray = jsonObject.getJSONArray("ResponseData");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
            ShopInfo data = new ShopInfo();

            data.mID = (jsonObject2.getInt("ID"));
            Utils.Logi(TAG, "data.mID=" + data.mID);
            data.mName = (jsonObject2.getString("Name"));
            Utils.Logi(TAG, "data.mName=" + data.mName);
            data.mX = (jsonObject2.getDouble("X"));
            Utils.Logi(TAG, "data.mX=" + data.mX);
            data.mY = (jsonObject2.getDouble("Y"));
            Utils.Logi(TAG, "data.mY=" + data.mY);
            data.mDistance = (jsonObject2.getDouble("Distance"));
            Utils.Logi(TAG, "data.mDistance=" + data.mDistance);
            
            data.mWashing = (jsonObject2.getInt("Washing"));
            Utils.Logi(TAG, "data.mWashing=" + data.mWashing);
            data.mPasting = (jsonObject2.getInt("Pasting"));
            Utils.Logi(TAG, "data.mPasting=" + data.mPasting);
            data.mCoating = (jsonObject2.getInt("Coating"));
            Utils.Logi(TAG, "data.mCoating=" + data.mCoating);
            data.mWaxing = (jsonObject2.getInt("Waxing"));
            Utils.Logi(TAG, "data.mWaxing=" + data.mWaxing);
            data.mGlazing = (jsonObject2.getInt("Glazing"));
            Utils.Logi(TAG, "data.mGlazing=" + data.mGlazing);
            mListData.add(data);
        }

        return mListData;
    }
    
    //违章列表解析函数
   // {"Success":true,"ErrorInfo":null,"ResponseData":
        //{"CarInfo":
        //{"CarNumber":"京P3FM52",
        //"CarType":"小型汽车",
        //"CarColor":"白",
        //"CarExamineValidity":"2013-12-31",
        //"CarStatus":"违法未处理"},
        
        //"Violations":
        //[{"ViolationsID":"1344","ViolationsDate":"2013-01-26 17:13",
        //"ViolationsAddress":"河北省承德市兴隆县兴隆镇大街",
        //"ViolationsType":"违反禁令标志指示的","ViolationsDispose":"未处理",
        //"ViolationsCharge":"未缴款","ViolationsPoint":"0","ViolationsMoney":
        //"0"},
        //{"ViolationsID":"1344","ViolationsDate":"2013-01-26 17:06",
        //"ViolationsAddress":"河北省承德市兴隆县兴隆镇大街","ViolationsType":
        //"违反禁令标志指示的","ViolationsDispose":"未处理","ViolationsCharge":
        //"未缴款","ViolationsPoint":"0","ViolationsMoney":"0"},
        //{"ViolationsID":"10391","ViolationsDate":"2012-08-08 10:12",
        //"ViolationsAddress":"北京市朝阳区西大望路平乐园至北工大路西口段",
        //"ViolationsType":"不按规定停车","ViolationsDispose":"未处理",
        //"ViolationsCharge":"未缴款","ViolationsPoint":"0","ViolationsMoney"
        //:"0"},
        
        //{"ViolationsID":"1019",
        //"ViolationsDate":"2012-03-31 18:29",
        //"ViolationsAddress":"北京市朝阳区京通快速K3+280由西向东",
        //"ViolationsType":"机动车违规使用专用车道",
        //"ViolationsDispose":"未处理",
        //"ViolationsCharge":"未缴款",
        //"ViolationsPoint":"0",
        //"ViolationsMoney":"150"}
        //]}}
    
    
    public static ViolationInfo ViolationInfoParser(String str)
            throws JSONException {
        if (str == null ) return null;
        ViolationInfo mViolationInfo = new ViolationInfo();
        JSONObject jsonObject = new JSONObject(str.toString());

        String state = jsonObject.getString("Success");
        Utils.Logi(TAG, "ViolationInfoParser Success=" + state);
        String msg = jsonObject.getString("ErrorInfo");
        Utils.Logi(TAG, "ViolationInfoParser msg=" + msg);
        //{"Success":true,"ErrorInfo":null,"ResponseData":{"CarInfo":{"CarNumber":"京P3FM52","CarType":"小型汽车","CarColor":"白","CarExamineValidity":"2013-12-31","CarStatus":"违法未处理"},
        //                                                 "Violations":[{"ViolationsID":"1344","ViolationsDate":"2013-01-26 17:13","ViolationsAddress":"河北省承德市兴隆县兴隆镇大街","ViolationsType":"违反禁令标志指示的","ViolationsDispose":"未处理","ViolationsCharge":"未缴款","ViolationsPoint":"0","ViolationsMoney":"0"},{"ViolationsID":"1344","ViolationsDate":"2013-01-26 17:06","ViolationsAddress":"河北省承德市兴隆县兴隆镇大街","ViolationsType":"违反禁令标志指示的","ViolationsDispose":"未处理","ViolationsCharge":"未缴款","ViolationsPoint":"0","ViolationsMoney":"0"},{"ViolationsID":"10391","ViolationsDate":"2012-08-08 10:12","ViolationsAddress":"北京市朝阳区西大望路平乐园至北工大路西口段","ViolationsType":"不按规定停车","ViolationsDispose":"未处理","ViolationsCharge":"未缴款","ViolationsPoint":"0","ViolationsMoney":"0"},{"ViolationsID":"1019","ViolationsDate":"2012-03-31 18:29","ViolationsAddress":"北京市朝阳区京通快速K3+280由西向东","ViolationsType":"机动车违规使用专用车道","ViolationsDispose":"未处理","ViolationsCharge":"未缴款","ViolationsPoint":"0","ViolationsMoney":"150"}]}}

        JSONObject jsonObjectResponseData = (JSONObject) jsonObject.getJSONObject("ResponseData");
        JSONObject jsonObjectCarInfo = (JSONObject) jsonObjectResponseData.getJSONObject("CarInfo");
        mViolationInfo.mCarNumber = jsonObjectCarInfo.getString("CarNumber");
        mViolationInfo.mCarType = jsonObjectCarInfo.getString("CarType");
        mViolationInfo.mCarColor = jsonObjectCarInfo.getString("CarColor");
        mViolationInfo.mCarExamineValidity = jsonObjectCarInfo.getString("CarExamineValidity");
        mViolationInfo.mCarStatus = jsonObjectCarInfo.getString("CarStatus");
        
        JSONArray jsonArray = jsonObjectResponseData.getJSONArray("Violations");
        if (jsonArray == null || jsonArray.length() == 0) {
            mViolationInfo.mViolationNum = 0;
            return mViolationInfo;
        } else
            mViolationInfo.mViolationNum = jsonArray.length();
        
        mViolationInfo.mViolationListData = new ArrayList<ViolationItemData>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
            ViolationItemData data = new ViolationItemData();
            //{"ViolationsID":"1019",
            //"ViolationsDate":"2012-03-31 18:29",
            //"ViolationsAddress":"北京市朝阳区京通快速K3+280由西向东",
            //"ViolationsType":"机动车违规使用专用车道",
            //"ViolationsDispose":"未处理",
            //"ViolationsCharge":"未缴款",
            //"ViolationsPoint":"0",
            //"ViolationsMoney":"150"}
            data.mViolationsID = (jsonObject2.getString("ViolationsID"));
            Utils.Logi(TAG, "data.mViolationsID=" + data.mViolationsID);
            data.mViolationsDate = (jsonObject2.getString("ViolationsDate"));
            Utils.Logi(TAG, "data.mViolationsDate=" + data.mViolationsDate);
        
            data.mViolationsAddress = (jsonObject2.getString("ViolationsAddress"));
            Utils.Logi(TAG, "data.mViolationsAddress=" + data.mViolationsAddress);
            data.mViolationsType = (jsonObject2.getString("ViolationsType"));
            Utils.Logi(TAG, "data.mViolationsType=" + data.mViolationsType);
            
            data.mViolationsDispose = (jsonObject2.getString("ViolationsDispose"));
            Utils.Logi(TAG, "data.mViolationsDispose=" + data.mViolationsDispose);
            data.mViolationsCharge = (jsonObject2.getString("ViolationsCharge"));
            Utils.Logi(TAG, "data.mViolationsCharge=" + data.mViolationsCharge);
  
            data.mViolationsPoint = (jsonObject2.getString("ViolationsPoint"));
            Utils.Logi(TAG, "data.mViolationsPoint=" + data.mViolationsPoint);
            data.mViolationsMoney = (jsonObject2.getString("ViolationsMoney"));
            Utils.Logi(TAG, "data.mViolationsMoney=" + data.mViolationsMoney);
            
            mViolationInfo.mViolationListData.add(data);
        }

        return mViolationInfo;
    }
    
    // 店铺打折信息
    // {"Success":true,"ErrorInfo":null,
    // "ResponseData":
    // {"ID":122,"Name":"北京阳光汽车技术服务中心",
    // "Address":"北京朝阳区西坝河太阳宫甲134号",
    // "Contact":"64295966",
    // "Price":22.00,"MPV":36.00,"SUV":28.00,
    // "PriceOriginal":30.00,"MPVOriginal":50.00,"SUVOriginal":40.00,
    // "PastingDiscount":null,"CoatingDiscount":null,"WaxingDiscount":null,
    // "GlazingDiscount":null}}
    public static ShopDiscountInfo ShopDiscountInfoParser(String str)
            throws JSONException {
        if (str == null ) return null;
        JSONObject jsonObject = new JSONObject(str.toString());

        String state = jsonObject.getString("Success");
        Utils.Logi(TAG, "ShopInfoParser Success=" + state);
        String msg = jsonObject.getString("ErrorInfo");
        Utils.Logi(TAG, "ShopInfoParser msg=" + msg);
        
        JSONObject jsonObject2 = jsonObject.getJSONObject("ResponseData");

            ShopDiscountInfo data = new ShopDiscountInfo();

            data.mID = (jsonObject2.getInt("ID"));
            Utils.Logi(TAG, "data.mID=" + data.mID);
            data.mName = (jsonObject2.getString("Name"));
            Utils.Logi(TAG, "data.mName=" + data.mName);
            data.mAddress = (jsonObject2.getString("Address"));
            Utils.Logi(TAG, "data.mAddress=" + data.mAddress);
            data.mContact = (jsonObject2.getString("Contact"));
            Utils.Logi(TAG, "data.mContact=" + data.mContact);
            
            data.mPriceOriginal = (jsonObject2.getDouble("PriceOriginal"));
            Utils.Logi(TAG, "data.mPriceOriginal=" + data.mPriceOriginal);            
            data.mMPVOriginal = (jsonObject2.getDouble("MPVOriginal"));
            Utils.Logi(TAG, "data.mMPVOriginal=" + data.mMPVOriginal);
            data.mSUVOriginal = (jsonObject2.getDouble("SUVOriginal"));
            Utils.Logi(TAG, "data.mSUVOriginal=" + data.mSUVOriginal);
            
            data.mPrice = (jsonObject2.getDouble("Price"));
            Utils.Logi(TAG, "data.mPrice=" + data.mPrice);            
            data.mMPV = (jsonObject2.getDouble("MPV"));
            Utils.Logi(TAG, "data.mMPV=" + data.mMPV);
            data.mSUV = (jsonObject2.getDouble("SUV"));
            Utils.Logi(TAG, "data.mSUV=" + data.mSUV);
            
            
            data.mPastingDiscount = (jsonObject2.getString("PastingDiscount"));
            Utils.Logi(TAG, "data.mPastingDiscount=" + data.mPastingDiscount);
            data.mCoatingDiscount = (jsonObject2.getString("CoatingDiscount"));
            Utils.Logi(TAG, "data.mCoatingDiscount=" + data.mCoatingDiscount);
            data.mWaxingDiscount = (jsonObject2.getString("WaxingDiscount"));
            Utils.Logi(TAG, "data.mWaxingDiscount=" + data.mWaxingDiscount);
            data.mGlazingDiscount = (jsonObject2.getString("GlazingDiscount"));
            Utils.Logi(TAG, "data.mGlazingDiscount=" + data.mGlazingDiscount);


        return data;
    }
}
