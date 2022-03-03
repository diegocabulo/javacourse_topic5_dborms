package com.datademo.datademojpa.services;

import com.datademo.datademojpa.domain.Course;
import com.datademo.datademojpa.exceptions.DBDBadRequestException;
import com.datademo.datademojpa.exceptions.DBDResourceNotFoundException;
import com.datademo.datademojpa.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses(){return courseRepository.findAll();}

    public Course getCourseById(Long courseId) throws DBDResourceNotFoundException{
        return courseRepository.findById(courseId).orElseThrow(()->
                new DBDResourceNotFoundException("Student with ID "+ courseId + " does not exist"));
    }

    public void addNewCourse(Course course) throws DBDBadRequestException{
        Optional<Course> courseOptional = courseRepository.findCourseByName(course.getCourseName());

        if(courseOptional.isPresent()){
            throw new DBDBadRequestException("Invalid details. failed to create course");
        }
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) throws DBDResourceNotFoundException {
        boolean studentExist = courseRepository.existsById(courseId);

        if(!studentExist){
            throw new DBDResourceNotFoundException("Course with ID "+ courseId + " does not exist");
        }
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void updateCourse(Long courseId, String courseName, String courseType)
            throws DBDResourceNotFoundException,DBDBadRequestException{
        Course course = courseRepository.findById(courseId).orElseThrow(()->
                new DBDResourceNotFoundException("Student with ID "+ courseId + " does not exist"));
        if(courseType != null && courseType.length() > 0 && !Objects.equals(course.getCourseType(),courseType)){
            course.setCourseType(courseType);
        }
        if(courseName != null && courseName.length() > 0 && !Objects.equals(course.getCourseName(),courseName)){
            Optional<Course> courseOptional = courseRepository.findCourseByName(courseName);
            if(courseOptional.isPresent()){
                throw new DBDBadRequestException("course name Taken");
            }
            course.setCourseName(courseName);
        }
    }



}
