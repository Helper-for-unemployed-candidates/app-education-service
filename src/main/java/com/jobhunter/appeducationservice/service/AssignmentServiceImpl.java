package com.jobhunter.appeducationservice.service;

import com.jobhunter.appeducationservice.dtos.AssignmentDTO;
import com.jobhunter.appeducationservice.entity.Assignment;
import com.jobhunter.appeducationservice.entity.Attachment;
import com.jobhunter.appeducationservice.entity.Topic;
import com.jobhunter.appeducationservice.exceptions.RestException;
import com.jobhunter.appeducationservice.mappers.AssignmentMapper;
import com.jobhunter.appeducationservice.mappers.TopicMapper;
import com.jobhunter.appeducationservice.repository.AssignmentRepository;
import com.jobhunter.appeducationservice.repository.AttachmentRepository;
import com.jobhunter.appeducationservice.repository.TopicRepository;
import com.jobhunter.appeducationservice.service.serives.AssignmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final AttachmentRepository attachmentRepository;
    private final AssignmentMapper assignmentMapper;
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Override
    public AssignmentDTO createAssignment(AssignmentDTO dto) {
        Attachment attachment = attachmentRepository
                .findById(dto.getAttachmentId())
                .orElseThrow(() -> RestException.restThrow("assignment with attachment id is not found"));
        Assignment assignment1 = assignmentMapper.toAssignment(dto);
        assignment1.setAttachment(attachment);
        assignmentRepository.save(assignment1);

        return dto;
    }

    @Override
    public AssignmentDTO updateAssignment(UUID topicId, AssignmentDTO dto) {

        Topic topic = topicRepository
                .findById(topicId)
                .orElseThrow(() -> RestException.restThrow("Topic is not found"));

        Assignment assignment = assignmentMapper.toAssignment(dto);
        Attachment attachment = attachmentRepository
                .findById(dto.getAttachmentId())
                .orElseThrow(() -> RestException.restThrow("Attachment is not found"));
        assignment.setAttachment(attachment);
        assignmentRepository.save(assignment);
        topicRepository.save(topic);

        topic.setAssignment(assignment);

        return dto;
    }

    @Override
    public AssignmentDTO getAssignmentById(UUID id) {
        Assignment assignment = assignmentRepository
                .findAssignmentById(id)
                .orElseThrow(() -> RestException.restThrow("Assignment with id and topic id is not found"));
        return assignmentMapper.toAssignmentDto(assignment);
    }

  /*  @Override
    public List<AssignmentDTO> getAllAssignments(UUID topicId) {
        List<Assignment> assignmentsByTopicId = assignmentRepository.findAssignmentsByTopicId(topicId);
        return assignmentMapper.entityListToDtoList(assignmentsByTopicId);
    }*/
}
