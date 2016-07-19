package com.lanou.evernote.loginandregister;

import android.content.Context;
import android.util.Log;

/**
 * Created by zouguohua on 16/7/18.
 */
public class LoginAndRegisterPresenter implements LoginAndRegistterContract.Presenter {
    private LoginAndRegistterContract.LogView logView;
    private LoginAndRegistterContract.RegView regView;
    private LoginAndRegistterContract.Model model;

    public LoginAndRegisterPresenter(LoginAndRegistterContract.LogView logView, LoginAndRegistterContract.Model model, LoginAndRegistterContract.RegView regView) {
        this.logView = logView;
        this.model = model;
        this.regView = regView;
    }

    @Override
    public void start() {
        model.setPresenter(this);
    }

    /**
     * 登录,传递给Model
     *
     * @param userName 用户名
     * @param password 密码
     */
    @Override
    public void login(String userName, String password) {

        Log.d("LoginAndRegisterPresent", userName);
        if (userName == null
                || password == null
                || userName.length() * password.length() == 0) {
            logView.loginError("用户名或密码不能为空");
        } else {
            model.checkLoginFoNet(userName, password);
        }
    }

    @Override
    public void register(String userName, String password) {
        if (userName == null
                || password == null
                || userName.length() * password.length() == 0) {
            regView.registerError("用户名或密码不能为空");
        } else {
            model.checkRegisterFoNet(userName, password);
        }
    }

    //登录成功
    @Override
    public void loginSuccess() {
        logView.loginSuccess();
    }

    //注册成功
    @Override
    public void registerSuccess() {
        regView.registerSuccess();
    }

    //登录失败
    @Override
    public void loginError(String errorMessage) {
        logView.loginError(errorMessage);
    }

    //注册失败
    @Override
    public void registerError(String errorMessage) {
        regView.registerError(errorMessage);
    }


}
