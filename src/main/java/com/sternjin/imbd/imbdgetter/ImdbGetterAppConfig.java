package com.sternjin.imbd.imbdgetter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by zinc on 02/07/2017.
 */

@Configuration
public class ImdbGetterAppConfig {

    @Autowired JdbcTemplate jdbcTemplate;


    @Bean
    public DataLoader dataLoader() {
        return new MovielensDataLoader(jdbcTemplate);
    }

    @Bean
    public DataHolder dataHolder() {
        return new RdbDataHolder(jdbcTemplate);
    }

    @Bean
    public Exporter exporter() {
        return new LocalFileExporter(jdbcTemplate);
    }

}
