package com.jobhunter.appeducationservice.controllers;
import com.jobhunter.appeducationservice.dtos.TopicDTO;
import com.jobhunter.appeducationservice.dtos.TopicUpdateDTO;
import com.jobhunter.appeducationservice.service.serives.TopicService;
import com.jobhunter.appeducationservice.shit.payload.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(TopicController.BASE_URL)
public class TopicController {

    public static final String BASE_URL = "/api/v1/e/topics";
    private final TopicService topicService;

    @Operation(summary = "Get all topics by chapter ID",
            description = "Retrieve a list of all topics for a specific chapter.",
            parameters = {
                    @Parameter(name = "chapterId", description = "Chapter ID", in = ParameterIn.PATH)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of topics retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TopicDTO.class)))
            })
    @GetMapping("/{chapterId}")
    public Response<List<TopicDTO>> getAllTopics(@PathVariable UUID chapterId) {
        return Response.successResponse(topicService.getAllTopicsByChapterId(chapterId));
    }

    @Operation(summary = "Create a new topic",
            description = "Create a new topic with the provided details.",
            requestBody = @RequestBody(
                    description = "Topic information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TopicDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Topic created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @PostMapping("/create")
    public Response<TopicDTO> createTopic(@Valid @RequestBody TopicDTO topicDTO) {
        return Response.successResponse(topicService.createTopic(topicDTO));
    }

    @Operation(summary = "Update a topic",
            description = "Update the details of an existing topic.",
            parameters = {
                    @Parameter(name = "chapterId", description = "Chapter ID", in = ParameterIn.PATH)
            },
            requestBody = @RequestBody(
                    description = "Topic update information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TopicUpdateDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Topic updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TopicDTO.class)))
            })
    @PutMapping("/update/{chapterId}")
    public Response<TopicDTO> updateTopic(@PathVariable UUID chapterId, @Valid @RequestBody TopicUpdateDTO topicDTO) {
        return Response.successResponse(topicService.updateTopic(chapterId, topicDTO));
    }

    @Operation(summary = "Delete a topic",
            description = "Remove a topic by its ID from a specific chapter.",
            parameters = {
                    @Parameter(name = "chapterId", description = "Chapter ID", in = ParameterIn.PATH),
                    @Parameter(name = "topicId", description = "Topic ID", in = ParameterIn.PATH)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Topic deleted successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class))),
                    @ApiResponse(responseCode = "404", description = "Topic not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @DeleteMapping("/delete/{chapterId}/topicId/{topicId}")
    public Response<Void> deleteTopic(@PathVariable UUID chapterId, @PathVariable UUID topicId) {
        topicService.deleteTopic(chapterId, topicId);
        return Response.successResponse();
    }
}