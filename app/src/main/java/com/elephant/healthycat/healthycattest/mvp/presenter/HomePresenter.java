package com.elephant.healthycat.healthycattest.mvp.presenter;

import com.elephant.healthycat.healthycattest.mvp.model.CarouselModl;
import com.elephant.healthycat.healthycattest.mvp.view.HomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */

public class HomePresenter {
    //view 接口对象
    private HomeView homeView;

    //model接口
    private IMainPresenter iMainPresenter;
    private List<CarouselModl> carouselModlList;

    private String[] imageUrls = {
            "http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};
    public HomePresenter(HomeView homeView){
        this.homeView=homeView;
        this.iMainPresenter=new HomeMainPresenter();
        carouselModlList=new ArrayList<>();
    }

    public void setData(){
        for(int i = 0; i < imageUrls.length; i ++){
            CarouselModl info = new CarouselModl();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i );
            carouselModlList.add(info);
        }
        iMainPresenter.setData(carouselModlList);
    }
    public List<CarouselModl> getData(){
        return iMainPresenter.getData();
    }
}
