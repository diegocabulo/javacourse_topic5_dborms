package com.datademo.datademojpa.services;

import com.datademo.datademojpa.domain.Course;
import com.datademo.datademojpa.domain.Student;
import com.datademo.datademojpa.exceptions.DBDBadRequestException;
import com.datademo.datademojpa.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses(){return courseRepository.findAll();}

    public void addNewCourse(Course course) throws DBDBadRequestException{
        Optional<Course> courseOptional = courseRepository.findCourseByName(course.getCourseName());

        if(courseOptional.isPresent()){
            throw new DBDBadRequestException("Invalid details. failed to create course");
        }
        courseRepository.save(course);
    }



}
