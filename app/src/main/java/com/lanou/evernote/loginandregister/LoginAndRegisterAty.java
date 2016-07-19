package com.lanou.evernote.loginandregister;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

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
    private ImageViewAction imageViewAction;
    private ImageView ivHead;

    @Override
    public int setLayout() {
        return R.layout.activity_login_register;
    }

    @Override
    protected void initView() {
        ivHead = bindView(R.id.iv_register_login_head);
        loginTabLayout = bindView(R.id.login_register_tabLayout);
        loginViewPager = bindView(R.id.login_register_viewPager);
    }


    @Override
    protected void initData() {
        LoginAndRegistterContract.Model model = new LoginAndRegisterModel();
        RegisterFragment registerFragment = new RegisterFragment();
        LoginFragment loginFragment = new LoginFragment();
        LoginAndRegistterContract.Presenter presenter = new LoginAndRegisterPresenter(loginFragment, model, registerFragment);

        fragments = new ArrayList<>();
        fragments.add(registerFragment);
        fragments.add(loginFragment);
        loginAndRegisterAdapter = new LoginAndRegisterAdapter(getSupportFragmentManager());
        loginAndRegisterAdapter.setFragments(fragments);
        loginViewPager.setAdapter(loginAndRegisterAdapter);
        loginTabLayout.setupWithViewPager(loginViewPager);

        registerFragment.setPresenter(presenter);
        loginFragment.setPresenter(presenter);
        imageViewAction = new ImageViewAction();
        IntentFilter intentFilter = new IntentFilter("com.lanou.evernote");
        registerReceiver(imageViewAction, intentFilter);

    }


    class ImageViewAction extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
        ivHead.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(imageViewAction);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            ivHead.setVisibility(View.VISIBLE);
        }
        return false;
    }
}
