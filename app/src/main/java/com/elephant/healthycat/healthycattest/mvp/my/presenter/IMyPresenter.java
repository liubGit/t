package com.elephant.healthycat.healthycattest.mvp.my.presenter;

import com.elephant.healthycat.healthycattest.mvp.my.model.MyModel;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public interface IMyPresenter<T> {
    void setData(List<T> list);

    List<T> getData();

}
