package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@EnableFeignClients
@PropertySource("classpath:/application.properties")
@SpringBootApplication()
public class SpaceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpaceServiceApplication.class, args);
    }
}