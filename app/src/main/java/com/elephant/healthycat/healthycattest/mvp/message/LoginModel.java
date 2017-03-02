package com.elephant.healthycat.healthycattest.mvp.message;

import android.content.Context;

/**
 * Created by ${liub} on 2017/2/27.
 * QQ: ${992630112}
 */


/**
 *登陆接口
 */
public interface LoginModel {
    void login(String name, String pwd, onLoginListener onLoginListener);
}
