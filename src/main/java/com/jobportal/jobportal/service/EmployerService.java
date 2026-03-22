package com.jobportal.jobportal.service;

import com.jobportal.jobportal.model.Employer;
import com.jobportal.jobportal.repository.EmployerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    // ✅ REGISTER
    public Object register(Employer employer) {

        Optional<Employer> existing = employerRepository.findByEmail(employer.getEmail());

        if (existing.isPresent()) {
            return "Email already exists";
        }

        return employerRepository.save(employer);
    }

    // ✅ LOGIN (FIXED)
    public Employer login(String email, String password) {

        if (email == null || password == null) {
            return null;
        }

        Optional<Employer> optional = employerRepository.findByEmail(email);

        if (optional.isPresent()) {
            Employer e = optional.get();

            if (e.getPassword() != null && e.getPassword().equals(password)) {
                return e;
            }
        }

        return null;
    }

    // ✅ IMPORTANT (FOR JWT)
    public Employer getEmployerByEmail(String email) {
        return employerRepository.findByEmail(email).orElse(null);
    }
}