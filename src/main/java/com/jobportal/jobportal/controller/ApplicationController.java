package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.dto.ApplicantDTO;
import com.jobportal.jobportal.dto.ApplicationDTO;
import com.jobportal.jobportal.dto.StudentDashboardDTO;
import com.jobportal.jobportal.model.Application;
import com.jobportal.jobportal.model.Student;
import com.jobportal.jobportal.service.ApplicationService;
import com.jobportal.jobportal.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private StudentService studentService;

    // ✅ APPLY JOB
    @PostMapping("/apply/{jobId}")
    public String applyJob(@PathVariable Long jobId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Student student = studentService.getStudentByEmail(email);

        if (student == null) {
            return "Student not found";
        }

        return applicationService.apply(jobId, student);
    }

    // ✅ STUDENT: MY APPLICATIONS
    @GetMapping("/my")
    public List<ApplicationDTO> myApplications() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Application> apps = applicationService.getMyApplications(email);

        return apps.stream()
                .map(app -> new ApplicationDTO(
                        app.getId(),
                        app.getJobId(),
                        app.getStatus()
                ))
                .collect(Collectors.toList());
    }

    // ✅ EMPLOYER: VIEW APPLICANTS
    @GetMapping("/job/{jobId}")
    public List<ApplicantDTO> applicants(@PathVariable Long jobId) {
        return applicationService.getApplicants(jobId);
    }

    // ✅ EMPLOYER: UPDATE STATUS
    @PutMapping("/status/{id}")
    public String update(@PathVariable Long id,
                         @RequestBody ApplicationDTO req) {

        return applicationService.updateStatus(id, req.getStatus());
    }
    @GetMapping("/dashboard")
public List<StudentDashboardDTO> studentDashboard() {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    return applicationService.getStudentDashboard(email);
}
}