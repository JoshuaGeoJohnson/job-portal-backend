package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.model.Student;
import com.jobportal.jobportal.service.StudentService;
import com.jobportal.jobportal.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public Object getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/register")
    public Object register(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    @PostMapping("/login")
public Object login(@RequestBody Student student) {

    Student result = studentService.login(
            student.getEmail(),
            student.getPassword()
    );

    if (result != null) {

        String token = JwtUtil.generateToken(
                student.getEmail()  // 🔥 IMPORTANT
        );

        return token;
    } else {
        return "Invalid Email or Password";
    }
}
}