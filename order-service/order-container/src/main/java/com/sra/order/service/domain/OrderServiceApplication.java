package com.sra.order.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.ecommerce.app.order.service.dataaccess", "com.ecommerce.app.dataaccess" })
@EntityScan(basePackages = { "com.ecommerce.app.order.service.dataaccess", "com.ecommerce.app.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.ecommerce.app")
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
