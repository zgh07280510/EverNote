package com.lanou.evernote.loginandregister;

import android.graphics.Color;
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
        LoginAndRegistterContract.Model model = new LoginAndRegisterModel();
        RegisterFragment registerFragment = new RegisterFragment();
        LoginFragment loginFragment = new LoginFragment();
        LoginAndRegistterContract.Presenter presenter = new LoginAndRegisterPresenter(loginFragment, model, registerFragment);

<<<<<<< HEAD
        RegisterFragment registerFragment = new RegisterFragment();
        LoginFragment loginFragment = new LoginFragment();
        LoginAndRegistterContract.Model model = new LoginAndRegisterModel();
        LoginAndRegistterContract.Presenter presenter = new LoginAndRegisterPresenter(loginFragment,model,registerFragment);
        registerFragment.setPresenter(presenter);
        loginFragment.setPresenter(presenter);
        fragments = new ArrayList<>();

=======

        fragments = new ArrayList<>();
>>>>>>> b462bda6484a62f6bd52cb3e2f327b8b95013d54

        fragments.add(registerFragment);
        fragments.add(loginFragment);
        loginAndRegisterAdapter = new LoginAndRegisterAdapter(getSupportFragmentManager());
        loginAndRegisterAdapter.setFragments(fragments);
        loginViewPager.setAdapter(loginAndRegisterAdapter);
        loginTabLayout.setupWithViewPager(loginViewPager);
<<<<<<< HEAD




//        LoginAndRegisterPresenter presenter = new LoginAndRegisterPresenter(model, registerFragment);
//        registerFragment.setPresenter(presenter);
=======

        registerFragment.setPresenter(presenter);
        loginFragment.setPresenter(presenter);

>>>>>>> b462bda6484a62f6bd52cb3e2f327b8b95013d54

    }
}
