package com.ujjwal.jobtrackr.controller;

import com.ujjwal.jobtrackr.dto.InterviewRoundDTO;
import com.ujjwal.jobtrackr.dto.InterviewRoundResponseDTO;
import com.ujjwal.jobtrackr.service.InterviewRoundService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Nested resource URL pattern — industry standard
// /api/v1/jobs/{jobId}/rounds
@RestController
@RequestMapping("/api/v1/jobs/{jobId}/rounds")
public class InterviewRoundController {

    private final InterviewRoundService roundService;

    public InterviewRoundController(InterviewRoundService roundService) {
        this.roundService = roundService;
    }

    @GetMapping
    public ResponseEntity<List<InterviewRoundResponseDTO>> getAll(
            @PathVariable Long jobId) {
        return ResponseEntity.ok(roundService.getAllForJob(jobId));
    }

    @PostMapping
    public ResponseEntity<InterviewRoundResponseDTO> create(
            @PathVariable Long jobId,
            @Valid @RequestBody InterviewRoundDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roundService.create(jobId, dto));
    }

    @PutMapping("/{roundId}")
    public ResponseEntity<InterviewRoundResponseDTO> update(
            @PathVariable Long jobId,
            @PathVariable Long roundId,
            @Valid @RequestBody InterviewRoundDTO dto) {
        return ResponseEntity.ok(
                roundService.update(jobId, roundId, dto));
    }

    @DeleteMapping("/{roundId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long jobId,
            @PathVariable Long roundId) {
        roundService.delete(jobId, roundId);
        return ResponseEntity.noContent().build();
    }
}