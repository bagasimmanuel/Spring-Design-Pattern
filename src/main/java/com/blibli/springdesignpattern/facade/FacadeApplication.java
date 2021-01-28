package com.blibli.springdesignpattern.facade;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

public class FacadeApplication {

    public static interface AddressService{

        void save(String customerId,String address,String city);

        void delete(String customerId,String address);

        void update(String customerId);
    }

    public static class AddressController {

        @Setter
        private AddressService addressService;

        public void saveAddress(String customerId,String address,String city){
            System.out.println("Controller");
            addressService.save(customerId,address,city);
        }
    }

    public static class AddressServiceImplPostgre implements AddressService{

        @Override
        public void save(String customerId, String address, String city) {
            System.out.println("Complicated Implementation Postgre");
        }

        @Override
        public void delete(String customerId, String address) {
            System.out.println("Complicated Implementation Postgre");
        }

        @Override
        public void update(String customerId) {
            System.out.println("Complicated Implementation Postgre");
        }
    }

    public static class AddressServiceImplMongo implements AddressService {

        @Override
        public void save(String customerId, String address, String city) {
            System.out.println("Complicated Implementation mongo");
        }

        @Override
        public void delete(String customerId, String address) {
            System.out.println("Complicated Implementation mongo");
        }

        @Override
        public void update(String customerId) {
            System.out.println("Complicated Implementation mongo");
        }

    }

    @SpringBootApplication
    public static class Application {

        @Bean("postgre")
        public AddressService addressServicePostgre() {
            return new AddressServiceImplPostgre();
        }

        @Bean("mongo")
        public AddressService addressServiceMongo() {
            return new AddressServiceImplMongo();
        }

        @Bean
        public AddressController addressController(@Qualifier("postgre") AddressService addressService) {
            AddressController controller = new AddressController();
            controller.setAddressService(addressService);
            return controller;
        }

    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class);
        AddressController controller = context.getBean(AddressController.class);

        controller.saveAddress("", "", "");
    }
}
