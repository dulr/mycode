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

public class InteractionApi {

    //发表互动/求助
    static public String SetInteractiont(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Interaction/SetInteractiont";
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
    }
    
    //互动操作-顶/踩
    static public String SetInteractionAction(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Interaction/SetInteractionAction";
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
    }
    
    
    //互动列表
    static public String InteractionList(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Interaction/InteractionList";
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
    }
    
    //互动评论
    static public String SetInteractiontComment(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Interaction/SetInteractiontComment";
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
    }  
    
    //互动评论列表
    static public String InteractionCommentList(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Interaction/InteractionCommentList";
        String result = "";
        HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
        Utils.Logi(NetworkUtils.TAG, "getStatusCode="
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, HTTP.UTF_8);

        Utils.Logi(NetworkUtils.TAG, "result=" + result);
        return result;
    }  
    
    //互动收藏
    static public String SetInteractiontCollect(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Interaction/SetInteractiontCollect";
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
