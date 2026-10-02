package com.ujjwal.jobtrackr.repository;

import com.ujjwal.jobtrackr.entity.InterviewRound;
import com.ujjwal.jobtrackr.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewRoundRepository
        extends JpaRepository<InterviewRound, Long> {

    List<InterviewRound> findByJobApplicationOrderByRoundNumberAsc(
            JobApplication jobApplication);

    Optional<InterviewRound> findByIdAndJobApplication(
            Long id, JobApplication jobApplication);

    void deleteByJobApplication(JobApplication jobApplication);
}