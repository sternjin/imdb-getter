package com.sternjin.imbd.imbdgetter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sternjin.imbd.imbdgetter.domain.Movie;

/**
 * Created by zinc on 14/07/2017.
 */
public class LocalFileExporter
    implements Exporter
{

    private final JdbcTemplate jdbcTemplate;

    public LocalFileExporter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override public void export(File file, List<Movie> list)
        throws IOException
    {

        try (FileWriter fw = new FileWriter(file);
            CSVPrinter p = new CSVPrinter(fw, CSVFormat.DEFAULT.withRecordSeparator("\n")))
        {
            for (Movie m : list) {
                List record = new ArrayList();
                record.add(m.getId());
                record.add(m.getImdbId());
                record.add(m.getName());
                record.add(m.getGenres());
                record.add(m.getImgUrl());
                p.printRecord(record);
            }
        } catch (IOException e) {
            return;
        }

    }

}
