package com.gdz.demo.gdnews.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.gdz.demo.gdnews.R;
import com.gdz.demo.gdnews.adapter.MyPagerAdapter;
import com.gdz.demo.gdnews.data.NewsEntity;
import com.gdz.demo.gdnews.fragment.BaseFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;
    private ArrayList<Fragment> mFragments;
    private Handler mHandler ;
    private  NewsEntity mNewsEntity;
    private static final String ADDR = "http://v.juhe.cn/toutiao/index";
    private static  final String SCRECT_KEY ="285117f66d7f35bbd2df9fbc21015cef";//openId
  //  类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    private String[] titles = new String[]{"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "onCreate: " );
        inithandler();
        findViews();
        initFragmentArrays();
        baseConfigs();
      getNetNewsEntity();
    }

    private void inithandler() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what ==1002){
                    Log.i("MainActivity", "handleMessage: "+mNewsEntity.getResult().getData().toString());
                    BaseFragment baseFragment = (BaseFragment) mFragments.get(0);
                    ArrayList<NewsEntity.ResultBean.DataBean> data = (ArrayList<NewsEntity.ResultBean.DataBean>) mNewsEntity.getResult().getData();
                    if(data!=null){
                        Log.i("MainActivity", "handleMessage: "+data.get(0).getTitle());
                    }
                    baseFragment.setAdapterData(data);
                    return  true;
                }

                return false;
            }
        });
    }

    //配置基本信息pageradapter  viewpager tablayout
    private void baseConfigs() {
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),mFragments,titles);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mPagerAdapter);
    }
    //创建每个fragment实例
    private void initFragmentArrays() {
        mFragments = new ArrayList<>();
        for(int i=0;i<titles.length;i++){
            BaseFragment basefragment = new BaseFragment();
            mFragments.add(basefragment);
        }
    }
     //绑定组件
    private void findViews() {
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }
    //网络请求新闻实体
    public void getNetNewsEntity() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ADDR+"?type=top&key="+SCRECT_KEY)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"请求网页失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBodyStr = response.body().string();
                Log.i("MainActivity", "onResponse: "+responseBodyStr);
                Gson gson = new Gson();
                mNewsEntity= gson.fromJson(responseBodyStr,NewsEntity.class);
                Message msg = new Message();
                msg.what = 1002;
                mHandler.sendMessage(msg);
            }
        });
    }
}
