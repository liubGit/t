package com.elephant.healthycat.healthycattest.mvp.message;

import android.content.Context;

/**
 * Created by ${liub} on 2017/2/28.
 * QQ: ${992630112}
 */

/**
 * 处理model与view
 */
public class LoginPresenter implements onLoginListener {

    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModelImple();
    }

    public void login() {
        String name = loginView.getName();
        String pwd = loginView.getPwd();
        loginModel.login(name, pwd, this);
        loginView.showProgressBar();
    }

    @Override
    public void onSuccess() {
        loginView.moveToNext();
        loginView.hideProgressBar();
    }

    @Override
    public void onFailure() {
        loginView.showToast("登陆失败");
        loginView.hideProgressBar();
    }

    @Override
    public void onNameError() {
        loginView.showToast("用户名错误");
        loginView.hideProgressBar();
    }

    @Override
    public void onPwdError() {
        loginView.showToast("密码错误");
        loginView.hideProgressBar();
    }
}
