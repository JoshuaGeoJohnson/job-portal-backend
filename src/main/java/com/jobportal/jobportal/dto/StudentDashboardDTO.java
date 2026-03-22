package com.jobportal.jobportal.dto;

public class StudentDashboardDTO {

    private Long applicationId;
    private String jobTitle;
    private String status;

    public StudentDashboardDTO(Long applicationId, String jobTitle, String status) {
        this.applicationId = applicationId;
        this.jobTitle = jobTitle;
        this.status = status;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getStatus() {
        return status;
    }
}