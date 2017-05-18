package com.gdz.demo.gdnews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdz.demo.gdnews.R;
import com.gdz.demo.gdnews.adapter.NewsRecyclerViewAdapter;
import com.gdz.demo.gdnews.data.NewsEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class BaseFragment extends Fragment {
    private RecyclerView  mRecyclerView;
    private View view ;
    private ArrayList<NewsEntity.ResultBean.DataBean> mDataBean;
   private NewsRecyclerViewAdapter mViewAdapter;
    //每次点击fragment Item 时都会调用oncreateView方法
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if( view==null){
            view = inflater.inflate(R.layout.basefragment,container,false);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleview);
            //设置布局管理器
            Context context = getContext();
            LinearLayoutManager llm = new LinearLayoutManager(context);
            mRecyclerView.setLayoutManager(llm);
            mViewAdapter = new NewsRecyclerViewAdapter(context,mDataBean);
            mRecyclerView.setAdapter(mViewAdapter);
        }else{
            mViewAdapter.setData(mDataBean);
            Log.i("BaseFragment", "onCreateView: ");
        }

        return view;
    }

    public void setAdapterData(ArrayList<NewsEntity.ResultBean.DataBean> mDataBean){
        Log.i("BaseFragment", "setAdapterData: ");
        this.mDataBean =mDataBean;
       if(mViewAdapter!=null){
            mViewAdapter.setData(mDataBean);
        }
    }

}
