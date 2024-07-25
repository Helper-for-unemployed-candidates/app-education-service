package com.jobhunter.appeducationservice.service.serives;

import com.jobhunter.appeducationservice.dtos.SubmissionDTO;
import com.jobhunter.appeducationservice.entity.Submission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface SubmissionService {

    SubmissionDTO saveSubmission(SubmissionDTO dto);

    List<SubmissionDTO> findAllSubmissions(UUID assignmentId);

    SubmissionDTO findSubmissionById(UUID submissionId, UUID assignmentId);

    void deleteSubmissionById(UUID submissionId, UUID assignmentId);

    void updateSubmission(UUID submissionId, SubmissionDTO dto);

}
