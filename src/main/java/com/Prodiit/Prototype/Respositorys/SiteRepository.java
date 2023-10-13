package com.Prodiit.Prototype.Respositorys;

import com.Prodiit.Prototype.Models.Entitys.SiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteRepository extends JpaRepository<SiteEntity, Long> {
    List<SiteEntity> findSiteByName(String name);
}
