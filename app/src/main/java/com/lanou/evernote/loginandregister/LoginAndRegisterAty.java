package com.lanou.evernote.loginandregister;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

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

        fragments = new ArrayList<>();
        //fragments.add(new LoginFragment());
        RegisterFragment registerFragment = new RegisterFragment();
        fragments.add(registerFragment);
        loginAndRegisterAdapter = new LoginAndRegisterAdapter(getSupportFragmentManager());
        loginAndRegisterAdapter.setFragments(fragments);
        loginViewPager.setAdapter(loginAndRegisterAdapter);
        loginTabLayout.setupWithViewPager(loginViewPager);


        LoginAndRegistterContract.Model model = new LoginAndRegisterModel();

        LoginAndRegisterPresenter presenter = new LoginAndRegisterPresenter(model, registerFragment);
        registerFragment.setPresenter(presenter);
    }
}
