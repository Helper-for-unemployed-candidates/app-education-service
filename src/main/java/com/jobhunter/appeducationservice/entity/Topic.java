package com.jobhunter.appeducationservice.entity;

import com.jobhunter.appeducationservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.*;
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
    @OneToOne(cascade = CascadeType.PERSIST)
    private Attachment attachment;
    @OneToOne
    private Assignment assignment;

}
