package org.linphone.zhizhiui.data;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class CommentsNetworkUtils {

	final static String TAG = "NetworkUtils";

	// 【评论】comment
	// send(to_mid,content,score): 发送评论
	// 返回json(state,msg)
	static public String sendcommentResultForHttpGet(String to_mid,
			String content, String score) throws ClientProtocolException,
			IOException {
		String path = NetworkUtils.HostIP + "comment/send.html?";

		String uri = path + "to_mid=" + to_mid + "&content=" + content
				+ "&score=" + score;
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

	// getList(page): 获取我的评论列表
	// 返回json(mid:评论人id,content:内容,ctime:时间)
	static public String getListcommentResultForHttpGet(int page, String mid)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "comment/getList.html?";

		String uri = path + "page=" + page + "&mid=" + mid;
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

	// 【消息】nmsg
	// send(to_mid,content,score): 发送评论
	static public String sendnmsgResultForHttpGet(String to_mid,
			String content, String score) throws ClientProtocolException,
			IOException {
		String path = NetworkUtils.HostIP + "nmsg/send.html?";

		String uri = path + "to_mid=" + to_mid + "&content=" + content
				+ "&score=" + score;
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

	// 返回json(state,msg)
	// getList(page): 获取我的消息列表
	// 返回json(mid:发送人id,content:内容,ctime:时间)
	static public String getListnmsgResultForHttpGet(int page)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "nmsg/getList.html?";

		String uri = path + "page=" + page;
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

	// 【电话】ring
	// call(tomid:打给谁,holdtime:通话时间)
	// 返回json(state,msg)
	static public String callResultForHttpGet(String to_mid, String holdtime)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "ring/call.html?";

		String uri = path + "tomid=" + to_mid + "&holdtime=" + holdtime;
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

	// getLog(page): 获取打电话日子列表
	// 返回json(mid:会员ID,name:名字,sex:性别,country:国籍,city:城市,language:母语,edu:学历,job:工作,goodat:特长,profile:头像,price:价格,teach_type:教育类型,teach_level:级别)

	static public String getLogResultForHttpGet(int page)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "ring/getLog.html?";

		String uri = path + "page=" + page;
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
