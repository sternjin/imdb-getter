package com.sternjin.imbd.imbdgetter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import com.sternjin.imbd.imbdgetter.domain.Movie;

/**
 * Created by zinc on 14/07/2017.
 */
@Component
public class LocalFileExporter
    implements Exporter
{
    @Override public void export(File file, List<Movie> list)
        throws IOException
    {

        try (FileWriter fw = new FileWriter(file);
            CSVPrinter p = new CSVPrinter(fw, CSVFormat.DEFAULT.withRecordSeparator("\n").withQuote(null)))
        {
            for (Movie m : list) {
                p.print(m);
                p.println();
            }
        } catch (IOException e) {
            return;
        }

    }

}
