package com.jobhunter.appeducationservice.service;

import com.jobhunter.appeducationservice.dtos.ChapterDTO;
import com.jobhunter.appeducationservice.dtos.ChapterUpdateDTO;
import com.jobhunter.appeducationservice.entity.Chapter;
import com.jobhunter.appeducationservice.entity.Course;
import com.jobhunter.appeducationservice.exceptions.RestException;
import com.jobhunter.appeducationservice.mappers.ChapterMapper;
import com.jobhunter.appeducationservice.repository.ChapterRepository;
import com.jobhunter.appeducationservice.repository.CourseRepository;
import com.jobhunter.appeducationservice.service.serives.ChapterService;
import com.jobhunter.appeducationservice.shit.payload.UserPrincipal;
import com.jobhunter.appeducationservice.shit.utils.ConstantFields;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {
    private final ChapterRepository chapterRepository;
    private final ChapterMapper mapper;
    private final CourseRepository courseRepository;

    @Override
    @Cacheable(value = "chapterDTO", key = "#courseId")
    public List<ChapterDTO> getAllChapterByCourseId(UUID courseId) {

        List<Chapter> chapter = chapterRepository.findByCourseId(courseId);

        return mapper.toChapterDTOPage(chapter);

    }


    @Override
//    @Transactional  bittadan kopro ga ishlatilinadi
    public ChapterDTO saveChapter(ChapterDTO chapterDTO) {
        UserPrincipal user = ConstantFields.currentUser();
        Chapter chapter = mapper.chapterDTOToChapter(chapterDTO);
        chapter.setCreatedById(user.getId());
        Course course = courseRepository
                .findById(chapterDTO.getCourseId())
                .orElseThrow(()->RestException.restThrow("Course Not Found"));
        chapter.setCourse(course);
        chapterRepository.save(chapter);
        return chapterDTO;
    }

    @Override
    @CachePut(value = "chapterDTO", key = "#keyGenerator")
    public ChapterDTO updateChapter(UUID chapterId, ChapterUpdateDTO chapterDTO) {
        UserPrincipal user = ConstantFields.currentUser();
        Chapter existingChapter = chapterRepository
                .findByIdAndCreatedById(chapterId, user.getId())
                .orElseThrow(
                        () -> RestException.restThrow("chapter is not found by given id: " + chapterId));
        mapper.updateChapter(existingChapter, chapterDTO);
        chapterRepository.save(existingChapter);
        return mapper.chapterToChapterDTO(existingChapter);
    }

    @Override
    @CacheEvict(value = "deleteChapter", key = "#id")
    public void deleteChapter(UUID id) {
        UserPrincipal user = ConstantFields.currentUser();
//        chapterRepository.findById(id).orElseThrow(() -> RestException.restThrow("chapter is not found by given id: " + id)); // shart emas
        chapterRepository.deleteByIdAndCreatedById(id, user.getId());
    }


    /// other should be logically implemented
//    UserPrincipal user = ConstantFields.currentUser(); // zapros jonatgan userni id sini oladi



}
