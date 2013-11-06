package org.linphone.zhizhiui.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class RegUtils {
	final static String TAG = "NetworkUtils";

	// List<NameValuePair> list = new ArrayList<NameValuePair>();
	// list.add(new BasicNameValuePair("name", name));
	// list.add(new BasicNameValuePair("pwd", pwd));

	// teachRegStep1(): 教师注册步骤1
	// 返回json(state,msg)
	static public String teachRegStep1ResultForHttpPost(List<NameValuePair> list)
			throws ClientProtocolException, IOException {
		String uri = NetworkUtils.HostIP + "user/teachRegStep1.html";
		String result = "";
		
		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);
		return result;
	}

	// teachRegStep2(): 教师注册步骤2
	// 返回json(state,msg)
	static public boolean teachRegStep2ResultForHttpPost(File uploadFile)
			throws ClientProtocolException, IOException {
		String uri = NetworkUtils.HostIP + "user/teachRegStep2.html";
//		String result = "";
//		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
//		if (response.getStatusLine().getStatusCode() == 200) {
//			HttpEntity entity = response.getEntity();
//			result = EntityUtils.toString(entity, HTTP.UTF_8);
//		} else {
//			result = "error";
//		}
//		Log.i(TAG, "result=" + result);
//		return result;
		Log.i(TAG, "uri=" + uri.toString());
		Log.i(TAG, "uploadFile=" + uploadFile.toString());
		return UploadFileUtils.uploadFile(uri, "person.png", uploadFile);
	}

	// teachRegStep3(): 教师注册步骤3
	// 返回json(state,msg)
	static public boolean teachRegStep3ResultForHttpPost(File uploadFile)
			throws ClientProtocolException, IOException {
		String uri = NetworkUtils.HostIP + "user/teachRegStep3.html";
//		String result = "";
//		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
//		if (response.getStatusLine().getStatusCode() == 200) {
//			HttpEntity entity = response.getEntity();
//			result = EntityUtils.toString(entity, HTTP.UTF_8);
//		} else {
//			result = "error";
//		}
//		Log.i(TAG, "result=" + result);
//		return result;
		Log.i(TAG, "uri=" + uri.toString());
		Log.i(TAG, "uploadFile=" + uploadFile.toString());
		return UploadFileUtils.uploadFile(uri, "person.png", uploadFile);
	}

	// teachRegStep4(): 教师注册步骤4
	// 返回json(state,msg)
	static public boolean teachRegStep4ResultForHttpPost(File uploadFile)
			throws ClientProtocolException, IOException {
		String uri = NetworkUtils.HostIP + "user/teachRegStep4.html";
//		String result = "";
//		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
//		if (response.getStatusLine().getStatusCode() == 200) {
//			HttpEntity entity = response.getEntity();
//			result = EntityUtils.toString(entity, HTTP.UTF_8);
//		} else {
//			result = "error";
//		}
//		Log.i(TAG, "result=" + result);
//		return result;
		Log.i(TAG, "uri=" + uri.toString());
		Log.i(TAG, "uploadFile=" + uploadFile.toString());
		return UploadFileUtils.uploadFile(uri, "person.png", uploadFile);
	}

	// teachRegStep5(): 教师注册步骤5
	// 返回json(state,msg)
	static public String teachRegStep5ResultForHttpPost(List<NameValuePair> list)
			throws ClientProtocolException, IOException {
		String uri = NetworkUtils.HostIP + "user/teachRegStep5.html";
		String result = "";
		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);
		return result;
	}

	// teachRegStep6(): 教师注册步骤6
	// 返回json(state,msg)
	static public String teachRegStep6ResultForHttpPost(List<NameValuePair> list)
			throws ClientProtocolException, IOException {
		String uri = NetworkUtils.HostIP + "user/teachRegStep6.html";
		String result = "";
		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);
		return result;
	}

	// teachSave(): 教师保存基本数据
	// 返回json(state,msg)
	static public String teachSaveResultForHttpPost(List<NameValuePair> list)
			throws ClientProtocolException, IOException {
		String uri = NetworkUtils.HostIP + "user/teachSave.html";
		String result = "";
		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);
		return result;
	}

	// teachSaveDiploma(): 教师保存证书
	// 返回json(state,msg)
	static public String teachSaveDiplomaResultForHttpPost(
			List<NameValuePair> list) throws ClientProtocolException,
			IOException {
		String uri = NetworkUtils.HostIP + "user/teachSaveDiploma.html";
		String result = "";
		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);
		return result;
	}

	// teachSaveIdcard(): 教师保存身份证
	// 返回json(state,msg)
	static public String teachSaveIdcardResultForHttpPost(
			List<NameValuePair> list) throws ClientProtocolException,
			IOException {
		String uri = NetworkUtils.HostIP + "user/teachSaveIdcard.html";
		String result = "";
		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);
		return result;
	}
	// stuRegStep3(): 学生注册步骤3
	// 返回json(state,msg)
	static public String stuRegStep3ResultForHttpPost(
			List<NameValuePair> list) throws ClientProtocolException,
			IOException {
		String uri = NetworkUtils.HostIP + "user/stuRegStep3.html";
		String result = "";
		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);
		return result;
	}
	// stuRegStep4(): 学生注册步骤4
	// 返回json(state,msg)
	static public boolean stuRegStep4ResultForHttpPost(
			File uploadFile) throws ClientProtocolException,
			IOException {
		String uri = NetworkUtils.HostIP + "user/stuRegStep4.html";
		Log.i(TAG, "uri=" + uri.toString());
		Log.i(TAG, "uploadFile=" + uploadFile.toString());
		return UploadFileUtils.uploadFile(uri, "person.png", uploadFile);
//		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
		
	}
	// stuRegStep5(): 学生注册步骤5
	// 返回json(state,msg)
	static public String stuRegStep5ResultForHttpPost(
			List<NameValuePair> list) throws ClientProtocolException,
			IOException {
		String uri = NetworkUtils.HostIP + "user/stuRegStep5.html";
		String result = "";
		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);
		return result;
	}
	// studentSave(): 学生保存基本数据
	static public String studentSaveResultForHttpPost(
			List<NameValuePair> list) throws ClientProtocolException,
			IOException {
		String uri = NetworkUtils.HostIP + "user/studentSave.html";
		String result = "";
		HttpResponse response = NetworkUtils.getReultForHttpPost(uri, list);
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
