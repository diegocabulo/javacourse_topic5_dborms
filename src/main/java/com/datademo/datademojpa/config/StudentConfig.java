package com.datademo.datademojpa.config;

import com.datademo.datademojpa.domain.Student;
import com.datademo.datademojpa.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student diego = new Student(
                    "diego",
                    "cabulo",
                    "diego.cabulo@email.com"
            );
            Student testStudent = new Student(
                    "testFN",
                    "testLN",
                    "testemail@email.com"
            );
            repository.saveAll(
                    List.of(diego,testStudent)
            );
        };
    }
}
