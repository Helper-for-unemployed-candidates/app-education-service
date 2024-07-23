package com.jobhunter.appeducationservice.controllers;

import com.jobhunter.appeducationservice.dtos.ChapterDTO;
import com.jobhunter.appeducationservice.dtos.ChapterUpdateDTO;
import com.jobhunter.appeducationservice.service.serives.ChapterService;
import com.jobhunter.appeducationservice.shit.aop.Authorize;
import com.jobhunter.appeducationservice.shit.enums.RoleEnum;
import com.jobhunter.appeducationservice.shit.payload.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/api/chapter")
@RequiredArgsConstructor
public class ChapterController {
    private final ChapterService chapterService;

    @GetMapping("/{courseId}")
    Response<List<ChapterDTO>> getAllChapterByCourseId(@PathVariable UUID courseId) {
        return Response.successResponse(chapterService.getAllChapterByCourseId(courseId));
    }

    @PostMapping("/save")
    @Authorize(permissions = RoleEnum.COMPANY)
    Response<ChapterDTO> saveChapter(@RequestBody ChapterDTO chapterDTO) {
        return Response.successResponse(chapterService.saveChapter(chapterDTO));
    }

    @PutMapping("/update/{chapterId}")
    @Authorize(permissions = RoleEnum.COMPANY)
    Response<ChapterDTO> updateChapter(@PathVariable UUID chapterId, @RequestBody ChapterUpdateDTO chapterDTO) {
        return Response.successResponse(chapterService.updateChapter(chapterId, chapterDTO));
    }

    @DeleteMapping("/{chapterId}")
    @Authorize(permissions = RoleEnum.ADMIN)
    Response<Void> deleteChapter(@PathVariable UUID chapterId) {
        chapterService.deleteChapter(chapterId);
        return Response.successResponse();
    }

}
