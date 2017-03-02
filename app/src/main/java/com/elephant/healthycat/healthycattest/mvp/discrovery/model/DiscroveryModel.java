package com.elephant.healthycat.healthycattest.mvp.discrovery.model;

/**
 * Created by ${liub} on 2017/2/28 18:00.
 * QQ: ${992630112}
 */

public interface DiscroveryModel {
    void loadNews(String url, int type, onLoadNewsListener onLoadNewsListener);
    void loadDetailsNews(String url,int type ,onLoadNewsListener onLoadNewsListener);
}
