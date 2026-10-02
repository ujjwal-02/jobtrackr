package com.ujjwal.jobtrackr.repository;

import com.ujjwal.jobtrackr.entity.ApplicationStatus;
import com.ujjwal.jobtrackr.entity.JobApplication;
import com.ujjwal.jobtrackr.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    // Get all jobs for a user — with pagination
    Page<JobApplication> findByUser(User user, Pageable pageable);

    // Get jobs by status for a user
    Page<JobApplication> findByUserAndStatus(
            User user, ApplicationStatus status, Pageable pageable);

    // Search by company name for a user
    Page<JobApplication> findByUserAndCompanyNameContainingIgnoreCase(
            User user, String companyName, Pageable pageable);

    // Find specific job belonging to user — security check
    Optional<JobApplication> findByIdAndUser(Long id, User user);

    // Count by status for dashboard stats
    @Query("SELECT j.status, COUNT(j) FROM JobApplication j " +
            "WHERE j.user = :user GROUP BY j.status")
    List<Object[]> countByStatusForUser(@Param("user") User user);

    // Combined filter — status + company search
    @Query("SELECT j FROM JobApplication j WHERE j.user = :user " +
            "AND (:status IS NULL OR j.status = :status) " +
            "AND (:company IS NULL OR " +
            "LOWER(j.companyName) LIKE LOWER(CONCAT('%', :company, '%')))")
    Page<JobApplication> findByUserWithFilters(
            @Param("user") User user,
            @Param("status") ApplicationStatus status,
            @Param("company") String company,
            Pageable pageable);
}