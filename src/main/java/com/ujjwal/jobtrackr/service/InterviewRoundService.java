package com.ujjwal.jobtrackr.service;

import com.ujjwal.jobtrackr.dto.InterviewRoundDTO;
import com.ujjwal.jobtrackr.dto.InterviewRoundResponseDTO;
import com.ujjwal.jobtrackr.entity.InterviewRound;
import com.ujjwal.jobtrackr.entity.JobApplication;
import com.ujjwal.jobtrackr.exception.ResourceNotFoundException;
import com.ujjwal.jobtrackr.repository.InterviewRoundRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewRoundService {

    private final InterviewRoundRepository roundRepository;
    private final JobApplicationService jobService;

    public InterviewRoundService(
            InterviewRoundRepository roundRepository,
            JobApplicationService jobService) {
        this.roundRepository = roundRepository;
        this.jobService = jobService;
    }

    // Get all rounds for a job — ownership checked by jobService
    public List<InterviewRoundResponseDTO> getAllForJob(Long jobId) {
        JobApplication job = jobService.getById(jobId);
        return roundRepository
                .findByJobApplicationOrderByRoundNumberAsc(job)
                .stream()
                .map(InterviewRoundResponseDTO::from)
                .collect(Collectors.toList());
    }

    // Add round to a job
    public InterviewRoundResponseDTO create(
            Long jobId, InterviewRoundDTO dto) {
        JobApplication job = jobService.getById(jobId);

        InterviewRound round = InterviewRound.builder()
                .jobApplication(job)
                .roundNumber(dto.getRoundNumber())
                .type(dto.getType())
                .outcome(dto.getOutcome() != null
                        ? dto.getOutcome()
                        : com.ujjwal.jobtrackr.entity.RoundOutcome.PENDING)
                .scheduledAt(dto.getScheduledAt())
                .interviewerName(dto.getInterviewerName())
                .feedback(dto.getFeedback())
                .notes(dto.getNotes())
                .build();

        return InterviewRoundResponseDTO.from(roundRepository.save(round));
    }

    // Update round outcome/feedback
    public InterviewRoundResponseDTO update(
            Long jobId, Long roundId, InterviewRoundDTO dto) {
        JobApplication job = jobService.getById(jobId);

        InterviewRound round = roundRepository
                .findByIdAndJobApplication(roundId, job)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "InterviewRound", roundId));

        round.setRoundNumber(dto.getRoundNumber());
        round.setType(dto.getType());
        if (dto.getOutcome() != null) round.setOutcome(dto.getOutcome());
        round.setScheduledAt(dto.getScheduledAt());
        round.setInterviewerName(dto.getInterviewerName());
        round.setFeedback(dto.getFeedback());
        round.setNotes(dto.getNotes());

        return InterviewRoundResponseDTO.from(roundRepository.save(round));
    }

    // Delete a round
    public void delete(Long jobId, Long roundId) {
        JobApplication job = jobService.getById(jobId);
        InterviewRound round = roundRepository
                .findByIdAndJobApplication(roundId, job)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "InterviewRound", roundId));
        roundRepository.delete(round);
    }
}