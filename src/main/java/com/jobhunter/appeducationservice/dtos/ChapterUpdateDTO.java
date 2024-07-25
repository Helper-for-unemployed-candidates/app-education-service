package com.jobhunter.appeducationservice.dtos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterUpdateDTO {
    private String title;
    private String description;
}
