package com.lsr.estudospringjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class EstudoSpringJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstudoSpringJdbcApplication.class, args);
    }

}
