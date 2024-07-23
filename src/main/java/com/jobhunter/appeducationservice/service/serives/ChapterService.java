package com.jobhunter.appeducationservice.service.serives;

import com.jobhunter.appeducationservice.dtos.ChapterDTO;
import com.jobhunter.appeducationservice.dtos.ChapterUpdateDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface ChapterService {
    List<ChapterDTO> getAllChapterByCourseId(UUID courseId);
    ChapterDTO saveChapter(ChapterDTO chapterDTO);
    ChapterDTO updateChapter(UUID chapterId,ChapterUpdateDTO chapterDTO);
    void deleteChapter(UUID chapterId);

}
