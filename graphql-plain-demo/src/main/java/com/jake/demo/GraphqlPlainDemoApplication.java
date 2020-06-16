package com.jake.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphqlPlainDemoApplication {

    public static void main(String[] args) {
        MockDataProvider.init();
        SpringApplication.run(GraphqlPlainDemoApplication.class, args);
    }

}
