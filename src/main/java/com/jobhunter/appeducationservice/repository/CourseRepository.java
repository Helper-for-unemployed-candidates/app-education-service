package com.jobhunter.appeducationservice.repository;


import com.jobhunter.appeducationservice.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    Optional<Course> findByIdAndCreatedById(UUID id, UUID userId);
    Page<Course> findAllByCreatedById(Pageable pageable, UUID createdById);
    void deleteByIdAndCreatedById(UUID id, UUID userId);
    Page<Course> findCourseByTitle(String title, Pageable pageable);
}
