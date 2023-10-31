package com.Prodiit.Prototype.Models.Entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "site")
public class SiteEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "BINARY(16)")
    private UUID siteId;

    private String name;
    private String description;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @JsonManagedReference
    @OneToMany(mappedBy = "site")
    private List<AreaEntity> areas;

    // Constructor, getters y setters



    public SiteEntity() {
    }

    public SiteEntity(UUID siteId, String name, String description, CompanyEntity company, List<AreaEntity> areas) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
        this.company = company;
        this.areas = areas;
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

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public List<AreaEntity> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaEntity> areas) {
        this.areas = areas;
    }
}
