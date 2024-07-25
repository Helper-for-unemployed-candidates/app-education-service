package com.jobhunter.appeducationservice.mappers;

import com.jobhunter.appeducationservice.dtos.SubmissionDTO;
import com.jobhunter.appeducationservice.entity.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubmissionMapper {

    SubmissionDTO submissionToSubmissionDTO(Submission submission);

    List<SubmissionDTO> submissioListToDTO(List<Submission> submission);

    Submission submissionDTOToSubmission(SubmissionDTO submissionDTO);

    @Mapping(target = "id", ignore = true)
    void updateSubmission(@MappingTarget Submission submission, SubmissionDTO submission1);
}
