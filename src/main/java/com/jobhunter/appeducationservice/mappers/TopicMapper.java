package com.jobhunter.appeducationservice.mappers;

import com.jobhunter.appeducationservice.dtos.TopicDTO;
import com.jobhunter.appeducationservice.dtos.TopicUpdateDTO;
import com.jobhunter.appeducationservice.entity.Topic;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PageMapper.class})
public abstract class TopicMapper {

    @Autowired
    private PageMapper pageMapper;

    @Mapping(target = "attachmentId", source = "attachment.id")
    public abstract TopicDTO topicDTO(Topic topic);

    @InheritInverseConfiguration
    public abstract Topic toEntity(TopicDTO dto);

    @Mapping(target = "id", ignore = true)
    public abstract void updateTopic(@MappingTarget Topic topic, TopicUpdateDTO dto);

    public abstract List<TopicDTO> listToTopicDTOList(List<Topic> topics);

    public Page<TopicDTO> toTopicDTOPageable(Page<Topic> topics) {
        return pageMapper.mapEntityPageToDtoPage(topics, this::topicDTO);
    }

    public List<Topic> topicDTOPageable(Page<TopicDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
    
}
