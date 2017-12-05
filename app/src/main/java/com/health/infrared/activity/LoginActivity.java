package com.health.infrared.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.health.infrared.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by joker on 2017/12/3.
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.username_edittext)
    EditText usernamEdittext;
    @BindView(R.id.password_edittext)
    EditText passwordEdittext;


    @Override
    public int getLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initComponentViews() {
        initToolbar();
    }

    private void initToolbar() {
        hideBackText();
        setRightText(getString(R.string.register));
        titleTextView.setText(getString(R.string.login));
    }

    @OnClick(R.id.login_button)
    void loginButton() {
//        if (checkArgument()) {
        login();
//        }
    }

    /**
     * 登录方法
     */
    private void login() {
        Logger.d("login::");
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private boolean checkArgument() {
        String username = usernamEdittext.getText().toString().trim();
        String password = passwordEdittext.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            showToast(getString(R.string.please_input));
            return false;
        }
        return true;
    }

}
