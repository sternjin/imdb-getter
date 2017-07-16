package com.sternjin.imbd.imbdgetter;

import java.io.IOException;

import com.sternjin.imbd.imbdgetter.domain.Movie;

/**
 * Created by zinc on 02/07/2017.
 */
public interface ImdbGetter {

    public Movie getDataByName(String name)
        throws IOException;

    public Movie getDataById(String id)
        throws IOException;

    public Movie getDataByObject(Movie movie)
        throws IOException;

}
