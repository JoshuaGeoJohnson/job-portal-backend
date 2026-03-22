package com.jobportal.jobportal.dto;

public class ApplicationResponse {

    private String jobTitle;
    private String company;
    private String location;
    private String status;

    public ApplicationResponse(String jobTitle, String company, String location, String status) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.status = status;
    }

    public String getJobTitle() { return jobTitle; }
    public String getCompany() { return company; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }
}   