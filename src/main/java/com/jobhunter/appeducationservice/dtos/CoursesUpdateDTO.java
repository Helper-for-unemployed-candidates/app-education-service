package com.jobhunter.appeducationservice.dtos;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoursesUpdateDTO {
    private String title;
    private String about;
    private Double price;
    private boolean isPayable;
    private UUID attachmentId;
}



