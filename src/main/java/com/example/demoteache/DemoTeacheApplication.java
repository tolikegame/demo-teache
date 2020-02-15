package com.example.demoteache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.demoteache.*")
@EntityScan(basePackages = "com.example.demoteache.model.po")
@EnableJpaRepositories(basePackages = "com.example.demoteache.repository")
//@ComponentScan(basePackages = "com.example.demoteache.*")
public class DemoTeacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTeacheApplication.class, args);
    }

}
