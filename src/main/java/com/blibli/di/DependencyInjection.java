package com.blibli.di;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class DependencyInjection {

    @SpringBootApplication
    public static class Application{

//
//        @Bean
//        public Foo foo(){
//            return new Foo();
//        }

        @Component
        public static class Foo{
            private Bar bar;

            public Foo(Bar bar){
                this.bar = bar;
            }

            // Atau bisa, kalau pakai ini bisa langsung ada constructornya dan gak error. tpi Cyclic in general is bad
//            @Autowired
//            private Bar bar;

        }

        @Component
        @Data
        public static class Bar { // CIRCULAR DENGAN FOO
            private Foo foo;

            public Bar(Foo foo){
                this.foo = foo;
            }
        }

        public static void main(String[] args) {
            ApplicationContext context = SpringApplication.run(Application.class);

            Application.Foo foo = context.getBean(Application.Foo.class);
            Application.Bar bar = context.getBean(Application.Bar.class);

            System.out.println(foo == bar.getFoo());

        }

    }
}
