package com.rashiid.demo.service;

import com.rashiid.demo.exception.StudentAlreadyExistsException;
import com.rashiid.demo.exception.StudentNotFoundException;
import com.rashiid.demo.model.Student;
import com.rashiid.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if (studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistsException(student.getEmail() + "Already exists!");
        }

        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student, long id) {
        return studentRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());

            return studentRepository.save(student);

        }).orElseThrow(()-> new StudentNotFoundException("Sorry this student could not be found"));
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Sorry, no student found with the id: " + id));
    }

    @Override
    public void deleteStudent(long id) {
        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Sorry, student not found!!");
        }

    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
