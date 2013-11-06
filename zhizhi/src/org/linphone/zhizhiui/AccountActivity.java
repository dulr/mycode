
package org.linphone.zhizhiui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.linphone.zhizhiui.data.JsonParser;
import org.linphone.zhizhiui.data.MyInfoData;
import org.linphone.zhizhiui.data.StaInfoData;
import org.linphone.zhizhiui.data.UploadFileUtils;
import org.linphone.zhizhiui.data.UserNetworkUtils;

import com.zhizhi.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountActivity extends Activity {

    TextView textViewuserName;

    TextView textViewUserBianhao;

    TextView textViewPhoneNumber;
    TextView textViewAmount;


    MyInfoData mMyInfoData;
    StaInfoData mStaInfoData;
    ImageView personImageDetail;

    public void tryToGetMyInfo() {
        try {
            String str = UserNetworkUtils.getMyInfoResultForHttpGet();
            mMyInfoData = JsonParser.ParserMyInfoData(str);

            str = UserNetworkUtils.getStaListResultForHttpGet();
            mStaInfoData = JsonParser.ParserStaInfoData(str);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void initTextView() {
        textViewuserName = (TextView)findViewById(R.id.textViewuserName);
        textViewUserBianhao = (TextView)findViewById(R.id.textViewUserBianhao);
        textViewPhoneNumber = (TextView)findViewById(R.id.textViewPhoneNumber);
        textViewAmount = (TextView)findViewById(R.id.textViewAmount);
        personImageDetail = (ImageView)findViewById(R.id.personImageDetail);

        try {
            File file = new File(UploadFileUtils.filename, mMyInfoData.mMid + ".zz");
            if (file.exists()) {
                FileInputStream stream = new FileInputStream(file);
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                personImageDetail.setImageBitmap(bitmap);
                stream.close();
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        textViewuserName.setText(mMyInfoData.mName);
        textViewUserBianhao.setText("编号：" + mMyInfoData.mMid);
        textViewPhoneNumber.setText(mMyInfoData.mPhoneNumber);
        textViewAmount.setText(mStaInfoData.mamount  + "元");
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountactivity);
        tryToGetMyInfo();
        initTextView();

        Button buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 充值
        Button buttonChongZhi2 = (Button)findViewById(R.id.buttonChongZhi);
        buttonChongZhi2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AccountActivity.this, ChongzhiActivity.class);
                startActivity(intent);
            }
        });

        // 提现
        Button buttonTixian2 = (Button)findViewById(R.id.buttonTixian);
        buttonTixian2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AccountActivity.this, TixianActivity.class);
                startActivity(intent);
            }
        });

        // 交易明细
        Button buttonJiaoyiMingxi2 = (Button)findViewById(R.id.buttonJiaoyiMingxi);
        buttonJiaoyiMingxi2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AccountActivity.this, JiaoyiMingxiActivity.class);
                startActivity(intent);
            }
        });

        // 设置银行信息
        Button buttonShezhiYinhangXinxi2 = (Button)findViewById(R.id.buttonShezhiYinhangXinxi);
        buttonShezhiYinhangXinxi2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AccountActivity.this, BankInfoActivity.class);
                startActivity(intent);
            }
        });

        // 设置支付密码
        Button buttonShezhiZhifuMima2 = (Button)findViewById(R.id.buttonShezhiZhifuMima);
        buttonShezhiZhifuMima2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AccountActivity.this, ModifyPayPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
