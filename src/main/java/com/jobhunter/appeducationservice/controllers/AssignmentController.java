package com.jobhunter.appeducationservice.controllers;

import com.jobhunter.appeducationservice.dtos.AssignmentDTO;
import com.jobhunter.appeducationservice.service.TopicServiceImpl;
import com.jobhunter.appeducationservice.service.serives.AssignmentService;
import com.jobhunter.appeducationservice.shit.payload.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Assignment Management", description = "APIs for managing assignments")
@RestController
@RequestMapping(AssignmentController.BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class AssignmentController {

    static final String BASE_PATH = "/api/v1/e/assignment";


    private final AssignmentService assignmentService;
    private final TopicServiceImpl topicServiceImpl;

    @Operation(summary = "Create a new assignment", description = "Creates a new assignment and returns the created assignment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created assignment",
                    content = @Content(schema = @Schema(implementation = AssignmentDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    Response<AssignmentDTO> createAssignment(
            @Parameter(description = "Assignment data to be created", required = true)
            @RequestBody AssignmentDTO assignmentDTO) {
        return Response.successResponse(assignmentService.createAssignment(assignmentDTO));
    }

    @Operation(summary = "Update an assignment", description = "Updates an existing assignment based on topic ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated assignment",
                    content = @Content(schema = @Schema(implementation = AssignmentDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Assignment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update/{topicId}")
    Response<AssignmentDTO> updateAssignment(
            @Parameter(description = "Topic ID to which the assignment belongs", required = true)
            @PathVariable UUID topicId,
            @Parameter(description = "Updated assignment data", required = true)
            @RequestBody AssignmentDTO assignmentDTO) {
        return Response.successResponse(assignmentService.updateAssignment(topicId, assignmentDTO));
    }



    @Operation(summary = "Get assignment by ID and topic ID", description = "Retrieves a specific assignment by its ID and the topic ID it belongs to.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved assignment",
                    content = @Content(schema = @Schema(implementation = AssignmentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Assignment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{assignmentId}/{topicId}")
    Response<AssignmentDTO> getAssignmentsByItsIdAndTopicId(
            @Parameter(description = "Assignment ID to be retrieved", required = true)
            @PathVariable UUID assignmentId,
            @Parameter(description = "Topic ID to which the assignment belongs", required = true)
            @PathVariable UUID topicId) {
        return Response.successResponse(assignmentService.getAssignmentById(assignmentId));
    }
}



/*
package com.jobhunter.appeducationservice.controllers;

import com.jobhunter.appeducationservice.dtos.AssignmentDTO;
import com.jobhunter.appeducationservice.service.TopicServiceImpl;
import com.jobhunter.appeducationservice.service.serives.AssignmentService;
import com.jobhunter.appeducationservice.shit.payload.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController("/api/assignment")
@RequiredArgsConstructor
@Slf4j
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final TopicServiceImpl topicServiceImpl;

    @PostMapping("/create")
    Response<AssignmentDTO> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        return Response.successResponse(assignmentService.createAssignment(assignmentDTO));
    }

    @PutMapping("/update/{topicId}")
    Response<AssignmentDTO> updateAssignment(@PathVariable UUID topicId, @RequestBody AssignmentDTO assignmentDTO) {
        return Response.successResponse(assignmentService.updateAssignment(topicId, assignmentDTO));
    }

    @GetMapping("/{topicId}")
    Response<List<AssignmentDTO>> getAllAssignments(@PathVariable UUID topicId) {
        return Response.successResponse(assignmentService.getAllAssignments(topicId));
    }

    @GetMapping("/{assignmentId}/{topicId}")
    Response<AssignmentDTO> getAssignmentsByItsIdAndTopicId(@PathVariable UUID assignmentId, @PathVariable UUID topicId) {
        return Response.successResponse(assignmentService.getAssignmentByIdAndTopicId(assignmentId, topicId));
    }
}
*/
