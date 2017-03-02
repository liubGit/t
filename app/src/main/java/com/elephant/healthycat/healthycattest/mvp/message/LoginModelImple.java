package com.elephant.healthycat.healthycattest.mvp.message;

import android.content.Context;
import android.provider.Settings;
import android.os.Handler;
import android.text.TextUtils;

import org.w3c.dom.Text;

/**
 * Created by ${liub} on 2017/2/27.
 * QQ: ${992630112}
 */

/***
 * 登陆MODEL的实现,用与网络请求操作
 */
public class LoginModelImple implements LoginModel {
    @Override
    public void login(final String name, final String pwd, final onLoginListener onLoginListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(name)) {
                    onLoginListener.onNameError();
                    error = true;
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    onLoginListener.onPwdError();
                    error = true;
                    return;
                }
                if (!error) {
                    onLoginListener.onSuccess();
                }

            }
        }, 2000);

    }
}
