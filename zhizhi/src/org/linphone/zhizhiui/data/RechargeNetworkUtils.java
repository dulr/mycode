package org.linphone.zhizhiui.data;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class RechargeNetworkUtils {

	final static String TAG = "NetworkUtils";

	// getPayHistory(page): 获取我的支付历史，所有的价格为负数，表示扣钱
	// 返回json(id:历史ID,mid:用户mid,amount:价格,content:备注,ctime:时间)
	static public String getPayHistoryResultForHttpGet(int page)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "recharge/getPayHistory.html?";

		// http://open.zhizhi.com/recharge/getPayHistory.html?page=1
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

	// getIncomeHistory(page):获取我的收益历史，所有的价格为正数，表示赚钱
	// 返回json(id:历史ID,mid:用户mid,amount:价格,content:备注,ctime:时间)
	static public String getIncomeHistoryResultForHttpGet(int page)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "recharge/getIncomeHistory.html?";

		// http://open.zhizhi.com/recharge/getIncomeHistory.html?page=1
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

	// doRecharge(amount,code): 充值
	// 返回json(state,msg)
	static public String doRechargeResultForHttpGet(String amount, String code)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "recharge/doRecharge.html?";

		// http://open.zhizhi.com/recharge/doRecharge.html?amount=amount&code=code
		String uri = path + "amount=" + amount + "&code=" + code;
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

	// getCash(amount,code): 提现
	// 返回json(state,msg)
	static public String getCashResultForHttpGet(String amount, String code)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "recharge/getCash.html?";

		// http://open.zhizhi.com/recharge/getCash.html?amount=amount&code=code
		String uri = path + "amount=" + amount + "&code=" + code;
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

	// setBank(bank,card_id,name,address): 设置银行
	// 返回json(state,msg)
	static public String setBankResultForHttpGet(String bank, String card_id,
			String name, String address) throws ClientProtocolException,
			IOException {
		String path = NetworkUtils.HostIP + "recharge/setBank.html?";

		// http://open.zhizhi.com/recharge/setBank.html?amount=amount&code=code
		String uri = path + "bank=" + bank + "&card_id=" + card_id + "&name="
				+ name + "&address=" + address;
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

	// setPassword(old,new): 重置取款密码
	// 返回json(state,msg)
	static public String setPasswordResultForHttpGet(String oldpw, String newpw)
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "recharge/setPassword.html?";

		// http://open.zhizhi.com/recharge/setPassword.html?amount=amount&code=code
		String uri = path + "old=" + oldpw + "&new=" + newpw;
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

	// getSta(): 获取统计信息
	// 返回json(paytimes:消费次数,paycount:支付总额,incometimes:收入次数,incomecount:收入总额)
	static public String getStaResultForHttpGet()
			throws ClientProtocolException, IOException {
		String path = NetworkUtils.HostIP + "recharge/getSta.html";

		// http://open.zhizhi.com/recharge/getSta.html?amount=amount&code=code
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

}
