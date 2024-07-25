package com.jobhunter.appeducationservice.controllers;

import com.jobhunter.appeducationservice.dtos.SubmissionDTO;
import com.jobhunter.appeducationservice.shit.payload.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(SubmissionController.BASE_PATH)
public interface SubmissionController {
    String BASE_PATH = "/api/v1/e/submission";
    String SUBMISSION_PATH = BASE_PATH + "/save";
    String FIND_ALL = BASE_PATH + "/find-all/{assignmentId}";
    String FIND_BY_ID = BASE_PATH + "/find-by-id/{submissionId}/{assignmentId}";
    String DELETE_BY_ID = BASE_PATH + "/delete/{submissionId}/{assignmentId}";
    String UPDATE_BY_ID = BASE_PATH + "/update/{submissionId}";

    @Operation(summary = "Save a new submission",
            description = "Create a new submission with the provided details.",
            requestBody = @RequestBody(
                    description = "Submission information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubmissionDTO.class))
            ),
            parameters = {
                    @Parameter(name = "saving", description = "Submission DATA TRANSFeR OBJECt", required = true)

            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Submission created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SubmissionDTO.class)))

            })
    @PostMapping(SUBMISSION_PATH)
    Response<SubmissionDTO> saveSubmission(@Valid @RequestBody SubmissionDTO dto);

    @Operation(summary = "Find all submissions by assignment ID",
            description = "Retrieve a list of all submissions for a specific assignment.",
            parameters = {
                    @Parameter(name = "assignmentId", description = "Assignment ID", in = ParameterIn.PATH)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of submissions retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SubmissionDTO.class)))
            })
    @GetMapping(FIND_ALL)
    Response<List<SubmissionDTO>> findAllSubmissions(@PathVariable UUID assignmentId);

    @Operation(summary = "Find a submission by ID",
            description = "Retrieve a specific submission by its ID and the assignment ID.",
            parameters = {
                    @Parameter(name = "submissionId", description = "Submission ID", in = ParameterIn.PATH),
                    @Parameter(name = "assignmentId", description = "Assignment ID", in = ParameterIn.PATH)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Submission retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SubmissionDTO.class)))
            })
    @GetMapping(FIND_BY_ID)
    Response<SubmissionDTO> findSubmissionById(@PathVariable UUID submissionId, @PathVariable UUID assignmentId);

    @Operation(summary = "Delete a submission by ID",
            description = "Remove a submission by its ID from a specific assignment.",
            parameters = {
                    @Parameter(name = "submissionId", description = "Submission ID", in = ParameterIn.PATH),
                    @Parameter(name = "assignmentId", description = "Assignment ID", in = ParameterIn.PATH)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Submission deleted successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class))),
                    @ApiResponse(responseCode = "404", description = "Submission not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @DeleteMapping(DELETE_BY_ID)
    Response<Void> deleteSubmissionById(@PathVariable UUID submissionId, @PathVariable UUID assignmentId);

    @Operation(summary = "Update a submission",
            description = "Update the details of an existing submission.",
            parameters = {
                    @Parameter(name = "submissionId", description = "Submission ID", in = ParameterIn.PATH)
            },
            requestBody = @RequestBody(
                    description = "Submission update information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubmissionDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Submission updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class))),
                    @ApiResponse(responseCode = "404", description = "Submission not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @PutMapping(UPDATE_BY_ID)
    Response<Void> updateSubmission(@PathVariable UUID submissionId, @Valid @RequestBody SubmissionDTO dto);
}


