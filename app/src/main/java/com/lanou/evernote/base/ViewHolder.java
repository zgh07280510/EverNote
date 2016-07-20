package com.lanou.evernote.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dllo on 16/7/19.
 */
public class ViewHolder {
    // 这个 SparseArray<View> mViews实际上就是一个键值对，
    // 用来保存listview或者gridview上每一行或者每一列上的所有控件的
    private SparseArray<View> mViews;// 可以看成map, 它的key值 就只能是int
    private int mPosition;
    private View mConvertView;


    public ViewHolder(Context context, ViewGroup viewGroup, int layoutId, int position) {
        mPosition = position;
        mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, viewGroup, false);
        mConvertView.setTag(this);

    }
    public static ViewHolder get(Context context, View convertView, ViewGroup viewGroup, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, viewGroup, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }
    /**
     * 通过viewId获取控件
     * @param viewId
     * @param <T> 这个泛型必须是view的子类
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);

        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    public View getmConvertView() {
        return mConvertView;
    }
    // 添加文字
    public ViewHolder setText(int viewId,String text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    // 添加图片
    public ViewHolder setImgResource(int viewId,int resId){
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }
}
