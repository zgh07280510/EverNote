package com.lanou.evernote.loginandregister;

import android.content.Context;

/**
 * Created by zouguohua on 16/7/18.
 */
public class LoginAndRegisterPresenter implements LoginAndRegistterContract.Presenter {
    private LoginAndRegistterContract.View view;
    private LoginAndRegistterContract.Model model;

    public LoginAndRegisterPresenter(LoginAndRegistterContract.Model model, LoginAndRegistterContract.View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * 登录,传递给Model
     *
     * @param userName 用户名
     * @param password 密码
     */
    @Override
    public void login(String userName, String password) {
        if (userName == null
                || password == null
                || userName.length() * password.length() == 0) {
            view.loginError("用户名或密码为空");
        } else {
            model.checkLoginFoNet(userName, password);
        }
    }

    @Override
    public void register(String userName, String password) {

    }

    @Override
    public void loginSuccess() {
        view.loginSuccess();
    }


    @Override
    public void loginError(String errorMessage) {
        view.loginError(errorMessage);
    }

    @Override
    public void start() {
        model.setPresenter(this);
    }
}
