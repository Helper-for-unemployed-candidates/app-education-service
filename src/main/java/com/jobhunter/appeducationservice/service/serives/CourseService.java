package com.jobhunter.appeducationservice.service.serives;

import com.jobhunter.appeducationservice.dtos.CourseDTO;
import com.jobhunter.appeducationservice.dtos.CoursesUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface CourseService {
    /**
     * Creates a new course.
     *
     * @param coursesDTO the course data transfer object
     * @return the created course data transfer object
     */
    CourseDTO createCourse(CourseDTO coursesDTO);

    /**
     * Updates an existing course.
     *
     * @param courseId         the unique identifier of the course
     * @param coursesUpdateDTO the course update data transfer object
     * @return the updated course data transfer object
     */
    CourseDTO updateCourse(UUID courseId, CoursesUpdateDTO coursesUpdateDTO);

    /**
     * Deletes a course by its unique identifier.
     *
     * @param courseId the unique identifier of the course
     */
    void deleteCourse(UUID courseId);

    /**
     * Retrieves a course by its unique identifier and the user who uploaded it.
     *
     * @param courseId         the unique identifier of the course
     * @param uploadedByUserId the unique identifier of the user who uploaded the course
     * @return the course data transfer object
     */
    CourseDTO getCourseById(UUID courseId, UUID uploadedByUserId);

    /**
     * Retrieves all courses.
     *
     * @return a list of all course data transfer objects
     */
    Page<CourseDTO> getAllCourses(Pageable pageable, UUID userId);
    Page<CourseDTO> getCourseByTitle(String title, Pageable pageable);


}
