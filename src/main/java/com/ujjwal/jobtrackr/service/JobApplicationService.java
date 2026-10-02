package com.ujjwal.jobtrackr.service;

import com.ujjwal.jobtrackr.dto.JobApplicationDTO;
import com.ujjwal.jobtrackr.dto.JobStatsDTO;
import com.ujjwal.jobtrackr.entity.ApplicationStatus;
import com.ujjwal.jobtrackr.entity.JobApplication;
import com.ujjwal.jobtrackr.entity.User;
import com.ujjwal.jobtrackr.exception.BadRequestException;
import com.ujjwal.jobtrackr.exception.ResourceNotFoundException;
import com.ujjwal.jobtrackr.repository.JobApplicationRepository;
import com.ujjwal.jobtrackr.security.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;
    private final SecurityUtils securityUtils;

    public JobApplicationService(JobApplicationRepository repository,
                                 SecurityUtils securityUtils) {
        this.repository = repository;
        this.securityUtils = securityUtils;
    }

    public String getCurrentUserEmail() {
        return securityUtils.getCurrentUser().getEmail();
    }

    // Get all jobs for current user — paginated + filtered
    public Page<JobApplication> getAll(
            int page, int size, String sortBy,
            String sortDir, ApplicationStatus status,
            String company) {

        User currentUser = securityUtils.getCurrentUser();

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        // If filters provided use filtered query
        if (status != null || company != null) {
            return repository.findByUserWithFilters(
                    currentUser, status, company, pageable);
        }

        return repository.findByUser(currentUser, pageable);
    }

    // Get single job — only if it belongs to current user
    public JobApplication getById(Long id) {
        User currentUser = securityUtils.getCurrentUser();
        return repository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "JobApplication", id));
    }

    // Create job — auto assign to current user
    @CacheEvict(value = "jobStats", key = "#root.target.getCurrentUserEmail()")
    public JobApplication create(JobApplicationDTO dto) {
        User currentUser = securityUtils.getCurrentUser();

        JobApplication job = JobApplication.builder()
                .companyName(dto.getCompanyName())
                .role(dto.getRole())
                .status(dto.getStatus())
                .jobUrl(dto.getJobUrl())
                .location(dto.getLocation())
                .salaryMin(dto.getSalaryMin())
                .salaryMax(dto.getSalaryMax())
                .notes(dto.getNotes())
                .recruiterName(dto.getRecruiterName())
                .recruiterEmail(dto.getRecruiterEmail())
                .appliedDate(dto.getAppliedDate())
                .nextInterviewDate(dto.getNextInterviewDate())
                .user(currentUser) // ← assign to logged in user
                .build();

        return repository.save(job);
    }

    // Update — only if job belongs to current user
    @CacheEvict(value = "jobStats", key = "#root.target.getCurrentUserEmail()")
    public JobApplication update(Long id, JobApplicationDTO dto) {
        JobApplication existing = getById(id); // already checks ownership

        existing.setCompanyName(dto.getCompanyName());
        existing.setRole(dto.getRole());
        existing.setStatus(dto.getStatus());
        existing.setJobUrl(dto.getJobUrl());
        existing.setLocation(dto.getLocation());
        existing.setSalaryMin(dto.getSalaryMin());
        existing.setSalaryMax(dto.getSalaryMax());
        existing.setNotes(dto.getNotes());
        existing.setRecruiterName(dto.getRecruiterName());
        existing.setRecruiterEmail(dto.getRecruiterEmail());
        existing.setAppliedDate(dto.getAppliedDate());
        existing.setNextInterviewDate(dto.getNextInterviewDate());

        return repository.save(existing);
    }

    // Delete — only if job belongs to current user
    @CacheEvict(value = "jobStats", key = "#root.target.getCurrentUserEmail()")
    public void delete(Long id) {
        JobApplication job = getById(id); // checks ownership
        repository.delete(job);
    }



    // Dashboard stats for current user
    @Cacheable(value = "jobStats", key = "#root.target.getCurrentUserEmail()")
    public JobStatsDTO getStats() {
        User currentUser = securityUtils.getCurrentUser();

        List<Object[]> results = repository
                .countByStatusForUser(currentUser);

        Map<String, Long> byStatus = new HashMap<>();
        long total = 0;

        for (Object[] row : results) {
            String status = row[0].toString();
            Long count = (Long) row[1];
            byStatus.put(status, count);
            total += count;
        }

        return new JobStatsDTO(total, byStatus);
    }
}