package com.jobportal.jobportal.service;

import com.jobportal.jobportal.model.Student;
import com.jobportal.jobportal.model.Employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private EmployerService employerService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // STUDENT
        Student student = studentService.getStudentByEmail(email);
        if (student != null) {
            return new User(
                    student.getEmail(),
                    student.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_STUDENT")) // ✅ MUST HAVE ROLE_
            );
        }

        // EMPLOYER
        Employer employer = employerService.getEmployerByEmail(email);
        if (employer != null) {
            return new User(
                    employer.getEmail(),
                    employer.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_EMPLOYER")) // ✅ MUST HAVE ROLE_
            );
        }

        throw new UsernameNotFoundException("User not found");
    }
}