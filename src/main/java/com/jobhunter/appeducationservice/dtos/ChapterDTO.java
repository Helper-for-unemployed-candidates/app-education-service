package com.jobhunter.appeducationservice.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private UUID courseId;
}
