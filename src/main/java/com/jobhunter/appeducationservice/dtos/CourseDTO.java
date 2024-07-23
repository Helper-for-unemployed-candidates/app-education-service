package com.jobhunter.appeducationservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String about;
    private boolean isPayable;
    private Double price;
    private UUID attachmentId;
}



