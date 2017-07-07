package com.sternjin.imbd.imbdgetter.domain;

/**
 * Created by zinc on 02/07/2017.
 */
public class Movie {

    private String id;
    private String name;

    private String imgUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    public Movie clone() {
        return this.clone();
    }
}
