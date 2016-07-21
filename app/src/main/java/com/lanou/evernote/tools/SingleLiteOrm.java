package com.lanou.evernote.tools;

import com.lanou.evernote.base.MyApplication;
import com.litesuits.orm.LiteOrm;

/**
 * Created by dllo on 16/7/21.
 */
public class SingleLiteOrm {
    private static SingleLiteOrm singleLiteOrm;
    private LiteOrm liteOrm;

    public LiteOrm getLiteOrm() {
        return liteOrm;
    }

    private SingleLiteOrm() {
        liteOrm = LiteOrm.newCascadeInstance(MyApplication.getContext(),"SearchHistory.db");

    }

    public static SingleLiteOrm getSingleLiteOrm() {
        if (singleLiteOrm == null) {
            synchronized (SingleLiteOrm.class) {
                if (singleLiteOrm == null) {
                    singleLiteOrm = new SingleLiteOrm();
                }
            }
        }

        return singleLiteOrm;
    }
}
