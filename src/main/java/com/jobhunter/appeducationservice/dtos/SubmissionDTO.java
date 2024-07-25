package com.jobhunter.appeducationservice.dtos;


import com.jobhunter.appeducationservice.entity.Assignment;
import com.jobhunter.appeducationservice.entity.Attachment;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.UUID;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmissionDTO {
    private UUID userId;
    private byte rate;
    private String feedBack;
    private UUID assignmentId;
    private UUID attachmentId;
}
