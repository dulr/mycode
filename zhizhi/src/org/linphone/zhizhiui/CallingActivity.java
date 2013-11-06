package org.linphone.zhizhiui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.linphone.DialerActivity;
import org.linphone.LinphoneService;
import com.zhizhi.R;
import org.linphone.VideoCallActivity;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCall.State;
import org.linphone.zhizhiui.data.CommentsNetworkUtils;
import org.linphone.zhizhiui.data.MainViewListData;
import org.linphone.zhizhiui.data.UploadFileUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CallingActivity extends Activity {
    private static CallingActivity theDialer;

    public static CallingActivity getDialer() {
        if (theDialer == null) {
            return null;
        } else {
            return theDialer;
        }
    }

    ImageView ImageViewPersonImage;

    void initPersonPic(int mmid) {
        ImageViewPersonImage = (ImageView)findViewById(R.id.ImageViewPersonImage);
        try {
            File file = new File(UploadFileUtils.filename, mmid + ".zz");
            if (file.exists()) {
                FileInputStream stream = new FileInputStream(file);
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                ImageViewPersonImage.setImageBitmap(bitmap);
                stream.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    void callPending(final LinphoneCall call) {
        String name = call.getRemoteAddress().getUserName();
        textViewCallName.setText(name);
        if(MainViewActivity.mListAllData != null)
        for(MainViewListData data : MainViewActivity.mListAllData) {
            if(data.mPersonName.equals(name)) {
//                if(data.SIPNumber.equals(name)) {
                initPersonPic(data.mMid);
                textViewCallName.setText(data.mPersonName);
                break;
            }
        }
        if(MainViewActivity.mListAllStudentData != null)
        for(MainViewListData data : MainViewActivity.mListAllStudentData) {
            if(data.SIPNumber.equals(name)) {
                initPersonPic(data.mMid);
                textViewCallName.setText(data.mPersonName);
                break;
            }
        }
    }
    public void callState(final LinphoneCore lc, final LinphoneCall call, final State state,
            final String message) {

        if (state == LinphoneCall.State.OutgoingInit) {
            // enterIncalMode(lc);
            // routeAudioToReceiver();
        } else if (state == LinphoneCall.State.IncomingReceived) {
            // resetCameraFromPreferences();
             callPending(call);

        } else if (state == LinphoneCall.State.Connected) {
            // enterIncalMode(lc);
            textViewCallState.setText("正在通话中");
            textViewCallTime.setText("00:00");
            handler.sendEmptyMessageDelayed(1000000, 1000);
        } else if (state == LinphoneCall.State.Error) {
            // if (mWakeLock.isHeld()) mWakeLock.release();
            // Toast toast = Toast.makeText(this
            // ,String.format(getString(R.string.call_error),message)
            // , Toast.LENGTH_LONG);
            // toast.show();
            // exitCallMode(); 
        	textViewCallState.setText("通话结束");    
        try {
			CommentsNetworkUtils.callResultForHttpGet(mid+"", DateUtils.formatElapsedTime(mCalltime));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
            finish();
        } else if (state == LinphoneCall.State.CallEnd) {
            // exitCallMode();
            textViewCallState.setText("通话结束");                try {
				CommentsNetworkUtils.callResultForHttpGet(mid+"", DateUtils.formatElapsedTime(mCalltime));
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            finish();
        } else if (state == LinphoneCall.State.StreamsRunning) {
            // if
            // (LinphoneService.instance().getLinphoneCore().getCurrentCall().getCurrentParamsCopy().getVideoEnabled())
            // {
            // if (!VideoCallActivity.launched) {
            // startVideoView(VIDEO_VIEW_ACTIVITY);
            // }
            // }
        }

    }

    int mid;
    int mCalltime = 0;
    TextView textViewCallState;
    TextView textViewCallName;
    TextView textViewCallTime;

    void initView() {
        textViewCallState = (TextView)findViewById(R.id.textViewCallState);
        textViewCallName = (TextView)findViewById(R.id.textViewCallName);
        textViewCallTime = (TextView)findViewById(R.id.textViewCallTime);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callingactivity);
        initView();
        mid = getIntent().getIntExtra("mid", 1);
        String name = getIntent().getStringExtra("name");
        textViewCallName.setText(name);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, LayoutParams.TYPE_SYSTEM_ERROR,
                LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        theDialer = this;
        initPersonPic(mid);
        // getWindow().addContentView(Calling, layoutParams);
        boolean showAnswer = getIntent().getBooleanExtra("showAnswer", false);

        final Button Answer = (Button)findViewById(R.id.buttonAnswer);
        if (showAnswer)
            Answer.setVisibility(View.VISIBLE);
        Answer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                LinphoneCore lLinphoneCore = LinphoneService.instance().getLinphoneCore();
                if (lLinphoneCore.isInComingInvitePending()) {
                    try {
                        lLinphoneCore.acceptCall(lLinphoneCore.getCurrentCall());
                        Answer.setVisibility(View.GONE);
                    } catch (LinphoneCoreException e) {
                        lLinphoneCore.terminateCall(lLinphoneCore.getCurrentCall());
                        // Toast toast = Toast.makeText(DialerActivity.this
                        // ,String.format(getString(R.string.warning_wrong_destination_address),mAddress.getText().toString())
                        // ,Toast.LENGTH_LONG);
                        // toast.show();
                        finish();
                    }
                    return;
                }
            }
        });

        Button guaDuanDianhua = (Button)findViewById(R.id.buttonGuaDuanDianhua);
        guaDuanDianhua.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                LinphoneCore lLinphoneCore = LinphoneService.instance().getLinphoneCore();
                lLinphoneCore.terminateCall(lLinphoneCore.getCurrentCall());
                try {
					CommentsNetworkUtils.callResultForHttpGet(mid+"", DateUtils.formatElapsedTime(mCalltime));
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
                Intent intent = new Intent();
                intent.setClass(CallingActivity.this, CallEndActivity.class);
                intent.putExtra("calltime", DateUtils.formatElapsedTime(mCalltime));
                startActivity(intent);
                finish();
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
            case 1000000:
                mCalltime ++;
                textViewCallTime.setText(DateUtils.formatElapsedTime(mCalltime));
                handler.sendEmptyMessageDelayed(1000000, 1000);
                break;
            }
        }
    };
}
