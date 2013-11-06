package com.example.zjtproject.jsonparser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.zjtproject.data.ShopInfo;
import com.example.zjtproject.data.UserInteractionCommentListItemData;
import com.example.zjtproject.data.UserInteractionListItemData;
import com.example.zjtproject.utils.Utils;

public class InteractionJsonParser {
    final static String TAG = "JsonParser";
    //
    //{"ID":11,
    //"UserID":60006012,
    //"NickName":null,
    //"ContentType":0,
    //"Content":"为什么1111",
    //"Top":1,
    //"Setp":0,
    //"Count":0,
    //"CreateTime":"2013-04-23T00:44:23.52",
    //"img":null,
    //"imgType":null,
    //"IsAnonymous":0,
    //"MemberFace":null}
    public static List<UserInteractionListItemData> InteractionListParser(JSONArray jsonArray)
            throws JSONException {
        
        List<UserInteractionListItemData> mListData = new ArrayList<UserInteractionListItemData>();
        if (jsonArray == null ) return mListData;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
            UserInteractionListItemData data = new UserInteractionListItemData();

            data.mID = (jsonObject2.getString("ID"));
            Utils.Logi(TAG, "data.mID=" + data.mID);
            data.mUserID = (jsonObject2.getString("UserID"));
            Utils.Logi(TAG, "data.mUserID=" + data.mUserID);
            data.mNickName = (jsonObject2.getString("NickName"));
            Utils.Logi(TAG, "data.mNickName=" + data.mNickName);
            data.mContentType = (jsonObject2.getString("ContentType"));
            Utils.Logi(TAG, "data.mContentType=" + data.mContentType);
            data.mContent = (jsonObject2.getString("Content"));
            Utils.Logi(TAG, "data.mContent=" + data.mContent);
            
            data.mTop = (jsonObject2.getInt("Top"));
            Utils.Logi(TAG, "data.mTop=" + data.mTop);
            data.mSetp = (jsonObject2.getInt("Setp"));
            Utils.Logi(TAG, "data.mSetp=" + data.mSetp);
            data.mCount = (jsonObject2.getInt("Count"));
            Utils.Logi(TAG, "data.mCount=" + data.mCount);
            data.mCreateTime = (jsonObject2.getString("CreateTime"));
            Utils.Logi(TAG, "data.mCreateTime=" + data.mCreateTime);
            data.mimg = (jsonObject2.getString("img"));
            Utils.Logi(TAG, "data.mimg=" + data.mimg);
            
            data.mimgType = (jsonObject2.getString("imgType"));
            Utils.Logi(TAG, "data.mimgType=" + data.mimgType);
            data.mIsAnonymous = (jsonObject2.getString("IsAnonymous"));
            Utils.Logi(TAG, "data.mIsAnonymous=" + data.mIsAnonymous);
            data.mMemberFace = (jsonObject2.getString("MemberFace"));
            Utils.Logi(TAG, "data.mMemberFace=" + data.mMemberFace);
            mListData.add(data);
        }

        return mListData;
    }
    
    
    
    
    public static List<UserInteractionCommentListItemData> InteractionCommentListParser(JSONArray jsonArray)
            throws JSONException {
        //{"ID":1,"UserID":600000057,"NickName":"12333","ParentID":24,
        // "Comment":"呵呵哈哈哈","CreateTime":"2013-06-26T10:32:53.397"}]}
        List<UserInteractionCommentListItemData> mListData = new ArrayList<UserInteractionCommentListItemData>();
        if (jsonArray == null ) return mListData;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
            UserInteractionCommentListItemData data = new UserInteractionCommentListItemData();

            data.mID = (jsonObject2.getString("ID"));
            Utils.Logi(TAG, "data.mID=" + data.mID);
            data.mUserID = (jsonObject2.getString("UserID"));
            Utils.Logi(TAG, "data.mUserID=" + data.mUserID);
            data.mNickName = (jsonObject2.getString("NickName"));
            Utils.Logi(TAG, "data.mNickName=" + data.mNickName);
            data.mParentID = (jsonObject2.getString("ParentID"));
            Utils.Logi(TAG, "data.mParentID=" + data.mParentID);
            data.mComment = (jsonObject2.getString("Comment"));
            Utils.Logi(TAG, "data.mComment=" + data.mComment);
            
       
            
            data.mCreateTime = (jsonObject2.getString("CreateTime"));
            Utils.Logi(TAG, "data.mCreateTime=" + data.mCreateTime);
            mListData.add(data);
        }

        return mListData;
    }
}
