package com.lanou.evernote.loginandregister;



import android.util.Log;

import com.lanou.evernote.base.MyApplication;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/7/18.
 */
public class LoginAndRegisterModel implements LoginAndRegistterContract.Model {
    private LoginAndRegistterContract.Presenter presenter;


    @Override
    public void checkLoginFoNet(String userName, String password) {
        BmobUser bmobUser = BmobUser.getCurrentUser(MyApplication.getContext());
        bmobUser = new BmobUser();
        bmobUser.setUsername(userName);
        bmobUser.setPassword(password);
        bmobUser.login(MyApplication.getContext(), new SaveListener() {
            @Override
            public void onSuccess() {

                presenter.loginSuccess();
            }

            @Override
            public void onFailure(int i, String s) {

                presenter.loginError("账号或密码不正确");
            }
        });


    }

    @Override
    public void checkRegisterFoNet(String userName, String password) {

        BmobUser bmobUser = BmobUser.getCurrentUser(MyApplication.getContext());
        bmobUser = new BmobUser();
        bmobUser.setUsername(userName);
        bmobUser.setPassword(password);
        bmobUser.signUp(MyApplication.getContext(), new SaveListener() {
            @Override
            public void onSuccess() {
                presenter.registerSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                presenter.registerError("该账号已经存在,请重新注册");
            }
        });


    }

    @Override
    public void setPresenter(LoginAndRegistterContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
