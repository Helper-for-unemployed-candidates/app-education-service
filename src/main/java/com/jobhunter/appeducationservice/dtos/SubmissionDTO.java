package com.jobhunter.appeducationservice.dtos;


import com.jobhunter.appeducationservice.entity.Assignment;
import com.jobhunter.appeducationservice.entity.Attachment;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.mapstruct.ap.shaded.freemarker.template.utility.XmlEscape;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmissionDTO {
    @NotNull(message = "User id cannot be null")
    private UUID userId;
    private byte rate;
    @NotBlank(message = "feedback cannot be null")
    private String feedBack;
    @NotNull(message = "assignmentId cannot be null")
    private UUID assignmentId;
    @NotNull(message = "attachmentId cannot be null")
    private UUID attachmentId;
}
