package com.jobportal.jobportal.dto;

public class ApplicationDTO {

    private Long id;
    private Long jobId;
    private String status;

    // ✅ EMPTY CONSTRUCTOR (required)
    public ApplicationDTO() {}

    // ✅ THIS IS WHAT YOU ARE MISSING 🔥
    public ApplicationDTO(Long id, Long jobId, String status) {
        this.id = id;
        this.jobId = jobId;
        this.status = status;
    }

    // GETTERS
    public Long getId() { return id; }
    public Long getJobId() { return jobId; }
    public String getStatus() { return status; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setJobId(Long jobId) { this.jobId = jobId; }
    public void setStatus(String status) { this.status = status; }
}