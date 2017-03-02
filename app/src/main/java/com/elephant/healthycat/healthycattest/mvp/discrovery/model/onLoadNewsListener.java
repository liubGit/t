package com.elephant.healthycat.healthycattest.mvp.discrovery.model;

import com.elephant.healthycat.healthycattest.bean.DiscroveryBean;

import java.util.List;

/**
 * Created by ${liub} on 2017/2/28 18:06.
 * QQ: ${992630112}
 */

/**
 * news接口监听回调
 */
public interface onLoadNewsListener {
    void onSuccess(List<DiscroveryBean> discroveryBean);

    void onFilure(String string, Exception msg);
}
