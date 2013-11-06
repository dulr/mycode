package com.tfb.tfb168.network;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.tfb.tfb168.utils.Utils;

public class NetworkUtils {
    public final static String TAG = "NetworkUtils";
    public static String cookie = null;
//    public final static String HostIP = "http://121.52.217.89:80/";
    // public final static String HostDownLoadIP = "121.52.217.89:80";
//    public final static String Host = "http://api.zuojiatong.com/";

    public final static String HostUri = "http://henro.cn:8080/WebRoot/";

    static HttpResponse getHttpResponseResultForHttpGet(String uri)
            throws ClientProtocolException, IOException {
        Utils.Logi(TAG, "uri=" + uri.toString());

        HttpGet httpGet = new HttpGet((uri));// 编者按：与HttpPost区别所在，这里是将参数在地址中传递

//        httpGet.addHeader("Host", Host);
        if (cookie != null) {
            httpGet.addHeader("Cookie", "PHPSESSID=" + cookie);
            Utils.Logi(TAG, "PHPSESSID=" + cookie.toString());
        }
        httpGet.addHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; rv:11.0) Gecko/20100101 Firefox/11.0");
        return new DefaultHttpClient().execute(httpGet);
    }

    public static String getHttpResultForHttpGet(String uri)
            throws ClientProtocolException, IOException {
        String result = "";
        HttpResponse response = getHttpResponseResultForHttpGet(uri);

        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;

    }

    static public String getHttpResultForHttpPost(String uri,
            List<NameValuePair> list) throws ClientProtocolException,
            IOException {
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
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

    static public String getRegisterResultForHttpGet(String name, String pwd)
            throws ClientProtocolException, IOException {
//      　　http://henro.cn:8080/WebRoot/phone_register.action?userId=zhangsan&userPwd=123456
        String path = HostUri + "phone_register.action?userId=";
        String uri = path + name + "&userPwd=" + pwd;
        
        String result = NetworkUtils.getHttpResultForHttpGet(uri);
        return result;
    }
    
    static public String getLoginResultForHttpGet(String name, String pwd)
            throws ClientProtocolException, IOException {
        String path = HostUri + "phone_login.action?userId=";
        // http://henro.cn:8080/WebRoot/phone_login.action?userId=zhangsan&userPwd=123456
        String uri = path + name + "&userPwd=" + pwd;
        // String uri = path + "test.zhizhi.com" + "&pwd=" + "123123";
        String result = "";
        Log.i(TAG, "uri=" + uri.toString());
        HttpGet httpGet = new HttpGet(uri);

//        httpGet.addHeader("Host", Host);
//        httpGet.addHeader("User-Agent",
//                "Mozilla/5.0 (Windows NT 6.1; rv:11.0) Gecko/20100101 Firefox/11.0");
        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpResponse response = httpclient.execute(httpGet);
        // HttpResponse response = getHttpResponseResultForHttpGet(uri);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, HTTP.UTF_8);

            List<Cookie> cookies = httpclient.getCookieStore().getCookies();
            if (cookies.isEmpty()) {
                Log.i(TAG, "-------Cookie NONE---------");
            } else {
                for (int i = 0; i < cookies.size(); i++) {
                    // 保存cookie
                    // if(cookies.get(i).getName().equalsIgnoreCase("PHPSESSID"))
                    cookie = cookies.get(i).getValue();
                    Log.d(TAG, cookies.get(i).getName() + "="
                            + cookies.get(i).getValue());
                }
            }
        } else {
            result = "error";
        }
        Log.i(TAG, "result=" + result);

        return result;
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
