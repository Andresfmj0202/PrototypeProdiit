package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Dtos.CompanyDTO;
import com.Prodiit.Prototype.Models.Dtos.SiteDTO;
import com.Prodiit.Prototype.Models.Entitys.CompanyEntity;
import com.Prodiit.Prototype.Models.Entitys.SiteEntity;
import com.Prodiit.Prototype.Services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.Prodiit.Prototype.Controllers.SiteController.getSiteDTO;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyController(CompanyService companyService, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    //crear empresa
    @PostMapping
    public CompanyDTO createCompany(@RequestBody CompanyDTO companyDTO) {
        CompanyEntity companyEntity = companyService.mapToEntity(companyDTO); // Convierte el DTO a entidad
        companyEntity = companyService.createAndSaveCompany(companyEntity);

        // Ahora, convierte la entidad creada nuevamente a un DTO para devolver en la respuesta
        return companyService.mapToDTO(companyEntity);
    }


    //obtener todos las empresas
    @GetMapping("/all")
    public List<CompanyDTO> getAllCompanies() {
        List<CompanyEntity> companyEntities = companyService.getAllCompanies();
        List<CompanyDTO> companyDTOs = new ArrayList<>();

        for (CompanyEntity entity : companyEntities) {
            // Convierte las entidades de sitios en DTOs de sitios
            List<SiteDTO> siteDTOs = entity.getSites().stream()
                    .map(siteEntity -> convertSiteEntityToDTO(siteEntity))
                    .collect(Collectors.toList());

            // Crea instancias de CompanyDTO utilizando el constructor modificado
            CompanyDTO dto = new CompanyDTO(
                    entity.getCompanyId(),
                    entity.getName(),
                    entity.getDescription(),
                    entity.getImageLogo(),
                    entity.getSiteCount(),
                    siteDTOs // Usa la lista de DTOs de sitios
            );

            companyDTOs.add(dto);
        }

        return companyDTOs;
    }

    // Método para convertir SiteEntity en SiteDTO
    private SiteDTO convertSiteEntityToDTO(SiteEntity siteEntity) {
        // Aquí debes implementar la lógica para crear un SiteDTO a partir de un SiteEntity
        // Puedes copiar los campos necesarios y mapearlos según corresponda
        return getSiteDTO(siteEntity);
    }

    //obtener empresa por id
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable UUID id) {
        Optional<CompanyEntity> company = companyService.getCompanyById(id);
        if (company.isPresent()) {
            CompanyEntity companyEntity = company.get();
            // Utilizar ModelMapper para convertir la entidad a un DTO
            CompanyDTO dto = modelMapper.map(companyEntity, CompanyDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            // Devolver una respuesta 404 Not Found si la empresa no existe
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener empresa por nombre
    @GetMapping("/name/{name}")
    public List<CompanyDTO> getCompanyByName(@PathVariable String name) {
        List<CompanyEntity> companies = companyService.getCompanyByName(name);

        // Utilizar ModelMapper para mapear todas las empresas a DTO
        return companies.stream()
                .map(companyEntity -> modelMapper.map(companyEntity, CompanyDTO.class))
                .collect(Collectors.toList());
    }

    //actualizar empresa
    @PutMapping(value = "/{id}", consumes = "application/json;charset=UTF-8")
    public CompanyDTO updateCompany(@PathVariable UUID id, @RequestBody CompanyDTO companyDTO) {
        // Convierte el DTO a una entidad CompanyEntity
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCompanyId(id);
        companyEntity.setName(companyDTO.getName());
        companyEntity.setDescription(companyDTO.getDescription());
        companyEntity.setImageLogo(companyDTO.getImageLogo());

        // Llama al servicio para actualizar la entidad
        companyEntity = companyService.updateCompany(id, companyEntity);

        // Convierte la entidad actualizada a un DTO y devuélvelo
        CompanyDTO updatedCompanyDTO = new CompanyDTO();
        updatedCompanyDTO.setCompanyId(companyEntity.getCompanyId());
        updatedCompanyDTO.setName(companyEntity.getName());
        updatedCompanyDTO.setDescription(companyEntity.getDescription());
        updatedCompanyDTO.setImageLogo(companyEntity.getImageLogo());

        return updatedCompanyDTO;
    }

    //borrar empresa
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable UUID id){
        companyService.deleteCompany(id);
    }

    //obtener el número de sitios asociados a una empresa
    @GetMapping("/{id}/sites")
    public int getSiteCountForCompany(@PathVariable UUID id){
        return companyService.getSiteCountForCompany(id);
    }
}

