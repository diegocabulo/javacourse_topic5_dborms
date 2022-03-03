package com.datademo.datademojpa.repositories;

import com.datademo.datademojpa.domain.Course;
import com.datademo.datademojpa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("SELECT s FROM Course s where s.courseName = ?1")
    Optional<Course> findCourseByName(String course);
}
