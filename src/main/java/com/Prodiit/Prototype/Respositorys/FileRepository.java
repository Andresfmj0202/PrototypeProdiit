package com.Prodiit.Prototype.Respositorys;

import com.Prodiit.Prototype.Models.Entitys.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> {
}
