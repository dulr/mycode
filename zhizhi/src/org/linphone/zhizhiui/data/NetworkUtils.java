package org.linphone.zhizhiui.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

public class NetworkUtils {

	// final static String Host = "http://open.zhizhi.com/member";
	public final static String HostIP = "http://121.52.217.89:80/";
	public final static String HostDownLoadIP = "121.52.217.89:80";
	public final static String Host = "open.zhizhi.com";

	final static String TAG = "NetworkUtils";
	public static String cookie = null;

	static public String getLoginResultForHttpGet(String name, String pwd)
			throws ClientProtocolException, IOException {
		String path = HostIP + "member/dologin.html?email=";
		// http://open.zhizhi.com/member/dologin.html?email=test.zhizhi.com&pwd=123123
		String uri = path + name + "&pwd=" + pwd;
		// String uri = path + "test.zhizhi.com" + "&pwd=" + "123123";
		String result = "";
		Log.i(TAG, "uri=" + uri.toString());
		HttpGet httpGet = new HttpGet(uri);
		
		httpGet.addHeader("Host", Host);
		httpGet.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; rv:11.0) Gecko/20100101 Firefox/11.0");
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

	static public String getRegisterResultForHttpGet(String name, String pwd)
			throws ClientProtocolException, IOException {
		String path = HostIP + "member/reg.html?email=";
		// reg注册
		// http://open.zhizhi.com/member/reg.html?email=test.zhizhi.com&pwd=123123
		String uri = path + name + "&pwd=" + pwd;
		String result = "";
		Log.i(TAG, "uri=" + uri.toString());
		HttpGet httpGet = new HttpGet(uri);

		httpGet.addHeader("Host", Host);
		httpGet.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; rv:11.0) Gecko/20100101 Firefox/11.0");
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
//	member/keeponline
//	调用这个接口。无需参数
//	返回简单状态true
	static public String getkeeponlineResultForHttpGet()
			throws ClientProtocolException, IOException {
		String path = HostIP + "member/keeponline.html";
		// setPassword重置密码
		// http://open.zhizhi.com/member/setPassword.html?old=222&new=111
		String uri = path ;//+ old + "&new=" + newpw;
		String result = "";
		HttpResponse response = getHttpResponseResultForHttpGet(uri);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);

		return result;
	}
	
	static public String getResetPWResultForHttpGet(String old, String newpw)
			throws ClientProtocolException, IOException {
		String path = HostIP + "member/setPassword.html?old=";
		// setPassword重置密码
		// http://open.zhizhi.com/member/setPassword.html?old=222&new=111
		String uri = path + old + "&new=" + newpw;
		String result = "";
		HttpResponse response = getHttpResponseResultForHttpGet(uri);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);

		return result;
	}

	static public String getUpdateSignResultForHttpGet(String sign)
			throws ClientProtocolException, IOException {
		String path = HostIP + "user/updatesign.html?sign=";
		// 修改签名
		// http://open.zhizhi.com/user/updatesign.html?sign=hahah
		String uri = path + sign;
		String result = "";
		HttpResponse response = getHttpResponseResultForHttpGet(uri);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);

		return result;
	}

	static public String getFollowResultForHttpGet(int mid)
			throws ClientProtocolException, IOException {
		String path = HostIP + "user/follow.html?mid=";
		// 关注
		// http://open.zhizhi.com/user/follow.html?mid=2
		String uri = path + mid;
		String result = "";
		HttpResponse response = getHttpResponseResultForHttpGet(uri);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);

		return result;
	}

	static public String getMyFansResultForHttpGet(int page)
			throws ClientProtocolException, IOException {
		String path = HostIP + "user/getMyfans.html?page=";
		// 获取我的粉丝
		// http://open.zhizhi.com/user/getMyfans.html?page=1
		String uri = path + page;
		String result = "";
		HttpResponse response = getHttpResponseResultForHttpGet(uri);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);

		return result;
	}

	static public String getMyFollowResultForHttpGet(int page)
			throws ClientProtocolException, IOException {
		String path = HostIP + "user/getMyfollow.html?page=";
		// 获取我的关注
		// open.zhizhi.com/user/getMyfollow.html?page=1
		String uri = path + page;
		String result = "";
		HttpResponse response = getHttpResponseResultForHttpGet(uri);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);

		return result;
	}

	static public String getExitResultForHttpGet()
			throws ClientProtocolException, IOException {
		String path = HostIP + "member/logout.html";
		// logout退出
		// http://open.zhizhi.com/member/logout.html
		String uri = path;
		String result = "";
		HttpResponse response = getHttpResponseResultForHttpGet(uri);

		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);

		return result;
	}

	static HttpResponse getHttpResponseResultForHttpGet(String uri)
			throws ClientProtocolException, IOException {
		Log.i(TAG, "uri=" + uri.toString());
	
		HttpGet httpGet = new HttpGet((uri));// 编者按：与HttpPost区别所在，这里是将参数在地址中传递

		httpGet.addHeader("Host", Host);
		if (cookie != null) {
			httpGet.addHeader("Cookie", "PHPSESSID=" + cookie);
			Log.d(TAG, "PHPSESSID=" + cookie.toString());
		}
		httpGet.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; rv:11.0) Gecko/20100101 Firefox/11.0");
		return new DefaultHttpClient().execute(httpGet);
	}
	static void PrintList(List<NameValuePair> list) {
		for (int i = 0; i < list.size(); i++)
			Log.i(TAG, list.get(i).getName() + "=" + list.get(i).getValue().toString());
	}
	static public HttpResponse getReultForHttpPost(String uri, List<NameValuePair> list)
			throws ClientProtocolException, IOException {
		Log.i(TAG, "uri=" + uri.toString());
		PrintList(list);
		HttpPost httpPost = new HttpPost(uri);
		httpPost.addHeader("Host", Host);
		if (cookie != null) {
			httpPost.addHeader("Cookie", "PHPSESSID=" + cookie);
			Log.d(TAG, "PHPSESSID=" + cookie.toString());
		}
		// 与HttpGet区别所在，这里是将参数用List传递
		httpPost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
		return new DefaultHttpClient().execute(httpPost);
	}

	//
	static public String getSearchResultForHttpGet(String sort, String orderby,
			int page) throws ClientProtocolException, IOException {
		String path = HostIP + "user/search.html?sort=";
		// 查询教师，其中sort可用参数price,teachlevel,online,ring，orderby可用参数为desc倒序,asc争取
		// 返回json(data:查询结果教师列表,total:查询到的教师总数,online:查询到的并在线的教师)

		// http://open.zhizhi.com/user/search.html?sort=price&orderby=desc&page=1
		String uri = path + sort + "&orderby=" + orderby + "&page=" + page;
		String result = "";
		HttpResponse response = getHttpResponseResultForHttpGet(uri);

		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, HTTP.UTF_8);
		} else {
			result = "error";
		}
		Log.i(TAG, "result=" + result);
		return result;
	}

	   //
    static public String getSearchStudentResultForHttpGet(String sort, String orderby,
            int page) throws ClientProtocolException, IOException {
        String path = HostIP + "user/search.html?sort=";
        // 查询教师，其中sort可用参数price,teachlevel,online,ring，orderby可用参数为desc倒序,asc争取
        // 返回json(data:查询结果教师列表,total:查询到的教师总数,online:查询到的并在线的教师)

        // http://open.zhizhi.com/user/search.html?sort=price&orderby=desc&page=1
        String uri = path + sort + "&orderby=" + orderby + "&page=" + page + "&type=N";
        String result = "";
        HttpResponse response = getHttpResponseResultForHttpGet(uri);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, HTTP.UTF_8);
        } else {
            result = "error";
        }
        Log.i(TAG, "result=" + result);
        return result;
    }

	static public Bitmap getPersonImage(String uri) {
		try {
			URL url = new URL(uri); 
			url.getFile();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Cookie", "PHPSESSID=" + NetworkUtils.cookie);
			conn.setDoInput(true);
			conn.connect();
			InputStream inputStream = conn.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
			UploadFileUtils.savaBitmap(bitmap,url.getFile());
			return bitmap;
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param downloadUrl
	 *            图片下载链接
	 * @param savePath
	 *            保存路径
	 * @param Uuid
	 *            保存的文件名
	 */
	// 固定n个线程来执行下载
	private static ExecutorService executorService = Executors
			.newFixedThreadPool(10);
	public static void download(final String downloadUrl, String savePath,
			final String Uuid) {
		Log.d(TAG, "download()--in " + "downloadUrl="+downloadUrl + " savePath="+savePath + " Uuid="+Uuid);
//		 Log.d(TAG, "instanceCounter: " + instanceCounter);
		File saveDirectory = new File(savePath);
		if (!saveDirectory.exists())
			saveDirectory.mkdirs();

		final File saveFile = new File(savePath, Uuid);
		final File tmpFile = new File(savePath, Uuid + ".tmp");
		Log.d(TAG, "图片(" + Uuid + "),临时文件名为: " + tmpFile);

		if (!saveFile.exists() && !tmpFile.exists()) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					Log.d(TAG, "threadID: "
							+ Thread.currentThread().getName());
					try {
//						if (downloadUrl.contains(".baidu.com")) {
//							IMG_BAIDU = true;
//						}

						 Log.d(TAG, "downloadUrl: " +
						 downloadUrl);
						URL reqUrl = new URL(downloadUrl);
						HttpURLConnection conn = (HttpURLConnection) reqUrl
								.openConnection();
						conn.setConnectTimeout(5 * 1000);
						conn.setRequestProperty("Cookie", "PHPSESSID=" + NetworkUtils.cookie);
						conn.setRequestProperty("Host", NetworkUtils.Host);
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "*/*");
						conn.setRequestProperty("Accept-Language", "zh-CN");
						conn.setRequestProperty("Charset", "UTF-8");
//						if (IMG_BAIDU) {
//							conn.setRequestProperty("Referer",
//									"http://video.baidu.com");
//						}
						conn.connect();
						if (conn.getResponseCode() == 200) {
							Log.d(TAG, "200,downloadUrl: "
									+ conn.getURL().toString());
							InputStream is = conn.getInputStream();
							int length = (int) conn.getContentLength();
							Log.d(TAG, "图片(" + Uuid
									+ "),服务器端大小: " + length);
							if (length != -1) {
								byte[] imgData = new byte[length];
								byte[] temp = new byte[512];
								int readLen = 0;
								int destPos = 0;
								while ((readLen = is.read(temp)) > 0) {
									System.arraycopy(temp, 0, imgData, destPos,
											readLen);
									destPos += readLen;
								}
								Log.d(TAG, "图片(" + Uuid
										+ "),已下载 : " + imgData.length);
								RandomAccessFile finalFile = new RandomAccessFile(
										tmpFile, "rwd");
								finalFile.write(imgData);
								imgData = null;
								temp = null;
								finalFile.close();
								tmpFile.renameTo(saveFile);
								Log.d(TAG, "图片(" + Uuid
										+ ")下载完成,重命名 为: " + saveFile);
								is.close();
								Log.d(TAG, "图片(" + Uuid
										+ ")is.close(); ");
							} else {
								Log.d(TAG, "Unkown file size");
								throw new IOException("Unkown file size ");
							}

						} else {
							Log.d(TAG,
									"can't connect to server");
							throw new IOException("can't connect to server ");
						}

					} catch (IOException e) {
						e.printStackTrace();
						tmpFile.delete();
						saveFile.delete();
					}
				}
			});
		}// end if
		else {
			if (tmpFile.exists()) {
				Log.d(TAG, "文件正在在线,tmp文件存在！");
			} else {
				Log.d(TAG, "文件已经存在,不需要下载");
			}
		}
		Log.d(TAG, "download()--out ");
	}
}
