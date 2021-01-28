package com.blibli.springdesignpattern.prototype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class PrototypeApplication {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Customer{

        private String name;
        private String id;
        private String category;
        private Integer discount;

    }

    @SpringBootApplication
    public static class Configuration{
        @Bean("standardCustomer")
        @Scope("prototype") // Membuat banyak Object
        public Customer standardCustomer(){
            return Customer.builder().category("STANDARD").discount(10).build();
        }

        @Bean("premiumCustomer")
        @Scope("prototype") // Kalau ada 2 Tipe
        public Customer premiumCustomer(){
            return Customer.builder().category("VVVVIP").discount(10).build();
        }
    }

    public static void main(String[] args) {
        Customer customer = Customer.builder().id("1").name("Eko").category("Standart").discount(10).build();

        //Normal dan membosankan
        Customer customer2 = Customer.builder().id(customer.getId()).name(customer.getName()).category(customer.getCategory())
                .discount(customer.getDiscount()).build();

        // Cara cepat wkwk
        ApplicationContext context = SpringApplication.run(Configuration.class);
//        Customer customer1 = context.getBean(Customer.class); // Kalau di run sekarang error karena ada 2 prototype
        Customer customer1 = context.getBean("standardCustomer",Customer.class);

        Customer customer4 = context.getBean("premiumCustomer",Customer.class);

        System.out.println(customer1 == customer4); // False karena Objek baru :)
        System.out.println(customer1);
        System.out.println(customer4);
    }

}
