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
public class RegisterFragment extends BaseFragment implements LoginAndRegistterContract.RegView {
    private Button btnStartUse;
    private EditText etMailAddress, etRegisterPassword;
    private LoginAndRegistterContract.Presenter presenter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_register;
    }


    @Override
    public void setPresenter(LoginAndRegistterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void initView(View view) {
        btnStartUse = (Button) view.findViewById(R.id.btn_start_use);
        etMailAddress = (EditText) view.findViewById(R.id.et_register_mail_address);
        etRegisterPassword = (EditText) view.findViewById(R.id.et_register_password);
    }

    @Override
    protected void initData() {
        btnStartUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etMailAddress.getText().toString();
                String psw = etRegisterPassword.getText().toString();
                presenter.register(userName, psw);
            }
        });
        presenter.start();
    }



    @Override
    public void registerSuccess() {
        Toast.makeText(MyApplication.getContext(), "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerError(String ErrorMessage) {
        Toast.makeText(MyApplication.getContext(), "注册失败", Toast.LENGTH_SHORT).show();

    }


}
