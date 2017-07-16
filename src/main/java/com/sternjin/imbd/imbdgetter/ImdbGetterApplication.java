package com.sternjin.imbd.imbdgetter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.HttpStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.sternjin.imbd.imbdgetter.domain.Movie;

@SpringBootApplication
public class ImdbGetterApplication {

    Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args)
        throws Exception
    {
        //SpringApplication.run(ImdbGetterApplication.class, args);

        try (ConfigurableApplicationContext context = SpringApplication.run(ImdbGetterApplication.class, args)) {
            ImdbGetterApplication app = context.getBean(ImdbGetterApplication.class);
            app.run(null, null);
        }

    }

    private List<Movie> movieList;

    @Autowired
    ImdbGetter imdbGetter;

    @Autowired
    DataLoader dataLoader;

    @Value("{file.import.location}")
    String importFileLocation;


    @Value("{file.export.location}")
    String exportFileLocation;


    public void run(File importFile, File exportFile)
        throws Exception
    {
        if ( importFile == null || exportFile == null) {
            importFile = new File(importFileLocation);
            exportFile = new File(exportFileLocation);
        }

        List<Movie> importList = dataLoader.load(importFile);

        List<Movie> exportList = new ArrayList<>();
        for (Movie movie : importList) {

            try {
                exportList.add(imdbGetter.getDataById(movie.getImdbId()));
            } catch (HttpStatusException ex) {
                logger.error("HttpStatusException {} for url {}, movie =>{}", ex.getStatusCode(), ex.getUrl(), movie.toString());
            }

        }

    }

}
