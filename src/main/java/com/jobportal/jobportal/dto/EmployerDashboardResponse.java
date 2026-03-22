package com.jobportal.jobportal.dto;

public class EmployerDashboardResponse {

    private String studentName;
    private String studentEmail;
    private String jobTitle;
    private String company;
    private String status;

    public EmployerDashboardResponse(String studentName, String studentEmail,
                                     String jobTitle, String company, String status) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.jobTitle = jobTitle;
        this.company = company;
        this.status = status;
    }

    public String getStudentName() { return studentName; }
    public String getStudentEmail() { return studentEmail; }
    public String getJobTitle() { return jobTitle; }
    public String getCompany() { return company; }
    public String getStatus() { return status; }
}