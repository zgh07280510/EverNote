package com.lanou.evernote.base;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by dllo on 16/7/19.
 */
public abstract class ListViewCommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater inflater;
    private int itemLayoutId;

    public ListViewCommonAdapter(Context mContext, List<T> mDatas, int itemLayoutId) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(mContext);
        this.itemLayoutId = itemLayoutId;
    }

    //刷新适配器里的全部数据
    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }
    public void addSingleData(T t,int pos){
    if (pos > mDatas.size()){
        pos = mDatas.size();
    }
    mDatas.add(pos,t);
    notifyDataSetChanged();
}
    @Override
    public int getCount() {
        return mDatas != null?mDatas.size():0;
    }



    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, itemLayoutId, position);
        convert(holder, getItem(position));
        return holder.getmConvertView();
    }
public abstract void convert(ViewHolder holder,T t);
}
