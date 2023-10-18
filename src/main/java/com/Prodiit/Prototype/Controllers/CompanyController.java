package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Dtos.CompanyDTO;
import com.Prodiit.Prototype.Models.Entitys.CompanyEntity;
import com.Prodiit.Prototype.Services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return companyService.mapToDTOList(companyEntities);
    }

    //obtener empresa por id
    @GetMapping("/{id}")
    public CompanyDTO getCompanyById(@PathVariable UUID id){
        Optional<CompanyEntity> company = companyService.getCompanyById(id);
        if (company.isPresent()) {
            CompanyEntity companyEntity = company.get();
            // Convertir la entidad a un DTO
            return new CompanyDTO(
                    companyEntity.getCompanyId(),
                    companyEntity.getName(),
                    companyEntity.getDescription(),
                    companyEntity.getImageLogo()
            );
        } else {
            // Manejar el caso en el que la empresa no existe
            return null;
        }
    }


    //obtener empresa por nombre
    @GetMapping("/name/{name}")
    public List<CompanyDTO> getCompanyByName(@PathVariable String name) {
        List<CompanyEntity> companies = companyService.getCompanyByName(name);

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

