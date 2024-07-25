package com.jobhunter.appeducationservice.service.serives;

import com.jobhunter.appeducationservice.dtos.TopicDTO;
import com.jobhunter.appeducationservice.dtos.TopicUpdateDTO;
import com.jobhunter.appeducationservice.entity.Topic;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface TopicService {
    TopicDTO createTopic(TopicDTO dto);
    TopicDTO updateTopic(UUID chapterId,TopicUpdateDTO dto);
    List<TopicDTO> getAllTopicsByChapterId(UUID chapterId);
    void deleteTopic(UUID chapterId,UUID topicId);
}
