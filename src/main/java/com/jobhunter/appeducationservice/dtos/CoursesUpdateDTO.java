package com.jobhunter.appeducationservice.dtos;

import lombok.*;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoursesUpdateDTO {

    @Schema(description = "Title of the course", example = "Advanced Java Programming")
    private String title;

    @Schema(description = "Description of the course", example = "Deep dive into Java programming, including advanced topics.")
    private String about;

    @Schema(description = "Price of the course if it is payable", example = "149.99")
    private Double price;

    @Schema(description = "Indicates if the course requires payment", example = "true")
    private boolean isPayable;

    @Schema(description = "Attachment ID related to the course", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID attachmentId;
}


