package com.jobhunter.appeducationservice.service;

import com.jobhunter.appeducationservice.dtos.SubmissionDTO;
import com.jobhunter.appeducationservice.entity.Assignment;
import com.jobhunter.appeducationservice.entity.Attachment;
import com.jobhunter.appeducationservice.entity.Submission;
import com.jobhunter.appeducationservice.exceptions.RestException;
import com.jobhunter.appeducationservice.mappers.SubmissionMapper;
import com.jobhunter.appeducationservice.repository.AssignmentRepository;
import com.jobhunter.appeducationservice.repository.AttachmentRepository;
import com.jobhunter.appeducationservice.repository.SubmissionRepository;
import com.jobhunter.appeducationservice.service.serives.SubmissionService;
import com.jobhunter.appeducationservice.shit.payload.UserPrincipal;
import com.jobhunter.appeducationservice.shit.utils.ConstantFields;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final AttachmentRepository attachmentRepository;
    private final SubmissionMapper submissionMapper;

    @Override
    public SubmissionDTO saveSubmission(SubmissionDTO dto) {

        Assignment assignment = assignmentRepository
                .findById(dto.getAssignmentId())
                .orElseThrow(() -> RestException.restThrow("assignment not found"));
        Attachment attachment = attachmentRepository
                .findById(dto.getAttachmentId())
                .orElseThrow(() -> RestException.restThrow("attachment not found"));

        Submission submission = submissionMapper.submissionDTOToSubmission(dto);
        submission.setAssignment(assignment);
        submission.setAttachment(attachment);
        submissionRepository.save(submission);

        return dto;
    }

    @Override
    public List<SubmissionDTO> findAllSubmissions(UUID assignmentId) {
        UserPrincipal user = ConstantFields.currentUser();
        List<Submission> allByUserIdAndAssignmentId = submissionRepository
                .findAllByUserIdAndAssignmentId(user.getId(), assignmentId);
        return submissionMapper.submissioListToDTO(allByUserIdAndAssignmentId);
    }

    @Override
    public SubmissionDTO findSubmissionById(UUID submissionId, UUID assignmentId) {
        Submission submission = submissionRepository
                .findByUserIdAndAssignmentId(submissionId, assignmentId);
        return submissionMapper.submissionToSubmissionDTO(submission);
    }

    @Override
    public void deleteSubmissionById(UUID submissionId, UUID assignmentId) {
        submissionRepository.deleteSubmissionByIdAndAssignmentId(submissionId, assignmentId);
    }

    @Override
    public void updateSubmission(UUID submissionId, SubmissionDTO dto) {
        Submission submission = submissionRepository
                .findByUserIdAndId(dto.getUserId(), submissionId)
                .orElseThrow(() -> RestException.restThrow("attachment not found"));
        submissionMapper.updateSubmission(submission, dto);
        submissionRepository.save(submission);
    }
}


/*TODO they must be completed tomorrow   25 JULY
 * 1. complete resume section inside of job-service
 * 2. complete the controller class of submission
 * 3. chat system integration
 * 4. AI integration
 * 5. DOCUMENTATION
 * 6.
 * */
