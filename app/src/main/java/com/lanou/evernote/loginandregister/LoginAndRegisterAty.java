package com.lanou.evernote.loginandregister;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseActivity;

import cn.bmob.v3.Bmob;

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
    protected void initView() {

    }


    @Override
    protected void initData() {
        Bmob.initialize(this,"3e8e0dce858ecb9845dcf7eceb687563");
        LoginAndRegistterContract.Model model = new LoginAndRegisterModel();

    }
}
