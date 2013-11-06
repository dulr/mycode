package com.example.zjtproject.data;

public class ShopDiscountInfo {
    // ID int 编号
    // Name string 名称
    // Address string 地址
    // Contact string 联系电话
    // Price Decimal 轿车现价（普通车）
    // MPV Decimal 商务车现价（七座商务车）
    // SUV Decimal SUV现价
    // PriceOriginal Decimal 轿车原价（普通车）
    // MPVOriginal Decimal 商务车原价（七座商务车）
    // SUVOriginal Decimal SUV原价
    // PastingDiscount string 贴膜优惠
    // CoatingDiscount string 镀膜优惠
    // WaxingDiscount string 打蜡优惠
    // GlazingDiscount string 封釉优惠

    public int mID;
    public String mName;
    public String mAddress;
    public String mContact;
    public double mPrice;
    public double mMPV;
    public double mSUV;
    public double mPriceOriginal;
    public double mMPVOriginal;
    public double mSUVOriginal;
    public String mPastingDiscount;
    public String mCoatingDiscount;
    public String mWaxingDiscount;
    public String mGlazingDiscount;
}
