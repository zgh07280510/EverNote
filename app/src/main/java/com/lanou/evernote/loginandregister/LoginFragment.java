package com.lanou.evernote.loginandregister;

import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseFragment;
import com.lanou.evernote.base.MyApplication;


/**
 * Created by dllo on 16/7/18.
 */
public class LoginFragment extends BaseFragment implements LoginAndRegistterContract.LogView, View.OnClickListener {
    private Button btnLogin;
    private EditText etUserName, etLoginPassword;
    private LoginAndRegistterContract.Presenter presenter;
    private LinearLayout llLoginShow;
    private CheckBox checkBox;

    @Override
    protected int setLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View view) {
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        etUserName = (EditText) view.findViewById(R.id.et_user_name_mail_address);
        etLoginPassword = (EditText) view.findViewById(R.id.et_login_password);
        llLoginShow = (LinearLayout) view.findViewById(R.id.ll_login_show);
        checkBox = (CheckBox) view.findViewById(R.id.cb_login);

    }

    @Override
    protected void initData() {
        checkBox.setOnClickListener(this);
        etLoginPassword.setOnClickListener(this);
        etUserName.setOnClickListener(this);
        llLoginShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox.isChecked()){

                    etLoginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    checkBox.setChecked(true);
                }else {
                    //点击隐藏密码
                    etLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    checkBox.setChecked(false);
                }
            }
        });
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
    public void setPresenter(LoginAndRegistterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(MyApplication.getContext(), "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginError(String ErrorMessage) {
        Toast.makeText(MyApplication.getContext(), ErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_user_name_mail_address:
                Intent intent = new Intent("com.lanou.evernote");
                MyApplication.getContext().sendBroadcast(intent);
                break;
            case  R.id.et_login_password:
                Intent intent1 = new Intent("com.lanou.evernote");
                MyApplication.getContext().sendBroadcast(intent1);
                break;
            case R.id.cb_login:
                if (checkBox.isChecked()){
                    etLoginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    etLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
        }
    }
}
