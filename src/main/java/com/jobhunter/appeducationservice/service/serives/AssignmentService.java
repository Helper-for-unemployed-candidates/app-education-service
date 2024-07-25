package com.jobhunter.appeducationservice.service.serives;

import com.jobhunter.appeducationservice.dtos.AssignmentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface AssignmentService {
    AssignmentDTO createAssignment(AssignmentDTO assignment);

    AssignmentDTO updateAssignment(UUID topicId,AssignmentDTO assignment);

    AssignmentDTO getAssignmentById(UUID id);

//    List<AssignmentDTO> getAllAssignments(UUID topicId);
}
