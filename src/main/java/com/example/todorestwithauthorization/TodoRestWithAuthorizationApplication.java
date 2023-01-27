package com.example.todorestwithauthorization;

import com.example.todorestwithauthorization.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoRestWithAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoRestWithAuthorizationApplication.class, args);
    }


}
