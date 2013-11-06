package org.linphone.zhizhiui.data;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class UserNetworkUtils {
	final static String TAG = "NetworkUtils";

	// getUserInfo(id): 获取其他用户的信息。根据实际需求显示
	// 返回json(mid:会员ID,name:名字,sex:性别,country:国籍,city:城市,language:母语,edu:学历,job:工作,goodat:特长,profile:头像,price:价格,teach_type:教育类型,teach_level:级别)
	static public String getUserInfoResultForHttpGet(int id)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/getUserInfo.html?";

		// http://open.zhizhi.com/recharge/getIncomeHistory.html?page=1
		String uri = path + "id=" + id;
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

	// getMyInfo(): 获取我的信息，返回所有的用户信息
	// 返回json(mid:会员ID,name:名字,sex:性别,country:国籍,city:城市,language:母语,edu:学历,job:工作,goodat:特长,profile:头像,price:价格,teach_type:教育类型,teach_level:级别)
	static public String getMyInfoResultForHttpGet()
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/getMyInfo.html";

		String uri = path;
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

	// getMyFollow(page): 获取我关注的人的列表，返回多个人的数组
	// 返回json(mid:会员ID,name:名字,sex:性别,country:国籍,city:城市,language:母语,edu:学历,job:工作,goodat:特长,profile:头像,price:价格,teach_type:教育类型,teach_level:级别)

	static public String getMyFollowResultForHttpGet(int page)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/getMyFollow.html?";

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

	static public String getStudentForHttpGet(int page)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/getMyFollow.html?";

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
	
	// getMyFans(page): 获取我的粉丝，返回多个人的数组
	// 返回json(mid:会员ID,name:名字,sex:性别,country:国籍,city:城市,language:母语,edu:学历,job:工作,goodat:特长,profile:头像,price:价格,teach_type:教育类型,teach_level:级别)
	static public String getMyFansResultForHttpGet(int page)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/getMyFans.html?";

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

	// getContactList(page): 获取联系人列表
	// 返回json(mid:用户ID,profile:用户头像地址,isonline:是否在线,name:名称,price:价格,sign:签名,points:积分)
	static public String getContactListResultForHttpGet(int page)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/getContactList.html?";

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

	// getCity(): 获取城市
	// 返回json(country:筛选列表的国家,city:筛选列表的城市)

	static public String getContactListResultForHttpGet()
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/getCity.html";

		String uri = path;
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

	// uploadProfile(file): 上传头像，文件参数名定义为file
	// 返回json(state,msg)

	static public String uploadProfileResultForHttpGet(String file)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/uploadProfile.html?";

		String uri = path + "file=" + file;
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

	// getSta(): 获取用户的统计信息
	// 返回json(onlineTime,ringTime)
	static public String getStaListResultForHttpGet()
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/getSta.html";

		String uri = path;
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

	// getSta(): 获取用户的统计信息
	// 返回json(onlineTime,ringTime)
	static public String getStaListResultForHttpGet(String mid)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/getSta.html?";

		String uri = path + "mid=" + mid;
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
	
//	follow(mid): 关注某人
//	返回json(state,msg)
	static public String followResultForHttpGet(int mid)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/follow.html?";

		String uri = path + "mid=" + mid;
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
//	isfollow(mid): 关注某人
//	返回json(state,msg)
	static public String isfollowResultForHttpGet(int mid)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/isfollow.html?";

		String uri = path + "mid=" + mid;
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
	
//	cancelfollow(mid): 关注某人
//	返回json(state,msg)
	static public String cancelfollowResultForHttpGet(int mid)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "user/cancelfollow.html?";

		String uri = path + "mid=" + mid;
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
