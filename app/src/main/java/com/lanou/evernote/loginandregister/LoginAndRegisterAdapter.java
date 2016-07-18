package com.lanou.evernote.loginandregister;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/18.
 */
public class LoginAndRegisterAdapter extends PagerAdapter {
    private ArrayList<Fragment> fragments;
    private String[] titles= {"创建账户","登录"};

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public LoginAndRegisterAdapter(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
