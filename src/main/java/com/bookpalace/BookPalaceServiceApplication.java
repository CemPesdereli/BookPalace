package com.bookpalace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookPalaceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookPalaceServiceApplication.class, args);
    }

/*
    @Bean
    public CustomerController customerController() {
        return new CustomerController(customerService());
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerService(customerRepository());
    }

    @Bean
    public CustomerRepository customerRepository() {
        return new CustomerRepository();
    }
*/
}
