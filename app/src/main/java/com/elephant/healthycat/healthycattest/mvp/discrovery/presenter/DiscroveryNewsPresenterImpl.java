package com.elephant.healthycat.healthycattest.mvp.discrovery.presenter;

import android.content.Context;
import android.util.Log;

import com.elephant.healthycat.healthycattest.bean.DiscroveryBean;
import com.elephant.healthycat.healthycattest.commons.Contants;
import com.elephant.healthycat.healthycattest.mvp.discrovery.model.DiscroveryModel;
import com.elephant.healthycat.healthycattest.mvp.discrovery.model.DiscroveryModelImpl;
import com.elephant.healthycat.healthycattest.mvp.discrovery.model.onLoadNewsListener;
import com.elephant.healthycat.healthycattest.mvp.discrovery.view.DiscroveryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${liub} on 2017/3/1 14:26.
 * QQ: ${992630112}
 */

public class DiscroveryNewsPresenterImpl implements DiscroveryNewsPresenter, onLoadNewsListener {

    private DiscroveryView discroveryView;
    private DiscroveryModel discroveryModel;

    public DiscroveryNewsPresenterImpl(DiscroveryView discroveryView) {
        this.discroveryView = discroveryView;
        discroveryModel = new DiscroveryModelImpl();
    }


    @Override
    public void LoadNews(int type, int page) {
        String url=getUrl(0,page);
        if (page==0){
            discroveryView.showProgress();
        }
        //top id T1348647909107
        discroveryModel.loadNews(url,0,this);

    }

    /**
     * 根据类别和页面索引创建url
     * @param type
     * @param pageIndex
     * @return
     */
    private String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case 0:
                sb.append(Contants.NEWS_RUL).append("T1348647909107");
                break;
            case 1:
                sb.append(Contants.NEWS_RUL).append("T1348647909107");
                break;
            default:
                sb.append(Contants.NEWS_RUL).append("T1348647909107");
                break;
        }
        sb.append("/").append(pageIndex).append(Contants.END_URL);
        return sb.toString();
    }

    @Override
    public void onSuccess(List<DiscroveryBean> list) {
        discroveryView.hideProgress();
        Log.e("newspresenter", "onSuccess: list:"+list.get(0).getTitle() );
        discroveryView.addNews(list);
    }

    @Override
    public void onFilure(String string, Exception msg) {
        discroveryView.hideProgress();
        discroveryView.showLoadMsg();
    }
}
