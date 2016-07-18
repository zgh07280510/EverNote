package com.lanou.evernote.loginandregister;

import com.lanou.evernote.base.BaseModel;
import com.lanou.evernote.base.BasePresenter;
import com.lanou.evernote.base.BaseView;

/**
 * Created by zouguohua on 16/7/18.
 */
public interface LoginAndRegistterContract {
    interface View extends BaseView<Presenter> {
        void loginSuccess();

        void loginError(String ErrorMessage);
    }

    interface Presenter extends BasePresenter {
        void login(String userName, String password);

        void loginSuccess();

        void loginError(String errorMessage);

    }

    interface Model extends BaseModel<Presenter> {
        void checkLoginFoNet(String userName, String password);

        void checkRegisterFoNet(String userName, String password);
    }

}
