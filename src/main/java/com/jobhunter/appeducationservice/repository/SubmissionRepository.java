package com.jobhunter.appeducationservice.repository;


import com.jobhunter.appeducationservice.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findAllByUserIdAndAssignmentId(UUID userId, UUID assignmentId);

    Submission findByUserIdAndAssignmentId(UUID userId, UUID assignmentId);

    Optional<Submission> findByUserIdAndId(UUID userId, UUID submissionId);

    void deleteSubmissionByIdAndAssignmentId(UUID submissionId, UUID assignmentId);

}
