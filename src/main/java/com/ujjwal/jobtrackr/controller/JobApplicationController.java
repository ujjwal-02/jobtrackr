package com.ujjwal.jobtrackr.controller;

import com.ujjwal.jobtrackr.dto.JobApplicationDTO;
import com.ujjwal.jobtrackr.dto.JobApplicationResponseDTO;
import com.ujjwal.jobtrackr.dto.JobStatsDTO;
import com.ujjwal.jobtrackr.entity.ApplicationStatus;
import com.ujjwal.jobtrackr.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<JobApplicationResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sort,
            @RequestParam(defaultValue = "desc") String dir,
            @RequestParam(required = false) ApplicationStatus status,
            @RequestParam(required = false) String company) {

        // Map Page<Entity> to Page<DTO>
        Page<JobApplicationResponseDTO> response = service
                .getAll(page, size, sort, dir, status, company)
                .map(JobApplicationResponseDTO::from);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponseDTO> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                JobApplicationResponseDTO.from(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponseDTO> create(
            @Valid @RequestBody JobApplicationDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(JobApplicationResponseDTO.from(service.create(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplicationResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody JobApplicationDTO dto) {
        return ResponseEntity.ok(
                JobApplicationResponseDTO.from(service.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats")
    public ResponseEntity<JobStatsDTO> getStats() {
        return ResponseEntity.ok(service.getStats());
    }
}