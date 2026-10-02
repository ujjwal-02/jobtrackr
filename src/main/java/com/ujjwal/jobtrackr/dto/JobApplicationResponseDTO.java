package com.ujjwal.jobtrackr.dto;

import com.ujjwal.jobtrackr.entity.ApplicationStatus;
import com.ujjwal.jobtrackr.entity.JobApplication;

import java.time.LocalDateTime;

public class JobApplicationResponseDTO {

    private Long id;
    private String companyName;
    private String role;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserSummaryDTO user; // ← only safe user fields

    // Static factory method — converts Entity to DTO
    // This is the "Mapper" pattern
    public static JobApplicationResponseDTO from(JobApplication job) {
        JobApplicationResponseDTO dto = new JobApplicationResponseDTO();
        dto.id = job.getId();
        dto.companyName = job.getCompanyName();
        dto.role = job.getRole();
        dto.status = job.getStatus();
        dto.jobUrl = job.getJobUrl();
        dto.location = job.getLocation();
        dto.salaryMin = job.getSalaryMin();
        dto.salaryMax = job.getSalaryMax();
        dto.notes = job.getNotes();
        dto.recruiterName = job.getRecruiterName();
        dto.recruiterEmail = job.getRecruiterEmail();
        dto.appliedDate = job.getAppliedDate();
        dto.nextInterviewDate = job.getNextInterviewDate();
        dto.createdAt = job.getCreatedAt();
        dto.updatedAt = job.getUpdatedAt();
        dto.user = new UserSummaryDTO(
                job.getUser().getId(),
                job.getUser().getName(),
                job.getUser().getEmail()
        );
        return dto;
    }

    // Getters
    public Long getId() { return id; }
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
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public UserSummaryDTO getUser() { return user; }
}