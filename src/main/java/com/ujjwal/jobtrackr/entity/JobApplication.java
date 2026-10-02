package com.ujjwal.jobtrackr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "jobApplication",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<InterviewRound> interviewRounds = new ArrayList<>();

    @NotBlank(message = "Company name is required")
    @Column(nullable = false)
    private String companyName;

    @NotBlank(message = "Role is required")
    @Column(nullable = false)
    private String role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // ── Constructors ──────────────────────────────
    public JobApplication() {}

    public JobApplication(Long id, String companyName, String role,
                          ApplicationStatus status, String jobUrl,
                          String location, Integer salaryMin,
                          Integer salaryMax, String notes,
                          String recruiterName, String recruiterEmail,
                          LocalDateTime appliedDate,
                          LocalDateTime nextInterviewDate) {
        this.id = id;
        this.companyName = companyName;
        this.role = role;
        this.status = status;
        this.jobUrl = jobUrl;
        this.location = location;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.notes = notes;
        this.recruiterName = recruiterName;
        this.recruiterEmail = recruiterEmail;
        this.appliedDate = appliedDate;
        this.nextInterviewDate = nextInterviewDate;
    }

    // ── Getters ───────────────────────────────────
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
    public User getUser() { return user; }
    public List<InterviewRound> getInterviewRounds() {
        return interviewRounds;
    }


    // ── Setters ───────────────────────────────────
    public void setId(Long id) { this.id = id; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setRole(String role) { this.role = role; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public void setJobUrl(String jobUrl) { this.jobUrl = jobUrl; }
    public void setLocation(String location) { this.location = location; }
    public void setSalaryMin(Integer salaryMin) { this.salaryMin = salaryMin; }
    public void setSalaryMax(Integer salaryMax) { this.salaryMax = salaryMax; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setRecruiterName(String recruiterName) { this.recruiterName = recruiterName; }
    public void setRecruiterEmail(String recruiterEmail) { this.recruiterEmail = recruiterEmail; }
    public void setAppliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; }
    public void setNextInterviewDate(LocalDateTime nextInterviewDate) { this.nextInterviewDate = nextInterviewDate; }
    public void setUser(User user) { this.user = user; }

    // ── Builder ───────────────────────────────────
    public static Builder builder() { return new Builder(); }

    public static class Builder {
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
        private User user;


        public Builder id(Long id) { this.id = id; return this; }
        public Builder companyName(String v) { this.companyName = v; return this; }
        public Builder role(String v) { this.role = v; return this; }
        public Builder status(ApplicationStatus v) { this.status = v; return this; }
        public Builder jobUrl(String v) { this.jobUrl = v; return this; }
        public Builder location(String v) { this.location = v; return this; }
        public Builder salaryMin(Integer v) { this.salaryMin = v; return this; }
        public Builder salaryMax(Integer v) { this.salaryMax = v; return this; }
        public Builder notes(String v) { this.notes = v; return this; }
        public Builder recruiterName(String v) { this.recruiterName = v; return this; }
        public Builder recruiterEmail(String v) { this.recruiterEmail = v; return this; }
        public Builder appliedDate(LocalDateTime v) { this.appliedDate = v; return this; }
        public Builder nextInterviewDate(LocalDateTime v) { this.nextInterviewDate = v; return this; }
        public Builder user(User v) { this.user = v; return this; }


        public JobApplication build() {
            JobApplication j = new JobApplication();
            j.id = this.id;
            j.companyName = this.companyName;
            j.role = this.role;
            j.status = this.status;
            j.jobUrl = this.jobUrl;
            j.location = this.location;
            j.salaryMin = this.salaryMin;
            j.salaryMax = this.salaryMax;
            j.notes = this.notes;
            j.recruiterName = this.recruiterName;
            j.recruiterEmail = this.recruiterEmail;
            j.appliedDate = this.appliedDate;
            j.nextInterviewDate = this.nextInterviewDate;
            j.user = this.user;
            return j;
        }
    }
}