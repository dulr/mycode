package com.tfb.tfb168.network;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.tfb.tfb168.utils.MD5;

public class OFCARDApi {
    //用户信息查询接口（queryuserinfo.do）
    static public String queryuserinfo() throws ClientProtocolException,
            IOException {
        // http://api2.ofpay.com/queryuserinfo.do?userid=Axxxxx&userpws=xxxxxxx&version=6.0
        String pw = MD5.md5("111111");
        String uri = NetworkUtils.HostUri
                + "queryuserinfo.do?userid=A00001&userpws=" + pw
                + "&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }
    
    //商品小类信息同步接口（querylist.do）
    //http://api2.ofpay.com/querylist.do?userid=Axxxxx&userpws=xxxxxxx&cardid=2303&version=6.0
    static public String querylist() throws ClientProtocolException,
            IOException {
        String uri = NetworkUtils.HostUri
                + "querylist.do?userid=Axxxxx&userpws=xxxxxxx&cardid=2303&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }
    
    //4具体商品信息同步接口（querycardinfo.do）
    //http://api2.ofpay.com/querycardinfo.do?userid=Axxxxx&userpws=xxxxxxx&cardid=2206&version=6.0
    static public String querycardinfo() throws ClientProtocolException,
            IOException {
        String uri = NetworkUtils.HostUri
                + "querycardinfo.do?userid=Axxxxx&userpws=xxxxxxx&cardid=2206&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }
    
    //5商品库存查询接口（queryleftcardnum.do）
    //http://api2.ofpay.com/queryleftcardnum.do?userid=Axxxxx&userpws=xxxxxxx&cardid=2101&version=6.0
    static public String queryleftcardnum() throws ClientProtocolException,
            IOException {
        String uri = NetworkUtils.HostUri
                + "queryleftcardnum.do?userid=Axxxxx&userpws=xxxxxxx&cardid=2101&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }
    
    //6商品价格查询接口（queryprice.do）
    //http://api2.ofpay.com/queryprice.do?userid= Axxxxx&userpws=xxxxxxxx&cardid=210102&version=6.0
    static public String queryprice() throws ClientProtocolException,
            IOException {
        String uri = NetworkUtils.HostUri
                + "queryprice.do?userid= Axxxxx&userpws=xxxxxxxx&cardid=210102&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }
    
    //7提卡接口（order.do）
    //http://api2.ofpay.com/order.do?userid=Axxxxx&userpws=xxxxxxx&cardid=360101&cardnum=1&sporder_id=xxxxxxxxx&sporder_time=xxxxxxxx& md5_str =xxxxxxxxxxxxx&version=6.0
    static public String order() throws ClientProtocolException,
            IOException {
        String uri = NetworkUtils.HostUri
                + "order.do?userid=Axxxxx&userpws=xxxxxxx&cardid=360101&cardnum=1&sporder_id=xxxxxxxxx&sporder_time=xxxxxxxx& md5_str =xxxxxxxxxxxxx&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }
    
    
    //8游戏直充接口（onlineorder.do）
    //http://api2.ofpay.com/onlineorder.do?userid=Axxxxx&userpws=xxxxxxx&cardid=360101&cardnum=1&sporder_id=xxxxxxxxx&sporder_time=xxxxxxxx&game_userid=xxxxx&game_area=xxxxxx&game_srv=xxxxx&md5_str=xxxxxxxxxxxxx&version=6.0
    static public String onlineorder() throws ClientProtocolException,
            IOException {
        String uri = NetworkUtils.HostUri
                + "onlineorder.do?userid=Axxxxx&userpws=xxxxxxx&cardid=360101&cardnum=1&sporder_id=xxxxxxxxx&sporder_time=xxxxxxxx&game_userid=xxxxx&game_area=xxxxxx&game_srv=xxxxx&md5_str=xxxxxxxxxxxxx&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    } 
    
    //9手机直充接口（onlineorder.do）
    //http://api2.ofpay.com/onlineorder.do?userid=Axxxxx&userpws=xxxxxxx&cardid=140101&cardnum=1&sporder_id=xxxxxxxxx&sporder_time=xxxxxxxx&game_userid=xxxxx&md5_str=xxxxxxxxxxxxx&ret_url=xxxxx&version=6.0
    static public String onlineorder9() throws ClientProtocolException,
            IOException {
        String uri = NetworkUtils.HostUri
                + "onlineorder.do?userid=Axxxxx&userpws=xxxxxxx&cardid=140101&cardnum=1&sporder_id=xxxxxxxxx&sporder_time=xxxxxxxx&game_userid=xxxxx&md5_str=xxxxxxxxxxxxx&ret_url=xxxxx&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    } 
    
    //10 querybill
    //http://api2.ofpay.com/querybill.do?userid=Axxxx&userpws=xxxxxxx&cardid=360101&starttime =xxxxxxxxx&endtime=xxxxxxxx&md5_str=xxxxxxxxxxxxx&version=6.0
    static public String querybill() throws ClientProtocolException,
            IOException {
        String uri = NetworkUtils.HostUri
                + "querybill.do?userid=Axxxx&userpws=xxxxxxx&cardid=360101&starttime =xxxxxxxxx&endtime=xxxxxxxx&md5_str=xxxxxxxxxxxxx&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    } 
    
    //11错卡提交
    //http://api2.ofpay.com/spcuokamgr.do?userid=Axxxxx&type=2&quest=xxxxxxx 
      //  &billid=K070928001179
    static public String spcuokamgr() throws ClientProtocolException,
            IOException {
        String uri = NetworkUtils.HostUri
                + "spcuokamgr.do?userid=Axxxxx&type=2&quest=xxxxxxx&billid=K070928001179";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    } 
    
    //13手机号码查询
    //http://api2.ofpay.com/mobinfo.do?mobilenum=1381383
    static public String mobinfo() throws ClientProtocolException,
            IOException {
        String uri =  "http://api2.ofpay.com/mobinfo.do?mobilenum=1381383";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }   
    
    //14给会员账号充值(此接口停用)
    //http://esales1.ofcard.com:8088/spcredittrans.do?userid=xxxxx&userpws=&nickname=&money=&sporder_id=xxx&md5_str=xxxx&version=6.0
    static public String spcredittrans() throws ClientProtocolException,
            IOException {
        String uri =  "http://esales1.ofcard.com:8088/spcredittrans.do?userid=xxxxx&userpws=&nickname=&money=&sporder_id=xxx&md5_str=xxxx&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }  
    
    //15查询商品最后修改时间
    //http://api2.ofpay.com/retmaxtime.do?cardid=360101
    static public String retmaxtime() throws ClientProtocolException,
            IOException {
        String uri =  "http://api2.ofpay.com/retmaxtime.do?cardid=360101";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }  
    
    //16 根据SP订单号查询充值状态(只查最近三天内订单)
    //http://202.102.53.141:83/api/query.do?userid=xxxxx&spbillid=spxxxxxx
    static public String query() throws ClientProtocolException,
            IOException {
        String uri =  "http://202.102.53.141:83/api/query.do?userid=xxxxx&spbillid=spxxxxxx";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }   
    
    //17 查询所有大类分类
    //http://api2.ofpay.com/querybigcard.do
    static public String querybigcard() throws ClientProtocolException,
            IOException {
        String uri =  "http://api2.ofpay.com/querybigcard.do";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    } 
 
    //18查询游戏区服 http://api2.ofpay.com/getareaserver.do?gameid=xxxxx
    static public String getareaserver() throws ClientProtocolException,
            IOException {
        String uri =  "http://api2.ofpay.com/getareaserver.do?gameid=xxxxx";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    } 
   
    //19查询手机号当时是否可以充值 http://api2.ofpay.com/telcheck.do?phoneno=13813856456&price=50&userid=Axxxxx
    static public String telcheck() throws ClientProtocolException,
            IOException {
        String uri =  "http://api2.ofpay.com/telcheck.do?phoneno=13813856456&price=50&userid=Axxxxx";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    } 
    
    //20 根据SP订单号补发充值状态(只补发最近三天内订单) http://api2.ofpay.com/reissue.do?userid=Axxxxx&userpws=xxxxx&spbillid=xxxx&version=6.0
    static public String reissue() throws ClientProtocolException,
            IOException {
        String uri =  "http://api2.ofpay.com/reissue.do?userid=Axxxxx&userpws=xxxxx&spbillid=xxxx&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }    
    
    //21根据手机号和面值查询商品信息
    //http://api2.ofpay.com/telquery.do?userid=Axxxxx&userpws=xxxxx&phoneno=xxxxx&pervalue=xx&version=6.0
    static public String telquery() throws ClientProtocolException,
            IOException {
        String uri =  "http://api2.ofpay.com/telquery.do?userid=Axxxxx&userpws=xxxxx&phoneno=xxxxx&pervalue=xx&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }  
    
    //22身份证核查接口（idcardquery.do）
    //http://api2.ofpay.com/idcardquery.do?userid=Axxxxx&userpws=xxxxxxx&sporder_id=xxxxxxxxx&sporder_time=xxxxxxxx&idcard=xxxxx&name=xxxxx&md5_str=xxxxxxxxxxxxx&version=6.0
    static public String idcardquery() throws ClientProtocolException,
            IOException {
        String uri =  "http://api2.ofpay.com/idcardquery.do?userid=Axxxxx&userpws=xxxxxxx&sporder_id=xxxxxxxxx&sporder_time=xxxxxxxx&idcard=xxxxx&name=xxxxx&md5_str=xxxxxxxxxxxxx&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }   
    //23根据固话号码和面值查询商品信息
    //http://api2.ofpay.com/fixtelquery.do?userid=Axxxxx&userpws=xxxxx&teltype=1&phoneno=xxxxx&pervalue=xx&version=6.0
    static public String fixtelquery() throws ClientProtocolException,
            IOException {
        String uri =  "http://api2.ofpay.com/fixtelquery.do?userid=Axxxxx&userpws=xxxxx&teltype=1&phoneno=xxxxx&pervalue=xx&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }     
   
    //24固话直充接口（fixtelorder.do）
    //http://api2.ofpay.com/fixtelorder.do?userid=Axxxxx&userpws=xxxxxxx&cardnum=50&teltype=1&sporder_id=xxxxxxxxx&sporder_time=xxxxxxxx&game_userid=xxxxx&md5_str=xxxxxxxxxxxxx&ret_url=xxxxx&version=6.0
    static public String fixtelorder() throws ClientProtocolException,
            IOException {
        String uri =  "http://api2.ofpay.com/fixtelorder.do?userid=Axxxxx&userpws=xxxxxxx&cardnum=50&teltype=1&sporder_id=xxxxxxxxx&sporder_time=xxxxxxxx&game_userid=xxxxx&md5_str=xxxxxxxxxxxxx&ret_url=xxxxx&version=6.0";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    } 
}
