package com.example.zjtproject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.example.zjtproject.network.Base64;
import com.example.zjtproject.network.DES;
import com.example.zjtproject.network.SuijiShu;
import com.example.zjtproject.network.UserApi;
import com.example.zjtproject.utils.Utils;


import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

public class PersonInfoActivity extends Activity
{
    final String TAG = "PersonInfoActivity";
	TextView mPersonInfor_back,mPersonInfor_register,mtextViewCancel;
	ImageView mimageViewheader,mimageViewCancel;
	
	ImageView mimageViewcamera_btn,mimageViewalbum_btn;
	RelativeLayout mimageViewheadSelect,mRelativeLayoutcamera,mRelativeLayoutalbum;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_infor);
        
        mimageViewheadSelect = (RelativeLayout) findViewById(R.id.imageViewheadSelect);
        mPersonInfor_back  = (TextView) findViewById(R.id.PersonInfor_back);
        mPersonInfor_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        });
        
        mPersonInfor_register  = (TextView) findViewById(R.id.PersonInfor_register);
        mPersonInfor_register.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();                
				intent.setClass(PersonInfoActivity.this, RegistActivity.class);                
				startActivity(intent);
			}
        	
        });
        
        //����ͷ��
        mimageViewheader = (ImageView) findViewById(R.id.imageViewMainheader);
        mimageViewheader.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mimageViewheadSelect.setVisibility(View.VISIBLE);
			}
        });
        
        //����
        mRelativeLayoutcamera  = (RelativeLayout) findViewById(R.id.RelativeLayoutcamera);
        mRelativeLayoutcamera.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        //���ѡ��
        mRelativeLayoutalbum  = (RelativeLayout) findViewById(R.id.RelativeLayoutalbum);
        mRelativeLayoutalbum.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        //取消
        mtextViewCancel= (TextView) findViewById(R.id.textViewCancel);
        mtextViewCancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mimageViewheadSelect.setVisibility(View.GONE);
			}
        });
       
        
        //手机拍照
        mimageViewcamera_btn = (ImageView) findViewById(R.id.imageViewcamera_btn);
        mimageViewcamera_btn.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    getPicFromCapture();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
        });
        
        //手机相册
        mimageViewalbum_btn = (ImageView) findViewById(R.id.imageViewalbum_btn);
        mimageViewalbum_btn.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                getPicFromGallery();
                }
        });
        
    }
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(mimageViewheadSelect.getVisibility()==View.VISIBLE)
			mimageViewheadSelect.setVisibility(View.GONE);
		else
			
		super.onBackPressed();
	}
    
    
	// 取照片
	// 调用系统拍照
    void getPicFromCapture() throws IOException {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String TEMP_TAKE_PHOTO_FILE_PATH = "sdcard";
        File myImageDir = new File(TEMP_TAKE_PHOTO_FILE_PATH);
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED))// sd存在并可写
            // 创建图片保存目录
            if (!myImageDir.exists()) {
                myImageDir.mkdirs();
            }

        // 根据时间来命名
        File imagFile = File.createTempFile("" + System.currentTimeMillis(),
                ".jpg", myImageDir);

        tmpuri = Uri.fromFile(imagFile);

        i.putExtra(MediaStore.EXTRA_OUTPUT, tmpuri);

        startActivityForResult(i, TAKE_PHOTO_REQUEST_CODE);
    }

    // 从图库选择图片
    void getPicFromGallery() {
        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT); // "android.intent.action.GET_CONTENT"

        innerIntent.setType("image/*"); // 查看类型

        // StringIMAGE_UNSPECIFIED="image/*";详细的类型在com.google.android.mms.ContentType中

        Intent wrapperIntent = Intent.createChooser(innerIntent, null);

        startActivityForResult(wrapperIntent, SELECT_PHOTO_REQUEST_CODE);
    }

    private static Uri tmpuri;
    static int TAKE_PHOTO_REQUEST_CODE = 1;
    static int SELECT_PHOTO_REQUEST_CODE = 2;
    static int CUT_PHOTO_REQUEST_CODE = 3;
    Bitmap uploadBm = null;
    // 返回后接收并调用系统裁剪工具

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent intent) {
        if (requestCode == TAKE_PHOTO_REQUEST_CODE
                || requestCode == SELECT_PHOTO_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                Uri uri = null;
                if (requestCode == SELECT_PHOTO_REQUEST_CODE) {
                    uri = intent.getData();
                } else if (requestCode == TAKE_PHOTO_REQUEST_CODE) {
                    uri = tmpuri;
                }
                Utils.Logi(TAG, uri.toString());
                if (uri != null) {

                    final Intent intent1 = new Intent(
                            "com.android.camera.action.CROP");
                    intent1.setDataAndType(uri, "image/*");
                    intent1.putExtra("crop", "true");
                    intent1.putExtra("aspectX", 1);
                    intent1.putExtra("aspectY", 1);
                    intent1.putExtra("outputX", 256);
                    intent1.putExtra("outputY", 256);
                    intent1.putExtra("return-data", true);
                    startActivityForResult(intent1,
                            CUT_PHOTO_REQUEST_CODE);

                }
            }
        } else if (requestCode == CUT_PHOTO_REQUEST_CODE) {
            if (resultCode == RESULT_OK && intent != null) {

                Uri uri = intent.getData();
                uploadBm = intent.getParcelableExtra("data");
                mimageViewheader.setImageBitmap(uploadBm);
                new Thread() {
                    public void run() {
                        try {
                            List<NameValuePair> list = new ArrayList<NameValuePair>();
                            list.add(new BasicNameValuePair(
                                    "Cookie",
                                    DES.encryptDES(
                                            SuijiShu.getToken(MainActivity.mTimediffwithserver),
                                            DES.KEY)));
                            byte[] byteArray = Bitmap2Bytes(uploadBm);
                            String byte64Array = Base64.encode(byteArray);
                            list.add(new BasicNameValuePair("FaceData", byte64Array));
                            UserApi.UploadFace(list);
                        } catch (ClientProtocolException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
                if (tmpuri != null) {
                    File myImageDir = new File(
                            tmpuri.getPath());
                    Utils.Logi(TAG, "myImageDir.exists()"
                            + myImageDir.exists());
                    if (myImageDir.exists())
                        myImageDir.delete();
                }
            }

        }
    }
    
    // 取照片
}
