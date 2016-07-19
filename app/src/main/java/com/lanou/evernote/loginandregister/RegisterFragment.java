package com.lanou.evernote.loginandregister;


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

import org.w3c.dom.Text;

/**
 * Created by dllo on 16/7/18.
 */
public class RegisterFragment extends BaseFragment implements LoginAndRegistterContract.RegView {
    private Button btnStartUse;
    private EditText etMailAddress, etRegisterPassword;
    private LoginAndRegistterContract.Presenter presenter;
    private LinearLayout llRegisterShow;
    private CheckBox checkBox;
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
        llRegisterShow = (LinearLayout) view.findViewById(R.id.ll_register_show);
        checkBox = (CheckBox) view.findViewById(R.id.cb_register);

    }

    @Override
    protected void initData() {
        llRegisterShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox.isChecked()){

                    etRegisterPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    checkBox.setChecked(true);
                }else {
                   //点击隐藏密码
                    etRegisterPassword.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    checkBox.setChecked(false);
                }
            }
        });
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
