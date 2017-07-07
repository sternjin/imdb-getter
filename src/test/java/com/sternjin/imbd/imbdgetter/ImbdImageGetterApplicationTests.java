package com.sternjin.imbd.imbdgetter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.sternjin.imbd.imbdgetter.domain.Movie;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ImdbGetterAppConfig.class })
public class ImbdImageGetterApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    ImdbGetter getter;

    @Test
    public void getImage()
        throws Exception
    {
        String id = "tt0468569";
        String assertImage = "https://images-na.ssl-images-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg";

        Movie movie = getter.getDataById(id);

        Assert.isTrue(movie.getImgUrl().equals(assertImage), "TRUE");

    }

}
