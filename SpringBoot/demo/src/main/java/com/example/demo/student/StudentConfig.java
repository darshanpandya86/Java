package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository){
        return args -> {
            Student darshan = new Student(
                    1L,
                    "darshan",
                    "dpandya@edgecast.com",
                    LocalDate.of(1986,12,11)
            );

            Student alex = new Student(
                    "alex",
                    "alex@edgecast.com",
                    LocalDate.of(2001,10,11)
            );

            repository.saveAll(
                    List.of(darshan,alex)
            );
        };
    }
}
