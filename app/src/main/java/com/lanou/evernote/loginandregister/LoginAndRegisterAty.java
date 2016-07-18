package com.lanou.evernote.loginandregister;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseActivity;

/**
 * Created by zouguohua on 16/7/18.
 */
public class LoginAndRegisterAty extends BaseActivity {
    private TabLayout loginTabLayout;
    private ViewPager loginViewPager;
    @Override
    public int setLayout() {
        return R.layout.activity_login_register;
    }

    @Override
    protected void initData() {

    }
}
