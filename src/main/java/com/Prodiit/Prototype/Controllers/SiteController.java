package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Dtos.AreaDTO;
import com.Prodiit.Prototype.Models.Dtos.SiteDTO;
import com.Prodiit.Prototype.Models.Entitys.AreaEntity;
import com.Prodiit.Prototype.Models.Entitys.CompanyEntity;
import com.Prodiit.Prototype.Respositorys.CompanyRepository;
import com.Prodiit.Prototype.Services.CompanyService;
import com.Prodiit.Prototype.Models.Entitys.SiteEntity;
import com.Prodiit.Prototype.Services.SiteService;
import jakarta.persistence.EntityNotFoundException;
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

    private final CompanyRepository companyRepository;

    @Autowired
    public SiteController(SiteService siteService, CompanyService companyService, CompanyRepository companyRepository) {
        this.siteService = siteService;
        this.companyService = companyService;
        this.companyRepository = companyRepository;
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
                    company.getCompanyId(),  // Utiliza el ID de compañía de la entidad CompanyEntity
                    new ArrayList<AreaDTO>()  // Puedes inicializar esto como una lista vacía o proporcionar áreas si es necesario
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
    public SiteDTO getSiteById(@PathVariable("id") UUID siteId) {
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
                siteEntity.getCompany().getCompanyId(),
                mapAreaEntitiesToAreaDTOs(siteEntity.getAreas())  // Ajusta esto según tu modelo de datos
        );
    }
    private List<AreaDTO> mapAreaEntitiesToAreaDTOs(List<AreaEntity> areaEntities) {
        List<AreaDTO> areaDTOs = new ArrayList<>();
        for (AreaEntity areaEntity : areaEntities) {
            areaDTOs.add(mapAreaEntityToAreaDTO(areaEntity));
        }
        return areaDTOs;
    }

    private AreaDTO mapAreaEntityToAreaDTO(AreaEntity areaEntity) {
        return new AreaDTO(
                areaEntity.getAreaId(),
                areaEntity.getName(),
                areaEntity.getType(),
                areaEntity.getDateCreated(),
                areaEntity.getSite().getSiteId(),
                areaEntity.getStatusArea()
        );
    }

    @PutMapping("/{id}")
    public SiteDTO updateSite(@PathVariable("id") UUID siteId, @RequestBody SiteDTO siteDTO) {
        // Convierte el DTO a una entidad SiteEntity
        SiteEntity siteEntity = new SiteEntity();
        siteEntity.setSiteId(siteId);
        siteEntity.setName(siteDTO.getName());
        siteEntity.setDescription(siteDTO.getDescription());

        // Busca la instancia de CompanyEntity a partir del UUID proporcionado en SiteDTO
        UUID companyId = siteDTO.getCompanyId();
        CompanyEntity companyEntity = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Compañía no encontrada"));

        // Asigna la instancia de CompanyEntity a SiteEntity
        siteEntity.setCompany(companyEntity);

        // Llama al servicio para actualizar la entidad
        siteEntity = siteService.updateSite(siteId, siteEntity);

        // Mapea la entidad actualizada a un nuevo DTO, incluyendo la lista de áreas
        return new SiteDTO(
                siteEntity.getSiteId(),
                siteEntity.getName(),
                siteEntity.getDescription(),
                siteEntity.getCompany().getCompanyId(),
                mapAreaEntitiesToAreaDTOs(siteEntity.getAreas())  // Ajusta esto según tu modelo de datos
        );
    }

    public static SiteDTO getSiteDTO(SiteEntity siteEntity) {
        SiteDTO updatedSiteDTO = new SiteDTO();
        updatedSiteDTO.setSiteId(siteEntity.getSiteId());
        updatedSiteDTO.setName(siteEntity.getName());
        updatedSiteDTO.setDescription(siteEntity.getDescription());
        updatedSiteDTO.setCompanyId(siteEntity.getCompany().getCompanyId());

        return updatedSiteDTO;
    }

    //traer los sites a travez de un id de la compania
    @GetMapping("/company/{companyId}")
    public List<SiteDTO> getSiteByCompanyId(@PathVariable UUID companyId) {
        List<SiteEntity> siteEntities = siteService.getSiteByCompanyId(companyId);
        // No es necesario convertir a SiteDTO aquí
        return siteService.mapSiteEntitiesToDTOs(siteEntities);
    }


    @DeleteMapping("/{id}")
    public void deleteSite(@PathVariable("id") UUID siteId){
        siteService.deleteSite(siteId);
    }

}
