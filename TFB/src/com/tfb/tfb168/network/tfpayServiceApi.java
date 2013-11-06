package com.tfb.tfb168.network;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class tfpayServiceApi {
    static String HOSTuri = "http:// 113.106.85.45:9081/mcg/tfpayService";

    // 公共事业缴费
    // 缴费城市查询
    // http://172.16.85.212:9081/mcg/tfpayService?TYPE=QueryCity&MERCHANT_ID=888001842150001&VERSION=1.0.0&PE_ITM=001&HMAC=7ec4ff27bc6a5e02e113186f457d0e28
    static public String QueryCity() throws ClientProtocolException,
            IOException {
        String uri = "http://172.16.85.212:9081/mcg/tfpayService?TYPE=QueryCity&MERCHANT_ID=888001842150001&VERSION=1.0.0&PE_ITM=001&HMAC=7ec4ff27bc6a5e02e113186f457d0e28";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }

    // 缴费事业单位查询
    // http://172.16.85.212:9081/mcg/tfpayService?TYPE=QueryInst&MERCHANT_ID=888001842150001&VERSION=1.0.0&PE_ITM=001&INST_CITY=0102&HMAC=62a936fd7b31078c07b5e5c77395d20e
    static public String QueryInst() throws ClientProtocolException,
            IOException {
        String uri = "http://172.16.85.212:9081/mcg/tfpayService?TYPE=QueryInst&MERCHANT_ID=888001842150001&VERSION=1.0.0&PE_ITM=001&INST_CITY=0102&HMAC=62a936fd7b31078c07b5e5c77395d20e";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }
    
    // 待缴费信息查询
    // http://172.16.85.212:9081/mcg/tfpayService?MERCHANT_ID=888001842150001&TYPE=QueryBill&VERSION=1.0.0&PE_ITM=001&RUT_CORG=CEBB&INST_CITY=0102&INST_CD=00000000000000000001&PE_BAR_CD=test&HMAC=0560486af8d7fdf9708221b758c33292
    static public String QueryBill() throws ClientProtocolException,
            IOException {
        String uri = "http://172.16.85.212:9081/mcg/tfpayService?MERCHANT_ID=888001842150001&TYPE=QueryBill&VERSION=1.0.0&PE_ITM=001&RUT_CORG=CEBB&INST_CITY=0102&INST_CD=00000000000000000001&PE_BAR_CD=test&HMAC=0560486af8d7fdf9708221b758c33292";
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }
    
    //单笔联机缴费 BillCharge
    
    
    
  //  多笔联机缴费通知 BillChargeBatch
    
    
    
    
    //联机缴费结果查询 BillChargeResult
}
