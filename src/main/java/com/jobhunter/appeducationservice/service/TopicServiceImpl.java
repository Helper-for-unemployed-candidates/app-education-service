package com.jobhunter.appeducationservice.service;

import com.jobhunter.appeducationservice.dtos.TopicDTO;
import com.jobhunter.appeducationservice.dtos.TopicUpdateDTO;
import com.jobhunter.appeducationservice.entity.Assignment;
import com.jobhunter.appeducationservice.entity.Attachment;
import com.jobhunter.appeducationservice.entity.Chapter;
import com.jobhunter.appeducationservice.entity.Topic;
import com.jobhunter.appeducationservice.exceptions.RestException;
import com.jobhunter.appeducationservice.mappers.TopicMapper;
import com.jobhunter.appeducationservice.repository.AssignmentRepository;
import com.jobhunter.appeducationservice.repository.AttachmentRepository;
import com.jobhunter.appeducationservice.repository.ChapterRepository;
import com.jobhunter.appeducationservice.repository.TopicRepository;
import com.jobhunter.appeducationservice.service.serives.TopicService;
import com.jobhunter.appeducationservice.shit.payload.UserPrincipal;
import com.jobhunter.appeducationservice.shit.utils.ConstantFields;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final AttachmentRepository attachmentRepository;
    private final AssignmentRepository assignmentRepository;
    private final ChapterRepository chapterRepository;
    private final TopicMapper topicMapper;
    private final static String MESSAGE_ATTACHMENT = "attachment is not found by given id: ";
    private final static String MESSAGE_ASSIGNMENT = "assignment is not found by given id: ";
    private final static String MESSAGE_CHAPTER = "chapter is not found by given id: ";

    @Override
    public TopicDTO createTopic(TopicDTO dto) {
        UserPrincipal user = ConstantFields.currentUser();
        Topic topic = topicMapper.toEntity(dto);

        Attachment attachment = attachmentRepository
                .findById(dto.getAttachmentId())
                .orElseThrow(() -> RestException
                        .restThrow(MESSAGE_ATTACHMENT + dto.getAttachmentId()));
        topic.setAttachment(attachment);
        Assignment assignment = assignmentRepository
                .findById(dto.getAssignmentId())
                .orElseThrow(() -> RestException
                        .restThrow(MESSAGE_ASSIGNMENT + dto.getAssignmentId()));
        topic.setAssignment(assignment);
        Chapter chapter = chapterRepository
                .findById(dto.getChapterId())
                .orElseThrow(() -> RestException.restThrow(MESSAGE_CHAPTER + dto.getChapterId()));
        topic.setChapter(chapter);

        Topic save = topicRepository.save(topic);

        return topicMapper.topicDTO(topic);

    }

    @Override
    @CachePut(value = "topicDTO", key = "#dto.chapterId")
    public TopicDTO updateTopic(UUID chapterId, TopicUpdateDTO dto) {
        UserPrincipal user = ConstantFields.currentUser();
        Topic existingTopic = topicRepository
                .findByIdAndChapterId(chapterId, user.getId())
                .orElseThrow(() -> RestException.restThrow(MESSAGE_CHAPTER + dto.getChapterId()));

        topicMapper.updateTopic(existingTopic, dto);
        Topic savedTopic = topicRepository.save(existingTopic);
        return topicMapper.topicDTO(savedTopic);
    }

    @Override
    public List<TopicDTO> getAllTopicsByChapterId(UUID chapterId) {
        return List.of();
    }

    @Override
    public void deleteTopic(UUID topicId) {

    }
}
