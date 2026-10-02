package com.ujjwal.jobtrackr.dto;

import com.ujjwal.jobtrackr.entity.RoundOutcome;
import com.ujjwal.jobtrackr.entity.RoundType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class InterviewRoundDTO {

    @NotNull(message = "Round number is required")
    private Integer roundNumber;

    @NotNull(message = "Round type is required")
    private RoundType type;

    private RoundOutcome outcome;
    private LocalDateTime scheduledAt;
    private String interviewerName;
    private String feedback;
    private String notes;

    // Getters
    public Integer getRoundNumber() { return roundNumber; }
    public RoundType getType() { return type; }
    public RoundOutcome getOutcome() { return outcome; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public String getInterviewerName() { return interviewerName; }
    public String getFeedback() { return feedback; }
    public String getNotes() { return notes; }

    // Setters
    public void setRoundNumber(Integer v) { this.roundNumber = v; }
    public void setType(RoundType v) { this.type = v; }
    public void setOutcome(RoundOutcome v) { this.outcome = v; }
    public void setScheduledAt(LocalDateTime v) { this.scheduledAt = v; }
    public void setInterviewerName(String v) { this.interviewerName = v; }
    public void setFeedback(String v) { this.feedback = v; }
    public void setNotes(String v) { this.notes = v; }
}