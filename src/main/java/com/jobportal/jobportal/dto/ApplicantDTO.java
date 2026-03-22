package com.jobportal.jobportal.dto;
public class ApplicantDTO {
    private String name;
    private String email;
    private String status;

    public ApplicantDTO(String name, String email, String status) {
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getStatus() { return status; }
}