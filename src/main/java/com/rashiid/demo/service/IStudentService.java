package com.rashiid.demo.service;

import com.rashiid.demo.model.Student;
import java.util.List;

/**
 * Interface for student-related operations.
 */
public interface IStudentService {

    /**
     * Adds a new student.
     */
    Student addStudent(Student student);

    /**
     * Retrieves all students.
     */
    List<Student> getStudents();

    /**
     * Updates an existing student by ID.
     */
    Student updateStudent(Student student, long id);

    /**
     * Retrieves a student by ID.
     */
    Student getStudentById(long id);

    /**
     * Deletes a student by ID.
     */
    void deleteStudent(long id);
}
