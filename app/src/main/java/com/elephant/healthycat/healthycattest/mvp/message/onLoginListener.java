package com.elephant.healthycat.healthycattest.mvp.message;

/**
 * Created by Administrator on 2017/2/27.
 */


/**
 * 监听器用于处理完model后的操作
 */
public interface onLoginListener {
    public void onSuccess();

    public void onFailure();

    public void onNameError();

    public void onPwdError();
}
