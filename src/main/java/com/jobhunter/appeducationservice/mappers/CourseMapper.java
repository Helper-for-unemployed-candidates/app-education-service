package com.jobhunter.appeducationservice.mappers;

import com.jobhunter.appeducationservice.dtos.CourseDTO;
import com.jobhunter.appeducationservice.dtos.CoursesUpdateDTO;
import com.jobhunter.appeducationservice.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PageMapper.class})
public abstract class CourseMapper {

    @Autowired
    private PageMapper pageMapper;

    public abstract Course toCourses(CourseDTO coursesDTO);

    @Mapping(target = "attachmentId", source = "attachment.id")
    public abstract CourseDTO toCoursesDTO(Course course);

    @Mapping(target = "id", ignore = true)
    public abstract void updateCourse(@MappingTarget Course courses, CoursesUpdateDTO coursesDTO);

    public abstract List<CourseDTO> courseListToDtoList(List<Course> all);

    public Page<CourseDTO> coursePageToDtoPage(Page<Course> all) {
        return pageMapper.mapEntityPageToDtoPage(all, this::toCoursesDTO);
    }
}
