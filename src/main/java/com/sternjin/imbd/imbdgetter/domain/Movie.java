package com.sternjin.imbd.imbdgetter.domain;

/**
 * Created by zinc on 02/07/2017.
 */
public class Movie {

    private String id;
    private String imdbId;
    private String name;
    private String genres;

    private String imgUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
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

    @Override public String toString() {
        return String.format("%s,%s,%s,%s,%s",
            this.id,
            this.imdbId,
            this.name,
            this.genres,
            this.imgUrl);
    }
}
