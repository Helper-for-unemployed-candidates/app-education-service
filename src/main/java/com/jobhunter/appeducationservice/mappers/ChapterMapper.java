package com.jobhunter.appeducationservice.mappers;

import com.jobhunter.appeducationservice.dtos.ChapterDTO;
import com.jobhunter.appeducationservice.dtos.ChapterUpdateDTO;
import com.jobhunter.appeducationservice.entity.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChapterMapper {

    ChapterDTO chapterToChapterDTO(Chapter chapter);
    Chapter chapterDTOToChapter(ChapterDTO chapterDTO);
    List<ChapterDTO> toChapterDTOPage(List<Chapter> chapters);
    @Mapping(target = "id", ignore = true)
    void updateChapter(@MappingTarget Chapter chapter, ChapterUpdateDTO chapterUpdateDTO);

}
