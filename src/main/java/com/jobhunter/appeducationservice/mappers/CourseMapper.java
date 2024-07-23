package com.jobhunter.appeducationservice.mappers;

import com.jobhunter.appeducationservice.dtos.CourseDTO;
import com.jobhunter.appeducationservice.dtos.CoursesUpdateDTO;
import com.jobhunter.appeducationservice.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course toCourses(CourseDTO coursesDTO);

    @Mapping(target = "attachmentId", source = "attachment.id")
    CourseDTO toCoursesDTO(Course course);

    @Mapping(target = "id", ignore = true)
    void updateCourse(@MappingTarget Course courses, CoursesUpdateDTO coursesDTO);

    List<CourseDTO> courseListToDtoList(List<Course> all);

    Page<CourseDTO> coursePageToDtoPage(Page<Course> all);

}
