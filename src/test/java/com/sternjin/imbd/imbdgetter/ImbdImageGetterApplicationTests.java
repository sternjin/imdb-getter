package com.sternjin.imbd.imbdgetter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.HttpStatusException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.sternjin.imbd.imbdgetter.domain.Movie;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ImdbGetterApplication.class })
public class ImbdImageGetterApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void contextLoads() {
    }

    @Autowired
    ImdbGetter imdbGetter;

    @Autowired
    DataLoader dataLoader;

    @Autowired
    Exporter exporter;

    @Autowired
    DataHolder dataHolder;

    @Test
    public void getAndSet()
        throws IOException
    {
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

        File exportFile = new File("/Users/zinc/Downloads/tmp.csv");
        exporter.export(exportFile, exportList);

    }

}
