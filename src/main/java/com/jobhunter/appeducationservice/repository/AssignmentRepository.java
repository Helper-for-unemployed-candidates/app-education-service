package com.jobhunter.appeducationservice.repository;

import com.jobhunter.appeducationservice.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {
    Optional<Assignment> findAssignmentById(UUID assignmentId);

//    List<Assignment> findAssignmentsByTopicId(UUID topicId);
}
