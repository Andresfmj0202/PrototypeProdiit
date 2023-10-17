package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Dtos.SiteDTO;
import com.Prodiit.Prototype.Models.Entitys.CompanyEntity;
import com.Prodiit.Prototype.Services.CompanyService;
import com.Prodiit.Prototype.Models.Entitys.SiteEntity;
import com.Prodiit.Prototype.Services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/site")
public class SiteController {

    private final SiteService siteService;
    private final CompanyService companyService;

    @Autowired
    public SiteController(SiteService siteService, CompanyService companyService) {
        this.siteService = siteService;
        this.companyService = companyService;
    }

@PostMapping
    public SiteDTO createAndSaveSite(@RequestBody SiteDTO siteDTO) {
        SiteEntity siteEntity = new SiteEntity();
        siteEntity.setName(siteDTO.getName());
        siteEntity.setDescription(siteDTO.getDescription());

        UUID companyId = siteDTO.getCompanyId();  // Obtiene el ID de la compañía desde el DTO

        // Obtén la compañía relacionada con el ID
        CompanyEntity company = companyService.getCompanyById(companyId).orElse(null);

        if (company != null) {
            // Establece la compañía en el sitio
            siteEntity.setCompany(company);

            // Crea y guarda el sitio
            siteEntity = siteService.createAndSaveSite(siteEntity);

            // Mapea la entidad a un nuevo DTO
            return new SiteDTO(
                    siteEntity.getSiteId(),
                    siteEntity.getName(),
                    siteEntity.getDescription(),
                    companyId  // Utiliza el mismo ID de compañía que se proporcionó
            );
        } else {
            // Maneja el caso en el que la compañía no existe
            return null;
        }
    }
    @GetMapping("/all")
    public List<SiteDTO> getAllSites() {
        List<SiteEntity> siteEntities = siteService.getAllSites();
        return mapSiteEntitiesToDTOs(siteEntities);
    }

    @GetMapping("/name/{name}")
    public List<SiteDTO> getSiteByName(@PathVariable String name) {
        List<SiteEntity> siteEntities = siteService.getSiteByName(name);
        return mapSiteEntitiesToDTOs(siteEntities);
    }

    @GetMapping("/{id}")
    public SiteDTO getSiteById(@PathVariable("id") long siteId) {
        Optional<SiteEntity> siteEntityOptional = siteService.getSiteById(siteId);
        if (siteEntityOptional.isPresent()) {
            SiteEntity siteEntity = siteEntityOptional.get();
            return mapSiteEntityToDTO(siteEntity);
        } else {
            // Maneja el caso en el que el sitio no existe
            return null;
        }
    }

    //mapeo de entidad a DTO y dto a entidad

    private List<SiteDTO> mapSiteEntitiesToDTOs(List<SiteEntity> siteEntities) {
        List<SiteDTO> siteDTOs = new ArrayList<>();
        for (SiteEntity siteEntity : siteEntities) {
            siteDTOs.add(mapSiteEntityToDTO(siteEntity));
        }
        return siteDTOs;
    }

    private SiteDTO mapSiteEntityToDTO(SiteEntity siteEntity) {
        return new SiteDTO(
                siteEntity.getSiteId(),
                siteEntity.getName(),
                siteEntity.getDescription(),
                siteEntity.getCompany().getCompanyId()  // Ajusta esto según tu modelo de datos
        );
    }

    @PutMapping("/{id}")
    public SiteEntity updateSite(@PathVariable("id") long siteId,@RequestBody SiteEntity siteEntity) {
        return siteService.updateSite(siteId,siteEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteSite(@PathVariable("id") long siteId){
        siteService.deleteSite(siteId);
    }

}
