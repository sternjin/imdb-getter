package com.sternjin.imbd.imbdgetter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.HttpStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

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
            app.run(args[0]);
        }

    }

    private List<Movie> movieList;

    @Autowired
    ImdbGetter imdbGetter;

    @Autowired
    DataLoader dataLoader;

    @Autowired
    DataHolder dataHolder;

    @Autowired
    Exporter exporter;

    @Value("{file.export.location}")
    String exportFileLocation;


    public void run(String exportFileLocation)
        throws Exception
    {
        if (exportFileLocation == null) {
            exportFileLocation = this.exportFileLocation;
        }
        File exportFile = new File(exportFileLocation);

        File file = new ClassPathResource("movielens/movies_links.csv").getFile();

        List<Movie> list = dataLoader.load(file);
        dataHolder.put(list);

        List<Movie> list4Getter = dataHolder.getImgNullMovies();

        logger.info("We have {} movies in file", list.size() - 1);
        logger.info("{} movies in database", dataHolder.size());
        logger.info("{} / {} of movies do not have imgUrl", list4Getter.size(), list.size());

        AtomicInteger cnt = new AtomicInteger(0);
        for (Movie movie : list4Getter) {
            try {
                dataHolder.put(imdbGetter.getDataByObject(movie));
                Thread.sleep(1000L); // preventing waf
            } catch (HttpStatusException ex) {
                logger.error("HttpStatusException {} for url {}, movie =>{}", ex.getStatusCode(), ex.getUrl(), movie.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (cnt.incrementAndGet() % 100 == 0) {
                logger.info("getting data ... {} / {}", cnt.get(), list4Getter.size());
            }
        }

        List<Movie> exportList = dataHolder.getAll();

        exporter.export(exportFile, exportList);

    }

}
