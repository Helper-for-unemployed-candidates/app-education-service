package com.jobhunter.appeducationservice.controllers;

import com.jobhunter.appeducationservice.dtos.AssignmentDTO;
import com.jobhunter.appeducationservice.service.TopicServiceImpl;
import com.jobhunter.appeducationservice.service.serives.AssignmentService;
import com.jobhunter.appeducationservice.shit.payload.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for managing assignments.
 * Provides endpoints for creating, updating, and retrieving assignments.
 * <p>
 * Security:
 * - Authentication and authorization are handled using JWT tokens.
 * - Roles: COMPANY, USER.
 * <p>
 * Error Handling:
 * - Returns appropriate HTTP status codes for errors (e.g., 400 for bad requests, 404 for not found).
 * - Error responses include a message and a status code.
 */
@Tag(name = "Assignment Management", description = "APIs for managing assignments")
@RestController
@RequestMapping(AssignmentController.BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class AssignmentController {

    static final String BASE_PATH = "/api/v1/e/assignment";

    private final AssignmentService assignmentService;
    private final TopicServiceImpl topicServiceImpl;

    /**
     * Create a new assignment.
     *
     * @param assignmentDTO the assignment data to be created
     * @return the created assignment
     */
    @Operation(summary = "Create a new assignment", description = "Creates a new assignment and returns the created assignment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created assignment",
                    content = @Content(schema = @Schema(implementation = AssignmentDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public Response<AssignmentDTO> createAssignment(
            @Parameter(description = "Assignment data to be created", required = true)
            @Valid @RequestBody AssignmentDTO assignmentDTO) {
        log.info("Creating assignment: {}", assignmentDTO);
        return Response.successResponse(assignmentService.createAssignment(assignmentDTO));
    }

    /**
     * Update an existing assignment based on topic ID.
     *
     * @param topicId       the ID of the topic to which the assignment belongs
     * @param assignmentDTO the updated assignment data
     * @return the updated assignment
     */
    @Operation(summary = "Update an assignment", description = "Updates an existing assignment based on topic ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated assignment",
                    content = @Content(schema = @Schema(implementation = AssignmentDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Assignment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update/{topicId}")
    public Response<AssignmentDTO> updateAssignment(
            @Parameter(description = "Topic ID to which the assignment belongs", required = true)
            @PathVariable UUID topicId,
            @Parameter(description = "Updated assignment data", required = true)
            @Valid @RequestBody AssignmentDTO assignmentDTO) {
        log.info("Updating assignment for topic ID {}: {}", topicId, assignmentDTO);
        return Response.successResponse(assignmentService.updateAssignment(topicId, assignmentDTO));
    }

    /**
     * Retrieve a specific assignment by its ID and the topic ID it belongs to.
     *
     * @param assignmentId the ID of the assignment to be retrieved
     * @param topicId      the ID of the topic to which the assignment belongs
     * @return the assignment details
     */
    @Operation(summary = "Get assignment by ID and topic ID", description = "Retrieves a specific assignment by its ID and the topic ID it belongs to.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved assignment",
                    content = @Content(schema = @Schema(implementation = AssignmentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Assignment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{assignmentId}/{topicId}")
    public Response<AssignmentDTO> getAssignmentsByItsIdAndTopicId(
            @Parameter(description = "Assignment ID to be retrieved", required = true)
            @PathVariable UUID assignmentId,
            @Parameter(description = "Topic ID to which the assignment belongs", required = true)
            @PathVariable UUID topicId) {
        log.info("Retrieving assignment ID {} for topic ID {}", assignmentId, topicId);
        return Response.successResponse(assignmentService.getAssignmentById(assignmentId));
    }
}
