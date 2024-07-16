package com.jobhunter.appeducationservice.entity;

import com.jobhunter.appeducationservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Assignment extends AbsUUIDEntity {
    private String taskDescription;
    @OneToOne
    private Attachment attachment;
}
