package com.datademo.datademojpa.controllers;

import com.datademo.datademojpa.domain.Course;
import com.datademo.datademojpa.domain.Student;
import com.datademo.datademojpa.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Boolean>> registerNewCourse(@RequestBody Course course) {
        courseService.addNewCourse(course);
        Map<String, Boolean> map = new HashMap<>();
        map.put("sucess", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
