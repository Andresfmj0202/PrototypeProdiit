package com.Prodiit.Prototype.Models.Entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "BINARY(16)")
    private UUID fileId;

    private String name;

    private String type;

    private long size;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaEntity area;

    private boolean status;
    public FileEntity() {
    }

    public FileEntity(UUID fileId, String name, String type, long size, AreaEntity area, boolean status) {
        this.fileId = fileId;
        this.name = name;
        this.type = type;
        this.size = size;
        this.area = area;
        this.status = status;
    }

    public UUID getFileId() {
        return fileId;
    }

    public void setFileId(UUID fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public AreaEntity getArea() {
        return area;
    }

    public void setArea(AreaEntity area) {
        this.area = area;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
