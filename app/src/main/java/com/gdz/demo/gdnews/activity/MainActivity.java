package com.gdz.demo.gdnews.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gdz.demo.gdnews.R;
import com.gdz.demo.gdnews.adapter.MyPagerAdapter;
import com.gdz.demo.gdnews.fragment.BaseFragment;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;

import static com.gdz.demo.gdnews.utils.Constant.titles;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(getApplicationContext(),"e8169f6b908c89b44c95bd13a2e01d72");
        findViews();
        initFragmentArrays();
        baseConfigs();

    }

    //配置基本信息pageradapter  viewpager tablayout
    private void baseConfigs() {
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),mFragments,titles);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(10);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mPagerAdapter);
    }
    //创建每个fragment实例
    private void initFragmentArrays() {
        mFragments = new ArrayList<>();
        for(int i=0;i< titles.length;i++){
            BaseFragment basefragment = new BaseFragment();
            basefragment.setType(i);
            mFragments.add(basefragment);

        }
    }
     //绑定组件
    private void findViews() {
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }

}
