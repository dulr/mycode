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

public class ViolationsApi {
    static public String Query(List<NameValuePair> list)
            throws ClientProtocolException, IOException {
        String uri = NetworkUtils.Host + "API/Violations/Query";
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
