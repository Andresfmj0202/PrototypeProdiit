package com.Prodiit.Prototype.Models.Entitys;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table (name = "company")
@JsonIdentityReference(alwaysAsId = true)
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID CompanyId;

    private String name;

    private String description;

    private String imageLogo;

    @ManyToMany(mappedBy = "companies")
    private Set<UserEntity> users = new HashSet<>();

    // Relación de la compañía con sitios
    @JsonManagedReference
    @OneToMany(mappedBy = "company")
    private List<SiteEntity> sites;

    private int siteCount;

    public int getSiteCount() {
        return sites.size(); // Método para obtener el número de sitios asociados
    }


    public CompanyEntity() {
    }

    public CompanyEntity(UUID CompanyId, String name, String description, String imageLogo, Set<UserEntity> users, List<SiteEntity> sites, int siteCount) {
        this.CompanyId = CompanyId;
        this.name = name;
        this.description = description;
        this.imageLogo = imageLogo;
        this.users = users;
        this.sites = sites;
        this.siteCount = siteCount;
    }

    public UUID getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(UUID id) {
        this.CompanyId = id;
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

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public List<SiteEntity> getSites() {
        return sites;
    }

    public void setSites(List<SiteEntity> sites) {
        this.sites = sites;
    }

    public void setSiteCount(int siteCount) {
        this.siteCount = siteCount;
    }

    public void addSite(SiteEntity site) {
        if (sites == null) {
            sites = new ArrayList<>();
        }
        sites.add(site);
        site.setCompany(this); // Establece la relación bidireccional
        siteCount = sites.size(); // Actualiza el recuento de sitios
    }

    public void removeSite(SiteEntity site) {
        if (sites != null && sites.remove(site)) {
            site.setCompany(null); // Elimina la relación bidireccional
            siteCount = sites.size(); // Actualiza el recuento de sitios
        }
    }
}
