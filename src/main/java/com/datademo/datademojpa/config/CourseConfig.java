package com.datademo.datademojpa.config;

import com.datademo.datademojpa.domain.Course;
import com.datademo.datademojpa.repositories.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseConfig {

    @Bean
    CommandLineRunner commandLineRunnerCourse(CourseRepository repository){
        return args -> {
            Course testCourse = new Course(
                    "course 1",
                    "type 1"
            );
            repository.save(testCourse);
        };
    }
}
