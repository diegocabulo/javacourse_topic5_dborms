package com.datademo.datademojpa.controllers;

import com.datademo.datademojpa.domain.Student;
import com.datademo.datademojpa.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Boolean>> registerNewUser(@RequestBody Student student){
        studentService.addNewStudent(student);
        Map<String, Boolean> map = new HashMap<>();
        map.put("sucess", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }
}
