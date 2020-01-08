package edu.sortstudents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring boot application exposing students sorting solution running http://localhost:8080/
 */
@SpringBootApplication
@ComponentScan(basePackages = { "edu.sortstudents.config, edu.sortstudents.ui"})
public class SpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

}
