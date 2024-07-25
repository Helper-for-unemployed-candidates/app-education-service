package com.jobhunter.appeducationservice.mappers;

import com.jobhunter.appeducationservice.dtos.AssignmentDTO;
import com.jobhunter.appeducationservice.entity.Assignment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AssignmentMapper {


    @Mapping(target = "attachmentId", source = "attachment.id")
    AssignmentDTO toAssignmentDto(Assignment assignment);

    @InheritInverseConfiguration
    Assignment toAssignment(AssignmentDTO assignment);

    List<AssignmentDTO> entityListToDtoList(List<Assignment> assignments);
}
