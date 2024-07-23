package com.jobhunter.appeducationservice.mappers;

import com.jobhunter.appeducationservice.dtos.TopicDTO;
import com.jobhunter.appeducationservice.dtos.TopicUpdateDTO;
import com.jobhunter.appeducationservice.entity.Topic;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    @Mapping(target = "attachmentId", source = "attachment.id")
    TopicDTO topicDTO(Topic topic);

    @InheritInverseConfiguration
    Topic toEntity(TopicDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateTopic(@MappingTarget Topic topic, TopicUpdateDTO dto);

    Page<TopicDTO> toTopicDTOPageable(Page<Topic> topics);

    List<Topic> topicDTOPageable(Page<TopicDTO> dtos);

}
