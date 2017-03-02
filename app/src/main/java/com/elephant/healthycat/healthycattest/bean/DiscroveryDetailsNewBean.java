package com.elephant.healthycat.healthycattest.bean;

import java.io.Serializable;

/**
 * Created by ${liub} on 2017/3/1 10:03.
 * QQ: ${992630112}
 */

public class DiscroveryDetailsNewBean  implements Serializable{
    private String imageSrc;
    private String titleNews;

    @Override
    public String toString() {
        return "DiscroveryDetailsNewBean{" +
                "imageSrc='" + imageSrc + '\'' +
                ", titleNews='" + titleNews + '\'' +
                '}';
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }
}
