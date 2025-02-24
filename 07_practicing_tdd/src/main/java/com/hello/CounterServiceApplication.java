package com.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hello")
public class CounterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CounterServiceApplication.class, args);
    }
}