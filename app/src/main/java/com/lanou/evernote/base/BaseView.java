package com.lanou.evernote.base;

/**
 * Created by zouguohua on 16/7/18.
 */
public interface BaseView<T> {

    //将presenter设置给View
    void setPresenter(T presenter);

}
