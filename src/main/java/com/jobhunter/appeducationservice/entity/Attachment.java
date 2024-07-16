package com.jobhunter.appeducationservice.entity;


import com.jobhunter.appeducationservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.Entity;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Attachment extends AbsUUIDEntity {
    private String originalName;
    private String path;
    private long size;
    private String type;
}
