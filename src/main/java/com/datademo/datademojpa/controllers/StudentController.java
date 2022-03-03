package com.datademo.datademojpa.controllers;

import com.datademo.datademojpa.domain.Student;
import com.datademo.datademojpa.exceptions.DBDResourceNotFoundException;
import com.datademo.datademojpa.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") Long studentId) throws DBDResourceNotFoundException {
        Optional<Student> student = studentService.getStudentById(studentId);
        if(student.isPresent()){
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        }
        else {
            throw new DBDResourceNotFoundException("Student with ID "+ studentId + " does not exist");
        }

    }

    @PostMapping
    public ResponseEntity<Map<String, Boolean>> registerNewUser(@RequestBody Student student){
        studentService.addNewStudent(student);
        Map<String, Boolean> map = new HashMap<>();
        map.put("sucess", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{studentId}")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("sucess", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping(path = "/{studentId}")
    public ResponseEntity<Map<String, Boolean>> updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Map<String, Object> studentMap){
        String firstName = (String) studentMap.get("firstname");
        String lastName = (String) studentMap.get("lastName");
        String email = (String) studentMap.get("email");
        studentService.updateStudent(studentId, firstName, lastName, email);
        Map<String, Boolean> map = new HashMap<>();
        map.put("sucess", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
