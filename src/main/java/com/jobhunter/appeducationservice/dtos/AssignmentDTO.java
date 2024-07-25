package com.jobhunter.appeducationservice.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentDTO {
    @NotBlank(message = "task description should be filled")
    private String taskDescription;
    private UUID attachmentId;
}

