package com.sternjin.imbd.imbdgetter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by zinc on 02/07/2017.
 */

@EnableAutoConfiguration
public class ImdbGetterAppConfig {

    @Bean
    private ImdbGetter imdbGetter() {
        return new ImdbGetterImpl();
    }

}
