package com.health.infrared.model;

import java.io.Serializable;

/**
 * Created by 123 on 2017/12/4.
 */

public class MainItem implements Serializable {

    private int type;
    private String name;
    private int imgId;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
