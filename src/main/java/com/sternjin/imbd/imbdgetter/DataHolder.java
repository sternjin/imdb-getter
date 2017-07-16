package com.sternjin.imbd.imbdgetter;

import java.io.IOException;
import java.util.List;

import com.sternjin.imbd.imbdgetter.domain.Movie;

/**
 * Created by zinc on 16/07/2017.
 */
public interface DataHolder {

    public void put(List<Movie> list) throws IOException;
    public void put(Movie movie) throws IOException;

    public List<Movie> getAll() throws IOException;

    public List<Movie> getImgNullMovies() throws IOException;

    public int size() throws IOException;


}
