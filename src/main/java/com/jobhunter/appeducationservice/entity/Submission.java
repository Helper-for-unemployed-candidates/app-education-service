package com.jobhunter.appeducationservice.entity;

import com.jobhunter.appeducationservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Submission extends AbsUUIDEntity {
    private UUID userId;
    private byte rate;
    private String feedBack;
    @ManyToOne
    private Assignment assignment;
    @OneToOne
    private Attachment attachment;

}
