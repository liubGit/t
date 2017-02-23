package com.elephant.healthycat.healthycattest.mvp.model;

/**
 * Created by Administrator on 2017/2/23.
 */

public class CarouselModl {

    private int id;
    private String url = "";
    private String content = "";
    private String type = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
