package com.Prodiit.Prototype.Models.Dtos;

import java.util.UUID;

public class FileDTO {

    private UUID fileId;
    private String name;
    private String type;
    private long size;
    private UUID areaId;
    private boolean status;

    public FileDTO() {
    }

    public FileDTO(UUID fileId, String name, String type, long size, UUID areaId, boolean status) {
        this.fileId = fileId;
        this.name = name;
        this.type = type;
        this.size = size;
        this.areaId = areaId;
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

    public UUID getAreaId() {
        return areaId;
    }

    public void setAreaId(UUID areaId) {
        this.areaId = areaId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
