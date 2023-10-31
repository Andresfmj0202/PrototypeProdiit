package com.Prodiit.Prototype.Models.Dtos;

import java.util.List;
import java.util.UUID;

public class SiteDTO {
    private UUID siteId;
    private String name;
    private String description;
    private UUID companyId;  // Usamos un UUID para el ID de la compañía
    private List<AreaDTO> areasDTOList;

    public SiteDTO() {
    }

    public SiteDTO(UUID siteId, String name, String description, UUID companyId, List<AreaDTO> areasDTOList) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
        this.areasDTOList = areasDTOList;
    }

    public UUID getSiteId() {
        return siteId;
    }

    public void setSiteId(UUID siteId) {
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

    public List<AreaDTO> getAreasDTOList() {
        return areasDTOList;
    }

    public void setAreasDTOList(List<AreaDTO> areasDTOList) {
        this.areasDTOList = areasDTOList;
    }
}
