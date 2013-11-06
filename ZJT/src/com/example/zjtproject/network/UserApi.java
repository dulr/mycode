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

public class UserApi {

	// 获取服务器时间
	// 客户端以服务器时间为基准，进行数据的读取操作。服务器时间也是作为授权验证的一部分。
	// 验证时时间差小于1分钟则授权。

	// static public String GetServerDatetime() throws ClientProtocolException,
	// IOException {
	// String uri = NetworkUtils.Host + "API/Base/GetServerDatetime";
	// String result = "";
	// HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, null);
	// Utils.Logi(NetworkUtils.TAG, "getStatusCode="
	// + response.getStatusLine().getStatusCode());
	// HttpEntity entity = response.getEntity();
	// result = EntityUtils.toString(entity, HTTP.UTF_8);
	//
	// Utils.Logi(NetworkUtils.TAG, "result=" + result);
	// return result;
	// }
	//
	// static public String SendShortMessage(List<NameValuePair> list)
	// throws ClientProtocolException, IOException {
	// String uri = NetworkUtils.Host + "API/Base/SendShortMessage";
	// String result = "";
	// HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
	// Utils.Logi(NetworkUtils.TAG, "getStatusCode="
	// + response.getStatusLine().getStatusCode());
	//
	// HttpEntity entity = response.getEntity();
	// result = EntityUtils.toString(entity, HTTP.UTF_8);
	//
	// Utils.Logi(NetworkUtils.TAG, "result=" + result);
	// return result;
	// }
	static public String Register(List<NameValuePair> list)
			throws ClientProtocolException, IOException {
		String uri = NetworkUtils.Host + "API/Member/Register";
		String result = "";
		HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
		Utils.Logi(NetworkUtils.TAG, "getStatusCode="
				+ response.getStatusLine().getStatusCode());

		HttpEntity entity = response.getEntity();
		result = EntityUtils.toString(entity, HTTP.UTF_8);

		Utils.Logi(NetworkUtils.TAG, "result=" + result);
		return result;
	}
	
	static public String Login(List<NameValuePair> list)
			throws ClientProtocolException, IOException {
		String uri = NetworkUtils.Host + "API/Member/Login";
		String result = "";
		HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
		Utils.Logi(NetworkUtils.TAG, "getStatusCode="
				+ response.getStatusLine().getStatusCode());

		HttpEntity entity = response.getEntity();
		result = EntityUtils.toString(entity, HTTP.UTF_8);

		Utils.Logi(NetworkUtils.TAG, "result=" + result);
		return result;
	}
	
	static public String ChangePassword(List<NameValuePair> list)
			throws ClientProtocolException, IOException {
		String uri = NetworkUtils.Host + "API/Member/ChangePassword";
		String result = "";
		HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
		Utils.Logi(NetworkUtils.TAG, "getStatusCode="
				+ response.getStatusLine().getStatusCode());

		HttpEntity entity = response.getEntity();
		result = EntityUtils.toString(entity, HTTP.UTF_8);

		Utils.Logi(NetworkUtils.TAG, "result=" + result);
		return result;
	}
	
	static public String UploadFace(List<NameValuePair> list)
			throws ClientProtocolException, IOException {
		String uri = NetworkUtils.Host + "API/Member/UploadFace";
		String result = "";
		HttpResponse response = NetworkUtils.getResponseForHttpPost(uri, list);
		Utils.Logi(NetworkUtils.TAG, "getStatusCode="
				+ response.getStatusLine().getStatusCode());

		HttpEntity entity = response.getEntity();
		result = EntityUtils.toString(entity, HTTP.UTF_8);

		Utils.Logi(NetworkUtils.TAG, "result=" + result);
		return result;
	}
	
	static public String GetProfile(List<NameValuePair> list)
			throws ClientProtocolException, IOException {
		String uri = NetworkUtils.Host + "API/Member/GetProfile";
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
