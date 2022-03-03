package com.datademo.datademojpa.controllers;

import com.datademo.datademojpa.domain.Course;
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

    @GetMapping(path = "/{courseId}")
    public ResponseEntity<Course> getStudentById(@PathVariable("courseId") Long courseId) {
        Course course = courseService.getCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.CREATED);

    }

    @PostMapping
    public ResponseEntity<Map<String, Boolean>> registerNewCourse(@RequestBody Course course) {
        courseService.addNewCourse(course);
        Map<String, Boolean> map = new HashMap<>();
        map.put("sucess", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{courseId}")
    public ResponseEntity<Map<String, Boolean>> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("sucess", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping(path = "/{courseId}")
    public ResponseEntity<Map<String, Boolean>> updateStudent(@PathVariable("courseId") Long courseId,
                                                              @RequestBody Map<String, Object> courseMap) {
        String courseName = (String) courseMap.get("courseName");
        String courseType = (String) courseMap.get("courseType");
        courseService.updateCourse(courseId, courseName, courseType);
        Map<String, Boolean> map = new HashMap<>();
        map.put("sucess", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
