package com.jobportal.jobportal.service;
import com.jobportal.jobportal.repository.ApplicationRepository;
import com.jobportal.jobportal.dto.EmployerDashboardDTO;
import com.jobportal.jobportal.model.Job;
import com.jobportal.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
private ApplicationRepository applicationRepository;

    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    public List<Job> searchByTitle(String title) {
    return jobRepository.findByTitleContainingIgnoreCase(title);
}

public List<Job> searchByLocation(String location) {
    return jobRepository.findByLocationContainingIgnoreCase(location);
}
public List<Job> getJobsByEmployer(Long employerId) {
    return jobRepository.findByEmployerId(employerId);
}
public List<EmployerDashboardDTO> getEmployerDashboard(Long employerId) {

    List<Job> jobs = jobRepository.findByEmployerId(employerId);

    return jobs.stream().map(job -> {

        Long count = applicationRepository.countByJobId(job.getId());

        return new EmployerDashboardDTO(
                job.getId(),
                job.getTitle(),
                count
        );

    }).toList();
}
public Page<Job> getJobsPaginated(int page, int size, String sortBy) {

    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

    return jobRepository.findAll(pageable);
}
}