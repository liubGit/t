package com.elephant.healthycat.healthycattest.mvp.my.presenter;

import com.elephant.healthycat.healthycattest.mvp.my.model.MyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MyMainPersenter implements IMyPresenter<MyModel> {
    private List<MyModel> myModelList;

    public MyMainPersenter() {
        myModelList = new ArrayList<>();
    }

    @Override
    public void setData(List<MyModel> list) {
        this.myModelList = list;
    }

    @Override
    public List<MyModel> getData() {
        return myModelList;
    }
}
