package com.lanou.evernote.base;

/**
 * Created by zouguohua on 16/7/18.
 */
public interface BaseModel<T> {
    //将 presenter设置给Model
    void setPresenter(T presenter);
}
