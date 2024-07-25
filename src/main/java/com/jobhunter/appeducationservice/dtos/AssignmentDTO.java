package com.jobhunter.appeducationservice.dtos;

import com.jobhunter.appeducationservice.entity.Assignment;
import com.jobhunter.appeducationservice.entity.Attachment;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentDTO {
    private String taskDescription;
    private UUID attachmentId;
}

