package ru.sbt.employees;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJPAApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJPAApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
