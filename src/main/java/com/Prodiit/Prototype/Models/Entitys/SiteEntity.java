package com.Prodiit.Prototype.Models.Entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "site")
public class SiteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long siteId;

    private String name;
    private String description;

    // Agregar la relación muchos a uno
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "company_id")  // Nombre de la columna en la tabla de sitios que hace referencia a la compañía
    private CompanyEntity company;

    public SiteEntity() {
    }

    public SiteEntity(long siteId, String name, String description, CompanyEntity company) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
        this.company = company;
    }

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

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
}
