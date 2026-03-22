package com.jobportal.jobportal.service;

import com.jobportal.jobportal.model.Student;
import com.jobportal.jobportal.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // ✅ REGISTER STUDENT
    public Object registerStudent(Student student) {

        Optional<Student> existing = studentRepository.findByEmail(student.getEmail());

        if (existing.isPresent()) {
            return "Email already exists";
        }

        return studentRepository.save(student);
    }

    // ✅ LOGIN
    public Student login(String email, String password) {

        if (email == null || password == null) {
            return null;
        }

        Optional<Student> optionalStudent = studentRepository.findByEmail(email);

        if (optionalStudent.isPresent()) {
            Student s = optionalStudent.get();

            if (s.getPassword() != null && s.getPassword().equals(password)) {
                return s;
            }
        }

        return null;
    }

    // ✅ GET ALL STUDENTS
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // ✅ VERY IMPORTANT (FIXED)
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email).orElse(null);
    }
}