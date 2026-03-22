package com.jobportal.jobportal.dto;

public class EmployerDashboardDTO {

    private Long jobId;
    private String jobTitle;
    private Long applicantCount;

    public EmployerDashboardDTO(Long jobId, String jobTitle, Long applicantCount) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.applicantCount = applicantCount;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Long getApplicantCount() {
        return applicantCount;
    }
}