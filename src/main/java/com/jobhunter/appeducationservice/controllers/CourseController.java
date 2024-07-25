package com.jobhunter.appeducationservice.controllers;

import com.jobhunter.appeducationservice.dtos.CourseDTO;
import com.jobhunter.appeducationservice.dtos.CoursesUpdateDTO;
import com.jobhunter.appeducationservice.service.serives.CourseService;
import com.jobhunter.appeducationservice.shit.aop.Authorize;
import com.jobhunter.appeducationservice.shit.enums.RoleEnum;
import com.jobhunter.appeducationservice.shit.payload.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(CourseController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    public static final String BASE_URL = "/api/v1/e/courses";
    private final CourseService courseService;

    @Operation(summary = "Get all courses by user ID",
            description = "Retrieve a paginated list of all courses for a specific user.",
            parameters = {
                    @Parameter(name = "size", description = "Number of items per page", in = ParameterIn.QUERY),
                    @Parameter(name = "page", description = "Page number", in = ParameterIn.QUERY),
                    @Parameter(name = "userId", description = "User ID", in = ParameterIn.PATH)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of courses retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @GetMapping("/{userId}")
    @Authorize(permissions = RoleEnum.APPLICANT)
    public Response<Page<CourseDTO>> getAllCourses(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @PathVariable UUID userId) {

        Pageable pageRequest = PageRequest.of(page - 1, size); // PageRequest uses zero-based index
        return Response.successResponse(courseService.getAllCourses(pageRequest, userId));
    }

    @Operation(summary = "Update a course",
            description = "Update the details of an existing course.",
            parameters = {
                    @Parameter(name = "courseId", description = "Course ID", in = ParameterIn.PATH)
            },
            requestBody = @RequestBody(
                    description = "Course update information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CoursesUpdateDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Course updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @PutMapping("/update/{courseId}")
    @Authorize(permissions = RoleEnum.COMPANY)
    public Response<CourseDTO> updateCourse(@PathVariable UUID courseId, @RequestBody CoursesUpdateDTO courseDTO) {
        return Response.successResponse(courseService.updateCourse(courseId, courseDTO));
    }

    @Operation(summary = "Create a new course",
            description = "Create a new course with the provided details.",
            requestBody = @RequestBody(
                    description = "Course information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Course created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @PostMapping("/create")
    public Response<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        return Response.successResponse(courseService.createCourse(courseDTO));
    }

    @Operation(summary = "Get a course by ID",
            description = "Retrieve a specific course by its ID and the user who uploaded it.",
            parameters = {
                    @Parameter(name = "courseId", description = "Course ID", in = ParameterIn.PATH),
                    @Parameter(name = "uploadedByUserId", description = "User ID of the uploader", in = ParameterIn.PATH)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Course retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @GetMapping("/courseId/{courseId}/uploadedUserId/{uploadedByUserId}")
    public Response<CourseDTO> getCourseById(@PathVariable UUID courseId, @PathVariable UUID uploadedByUserId) {
        return Response.successResponse(courseService.getCourseById(courseId, uploadedByUserId));
    }

    @Operation(summary = "Find courses by title",
            description = "Retrieve a paginated list of courses by title.",
            parameters = {
                    @Parameter(name = "size", description = "Number of items per page", in = ParameterIn.QUERY),
                    @Parameter(name = "page", description = "Page number", in = ParameterIn.QUERY),
                    @Parameter(name = "title", description = "Course title to search for", in = ParameterIn.QUERY)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of courses by title retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @GetMapping("/find-by-title")
    public Response<Page<CourseDTO>> getCourseByTitle(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam String title
    ) {
        Pageable pageRequest = PageRequest.of(page - 1, size); // PageRequest uses zero-based index
        return Response.successResponse(courseService.getCourseByTitle(title, pageRequest));
    }

    @Operation(summary = "Delete a course",
            description = "Remove a course by its ID.",
            parameters = {
                    @Parameter(name = "courseId", description = "Course ID", in = ParameterIn.PATH)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Course deleted successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class))),
                    @ApiResponse(responseCode = "404", description = "Course not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)))
            })
    @DeleteMapping("/{courseId}")
    @Authorize(permissions = RoleEnum.COMPANY)
    public Response<Void> deleteCourse(@PathVariable UUID courseId) {
        courseService.deleteCourse(courseId);
        return Response.successResponse();
    }
}


/*
package com.jobhunter.appeducationservice.controllers;

import com.jobhunter.appeducationservice.dtos.CourseDTO;
import com.jobhunter.appeducationservice.dtos.CoursesUpdateDTO;
import com.jobhunter.appeducationservice.service.serives.CourseService;
import com.jobhunter.appeducationservice.shit.aop.Authorize;
import com.jobhunter.appeducationservice.shit.enums.RoleEnum;
import com.jobhunter.appeducationservice.shit.payload.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(CourseController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    public static final String BASE_URL = "/api/v1/e/courses";
    private final CourseService courseService;

    @GetMapping("/{userId}")
    @Authorize(permissions = RoleEnum.APPLICANT)
    public Response<Page<CourseDTO>> getAllCourses(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @PathVariable UUID userId) {

        Pageable pageRequest = PageRequest.of(size, page);
        return Response.successResponse(courseService.getAllCourses(pageRequest, userId));
    }

    @PutMapping("/update/{courseId}")
    @Authorize(permissions = RoleEnum.COMPANY)
    Response<CourseDTO> updateCourse(@PathVariable UUID courseId, @RequestBody CoursesUpdateDTO coursesUpdateDTO) {
        return Response.successResponse(courseService.updateCourse(courseId, coursesUpdateDTO));
    }

    @PostMapping("/create")
    public Response<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        return Response.successResponse(courseService.createCourse(courseDTO));
    }

    @GetMapping("/courseId/{courseId}/uploadedUserId/{uploadedByUserId}")
    Response<CourseDTO> getCourseById(@PathVariable UUID courseId, @PathVariable UUID uploadedByUserId) {
        return Response.successResponse(courseService.getCourseById(courseId, uploadedByUserId));
    }

    @GetMapping("/find-by-title")
    Response<Page<CourseDTO>> getCourseByTitle(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam String title
    ) {
        Pageable pageRequest = PageRequest.of(size, page);
        return Response.successResponse(courseService.getCourseByTitle(title, pageRequest));
    }

    @DeleteMapping("/{courseId}")
    Response<Void> deleteCourse(@PathVariable UUID courseId) {
        courseService.deleteCourse(courseId);
        return Response.successResponse();
    }


}
*/
