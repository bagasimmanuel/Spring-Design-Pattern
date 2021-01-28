package com.blibli.springdesignpattern.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

public class AdapterPattern {

    public static class myHealthIndicator implements HealthIndicator{

        @Override
        public Health health() {
            return health().up().build();
        }
    }

    public static class MyDownHealthIndicator implements HealthIndicator {

        @Override
        public Health health() {
            return health().down().build();
        }
    }

    @SpringBootApplication
    public static class Application{

        @Bean
        public myHealthIndicator myHealthIndicator(){
            return new myHealthIndicator();
        }

        @Bean
        public MyDownHealthIndicator myDownHealthIndicator(){
            return new MyDownHealthIndicator();
        }

    }

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

}
