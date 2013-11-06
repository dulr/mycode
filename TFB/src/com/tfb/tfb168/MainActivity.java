package com.tfb.tfb168;

import com.tfb.tfb168.jinrong.JinRongActivity;
import com.tfb.tfb168.licai.LiCaiActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    TextView  mlogin, mtextview_popupmenu;
    ImageView mimageViewtfb_jinrong, mimageViewtfb_bianmin, mimageViewtfb_licai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainview);

        mlogin = (TextView) findViewById(R.id.Login_button);
        mlogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startNewActivity(LoginActivity.class);
            }
        });
        mtextview_popupmenu = (TextView) findViewById(R.id.textview_popupmenu);
        mtextview_popupmenu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                initPopupMenu(v);
            }
        });
        
        
        mimageViewtfb_jinrong = (ImageView) findViewById(R.id.imageViewtfb_jinrong);
        mimageViewtfb_jinrong.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startNewActivity(JinRongActivity.class);
            }
        });
        
        mimageViewtfb_bianmin = (ImageView) findViewById(R.id.imageViewtfb_bianmin);
        mimageViewtfb_bianmin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startNewActivity(BianMinActivity.class);
            }
        });
        
        mimageViewtfb_licai = (ImageView) findViewById(R.id.imageViewtfb_licai);
        mimageViewtfb_licai.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startNewActivity(LiCaiActivity.class);
            }
        });
        
        
//        // 取得GridView对象
//        GridView gridview = (GridView) findViewById(R.id.gridview);
//        // 添加元素给gridview
//        gridview.setAdapter(new GridViewImageAdapter(this));
//
//        // 设置Gallery的背景
//        gridview.setBackgroundResource(R.drawable.bg);
//
//        // 事件监听
//        gridview.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v,
//                    int position, long id) {
//                // Toast.makeText(
//                // MainActivity.this,
//                // "你选择了" + (position + 1) + " 号图片" + parent + " " + id
//                // + " ", Toast.LENGTH_SHORT).show();
//                InfoDialog dialog;Message msg;
//                switch (position) {
//                
//                case 0:
//                    startNewActivity(ReceiveMoney.class);
//                    break;
//                case 1:
//                    dialog = Utils.createInfoDialog(
//                            MainActivity.this, "请插入设备...");
//                    dialog.show();
//                      msg = new Message();
//                    msg.what = Cancel_dialog;
//                    msg.obj = dialog;
////                    mHandler.sendMessageDelayed(msg, 1500);
//                    break;
//                case 2:
//                    dialog = Utils.createInfoDialog(
//                            MainActivity.this, null, true);
//                    dialog.show();
//                      msg = new Message();
//                    msg.what = Cancel_dialog;
//                    msg.obj = dialog;
////                    mHandler.sendMessageDelayed(msg, 1500);
//                    break;
//                case 3:
//                    startNewActivity(CreditCardActivity.class);
//                    break;
//                case 4:
//                    startNewActivity(PhoneRechargeActivity.class);
//                    break;
//                case 5:
//                  Toast.makeText(
//                     MainActivity.this, " 正在建设中...", Toast.LENGTH_SHORT).show();
//                    break;  
//                case 6:
//                    
//                    startNewActivity(AccountActivity.class);
////                    Toast.makeText(
////                       MainActivity.this, " 帐户中心...", Toast.LENGTH_SHORT).show();
//                      break; 
//                case 7:startNewActivity(MoreActivity.class);
////                    Toast.makeText(
////                       MainActivity.this, " 更多...", Toast.LENGTH_SHORT).show();
////                      break; 
//                }
//            }
//
//            // public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//            // long arg3) {
//            // // TODO Auto-generated method stub
//            //
//            // }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    void startNewActivity(Class<?> a) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, a);
        startActivity(intent);
    }

    static final int Cancel_dialog = 999;
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {

            case Cancel_dialog:
                ((InfoDialog)msg.obj).dismiss();
                break;
            }
            ;

        };

    };

    
    void initPopupMenu(View v) {
        final OnMenuItemClickListener menuItemClickListener= new OnMenuItemClickListener() {
            // Switch Hold/Active state if the user selects Hold or Resume in the Pop up menu
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1:
//                    case R.id.options_menu_resume_call:
//                      
//                        return true;
//                    case R.id.options_menu_start_record_call:
//                      
//                        return true;
//                    case R.id.options_menu_stop_record_call:
//                        return true;
//                    case R.id.options_menu_equalizer:
//                        showEqualizerDialog();
//                        return true;
//                    case R.id.options_menu_slow_talk:
                        return true;
                    default:
                        return false;
                
                }
            }
        };
       PopupMenu mPopupMenu = new  PopupMenu(this, v);
//       mPopupMenu = new PopupMenu(this, v);
       mPopupMenu.setOnMenuItemClickListener(menuItemClickListener);
       MenuInflater inflater = mPopupMenu.getMenuInflater();
       Menu menu = mPopupMenu.getMenu();
       inflater.inflate(R.menu.menu_mainview, menu);
//       setMenuBackGround();
       mPopupMenu.show();
    }
    
    
    protected void setMenuBackGround() {
        getLayoutInflater().setFactory(
                new android.view.LayoutInflater.Factory() {
                    @Override
                    public View onCreateView(String name, Context context,
                            AttributeSet attrs) {
                        // TODO Auto-generated method stub
                        // 指定自定义inflater的对象
                        if (name.equalsIgnoreCase("com.android.internal.view.menu.ListMenuItemView")) {
                            try {
                                LayoutInflater inflater = getLayoutInflater();
                                final View view = inflater.createView(name,
                                        null, attrs);
                                new Handler().post(new Runnable() {
                                    public void run() {
                                        view.setBackgroundResource(R.drawable.sy2_01);
                                    }
                                });
                                return view;
                            } catch (InflateException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }

                   
                });
    }
}
