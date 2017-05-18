package com.gdz.demo.gdnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mfragments;
    private String[] titles;
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public MyPagerAdapter(FragmentManager fm,ArrayList<Fragment> mfragments,String[] titles) {
        super(fm);
        this.mfragments =mfragments;
        this.titles =titles;
    }

    @Override
    public int getCount() {
        if(mfragments!=null){
            return mfragments.size();
        }
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        if(mfragments!=null&&mfragments.size()>0){
            return mfragments.get(position);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
