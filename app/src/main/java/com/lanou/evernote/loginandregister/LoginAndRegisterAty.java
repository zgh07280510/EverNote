package com.lanou.evernote.loginandregister;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseActivity;
import com.lanou.evernote.base.MyApplication;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;

/**
 * Created by zouguohua on 16/7/18.
 */
public class LoginAndRegisterAty extends BaseActivity {
    private TabLayout loginTabLayout;
    private ViewPager loginViewPager;
    private LoginAndRegisterAdapter loginAndRegisterAdapter;
    private ArrayList<Fragment> fragments;

    @Override
    public int setLayout() {
        return R.layout.activity_login_register;
    }

    @Override
    protected void initView() {
        loginTabLayout = bindView(R.id.login_register_tabLayout);
        loginViewPager = bindView(R.id.login_register_viewPager);


    }


    @Override
    protected void initData() {
        Bmob.initialize(this, "3e8e0dce858ecb9845dcf7eceb687563");
        LoginAndRegistterContract.Model model = new LoginAndRegisterModel();
        RegisterFragment registerFragment = new RegisterFragment();
        LoginAndRegistterContract.Presenter presenter = new LoginAndRegisterPresenter(model, registerFragment);
        registerFragment.setPresenter(presenter);

        fragments = new ArrayList<>();
        fragments.add(registerFragment);
        fragments.add(new LoginFragment());
        loginAndRegisterAdapter = new LoginAndRegisterAdapter(getSupportFragmentManager());
        loginAndRegisterAdapter.setFragments(fragments);
        loginViewPager.setAdapter(loginAndRegisterAdapter);
        loginTabLayout.setupWithViewPager(loginViewPager);


    }
}
