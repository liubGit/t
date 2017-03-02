package com.elephant.healthycat.healthycattest.mvp.discrovery.model;

import com.elephant.healthycat.healthycattest.bean.DiscroveryDetailsNewBean;

import java.util.List;

/**
 * Created by ${liub} on 2017/3/1 10:02.
 * QQ: ${992630112}
 */

/**
 * news详情接口监听
 */
public interface onLoadDeatilsNewsListener {
    void Success(List<DiscroveryDetailsNewBean> list,onLoadDeatilsNewsListener onLoadDeatilsNewsListener);
    void Filure(String string,Exception e);
}
