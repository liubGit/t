package com.elephant.healthycat.healthycattest.mvp.presenter;

import android.content.ContentUris;

import com.elephant.healthycat.healthycattest.mvp.model.CarouselModl;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/23.
 */

public interface IMainPresenter<T> {
    void setData(List<T> list);
    List<T> getData();
    void deleteData(int position);
}
