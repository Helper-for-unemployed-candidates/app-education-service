package com.jobhunter.appeducationservice.controllers;

import com.jobhunter.appeducationservice.dtos.ChapterDTO;
import com.jobhunter.appeducationservice.dtos.ChapterUpdateDTO;
import com.jobhunter.appeducationservice.service.serives.ChapterService;
import com.jobhunter.appeducationservice.shit.aop.Authorize;
import com.jobhunter.appeducationservice.shit.enums.RoleEnum;
import com.jobhunter.appeducationservice.shit.payload.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ChapterController.BASE_URL)
@RequiredArgsConstructor
public class ChapterController {
    public static final String BASE_URL = "/api/v1/e/chapter";
    private final ChapterService chapterService;

    @Operation(summary = "Get all chapters by course ID",
            description = "Retrieve a list of all chapters for a specific course.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of chapters retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @GetMapping("/{courseId}")
    Response<List<ChapterDTO>> getAllChapterByCourseId(@PathVariable UUID courseId) {
        return Response.successResponse(chapterService.getAllChapterByCourseId(courseId));
    }

    @Operation(summary = "Save a new chapter",
            description = "Create a new chapter for a course.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Chapter created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @PostMapping("/save")
    @Authorize(permissions = RoleEnum.COMPANY)
    Response<ChapterDTO> saveChapter(@RequestBody ChapterDTO chapterDTO) {
        return Response.successResponse(chapterService.saveChapter(chapterDTO));
    }

    @Operation(summary = "Update an existing chapter",
            description = "Update the details of an existing chapter.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Chapter updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @PutMapping("/update/{chapterId}")
    @Authorize(permissions = RoleEnum.COMPANY)
    Response<ChapterDTO> updateChapter(@PathVariable UUID chapterId, @RequestBody ChapterUpdateDTO chapterDTO) {
        return Response.successResponse(chapterService.updateChapter(chapterId, chapterDTO));
    }

    @Operation(summary = "Delete a chapter",
            description = "Remove a chapter from the course.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Chapter deleted successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class))),
                    @ApiResponse(responseCode = "404", description = "Chapter not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @DeleteMapping("/{chapterId}")
    @Authorize(permissions = RoleEnum.ADMIN)
    Response<Void> deleteChapter(@PathVariable UUID chapterId) {
        chapterService.deleteChapter(chapterId);
        return Response.successResponse();
    }
}




/*
package com.jobhunter.appeducationservice.controllers;

import com.jobhunter.appeducationservice.dtos.ChapterDTO;
import com.jobhunter.appeducationservice.dtos.ChapterUpdateDTO;
import com.jobhunter.appeducationservice.service.serives.ChapterService;
import com.jobhunter.appeducationservice.shit.aop.Authorize;
import com.jobhunter.appeducationservice.shit.enums.RoleEnum;
import com.jobhunter.appeducationservice.shit.payload.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ChapterController.BASE_URL)
@RequiredArgsConstructor
public class ChapterController {
    public static final String BASE_URL = "/api/v1/e/chapter";
    private final ChapterService chapterService;

    @GetMapping("/{courseId}")
    Response<List<ChapterDTO>> getAllChapterByCourseId(@PathVariable UUID courseId) {
        return Response.successResponse(chapterService.getAllChapterByCourseId(courseId));
    }

    @PostMapping("/save")
    @Authorize(permissions = RoleEnum.COMPANY)
    Response<ChapterDTO> saveChapter(@RequestBody ChapterDTO chapterDTO) {
        return Response.successResponse(chapterService.saveChapter(chapterDTO));
    }

    @PutMapping("/update/{chapterId}")
    @Authorize(permissions = RoleEnum.COMPANY)
    Response<ChapterDTO> updateChapter(@PathVariable UUID chapterId, @RequestBody ChapterUpdateDTO chapterDTO) {
        return Response.successResponse(chapterService.updateChapter(chapterId, chapterDTO));
    }

    @DeleteMapping("/{chapterId}")
    @Authorize(permissions = RoleEnum.ADMIN)
    Response<Void> deleteChapter(@PathVariable UUID chapterId) {
        chapterService.deleteChapter(chapterId);
        return Response.successResponse();
    }

}
*/
