package com.jobportal.jobportal.service;

import com.jobportal.jobportal.dto.ApplicantDTO;
import com.jobportal.jobportal.dto.StudentDashboardDTO;
import com.jobportal.jobportal.model.Application;
import com.jobportal.jobportal.model.Job;
import com.jobportal.jobportal.model.Student;
import com.jobportal.jobportal.repository.ApplicationRepository;
import com.jobportal.jobportal.repository.JobRepository;
import com.jobportal.jobportal.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private StudentRepository studentRepository;

    // ✅ APPLY JOB
    public String apply(Long jobId, Student student) {

        Optional<Application> existing =
                applicationRepository.findByStudentIdAndJobId(student.getId(), jobId);

        if (existing.isPresent()) {
            return "Already applied";
        }

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Application app = new Application();
        app.setStudentId(student.getId());
        app.setJobId(job.getId());
        app.setStatus("APPLIED");

        applicationRepository.save(app);

        return "Application submitted";
    }

    // ✅ MY APPLICATIONS
    public List<Application> getMyApplications(String email) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return applicationRepository.findByStudentId(student.getId());
    }

    // ✅ GET APPLICANTS
    public List<ApplicantDTO> getApplicants(Long jobId) {

        List<Application> applications = applicationRepository.findByJobId(jobId);

        return applications.stream().map(app -> {
            Student student = studentRepository.findById(app.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            return new ApplicantDTO(
                    student.getName(),
                    student.getEmail(),
                    app.getStatus()
            );
        }).collect(Collectors.toList());
    }

    // ✅ UPDATE STATUS
    public String updateStatus(Long id, String status) {

        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (app.getStatus().equals("ACCEPTED")) {
            return "Already accepted";
        }

        if (!status.equals("ACCEPTED") && !status.equals("REJECTED")) {
            return "Invalid status";
        }

        app.setStatus(status);
        applicationRepository.save(app);

        return "Updated";
    }
    public List<StudentDashboardDTO> getStudentDashboard(String email) {

    Student student = studentRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Student not found"));

    List<Application> apps = applicationRepository.findByStudentId(student.getId());

    return apps.stream().map(app -> {
        Job job = jobRepository.findById(app.getJobId()).orElse(null);

        return new StudentDashboardDTO(
                app.getId(),
                job != null ? job.getTitle() : "Unknown",
                app.getStatus()
        );
    }).toList();
}
}