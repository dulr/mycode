package org.linphone.zhizhiui.data;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class YaoqingNetworkUtils {
	final static String TAG = "NetworkUtils";

	// send(to_mid:通知接收人id,content:请求内容):发送请求
	// 返回json(state,msg)
	static public String noticesendResultForHttpGet(String to_mid, String content)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "notice/sendRequest.html?";

		String uri = path + "to_mid=" + to_mid + "&content=" + content;
		String result = "";
		HttpResponse response = NetworkUtils
				.getHttpResponseResultForHttpGet(uri);

		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);

		return result;
	}

	// pass(id:通过的通知的id):接收请求
	// 返回json(state,msg)
	static public String noticepassResultForHttpGet(String id, String state)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "notice/passRequest.html?";

		String uri = path + "id=" + id+"&pass=" + state;;
		String result = "";
		HttpResponse response = NetworkUtils
				.getHttpResponseResultForHttpGet(uri);

		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);

		return result;
	}

	// getPassList(time通知通过时间，返回该时间之后的所有我通过的通知,page:第几页):
	// 获取通过我请求的数据，为避免需要通过socket建立长连接。
	// 通过定时取消息方式获取是否通过请求
	// 返回(mid用户id,content请求内容,passtime通过实践,uinfo用户信息)
	static public String noticegetPassListResultForHttpGet(String time, int page)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "notice/getPassList.html?";

		String uri = path + "time=" + time + "&page=" + page;
		String result = "";
		HttpResponse response = NetworkUtils
				.getHttpResponseResultForHttpGet(uri);

		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);

		return result;
	}

	// getList(time通知时间，返回该时间之后的所有通知,page:第几页):获取请求我的数据
	// 返回(mid用户id,content请求内容,passtime通过实践,uinfo用户信息)
	// 实践,uinfo用户信息)
	static public String noticegetListResultForHttpGet(String time, int page)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "notice/getList.html?";

		String uri = path + "time=" + time + "&page=" + page;
		String result = "";
		HttpResponse response = NetworkUtils
				.getHttpResponseResultForHttpGet(uri);

		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);

		return result;
	}

	   static public String noticegetLastReceiveResultForHttpGet()
	            throws ClientProtocolException, IOException {
	        String path = NetworkUtils.HostIP + "notice/getLastReceive.html";

	        String uri = path;// + "time=" + time + "&page=" + page;
	        String result = "";
	        HttpResponse response = NetworkUtils
	                .getHttpResponseResultForHttpGet(uri);

	        if (response.getStatusLine().getStatusCode() == 200) {
	            HttpEntity entity = response.getEntity();
	            result = EntityUtils.toString(entity, HTTP.UTF_8);
	        } else {
	            result = "error";
	        }
	        Log.i(TAG, "result=" + result);

	        return result;
	    }


	    static public String noticegetLastRequestResultForHttpGet()
	            throws ClientProtocolException, IOException {
	        String path = NetworkUtils.HostIP + "notice/getLastRequest.html";

	        String uri = path;// + "time=" + time + "&page=" + page;
	        String result = "";
	        HttpResponse response = NetworkUtils
	                .getHttpResponseResultForHttpGet(uri);

	        if (response.getStatusLine().getStatusCode() == 200) {
	            HttpEntity entity = response.getEntity();
	            result = EntityUtils.toString(entity, HTTP.UTF_8);
	        } else {
	            result = "error";
	        }
	        Log.i(TAG, "result=" + result);

	        return result;
	    }
}
