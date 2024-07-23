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

@RestController("/api/course")
@RequiredArgsConstructor
@Slf4j
public class CourseController {
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
