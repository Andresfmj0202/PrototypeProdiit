package com.Prodiit.Prototype.Models.Dtos;

import java.util.UUID;

public class CompanyDTO {
    private UUID companyId;
    private String name;
    private String description;
    private String imageLogo;

    // Constructores, getters y setters

    public CompanyDTO() {
        // Constructor vacío
    }

    public CompanyDTO(UUID companyId, String name, String description, String imageLogo) {
        this.companyId = companyId;
        this.name = name;
        this.description = description;
        this.imageLogo = imageLogo;
    }

    // Getters y setters para los campos

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
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

    public String getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(String imageLogo) {
        this.imageLogo = imageLogo;
    }
}