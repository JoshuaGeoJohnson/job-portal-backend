package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.model.Job;
import com.jobportal.jobportal.dto.EmployerDashboardDTO;
import com.jobportal.jobportal.model.Employer;
import com.jobportal.jobportal.service.JobService;
import com.jobportal.jobportal.service.EmployerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private EmployerService employerService;

    // ✅ ADD JOB (AUTO EMPLOYER)
    @PostMapping("/add")
    public Job addJob(@RequestBody Job job) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Employer employer = employerService.getEmployerByEmail(email);

        if (employer == null) {
            throw new RuntimeException("Employer not found");
        }

        job.setEmployerId(employer.getId()); // 🔥 IMPORTANT

        return jobService.addJob(job);
    }

    // ✅ VIEW ALL JOBS
    @GetMapping("/all")
public Page<Job> getAllJobs(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy
) {
    return jobService.getJobsPaginated(page, size, sortBy);
}
    // ✅ SEARCH BY TITLE
    @GetMapping("/search/title")
    public List<Job> searchByTitle(@RequestParam String title) {
        return jobService.searchByTitle(title);
    }

    // ✅ SEARCH BY LOCATION
    @GetMapping("/search/location")
    public List<Job> searchByLocation(@RequestParam String location) {
        return jobService.searchByLocation(location);
    }

    // ✅ EMPLOYER: MY JOBS (AUTO)
    @GetMapping("/my")
    public List<Job> myJobs() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Employer employer = employerService.getEmployerByEmail(email);

        return jobService.getJobsByEmployer(employer.getId());
    }
    @GetMapping("/dashboard")
public List<EmployerDashboardDTO> employerDashboard() {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    Employer employer = employerService.getEmployerByEmail(email);

    return jobService.getEmployerDashboard(employer.getId());
}
}