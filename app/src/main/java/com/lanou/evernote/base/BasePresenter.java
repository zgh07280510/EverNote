package com.lanou.evernote.base;

/**
 * Created by zouguohua on 16/7/18.
 */
public interface BasePresenter {
    //当View层准备好了之后,会调用该方法
    //它可以简单地理解为 我们之前写的onCreate
    void start();
}
