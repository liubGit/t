package com.elephant.healthycat.healthycattest.mvp.my.presenter;

import android.graphics.Path;

import com.elephant.healthycat.healthycattest.mvp.model.CarouselModl;
import com.elephant.healthycat.healthycattest.mvp.my.model.MyModel;
import com.elephant.healthycat.healthycattest.mvp.my.view.MyView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MyPersenter {
    //view接口
    private MyView myView;
    //model 接口
    private IMyPresenter myPersenter;
    private List<MyModel> myModelList;
//    private List<CarouselModl> myCarouselModelList;

    private String[] imageUrls =new String[] {
            "http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};
    public MyPersenter(MyView myView) {
        this.myView = myView;
        this.myPersenter = new MyMainPersenter();
        myModelList = new ArrayList<>();
//        myCarouselModelList=new ArrayList<>();
    }

    public void setData() {
        for (int i = 0; i < imageUrls.length; i++) {
            MyModel model = new MyModel();
            model.setName("name" + i);
            model.setNumber("number" + i);
            model.setUrl(imageUrls[i]);
            myModelList.add(model);
        }
        myPersenter.setData(myModelList);
    }
//    public void setCarouselData(){
//        for(int i = 0; i < imageUrls.length; i ++){
//            CarouselModl info = new CarouselModl();
//            info.setUrl(imageUrls[i]);
//            info.setContent("图片-->" + i );
//            myCarouselModelList.add(info);
//        }
//        myCarouselPersenter.setCarouselData(myCarouselModelList);
//    }
    public List<MyModel> getData() {
        return myPersenter.getData();
    }

//    public List<CarouselModl> getCarouselData(){
//        return myCarouselPersenter.getCarouselData();
//    }
}
