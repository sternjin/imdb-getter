package com.sternjin.imbd.imbdimagegetter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ImbdImageGetterApplication {

    public static void main(String[] args)
        throws Exception
    {
        //SpringApplication.run(ImbdImageGetterApplication.class, args);

        try (ConfigurableApplicationContext context = SpringApplication.run(ImbdImageGetterApplication.class, args)) {
            ImbdImageGetterApplication app = context.getBean(ImbdImageGetterApplication.class);
            app.run(args);
        }

    }

    public void run(String... args)
        throws Exception
    {
        // load movielens data , into h2


        // looping

        // export 


    }

}
