package com.jobhunter.appeducationservice.entity;

import com.jobhunter.appeducationservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Topic extends AbsUUIDEntity {
    private String title;
    private String description;
    @ManyToOne
    private Chapter chapter;

    private Attachment attachment;
    private Assignment assignment;

}
