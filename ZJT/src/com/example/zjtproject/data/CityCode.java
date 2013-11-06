package com.example.zjtproject.data;

public class CityCode {
    public final static CityInfo[] sCityInfo = 
        { 
        new CityInfo("100010", "北京"),
        new CityInfo("100011", "上海"), 
        new CityInfo("100012", "天津"), 
        new CityInfo("100013", "重庆"), 
        new CityInfo("100014", "福建"), 
        new CityInfo("100015", "甘肃"), 
        new CityInfo("100016", "广东"), 
        new CityInfo("100017", "广西"), 
        new CityInfo("100018", "贵州"), 
        new CityInfo("100019", "海南"), 
        new CityInfo("100020", "河北"), 
        new CityInfo("100021", "河南"), 
        new CityInfo("100023", "湖北"), 
        new CityInfo("100024", "湖南"), 
        new CityInfo("100025", "吉林"), 
        new CityInfo("100026", "江苏"), 
        new CityInfo("100027", "江西"), 
        new CityInfo("100028", "辽宁"), 
        new CityInfo("100029", "内蒙古"), 
        new CityInfo("100030", "安徽"), 
        new CityInfo("100031", "宁夏"), 
        new CityInfo("100032", "青海"), 
        new CityInfo("100033", "山东"), 
        new CityInfo("100034", "陕西"), 
        new CityInfo("100035", "山西"), 
        new CityInfo("100036", "四川"), 
        new CityInfo("100037", "西藏"), 
        new CityInfo("100038", "黑龙江"), 
        new CityInfo("100039", "新疆"), 
        new CityInfo("100040", "云南"), 
        new CityInfo("100041", "浙江"), 
        new CityInfo("100042", "深圳"), 
       /*
        
        */
        
        
        };

    public static class CityInfo {
        public final String mCityCode;
        public final String mCityName;

        CityInfo(String CityCode, String CityName) {
            mCityCode = CityCode;
            mCityName = CityName;
        }
    };
}
