package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Entitys.CompanyEntity;
import com.Prodiit.Prototype.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    //crear empresa
    @PostMapping
    public Object createCompany(@RequestBody CompanyEntity companyEntity){

        return companyService.createAndSaveCompany(companyEntity);
    }


    //obtener todos las empresas
    @GetMapping
    public List<CompanyEntity> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    //obtener empresa por id
    @GetMapping("/{id}")
    public Optional<CompanyEntity> getCompanyById(@PathVariable UUID id){
        return companyService.getCompanyById(id);
    }

    //obtener empresa por nombre
    @GetMapping("/name/{name}")
    public Optional<CompanyEntity> getCompanyByName(@PathVariable String name){
        return companyService.getCompanyByName(name);
    }

    //actualizar empresa
    @PutMapping("/{id}")
    public CompanyEntity updateCompany(@PathVariable UUID id, @RequestBody CompanyEntity company){
        company.setCompanyId(id);
        return companyService.updateCompany(id,company);
    }

    //borrar empresa
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable UUID id){
        companyService.deleteCompany(id);
    }
}

