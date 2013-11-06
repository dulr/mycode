package com.zhihui.zhihuicity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zhihui.zhihuicity.ctrl.InfoCatListAdapter;
import com.zhihui.zhihuicity.ctrl.MainViewListAdapter;
import com.zhihui.zhihuicity.ctrl.MyGallery;
import com.zhihui.zhihuicity.ctrl.XListView;
import com.zhihui.zhihuicity.ctrl.XListView.IXListViewListener;
import com.zhihui.zhihuicity.ctrl.XListViewFooter;
import com.zhihui.zhihuicity.data.InfoCatBean;
import com.zhihui.zhihuicity.data.MainViewListItemData;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity implements IXListViewListener {
    private List<InfoCatBean> infoCatListTemp;
    MyGallery mMygallery;

    void initMyGallery() {
        infoCatListTemp = new ArrayList<InfoCatBean>();
        InfoCatBean localInfoCatBean2 = new InfoCatBean();
        localInfoCatBean2.id = (0);
        localInfoCatBean2.name = "播报";
        localInfoCatBean2.sort_order = (0);
        infoCatListTemp.add(localInfoCatBean2);
        localInfoCatBean2 = new InfoCatBean();
        localInfoCatBean2.id = (0);
        localInfoCatBean2.name = "心灵";
        localInfoCatBean2.sort_order = (0);
        infoCatListTemp.add(localInfoCatBean2);
        localInfoCatBean2 = new InfoCatBean();
        localInfoCatBean2.id = (0);
        localInfoCatBean2.name = "健康";
        localInfoCatBean2.sort_order = (0);
        infoCatListTemp.add(localInfoCatBean2);
        localInfoCatBean2 = new InfoCatBean();
        localInfoCatBean2.id = (0);
        localInfoCatBean2.name = "汽车";
        localInfoCatBean2.sort_order = (0);
        infoCatListTemp.add(localInfoCatBean2);
        localInfoCatBean2 = new InfoCatBean();
        localInfoCatBean2.id = (0);
        localInfoCatBean2.name = "头条";
        localInfoCatBean2.sort_order = (0);
        infoCatListTemp.add(localInfoCatBean2);
        localInfoCatBean2 = new InfoCatBean();
        localInfoCatBean2.id = (0);
        localInfoCatBean2.name = "全部";
        localInfoCatBean2.sort_order = (0);
        infoCatListTemp.add(localInfoCatBean2);

        InfoCatListAdapter localInfoCatListAdapter2 = new InfoCatListAdapter(
                MainActivity.this, MainActivity.this.infoCatListTemp, false);
        localInfoCatListAdapter2.setSelectedTab(1000);
        // localInfoCatListAdapter2.
        this.mMygallery = ((MyGallery) findViewById(R.id.gallery));
        this.mMygallery.setOnItemClickListener(this.onItemClickListener);

        mMygallery.setAdapter(localInfoCatListAdapter2);
        mMygallery.setSelection(1000);
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> paramAdapterView,
                View paramView, int paramInt, long paramLong) {
            ((InfoCatListAdapter) paramAdapterView.getAdapter())
                    .setSelectedTab(paramInt);
            if ((paramView.getTag() instanceof InfoCatBean)) {
                InfoCatBean localInfoCatBean = (InfoCatBean) paramView.getTag();
                // IndexActivity.this.currentCat = localInfoCatBean;
                // IndexActivity.this.changeTab(localInfoCatBean.getId());
            }
        }
    };

    private XListView mListView;
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;

    private MainViewListAdapter mAdapter;
    private ArrayList<MainViewListItemData> items = new ArrayList<MainViewListItemData>();

    void initListView() {
        geneItems();
        mListView = (XListView) findViewById(R.id.xListView);
        mListView.setPullLoadEnable(true);
        mAdapter = new MainViewListAdapter(this, items, true);
        mListView.setAdapter(mAdapter);

        // mListView.setPullLoadEnable(false);
        // mListView.setPullRefreshEnable(false);
        mListView.setXListViewListener(this);
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                MainViewListItemData item =(MainViewListItemData) mListView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        item.mTitle+"  "+item.mContent,
                        Toast.LENGTH_LONG).show();
            }
        });
        mHandler = new Handler();
    }

    private void geneItems() {
        for (int i = 0; i != 20; ++i) {
            MainViewListItemData data = new MainViewListItemData();
            data.mTitle = "aa" + i;
            data.mContent = "bbbbbb" + i;
            data.mImageUri = "http://e.hiphotos.baidu.com/album/w%3D2048/sign=e5c0ca193ac79f3d8fe1e3308e99cf11/7a899e510fb30f246e5a63e5c995d143ac4b0317.jpg";
            items.add(data);
        }
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        java.text.DateFormat format1 = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String s = format1.format(new Date());
        mListView.setRefreshTime(s);
        // mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = ++refreshCnt;
                items.clear();
                geneItems();
                // mAdapter.notifyDataSetChanged();
                mAdapter = new MainViewListAdapter(MainActivity.this, items,
                        true);
                mListView.setAdapter(mAdapter);
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_main);
        initTitleBar();

        initMyGallery();
        initListView();
        initRadioButtons();

        // mMygallery.setVisibility(View.GONE);
        // mRadioGroup.setVisibility(View.GONE);
    }

    RadioGroup mRadioGroup;

    void initRadioButtons() {
        mRadioGroup = (RadioGroup) this
                .findViewById(R.id.mainview_radiobutton_group);
        // RadioButton b1=(RadioButton)findViewById(R.id.radiobutton_1);
        // // b1.setChecked(true);
        // b1.setBackgroundResource(R.drawable.botton_selected);
        // 绑定一个匿名监听器13
        mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                // TODO Auto-generated method stub18
                // 获取变更后的选中项的ID19
                int radioButtonId = arg0.getCheckedRadioButtonId();

                switch (radioButtonId) {
                case R.id.radiobutton_1:
                    onClickShouYe();
                    break;
                case R.id.radiobutton_2:
                    onClickShangPin();
                    break;
                case R.id.radiobutton_3:
                    onClickHuangYe();
                    break;
                case R.id.radiobutton_4:
                    onClickHuiYuan();
                    break;
                case R.id.radiobutton_5:
                    onClickMore();
                    break;
                }
                // 根据ID获取RadioButton的实例21
                RadioButton rb = (RadioButton) MainActivity.this
                        .findViewById(radioButtonId);
                // //更新文本内容，以符合选中项23
                Toast.makeText(getApplicationContext(), rb.getText(),
                        Toast.LENGTH_LONG).show();
                // tv.setText("您的性别是：" + rb.getText());
            }
        });

    }

    RelativeLayout mtop_bar_1;
    TextView mtextView_Title;
    ImageView mimageView_login;

    RelativeLayout mtop_bar_2;
    TextView mimageViewtrade_left_button, mimageViewtrade_right_button;

    void initTitleBar() {
        mtop_bar_1 = ((RelativeLayout) findViewById(R.id.top_bar_1));
        mtextView_Title = ((TextView) findViewById(R.id.textView_Title));
        mimageView_login = ((ImageView) findViewById(R.id.imageView_login));

        mtop_bar_2 = ((RelativeLayout) findViewById(R.id.top_bar_2));
        mimageViewtrade_right_button = ((TextView) findViewById(R.id.imageViewtrade_right_button));
        mimageViewtrade_left_button = ((TextView) findViewById(R.id.imageViewtrade_left_button));
    }

    void onClickShouYe() {
        showTitleBar1("首页");
        mimageView_login.setVisibility(View.VISIBLE);
    }

    void onClickShangPin() {
        showTitleBar2("商品");
        mimageView_login.setVisibility(View.GONE);
    }

    void onClickHuangYe() {
        showTitleBar1("黄页");
        mimageView_login.setVisibility(View.GONE);
    }

    void onClickHuiYuan() {
        showTitleBar1("会员");
        mimageView_login.setVisibility(View.GONE);
    }

    void onClickMore() {
        showTitleBar1("登陆");
        mimageView_login.setVisibility(View.GONE);
    }

    void showTitleBar1(String title) {
        mtop_bar_1.setVisibility(View.VISIBLE);
        mtop_bar_2.setVisibility(View.GONE);
        mtextView_Title.setText(title);
    }

    void showTitleBar2(String title) {
        mtop_bar_2.setVisibility(View.VISIBLE);
        mtop_bar_1.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
