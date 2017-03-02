package com.elephant.healthycat.healthycattest.mvp.discrovery.view;

import com.elephant.healthycat.healthycattest.bean.DiscroveryBean;

import java.util.List;

/**
 * Created by ${liub} on 2017/2/28 17:47.
 * QQ: ${992630112}
 */

/**
 * view的接口
 */
public interface DiscroveryView {
    void addNews(List<DiscroveryBean> list);

    void showProgress();

    void hideProgress();

    void showLoadMsg();

}
