package com.elephant.healthycat.healthycattest.mvp.message;

/**
 * Created by Administrator on 2017/2/27.
 */

public interface LoginView {
    void moveToNext();

    void showToast(String msg);

    String getName();

    String getPwd();

    void showProgressBar();

    void hideProgressBar();
}
