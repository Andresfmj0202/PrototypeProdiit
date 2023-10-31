package com.Prodiit.Prototype.Models.Dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class AreaDTO {

    private UUID areaId;
    private String name;
    private String type;
    private LocalDateTime dateCreated;

    private UUID siteId;

    public AreaDTO() {

    }

    public AreaDTO(UUID areaId, String name, String type, LocalDateTime dateCreated, UUID siteId) {
        this.areaId = areaId;
        this.name = name;
        this.type = type;
        this.dateCreated = dateCreated;
        this.siteId = siteId;
    }

    public UUID getAreaId() {
        return areaId;
    }

    public void setAreaId(UUID areaId) {
        this.areaId = areaId;
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public UUID getSiteId() {
        return siteId;
    }

    public void setSiteId(UUID siteId) {
        this.siteId = siteId;
    }
}
