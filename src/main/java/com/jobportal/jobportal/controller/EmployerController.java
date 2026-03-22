package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.model.Employer;
import com.jobportal.jobportal.service.EmployerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @PostMapping("/register")
    public Object register(@RequestBody Employer employer) {
        return employerService.register(employer);
    }

    @PostMapping("/login")
    public Object login(@RequestBody Employer employer) {
        return employerService.login(employer.getEmail(), employer.getPassword());
    }
}