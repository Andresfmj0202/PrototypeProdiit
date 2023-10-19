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


    public int getSiteCount() {
        return sites.size(); // Método para obtener el número de sitios asociados
    }
    public CompanyEntity() {
    }

    public CompanyEntity(UUID CompanyId, String name, String description, String imageLogo, Set<UserEntity> users, List<SiteEntity> sites) {
        this.CompanyId = CompanyId;
        this.name = name;
        this.description = description;
        this.imageLogo = imageLogo;
        this.users = users;
        this.sites = sites;
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
}
