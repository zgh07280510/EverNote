package com.lanou.evernote.loginandregister;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseFragment;
import com.lanou.evernote.base.MyApplication;

/**
 * Created by dllo on 16/7/18.
 */
public class LoginFragment extends BaseFragment implements  LoginAndRegistterContract.LoginView {
    private Button btnLogin;
    private EditText etUserName, etLoginPassword;
    private LoginAndRegistterContract.Presenter presenter;

    @Override
    protected void initData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                String psw = etLoginPassword.getText().toString();
                presenter.login(userName, psw);
            }
        });
        presenter.start();
    }

    @Override
    protected void initView(View view) {
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        etUserName = (EditText) view.findViewById(R.id.et_user_name_mail_address);
        etLoginPassword = (EditText) view.findViewById(R.id.et_login_password);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(MyApplication.getContext(), "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginError(String ErrorMessage) {
    }




    @Override
    public void setPresenter(LoginAndRegistterContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
