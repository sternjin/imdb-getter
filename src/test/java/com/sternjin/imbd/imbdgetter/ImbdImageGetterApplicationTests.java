package com.sternjin.imbd.imbdgetter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ImdbGetterApplication.class })
public class ImbdImageGetterApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void contextLoads() {
    }

    @Test
    public void getAndSet()
        throws Exception
    {

        String exportFileLocation = "/Users/zinc/Downloads/tmp.csv";

        ImdbGetterApplication.main(new String[] { exportFileLocation });

    }

}
