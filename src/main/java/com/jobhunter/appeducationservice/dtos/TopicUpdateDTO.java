package com.jobhunter.appeducationservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicUpdateDTO {
    @NotBlank(message = "title cannot be blank")
    private String title;
    @NotBlank(message = "description cannot be blank")
    private String description;
    @NotBlank(message = "chapterId cannot be blank")
    private UUID chapterId;
//    @NotBlank(message = "chapterId cannot be blank")
//    private UUID attachmentId;
//    @NotBlank(message = "chapterId cannot be blank")
//    private UUID assignmentId;


}
