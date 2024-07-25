package com.jobhunter.appeducationservice.controllers;

import com.jobhunter.appeducationservice.dtos.SubmissionDTO;
import com.jobhunter.appeducationservice.repository.SubmissionRepository;
import com.jobhunter.appeducationservice.service.serives.SubmissionService;
import com.jobhunter.appeducationservice.shit.aop.Authorize;
import com.jobhunter.appeducationservice.shit.enums.RoleEnum;
import com.jobhunter.appeducationservice.shit.payload.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class SubmissionControllerImpl implements SubmissionController {
    private final SubmissionService submissionService;


    @Override
    public Response<SubmissionDTO> saveSubmission(SubmissionDTO dto) {
        return Response.successResponse(submissionService.saveSubmission(dto));
    }

    @Override
    @Authorize(permissions = RoleEnum.APPLICANT)
    public Response<List<SubmissionDTO>> findAllSubmissions(UUID assignmentId) {
        return Response.successResponse(submissionService.findAllSubmissions(assignmentId));
    }

    @Override
    public Response<SubmissionDTO> findSubmissionById(UUID submissionId, UUID assignmentId) {
        return Response.successResponse(submissionService.findSubmissionById(assignmentId, submissionId));
    }

    @Override
    public Response<Void> deleteSubmissionById(UUID submissionId, UUID assignmentId) {
        submissionService.deleteSubmissionById(submissionId, assignmentId);
        return Response.successResponse();
    }

    @Override
    public Response<Void> updateSubmission(UUID submissionId, SubmissionDTO dto) {
        submissionService.updateSubmission(submissionId, dto);
        return Response.successResponse();
    }
}
