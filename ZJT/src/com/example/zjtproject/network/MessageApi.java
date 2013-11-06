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

public class MessageApi {
    static public String Send(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Message/Send";
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
    }
    
    static public String DeleteMsg(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Message/DeleteMsg";
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
    }
    
    static public String DeleteContact(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Message/DeleteContact";
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
    }
    
    
    static public String Report(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Message/Report";
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
    }
    static public String ContactList(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Message/ContactList";
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
    }
    static public String MsgList(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Message/MsgList";
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
