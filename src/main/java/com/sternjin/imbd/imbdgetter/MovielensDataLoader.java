package com.sternjin.imbd.imbdgetter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sternjin.imbd.imbdgetter.domain.Movie;

/**
 * Created by zinc on 10/07/2017.
 */
public class MovielensDataLoader
    implements DataLoader
{
    Logger logger = LoggerFactory.getLogger(getClass());

    private final JdbcTemplate jdbcTemplate;

    public MovielensDataLoader(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override public List<Movie> load(File file)
        throws IOException
    {
        List<Movie> list = new ArrayList<Movie>();
        try (Reader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            Reader reader = new BufferedReader(isr);
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withEscape('\\').withSkipHeaderRecord(true));)
        {
            parser.forEach(record -> {
                Movie m = new Movie();
                m.setId(record.get(0));
                m.setName(record.get(1));
                m.setGenres(record.get(2));
                m.setImdbId(record.get(4));
                list.add(m);
            });
        } catch (IOException e) {
            logger.error("load sizeHolder exception", e.getMessage());
        }
        return list;
    }


}
