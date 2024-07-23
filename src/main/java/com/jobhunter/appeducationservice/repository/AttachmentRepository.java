package com.jobhunter.appeducationservice.repository;

import com.jobhunter.appeducationservice.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
