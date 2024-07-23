package com.jobhunter.appeducationservice.entity;

import com.jobhunter.appeducationservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.ws.rs.DefaultValue;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Course extends AbsUUIDEntity {
    private String title;
    private String about;
    @DefaultValue(value = "true")
    private boolean isPayable;
    private Double price;
    @OneToOne
    private Attachment attachment;
}
