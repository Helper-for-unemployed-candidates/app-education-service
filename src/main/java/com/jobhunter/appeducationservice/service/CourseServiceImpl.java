package com.jobhunter.appeducationservice.service;

import com.jobhunter.appeducationservice.dtos.CourseDTO;
import com.jobhunter.appeducationservice.dtos.CoursesUpdateDTO;
import com.jobhunter.appeducationservice.entity.Attachment;
import com.jobhunter.appeducationservice.entity.Course;
import com.jobhunter.appeducationservice.exceptions.RestException;
import com.jobhunter.appeducationservice.mappers.CourseMapper;
import com.jobhunter.appeducationservice.repository.AttachmentRepository;
import com.jobhunter.appeducationservice.repository.CourseRepository;
import com.jobhunter.appeducationservice.service.serives.CourseService;
import com.jobhunter.appeducationservice.shit.payload.UserPrincipal;
import com.jobhunter.appeducationservice.shit.utils.ConstantFields;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
    public static String MESSAGE_FOR_EXCEPTION = "OOOPs ! Course not found by given id:  ";
    private final CourseRepository courseRepository;
    private final CourseMapper mapper;
    private final AttachmentRepository attachmentRepository;

    @Override
    @Transactional
    @Cacheable(value = "courseDto", key = "#keyGenerator")
    public CourseDTO createCourse(CourseDTO coursesDTO) {
        UserPrincipal user = ConstantFields.currentUser();

        Course course = mapper.toCourses(coursesDTO);
        course.setCreatedById(user.getId());
        Attachment attachment = attachmentRepository
                .findById(coursesDTO.getAttachmentId())
                .orElseThrow(() -> RestException.restThrow("Attachment is not found: " + coursesDTO.getAttachmentId()));
        course.setAttachment(attachment);
        courseRepository.save(course);
        return coursesDTO;
    }

    @Override
    @Transactional
    @CachePut(value = "courseUpdateDTO", key = "#courseId")
    public CourseDTO updateCourse(UUID courseId, CoursesUpdateDTO coursesUpdateDTO) {
        UserPrincipal user = ConstantFields.currentUser();
        Course existingCourse = courseRepository
                .findByIdAndCreatedById(courseId, user.getId())
                .orElseThrow(
                        () -> RestException
                                .restThrow(MESSAGE_FOR_EXCEPTION + courseId));
        Attachment attachment = attachmentRepository
                .findById(coursesUpdateDTO.getAttachmentId())
                .orElseThrow(() -> RestException.restThrow("Attachment is not found: " + coursesUpdateDTO.getAttachmentId()));
        existingCourse.setAttachment(attachment);
        mapper.updateCourse(existingCourse, coursesUpdateDTO);
        CourseDTO coursesDTO = mapper.toCoursesDTO(existingCourse);
        courseRepository.save(existingCourse);
        return coursesDTO;
    }

    @Override
    @CacheEvict(value = "deleteCourse", key = "#courseId")
    public void deleteCourse(UUID courseId) {
        UserPrincipal user = ConstantFields.currentUser();
        courseRepository.deleteByIdAndCreatedById(user.getId(), courseId);
        log.info("Deleted course {}", courseId);
    }

    @Override
    @Cacheable(value = "courseDTO", key = "#courseId")
    public CourseDTO getCourseById(UUID courseId, UUID uploadedByUserId) {
        Course courses = courseRepository
                .findByIdAndCreatedById(courseId, uploadedByUserId)
                .orElseThrow(() -> RestException.restThrow(MESSAGE_FOR_EXCEPTION + courseId));

        return mapper.toCoursesDTO(courses);
    }

    @Override
    @Cacheable(value = "coursesPage",
            key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #userId")
    public Page<CourseDTO> getAllCourses(Pageable pageable, UUID userId) {

        Page<Course> allUserId = courseRepository
                .findAllByCreatedById(pageable, userId);
        return mapper.coursePageToDtoPage(allUserId);
    }

    @Override
    public Page<CourseDTO> getCourseByTitle(String title, Pageable pageable) {
        Page<Course> courseByTitle = courseRepository.findCourseByTitle(title, pageable);
        return mapper.coursePageToDtoPage(courseByTitle);
    }

}
