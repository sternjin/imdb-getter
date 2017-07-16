package com.sternjin.imbd.imbdgetter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


    @Test
    public void getAndSet()
        throws IOException
    {
        File file = new ClassPathResource("movielens/movies_links_test.csv").getFile();

        List<Movie> list = dataLoader.load(file);

        for (Movie m : list) {
            System.out.println(m.toString());
        }

        List<Movie> exportList = new ArrayList<>();
        for (Movie movie : list) {
            try {
                exportList.add(imdbGetter.getDataByObject(movie));
            } catch (HttpStatusException ex) {
                logger.error("HttpStatusException {} for url {}, movie =>{}", ex.getStatusCode(), ex.getUrl(), movie.toString());
            }
        }

        for (Movie m : exportList) {
            System.out.println(m.toString());
        }

        File exportFile = new File("/Users/zinc/Downloads/tmp.csv");
        exporter.export(exportFile, exportList);

    }

}
