package com.jobhunter.appeducationservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {

    @Schema(description = "Title of the course", example = "Java Programming Basics", required = true)
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Schema(description = "Description of the course", example = "Learn the basics of Java programming.", required = true)
    @NotBlank(message = "About cannot be blank")
    private String about;

    @Schema(description = "Indicates if the course requires payment", example = "true")
    private boolean isPayable;

    @Schema(description = "Price of the course if it is payable", example = "99.99")
    private Double price;

    @Schema(description = "Attachment ID related to the course", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID attachmentId;
}
