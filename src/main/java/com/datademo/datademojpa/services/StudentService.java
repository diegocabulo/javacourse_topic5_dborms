package com.datademo.datademojpa.services;

import com.datademo.datademojpa.domain.Student;
import com.datademo.datademojpa.exceptions.DBDBadRequestException;
import com.datademo.datademojpa.exceptions.DBDResourceNotFoundException;
import com.datademo.datademojpa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long studentId) throws DBDResourceNotFoundException{
        return studentRepository.findById(studentId);
    }

    public void addNewStudent(Student student) throws DBDBadRequestException{
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new DBDBadRequestException("Invalid details. failed to create account");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) throws DBDResourceNotFoundException {
        boolean studentExist = studentRepository.existsById(studentId);

        if(!studentExist){
            throw new DBDResourceNotFoundException("Student with ID "+ studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String firstName, String lastName, String email)
            throws DBDResourceNotFoundException,DBDBadRequestException{
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new DBDResourceNotFoundException("Student with ID "+ studentId + " does not exist"));
        if(firstName != null && firstName.length() > 0 && !Objects.equals(student.getFirstName(),firstName)){
            student.setFirstName(firstName);
        }
        if(lastName != null && lastName.length() > 0 && !Objects.equals(student.getLastName(),lastName)){
            student.setLastName(lastName);
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new DBDBadRequestException("Email Taken");
            }
            student.setEmail(email);
        }
    }
}
