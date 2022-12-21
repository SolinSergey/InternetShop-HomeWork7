package ru.gb.internetshop.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://cloud.spring.io/spring-cloud-gateway/reference/html/

@SpringBootApplication
public class InternetShopGateway {
    public static void main(String[] args) {
        SpringApplication.run(InternetShopGateway.class, args);
    }
}