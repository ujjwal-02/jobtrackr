package com.ujjwal.jobtrackr.dto;

import java.util.Map;

public class JobStatsDTO {
    private long totalApplications;
    private Map<String, Long> byStatus;
    private long activeInterviews;
    private long offersReceived;

    public JobStatsDTO(long totalApplications,
                       Map<String, Long> byStatus) {
        this.totalApplications = totalApplications;
        this.byStatus = byStatus;
        this.activeInterviews = byStatus.entrySet().stream()
                .filter(e -> e.getKey().contains("INTERVIEW"))
                .mapToLong(Map.Entry::getValue)
                .sum();
        this.offersReceived = byStatus.getOrDefault(
                "OFFER_RECEIVED", 0L) +
                byStatus.getOrDefault("OFFER_ACCEPTED", 0L);
    }

    public long getTotalApplications() { return totalApplications; }
    public Map<String, Long> getByStatus() { return byStatus; }
    public long getActiveInterviews() { return activeInterviews; }
    public long getOffersReceived() { return offersReceived; }
}