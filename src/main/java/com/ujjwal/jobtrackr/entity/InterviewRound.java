package com.ujjwal.jobtrackr.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interview_rounds")
public class InterviewRound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ManyToOne — many rounds belong to one job
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_application_id", nullable = false)
    private JobApplication jobApplication;

    @Column(nullable = false)
    private Integer roundNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoundType type;

    @Enumerated(EnumType.STRING)
    private RoundOutcome outcome = RoundOutcome.PENDING;

    private LocalDateTime scheduledAt;
    private String interviewerName;
    private String feedback;
    private String notes;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters
    public Long getId() { return id; }
    public JobApplication getJobApplication() { return jobApplication; }
    public Integer getRoundNumber() { return roundNumber; }
    public RoundType getType() { return type; }
    public RoundOutcome getOutcome() { return outcome; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public String getInterviewerName() { return interviewerName; }
    public String getFeedback() { return feedback; }
    public String getNotes() { return notes; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setJobApplication(JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }
    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }
    public void setType(RoundType type) { this.type = type; }
    public void setOutcome(RoundOutcome outcome) { this.outcome = outcome; }
    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }
    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    public void setNotes(String notes) { this.notes = notes; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private JobApplication jobApplication;
        private Integer roundNumber;
        private RoundType type;
        private RoundOutcome outcome = RoundOutcome.PENDING;
        private LocalDateTime scheduledAt;
        private String interviewerName;
        private String feedback;
        private String notes;

        public Builder jobApplication(JobApplication v) {
            this.jobApplication = v; return this;
        }
        public Builder roundNumber(Integer v) {
            this.roundNumber = v; return this;
        }
        public Builder type(RoundType v) { this.type = v; return this; }
        public Builder outcome(RoundOutcome v) {
            this.outcome = v; return this;
        }
        public Builder scheduledAt(LocalDateTime v) {
            this.scheduledAt = v; return this;
        }
        public Builder interviewerName(String v) {
            this.interviewerName = v; return this;
        }
        public Builder feedback(String v) { this.feedback = v; return this; }
        public Builder notes(String v) { this.notes = v; return this; }

        public InterviewRound build() {
            InterviewRound r = new InterviewRound();
            r.jobApplication = this.jobApplication;
            r.roundNumber = this.roundNumber;
            r.type = this.type;
            r.outcome = this.outcome;
            r.scheduledAt = this.scheduledAt;
            r.interviewerName = this.interviewerName;
            r.feedback = this.feedback;
            r.notes = this.notes;
            return r;
        }
    }
}