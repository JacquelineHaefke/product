package com.jacquelinehaefke.mircoservice.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {


    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Product("product 1", 1.20)));
            log.info("Preloading " + repository.save(new Product("product 2", 2.4)));
        };
    }
}
