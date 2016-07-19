package com.lanou.evernote.loginandregister;

import android.content.Context;

import com.lanou.evernote.base.BaseModel;
import com.lanou.evernote.base.BasePresenter;
import com.lanou.evernote.base.BaseView;

/**
 * Created by zouguohua on 16/7/18.
 */
public interface LoginAndRegistterContract {

    interface LoginView extends BaseView<Presenter> {
        void loginSuccess();

        void loginError(String ErrorMessage);


    }

    interface RegisterView extends BaseView<Presenter>{
        void registerSuccess();

        void registerError(String ErrorMessage);
    }

    interface Presenter extends BasePresenter {
        void login(String userName, String password);

        void register(String userName, String password);

        void loginSuccess();

        void registerSuccess();

        void loginError(String errorMessage);

        void registerError(String errorMessage);
    }

    interface Model extends BaseModel<Presenter> {
        void checkLoginFoNet(String userName, String password);

        void checkRegisterFoNet(String userName, String password);
    }

}
