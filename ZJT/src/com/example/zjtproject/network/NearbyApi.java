package com.example.zjtproject.network;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.example.zjtproject.utils.Utils;

import android.util.Log;

public class NearbyApi {

    // 附近店铺  查询附近一定距离内的店铺信息并显示在地图上
    static public String Nearby(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Shop/Nearby";
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
    }
    
    // 店铺优惠信息
    // 查询附近一定距离内的店铺信息并显示在地图上
    static public String Discount(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Shop/Discount";
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
    }
}
