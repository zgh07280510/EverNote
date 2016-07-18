package com.lanou.evernote.loginandregister;

import com.lanou.evernote.base.BaseActivity;

import cn.bmob.v3.Bmob;

/**
 * Created by zouguohua on 16/7/18.
 */
public class LoginAndRegisterAty extends BaseActivity {
    @Override
    public int setLayout() {
        return 0;
    }

    @Override
    protected void initData() {
        Bmob.initialize(this,"3e8e0dce858ecb9845dcf7eceb687563");
        LoginAndRegistterContract.Model model = new LoginAndRegisterModel();

    }
}
