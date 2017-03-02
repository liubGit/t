package com.elephant.healthycat.healthycattest.mvp.presenter;

import com.elephant.healthycat.healthycattest.mvp.model.CarouselModl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */

public class HomeMainPresenter implements IMainPresenter<CarouselModl> {
    private List<CarouselModl> carouselModlList;

    public HomeMainPresenter() {
        carouselModlList = new ArrayList<>();
    }

    @Override
    public void setData(List<CarouselModl> list) {
        this.carouselModlList = list;
    }

    @Override
    public List<CarouselModl> getData() {
        return carouselModlList;
    }

    @Override
    public void deleteData(int position) {
        carouselModlList.remove(position);
    }
}
