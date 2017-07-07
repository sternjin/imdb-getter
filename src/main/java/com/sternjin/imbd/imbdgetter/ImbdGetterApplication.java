package com.sternjin.imbd.imbdgetter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.sternjin.imbd.imbdgetter.domain.Movie;

@SpringBootApplication
public class ImbdGetterApplication {


    public static void main(String[] args)
        throws Exception
    {
        //SpringApplication.run(ImbdGetterApplication.class, args);

        try (ConfigurableApplicationContext context = SpringApplication.run(ImbdGetterApplication.class, args)) {
            ImbdGetterApplication app = context.getBean(ImbdGetterApplication.class);
            app.run(args);
        }

    }

    private List<Movie> movieList;

    @Autowired
    ImdbGetter imdbGetter;

    @Value("{file.data.location}")
    String fileLocation;


    @Value("{file.output.location}")
    String outputFileLocation;


    public void run(String... args)
        throws Exception
    {

        List<Movie> exportList = new ArrayList<>();

        exportList.add(imdbGetter.getDataById(null));


    }

}
