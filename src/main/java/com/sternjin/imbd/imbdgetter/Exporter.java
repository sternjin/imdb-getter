package com.sternjin.imbd.imbdgetter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.sternjin.imbd.imbdgetter.domain.Movie;

/**
 * Created by zinc on 07/07/2017.
 */
public interface Exporter {

    public void export(File file, List<Movie> list) throws IOException;

}
