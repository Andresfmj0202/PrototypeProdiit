package com.Prodiit.Prototype.Models.Dtos;

import java.util.UUID;

public class SiteDTO {
    private long siteId;
    private String name;
    private String description;
    private UUID companyId;  // Usamos un UUID para el ID de la compañía

    public SiteDTO() {
        // Constructor vacío
    }

    public SiteDTO(long siteId, String name, String description, UUID companyId) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
    }

    // Getters y setters para los campos

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }
}
