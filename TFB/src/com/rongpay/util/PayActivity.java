package com.rongpay.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.rongpay.config.RongpayConfig;
import com.rongpay.util.RongpayService;
import com.tfb.tfb168.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class PayActivity extends Activity {
    WebView mWebView;

    String mTitle;
    String mbody,mtotal_fee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        mTitle = getIntent().getStringExtra("title");
        mbody = getIntent().getStringExtra("body");
        mtotal_fee = getIntent().getStringExtra("total_fee");
        
//        getOilList();
        mWebView = (WebView) findViewById(R.id.wv);
        mWebView.getSettings().setJavaScriptEnabled(true); 
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.loadUrl(payUrl());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // 重写方法 设置Activity的进度条
                PayActivity.this.setProgress(newProgress * 100);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode,
                    String description, String failingUrl) {
                // 重写 onReceivedError方法 创建Toast并显示
                Toast.makeText(PayActivity.this, "Sorry!" + description,
                        Toast.LENGTH_LONG).show();
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
    }

    String payUrl() {
        String charset = RongpayConfig.charset;
        String sign_type = RongpayConfig.sign_type;
        String seller_email = RongpayConfig.seller_email;
        String merchant_ID = RongpayConfig.merchant_ID;
        String key = RongpayConfig.key;
        String notify_url = RongpayConfig.notify_url;
        String return_url = RongpayConfig.return_url;
        String service = RongpayConfig.service;
        String payment_type = RongpayConfig.payment_type;

        String order_no = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new java.util.Date());

        // 订单名称，显示在融宝支付收银台里的“商品名称”里，显示在融宝支付的交易管理的“商品名称”的列表里。
       // String title = "aaaaa";
        String title = mTitle;//"aaaaa";// new
                               // String(request.getParameter("subject").getBytes("ISO-8859-1"),"GBK");

        // 订单描述、订单详细、订单备注，显示在融宝支付收银台里的“商品描述”里
//        String body = "bbbbbb";
        String body = mbody;//"bbbbbb";// new
                               // String(request.getParameter("body").getBytes("ISO-8859-1"),"GBK");
        System.out.println(body);

        // 订单总金额，显示在融宝支付收银台里的“应付总额”里
        String total_fee = mtotal_fee;//mtotal_fee"100";// request.getParameter("Rongmoney");

        // 默认买家融宝支付账号
//         String buyer_email = "game211@126.com";
        String buyer_email = "";
        // 支付方式
        String paymethod = "";

        // 网银代码
        String defaultbank = "No";// =request.getParameter("defaultbank");
        if ("No".equals(defaultbank)) {
            paymethod = "bankPay";
            defaultbank = "";
        } else {
            paymethod = "directPay";
        }

        // //构造函数，生成请求URL
        String sHtmlText = RongpayService.BuildForm(service, payment_type,
                merchant_ID, seller_email, return_url, notify_url, order_no,
                title, body, total_fee, buyer_email, charset, paymethod,
                defaultbank, key, sign_type);
        Log.i("aaaaa", sHtmlText);
        return sHtmlText;
    }

    void getOilList() {
        new Thread() {
            public void run() {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                // 提交HTTP GET请求
                // 向url添加请求参数
                // 第1步：创建HttpGet对象
                HttpGet httpGet = new HttpGet(payUrl());
                // 第2步：使用execute方法发送HTTP GET请求，并返回HttpResponse对象
                HttpResponse httpResponse = null;
                try {
                    httpResponse = new DefaultHttpClient().execute(httpGet);
                
                // 判断请求响应状态码，状态码为200表示服务端成功响应了客户端的请求
                if (((HttpResponse) httpResponse).getStatusLine()
                        .getStatusCode() == 200) {
                    // 第3步：使用getEntity方法获得返回结果
                    String result;
                    
                        result = EntityUtils.toString(httpResponse.getEntity());
                        Log.i("aaaaa", result);
//                        mWebView.loadUrl(result);
//                        mWebView.
                    }
                }catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    // 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格

                

            }
        }.start();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
