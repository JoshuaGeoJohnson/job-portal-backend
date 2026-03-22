package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.model.Student;
import com.jobportal.jobportal.model.Employer;
import com.jobportal.jobportal.service.StudentService;
import com.jobportal.jobportal.service.EmployerService;
import com.jobportal.jobportal.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private EmployerService employerService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        Map<String, Object> response = new HashMap<>();

        Student student = studentService.login(email, password);
        if (student != null) {
            String token = JwtUtil.generateToken(email);

            response.put("role", "STUDENT");
            response.put("token", token);
            return response;
        }

        Employer employer = employerService.login(email, password);
        if (employer != null) {
            String token = JwtUtil.generateToken(email);

            response.put("role", "EMPLOYER");
            response.put("token", token);
            return response;
        }

        response.put("message", "Invalid credentials");
        return response;
    }
}