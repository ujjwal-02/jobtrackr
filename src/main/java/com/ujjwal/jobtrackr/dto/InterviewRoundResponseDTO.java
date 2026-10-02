package com.ujjwal.jobtrackr.dto;

import com.ujjwal.jobtrackr.entity.InterviewRound;
import com.ujjwal.jobtrackr.entity.RoundOutcome;
import com.ujjwal.jobtrackr.entity.RoundType;

import java.time.LocalDateTime;

public class InterviewRoundResponseDTO {

    private Long id;
    private Long jobApplicationId;
    private String companyName;
    private Integer roundNumber;
    private RoundType type;
    private RoundOutcome outcome;
    private LocalDateTime scheduledAt;
    private String interviewerName;
    private String feedback;
    private String notes;
    private LocalDateTime createdAt;

    public static InterviewRoundResponseDTO from(InterviewRound round) {
        InterviewRoundResponseDTO dto = new InterviewRoundResponseDTO();
        dto.id = round.getId();
        dto.jobApplicationId = round.getJobApplication().getId();
        dto.companyName = round.getJobApplication().getCompanyName();
        dto.roundNumber = round.getRoundNumber();
        dto.type = round.getType();
        dto.outcome = round.getOutcome();
        dto.scheduledAt = round.getScheduledAt();
        dto.interviewerName = round.getInterviewerName();
        dto.feedback = round.getFeedback();
        dto.notes = round.getNotes();
        dto.createdAt = round.getCreatedAt();
        return dto;
    }

    // Getters
    public Long getId() { return id; }
    public Long getJobApplicationId() { return jobApplicationId; }
    public String getCompanyName() { return companyName; }
    public Integer getRoundNumber() { return roundNumber; }
    public RoundType getType() { return type; }
    public RoundOutcome getOutcome() { return outcome; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public String getInterviewerName() { return interviewerName; }
    public String getFeedback() { return feedback; }
    public String getNotes() { return notes; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}