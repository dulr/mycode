
package org.linphone.zhizhiui.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser {
    final static String TAG = "JsonParser";

    int ParserLogin(String str) throws JSONException {

        JSONObject jsonObject = new JSONObject(str.toString());
        // {"state":true,"msg":"login success"}

        String state;
        String msg;

        state = jsonObject.getString("state");
        msg = jsonObject.getString("msg");

        return 1;
    }

    public static List<CommnetsData> ParserCommentList(JSONArray jsonArray) throws JSONException {
        List<CommnetsData> mListData = new ArrayList<CommnetsData>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i);
            CommnetsData data = new CommnetsData();

            data.mcontent = (jsonObject2.getString("content"));
            Log.i(TAG, "data.mcontent=" + data.mcontent);
            data.mscore = (jsonObject2.getString("score"));
            Log.i(TAG, "data.mscore=" + data.mscore);
            data.mname = (jsonObject2.getString("name"));
            Log.i(TAG, "data.mname=" + data.mname);
            data.mctime = (jsonObject2.getString("ctime"));
            Log.i(TAG, "data.mctime=" + data.mctime);

            mListData.add(data);
        }

        return mListData;
    }

    public static StaInfoData ParserStaInfoData(String str) throws JSONException {
        StaInfoData data = new StaInfoData();
        JSONObject jsonObject = new JSONObject(str.toString());
        // {"onlineTime":234234,
        // "ringTime":0,
        // "regtime":"1351150337",
        // "msgNum":"0",
        // "contactNum":"2",
        // "fansNum":"0",
        // "followNum":"1",
        // "amount":0,
        // "payTimes":3,
        // "payTotal":-7.1,
        // "star1":11,
        // "star2":12,
        // "star3":13,
        // "star4":18,
        // "star5":46}

        data.monlineTime = jsonObject.getString("onlineTime");
        data.mringTime = jsonObject.getString("ringTime");
        data.mregtime = jsonObject.getString("regtime");
        data.mmsgNum = jsonObject.getString("msgNum");
        data.mcontactNum = jsonObject.getString("contactNum");
        data.mfansNum = jsonObject.getString("fansNum");
        data.mfollowNum = jsonObject.getString("followNum");
        data.mamount = jsonObject.getString("amount");
        data.mpayTimes = jsonObject.getString("payTimes");
        data.mpayTotal = jsonObject.getString("payTotal");
        data.mstar1 = jsonObject.getInt("star1");
        data.mstar2 = jsonObject.getInt("star2");
        data.mstar3 = jsonObject.getInt("star3");
        data.mstar4 = jsonObject.getInt("star4");
        data.mstar5 = jsonObject.getInt("star5");

        return data;
    }

    public static List<MainViewListData> ParserMainViewListData(JSONArray jsonArray) {
        List<MainViewListData> mListData = new ArrayList<MainViewListData>();
        // {"mid":"1","profile":"http:\/\/open.zhizhi.com\/data\/profile\/mid1.png","isonline":"Y","name":"nomit","price":"22.00","sign":null,"points":"1"},
        // {"mid":"2","profile":"http:\/\/open.zhizhi.com\/data\/default\/sex0.png","isonline":"Y","name":"test","price":"0.00","sign":"hahah","points":"2"}
        try {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i);
            MainViewListData data = new MainViewListData();
            data.mMid = (jsonObject2.getInt("mid"));
            Log.i(TAG, "data.mMid=" + data.mMid);
            data.mPersonImageDrawablePath = (jsonObject2.getString("profile"));
            Log.i(TAG, "data.mPersonImageDrawablePath=" + data.mPersonImageDrawablePath);
            if (jsonObject2.has("isonline"))
                data.mStateImageId = (jsonObject2.getString("isonline"));
            Log.i(TAG, "data.mStateImageId=" + data.mStateImageId);
            if (jsonObject2.has("points"))
                data.mdengjiImageId = (jsonObject2.getInt("points"));
            Log.i(TAG, "data.mdengjiImageId=" + data.mdengjiImageId);
            data.mPersonName = (jsonObject2.getString("name"));
            Log.i(TAG, "data.mPersonName=" + data.mPersonName);
            if (jsonObject2.has("sign"))
                data.mPersonInfo = (jsonObject2.getString("sign"));
            Log.i(TAG, "data.mPersonInfo=" + data.mPersonInfo);
            if (jsonObject2.has("price"))
                data.mPersonPrice = (jsonObject2.getString("price"));
            Log.i(TAG, "data.mPersonPrice=" + data.mPersonPrice);
            if (jsonObject2.has("is_teach"))
                data.mis_teach = (jsonObject2.getString("is_teach"));
            Log.i(TAG, "data.mis_teach=" + data.mis_teach);
            if (jsonObject2.has("SIPNumber"))
                data.SIPNumber = (jsonObject2.getString("SIPNumber"));
            Log.i(TAG, "data.SIPNumber=" + data.SIPNumber);

            if (jsonObject2.has("phone"))
                data.phoneNumber = (jsonObject2.getString("phone"));
            Log.i(TAG, "data.phoneNumber=" + data.phoneNumber);
            mListData.add(data);
        }
        } catch (Exception e) {
			e.printStackTrace();
		}
        return mListData;
    }

    public static List<MainViewListData> ParserZuijinLianxirenListData(String str) {
        List<MainViewListData> mListData = new ArrayList<MainViewListData>();
        // {"mid":"1","profile":"http:\/\/open.zhizhi.com\/data\/profile\/mid1.png","isonline":"Y","name":"nomit","price":"22.00","sign":null,"points":"1"},
        // {"mid":"2","profile":"http:\/\/open.zhizhi.com\/data\/default\/sex0.png","isonline":"Y","name":"test","price":"0.00","sign":"hahah","points":"2"}
        try {
        JSONObject jsonObject = new JSONObject(str.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject3 = (JSONObject)jsonArray.opt(i);
            JSONObject jsonObject2 = (JSONObject)jsonObject3.getJSONObject("uinfo");

            MainViewListData data = new MainViewListData();
            data.mMid = (jsonObject2.getInt("mid"));
            Log.i(TAG, "data.mMid=" + data.mMid);
            data.mPersonImageDrawablePath = (jsonObject2.getString("profile"));
            Log.i(TAG, "data.mPersonImageDrawablePath=" + data.mPersonImageDrawablePath);
            if (jsonObject2.has("isonline"))
                data.mStateImageId = (jsonObject2.getString("isonline"));
            Log.i(TAG, "data.mStateImageId=" + data.mStateImageId);
            if (jsonObject2.has("points"))
                data.mdengjiImageId = (jsonObject2.getInt("points"));
            Log.i(TAG, "data.mdengjiImageId=" + data.mdengjiImageId);
            data.mPersonName = (jsonObject2.getString("name"));
            Log.i(TAG, "data.mPersonName=" + data.mPersonName);
            if (jsonObject2.has("sign"))
                data.mPersonInfo = (jsonObject2.getString("sign"));
            Log.i(TAG, "data.mPersonInfo=" + data.mPersonInfo);
            if (jsonObject2.has("price"))
                data.mPersonPrice = (jsonObject2.getString("price"));
            Log.i(TAG, "data.mPersonPrice=" + data.mPersonPrice);
            if (jsonObject2.has("is_teach"))
                data.mis_teach = (jsonObject2.getString("is_teach"));
            Log.i(TAG, "data.mis_teach=" + data.mis_teach);
            if (jsonObject2.has("SIPNumber"))
                data.SIPNumber = (jsonObject2.getString("SIPNumber"));
            Log.i(TAG, "data.SIPNumber=" + data.SIPNumber);

            if (jsonObject2.has("phone"))
                data.phoneNumber = (jsonObject2.getString("phone"));
            Log.i(TAG, "data.phoneNumber=" + data.phoneNumber);
            mListData.add(data);
        }
        }catch (Exception e) {
			e.printStackTrace();
		}
        return mListData;
    }

    public static MyInfoData ParserMyInfoData(String str) throws JSONException {
        // {"name":"nomit","sex":"U","mid":"1","price":"22.00","points":"1","teach_level":"","sign":null}
        MyInfoData userInfo = new MyInfoData();
        JSONObject jsonObject = new JSONObject(str.toString());

        userInfo.mName = jsonObject.getString("name");
        userInfo.mSex = jsonObject.getString("sex");
        userInfo.mMid = jsonObject.getString("mid");
        if (jsonObject.has("price"))
            userInfo.mPrice = jsonObject.getString("price");
        if (jsonObject.has("points"))
            userInfo.mPoints = jsonObject.getString("points");
        if (jsonObject.has("is_teach"))
            userInfo.mis_teach = jsonObject.getString("is_teach");
        if (jsonObject.has("teach_level"))
            userInfo.mTeach_level = jsonObject.getString("teach_level");
        if (jsonObject.has("sign"))
            userInfo.mSign = jsonObject.getString("sign");
        if (jsonObject.has("SIPNumber"))
            userInfo.SIPNumber = (jsonObject.getString("SIPNumber"));
        Log.i(TAG, "userInfo.SIPNumber=" + userInfo.SIPNumber);

        if (jsonObject.has("phone"))
            userInfo.mPhoneNumber = (jsonObject.getString("phone"));
        Log.i(TAG, "userInfo.mPhoneNumber=" + userInfo.mPhoneNumber);
        if (jsonObject.has("sex"))
            userInfo.mSex = jsonObject.getString("sex");
        if (jsonObject.has("age"))
            userInfo.mage = jsonObject.getString("age");
        if (jsonObject.has("country"))
            userInfo.mcountry = jsonObject.getString("country");
        if (jsonObject.has("city"))
            userInfo.mcity = jsonObject.getString("city");
        if (jsonObject.has("resume"))
            userInfo.mresume = jsonObject.getString("resume");
        
        if (jsonObject.has("isonline"))
            userInfo.isonline = (jsonObject.getString("isonline"));
        Log.i(TAG, "userInfo.isonline=" + userInfo.isonline);
        return userInfo;
    }

    public static UserInfoData ParserUserInfoData(String str) throws JSONException {
        // {"name":"nomit","sex":"U","mid":"1","price":"22.00","points":"1","teach_level":"","sign":null}
        UserInfoData userInfo = new UserInfoData();
        JSONObject jsonObject = new JSONObject(str.toString());

        userInfo.mName = jsonObject.getString("name");
        userInfo.mSex = jsonObject.getString("sex");
        userInfo.mage = jsonObject.getString("age");
        userInfo.mMid = jsonObject.getString("mid");
        if (jsonObject.has("price"))
            userInfo.mPrice = jsonObject.getString("price");
        if (jsonObject.has("points"))
            userInfo.mPoints = jsonObject.getString("points");
        if (jsonObject.has("is_teach"))
            userInfo.mis_teach = jsonObject.getString("is_teach");
        userInfo.mTeach_level = jsonObject.getString("teach_level");
        userInfo.mSign = jsonObject.getString("sign");

        // "country":null,"city":null,"language":null,"other_language":null,"edu":null,"job":null,"goodat":null,"teach_descript":null,
        // "major":null,"student_type":null,"student_level":null,}
        userInfo.mcountry = jsonObject.getString("country");
        userInfo.mcity = jsonObject.getString("city");
        userInfo.mlanguage = jsonObject.getString("language");
        userInfo.mother_language = jsonObject.getString("other_language");
        userInfo.medu = jsonObject.getString("edu");
        userInfo.mjob = jsonObject.getString("job");
        userInfo.mgoodat = jsonObject.getString("goodat");
        userInfo.mteach_descript = jsonObject.getString("teach_descript");
        userInfo.mmajor = jsonObject.getString("major");
        userInfo.mstudent_type = jsonObject.getString("student_type");
        userInfo.mstudent_level = jsonObject.getString("student_level");
        if (jsonObject.has("SIPNumber"))
            userInfo.SIPNumber = (jsonObject.getString("SIPNumber"));
        Log.i(TAG, "userInfo.SIPNumber=" + userInfo.SIPNumber);

        if (jsonObject.has("phone"))
            userInfo.mPhoneNumber = (jsonObject.getString("phone"));
        Log.i(TAG, "userInfo.mPhoneNumber=" + userInfo.mPhoneNumber);
        
        if (jsonObject.has("isonline"))
            userInfo.isonline = (jsonObject.getString("isonline"));
        Log.i(TAG, "userInfo.isonline=" + userInfo.isonline);
        return userInfo;
    }
}
