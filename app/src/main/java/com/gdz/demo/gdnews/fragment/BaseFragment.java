package com.gdz.demo.gdnews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gdz.demo.gdnews.R;
import com.gdz.demo.gdnews.adapter.NewsRecyclerViewAdapter;
import com.gdz.demo.gdnews.data.NewsEntity;
import com.gdz.demo.gdnews.utils.Constant;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class BaseFragment extends Fragment {
    private RecyclerView  mRecyclerView;
    private View view ;
    private ArrayList<NewsEntity.ResultBean.DataBean> mDataBean;
   private NewsRecyclerViewAdapter mViewAdapter;
    private int mType;
    private Handler mHandler ;
    private  NewsEntity mNewsEntity;
    //每次点击fragment Item 时都会调用oncreateView方法
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         String uri = Constant.matchUri(mType);
        inithandler();
        getNetNewsEntity(uri);
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

    public void setType(int type) {
        mType = type;
    }
    //网络请求新闻实体
    public void getNetNewsEntity(String uri) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(uri)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
              getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),"请求网页失败",Toast.LENGTH_SHORT).show();
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

    private void inithandler() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what ==1002){
                    ArrayList<NewsEntity.ResultBean.DataBean> data = (ArrayList<NewsEntity.ResultBean.DataBean>) mNewsEntity.getResult().getData();
                    if(data!=null){
                        Log.i("MainActivity", "handleMessage: "+data.get(0).getTitle());
                    }
                   setAdapterData(data);
                    return  true;
                }
                return false;
            }
        });
    }
}
