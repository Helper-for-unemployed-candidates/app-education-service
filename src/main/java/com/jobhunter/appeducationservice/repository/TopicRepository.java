package com.jobhunter.appeducationservice.repository;

import com.jobhunter.appeducationservice.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {

    Optional<Topic> findByIdAndChapterId(UUID id, UUID userId);

    List<Topic> findAllByChapterId(UUID chapterId);

    void deleteByIdAndChapterId(UUID id, UUID chapterId);
}
