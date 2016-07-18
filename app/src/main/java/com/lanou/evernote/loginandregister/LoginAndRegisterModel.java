package com.lanou.evernote.loginandregister;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/7/18.
 */
public class LoginAndRegisterModel implements LoginAndRegistterContract.Model {
    private LoginAndRegistterContract.Presenter presenter;



    public LoginAndRegisterModel(){


    }


    @Override
    public void checkLoginFoNet(String userName, String password) {
    //    BmobUser bmobUser = BmobUser.getCurrentUser()

    }

    @Override
    public void checkRegisterFoNet(String userName, String password) {

    }

    @Override
    public void setPresenter(LoginAndRegistterContract.Presenter presenter) {

    }
}
