package com.elephant.healthycat.healthycattest.mvp.discrovery.model;

/**
 * Created by ${liub} on 2017/3/1 10:12.
 * QQ: ${992630112}
 */

import android.util.Log;

import com.elephant.healthycat.healthycattest.bean.DiscroveryBean;
import com.elephant.healthycat.healthycattest.util.NewsJsonUtils;
import com.elephant.healthycat.healthycattest.util.OkHttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * news逻辑处理
 */
public class DiscroveryModelImpl implements DiscroveryModel {

    private String TAG = DiscroveryModelImpl.class.getSimpleName();

    /**
     * news列表
     *
     * @param url
     * @param type
     * @param onLoadNewsListener
     */
    @Override
    public void loadNews(String url, int type, final onLoadNewsListener onLoadNewsListener) {
        OkHttpUtils.ResultCallback<String> resultCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                Log.e(TAG, "onSuccess: discroverybean:" + response.toString());
                List<DiscroveryBean> discroverybean= NewsJsonUtils.readJsonNewsBeans(response.toString(),"T1348647909107");
                onLoadNewsListener.onSuccess(discroverybean);
            }

            @Override
            public void onFailure(Exception e) {
                onLoadNewsListener.onFilure("load news list filure", e);
            }
        };
        OkHttpUtils.get(url,resultCallback);
    }


    /**
     * news详细页面
     *
     * @param url
     * @param type
     * @param onLoadNewsListener
     */
    @Override
    public void loadDetailsNews(String url, int type, onLoadNewsListener onLoadNewsListener) {

    }
}
