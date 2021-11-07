package com.example.ecommerceappdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ECommerceAppDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ECommerceAppDemoApplication.class, args);

        Logger logger = LoggerFactory.getLogger(ECommerceAppDemoApplication.class);
        logger.info("==================================================\n" +
                "======================================================\n" +
                "                      Login For Admin\n" +
                "                   Username   => thura13@gmail.com              \n" +
                "                   Password   => thura                          \n" +
                "=================================================\n" +
                "===================================================");
    }



}
