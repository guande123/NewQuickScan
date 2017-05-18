package com.gdz.demo.gdnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gdz.demo.gdnews.R;
import com.gdz.demo.gdnews.data.NewsEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {
    private ArrayList<NewsEntity.ResultBean.DataBean> mDataBean;
    private Context mContext;
    public NewsRecyclerViewAdapter(Context context, ArrayList<NewsEntity.ResultBean.DataBean> mDataBean){
        this.mDataBean =mDataBean;
        mContext = context;
    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item_layout 布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        if(mDataBean!=null){
            Log.i("NewsRecyclerViewAdapter", "onBindViewHolder: ");
            holder.mTextView.setText(mDataBean.get(position).getTitle());
          Glide.with(holder.mImageView.getContext())
                    .load(mDataBean.get(position).getThumbnail_pic_s())
                    .into(holder.mImageView);
        }

    }

    @Override
    public int getItemCount() {
        if (mDataBean==null)
            return  10;
        else
        return mDataBean.size();
    }

    public void setData(ArrayList<NewsEntity.ResultBean.DataBean> data) {
        mDataBean = data;
        notifyDataSetChanged();
    }

    class  NewsViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        ImageView mImageView;
        public NewsViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tx_item);
            mImageView = (ImageView) itemView.findViewById(R.id.img_item);
        }
    }
}
