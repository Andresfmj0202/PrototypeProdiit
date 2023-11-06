package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Dtos.AreaDTO;
import com.Prodiit.Prototype.Models.Entitys.AreaEntity;
import com.Prodiit.Prototype.Models.Entitys.SiteEntity;
import com.Prodiit.Prototype.Services.AreaService;
import com.Prodiit.Prototype.Services.SiteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/area")
public class AreaController {
    private final AreaService areaService;
    private final SiteService siteService;
    private final ModelMapper modelMapper;

    @Autowired
    public AreaController(AreaService areaService, SiteService siteService, ModelMapper modelMapper) {
        this.areaService = areaService;
        this.siteService = siteService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public AreaDTO createAndSaveArea(@RequestBody AreaDTO areaDTO) {
        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setName(areaDTO.getName());
        areaEntity.setType(areaDTO.getType());
        areaEntity.setDateCreated(LocalDateTime.now());
        areaEntity.setStatusArea(true);

        UUID siteId = areaDTO.getSiteId();  // Obtiene el ID del sitio desde el DTO

        // Obtén el sitio relacionado con el ID
        SiteEntity site = siteService.getSiteById(siteId).orElse(null);

        if (site != null) {
            // Establece el sitio en el área
            areaEntity.setSite(site);

            // Crea y guarda el área
            areaEntity = areaService.createAndSaveArea(areaEntity);

            // Mapea la entidad a un nuevo DTO
            return new AreaDTO(
                    areaEntity.getAreaId(),
                    areaEntity.getName(),
                    areaEntity.getType(),
                    areaEntity.getDateCreated(),
                    siteId,  // Utiliza el mismo ID de sitio que se proporcionó
                    areaEntity.isStatusArea()
            );
        } else {
            // Maneja el caso en el que el sitio no existe
            return null;  // O puedes devolver un ResponseEntity con un código de error apropiado
        }
    }

    @GetMapping("/all")
    public List<AreaDTO> getAllAreas() {
        List<AreaDTO> areaEntities = areaService.getAllAreas();
        return areaEntities;
    }

    @GetMapping("/{areaId}")
    public AreaDTO getAreaById(@PathVariable UUID areaId) {
        Optional<AreaEntity> areaEntityOptional = areaService.getAreaById(areaId);
        if (areaEntityOptional.isPresent()) {
            AreaEntity areaEntity = areaEntityOptional.get();
            // Mapea la entidad a un DTO
            return mapAreaEntityToDTO(areaEntity);
        } else {
            // Maneja el caso en el que el área no existe (puedes devolver null o un ResponseEntity apropiado)
            return null;
        }
    }

    public AreaDTO mapAreaEntityToDTO(AreaEntity areaEntity) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setAreaId(areaEntity.getAreaId());
        areaDTO.setName(areaEntity.getName());
        areaDTO.setType(areaEntity.getType());
        areaDTO.setDateCreated(areaEntity.getDateCreated());
        areaDTO.setSiteId(areaEntity.getSite().getSiteId());
        return areaDTO;
    }
    @GetMapping("/name/{name}")
    public List<AreaDTO> getAreaByName(@PathVariable String name) {
        return areaService.getAreaByName(name);
    }

    @PutMapping("/{areaId}")
    public AreaDTO updateArea(@PathVariable UUID areaId, @RequestBody AreaDTO areaDTO) {
        return areaService.updateArea(areaId, areaDTO);
    }

    @DeleteMapping("/{areaId}")
    public void deleteAreaById(@PathVariable UUID areaId) {
        areaService.deleteAreaById(areaId);
    }

    // Cambiar el estado activo/inactivo de un area
    @PutMapping("/status/{id}")
    public ResponseEntity<AreaDTO> updateStatusArea(@PathVariable UUID id) {
        AreaEntity updatedAreaEntity = areaService.updateStatusArea(id);

        // Convierte la entidad actualizada a un DTO y devuélvelo en la respuesta
        AreaDTO updatedAreaDTO = new AreaDTO();
        updatedAreaDTO.setName(updatedAreaEntity.getName());
        updatedAreaDTO.setType(updatedAreaEntity.getType());
        updatedAreaDTO.setStatusArea(updatedAreaEntity.isStatusArea());

        return ResponseEntity.ok(updatedAreaDTO);
    }
}
