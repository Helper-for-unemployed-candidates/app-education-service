package com.jobhunter.appeducationservice.repository;

import com.jobhunter.appeducationservice.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
