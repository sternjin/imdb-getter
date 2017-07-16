package com.sternjin.imbd.imbdgetter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.sternjin.imbd.imbdgetter.domain.Movie;

/**
 * Created by zinc on 10/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ImdbGetterApplication.class })
public class DataLoaderTest {

    @Autowired
    DataHolder dataHolder;
    @Autowired
    DataLoader dataLoader;

    @Test
    public void loadData()
        throws IOException
    {
        File file = new ClassPathResource("movielens/movies_links_test.csv").getFile();

        List<Movie> list = dataLoader.load(file);

        for (Movie m : list) {
            System.out.println(m.toString());
        }

        dataHolder.put(list);

        List<Movie> holdList = dataHolder.getAll();
        for (Movie m : holdList) {
            System.out.println(m.toString());
        }

    }

}
