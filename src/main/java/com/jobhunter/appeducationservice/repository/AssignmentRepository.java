package com.jobhunter.appeducationservice.repository;

import com.jobhunter.appeducationservice.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {
}
