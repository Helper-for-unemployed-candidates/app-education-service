package com.jobhunter.appeducationservice.repository;


import com.jobhunter.appeducationservice.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ChapterRepository extends JpaRepository<Chapter, UUID> {
    List<Chapter> findByTitleAndCourseId(String title, UUID courseId);

    List<Chapter> findByCourseId(UUID courseId);

    boolean deleteChapterByTitle(String title);

    Optional<Chapter> findByIdAndCreatedById(UUID id, UUID userId);

    void deleteByIdAndCreatedById(UUID id, UUID userId);
}
