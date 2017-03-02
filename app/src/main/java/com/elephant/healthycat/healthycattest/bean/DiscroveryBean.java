package com.elephant.healthycat.healthycattest.bean;

import java.io.Serializable;

/**
 * Created by ${liub} on 2017/2/28 17:48.
 * QQ: ${992630112}
 */

public class DiscroveryBean implements Serializable {
    private String title;
    private String digest;
    private String imgsrc;
    private String ptime;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    @Override
    public String toString() {
        return "DiscroveryBean{" +
                "title='" + title + '\'' +
                ", digest='" + digest + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", ptime='" + ptime + '\'' +
                '}';
    }
}
