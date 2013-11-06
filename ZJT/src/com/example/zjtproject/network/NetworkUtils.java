package com.example.zjtproject.network;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import com.example.zjtproject.utils.Utils;

import android.util.Log;

public class NetworkUtils {
    public final static String TAG = "NetworkUtils";
    public static String cookie = null;
    //public final static String HostIP = "http://121.52.217.89:80/";
    //public final static String HostDownLoadIP = "121.52.217.89:80";
    public final static String Host = "http://api.zuojiatong.com/";

    public final static String HostUri = "api.zuojiatong.com/";

    static HttpResponse getHttpResponseResultForHttpGet(String uri)
            throws ClientProtocolException, IOException {
        Utils.Logi(TAG, "uri=" + uri.toString());

        HttpGet httpGet = new HttpGet((uri));// 编者按：与HttpPost区别所在，这里是将参数在地址中传递

        httpGet.addHeader("Host", Host);
        if (cookie != null) {
            httpGet.addHeader("Cookie", "PHPSESSID=" + cookie);
            Utils.Logi(TAG, "PHPSESSID=" + cookie.toString());
        }
        httpGet.addHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; rv:11.0) Gecko/20100101 Firefox/11.0");
        return new DefaultHttpClient().execute(httpGet);
    }

    static public HttpResponse getResponseForHttpPost(String uri,
            List<NameValuePair> list) throws ClientProtocolException,
            IOException {
        Utils.Logi(TAG, "uri=" + uri.toString());
        HttpPost httpPost = new HttpPost(uri);

        if (cookie != null) {
            httpPost.addHeader("Cookie", "PHPSESSID=" + cookie);
            Utils.Logi(TAG, "PHPSESSID=" + cookie.toString());
        }
        // 与HttpGet区别所在，这里是将参数用List传递
        if (list != null) {
            PrintList(list);
            httpPost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
        }
            
        return new DefaultHttpClient().execute(httpPost);
    }

    static void PrintList(List<NameValuePair> list) {
        if (list == null) {
            Utils.Logi(TAG, " Post list is null");
            return;
        }
        for (int i = 0; i < list.size(); i++)
            Utils.Logi(TAG, list.get(i).getName() + "="
                    + list.get(i).getValue());
    }
}
