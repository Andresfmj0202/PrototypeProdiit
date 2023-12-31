package com.Prodiit.Prototype.Models.Entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "area")
public class AreaEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "BINARY(16)")
    private UUID areaId;
    private String name;
    private String Type;
    // Utiliza LocalDateTime para almacenar fecha y hora
    private LocalDateTime dateCreated;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "site_id")
    private SiteEntity site;

    @JsonManagedReference
    @OneToMany(mappedBy = "area")
    private List<FileEntity> files;

    public AreaEntity() {

    }

    public AreaEntity(UUID areaId, String name, String type, LocalDateTime dateCreated, SiteEntity site, List<FileEntity> files) {
        this.areaId = areaId;
        this.name = name;
        this.Type = type;
        this.dateCreated = dateCreated;
        this.site = site;
        this.files = files;
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
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public SiteEntity getSite() {
        return site;
    }

    public void setSite(SiteEntity site) {
        this.site = site;
    }

    public List<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(List<FileEntity> files) {
        this.files = files;
    }
}
