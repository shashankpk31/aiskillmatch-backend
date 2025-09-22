package com.shashankpk31.aiskillmatch_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.shashankpk31.aiskillmatch_backend.repository")
public class AiskillmatchBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiskillmatchBackendApplication.class, args);
    }
}
