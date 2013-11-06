package com.example.zjtproject.data;


public class UserInteractionListItemData {
    
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
    public String mID;
    public String mUserID;
    public String mNickName;
    public String mContentType; //互动类型0表示互动，1表示求助
    public String mContent;
    
    public int mTop;
    public int mSetp;
    public int mCount;
    
    public String mCreateTime;
    
    public String mimg;
    public String mimgType;
    public String mIsAnonymous;
    public String mMemberFace;
   
}
