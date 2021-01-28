package com.blibli.springdesignpattern.observer;

import lombok.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.*;
import org.springframework.context.annotation.Bean;

public class ObserverApplication {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Product{

        private String id;
        private String name;

    }

    public static class ProductEvent extends ApplicationEvent {

        public ProductEvent(Object source) {
            super(source);
        }
    }

    public static class ProductRepository implements ApplicationEventPublisherAware{

        @Setter
        private ApplicationEventPublisher applicationEventPublisher;

        public void save(Product product){
            System.out.println("Done save to Database");
            applicationEventPublisher.publishEvent(new ProductEvent(product));
        }
    }

    public static class MessageBrokerObserver implements ApplicationListener<ProductEvent> {

        @Override
        public void onApplicationEvent(ProductEvent productEvent) {
            System.out.println("Kirim ke message Broker");
        }
    }

    public static class RedisObserver implements ApplicationListener<ProductEvent> {

        @Override
        public void onApplicationEvent(ProductEvent productEvent) {
            System.out.println("Kirim ke Redis Server");
        }
    }
    public static class logObserver implements ApplicationListener<ProductEvent> {

        @Override
        public void onApplicationEvent(ProductEvent productEvent) {
            System.out.println("Kirim ke log Server");
        }
    }

    @SpringBootApplication
    public static class Application{

        @Bean
        public ProductRepository productRepository(){
            return new ProductRepository();
        }

        @Bean
        public MessageBrokerObserver messageBrokerObserver(){
            return new MessageBrokerObserver();
        }

        @Bean
        public RedisObserver redisObserver(){
            return new RedisObserver();
        }

        @Bean
        public logObserver logObserver(){
            return new logObserver();
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);

        productRepository.save(new Product("1", "ipon"));
    }

}
