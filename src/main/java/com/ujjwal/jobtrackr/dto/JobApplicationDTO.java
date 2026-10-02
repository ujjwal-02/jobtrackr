package com.ujjwal.jobtrackr.dto;

import com.ujjwal.jobtrackr.entity.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class JobApplicationDTO {

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Role is required")
    private String role;

    @NotNull(message = "Status is required")
    private ApplicationStatus status;

    private String jobUrl;
    private String location;
    private Integer salaryMin;
    private Integer salaryMax;
    private String notes;
    private String recruiterName;
    private String recruiterEmail;
    private LocalDateTime appliedDate;
    private LocalDateTime nextInterviewDate;

    // Getters
    public String getCompanyName() { return companyName; }
    public String getRole() { return role; }
    public ApplicationStatus getStatus() { return status; }
    public String getJobUrl() { return jobUrl; }
    public String getLocation() { return location; }
    public Integer getSalaryMin() { return salaryMin; }
    public Integer getSalaryMax() { return salaryMax; }
    public String getNotes() { return notes; }
    public String getRecruiterName() { return recruiterName; }
    public String getRecruiterEmail() { return recruiterEmail; }
    public LocalDateTime getAppliedDate() { return appliedDate; }
    public LocalDateTime getNextInterviewDate() { return nextInterviewDate; }

    // Setters
    public void setCompanyName(String v) { this.companyName = v; }
    public void setRole(String v) { this.role = v; }
    public void setStatus(ApplicationStatus v) { this.status = v; }
    public void setJobUrl(String v) { this.jobUrl = v; }
    public void setLocation(String v) { this.location = v; }
    public void setSalaryMin(Integer v) { this.salaryMin = v; }
    public void setSalaryMax(Integer v) { this.salaryMax = v; }
    public void setNotes(String v) { this.notes = v; }
    public void setRecruiterName(String v) { this.recruiterName = v; }
    public void setRecruiterEmail(String v) { this.recruiterEmail = v; }
    public void setAppliedDate(LocalDateTime v) { this.appliedDate = v; }
    public void setNextInterviewDate(LocalDateTime v) { this.nextInterviewDate = v; }
}